package fr.istic;

public class Update implements Runnable {
    private Capteur capteur;
    private ObserverDeCapteur afficheur;
    private CapteurAsync canal;

    public Update(Capteur capteur, ObserverDeCapteur afficheur, CapteurAsync canal){
        this.capteur = capteur;
        this.afficheur = afficheur;
        this.canal = canal;
    }

    @Override
    public void run() {
        afficheur.update(capteur, canal);
    }
}
