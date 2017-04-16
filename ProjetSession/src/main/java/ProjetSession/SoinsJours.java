package ProjetSession;

/**
 * Classe permettant de comptabiliser le nombre de soins par jours
 *
 * @author Leopold Quenum
 * @author JP Rioux
 */
public class SoinsJours {
    
    private int nbSoin;

    public void incrementeNbSoin(){
        this.nbSoin++;
    }
    
    public int getNbSoin() {
        return nbSoin;
    }
    
}
