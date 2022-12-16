package ch.coll.aco2022;

import ch.coll.aoc.Day;
import ch.coll.aoc.Runner;

import java.lang.reflect.InvocationTargetException;

public class Day4 implements Day {
    @Override
    public String part1(String input) {
        int count = 0;

        for(String line : input.split("\n")) {
            if (Integer.parseInt(line.split(",")[0].split("-")[0]) >= Integer.parseInt(line.split(",")[1].split("-")[0]) && Integer.parseInt(line.split(",")[0].split("-")[1]) <= Integer.parseInt(line.split(",")[1].split("-")[1])) {
                count++;
            } else if (Integer.parseInt(line.split(",")[0].split("-")[0]) <= Integer.parseInt(line.split(",")[1].split("-")[0]) && Integer.parseInt(line.split(",")[0].split("-")[1]) >= Integer.parseInt(line.split(",")[1].split("-")[1])) {
                count++;
            }
        }

        return count + "";
    }

    @Override
    public String part2(String input) {
        int count = 0;

        for(String line : input.split("\n")) {
            if(Integer.parseInt(line.split(",")[0].split("-")[0]) >= Integer.parseInt(line.split(",")[1].split("-")[0]) && Integer.parseInt(line.split(",")[0].split("-")[0]) <= Integer.parseInt(line.split(",")[1].split("-")[1])) {
                count++;
            } else if(Integer.parseInt(line.split(",")[0].split("-")[1]) >= Integer.parseInt(line.split(",")[1].split("-")[0]) && Integer.parseInt(line.split(",")[0].split("-")[1]) <= Integer.parseInt(line.split(",")[1].split("-")[1])) {
                count++;
            } else if(Integer.parseInt(line.split(",")[1].split("-")[0]) >= Integer.parseInt(line.split(",")[0].split("-")[0]) && Integer.parseInt(line.split(",")[1].split("-")[0]) <= Integer.parseInt(line.split(",")[0].split("-")[1])) {
                count++;
            } else if(Integer.parseInt(line.split(",")[1].split("-")[1]) >= Integer.parseInt(line.split(",")[0].split("-")[0]) && Integer.parseInt(line.split(",")[1].split("-")[1]) <= Integer.parseInt(line.split(",")[0].split("-")[1])) {
                count++;
            }
        }

        return count + "";
    }

    public static void main(String[] args) {
        try {
            Runner.input = "2-4,6-8\n" +
                    "2-3,4-5\n" +
                    "5-7,7-9\n" +
                    "2-8,3-7\n" +
                    "6-6,4-6\n" +
                    "2-6,4-8";
            Runner.run((Day) Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getDeclaredConstructor().newInstance(), "2022 12 4");
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException exception) {
            System.out.println("Class couldn't be found please enter it manually");
        }
    }
}
