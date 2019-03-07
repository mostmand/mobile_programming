package me.telegram.android;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class StorageManager {
    private static final StorageManager instance = new StorageManager();

    private StorageManager() {
    }

    public static StorageManager getInstance() {
        return instance;
    }

    int file = 0;

    public int getFile() {
        return file;
    }

    public ArrayList<Integer> load(int lastLoaded) {
        ArrayList<Integer> res = new ArrayList<>();
        int lastWritten = readFile();

        if (lastWritten > 0 && lastLoaded < lastWritten) {
            for (int i = lastLoaded + 1; i <= lastLoaded + 10; i++) {
                res.add(i);
            }
        }

        return res;
    }

    public void save(int number) {
        file = number;
    }

    private int readFile() {
        return file;
    }
}
