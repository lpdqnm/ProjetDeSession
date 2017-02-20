import java.util.Arrays;

/**
* Tout ce qui a rapport au dossier de réclmations
*
* @author Leopold Quenum
* @author JP Rioux
* @version 1.0
* @since   2017-02-27 
*/
public class Dossier {
    
    private final String numeroClient;
    private final String contrat;
    private final String mois;
    private final Reclamation []reclamations;
    private Remboursement [] remboursements;

    public Dossier(String numeroClient, String contrat, String mois, 
            Reclamation[] reclamations, Remboursement[] remboursements) {
        this.numeroClient = numeroClient;
        this.contrat = contrat;
        this.mois = mois;
        this.reclamations = reclamations;
        this.remboursements = remboursements;
    }

    public boolean estValide(){
        if(!this.estValideNumeroClient() || !this.estValideContrat())
            return false;
  
        for (int i = 0; i < this.reclamations.length; i++) { 
            if(!reclamations[i].estValide(this.mois))
                return false;
        }
        
        return true;
    }
    
    private boolean estValideNumeroClient(){
        /**
         * REGEX 
         * Valide si on a exactement 6 chiffres entre 0 et 9
         */
        return this.numeroClient.matches("[0-9]{6}");
    }
    
    private boolean estValideContrat(){
        /**
         * REGEX
         * Valide si contenu entre A et E inclusivement (Majuscule)
         */
        return this.contrat.matches("[A-E]");
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

    public Reclamation[] getReclamations() {
        return reclamations;
    }

    public Remboursement[] getRemboursements() {
        return remboursements;
    }

    public void setRemboursements(Remboursement[] remboursements) {
        this.remboursements = remboursements;
    }

    @Override
    public String toString() {
        return "Dossier{" + "numeroClient=" + numeroClient + ", contrat=" 
                + contrat + ", mois=" + mois + ",\nreclamations=" 
                + Arrays.toString(reclamations) + ",\nremboursements=" 
                + Arrays.toString(remboursements) + '}';
    }
}
