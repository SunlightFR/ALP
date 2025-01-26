package fr.istic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CapteurImpl implements Capteur{
    private final List<ObserverDeCapteurAsync> observers = new ArrayList<>();
    private int value = 0;
    private AlgoDiffusion algo;

    @Override
    public int getValue() {
        return algo.execute();
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
