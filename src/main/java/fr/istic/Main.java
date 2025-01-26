package fr.istic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args){
        Afficheur a1 = new Afficheur("1");
        Afficheur a2 = new Afficheur("2");

        ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(15);

        List<ObserverDeCapteurAsync> canaux = new ArrayList<>();
        Canal c1 = new Canal(a1, scheduler);
        canaux.add(c1);
        Canal c2 = new Canal(a2, scheduler);
        canaux.add(c2);

        Capteur capteur = new CapteurImpl();
        for(ObserverDeCapteurAsync c:canaux){
            capteur.attach(c);
        }
        try{
            scheduler.scheduleAtFixedRate(capteur::tick, 0, 500, TimeUnit.MILLISECONDS);
        }catch(Exception e){
            System.out.println("Erreur main");
        }
    }
}
