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
                && estValideDateReclamVsDateDemande(DateTraitement) && estValideMontant();
    }

    private boolean estValideSoin() {
        this.erreur = "Le numéro de soin est invalide.";
        int iSoin;

        try {
            iSoin = Integer.parseInt(this.soin);
        } catch (NumberFormatException ex) {
            return false;
        }

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

    /**
     * Valide l'année et le mois de date de la demande vs la date de reclamation.
     *
     * @param DateDemande La date de la demande
     * @return True si valide False sinon
     */
    private boolean estValideDateReclamVsDateDemande(String DateDemande) {
        this.erreur = "Le mois de réclamation suivant : " + DateDemande.substring(0, 7)
                + " ne correspond pas avec la date de réclamation suivante :  "
                + this.date;
        return this.date.substring(0, 7).equals(DateDemande.substring(0, 7));
    }

    /**
     * Valide le format de date ISO 8601 (YYYY-MM-DD)
     *
     * @param DateDemande
     * @return
     */
    private boolean estValideFormatDate(String DateDemande) {
        this.erreur = "Le mois de réclamation suivant : " + DateDemande +" est invalide";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        try {
            sdf.parse(this.date);
        } catch (ParseException e) {
            this.erreur = "La date de réclamation suivante : " + this.date +" est invalide";
            return false;
        }

        return true;
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
