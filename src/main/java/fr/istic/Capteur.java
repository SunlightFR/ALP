package fr.istic;

public interface Capteur {
    void attach(ObserverDeCapteurAsync o);
    int getValue();
    void tick();
    String getName();
}
