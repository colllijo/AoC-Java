package ch.coll.aco2022;

import ch.coll.aoc.Day;
import ch.coll.aoc.Runner;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

public class Day5 implements Day {
    @Override
    public String part1(String input) {
        //Creating the towers
        int towerCount = (input.split("\n")[0].length() + 1) / 4;
        ArrayList<String>[] towers = new ArrayList[towerCount];

        for(int i = 0; i < towerCount; i++) {
            towers[i] = new ArrayList<>();
        }

        String towerBlocks = input.split("\n\n")[0];

        for(int i = towerBlocks.split("\n").length - 2; i >= 0; i--) {
            for(int j = 0; j < towerCount; j++) {
                if(towerBlocks.split("\n")[i].charAt(j * 4 + 1) != ' ') {
                    towers[j].add(towerBlocks.split("\n")[i].charAt(j * 4 + 1) + "");
                }

            }
        }

        //Moving the blocks
        String instructions = input.split("\n\n")[1];

        for(String line : instructions.split("\n")) {
            for(int i = 0; i < Integer.parseInt(line.split(" ")[1]); i++) {
                towers[Integer.parseInt(line.split(" ")[5]) - 1].add(towers[Integer.parseInt(line.split(" ")[3]) - 1].get(towers[Integer.parseInt(line.split(" ")[3]) - 1].size() - 1));
                towers[Integer.parseInt(line.split(" ")[3]) - 1].remove(towers[Integer.parseInt(line.split(" ")[3]) - 1].size() - 1);
            }
        }

        String out = "";

        for(int i = 0; i < towerCount; i++) {
            out += towers[i].get(towers[i].size() - 1);
        }

        return out;
    }

    @Override
    public String part2(String input) {
        //Creating the towers
        int towerCount = (input.split("\n")[0].length() + 1) / 4;
        ArrayList<String>[] towers = new ArrayList[towerCount];

        for(int i = 0; i < towerCount; i++) {
            towers[i] = new ArrayList<>();
        }

        String towerBlocks = input.split("\n\n")[0];

        for(int i = towerBlocks.split("\n").length - 2; i >= 0; i--) {
            for(int j = 0; j < towerCount; j++) {
                if(towerBlocks.split("\n")[i].charAt(j * 4 + 1) != ' ') {
                    towers[j].add(towerBlocks.split("\n")[i].charAt(j * 4 + 1) + "");
                }

            }
        }

        //Moving the blocks
        String instructions = input.split("\n\n")[1];

        for(String line : instructions.split("\n")) {
            for(int i = Integer.parseInt(line.split(" ")[1]); i > 0; i--) {
                towers[Integer.parseInt(line.split(" ")[5]) - 1].add(towers[Integer.parseInt(line.split(" ")[3]) - 1].get(towers[Integer.parseInt(line.split(" ")[3]) - 1].size() - i));
                towers[Integer.parseInt(line.split(" ")[3]) - 1].remove(towers[Integer.parseInt(line.split(" ")[3]) - 1].size() - i);
            }
        }

        String out = "";

        for(int i = 0; i < towerCount; i++) {
            out += towers[i].get(towers[i].size() - 1);
        }

        return out;
    }

    public static void main(String[] args) {
        try {
            Runner.input = "    [D]    \n" +
                    "[N] [C]    \n" +
                    "[Z] [M] [P]\n" +
                    " 1   2   3 \n" +
                    "\n" +
                    "move 1 from 2 to 1\n" +
                    "move 3 from 1 to 3\n" +
                    "move 2 from 2 to 1\n" +
                    "move 1 from 1 to 2";
            Runner.run((Day) Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getDeclaredConstructor().newInstance(), "2022-12-5");
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException exception) {
            System.out.println("Class couldn't be found please enter it manually");
        }
    }
}
