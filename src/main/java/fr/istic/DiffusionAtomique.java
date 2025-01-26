package fr.istic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class DiffusionAtomique implements AlgoDiffusion{
    private List<Future<Integer>> futures;
    private Capteur capteur;

    public DiffusionAtomique(){
        this.futures = new ArrayList<Future<Integer>>();
    }

    @Override
    public void configure(Capteur capteur) {
        this.capteur = capteur;
    }

    @Override
    public int execute() {
        return 0;
    }
}
