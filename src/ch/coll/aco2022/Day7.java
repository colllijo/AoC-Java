package ch.coll.aco2022;

import ch.coll.aoc.Day;
import ch.coll.aoc.Runner;
import ch.coll.utilities.FileSystemNode;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Day7 implements Day {
    @Override
    public String part1(String input) {
        int size = 0;
        String curPath = "";
        FileSystemNode root = new FileSystemNode();
        root.path = "";
        root.name = "/";
        root.isFile = false;
        root.parent = null;

        ArrayList<String> directories = new ArrayList<>();

        FileSystemNode curNode = root;

        for (String line : input.split("\n")) {
            if (line.charAt(0) == '$') {
                //current line is a command
                if (line.split(" ")[1].equals("cd")) {
                    //command is cd
                    if (line.split(" ")[2].equals("..")) {
                        //go up a directory
                        curPath = (curPath.substring(0, curPath.lastIndexOf("/")));
                        curPath = curPath.equals("") ? "/" : curPath;
                        curNode = curNode.parent;
                    } else {
                        //move to directory
                        curPath = (curPath + "/" + line.split(" ")[2]).replace("//", "/");
                        curNode = curNode.getChildByName(line.split(" ")[2]);

                        if(!directories.contains(curPath)) {
                            directories.add(curPath);
                        }
                    }
                }
                //ls command can be ignored
            } else {
                //current line is ls output
                FileSystemNode output = new FileSystemNode();
                if (line.split(" ")[0].equals("dir")) {
                    //line is directory
                    output.path = curPath;
                    output.name = line.split(" ")[1];
                    output.isFile = false;

                    output.parent = curNode;
                    curNode.children.add(output);
                } else {
                    //line is file
                    output.path = curPath;
                    output.name = line.split(" ")[1];
                    output.isFile = true;
                    output.size = Integer.parseInt(line.split(" ")[0]);

                    output.parent = curNode;
                    curNode.children.add(output);
                }
            }
        }

        for(int i = 0; i < directories.size(); i++) {
            curNode = root;
            for(int j = 0; j < directories.get(i).split("/").length; j++) {
                curNode = curNode.getChildByName(directories.get(i).split("/")[j]);
            }

            directories.set(i, directories.get(i) + " " + curNode.getSize());
        }

        for(String directory : directories) {
            if(Integer.parseInt(directory.split(" ")[1]) <= 100000) {
                size += Integer.parseInt(directory.split(" ")[1]);
            }
        }

        return size + "";
    }

    @Override
    public String part2(String input) {
        int totalSize = 70000000;
        int availableSize = 0;
        int updateSize = 30000000;
        int bestPossibleSize = totalSize;

        int size = 0;
        String curPath = "";
        FileSystemNode root = new FileSystemNode();
        root.path = "";
        root.name = "/";
        root.isFile = false;
        root.parent = null;

        ArrayList<String> directories = new ArrayList<>();

        FileSystemNode curNode = root;

        for (String line : input.split("\n")) {
            if (line.charAt(0) == '$') {
                //current line is a command
                if (line.split(" ")[1].equals("cd")) {
                    //command is cd
                    if (line.split(" ")[2].equals("..")) {
                        //go up a directory
                        curPath = (curPath.substring(0, curPath.lastIndexOf("/")));
                        curPath = curPath.equals("") ? "/" : curPath;
                        curNode = curNode.parent;
                    } else {
                        //move to directory
                        curPath = (curPath + "/" + line.split(" ")[2]).replace("//", "/");
                        curNode = curNode.getChildByName(line.split(" ")[2]);

                        if(!directories.contains(curPath)) {
                            directories.add(curPath);
                        }
                    }
                }
                //ls command can be ignored
            } else {
                //current line is ls output
                FileSystemNode output = new FileSystemNode();
                if (line.split(" ")[0].equals("dir")) {
                    //line is directory
                    output.path = curPath;
                    output.name = line.split(" ")[1];
                    output.isFile = false;

                    output.parent = curNode;
                    curNode.children.add(output);
                } else {
                    //line is file
                    output.path = curPath;
                    output.name = line.split(" ")[1];
                    output.isFile = true;
                    output.size = Integer.parseInt(line.split(" ")[0]);

                    output.parent = curNode;
                    curNode.children.add(output);
                }
            }
        }

        for(int i = 0; i < directories.size(); i++) {
            curNode = root;
            for(int j = 0; j < directories.get(i).split("/").length; j++) {
                curNode = curNode.getChildByName(directories.get(i).split("/")[j]);
            }

            directories.set(i, directories.get(i) + " " + curNode.getSize());
        }

        availableSize = totalSize - Integer.parseInt(directories.get(0).split(" ")[1]);

        for(String directory : directories) {
            if(Integer.parseInt(directory.split(" ")[1]) > (updateSize - availableSize) && Integer.parseInt(directory.split(" ")[1]) < bestPossibleSize) {
                bestPossibleSize = Integer.parseInt(directory.split(" ")[1]);
            }
        }

        return bestPossibleSize + "";
    }

    public static void main(String[] args) {
        try {
            Runner.input = "$ cd /\n" +
                    "$ ls\n" +
                    "dir a\n" +
                    "14848514 b.txt\n" +
                    "8504156 c.dat\n" +
                    "dir d\n" +
                    "$ cd a\n" +
                    "$ ls\n" +
                    "dir e\n" +
                    "29116 f\n" +
                    "2557 g\n" +
                    "62596 h.lst\n" +
                    "$ cd e\n" +
                    "$ ls\n" +
                    "584 i\n" +
                    "$ cd ..\n" +
                    "$ cd ..\n" +
                    "$ cd d\n" +
                    "$ ls\n" +
                    "4060174 j\n" +
                    "8033020 d.log\n" +
                    "5626152 d.ext\n" +
                    "7214296 k";
            Runner.run((Day) Class.forName(Thread.currentThread().getStackTrace()[1].getClassName()).getDeclaredConstructor().newInstance(), "2022-12-07");
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException exception) {
            System.out.println("Class couldn't be found please enter it manually");
        }
    }
}
