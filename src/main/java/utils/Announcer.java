package utils;

import city.City;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

public class Announcer {

    private Set<Listener> listeners;

    public Announcer() {
        this.listeners = new LinkedHashSet<>();
    }

    public void addListenet(Listener listener){
        listeners.add(listener);
    }

    public void deleteListener(Listener listener){
        listeners.remove(listener);
    }

    public void informListeners(String name, BigDecimal balance, City city){
        listeners.forEach(listener -> listener.informListener(name, balance, city));
    }
}
