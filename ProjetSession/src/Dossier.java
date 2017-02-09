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

    /**
     * Constructeur Dossier
     * @param numeroClient
     * @param contrat
     * @param mois
     * @param reclamations
     * @param remboursements 
     */
    public Dossier(String numeroClient, String contrat, String mois, 
            Reclamation[] reclamations, Remboursement[] remboursements) {
        this.numeroClient = numeroClient;
        this.contrat = contrat;
        this.mois = mois;
        this.reclamations = reclamations;
        this.remboursements = remboursements;
    }
    /**
     * Obtenir le numéro du client
     * @return String Numéro du client
     */
    public String getNumeroClient() {
        return numeroClient;
    }
    
    /**
     * Obtenir le contrat du client
     * @return String contrat
     */
    public String getContrat() {
        return contrat;
    }

    /**
     * Obtenir le mois de la demande
     * @return String le mois
     */
    public String getMois() {
        return mois;
    }

    /**
     * Obtenir les réclamations
     * @return Reclamation Les réclamations
     */
    public Reclamation[] getReclamations() {
        return reclamations;
    }

    /**
     * Obtenir les remboursements
     * @return Rebmoursement Remboursements
     */
    public Remboursement[] getRemboursements() {
        return remboursements;
    }

    /** 
     * Modifie les remboursements
     * @param remboursements 
     */
    public void setRemboursements(Remboursement[] remboursements) {
        this.remboursements = remboursements;
    }

    /**
     * Override de la méthode to string pour l'objet
     * @return String l'objet
     */
    @Override
    public String toString() {
        return "Dossier{" + "numeroClient=" + numeroClient + ", contrat=" 
                + contrat + ", mois=" + mois + ",\nreclamations=" 
                + Arrays.toString(reclamations) + ",\nremboursements=" 
                + Arrays.toString(remboursements) + '}';
    }
    
    /**
     * Valide si le dossier
     * @return True si valide
     *         False sinon
     */
    public boolean estValide(){
        if(!this.estValideNumeroClient() || !this.estValideContrat())
            return false;
  
        for (int i = 0; i < this.reclamations.length; i++) { 
            if(!reclamations[i].estValide(this.mois))
                return false;
        }
        
        return true;
    }
    
    /**
     * Valide le numéro du client
     * @return True si valide
     *         False sinon
     */
    private boolean estValideNumeroClient(){
        return this.numeroClient.matches("[0-9]{6}");
    }
    
    /**
     * Valide le contrat
     * @return True si valide
     *         False sinon
     */
    private boolean estValideContrat(){
        return this.contrat.matches("[A-D]");
    }
}
