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
        ArrayList<Point> possiblePositions = new ArrayList<>();

        for (String reading : input.split("\n")) {
            Sensor curSensor = new Sensor();
            curSensor.x = Integer.parseInt(reading.split("x=")[1].split(", ")[0]);
            curSensor.y = Integer.parseInt(reading.split("y=")[1].split(": ")[0]);

            curSensor.closestBeacon = new Point(
                    Integer.parseInt(reading.split("x=")[2].split(", ")[0]),
                    Integer.parseInt(reading.split("y=")[2].split("\n")[0])
            );

            curSensor.distanceToClosestBeacon = Math.abs(curSensor.x - curSensor.closestBeacon.x) + Math.abs(curSensor.y - curSensor.closestBeacon.y);
            sensors.add(curSensor);
        }

        for (Sensor sensor : sensors) {
            for (int i = 0; i < sensor.distanceToClosestBeacon; i++) {
                possiblePositions.add(new Point(sensor.x + i, sensor.y + ((sensor.distanceToClosestBeacon + 1) - i)));
                possiblePositions.add(new Point(sensor.x + i, sensor.y - ((sensor.distanceToClosestBeacon + 1) - i)));
                possiblePositions.add(new Point(sensor.x - i, sensor.y + ((sensor.distanceToClosestBeacon + 1) - i)));
                possiblePositions.add(new Point(sensor.x - i, sensor.y - ((sensor.distanceToClosestBeacon + 1) - i)));
            }
        }

        System.out.println("with duplicates " + possiblePositions.size());

        Set<Point> s = new LinkedHashSet<>(possiblePositions);
        possiblePositions.clear();
        possiblePositions.addAll(s);

        System.out.println("without duplicates " + possiblePositions.size());

        System.out.println(possiblePositions.size());

        ArrayList<Point> actualPoints = new ArrayList<>();

        for (Point point : possiblePositions) {
            boolean distressOrigin = true;

            for (Sensor sensor : sensors) {
                if (Math.abs(sensor.x - point.x) + Math.abs(sensor.y - point.y) <= sensor.distanceToClosestBeacon) {
                    distressOrigin = false;
                    break;
                }
            }

            if (distressOrigin) {
                actualPoints.add(point);
            }
        }

        System.out.println(actualPoints.size());

        possiblePositions = actualPoints;
        actualPoints = new ArrayList<>();

        for(Point point : possiblePositions) {
            if(point.x >= 0 && point.x <= 4000000) {
                if(point.y >= 0 && point.y <= 4000000) {
                    actualPoints.add(point);
                }
            }
        }

        System.out.println(actualPoints.size());

        return "" + (((long) actualPoints.get(0).x * 4000000L) + ((long) actualPoints.get(0).y));
    }

    public static void main(String[] args) {
        try {
            Runner.input =
                    "";
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