package fr.istic;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Afficheur implements ObserverDeCapteur {
    private String name;

    public Afficheur(String name){
        this.name = name;
    }

    @Override
    public void update(Capteur capteur, CapteurAsync capteurAsync) {
        System.out.println("update2");
        Future<Integer> value = capteurAsync.getValue(capteur);
        try{
            System.out.println("Afficheur " + name + " : capteur " + capteur.getName() + " : " + value.get());
        }catch(Exception e){
            System.out.println("Erreur afficher");
        }
    }
}
