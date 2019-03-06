package me.telegram.android;

import java.util.ArrayList;

public class MessageController {

    private static final MessageController instance = new MessageController();

    private MessageController() {
    }

    public static MessageController getInstance() {
        return instance;
    }

    private ArrayList<Integer> messages = new ArrayList<>();

    public ArrayList<Integer> fetch(boolean fromCache) {
        ArrayList<Integer> res;
        if (fromCache) {
            res=StorageManager.getInstance().load();
        } else {
            res= ConnectionManager.getInstance().load(10);
            StorageManager.getInstance().save(res.get(9));
        }
        return res;
    }
}
