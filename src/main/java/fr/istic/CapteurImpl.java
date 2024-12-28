package fr.istic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CapteurImpl implements Capteur{
    private final List<ObserverDeCapteurAsync> observers = new ArrayList<>();
    private int value = 0;
    private String name;

    public CapteurImpl(String name){
        this.name = name;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void attach(ObserverDeCapteurAsync observer) {
        Objects.requireNonNull(observer);
        if(observers.contains(observer)){
            throw new IllegalArgumentException();
        }
        observers.add(observer);
    }

    @Override
    public void tick() {
        value++;
        notifyObservers();
    }

    private void notifyObservers() {
        for (ObserverDeCapteurAsync observer : observers) {
            observer.update(this);
        }
    }
}
