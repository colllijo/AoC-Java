package ch.coll.aco2022;

import ch.coll.aoc.Day;
import ch.coll.aoc.Runner;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Day1 implements Day {
    @Override
    public String part1(String input) {
        ArrayList<Integer> elvCals = new ArrayList<>();

        int currentCals = 0;
        for(String line: input.split("\n")) {
            if(!line.equals("")) {
                currentCals += Integer.parseInt(line);
            } else {
                elvCals.add(currentCals);
                currentCals = 0;
            }
        }

        Collections.sort(elvCals);

        return elvCals.get(elvCals.size() - 1) + "";
    }

    @Override
    public String part2(String input) {
        ArrayList<Integer> elvCals = new ArrayList<>();

        int currentCals = 0;
        for(String line: input.split("\n")) {
            if(!line.equals("")) {
                currentCals += Integer.parseInt(line);
            } else {
                elvCals.add(currentCals);
                currentCals = 0;
            }
        }

        Collections.sort(elvCals);

        return (elvCals.get(elvCals.size() - 1) + elvCals.get(elvCals.size() - 2) + elvCals.get(elvCals.size() - 3)) + "";
    }

    public static void main(String[] args) {
        try {
            Runner.input = "1000\n" +
                    "2000\n" +
                    "3000\n" +
                    "\n" +
                    "4000\n" +
                    "\n" +
                    "5000\n" +
                    "6000\n" +
                    "\n" +
                    "7000\n" +
                    "8000\n" +
                    "9000\n" +
                    "\n" +
                    "10000";
            Runner.run((Day) Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getDeclaredConstructor().newInstance(), "2022-12-1");
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException exception) {
            System.out.println("Class couldn't be found please enter it manually");
        }
    }
}
