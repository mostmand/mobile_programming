package me.telegram.android;

public class NotificationCenter {
    private static final NotificationCenter instance = new NotificationCenter();

    private NotificationCenter(){
        this.postsLoadedEvent = new MyEvent();
        this.commentsLoadedEvent = new MyEvent();
    }

    public static NotificationCenter getInstance(){
        return instance;
    }

    public void postsLoaded(){
        postsLoadedEvent.occur();
    }

    public void commentsLoaded(){
        commentsLoadedEvent.occur();
    }

    private MyEvent postsLoadedEvent;
    private MyEvent commentsLoadedEvent;

    public MyEvent getPostsLoadedEvent() {
        return postsLoadedEvent;
    }

    public MyEvent getCommentsLoadedEvent() { return commentsLoadedEvent; }
}
