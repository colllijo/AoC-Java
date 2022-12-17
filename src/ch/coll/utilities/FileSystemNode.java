package ch.coll.utilities;

import java.util.ArrayList;

public class FileSystemNode {
    public String name;
    public String path;
    public boolean isFile;
    public int size;

    public FileSystemNode parent;
    public ArrayList<FileSystemNode> children = new ArrayList<>();

    public int getSize() {
        if(isFile) {
            return size;
        } else {
            int totalSize = 0;
            for(FileSystemNode child : children) {
                totalSize += child.getSize();
            }
            return totalSize;
        }
    }

    public FileSystemNode getChildByName(String name) {
        for(int i = 0; i < children.size(); i++) {
            if(children.get(i).name.equals(name)) {
                return children.get(i);
            }
        }
        return this;
    }
}
