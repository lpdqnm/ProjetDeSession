import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
* Tout ce qui a rapport à la reclamation
*
* @author Leopold Quenum
* @author JP Rioux
* @version 1.0
* @since   2017-02-27 
*/
public class Reclamation {
    
    private final String soin;
    private final String date;
    private final String montant;

    public Reclamation(String soin, String date, String montant) {
        this.soin = soin;
        this.date = date;
        this.montant = montant;
    }
    
    public boolean estValide(String DateTraitement){
        return estValideSoin() && estValideMontant() && estValideFormatDate(DateTraitement) 
                && estValideDateReclamVsDateDemande(DateTraitement);
    }
    
    private boolean estValideSoin(){
        int iSoin;
        
        try{
            iSoin = Integer.parseInt(this.soin);
        }catch(NumberFormatException ex){
            return false;
        }
        
        return iSoin == Soin.CHIROPRATIE || iSoin == Soin.MASSOTHERAPIE || 
                (iSoin >= Soin.MIN_SOIN_DENTAIRE 
                && iSoin <= Soin.MAX_SOIN_DENTAIRE) || 
                iSoin == Soin.NATUROPATHIE_ACUPONCTURE ||
                iSoin == Soin.ORTHOPHONIE_ERGOTHERAPIE ||
                iSoin == Soin.OSTEOPATHIE ||
                iSoin == Soin.PHYSIOTHERAPIE ||
                iSoin == Soin.PSYCHOLOGIE_INDIVIDUELLE ||
                iSoin == Soin.KINESITHERAPIE ||
                iSoin == Soin.MEDECIN_GENERALISTE_PRIVE;
    }
    
    private boolean estValideMontant(){
        /**
         * REGEX
         * Chiffres entre 0 et 9
         * Suivi d'un point ou d'une virgule
         * Suivi de un ou deux chiffre entre 0 et 9
         * Se terminant par un signe de dollars $
         */
        return this.montant.matches("[0-9]+([,.][0-9]{1,2})?[\\\\$]");
    }
    
    /**
     * Valide l'année et le mois de date de la demande vs la date de reclamation.
     * @param DateDemande La date de la demande
     * @return True si valide
     *         False sinon
     */
    private boolean estValideDateReclamVsDateDemande(String DateDemande){       
        return this.date.substring(0,7).equals(DateDemande.substring(0,7));
    }
    
    /**
     * Valide le format de date ISO 8601 (YYYY-MM-DD)
     * @param DateDemande
     * @return 
     */
    private boolean estValideFormatDate(String DateDemande){       
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        
        try {
            sdf.parse(this.date);
        } catch (ParseException e) {
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

    @Override
    public String toString() {
        return "Reclamation{" + "soin=" + soin + ", date=" + date + ", montant=" + montant + '}';
    }
}
