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

    public ArrayList<Integer> load() {
        ArrayList<Integer> res = new ArrayList<>();
        int lastLoaded = readFile();

        if (lastLoaded > 0) {
            for (int i = lastLoaded - 9; i <= lastLoaded; i++) {
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
