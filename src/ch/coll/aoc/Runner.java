package ch.coll.aoc;

import java.time.LocalDate;

public class Runner {
    public static String input;
    public static void run(Day d) {
        run(d, LocalDate.now().toString());
    }

    public static void run(Day d, String day) {
        Input.setDate(LocalDate.of(Integer.parseInt(day.split("[- ]")[0]), Integer.parseInt(day.split("[- ]")[1]), Integer.parseInt(day.split("[- ]")[2])));

        String part1t = null;
        if(input != null) {
            part1t = d.part1(input);
        }
        String part1 = d.part1(Input.getEntireInput());
        if(part1 == null) {
            part1 = "Part 1 has not yet been implemented";
        }
        if(part1t != null) {
            System.out.println("Part 1 Example: " + part1t);
        }
        System.out.println("Part 1 Solution: " + part1);
        if(!part1.equals("Part 1 has not yet been implemented")) {
            Input.getResultToClipboard(part1);
        }

        String part2t = null;
        if(input != null) {
            part2t = d.part2(input);
        }
        String part2 = d.part2(Input.getEntireInput());
        if(part2 == null) {
            part2 = "Part 2 has not yet been implemented";
        }
        if(part2t != null) {
            System.out.println("Part 2 Example: " + part2t);
        }
        System.out.println("Part 2 Solution: " + part2);
        if(!part2.equals("Part 2 has not yet been implemented")) {
            Input.getResultToClipboard(part2);
        }
    }
}
