/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fichierjson;

import java.util.Arrays;



/**
 *
 * @author Leopold
 */
public class Dossier {
    
    private String numeroClient;
    private String contrat;
    private String mois;
    private Reclamation []reclamations;
    private Remboursement [] remboursements;

    public Dossier() {
    }

    public Dossier(String numeroClient, String contrat, String mois, 
            Reclamation[] reclamations, Remboursement[] remboursements) {
        this.numeroClient = numeroClient;
        this.contrat = contrat;
        this.mois = mois;
        this.reclamations = reclamations;
        this.remboursements = remboursements;
    }
    
    public String getNumeroClient() {
        return numeroClient;
    }

    public void setNumeroClient(String numeroClient) {
        this.numeroClient = numeroClient;
    }

    public String getContrat() {
        return contrat;
    }

    public void setContrat(String contrat) {
        this.contrat = contrat;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public Reclamation[] getReclamations() {
        return reclamations;
    }

    public void setReclamations(Reclamation[] reclamations) {
        this.reclamations = reclamations;
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
