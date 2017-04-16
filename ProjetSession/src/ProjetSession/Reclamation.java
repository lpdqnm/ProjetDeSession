package ProjetSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Tout ce qui a rapport à la reclamation
 *
 * @author Leopold Quenum
 * @author JP Rioux
 */
public class Reclamation {

    private static final String DOLLARS = "$";
    private static final int MONTANT_MAXIMUM = 50000;
    private static final int MONTANT_MINIMUM = 0;
    private final String soin;
    private final String date;
    private final String montant;
    private String erreur;

    public Reclamation(String soin, String date, String montant) {
        this.soin = soin;
        this.date = date;
        this.montant = montant;
    }

    public boolean estValide(String DateTraitement) {
        return estValideSoin() && estValideFormatDate(DateTraitement)
                && estValideDateReclamVsDateDemande(DateTraitement) && estValideMontant() 
                && estValideMaxMontant() && estValideMinMontant();
    }

    private boolean estValideSoin() {
        this.erreur = "Le numéro de soin est invalide.";
        int iSoin;

        try {
            iSoin = Integer.parseInt(this.soin);
        } catch (NumberFormatException ex) {
            return false;
        }

        /**
         * Retourne vrai numéro du soin est dans les soins qu'on a de disponible
         */
        return iSoin == Soin.CHIROPRATIE || iSoin == Soin.MASSOTHERAPIE
                || (iSoin >= Soin.MIN_SOIN_DENTAIRE
                && iSoin <= Soin.MAX_SOIN_DENTAIRE)
                || iSoin == Soin.NATUROPATHIE_ACUPONCTURE
                || iSoin == Soin.ORTHOPHONIE_ERGOTHERAPIE
                || iSoin == Soin.OSTEOPATHIE
                || iSoin == Soin.PHYSIOTHERAPIE
                || iSoin == Soin.PSYCHOLOGIE_INDIVIDUELLE
                || iSoin == Soin.KINESITHERAPIE
                || iSoin == Soin.MEDECIN_GENERALISTE_PRIVE;
    }

    private boolean estValideMontant() {
        this.erreur = "Le montant de réclamation suivant : " + this.montant + " est invalide";
        /**
         * REGEX Chiffres entre 0 et 9 Suivi d'un point ou d'une virgule Suivi de un ou deux chiffre
         * entre 0 et 9 Se terminant par un signe de dollars $
         */
        return this.montant.matches("[0-9]+([,.][0-9]{1,2})?[\\\\$]");
    }

    private boolean estValideDateReclamVsDateDemande(String DateDemande) {
        this.erreur = "Le mois de réclamation suivant : " + DateDemande.substring(0, 7)
                + " ne correspond pas avec la date de réclamation suivante :  "
                + this.date;
        // L'année et le mois de la date de la demande est il égale à la date de reclamation.
        return this.date.substring(0, 7).equals(DateDemande.substring(0, 7));
    }

    private boolean estValideFormatDate(String DateDemande) {
        this.erreur = "Le mois de réclamation suivant : " + DateDemande + " est invalide";
        // Valide le format de date ISO 8601 (YYYY-MM-DD)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        try {
            sdf.parse(this.date);
        } catch (ParseException e) {
            this.erreur = "La date de réclamation suivante : " + this.date + " est invalide";
            return false;
        }

        return true;
    }
    
    private boolean estValideMaxMontant(){
        this.erreur = "Le montant de réclamation maximum est de " + 
                Dollar.IntVersString(MONTANT_MAXIMUM) + DOLLARS +". "
                + "Vous avez un montant de réclamation à " + this.montant +".";
        
        return Dollar.StringVersInt(this.montant) <= MONTANT_MAXIMUM;          
    }
    
    private boolean estValideMinMontant(){
        this.erreur = "Tous les montants de réclamation doivent être supérieur à " +
                Dollar.IntVersString(MONTANT_MINIMUM) + DOLLARS +". ";
        
        return Dollar.StringVersInt(this.montant) > MONTANT_MINIMUM;   
    }

    public String getSoin() {
        return soin;
    }

    public String getDate() {
        return date;
    }

    public String getMontant() {
        return montant;
    }

    public String getErreur() {
        return erreur;
    }

    public void setErreur(String erreur) {
        this.erreur = erreur;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "soin=" + soin + ", date=" + date + ", montant=" + montant + '}';
    }
}
