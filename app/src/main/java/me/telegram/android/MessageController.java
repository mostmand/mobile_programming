package me.telegram.android;

import java.util.ArrayList;

public class MessageController {

    private static final MessageController instance = new MessageController();

    private MessageController() {
    }

    public static MessageController getInstance() {
        return instance;
    }

    public ArrayList<Integer> messages = new ArrayList<>();

    private void addMessages(ArrayList<Integer> messages){
        this.messages.addAll(messages);
        NotificationCenter.getInstance().dataLoaded();
    }

    public void fetch(boolean fromCache) {
        if (fromCache) {
            Thread storage = new Thread(new Runnable() {
                @Override
                public void run() {
                    ArrayList<Integer> res = StorageManager.getInstance().load();
                    addMessages(res);
                }
            });
            storage.start();
        } else {
            Thread cloud = new Thread(new Runnable() {
                @Override
                public void run() {
                    ArrayList<Integer> res = ConnectionManager.getInstance().load(10);
                    StorageManager.getInstance().save(res.get(res.size() - 1));
                    addMessages(res);
                }
            });
            cloud.start();
        }
    }
}
