package ch.coll.aoc;

import ch.coll.aoc.cookie.ChromeCookie;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

public class Input {
    private static String SESSION_ID = ChromeCookie.getCookie(".adventofcode.com", "session");
    private static LocalDate DATE = LocalDate.now();
    private static String BASE_URL = "https://adventofcode.com/%d/day/%d/input";
    private static String BASE_FILE = "input/%d/day%d.txt";

    public static void getInput() {
        if(DATE.getYear() < 2015 || DATE.getYear() > LocalDate.now().getYear()) {
            System.out.println("Invalid year to get input");
            return;
        } else if (DATE.getMonth() != LocalDate.of(1900, 12, 01).getMonth()) {
            System.out.println("Invalid month to get input");
            return;
        } else if(DATE.getDayOfMonth() > 25) {
            System.out.println("Invalid day of month to get input");
            return;
        }

        if (new File(String.format(BASE_FILE, DATE.getYear(), DATE.getDayOfMonth())).exists()) {
            return;
        } else if (!new File("input/" + DATE.getYear()).exists()) {
            new File("input/" + DATE.getYear()).mkdir();
        }

        try {
            URL url = new URL(String.format(BASE_URL, DATE.getYear(), DATE.getDayOfMonth()));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Cookie", "session=" + SESSION_ID);

            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = con.getInputStream();
                FileOutputStream outputStream = new FileOutputStream(String.format(BASE_FILE, DATE.getYear(), DATE.getDayOfMonth()));

                int bytesRead = -1;
                byte[] buffer = new byte[4096];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                outputStream.close();
                inputStream.close();
            } else {
                System.out.println("GET request did not work.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getEntireInput() {
        getInput();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(String.format(BASE_FILE, DATE.getYear(), DATE.getDayOfMonth())));

            String file = "";
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                file += currentLine + "\n";
            }

            return file;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void getResultToClipboard(String result) {
        StringSelection selection = new StringSelection(result);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }

    public static void setSessionId(String NEW_SESSION_ID) {
        SESSION_ID = NEW_SESSION_ID;
    }

    public static void setDate(LocalDate NEW_DATE) {
        DATE = NEW_DATE;
    }
}
