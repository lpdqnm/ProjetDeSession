/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetSession;

/**
 *
 * @author Leopold
 */
public class Statistiques {
    
    //CONSTANTES
    ////////////
    public static final String RECLAMATIONS_VALIDES = "Réclamations valides traitées";
    public static final String RECLAMATIONS_REJETEES = "Réclamations rejetées";
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
    public static final Integer [] SOINS_NO = {
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
    public static final String PRINT_FORMAT_ENTETE = "|%-25s|%13s|";
    public static final String PRINT_FORMAT = "|%-25s|%13d|";
    public static final String EN_TETE_1_TAB = "Catégorie de soins (No Soins)";
    public static final String EN_TETE_2_TAB = "Statistiques";
    
    //ATTRIBUTS STATIQUES
    /////////////////////
    private static int statReclamValides = 0;
    private static int statReclamRejetees = 0;
    private static int [] statsSoins = new int[SOINS_NO.length];

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
    
    private void majStatsSoins(Dossier dossier){
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
    
    private int soinDentaireNoMin(int soinNo){
        if (soinNo >= Soin.MIN_SOIN_DENTAIRE && soinNo <= Soin.MAX_SOIN_DENTAIRE) {
                return Soin.MIN_SOIN_DENTAIRE;
        }
        return soinNo;
    }
    
    private int obtIndexSoinNo(int soinNo){
        int i = 0;
        while (i < SOINS_NO.length) {
            if (SOINS_NO[i] == soinNo) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Statistiques{" + '}';
    }
    
    public static void afficherStats(){
        System.out.printf(PRINT_FORMAT_ENTETE, EN_TETE_1_TAB, EN_TETE_2_TAB);
    }
    
    
}
