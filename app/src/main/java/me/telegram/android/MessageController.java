package me.telegram.android;

import android.content.Context;

import java.net.ContentHandler;
import java.util.ArrayList;

public class MessageController {

    private static final MessageController instance = new MessageController();

    private Context context;

    private MessageController() {
    }

    public static MessageController getInstance(Context context) {
        instance.context = context;
        return instance;
    }

    public ArrayList<Integer> messages = new ArrayList<>();

    private void addMessages(ArrayList<Integer> messages){
        this.messages.addAll(messages);
        NotificationCenter.getInstance().dataLoaded();
    }

    private void setMessages(ArrayList<Integer> messages){
        this.messages = new ArrayList<>(messages);
        NotificationCenter.getInstance().dataLoaded();
    }

    public void fetch(boolean fromCache) {
        if (fromCache) {

            Thread storage = new Thread(new Runnable() {
                @Override
                public void run() {
                    ArrayList<Integer> res = StorageManager.getInstance(context).load(getLastLoaded());
                    addMessages(res);
                }
            });
            storage.start();
        } else {
            Thread cloud = new Thread(new Runnable() {
                @Override
                public void run() {

                    ArrayList<Integer> res = ConnectionManager.getInstance().load(getLastLoaded());
                    StorageManager.getInstance(context).save(res.get(res.size() - 1));
                    addMessages(res);
                }
            });
            cloud.start();
        }
    }

    private int getLastLoaded(){
        if (this.messages.size() == 0)
            return 0;
        else
            return this.messages.get(this.messages.size() - 1);
    }
}
