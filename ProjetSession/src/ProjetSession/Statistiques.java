package ProjetSession;

/**
 * Classe permettant le calcul de statistiques
 *
 * @author Leopold Quenum
 * @author JP Rioux
 */
public class Statistiques {

    public static final String RECLAMATIONS_VALIDES = "Réclamations valides traitées";
    public static final String RECLAMATIONS_REJETEES = "Réclamations rejetées";
    public static final String SOINS = "Soins";
    public static final String MONTT_MAX_SOINS = "Montant max soins";
    public static final String MONTT_TOT_SOINS = "Montant total soins";

    public static final String[] SOINS_CATEGORIE = {
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
    public static final int[] SOINS_NO = {
        Soin.MASSOTHERAPIE,
        Soin.OSTEOPATHIE,
        Soin.KINESITHERAPIE,
        Soin.MEDECIN_GENERALISTE_PRIVE,
        Soin.PSYCHOLOGIE_INDIVIDUELLE,
        Soin.MIN_SOIN_DENTAIRE, //valeur symbolique pour les statsdes soins dentaires
        Soin.NATUROPATHIE_ACUPONCTURE,
        Soin.CHIROPRATIE,
        Soin.PHYSIOTHERAPIE,
        Soin.ORTHOPHONIE_ERGOTHERAPIE
    };

    public static final String PRINT_FORMAT_ENTETE_1 = "|%-31s|%7s|\n";
    public static final String PRINT_FORMAT_1 = "|%-31s|%7d|\n";
    public static final String PRINT_FORMAT_ENTETE_2 = "|%-31s|%7s|%16s|%14s|\n";
    public static final String PRINT_FORMAT_2 = "|%-31s|%7d|%14s$|%16.2f$|\n";
    public static final String ENTETE_1_TAB = "Réclamations";
    public static final String ENTETE_2_TAB = "Nombre";
    public static final String ENTETE_3_TAB = "Catégorie de soins (No Soins)";
    public static final String ENTETE_4_TAB = "Montant maximal";
    public static final String ENTETE_5_TAB = "Montant moyen";
    public static final String[] PRTHS = {" (", ")"};
    public static final String TRAIT_HORIZONTAL_1 = "-----------------------------"
            + "------------";
    public static final String TRAIT_HORIZONTAL_2 = "-----------------------------"
            + "----------------------------------------------";

    private static int nbrReclamValides = 0;
    private static int nbrReclamRejetees = 0;
    private static int[] tabNbrSoins = new int[SOINS_NO.length];
    private static int[] tbMaxMonttSoins = new int[SOINS_NO.length];
    private static int[] tbTotMonttSoins = new int[SOINS_NO.length];

    public static void initStats(int statReclamValidesInit, int statReclamRejeteesInit,
            int[] statsSoinsInit, int[] statsMxMttSoinsInit , int[] statsTtMttSoinsInit) {
        nbrReclamValides = statReclamValidesInit;
        nbrReclamRejetees = statReclamRejeteesInit;
        tabNbrSoins = statsSoinsInit;
        tbMaxMonttSoins = statsMxMttSoinsInit;
        tbTotMonttSoins = statsTtMttSoinsInit;
    }

    public static void majStatReclamRejetees() {
        nbrReclamRejetees += 1;
    }

    public static void majStatReclamValides() {
        nbrReclamValides += 1;
    }

    public static int getNbrReclamValides() {
        return nbrReclamValides;
    }

    public static int getNbrReclamRejetees() {
        return nbrReclamRejetees;
    }

    public static int[] getTabNbrSoins() {
        return tabNbrSoins;
    }

    public static int[] getTbMaxMonttSoins() {
        return tbMaxMonttSoins;
    }

    public static int[] getTbTotMonttSoins() {
        return tbTotMonttSoins;
    }

    public static void reinitStats() {
        nbrReclamValides = 0;
        nbrReclamRejetees = 0;
        tabNbrSoins = new int[SOINS_NO.length];
        tbMaxMonttSoins = new int[SOINS_NO.length];
        tbTotMonttSoins = new int[SOINS_NO.length];
        
        System.out.println("Les statisques ont été réinitialisées avec succès.");
    }

    protected static void majStatsSoins(Dossier dossier) {
        int soinNo;
        int idxNoSoin;
        int monttSoin;
        Remboursement[] remboursements = dossier.getRemboursements();

        for (int i = 0; i < remboursements.length; i++) {
            soinNo = soinDentaireNoMin(remboursements[i].formatSoin(remboursements[i].getSoin()));
            monttSoin = Dollar.StringVersInt(remboursements[i].getMontant());            
            idxNoSoin = obtIndexTbSoinNo(soinNo);

            if (idxNoSoin >= 0) {
                tabNbrSoins[idxNoSoin]++;
                
                if (monttSoin > tbMaxMonttSoins[idxNoSoin]) {
                    tbMaxMonttSoins[idxNoSoin] = monttSoin;
                }
                
                tbTotMonttSoins[idxNoSoin] += monttSoin;
            }
        }
    }

    protected static int soinDentaireNoMin(int soinNo) {
        //Permet de simulier un numéro de soin unique (MIN_SOIN_DENTAIRE) pour tous les soins
        //dentaires, afin de mettre à jour les statistiques des soins dentaires dans le tableau
        //statsSoins
        if (soinNo >= Soin.MIN_SOIN_DENTAIRE && soinNo <= Soin.MAX_SOIN_DENTAIRE) {
            return Soin.MIN_SOIN_DENTAIRE;
        }

        return soinNo;
    }

    protected static int obtIndexTbSoinNo(int soinNo) {
        int i = 0;

        while (i < SOINS_NO.length) {
            if (SOINS_NO[i] == soinNo) {
                return i;
            }
            i++;
        }

        return -1;
    }

    public static void afficherStats() {
        afficherEntete(ENTETE_1_TAB, ENTETE_2_TAB);
        System.out.printf(PRINT_FORMAT_1, RECLAMATIONS_VALIDES, nbrReclamValides);
        System.out.printf(PRINT_FORMAT_1, RECLAMATIONS_REJETEES, nbrReclamRejetees);

        afficherEntete(ENTETE_3_TAB, ENTETE_2_TAB, ENTETE_4_TAB, ENTETE_5_TAB);
        afficherStatsSoins();
    }

    protected static void afficherEntete(String chaine1, String chaine2) {
        System.out.println(TRAIT_HORIZONTAL_1);
        System.out.printf(PRINT_FORMAT_ENTETE_1, chaine1, chaine2);
        System.out.println(TRAIT_HORIZONTAL_1);
    }
    
    protected static void afficherEntete(String chaine1, String chaine2, String chaine3,
            String chaine4) {
        System.out.println(TRAIT_HORIZONTAL_2);
        System.out.printf(PRINT_FORMAT_ENTETE_2, chaine1, chaine2, chaine3, chaine4);
        System.out.println(TRAIT_HORIZONTAL_2);
    }

    protected static void afficherStatsSoins() {
        String typSoin, monttMxStr;        
        for (int i = 0; i < SOINS_CATEGORIE.length; i++) {
            Double monttMy = 0.0;
            typSoin = SOINS_CATEGORIE[i] + PRTHS[0] + obtIntervalSoinsDents(SOINS_NO[i]) + PRTHS[1];
            monttMxStr = Dollar.IntVersString(tbMaxMonttSoins[i]);
            
            if (tabNbrSoins[i] > 0) {
                monttMy = (tbTotMonttSoins[i] / 100.0)  / tabNbrSoins[i];
            }
            
            System.out.printf(PRINT_FORMAT_2, typSoin, tabNbrSoins[i], monttMxStr, monttMy);
        }
    }

    protected static String obtIntervalSoinsDents(int soinNoI) {
        //Met en String le No de soin et restore l'intervalle des soins dentaires pour l'affichage
        if (soinNoI == Soin.MIN_SOIN_DENTAIRE) {
            return Soin.MIN_SOIN_DENTAIRE + "..." + Soin.MAX_SOIN_DENTAIRE;
        } else {
            return "" + soinNoI;
        }
    }

}
