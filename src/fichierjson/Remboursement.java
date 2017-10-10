/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fichierjson;

/**
 *
 * @author Leopold
 */
public class Remboursement {
    
    //Atrributs d'instance
    private int soin;
    private String date;
    private String montant;

    public Remboursement() {
    }

    public Remboursement(int soin, String date, String montant) {
        this.soin = soin;
        this.date = date;
        this.montant = montant;
    }

    public int getSoin() {
        return soin;
    }

    public void setSoin(int soin) {
        this.soin = soin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "Remboursement{" + "soin=" + soin + ", date=" + date + ", montant=" + montant + '}';
    }
    
    
}
