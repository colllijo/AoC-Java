package ch.coll.aco2022;

import ch.coll.aoc.Day;
import ch.coll.aoc.Runner;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;

public class Day15 implements Day {
    @Override
    public String part1(String input) {
        ArrayList<Point> sensors = new ArrayList<>();
        ArrayList<Point> beacons = new ArrayList<>();
        ArrayList<Integer> covered = new ArrayList<>();


        int rowToCheck = 2000000;

        //Create sensors
        for (String reading : input.split("\n")) {
            sensors.add(new Point(Integer.parseInt(reading.split(" ")[2].split("=")[1].replace(",", "")), Integer.parseInt(reading.split(" ")[3].split("=")[1].replace(":", ""))));
            beacons.add(new Point(Integer.parseInt(reading.split(" ")[8].split("=")[1].replace(",", "")), Integer.parseInt(reading.split(" ")[9].split("=")[1].replace(":", ""))));
        }

        for (int i = 0; i < sensors.size(); i++) {
            int distance = Math.abs(sensors.get(i).x - beacons.get(i).x) + Math.abs(sensors.get(i).y - beacons.get(i).y);
            int coverage = distance - (Math.abs(rowToCheck - sensors.get(i).y) - 1);
            coverage = coverage * 2 - 1;

            for (int x = sensors.get(i).x - coverage / 2; x <= sensors.get(i).x + coverage / 2; x++) {
                covered.add(x);
            }
        }

        Collections.sort(covered);

        int uniqueValues = 0;

        if (covered.size() > 0) {
            int previousInteger = covered.get(0);

            for (Integer x : covered) {
                if (previousInteger != x) {
                    previousInteger = x;
                    uniqueValues++;
                }
            }
        }


        return uniqueValues + "";
    }

    @Override
    public String part2(String input) {
        ArrayList<Sensor> sensors = new ArrayList<>();
        ArrayList<Point> beacons = new ArrayList<>();
        ArrayList<Integer> covered = new ArrayList<>();

        //Create sensors
        for (String reading : input.split("\n")) {
            Sensor currentSensor = new Sensor();

            currentSensor.x = Integer.parseInt(reading.split(" ")[2].split("=")[1].replace(",", ""));
            currentSensor.y = Integer.parseInt(reading.split(" ")[3].split("=")[1].replace(":", ""));
            currentSensor.closestBeacon = new Point(Integer.parseInt(reading.split(" ")[8].split("=")[1].replace(",", "")), Integer.parseInt(reading.split(" ")[9].split("=")[1].replace(":", "")));
            currentSensor.distanceToClosestBeacon = Math.abs(currentSensor.x - currentSensor.closestBeacon.x) + Math.abs(currentSensor.y - currentSensor.closestBeacon.y);

            sensors.add(currentSensor);
            beacons.add(currentSensor.closestBeacon);
        }

        int tuningFrequency = 0;

        if(!input.equals("Sensor at x=2, y=18: closest beacon is at x=-2, y=15\n" +
                "Sensor at x=9, y=16: closest beacon is at x=10, y=16\n" +
                "Sensor at x=13, y=2: closest beacon is at x=15, y=3\n" +
                "Sensor at x=12, y=14: closest beacon is at x=10, y=16\n" +
                "Sensor at x=10, y=20: closest beacon is at x=10, y=16\n" +
                "Sensor at x=14, y=17: closest beacon is at x=10, y=16\n" +
                "Sensor at x=8, y=7: closest beacon is at x=2, y=10\n" +
                "Sensor at x=2, y=0: closest beacon is at x=2, y=10\n" +
                "Sensor at x=0, y=11: closest beacon is at x=2, y=10\n" +
                "Sensor at x=20, y=14: closest beacon is at x=25, y=17\n" +
                "Sensor at x=17, y=20: closest beacon is at x=21, y=22\n" +
                "Sensor at x=16, y=7: closest beacon is at x=15, y=3\n" +
                "Sensor at x=14, y=3: closest beacon is at x=15, y=3\n" +
                "Sensor at x=20, y=1: closest beacon is at x=15, y=3")) {
            for(int y = 0; y < 4000000; y++) {
                System.out.println("Checking y = " + y);
                for(int x = 0; x < 4000000; x++) {

                    boolean inRange = false;
                    for(Sensor sensor : sensors) {
                        if(Math.abs(x - sensor.x) + Math.abs(y - sensor.y) <= sensor.distanceToClosestBeacon) {
                            inRange = true;
                        }
                    }

                    if(!inRange) {
                        tuningFrequency = x * y;
                        return tuningFrequency + "";
                    }
                }
            }
        }

        return tuningFrequency + "";
    }

    public static void main(String[] args) {
        try {
            Runner.input =
                    "Sensor at x=2, y=18: closest beacon is at x=-2, y=15\n" +
                    "Sensor at x=9, y=16: closest beacon is at x=10, y=16\n" +
                    "Sensor at x=13, y=2: closest beacon is at x=15, y=3\n" +
                    "Sensor at x=12, y=14: closest beacon is at x=10, y=16\n" +
                    "Sensor at x=10, y=20: closest beacon is at x=10, y=16\n" +
                    "Sensor at x=14, y=17: closest beacon is at x=10, y=16\n" +
                    "Sensor at x=8, y=7: closest beacon is at x=2, y=10\n" +
                    "Sensor at x=2, y=0: closest beacon is at x=2, y=10\n" +
                    "Sensor at x=0, y=11: closest beacon is at x=2, y=10\n" +
                    "Sensor at x=20, y=14: closest beacon is at x=25, y=17\n" +
                    "Sensor at x=17, y=20: closest beacon is at x=21, y=22\n" +
                    "Sensor at x=16, y=7: closest beacon is at x=15, y=3\n" +
                    "Sensor at x=14, y=3: closest beacon is at x=15, y=3\n" +
                    "Sensor at x=20, y=1: closest beacon is at x=15, y=3";
            Runner.run((Day) Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getDeclaredConstructor().newInstance(), "2022-12-15");
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException exception) {
            System.out.println("Class couldn't be found please enter it manually");
        }
    }
}

class Sensor {
    public int x;
    public int y;

    public Point closestBeacon;
    public int distanceToClosestBeacon;
}
