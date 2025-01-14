package fr.istic;

import java.util.concurrent.Future;

public interface CapteurAsync {
    Future<Integer> getValue(Capteur capteur);
}
