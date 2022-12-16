package ch.coll.aoc.cookie;

import com.sun.jna.platform.win32.Crypt32Util;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.sql.*;
import java.util.Base64;

public class ChromeCookie {

    private final static String COOKIE_FILE_PATH = System.getenv("LocalAppData") + "\\Google\\Chrome\\User Data\\Default\\Network\\Cookies";
    private final static String COOKIE_KEY_PATH = System.getenv("LocalAppData") + "\\Google\\Chrome\\User Data\\Local State";

    public static String getCookie(String site, String cookieName) {
        JSONParser jsonParser = new JSONParser();

        try {
            //Get the key used to encrypt the cookie and decrypt it
            FileReader reader = new FileReader(COOKIE_KEY_PATH);
            JSONObject localState = (JSONObject) jsonParser.parse(reader);

            byte[] encryptedKey = removeBytesFromKey(Base64.getDecoder().decode(((JSONObject) localState.get("os_crypt")).get("encrypted_key").toString().getBytes()), 0, 5);
            byte[] decryptedKey = Crypt32Util.cryptUnprotectData(encryptedKey);

            //Get the cookie from the sqlite3 database
            Connection cookies = connect();
            Statement statement = cookies.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT encrypted_value FROM Cookies WHERE host_key = '" + site + "' AND name = '" + cookieName + "'");

            byte[] cookie = resultSet.getBytes("encrypted_value");

            cookies.close();

            //decrypt the cookie using the decrypted key and return it
            SecretKey secretKey = new SecretKeySpec(decryptedKey, "AES");
            byte[] cipherText = getBytesFromKey(cookie, 3, cookie.length - 3);
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            AlgorithmParameterSpec gcmIv = new GCMParameterSpec(128, cipherText, 0, 12);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmIv);

            return ByteArrayToString((cipher.doFinal(cipherText, 12, cipherText.length - 12)));
        } catch (IOException | ParseException | SQLException | InvalidKeyException | BadPaddingException |
                 IllegalBlockSizeException | InvalidAlgorithmParameterException | NoSuchAlgorithmException |
                 NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:" + COOKIE_FILE_PATH;
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    private static byte[] removeBytesFromKey(byte[] key, int start, int length) {
        byte[] cutKey = new byte[key.length - length];
        int index = 0;

        for (int i = 0; i < key.length; i++) {
            if (i < start || i > start + length - 1) {
                cutKey[index++] = key[i];
            }
        }

        return cutKey;
    }

    private static byte[] getBytesFromKey(byte[] key, int start, int length) {
        byte[] cutKey = new byte[length];

        for (int i = 0; i < length; i++) {
            cutKey[i] = key[start + i];
        }

        return cutKey;
    }

    private static String ByteArrayToString(byte[] hexByteArray) {
        StringBuilder string = new StringBuilder();

        for (byte b : hexByteArray) {
            string.append((char) b);
        }

        return string.toString();
    }
}
