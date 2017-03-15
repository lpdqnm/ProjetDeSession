package ProjetSession;

/**
 * Classe servant au traitement de monnaie pour éviter la perte de précision
 * 
 * @author Leopold Quenum
 * @author JP Rioux
 */
public final class Dollar {
 
    /**
     * Prend en paramètre un string contenant ou non le signe de dollars "$" et contenant un point
     * ou une virgule comme séparateur de décimal et retourne un montant entier en nombre de cents
     *
     * @param montant
     * @return int montant
     */
    public static int StringVersInt(String montant) {
        double montantReclame;
        int montantFormat;
            
        try {
            if(montant == null)
                montant = "0";
            montantReclame = Double.parseDouble(montant.replace("$", "").replace(",", "."));
        } catch (NumberFormatException ex) {
            montantReclame = 0.00;
        }
        
        montantFormat = (int) (montantReclame * 100);
        
        return montantFormat;
    }
    
    /**
     * Prend un entier (en nombre de cents) et le convertie en String en format monnaie
     *  ex: Entre 550 --> sort 5.50
     * @param montant
     * @return String montant formaté
     */
    public static String IntVersString(int montant){
        if (montant < 0)
            montant = 0;
                
        String cents = String.valueOf(montant % 100);
        
        if(cents.length() < 2){
            cents = cents + "0";
        }
               
        return (montant / 100) + "." + cents;
    }
    
    /**
     * Multiple le montant par son multiplicateur
     * @param montant
     * @param multiplicateur
     * @return Montant (en nombre de cents)
     */
    public static int multiplication(int montant, double multiplicateur){  
        int dollar = 0;
        int cents = 0;
        
        if (montant < 0)
            montant = 0;
                
        dollar = (int) ((montant * multiplicateur) / 100);
        cents = (int) Math.round((montant * multiplicateur) % 100);
                
        return (dollar * 100) + cents;        
    }
}
