package me.telegram.android;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class StorageManager {
    private Context context;
    private static final StorageManager instance = new StorageManager();

    private StorageManager() {
    }

    public static StorageManager getInstance(Context context) {
        instance.context = context;
        return instance;
    }

    FileOutputStream file;


    public int getIdx() {
        int idx = 0;
        try {
            file = this.context.openFileOutput("tmp.txt", Context.MODE_PRIVATE);
            BufferedReader reader = new BufferedReader(new FileReader("tmp.txt"));
            idx = Integer.parseInt(reader.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return idx;
    }

    public ArrayList<Integer> load() {
        ArrayList<Integer> res = new ArrayList<>();
        int lastLoaded = getIdx();

        if (lastLoaded > 0) {
            for (int i = lastLoaded - 9; i <= lastLoaded; i++) {
                res.add(i);
            }
        }

        return res;
    }

    public void save(int number) {
        try {
            file = this.context.openFileOutput("tmp.txt", Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(file));
            writer.write(number);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
