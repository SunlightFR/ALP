package fr.istic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class Afficheur implements ObserverDeCapteur {
    private List<Integer> valeurs;
    private String name;

    public Afficheur(String name){
        this.name = name;
        this.valeurs = new ArrayList<>();
    }

    @Override
    public void update(Capteur capteur, CapteurAsync capteurAsync) {
        Future<Integer> value = capteurAsync.getValue(capteur);
        try {
            valeurs.add(value.get());
        }catch(Exception e){

        }
    }

    @Override
    public List<Integer> getValeurs() {
        return valeurs;
    }
}
