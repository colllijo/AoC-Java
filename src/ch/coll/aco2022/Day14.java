package ch.coll.aco2022;

import ch.coll.aoc.Day;
import ch.coll.aoc.Runner;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Day14 implements Day {
    @Override
    public String part1(String input) {
        ArrayList<Point> rocks = new ArrayList<>();
        Point sandOrigin = new Point(500, 0);


        //recreate scan
        for (String path : input.split("\n")) {
            int prevX = -1;
            int prevY = -1;

            for (String cornerPoint : path.split(" -> ")) {
                int x = Integer.parseInt(cornerPoint.split(",")[0]);
                int y = Integer.parseInt(cornerPoint.split(",")[1]);

                if (prevX == -1 && prevY == -1) {
                    if (!rocks.contains(new Point(x, y))) {
                        rocks.add(new Point(x, y));
                    }
                } else {
                    if (prevX == x) {
                        if (y < prevY) {
                            for (int lineY = y; lineY <= prevY; lineY++) {
                                if (!rocks.contains(new Point(x, lineY))) {
                                    rocks.add(new Point(x, lineY));
                                }
                            }
                        } else {
                            for (int lineY = prevY; lineY <= y; lineY++) {
                                if (!rocks.contains(new Point(x, lineY))) {
                                    rocks.add(new Point(x, lineY));
                                }
                            }
                        }
                    } else if (prevY == y) {
                        if (x < prevX) {
                            for (int lineX = x; lineX <= prevX; lineX++) {
                                if(!rocks.contains(new Point(lineX, y))) {
                                    rocks.add(new Point(lineX, y));
                                }
                            }
                        } else {
                            for (int lineX = prevX; lineX <= x; lineX++) {
                                if(!rocks.contains(new Point(lineX, y))) {
                                    rocks.add(new Point(lineX, y));
                                }
                            }
                        }
                    }
                }

                prevX = x;
                prevY = y;
            }
        }

        int lowesPoint = -1;

        for(Point rock : rocks) {
            if(rock.y > lowesPoint) {
                lowesPoint = rock.y;
            }
        }

        //simulate sand
        int sandCounter = 0;
        boolean overflow = false;

        while (!overflow) {
            Point curSand = (Point) sandOrigin.clone();
            sandCounter++;
            boolean stopped = false;

            while (!stopped) {
                if(!rocks.contains(new Point(curSand.x, curSand.y + 1))) {
                    curSand.y++;
                } else if(!rocks.contains(new Point(curSand.x - 1, curSand.y + 1))) {
                    curSand.x--;
                    curSand.y++;
                } else if (!rocks.contains(new Point(curSand.x +1, curSand.y + 1))) {
                    curSand.x++;
                    curSand.y++;
                } else {
                    stopped = true;
                    rocks.add((Point) curSand.clone());
                }

                if(curSand.y > lowesPoint) {
                    overflow = true;
                    stopped = true;
                }
            }
        }

        return (sandCounter - 1) + "";
    }

    @Override
    public String part2(String input) {
        ArrayList<Point> rocks = new ArrayList<>();
        Point sandOrigin = new Point(500, 0);


        //recreate scan
        for (String path : input.split("\n")) {
            int prevX = -1;
            int prevY = -1;

            for (String cornerPoint : path.split(" -> ")) {
                int x = Integer.parseInt(cornerPoint.split(",")[0]);
                int y = Integer.parseInt(cornerPoint.split(",")[1]);

                if (prevX == -1 && prevY == -1) {
                    if (!rocks.contains(new Point(x, y))) {
                        rocks.add(new Point(x, y));
                    }
                } else {
                    if (prevX == x) {
                        if (y < prevY) {
                            for (int lineY = y; lineY <= prevY; lineY++) {
                                if (!rocks.contains(new Point(x, lineY))) {
                                    rocks.add(new Point(x, lineY));
                                }
                            }
                        } else {
                            for (int lineY = prevY; lineY <= y; lineY++) {
                                if (!rocks.contains(new Point(x, lineY))) {
                                    rocks.add(new Point(x, lineY));
                                }
                            }
                        }
                    } else if (prevY == y) {
                        if (x < prevX) {
                            for (int lineX = x; lineX <= prevX; lineX++) {
                                if(!rocks.contains(new Point(lineX, y))) {
                                    rocks.add(new Point(lineX, y));
                                }
                            }
                        } else {
                            for (int lineX = prevX; lineX <= x; lineX++) {
                                if(!rocks.contains(new Point(lineX, y))) {
                                    rocks.add(new Point(lineX, y));
                                }
                            }
                        }
                    }
                }

                prevX = x;
                prevY = y;
            }
        }

        int lowesPoint = -1;

        for(Point rock : rocks) {
            if(rock.y > lowesPoint) {
                lowesPoint = rock.y;
            }
        }

        //simulate sand
        int sandCounter = 0;
        boolean overflow = false;

        while (!overflow) {
            Point curSand = (Point) sandOrigin.clone();
            sandCounter++;
            boolean stopped = false;

            while (!stopped) {
                if(!rocks.contains(new Point(curSand.x, curSand.y + 1)) && curSand.y + 1 < lowesPoint + 2) {
                    curSand.y++;
                } else if(!rocks.contains(new Point(curSand.x - 1, curSand.y + 1)) && curSand.y + 1 < lowesPoint + 2) {
                    curSand.x--;
                    curSand.y++;
                } else if (!rocks.contains(new Point(curSand.x +1, curSand.y + 1)) && curSand.y + 1 < lowesPoint + 2) {
                    curSand.x++;
                    curSand.y++;
                } else {
                    stopped = true;
                    rocks.add((Point) curSand.clone());
                }

                if(stopped && curSand.x == 500 && curSand.y == 0) {
                    overflow = true;
                }
            }
        }

        return sandCounter + "";
    }

    public static void main(String[] args) {
        try {
            Runner.input =
                    "498,4 -> 498,6 -> 496,6\n" +
                            "503,4 -> 502,4 -> 502,9 -> 494,9\n";
            Runner.run((Day) Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getDeclaredConstructor().newInstance(), "2022-12-14");
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException exception) {
            System.out.println("Class couldn't be found please enter it manually");
        }
    }
}
