package fr.istic;

import java.util.List;

public class Update implements Runnable {
    private Capteur capteur;
    private ObserverDeCapteur afficheur;

    public Update(Capteur capteur, ObserverDeCapteur afficheur){
        this.capteur = capteur;
        this.afficheur = afficheur;
    }

    @Override
    public void run() {
        afficheur.update(capteur);
    }
}
