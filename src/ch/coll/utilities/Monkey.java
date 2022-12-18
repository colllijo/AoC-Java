package ch.coll.utilities;

import java.util.ArrayList;

public class Monkey {
    public ArrayList<Long> items = new ArrayList<>();
    public String operation;
    public long test;
    public int trueMonkey;
    public int falseMoney;

    public long operation(long number) {
        long num2;
        if(operation.split(" ")[2].equals("old")) {
            num2 = number;
        } else {
            num2 = Integer.parseInt(operation.split(" ")[2]);
        }

        switch (operation.split(" ")[1]) {
            case "+":
                return number + num2;
            case "-":
                return number - num2;
            case "*":
                return number * num2;
            case "/":
                return number / num2;
        }

        return -1;
    }
}
