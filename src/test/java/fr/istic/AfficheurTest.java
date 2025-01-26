package fr.istic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class AfficheurTest {
    static int NB = 3;
    static List<Integer> TICKS = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    static ScheduledExecutorService scheduler;
    static List<Afficheur> afficheurs;
    static Capteur capteur;
    static List<Canal> canaux;


    @BeforeAll
    static void setup(){

        scheduler = new ScheduledThreadPoolExecutor(2+2*NB);
        capteur = new CapteurImpl();
        afficheurs = new ArrayList<>();
        canaux = new ArrayList<>();
        for(int i =0; i<NB; i++){
            Afficheur a = new Afficheur("Afficheur "+i);
            Canal c = new Canal(a, scheduler);

            afficheurs.add(a);
            canaux.add(c);

            capteur.attach(c);
        }
    }

    /**
     * Exécution des ticks*
     */
    private void run() throws InterruptedException {
        scheduler.scheduleAtFixedRate(capteur::tick, 0, 500, TimeUnit.MILLISECONDS);
        scheduler.schedule(()->scheduler.shutdown(), 5, TimeUnit.SECONDS);
        scheduler.awaitTermination(6, TimeUnit.SECONDS);
    }

    /**
     * Vérifie que liste2 est un préfixe de liste1 (pour atomique)
     */
    private boolean isPrefix(List<Integer> liste1, List<Integer> liste2) {
        if (liste2.size() > liste1.size()) {
            return false;
        }
        return liste1.subList(0, liste2.size()).equals(liste2);
    }

    /**
     * Vérifie que la liste est triée par ordre croissant (pour époque)
     */
    private boolean isSorted(List<Integer> list){
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Vérifie que liste2 est un sous-ensemble de liste1 (pour séquentielle et époque)
     */
    private boolean isSubset(List<Integer> liste1, List<Integer> liste2){
        return liste1.containsAll(liste2);
    }

    @Test
    public void testAtomique(){
        AlgoDiffusion atomique = new DiffusionAtomique();
        atomique.configure(capteur);
        try{
            run();
            //On vérifie que les afficheurs ont tous bien reçu [1,2,3,4,...]
            assertTrue(afficheurs.stream().allMatch(afficheur -> isPrefix(TICKS, afficheur.getValeurs())));
            //On vérifie ensuite que tous les afficheurs ont obtenu les mêmes valeurs
            for(int i = 1;i<NB;i++){
                assertEquals(afficheurs.getFirst().getValeurs(), afficheurs.get(i).getValeurs());
            }
        }catch(Exception e){
            fail();
        }
    }

    @Test
    public void testEpoque(){
        AlgoDiffusion epoque = new DiffusionEpoque();
        epoque.configure(capteur);
        try{
            run();
            //On vérifie que tous les afficheurs ont obtenu un sous-ensemble trié, mais pas forcément tous le même
            for(int i =0; i<NB; i++){
                assertTrue(isSubset(TICKS, afficheurs.get(i).getValeurs()));
                assertTrue(isSorted(afficheurs.get(i).getValeurs()));
            }

        }catch(Exception e){
            fail();
        }
    }

    @Test
    public void testSequentielle(){
        AlgoDiffusion sequentielle = new DiffusionSequentielle();
        sequentielle.configure(capteur);
        try{
            run();
            //On vérifie que tous les afficheurs ont tous obtenu le même sous-ensemble trié
            for(int i =0; i<NB; i++){
                assertTrue(isSubset(TICKS, afficheurs.get(i).getValeurs()));
                assertTrue(isSorted(afficheurs.get(i).getValeurs()));
            }
            for(int i = 1;i<NB;i++){
                assertEquals(afficheurs.getFirst().getValeurs(), afficheurs.get(i).getValeurs());
            }

        }catch(Exception e){
            fail();
        }
    }

}