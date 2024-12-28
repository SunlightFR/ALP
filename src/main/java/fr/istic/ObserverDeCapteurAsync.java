package fr.istic;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;

public interface ObserverDeCapteurAsync {
    Future<Integer> update(Capteur capteur);
    Future<Integer> getValue(Capteur capteur);
}
