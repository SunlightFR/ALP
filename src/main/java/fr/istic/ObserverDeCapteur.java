package fr.istic;

import java.util.List;

public interface ObserverDeCapteur {
    void update(Capteur capteur, CapteurAsync capteurAsync);
    List<Integer> getValeurs();
}
