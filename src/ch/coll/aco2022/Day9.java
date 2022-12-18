package ch.coll.aco2022;

import ch.coll.aoc.Day;
import ch.coll.aoc.Runner;

import javax.management.MBeanRegistration;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Day9 implements Day {
    @Override
    public String part1(String input) {
        int headX = 0;
        int headY = 0;

        int tailX = 0;
        int tailY = 0;

        ArrayList<Point> uniqueSpots = new ArrayList<>();

        for (String line : input.split("\n")) {
            for (int i = 0; i < Integer.parseInt(line.split(" ")[1]); i++) {
                //Move the head
                switch (line.split(" ")[0]) {
                    case "U":
                        headY--;
                        break;
                    case "D":
                        headY++;
                        break;
                    case "L":
                        headX--;
                        break;
                    case "R":
                        headX++;
                        break;
                }

                if (tailX - headX > 1) {
                    tailX--;
                    if (tailY > headY) {
                        tailY--;
                    } else if (tailY < headY) {
                        tailY++;
                    }
                } else if (headX - tailX > 1) {
                    tailX++;
                    if (tailY > headY) {
                        tailY--;
                    } else if (tailY < headY) {
                        tailY++;
                    }
                } else if (tailY - headY > 1) {
                    tailY--;
                    if (tailX > headX) {
                        tailX--;
                    } else if (tailX < headX) {
                        tailX++;
                    }
                } else if (headY - tailY > 1) {
                    tailY++;
                    if (tailX > headX) {
                        tailX--;
                    } else if (tailX < headX) {
                        tailX++;
                    }
                }

                if (!uniqueSpots.contains(new Point(tailX, tailY))) {
                    uniqueSpots.add(new Point(tailX, tailY));
                }
            }
        }

        return uniqueSpots.size() + "";
    }

    @Override
    public String part2(String input) {
        int headX = 0;
        int headY = 0;

        int[] tailX = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] tailY = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};

        ArrayList<Point> uniqueSpots = new ArrayList<>();

        for (String line : input.split("\n")) {
            for (int i = 0; i < Integer.parseInt(line.split(" ")[1]); i++) {
                //Move the head
                switch (line.split(" ")[0]) {
                    case "U":
                        headY--;
                        break;
                    case "D":
                        headY++;
                        break;
                    case "L":
                        headX--;
                        break;
                    case "R":
                        headX++;
                        break;
                }

                for (int j = 0; j < tailX.length; j++) {

                    if (tailX[j] - (j == 0 ? headX : tailX[j - 1]) > 1) {
                        tailX[j]--;
                        if (tailY[j] > (j == 0 ? headY : tailY[j - 1])) {
                            tailY[j]--;
                        } else if (tailY[j] < (j == 0 ? headY : tailY[j - 1])) {
                            tailY[j]++;
                        }
                    } else if ((j == 0 ? headX : tailX[j - 1]) - tailX[j] > 1) {
                        tailX[j]++;
                        if (tailY[j] > (j == 0 ? headY : tailY[j - 1])) {
                            tailY[j]--;
                        } else if (tailY[j] < (j == 0 ? headY : tailY[j - 1])) {
                            tailY[j]++;
                        }
                    } else if (tailY[j] - (j == 0 ? headY : tailY[j - 1]) > 1) {
                        tailY[j]--;
                        if (tailX[j] > (j == 0 ? headX : tailX[j - 1])) {
                            tailX[j]--;
                        } else if (tailX[j] < (j == 0 ? headX : tailX[j - 1])) {
                            tailX[j]++;
                        }
                    } else if ((j == 0 ? headY : tailY[j - 1]) - tailY[j] > 1) {
                        tailY[j]++;
                        if (tailX[j] > (j == 0 ? headX : tailX[j - 1])) {
                            tailX[j]--;
                        } else if (tailX[j] < (j == 0 ? headX : tailX[j - 1])) {
                            tailX[j]++;
                        }
                    }
                }

                if (!uniqueSpots.contains(new Point(tailX[8], tailY[8]))) {
                    uniqueSpots.add(new Point(tailX[8], tailY[8]));
                }
            }
        }

        return uniqueSpots.size() + "";
    }

    public static void main(String[] args) {
        try {
            Runner.input =
                    "R 5\n" +
                            "U 8\n" +
                            "L 8\n" +
                            "D 3\n" +
                            "R 17\n" +
                            "D 10\n" +
                            "L 25\n" +
                            "U 20";
            Runner.run((Day) Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getDeclaredConstructor().newInstance(), "2022-12-09");
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException exception) {
            System.out.println("Class couldn't be found please enter it manually");
        }
    }
}
