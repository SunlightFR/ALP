package fr.istic;

import java.util.concurrent.Callable;

public class GetValue implements Callable<Integer> {
    private Capteur capteur;

    public GetValue(Capteur capteur){
        this.capteur = capteur;
    }

    @Override
    public Integer call(){
        return capteur.getValue();
    }
}
