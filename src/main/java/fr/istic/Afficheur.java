package fr.istic;

public class Afficheur implements ObserverDeCapteur{

    @Override
    public void update(Capteur capteur) {
        System.out.println(capteur.getValue());
    }
}
