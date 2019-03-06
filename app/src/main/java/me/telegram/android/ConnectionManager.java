package me.telegram.android;

import java.util.ArrayList;

public class ConnectionManager {

    private static final ConnectionManager instance = new ConnectionManager();

    private ConnectionManager() {
    }

    public static ConnectionManager getInstance() {
        return instance;
    }

    public ArrayList<Integer> load(int lastLoaded) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = lastLoaded + 1; i <= lastLoaded + 10; i++) {
            res.add(i);
        }
        return res;
    }

}
