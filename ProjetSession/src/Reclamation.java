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

    /**
     * Constructeur pour reclamation
     * @param soin
     * @param date
     * @param montant 
     */
    public Reclamation(String soin, String date, String montant) {
        this.soin = soin;
        this.date = date;
        this.montant = montant;
    }
    
    /**
     * Obtenir le soin
     * @return String soin
     */
    public String getSoin() {
        return soin;
    }

    /**
     * Obtenir la date
     * @return String date
     */
    public String getDate() {
        return date;
    }

    /**
     * Obtenir le montant
     * @return String montant
     */
    public String getMontant() {
        return montant;
    }

    /**
     * Override de la méthode to string pour l'objet
     * @return String l'objet
     */
    @Override
    public String toString() {
        return "Reclamation{" + "soin=" + soin + ", date=" + date + ", montant=" + montant + '}';
    }
    
    /**
     * Valide la reclamation
     * @param DateTraitement La date du traitement
     * @return True si valide
     *         False sinon
     */
    public boolean estValide(String DateTraitement){
        return estValideSoin() && estValideMontant() 
                && estValideDate(DateTraitement);
    }
    
    /**
     * Valide le soin
     * @return True si valide
     *         Faux sinon
     */
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
                iSoin == Soin.PSYCHOLOGIE_INDIVIDUELLE;
    }
    
    /**
     * Valide le montant
     * @return True si valide
     *         False sinon
     */
    private boolean estValideMontant(){
        return this.montant.matches("[0-9]+([,.][0-9]{1,2})?[\\\\$]");
    }
    
    /**
     * Valide le format de date et la date de la demande 
     * vs la date de reclamation. Les 2 dates doivent être dans le même mois.
     * @param DateDemande La date de la demande
     * @return True si valide
     *         False sinon
     */
    private boolean estValideDate(String DateDemande){       
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        
        try {
            sdf.parse(this.date);
            return this.date.substring(0,7).equals(
                    DateDemande.substring(0,7));
        } catch (ParseException e) {
                return false;
        }

    }
}
