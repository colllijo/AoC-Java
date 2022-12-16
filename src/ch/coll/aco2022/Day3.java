package ch.coll.aco2022;

import ch.coll.aoc.Day;
import ch.coll.aoc.Runner;

import java.lang.reflect.InvocationTargetException;

public class Day3 implements Day {
    @Override
    public String part1(String input) {
        int priority = 0;

        for (String line : input.split("\n")) {
            for (int i = 0; i < line.length() / 2; i++) {
                if (line.substring(line.length() / 2).contains(line.charAt(i) + "")) {
                    priority += line.charAt(i) - (line.charAt(i) > 90 ? 96 : 38);
                    break;
                }
            }
        }

        return priority + "";
    }

    @Override
    public String part2(String input) {
        int priority = 0;

        for (int i = 0; i < input.split("\n").length; i += 3) {
            for (int j = 0; j < input.split("\n")[i].length(); j++) {
                if (input.split("\n")[i + 1].contains(input.split("\n")[i].charAt(j) + "") && input.split("\n")[i + 2].contains(input.split("\n")[i].charAt(j) + "")) {
                    priority += input.split("\n")[i].charAt(j) - (input.split("\n")[i].charAt(j) > 90 ? 96 : 38);
                    break;
                }
            }
        }

        return priority + "";
    }

    public static void main(String[] args) {
        try {
            Runner.input = "vJrwpWtwJgWrhcsFMMfFFhFp\n" +
                    "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\n" +
                    "PmmdzqPrVvPwwTWBwg\n" +
                    "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\n" +
                    "ttgJtRGJQctTZtZT\n" +
                    "CrZsJsPPZsGzwwsLwLmpwMDw";
            Runner.run((Day) Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getDeclaredConstructor().newInstance(), "2022-12-3");
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException exception) {
            System.out.println("Class couldn't be found please enter it manually");
        }
    }
}
