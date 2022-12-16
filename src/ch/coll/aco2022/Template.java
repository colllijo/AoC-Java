package ch.coll.aco2022;

import ch.coll.aoc.Day;
import ch.coll.aoc.Input;
import ch.coll.aoc.Runner;

import java.lang.reflect.InvocationTargetException;

public class Template implements Day {
    @Override
    public String part1(String input) {
        return null;
    }

    @Override
    public String part2(String input) {
        return null;
    }

    public static void main(String[] args) {
        try {
            Runner.input = null;
            Runner.run((Day) Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getDeclaredConstructor().newInstance());
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException exception) {
            System.out.println("Class couldn't be found please enter it manually");
        }
    }
}
