package ch.coll.aco2022;

import ch.coll.aoc.Day;
import ch.coll.aoc.Runner;

import java.lang.reflect.InvocationTargetException;

public class Day8 implements Day {
    @Override
    public String part1(String input) {
        int[][] treeGrid = new int[input.split("\n").length][input.split("\n")[0].length()];
        int visibleTrees = 0;

        for (int i = 0; i < treeGrid.length; i++) {
            for (int j = 0; j < treeGrid[i].length; j++) {
                treeGrid[i][j] = Integer.parseInt(input.split("\n")[i].charAt(j) + "");
            }
        }

        visibleTrees += treeGrid.length * 2 + (treeGrid[0].length - 2) * 2;

        for (int i = 1; i < treeGrid.length - 1; i++) {
            for (int j = 1; j < treeGrid[i].length - 1; j++) {
                boolean visible = false;

                //to top
                for (int y = i; y >= 0; y--) {
                    if (y == 0) {
                        visible = true;
                        break;
                    }
                    if (treeGrid[y - 1][j] >= treeGrid[i][j]) {
                        break;
                    }
                }

                //to bottom
                for (int y = i; y < treeGrid.length; y++) {
                    if (y == treeGrid.length - 1) {
                        visible = true;
                        break;
                    }
                    if (treeGrid[y + 1][j] >= treeGrid[i][j]) {
                        break;
                    }
                }

                //to left
                for (int x = j; x >= 0; x--) {
                    if (x == 0) {
                        visible = true;
                        break;
                    }
                    if (treeGrid[i][x - 1] >= treeGrid[i][j]) {
                        break;
                    }
                }

                //to right
                for (int x = j; x < treeGrid.length; x++) {
                    if (x == treeGrid.length - 1) {
                        visible = true;
                        break;
                    }
                    if (treeGrid[i][x + 1] >= treeGrid[i][j]) {
                        break;
                    }
                }

                if (visible) {
                    visibleTrees++;
                }
            }
        }

        return visibleTrees + "";
    }

    @Override
    public String part2(String input) {
        int[][] treeGrid = new int[input.split("\n").length][input.split("\n")[0].length()];
        int[][] treeScore = new int[input.split("\n").length][input.split("\n")[0].length()];

        for (int i = 0; i < treeGrid.length; i++) {
            for (int j = 0; j < treeGrid[i].length; j++) {
                treeGrid[i][j] = Integer.parseInt(input.split("\n")[i].charAt(j) + "");
            }
        }

        for (int i = 1; i < treeGrid.length - 1; i++) {
            for (int j = 1; j < treeGrid[i].length - 1; j++) {
                int visibleTrees = 0;

                //to top
                for (int y = i; y > 0; y--) {
                    if (treeGrid[y - 1][j] >= treeGrid[i][j]) {
                        visibleTrees++;
                        break;
                    } else {
                        visibleTrees++;
                    }
                }
                treeScore[i][j] = visibleTrees;
                visibleTrees = 0;

                //to bottom
                for (int y = i; y < treeGrid.length - 1; y++) {
                    if (treeGrid[y + 1][j] >= treeGrid[i][j]) {
                        visibleTrees++;
                        break;
                    } else {
                        visibleTrees++;
                    }
                }
                treeScore[i][j] *= visibleTrees;
                visibleTrees = 0;

                //to left
                for (int x = j; x > 0; x--) {
                    if (treeGrid[i][x - 1] >= treeGrid[i][j]) {
                        visibleTrees++;
                        break;
                    } else {
                        visibleTrees++;
                    }
                }
                treeScore[i][j] *= visibleTrees;
                visibleTrees = 0;

                //to right
                for (int x = j; x < treeGrid.length - 1; x++) {
                    if (treeGrid[i][x + 1] >= treeGrid[i][j]) {
                        visibleTrees++;
                        break;
                    } else {
                        visibleTrees++;
                    }
                }
                treeScore[i][j] *= visibleTrees;
                visibleTrees = 0;
            }
        }

        int highestScore = 0;

        for(int i = 1; i < treeGrid.length - 1; i++) {
            for (int j = 1; j < treeGrid[i].length - 1; j++) {
                highestScore = Math.max(highestScore, treeScore[i][j]);
            }
        }

        return highestScore + "";
    }

    public static void main(String[] args) {
        try {
            Runner.input =
                    "30373\n" +
                            "25512\n" +
                            "65332\n" +
                            "33549\n" +
                            "35390";
            Runner.run((Day) Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getDeclaredConstructor().newInstance(), "2022-12-08");
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException exception) {
            System.out.println("Class couldn't be found please enter it manually");
        }
    }
}
