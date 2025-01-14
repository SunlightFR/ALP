package fr.istic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Canal implements ObserverDeCapteurAsync, CapteurAsync{
    private ScheduledExecutorService s;
    private ObserverDeCapteur afficheur;

    public Canal(ObserverDeCapteur afficheur, ScheduledExecutorService s){
        this.afficheur = afficheur;
        this.s = s;
    }


    @Override
    public Future update(Capteur capteur) {
        return s.schedule(new Update(capteur, afficheur, this), 500 + (int)(Math.random() * (1000)), TimeUnit.MILLISECONDS);
    }

    @Override
    public Future<Integer> getValue(Capteur capteur){
        return s.schedule(new GetValue(capteur), 500 + (int)(Math.random() * (1500)), TimeUnit.MILLISECONDS);
    }

}
