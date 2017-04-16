package ProjetSession;

import java.util.Arrays;

/**
 * Tout ce qui a rapport au dossier de réclamations
 *
 * @author Leopold Quenum
 * @author JP Rioux
 */
public class Dossier {

    private static final String DOLLARS = "$";
    private static final int NB_MAX_SOINS = 4;
    private static final int NB_JOURS_MOIS = 31;
    
    private final String dossierClient;
    private String numeroClient;
    private String contrat;
    private final String mois;
    private final Reclamation[] reclamations;
    private Remboursement[] remboursements;
    private String total;
    private String erreur;

    public Dossier(String dossierClient, String mois, Reclamation[] reclamations,
            Remboursement[] remboursements, String total) {
        setNumeroClient(dossierClient);
        setContrat(dossierClient);
        this.dossierClient = dossierClient;
        this.mois = mois;
        this.reclamations = reclamations;
        this.remboursements = remboursements;
    }

    public boolean estValide() {
        if (!this.estValideContrat() || !this.estValideNumeroClient() || 
                !this.estValideNbSoinsJours()) {
            return false;
        }

        for (int i = 0; i < this.reclamations.length; i++) {
            if (!reclamations[i].estValide(this.mois)) {
                this.erreur = reclamations[i].getErreur();
                return false;
            }
        }

        return true;
    }

    private boolean estValideNumeroClient() {
        this.erreur = "Le numéro du client est invalide.";
        /*
            Regex
                Doit contenir exactement 6 chiffres
         */
        return this.numeroClient.matches("[0-9]{6}");
    }

    private boolean estValideContrat() {
        this.erreur = "Le contrat du client est invalide.";
        /*
            Regex
                Doit contenir une lettre entre A et E inclusivement
         */
        return this.contrat.matches("[A-E]");
    }
    
    private boolean estValideNbSoinsJours(){
        int nbReclamation =  getReclamations().length;
        this.erreur = "Le maximum de soins réclamés pour un même jour est de " + NB_MAX_SOINS + "." +
                " Vous en avez " + nbReclamation + " dans votre fichier.";
        
        SoinsJours[] tabSoinsJours = new SoinsJours[NB_JOURS_MOIS];        
        tabSoinsJours = genererSoinParJour(tabSoinsJours);
        
        for (int i = 0; i < tabSoinsJours.length; i++) {
            if(tabSoinsJours[i].getNbSoin() > NB_MAX_SOINS)
                return false;
        }
        
        return true;
    }
    
    private SoinsJours[] genererSoinParJour(SoinsJours[] tabSoinsJours){
        int jour;
        for (int i = 0; i < tabSoinsJours.length; i++) {
            tabSoinsJours[i] = new SoinsJours();
        }  
        
        for (int i = 0; i < this.reclamations.length; i++) {
            jour = this.reclamations[i].getJour();
            if(jour > 0 && jour <= NB_JOURS_MOIS)
                tabSoinsJours[jour-1].incrementeNbSoin();
        }  
        
        return tabSoinsJours;
    }

    public String getNumeroClient() {
        return numeroClient;
    }

    public String getContrat() {
        return contrat;
    }

    public String getMois() {
        return mois;
    }

    public String getErreur() {
        return erreur;
    }

    public Reclamation[] getReclamations() {
        return reclamations;
    }

    public Remboursement[] getRemboursements() {
        return remboursements;
    }

    public String getDossierClient() {
        return dossierClient;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal() {
        int total = 0;

        try {
            for (int i = 0; i < this.remboursements.length; i++) {
                total += Dollar.StringVersInt((remboursements[i].getMontant()));
            }
        } catch (Exception e) {
        }

        this.total = Dollar.IntVersString(total) + DOLLARS;
    }

    public void setRemboursements(Remboursement[] remboursements) {
        this.remboursements = remboursements;
    }

    public void setContrat(String dossierClient) {
        this.contrat = dossierClient.substring(0, 1);
    }

    public void setNumeroClient(String dossierClient) {
        this.numeroClient = dossierClient.substring(1);
    }

    public void setErreur(String erreur) {
        this.erreur = erreur;
    }

    @Override
    public String toString() {
        return "Dossier{" + "dossierClient=" + dossierClient + ", mois=" + mois
                + ",\nreclamations=" + Arrays.toString(reclamations)
                + ",\nremboursements=" + Arrays.toString(remboursements)
                + ",\ntotal=" + total + "}";
    }
}
