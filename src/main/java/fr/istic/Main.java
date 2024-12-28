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

        List<ObserverDeCapteurAsync> canaux = new ArrayList<>();
        Canal c1 = new Canal(a1);
        canaux.add(c1);
        Canal c2 = new Canal(a2);
        canaux.add(c2);

        a1.setCanal(c1);
        a2.setCanal(c2);

        Capteur capteur = new CapteurImpl("1");
        Capteur capteur2 = new CapteurImpl("2");
        for(ObserverDeCapteurAsync c:canaux){
            capteur.attach(c);
            capteur2.attach(c);
        }
        try{
            ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(5);
            scheduler.scheduleAtFixedRate(capteur::tick, 0, 500, TimeUnit.MILLISECONDS);
            scheduler.scheduleAtFixedRate(capteur2::tick, 3, 500, TimeUnit.MILLISECONDS);

        }catch(Exception e){
            System.out.println("Erreur main");
        }
    }
}
