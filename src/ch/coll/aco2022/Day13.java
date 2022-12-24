package ch.coll.aco2022;

import ch.coll.aoc.Day;
import ch.coll.aoc.Runner;

import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class Day13 implements Day {
    @Override
    public String part1(String input) {
        int pairCounter = 1;
        ArrayList<Integer> orderedIndices = new ArrayList<>();

        for (String comparison : input.split("\n\n")) {
            if (checkLists(parseStringToList(comparison.split("\n")[0]), parseStringToList(comparison.split("\n")[1])).equals("ordered")) {
                orderedIndices.add(pairCounter);
            }

            pairCounter++;
        }

        int orderedSum = 0;

        for(Integer indic : orderedIndices) {
            orderedSum += indic;
        }

        return orderedSum + "";
    }

    public String checkLists(ArrayList<Object> leftList, ArrayList<Object> rightList) {
        boolean ordered = false;
        boolean checked = false;

        for (int i = 0; i < leftList.size(); i++) {
            if (i < rightList.size()) {
                if (leftList.get(i) instanceof Integer && rightList.get(i) instanceof Integer) {
                    //Both are Integers
                    if ((Integer) leftList.get(i) < (Integer) rightList.get(i)) {
                        ordered = true;
                        checked = true;
                        break;
                    } else if ((Integer) leftList.get(i) > (Integer) rightList.get(i)) {
                        ordered = false;
                        checked = true;
                        break;
                    }
                } else if (leftList.get(i) instanceof ArrayList<?> && rightList.get(i) instanceof ArrayList<?>) {
                    //Both are lists
                    String evaluation = checkLists((ArrayList<Object>) leftList.get(i), (ArrayList<Object>) rightList.get(i));
                    if (!evaluation.equals("continue")) {
                        checked = true;
                        ordered = evaluation.equals("ordered");
                        break;
                    }
                } else {
                    String evaluation = "";
                    if (leftList.get(i) instanceof Integer) {
                        evaluation = checkLists(new ArrayList<>(Arrays.asList(new Integer[]{(Integer) leftList.get(i)})), (ArrayList<Object>) rightList.get(i));
                    } else {
                        evaluation = checkLists((ArrayList<Object>) leftList.get(i), new ArrayList<>(Arrays.asList(new Integer[]{(Integer) rightList.get(i)})));
                    }

                    if (!evaluation.equals("continue")) {
                        checked = true;
                        ordered = evaluation.equals("ordered");
                        break;
                    }
                }
            } else {
                checked = true;
                ordered = false;
                break;
            }
        }

        String listEvaluation = "continue";

        if (checked) {
            if (ordered) {
                listEvaluation = "ordered";
            } else {
                listEvaluation = "unordered";
            }
        } else {
            if (leftList.size() < rightList.size()) {
                listEvaluation = "ordered";
            } else if (rightList.size() < leftList.size()) {
                listEvaluation = "unordered";
            }
        }

        return listEvaluation;
    }

    public ArrayList<Object> parseStringToList(String listString) {
        ArrayList<Object> list = new ArrayList<>();

        ArrayList<String> unconvertedList = new ArrayList<>();

        int listDepth = 0;

        int startIndex = 1;

        for (int i = 0; i < listString.length(); i++) {
            if (listString.charAt(i) == '[') {
                listDepth++;
            } else if (listString.charAt(i) == ']') {
                listDepth--;
            }

            if (listDepth == 1) {
                if (listString.charAt(i) == ',') {
                    unconvertedList.add(listString.substring(startIndex, i));
                    startIndex = i + 1;
                }
            }
        }
        unconvertedList.add(listString.substring(startIndex, listString.length() - 1));

        for(String item : unconvertedList) {
            if(!item.equals("")) {
                if(item.charAt(0) == '[') {
                    list.add(parseStringToList(item));
                } else {
                    list.add(Integer.parseInt(item));
                }
            }
        }

        return list;
    }

    @Override
    public String part2(String input) {
        input = input.replace("\n\n", "\n");
        input +=
                "[[2]]\n" +
                "[[6]]";

        ArrayList<String> packets = new ArrayList<>();

        for(String packet : input.split("\n")) {
            packets.add(packet);
        }

        for(int i = 0; i < packets.size(); i++) {
            for(int j = 0; j < packets.size() - 1 - i; j++) {
                if(checkLists(parseStringToList(packets.get(j)), parseStringToList(packets.get(j + 1))).equals("unordered")) {
                    String tempPacket = packets.get(j + 1);
                    packets.set(j + 1, packets.get(j));
                    packets.set(j, tempPacket);
                }
            }
        }

        int decoderKey = 0;

        for(int i = 0; i < packets.size(); i++) {
            if(packets.get(i).equals("[[2]]")) {
                decoderKey = i + 1;
            } else if(packets.get(i).equals("[[6]]")) {
                decoderKey *= i + 1;
            }
        }

        return decoderKey + "";
    }

    public static void main(String[] args) {
        try {
            Runner.input =
                    "[1,1,3,1,1]\n" +
                            "[1,1,5,1,1]\n" +
                            "\n" +
                            "[[1],[2,3,4]]\n" +
                            "[[1],4]\n" +
                            "\n" +
                            "[9]\n" +
                            "[[8,7,6]]\n" +
                            "\n" +
                            "[[4,4],4,4]\n" +
                            "[[4,4],4,4,4]\n" +
                            "\n" +
                            "[7,7,7,7]\n" +
                            "[7,7,7]\n" +
                            "\n" +
                            "[]\n" +
                            "[3]\n" +
                            "\n" +
                            "[[[]]]\n" +
                            "[[]]\n" +
                            "\n" +
                            "[1,[2,[3,[4,[5,6,7]]]],8,9]\n" +
                            "[1,[2,[3,[4,[5,6,0]]]],8,9]\n";
            Runner.run((Day) Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getDeclaredConstructor().newInstance(), "2022-12-13");
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException exception) {
            System.out.println("Class couldn't be found please enter it manually");
        }
    }
}