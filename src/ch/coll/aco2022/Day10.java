package ch.coll.aco2022;

import ch.coll.aoc.Day;
import ch.coll.aoc.Runner;

import java.lang.reflect.InvocationTargetException;

public class Day10 implements Day {
    @Override
    public String part1(String input) {
        int cycle = 0;
        int regX = 1;

        int signal = 0;

        for (String line : input.split("\n")) {
            cycle++;

            if ((cycle - 20) % 40 == 0) {
                signal += regX * cycle;
            }

            if (line.split(" ")[0].equals("addx")) {
                cycle++;

                if ((cycle - 20) % 40 == 0) {
                    signal += regX * cycle;
                }

                regX += Integer.parseInt(line.split(" ")[1]);
            }
        }

        return signal + "";
    }

    @Override
    public String part2(String input) {
        char[][] CRT = new char[6][40];

        int cycle = 0;
        int regX = 1;


        for (String line : input.split("\n")) {
            if (regX - 1 == cycle % 40 || regX == cycle % 40 || regX + 1 == cycle % 40) {
                CRT[cycle / 40][cycle % 40] = '⬜';
            } else {
                CRT[cycle / 40][cycle % 40] = '⬛';
            }

            cycle++;

            if (line.split(" ")[0].equals("addx")) {
                if (regX - 1 == cycle % 40 || regX == cycle % 40 || regX + 1 == cycle % 40) {
                    CRT[cycle / 40][cycle % 40] = '⬜';
                } else {
                    CRT[cycle / 40][cycle % 40] = '⬛';
                }

                cycle++;

                regX += Integer.parseInt(line.split(" ")[1]);
            }
        }

        String CRTScreen = "\n";

        for(int y = 0; y < CRT.length; y++) {
            for(int x = 0; x < CRT[y].length; x++) {
                CRTScreen += CRT[y][x];
            }
            CRTScreen += "\n";
        }

        return CRTScreen;
    }

    public static void main(String[] args) {
        try {
            Runner.input =
                    "addx 15\n" +
                            "addx -11\n" +
                            "addx 6\n" +
                            "addx -3\n" +
                            "addx 5\n" +
                            "addx -1\n" +
                            "addx -8\n" +
                            "addx 13\n" +
                            "addx 4\n" +
                            "noop\n" +
                            "addx -1\n" +
                            "addx 5\n" +
                            "addx -1\n" +
                            "addx 5\n" +
                            "addx -1\n" +
                            "addx 5\n" +
                            "addx -1\n" +
                            "addx 5\n" +
                            "addx -1\n" +
                            "addx -35\n" +
                            "addx 1\n" +
                            "addx 24\n" +
                            "addx -19\n" +
                            "addx 1\n" +
                            "addx 16\n" +
                            "addx -11\n" +
                            "noop\n" +
                            "noop\n" +
                            "addx 21\n" +
                            "addx -15\n" +
                            "noop\n" +
                            "noop\n" +
                            "addx -3\n" +
                            "addx 9\n" +
                            "addx 1\n" +
                            "addx -3\n" +
                            "addx 8\n" +
                            "addx 1\n" +
                            "addx 5\n" +
                            "noop\n" +
                            "noop\n" +
                            "noop\n" +
                            "noop\n" +
                            "noop\n" +
                            "addx -36\n" +
                            "noop\n" +
                            "addx 1\n" +
                            "addx 7\n" +
                            "noop\n" +
                            "noop\n" +
                            "noop\n" +
                            "addx 2\n" +
                            "addx 6\n" +
                            "noop\n" +
                            "noop\n" +
                            "noop\n" +
                            "noop\n" +
                            "noop\n" +
                            "addx 1\n" +
                            "noop\n" +
                            "noop\n" +
                            "addx 7\n" +
                            "addx 1\n" +
                            "noop\n" +
                            "addx -13\n" +
                            "addx 13\n" +
                            "addx 7\n" +
                            "noop\n" +
                            "addx 1\n" +
                            "addx -33\n" +
                            "noop\n" +
                            "noop\n" +
                            "noop\n" +
                            "addx 2\n" +
                            "noop\n" +
                            "noop\n" +
                            "noop\n" +
                            "addx 8\n" +
                            "noop\n" +
                            "addx -1\n" +
                            "addx 2\n" +
                            "addx 1\n" +
                            "noop\n" +
                            "addx 17\n" +
                            "addx -9\n" +
                            "addx 1\n" +
                            "addx 1\n" +
                            "addx -3\n" +
                            "addx 11\n" +
                            "noop\n" +
                            "noop\n" +
                            "addx 1\n" +
                            "noop\n" +
                            "addx 1\n" +
                            "noop\n" +
                            "noop\n" +
                            "addx -13\n" +
                            "addx -19\n" +
                            "addx 1\n" +
                            "addx 3\n" +
                            "addx 26\n" +
                            "addx -30\n" +
                            "addx 12\n" +
                            "addx -1\n" +
                            "addx 3\n" +
                            "addx 1\n" +
                            "noop\n" +
                            "noop\n" +
                            "noop\n" +
                            "addx -9\n" +
                            "addx 18\n" +
                            "addx 1\n" +
                            "addx 2\n" +
                            "noop\n" +
                            "noop\n" +
                            "addx 9\n" +
                            "noop\n" +
                            "noop\n" +
                            "noop\n" +
                            "addx -1\n" +
                            "addx 2\n" +
                            "addx -37\n" +
                            "addx 1\n" +
                            "addx 3\n" +
                            "noop\n" +
                            "addx 15\n" +
                            "addx -21\n" +
                            "addx 22\n" +
                            "addx -6\n" +
                            "addx 1\n" +
                            "noop\n" +
                            "addx 2\n" +
                            "addx 1\n" +
                            "noop\n" +
                            "addx -10\n" +
                            "noop\n" +
                            "noop\n" +
                            "addx 20\n" +
                            "addx 1\n" +
                            "addx 2\n" +
                            "addx 2\n" +
                            "addx -6\n" +
                            "addx -11\n" +
                            "noop\n" +
                            "noop\n" +
                            "noop";
            Runner.run((Day) Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getDeclaredConstructor().newInstance(), "2022-12-10");
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException exception) {
            System.out.println("Class couldn't be found please enter it manually");
        }
    }
}
