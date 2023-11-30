package ch.coll.aoc;

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
            Runner.run((Day) Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getDeclaredConstructor().newInstance(), "yyyy-mm-dd");
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException exception) {
            System.out.println("Class couldn't be found please enter it manually");
        }
    }
}
