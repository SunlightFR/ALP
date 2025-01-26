package fr.istic;

import java.util.List;
import java.util.concurrent.Future;

public class DiffusionEpoque implements AlgoDiffusion{
    List<Future<Integer>> futures;
    List<ObserverDeCapteur> observers;
    Canal canal;
    Capteur capteur;

    @Override
    public void configure(Capteur capteur) {
        this.capteur = capteur;

    }

    @Override
    public int execute() {
        for(ObserverDeCapteur observer : observers){
            futures.add(canal.update(capteur));
        }

        return 0;
    }
}
