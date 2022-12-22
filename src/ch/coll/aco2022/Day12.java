package ch.coll.aco2022;

import ch.coll.aoc.Day;
import ch.coll.aoc.Runner;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Day12 implements Day {
    @Override
    public String part1(String input) {
        int[][] hill = new int[input.split("\n").length][input.split("\n")[0].length()];
        Point startCoordinate = new Point();
        Point goalCoordinate = new Point();

        for (int y = 0; y < input.split("\n").length; y++) {
            for (int x = 0; x < input.split("\n")[y].length(); x++) {
                if (input.split("\n")[y].charAt(x) == 'S') {
                    startCoordinate = new Point(x, y);
                    hill[y][x] = 1;
                } else if (input.split("\n")[y].charAt(x) == 'E') {
                    goalCoordinate = new Point(x, y);
                    hill[y][x] = 26;
                } else {
                    hill[y][x] = (int) input.split("\n")[y].charAt(x) - 96;
                }
            }
        }


        int[][] distances = new int[hill.length][hill[0].length];
        for (int i = 0; i < distances.length; i++) {
            for (int j = 0; j < distances[i].length; j++) {
                distances[i][j] = -1;
            }
        }

        distances[startCoordinate.y][startCoordinate.x] = 0;
        int curDistance = 0;

        while (distances[goalCoordinate.y][goalCoordinate.x] == -1) {

            for (int y = 0; y < distances.length; y++) {
                for (int x = 0; x < distances[y].length; x++) {
                    if (distances[y][x] == curDistance) {
                        //Check top
                        if (y - 1 >= 0 && distances[y - 1][x] == -1) {
                            if (hill[y][x] >= hill[y - 1][x] - 1) {
                                distances[y - 1][x] = curDistance + 1;
                            }
                        }
                        //Check bottom
                        if (y + 1 < distances.length && distances[y + 1][x] == -1) {
                            if (hill[y][x] >= hill[y + 1][x] - 1) {
                                distances[y + 1][x] = curDistance + 1;
                            }
                        }
                        //Check left
                        if (x - 1 >= 0 && distances[y][x - 1] == -1) {
                            if (hill[y][x] >= hill[y][x - 1] - 1) {
                                distances[y][x - 1] = curDistance + 1;
                            }
                        }
                        //Check right
                        if (x + 1 < distances[y].length && distances[y][x + 1] == -1) {
                            if (hill[y][x] >= hill[y][x + 1] - 1) {
                                distances[y][x + 1] = curDistance + 1;
                            }
                        }
                    }
                }
            }
            curDistance++;
        }

        return distances[goalCoordinate.y][goalCoordinate.x] + "";
    }

    @Override
    public String part2(String input) {int[][] hill = new int[input.split("\n").length][input.split("\n")[0].length()];
        Point goalCoordinate = new Point();

        for (int y = 0; y < input.split("\n").length; y++) {
            for (int x = 0; x < input.split("\n")[y].length(); x++) {
                if (input.split("\n")[y].charAt(x) == 'S') {
                    hill[y][x] = 1;
                } else if (input.split("\n")[y].charAt(x) == 'E') {
                    goalCoordinate = new Point(x, y);
                    hill[y][x] = 26;
                } else {
                    hill[y][x] = (int) input.split("\n")[y].charAt(x) - 96;
                }
            }
        }

        ArrayList<Point> possibleStartingPositions = new ArrayList<>();
        for(int y = 0; y < hill.length; y++) {
            for(int x = 0; x < hill[y].length; x++) {
                if(hill[y][x] == 1) {
                    possibleStartingPositions.add(new Point(x, y));
                }
            }
        }

        ArrayList<Integer> allPossibleDistances = new ArrayList<>();

        int curMinDistance = Integer.MAX_VALUE;

        for(Point startCoordinate : possibleStartingPositions) {
            int[][] distances = new int[hill.length][hill[0].length];
            for (int i = 0; i < distances.length; i++) {
                for (int j = 0; j < distances[i].length; j++) {
                    distances[i][j] = -1;
                }
            }

            distances[startCoordinate.y][startCoordinate.x] = 0;
            int curDistance = 0;

            while (distances[goalCoordinate.y][goalCoordinate.x] == -1 && curDistance <= curMinDistance) {

                for (int y = 0; y < distances.length; y++) {
                    for (int x = 0; x < distances[y].length; x++) {
                        if (distances[y][x] == curDistance) {
                            //Check top
                            if (y - 1 >= 0 && distances[y - 1][x] == -1) {
                                if (hill[y][x] >= hill[y - 1][x] - 1) {
                                    distances[y - 1][x] = curDistance + 1;
                                }
                            }
                            //Check bottom
                            if (y + 1 < distances.length && distances[y + 1][x] == -1) {
                                if (hill[y][x] >= hill[y + 1][x] - 1) {
                                    distances[y + 1][x] = curDistance + 1;
                                }
                            }
                            //Check left
                            if (x - 1 >= 0 && distances[y][x - 1] == -1) {
                                if (hill[y][x] >= hill[y][x - 1] - 1) {
                                    distances[y][x - 1] = curDistance + 1;
                                }
                            }
                            //Check right
                            if (x + 1 < distances[y].length && distances[y][x + 1] == -1) {
                                if (hill[y][x] >= hill[y][x + 1] - 1) {
                                    distances[y][x + 1] = curDistance + 1;
                                }
                            }
                        }
                    }
                }
                curDistance++;
            }
            if(distances[goalCoordinate.y][goalCoordinate.x] != -1) {
                allPossibleDistances.add(distances[goalCoordinate.y][goalCoordinate.x]);
            }
            Collections.sort(allPossibleDistances);
            curMinDistance = allPossibleDistances.get(0);
        }

        Collections.sort(allPossibleDistances);

        return allPossibleDistances.get(0) + "";
    }

    public static void main(String[] args) {
        try {
            Runner.input =
                    "Sabqponm\n" +
                            "abcryxxl\n" +
                            "accszExk\n" +
                            "acctuvwj\n" +
                            "abdefghi";
            Runner.run((Day) Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getDeclaredConstructor().newInstance(), "2022-12-12");
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException exception) {
            System.out.println("Class couldn't be found please enter it manually");
        }
    }
}
