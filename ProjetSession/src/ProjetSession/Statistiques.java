/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetSession;

/**
 *
 * @author Leopold Quenum
  * @author JP Rioux
*/
public class Statistiques {
    
    //CONSTANTES
    ////////////
    public static final String RECLAMATIONS_VALIDES = "Réclamations valides traitées";
    public static final String RECLAMATIONS_REJETEES = "Réclamations rejetées";
    public static final String SOINS = "Soins";

    public static final String [] SOINS_CATEGORIE = {
        "Massothérapie",
        "Ostéopathie",
        "Kinésithérapie",
        "Médecin généraliste privé",
        "Psychologie individuelle",
        "Soins dentaires",
        "Naturopathie, acuponcture",
        "Chiropratie",
        "Physiothérapie",
        "Orthophonie, ergothérapie"
    };
    //Dans le tableau SOINS_NO le numéro de soin est à la même postion que la 
    //catégorie de soin correspondant dans le tableau SOINS_CATEGORIE
    public static final int [] SOINS_NO = {
        Soin.MASSOTHERAPIE,
        Soin.OSTEOPATHIE,
        Soin.KINESITHERAPIE,
        Soin.MEDECIN_GENERALISTE_PRIVE,
        Soin.PSYCHOLOGIE_INDIVIDUELLE,
        Soin.MIN_SOIN_DENTAIRE,
        Soin.NATUROPATHIE_ACUPONCTURE,
        Soin.CHIROPRATIE,
        Soin.PHYSIOTHERAPIE,
        Soin.ORTHOPHONIE_ERGOTHERAPIE
    };
    public static final String PRINT_FORMAT_ENTETE = "|%-31s|%13s|\n";
    public static final String PRINT_FORMAT = "|%-31s|%13d|\n";
    public static final String EN_TETE_1_TAB = "Réclamations";
    public static final String EN_TETE_2_TAB = "Statistiques";
    public static final String EN_TETE_3_TAB = "Catégorie de soins (No Soins)";
    public static final String [] PARENTHESES = {" (", ")"};
    public static final String TRAIT_HORIZONTAL = "-----------------------------"
            + "------------------";
    
    //ATTRIBUTS STATIQUES
    /////////////////////
    private static int statReclamValides = 0;
    private static int statReclamRejetees = 0;
    private static int [] statsSoins = new int[SOINS_NO.length];

    public Statistiques() {
    }

    public Statistiques(Dossier dossier) {
        statReclamValides += dossier.getRemboursements().length;
        statReclamRejetees += dossier.getReclamations().length
                - statReclamValides;
        majStatsSoins(dossier);
    }

    public static void initStats(int statReclamValidesInit, int statReclamRejeteesInit,
            int[] statsSoinsInit){
        statReclamValides = statReclamValidesInit;
        statReclamRejetees = statReclamRejeteesInit;
        statsSoins = statsSoinsInit;
    }
    
    public static int getStatReclamValides() {
        return statReclamValides;
    }

    public static int getStatReclamRejetees() {
        return statReclamRejetees;
    }
    
    public static int[] getStatsSoins() {
        return statsSoins;
    }
    
    protected void majStatsSoins(Dossier dossier){
        int soinNo;
        int index;
        Reclamation [] reclamations = dossier.getReclamations();
        
        for (int i = 0; i < reclamations.length; i++) {
            soinNo = soinDentaireNoMin(Integer.parseInt(reclamations[i].getSoin()));
            index = obtIndexSoinNo(soinNo);
            if (index >= 0) {
                statsSoins[index]++;
            }
        }
    }
    
    protected int soinDentaireNoMin(int soinNo){
        if (soinNo >= Soin.MIN_SOIN_DENTAIRE && soinNo <= Soin.MAX_SOIN_DENTAIRE) {
                return Soin.MIN_SOIN_DENTAIRE;
        }
        return soinNo;
    }
    
    protected int obtIndexSoinNo(int soinNo){
        int i = 0;
        while (i < SOINS_NO.length) {
            if (SOINS_NO[i] == soinNo) {
                return i;
            }
            i++;
        }
        return -1;
    }
    
    public static void afficherStats(){
        afficherEntete(EN_TETE_1_TAB, EN_TETE_2_TAB);
        System.out.printf(PRINT_FORMAT, RECLAMATIONS_VALIDES, statReclamValides);
        System.out.printf(PRINT_FORMAT, RECLAMATIONS_REJETEES, statReclamRejetees);
        afficherEntete(EN_TETE_3_TAB, EN_TETE_2_TAB);

    }
    
    protected static void afficherEntete(String chaine1, String chaine2){
        System.out.println(TRAIT_HORIZONTAL);
        System.out.printf(PRINT_FORMAT_ENTETE, chaine1, chaine2);
        System.out.println(TRAIT_HORIZONTAL);
    }
    
    protected static void afficherStatsSoins(){
        for (int i = 0; i < SOINS_CATEGORIE.length; i++) {
            String string = SOINS_CATEGORIE[i];
            
            
        }
    }
    
}
