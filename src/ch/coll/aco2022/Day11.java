package ch.coll.aco2022;

import ch.coll.aoc.Day;
import ch.coll.aoc.Runner;
import ch.coll.utilities.COLLMath;
import ch.coll.utilities.Monkey;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Day11 implements Day {
    @Override
    public String part1(String input) {
        ArrayList<Monkey> monkeys = new ArrayList<>();
        long worryLevel = 0;

        for (String monkey : input.split("\n\n")) {
            String[] lines = monkey.split("\n");
            Monkey curMonkey = new Monkey();

            for (String item : lines[1].split(": ")[1].split(", ")) {
                curMonkey.items.add(Long.parseLong(item));
            }

            curMonkey.operation = lines[2].split("= ")[1];
            curMonkey.test = Long.parseLong(lines[3].split("by ")[1]);
            curMonkey.trueMonkey = Integer.parseInt(lines[4].split("monkey ")[1]);
            curMonkey.falseMoney = Integer.parseInt(lines[5].split("monkey ")[1]);

            monkeys.add(curMonkey);
        }

        long[] monkeyActions = new long[monkeys.size()];

        for (int round = 0; round < 20; round++) {
            for (Monkey monkey : monkeys) {
                for (int i = 0; i < monkey.items.size(); i++) {
                    monkeyActions[monkeys.indexOf(monkey)]++;

                    worryLevel = monkey.operation(monkey.items.get(i));
                    worryLevel /= 3;

                    if (worryLevel % monkey.test == 0) {
                        monkeys.get(monkey.trueMonkey).items.add(worryLevel);
                    } else {
                        monkeys.get(monkey.falseMoney).items.add(worryLevel);
                    }
                }
                monkey.items.clear();
            }
        }

        Arrays.sort(monkeyActions);
        return (monkeyActions[monkeyActions.length - 1] * monkeyActions[monkeyActions.length - 2]) + "";
    }

    @Override
    public String part2(String input) {
        ArrayList<Monkey> monkeys = new ArrayList<>();
        long worryLevel = 0;

        for (String monkey : input.split("\n\n")) {
            String[] lines = monkey.split("\n");
            Monkey curMonkey = new Monkey();

            for (String item : lines[1].split(": ")[1].split(", ")) {
                curMonkey.items.add(Long.parseLong(item));
            }

            curMonkey.operation = lines[2].split("= ")[1];
            curMonkey.test = Integer.parseInt(lines[3].split("by ")[1]);
            curMonkey.trueMonkey = Integer.parseInt(lines[4].split("monkey ")[1]);
            curMonkey.falseMoney = Integer.parseInt(lines[5].split("monkey ")[1]);

            monkeys.add(curMonkey);
        }

        long[] monkeyActions = new long[monkeys.size()];

        long[] tests = new long[monkeys.size()];
        for (int i = 0; i < monkeys.size(); i++) {
            tests[i] = monkeys.get(i).test;
        }

        long leastCommonMultiple = COLLMath.leastCommonMultiple(tests);

        for (int round = 0; round < 10000; round++) {
            for (Monkey monkey : monkeys) {
                for (int i = 0; i < monkey.items.size(); i++) {
                    monkeyActions[monkeys.indexOf(monkey)]++;

                    worryLevel = monkey.operation(monkey.items.get(i));
                    worryLevel %= leastCommonMultiple;

                    if (worryLevel % monkey.test == 0) {
                        monkeys.get(monkey.trueMonkey).items.add(worryLevel);
                    } else {
                        monkeys.get(monkey.falseMoney).items.add(worryLevel);
                    }
                }
                monkey.items.clear();
            }
        }


        Arrays.sort(monkeyActions);
        return (monkeyActions[monkeyActions.length - 1] * monkeyActions[monkeyActions.length - 2]) + "";
    }

    public static void main(String[] args) {
        try {
            Runner.input =
                    "Monkey 0:\n" +
                            "  Starting items: 79, 98\n" +
                            "  Operation: new = old * 19\n" +
                            "  Test: divisible by 23\n" +
                            "    If true: throw to monkey 2\n" +
                            "    If false: throw to monkey 3\n" +
                            "\n" +
                            "Monkey 1:\n" +
                            "  Starting items: 54, 65, 75, 74\n" +
                            "  Operation: new = old + 6\n" +
                            "  Test: divisible by 19\n" +
                            "    If true: throw to monkey 2\n" +
                            "    If false: throw to monkey 0\n" +
                            "\n" +
                            "Monkey 2:\n" +
                            "  Starting items: 79, 60, 97\n" +
                            "  Operation: new = old * old\n" +
                            "  Test: divisible by 13\n" +
                            "    If true: throw to monkey 1\n" +
                            "    If false: throw to monkey 3\n" +
                            "\n" +
                            "Monkey 3:\n" +
                            "  Starting items: 74\n" +
                            "  Operation: new = old + 3\n" +
                            "  Test: divisible by 17\n" +
                            "    If true: throw to monkey 0\n" +
                            "    If false: throw to monkey 1";
            Runner.run((Day) Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getDeclaredConstructor().newInstance(), "2022-12-11");
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException exception) {
            System.out.println("Class couldn't be found please enter it manually");
        }
    }
}
