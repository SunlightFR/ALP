package fr.istic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Canal implements ObserverDeCapteurAsync{
    private ScheduledExecutorService s = new ScheduledThreadPoolExecutor(5);
    private ObserverDeCapteur afficheur;

    public Canal(ObserverDeCapteur afficheur){
        this.afficheur = afficheur;
    }


    @Override
    public Future update(Capteur capteur) {
        return s.schedule(new Update(capteur, afficheur), 500, TimeUnit.MILLISECONDS);
    }

    @Override
    public Future<Integer> getValue(Capteur capteur){
        return s.schedule(new GetValue(capteur), 500, TimeUnit.MILLISECONDS);
    }

}
