package me.telegram.android;
import java.util.Observable;

public class MyEvent extends Observable {
    public void occur(){
        setChanged();
        notifyObservers();
    }

    public MyEvent(){
    }
}
