package utils;


import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

public class Announcer implements Serializable { //or Subject

    private Set<Listener> listeners;

    public Announcer() {
        this.listeners = new LinkedHashSet<>();
    }

    public void addListener(Listener listener){
        listeners.add(listener);
    }

    public void deleteListener(Listener listener){
        listeners.remove(listener);
    }

    public void informListeners(String message){
        listeners.forEach(listener -> listener.update(message));
    }
}
