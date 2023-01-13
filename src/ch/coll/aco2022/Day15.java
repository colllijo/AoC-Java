package ch.coll.aco2022;

import ch.coll.aoc.Day;
import ch.coll.aoc.Runner;

import java.awt.*;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Day15 implements Day {
    @Override
    public String part1(String input) {
        int rowToCheck;

        if(input.split("\n")[0].equals("test")) {
            rowToCheck = 10;
            input = input.substring(input.indexOf("\n") + 1);
        } else {
            rowToCheck = 2000000;
        }

        ArrayList<Sensor> sensors = new ArrayList<>();

        for(String reading : input.split("\n")) {
            Sensor sensor = new Sensor();

            sensor.x = Integer.parseInt(reading.split("x=")[1].split(",")[0]);
            sensor.y = Integer.parseInt(reading.split("y=")[1].split(":")[0]);

            sensor.closestBeacon.x = Integer.parseInt(reading.split("x=")[2].split(",")[0]);
            sensor.closestBeacon.y = Integer.parseInt(reading.split("y=")[2].split("\n")[0]);

            sensor.distanceToClosestBeacon = Math.abs(sensor.x - sensor.closestBeacon.x) + Math.abs(sensor.y - sensor.closestBeacon.y);

            sensors.add(sensor);
        }

        ArrayList<int[]> ranges = getRangesForRow(sensors, rowToCheck);

        int coveredPositions = 0;

        for(int[] range : ranges) {
            coveredPositions += range[1] - range[0];
        }

        return coveredPositions + "";
    }

    @Override
    public String part2(String input) {
        int gridSize;

        if(input.split("\n")[0].equals("test")) {
            gridSize = 20;
            input = input.substring(input.indexOf("\n") + 1);
        } else {
            gridSize = 4000000;
        }

        ArrayList<Sensor> sensors = new ArrayList<>();

        for(String reading : input.split("\n")) {
            Sensor sensor = new Sensor();

            sensor.x = Integer.parseInt(reading.split("x=")[1].split(",")[0]);
            sensor.y = Integer.parseInt(reading.split("y=")[1].split(":")[0]);

            sensor.closestBeacon.x = Integer.parseInt(reading.split("x=")[2].split(",")[0]);
            sensor.closestBeacon.y = Integer.parseInt(reading.split("y=")[2].split("\n")[0]);

            sensor.distanceToClosestBeacon = Math.abs(sensor.x - sensor.closestBeacon.x) + Math.abs(sensor.y - sensor.closestBeacon.y);

            sensors.add(sensor);
        }

        for(int y = 0; y <= gridSize ; y++) {
            ArrayList<int[]> ranges = getRangesForRow(sensors, y);

            if(ranges.size() > 1) {
                for(int[] range : ranges) {
                    if(range[0] > 0) {
                        return ((range[0] - 1) * 4000000L + y) + "";
                    } else if(range[1] < gridSize) {
                        return ((range[1] + 1) * 4000000L + y) + "";
                    }
                }
            }
        }

        return "bad run";
    }

    public ArrayList<int[]> getRangesForRow(ArrayList<Sensor> sensors, int row) {
        ArrayList<int[]> ranges = new ArrayList<>();

        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;

        for(Sensor sensor : sensors) {
            minValue = Math.min(minValue, sensor.x - (sensor.distanceToClosestBeacon - Math.abs(row - sensor.y)));
            maxValue = Math.max(maxValue, sensor.x + (sensor.distanceToClosestBeacon - Math.abs(row - sensor.y)));
        }

        boolean activeRange = true;
        int[] range = new int[2];
        int tempMax = 0;
        range[0] = minValue;

        for(int x = minValue + 1; x <= maxValue; x++) {
            boolean covered = false;

            for(Sensor sensor : sensors) {
                if(Math.abs(sensor.x - x) + Math.abs(sensor.y - row) <= sensor.distanceToClosestBeacon) {
                    covered = true;
                    x = sensor.x + (sensor.distanceToClosestBeacon - Math.abs(row - sensor.y));
                    tempMax = x;
                    break;
                }
            }

            if(!covered) {
                if(activeRange) {
                    activeRange = false;
                    range[1] = tempMax;
                    tempMax = 0;
                    ranges.add(range);
                    range = new int[2];
                }
            } else if(!activeRange) {
                activeRange = true;
                range[0] = x;
            }
        }

        if(activeRange) {
            activeRange = false;
            range[1] = tempMax;
            tempMax = 0;
            ranges.add(range);
            range = new int[2];
        }

        return ranges;
    }

    public static void main(String[] args) {
        try {
            Runner.input =
                    "test\n" +
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

    public Point closestBeacon = new Point();
    public int distanceToClosestBeacon;
}