
package ProjetSession;

/**
 *
 * @author jprioux
 */
public class Mensuelle {
    
    public int soin;
    public int max;
    public int depense;
    public Mensuelle osteo;
    public Mensuelle medecin;
    public Mensuelle psycho;
    public Mensuelle chiro;
    public Mensuelle physio;
    
    public Mensuelle(){
        this.osteo = new Mensuelle(Soin.OSTEOPATHIE,25000,0);
        this.medecin = new Mensuelle(Soin.MEDECIN_GENERALISTE_PRIVE,20000,0);
        this.psycho = new Mensuelle(Soin.PSYCHOLOGIE_INDIVIDUELLE,25000,0);
        this.chiro = new Mensuelle(Soin.CHIROPRATIE,15000,0);
        this.physio = new Mensuelle(Soin.PHYSIOTHERAPIE,30000,0);
    }
        
    public Mensuelle(int soin, int max, int depense){
        this.soin = soin;
        this.max = max;
        this.depense = depense;
    }
    
    public void additionerMontantPourSoin(int soin, int montantRemb) {        
        Mensuelle insSoin = getInstanceSoin(soin);        
        insSoin.depense += montantRemb;      
    }

    public boolean estMaxAtteintPourSoin(int soin) {
        Mensuelle insSoin = getInstanceSoin(soin);         
        return insSoin.depense >= insSoin.max;
    }
    
    public int getMaxPourSoin(int soin, int montantRemb){
        Mensuelle insSoin = getInstanceSoin(soin); 
           
        if(estMaxAtteintPourSoin(soin)){
            return 0;
        }else if(montantRemb <= (insSoin.max-insSoin.depense)){
            return montantRemb;
        }
        else{
             return (insSoin.max-insSoin.depense);
        }
    }
    
    public Mensuelle getInstanceSoin(int soin){        
        if(soin == Soin.OSTEOPATHIE){
            return this.osteo;
        }else if(soin == Soin.MEDECIN_GENERALISTE_PRIVE){
            return this.medecin;
        }else if(soin == Soin.PSYCHOLOGIE_INDIVIDUELLE){
            return this.psycho;
        }else if(soin == Soin.CHIROPRATIE){
            return this.chiro;
        }else if(soin == Soin.PHYSIOTHERAPIE){
            return this.physio;            
        }         
        return null;
    }
}
