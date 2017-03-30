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
        if (!this.estValideContrat() || !this.estValideNumeroClient()) {
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
