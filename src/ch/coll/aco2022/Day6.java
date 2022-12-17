package ch.coll.aco2022;

import ch.coll.aoc.Day;
import ch.coll.aoc.Runner;

import java.lang.reflect.InvocationTargetException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Day6 implements Day {
    @Override
    public String part1(String input) {
        int marker = -1;

        for (int i = 0; i < input.length() - 4; i++) {
            if (distinctCharacters(input.substring(i, i + 4))) {
                marker = i + 4;
                break;
            }
        }

        return marker + "";
    }

    @Override
    public String part2(String input) {
        int marker = -1;

        for (int i = 0; i < input.length() - 14; i++) {
            if (distinctCharacters(input.substring(i, i + 14))) {
                marker = i + 14;
                break;
            }
        }

        return marker + "";
    }

    public boolean distinctCharacters(String segment) {
        boolean repetition = true;

        for(int i = 0; i < segment.length(); i++) {
            for(int j = 1 + i; j < segment.length(); j++) {
                if(segment.charAt(i) == segment.charAt(j)) {
                    repetition = false;
                }
            }
        }

        return repetition;
    }

    public static void main(String[] args) {
        try {
            Runner.input = "nppdvjthqldpwncqszvftbrmjlhg";
            Runner.run((Day) Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getDeclaredConstructor().newInstance(), "2022-12-06");
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException exception) {
            System.out.println("Class couldn't be found please enter it manually");
        }
    }
}
