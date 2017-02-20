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

    public Remboursement(String contrat, String soin, String date, String montant) {
        this.contrat = contrat;
        this.soin = soin;
        this.date = date;
        setMontant(formatSoin(soin), formatMontant(montant));
    }
    
    /**
     * Prend en paramètre un string contenant ou non le signe de dollars "$"
     * et contenant un point ou une virgule comme séparateur de décimal
     * @param montant
     * @return double montant
     */
    private double formatMontant (String montant){
        double montantReclame;
        
        try{
           montantReclame = Double.parseDouble(montant.replace("$","").replace(",","."));
        }catch(NumberFormatException ex){
            montantReclame = 0.00;
        }
        return montantReclame;
    }
    
    private int formatSoin (String soin){        
        try{
            return Integer.parseInt(this.soin);
        }catch(NumberFormatException ex){            
            return -1;
        }
    }

    private void setMontant(int soin, double montant) {   
        if(soin == Soin.MASSOTHERAPIE){
            this.montant = String.format( "%.2f", this.getMontantMasso(this.contrat, montant));       
        }else if(soin == Soin.OSTEOPATHIE){
            this.montant = String.format( "%.2f",this.getMontantOsteo(this.contrat, montant));
        }else if(soin == Soin.KINESITHERAPIE){
            this.montant = String.format( "%.2f",this.getMontantKine(this.contrat, montant));
        }else if(soin == Soin.MEDECIN_GENERALISTE_PRIVE){
            this.montant = String.format( "%.2f",this.getMontantMedGen(this.contrat, montant));            
        }else if(soin >= Soin.MIN_SOIN_DENTAIRE && soin <= Soin.MAX_SOIN_DENTAIRE){
            this.montant = String.format( "%.2f",this.getMontantDentaire(this.contrat, montant));           
        }else if(soin == Soin.PSYCHOLOGIE_INDIVIDUELLE){
            this.montant = String.format( "%.2f",this.getMontantPsycho(this.contrat, montant));           
        }else if(soin == Soin.NATUROPATHIE_ACUPONCTURE){
            this.montant = String.format( "%.2f",this.getMontantNaturo(this.contrat, montant));          
        }else if(soin == Soin.CHIROPRATIE){
            this.montant = String.format( "%.2f",this.getMontantChiro(this.contrat, montant));          
        }else if(soin == Soin.PHYSIOTHERAPIE){
            this.montant = String.format( "%.2f",this.getMontantPhysio(this.contrat, montant));           
        }else if(soin == Soin.ORTHOPHONIE_ERGOTHERAPIE){
            this.montant = String.format( "%.2f",this.getMontantOrtho(this.contrat, montant));              
        }else{
            this.montant = String.valueOf(montant);
        }     
    }

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
        }else if(contrat.equals(Contrat.E)){
            montantRemb = montant * 0.15;
        }         
        return montantRemb;
    }
    
    private double getMontantOsteo(String contrat, double montant){
        double montantRemb = 0;
        
        if(contrat.equals(Contrat.A)){
            montantRemb = montant * 0.35;
        }else if(contrat.equals(Contrat.B)){
            montantRemb = (montant * 0.5) < 50 ? (montant * 0.5) : 50;
        }else if(contrat.equals(Contrat.C)){
            montantRemb = montant * 0.95;
        }else if(contrat.equals(Contrat.D)){
            montantRemb = montant < 75 ? montant : 75;
        }else if(contrat.equals(Contrat.E)){
            montantRemb = montant * 0.25;
        }         
        return montantRemb;
    }
    
    private double getMontantPsycho(String contrat, double montant){
        double montantRemb = 0;
        
        if(contrat.equals(Contrat.A)){
            montantRemb = montant * 0.25;
        }else if(contrat.equals(Contrat.B)){
            montantRemb = montant;
        }else if(contrat.equals(Contrat.C)){
            montantRemb = montant * 0.9;
        }else if(contrat.equals(Contrat.D)){
            montantRemb = montant < 100 ? montant : 100;
        }else if(contrat.equals(Contrat.E)){
            montantRemb = montant * 0.12;
        }         
        return montantRemb;
    }
      
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
        }else if(contrat.equals(Contrat.E)){
            montantRemb = montant * 0.6;
        }         
        return montantRemb;
    }
    
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
        }else if(contrat.equals(Contrat.E)){    
            montantRemb = (montant * 0.25) < 15 ? (montant * 0.25) : 15;
        }         
        return montantRemb;
    }
    
    private double getMontantChiro(String contrat, double montant){
        double montantRemb = 0;
        
        if(contrat.equals(Contrat.A)){
            montantRemb = montant * 0.25;
        }else if(contrat.equals(Contrat.B)){
            montantRemb = (montant * 0.5) < 50 ? (montant * 0.5) : 50;
        }else if(contrat.equals(Contrat.C)){
            montantRemb = montant * 0.9;
        }else if(contrat.equals(Contrat.D)){
            montantRemb = montant;
        }else if(contrat.equals(Contrat.E)){
            montantRemb = (montant * 0.30) < 20 ? (montant * 0.30) : 20;
        }         
        return montantRemb;
    }

    private double getMontantPhysio(String contrat, double montant){
        double montantRemb = 0;
        
        if(contrat.equals(Contrat.A)){
            montantRemb = montant * 0.40;
        }else if(contrat.equals(Contrat.B)){
            montantRemb = montant;
        }else if(contrat.equals(Contrat.C)){
            montantRemb = montant * 0.75;
        }else if(contrat.equals(Contrat.D)){
            montantRemb = montant < 100 ? montant : 100;
        }else if(contrat.equals(Contrat.E)){
            montantRemb = montant * 0.15;
        }         
        return montantRemb;
    }
    
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
        }else if(contrat.equals(Contrat.E)){
            montantRemb = montant * 0.22;
        }         
        return montantRemb;
    }
    
    private double getMontantKine(String contrat, double montant){
        double montantRemb = 0;
        
        if(contrat.equals(Contrat.A) || contrat.equals(Contrat.B)){
            montantRemb = 0;            
        }else if(contrat.equals(Contrat.C)){
            montantRemb = montant * 0.85;
        }else if(contrat.equals(Contrat.D)){
            montantRemb = montant < 150 ? montant : 150;
        }else if(contrat.equals(Contrat.E)){
            montantRemb = montant * 0.15;
        }         
        return montantRemb;
    }
    
    private double getMontantMedGen(String contrat, double montant){
        double montantRemb = 0;
        
        if(contrat.equals(Contrat.A)){
            montantRemb = montant * 0.5;
        }else if(contrat.equals(Contrat.B)){
            montantRemb = montant * 0.75;
        }else if(contrat.equals(Contrat.C)){
            montantRemb = montant * 0.90;
        }else if(contrat.equals(Contrat.D)){
            montantRemb = montant * 0.95;
        }else if(contrat.equals(Contrat.E)){
            montantRemb = (montant * 0.25) < 20 ? (montant * 0.25) : 20;
        }         
        return montantRemb;
    }
    
    @Override
    public String toString() {
        return "Remboursement{" + "soin=" + soin + ", date=" + date + ", montant=" + montant + '}';
    }
    
    public String getSoin() {        
        return soin;
    }

    public String getDate() {
        return date;
    }
    
    public String getMontant() {
        return montant + DOLLARS;
    }
}