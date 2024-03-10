package service;

import java.io.File;
public class FileManager {

    public File[] allFolders(String path) {
        File directory = new File(path);
        return directory.listFiles(File::isDirectory);
    }

    public File[] allFiles(String path) {
        File directory = new File(path);
        return directory.listFiles(File::isFile);
    }
}