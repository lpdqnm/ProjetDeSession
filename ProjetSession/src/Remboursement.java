/**
* Tout ce qui a rapport au remboursement
*
* @author Leopold Quenum
* @author JP Rioux
* @version 1.0
* @since   2017-02-27 
*/
public class Remboursement {
    
    private static final String DOLLARS = "$";
    private String contrat;    
    private String soin;
    private String date;
    private String montant;

    /**
     * Constructeur du remboursement
     * @param contrat
     * @param soin
     * @param date
     * @param montant 
     */
    public Remboursement(String contrat, String soin, String date, String montant) {
        this.contrat = contrat;
        this.soin = soin;
        this.date = date;
        setMontant(soin, montant);
    }

    /**
     * Obtenir soin
     * @return String soin 
     */
    public String getSoin() {        
        return soin;
    }

    /**
     * Obtenir date
     * @return String date
     */
    public String getDate() {
        return date;
    }
    
    /**
     * Obtenir montant
     * @return String montant
     */
    public String getMontant() {
        return montant + DOLLARS;
    }
    
    /**
     * Modifie le montant
     * @param soin
     * @param montant 
     */
    private void setMontant(String soin, String montant) {        
        double montantReclame;
        int iSoin;
        
        try{
           montantReclame = Double.parseDouble(montant.replace("$",""));
        }catch(NumberFormatException ex){
            montantReclame = 0.00;
        }
        
        try{
            iSoin = Integer.parseInt(this.soin);
        }catch(NumberFormatException ex){
            this.montant = montant;
            return;
        }
        
        if(iSoin == Soin.MASSOTHERAPIE){
            this.montant = String.format( "%.2f", 
                    this.getMontantMasso(this.contrat, montantReclame));       
        }else if(iSoin == Soin.OSTEOPATHIE){
            this.montant = String.format( "%.2f",
                    this.getMontantOsteo(this.contrat, montantReclame));
        }else if(iSoin >= Soin.MIN_SOIN_DENTAIRE &&
                iSoin <= Soin.MAX_SOIN_DENTAIRE){
            this.montant = String.format( "%.2f",
                    this.getMontantDentaire(this.contrat, montantReclame));           
        }else if(iSoin == Soin.PSYCHOLOGIE_INDIVIDUELLE){
            this.montant = String.format( "%.2f",
                    this.getMontantPsycho(this.contrat, montantReclame));           
        }else if(iSoin == Soin.NATUROPATHIE_ACUPONCTURE){
            this.montant = String.format( "%.2f",
                    this.getMontantNaturo(this.contrat, montantReclame));          
        }else if(iSoin == Soin.CHIROPRATIE){
            this.montant = String.format( "%.2f",
                    this.getMontantChiro(this.contrat, montantReclame));          
        }else if(iSoin == Soin.PHYSIOTHERAPIE){
            this.montant = String.format( "%.2f",
                    this.getMontantPhysio(this.contrat, montantReclame));           
        }else if(iSoin == Soin.ORTHOPHONIE_ERGOTHERAPIE){
            this.montant = String.format( "%.2f",
                    this.getMontantOrtho(this.contrat, montantReclame));                     
        }else{
            this.montant = montant;
        }     
    }

    /**
     * Override de la méthode to string pour l'objet
     * @return String l'objet
     */
    @Override
    public String toString() {
        return "Remboursement{" + "soin=" + soin + ", date=" + date + ", montant=" + montant + '}';
    }
    
    /**
     * Obtenir le montant remboursé pour Massothérapie
     * @param contrat
     * @param montant
     * @return double montant remboursé
     */
    private double getMontantMasso(String contrat, double montant){
        double montantRemb = 0;
        
        if(contrat.equals(Contrat.A)){
            montantRemb = montant * 0.25;
        }else if(contrat.equals(Contrat.B)){
            montantRemb = (montant * 0.5) < 40 ? (montant * 0.5) : 40;
        }else if(contrat.equals(Contrat.C)){
            montantRemb = montant * 0.9;
        }else if(contrat.equals(Contrat.D)){
            montantRemb = montant < 85 ? montant : 85;
        }         
        return montantRemb;
    }
    
    /**
     * Obtenir le montant remboursé pour Ostéopathie
     * @param contrat
     * @param montant
     * @return double montant remboursé
     */
    private double getMontantOsteo(String contrat, double montant){
        double montantRemb = 0;
        
        if(contrat.equals(Contrat.A)){
            montantRemb = montant * 0.25;
        }else if(contrat.equals(Contrat.B)){
            montantRemb = (montant * 0.5) < 50 ? (montant * 0.5) : 50;
        }else if(contrat.equals(Contrat.C)){
            montantRemb = montant * 0.9;
        }else if(contrat.equals(Contrat.D)){
            montantRemb = montant < 75 ? montant : 75;
        }         
        return montantRemb;
    }
    
    /**
     * Obtenir le montant remboursé pour Psychologie
     * @param contrat
     * @param montant
     * @return double montant remboursé
     */
    private double getMontantPsycho(String contrat, double montant){
        double montantRemb = 0;
        
        if(contrat.equals(Contrat.A)){
            montantRemb = montant * 0.25;
        }else if(contrat.equals(Contrat.B)){
            montantRemb = montant < 70 ? montant : 70;
        }else if(contrat.equals(Contrat.C)){
            montantRemb = montant * 0.9;
        }else if(contrat.equals(Contrat.D)){
            montantRemb = montant < 100 ? montant : 100;
        }         
        return montantRemb;
    }
    
    /**
     * Obtenir le montant remboursé pour Dentaire
     * @param contrat
     * @param montant
     * @return double montant remboursé
     */    
    private double getMontantDentaire(String contrat, double montant){
        double montantRemb = 0;
        
        if(contrat.equals(Contrat.A)){
            montantRemb = montant * 0;
        }else if(contrat.equals(Contrat.B)){
            montantRemb = montant * 0.5;
        }else if(contrat.equals(Contrat.C)){
            montantRemb = montant * 0.9;
        }else if(contrat.equals(Contrat.D)){
            montantRemb = montant;
        }         
        return montantRemb;
    }
    
    /**
     * Obtenir le montant remboursé pour Naturopathie/Acuponcture
     * @param contrat
     * @param montant
     * @return double montant remboursé
     */
    private double getMontantNaturo(String contrat, double montant){
        double montantRemb = 0;
        
        if(contrat.equals(Contrat.A)){
            montantRemb = montant * 0;
        }else if(contrat.equals(Contrat.B)){
            montantRemb = montant * 0;
        }else if(contrat.equals(Contrat.C)){
            montantRemb = montant * 0.9;
        }else if(contrat.equals(Contrat.D)){
            montantRemb = montant < 65 ? montant : 65;
        }         
        return montantRemb;
    }
    
    /**
     * Obtenir le montant remboursé pour Chiropratie
     * @param contrat
     * @param montant
     * @return double montant remboursé
     */
    private double getMontantChiro(String contrat, double montant){
        double montantRemb = 0;
        
        if(contrat.equals(Contrat.A)){
            montantRemb = montant * 0.25;
        }else if(contrat.equals(Contrat.B)){
            montantRemb = (montant * 0.5) < 50 ? (montant * 0.5) : 50;
        }else if(contrat.equals(Contrat.C)){
            montantRemb = montant * 0.9;
        }else if(contrat.equals(Contrat.D)){
            montantRemb = montant < 75 ? montant : 75;
        }         
        return montantRemb;
    }
    
    /**
     * Obtenir le montant remboursé pour Physiotérapie
     * @param contrat
     * @param montant
     * @return double montant remboursé
     */
    private double getMontantPhysio(String contrat, double montant){
        double montantRemb = 0;
        
        if(contrat.equals(Contrat.A)){
            montantRemb = montant * 0.40;
        }else if(contrat.equals(Contrat.B)){
            montantRemb = montant;
        }else if(contrat.equals(Contrat.C)){
            montantRemb = montant * 0.9;
        }else if(contrat.equals(Contrat.D)){
            montantRemb = montant < 100 ? montant : 100;
        }         
        return montantRemb;
    }
    
    /**
     * Obtenir le montant remboursé pour Orthophonie/Ergothérapie
     * @param contrat
     * @param montant
     * @return double montant remboursé
     */
    private double getMontantOrtho(String contrat, double montant){
        double montantRemb = 0;
        
        if(contrat.equals(Contrat.A)){
            montantRemb = montant * 0;
        }else if(contrat.equals(Contrat.B)){
            montantRemb = montant * 0.7;
        }else if(contrat.equals(Contrat.C)){
            montantRemb = montant * 0.9;
        }else if(contrat.equals(Contrat.D)){
            montantRemb = montant < 90 ? montant : 90;
        }         
        return montantRemb;
    }
}