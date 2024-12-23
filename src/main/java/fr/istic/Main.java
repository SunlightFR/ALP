package fr.istic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class Main {
    public static void main(String[] args){
        Afficheur a1 = new Afficheur();
        Afficheur a2 = new Afficheur();

        List<ObserverDeCapteurAsync> canaux = new ArrayList<>();
        canaux.add(new Canal(a1));
        canaux.add(new Canal(a2));

        Capteur capteur = new CapteurImpl();
        for(ObserverDeCapteurAsync c:canaux){
            capteur.attach(c);
        }




    }
}
