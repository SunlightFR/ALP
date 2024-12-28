package fr.istic;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Afficheur implements ObserverDeCapteur {
    private ScheduledExecutorService s = new ScheduledThreadPoolExecutor(5);
    private Canal canal;
    private String name;

    public Afficheur(String name){
        this.name = name;
    }

    public void setCanal(Canal canal){
        this.canal = canal;
    }

    @Override
    public void update(Capteur capteur) {
        Future<Integer> value = canal.getValue(capteur);
        try{
            System.out.println("Afficheur " + name + " : capteur " + capteur.getName() + " : " + value.get());
        }catch(Exception e){
            System.out.println("Erreur afficher");
        }
    }
}
