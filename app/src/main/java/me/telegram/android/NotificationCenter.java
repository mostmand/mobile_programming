package me.telegram.android;

public class NotificationCenter {
    private static final NotificationCenter instance = new NotificationCenter();

    private NotificationCenter(){
        this.dataLoadedEvent = new MyEvent();
    }

    public static NotificationCenter getInstance(){
        return instance;
    }

    public void dataLoaded(){
        dataLoadedEvent.occur();
    }

    private MyEvent dataLoadedEvent;

    public MyEvent getDataLoadedEvent() {
        return dataLoadedEvent;
    }
}
