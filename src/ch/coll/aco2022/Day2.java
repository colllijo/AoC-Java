package ch.coll.aco2022;

import ch.coll.aoc.Day;
import ch.coll.aoc.Runner;

import java.lang.reflect.InvocationTargetException;

public class Day2 implements Day {
    @Override
    public String part1(String input) {
        int score = 0;

        for (String line : input.split("\n")) {
            score += (int) (line.split(" ")[1].charAt(0)) - 87;

            switch (line.split(" ")[0]) {
                case "A":
                    switch (line.split(" ")[1]) {
                        case "X":
                            score += 3;
                            break;
                        case "Y":
                            score += 6;
                    }
                    break;
                case "B":
                    switch (line.split(" ")[1]) {
                        case "Y":
                            score += 3;
                            break;
                        case "Z":
                            score += 6;
                    }
                    break;
                case "C":
                    switch (line.split(" ")[1]) {
                        case "Z":
                            score += 3;
                            break;
                        case "X":
                            score += 6;
                    }
                    break;
            }
        }

        return score + "";
    }

    @Override
    public String part2(String input) {
        int score = 0;

        for (String line : input.split("\n")) {
            score += ((int) (line.split(" ")[1].charAt(0)) - 88) * 3;

            switch (line.split(" ")[0]) {
                case "A":
                    switch (line.split(" ")[1]) {
                        case "X":
                            score += 3;
                            break;
                        case "Y":
                            score += 1;
                            break;
                        case "Z":
                            score += 2;
                            break;
                    }
                    break;
                case "B":
                    switch (line.split(" ")[1]) {
                        case "X":
                            score += 1;
                            break;
                        case "Y":
                            score += 2;
                            break;
                        case "Z":
                            score += 3;
                            break;
                    }
                    break;
                case "C":
                    switch (line.split(" ")[1]) {
                        case "X":
                            score += 2;
                            break;
                        case "Y":
                            score += 3;
                            break;
                        case "Z":
                            score += 1;
                            break;
                    }
                    break;
            }
        }

        return score + "";
    }

    public static void main(String[] args) {
        try {
            Runner.input = "A Y\n" +
                    "B X\n" +
                    "C Z";
            Runner.run((Day) Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getDeclaredConstructor().newInstance(), "2022-12-2");
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException exception) {
            System.out.println("Class couldn't be found please enter it manually");
        }
    }
}
