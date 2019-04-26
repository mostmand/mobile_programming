package me.telegram.android;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class StorageManager {
    private Context context;
    private static final StorageManager instance = new StorageManager();
    private static File file;
    private StorageManager() {
    }

    public static StorageManager getInstance(Context context) {
        if (file == null){
            file  = new File(context.getFilesDir(),"file.txt");
        }
        instance.context = context;
        return instance;
    }

    FileOutputStream tempFile;

    private static void InitFile(Context context){
        try {
            FileOutputStream file = context.openFileOutput("tmp.txt", Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(file));
            writer.write(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getIdx() {
        int idx = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int index;
            String line;
            while ((line = reader.readLine()) != null) {
                idx = Integer.parseInt(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return idx;
    }

    public ArrayList<Integer> load(int lastLoaded) {
        ArrayList<Integer> res = new ArrayList<>();
        int lastWritten = getIdx();

        if (lastWritten > 0 && lastLoaded < lastWritten) {
            for (int i = lastLoaded + 1; i <= lastLoaded + 10; i++) {
                res.add(i);
            }
        }

        return res;
    }

    public void save(int number) {
        try {
            FileWriter writer = new FileWriter(file);
            writer.append("" + number);
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
