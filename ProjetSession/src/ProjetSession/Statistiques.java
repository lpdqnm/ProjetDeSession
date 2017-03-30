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
    public static final String[] PARENTHESES = {" (", ")"};
    public static final String TRAIT_HORIZONTAL = "-----------------------------"
            + "------------------";

    private static int statReclamValides = 0;
    private static int statReclamRejetees = 0;
    private static int[] statsSoins = new int[SOINS_NO.length];

    public static void initStats(int statReclamValidesInit, int statReclamRejeteesInit,
            int[] statsSoinsInit) {
        statReclamValides = statReclamValidesInit;
        statReclamRejetees = statReclamRejeteesInit;
        statsSoins = statsSoinsInit;
    }

    public static void majStatReclamRejetees(Dossier dossier) {
        statReclamRejetees += dossier.getReclamations().length;
    }

    public static void majStatReclamValides(Dossier dossier) {
        statReclamValides += dossier.getReclamations().length;
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

    public static void reinitStats() {
        statReclamValides = 0;
        statReclamRejetees = 0;
        statsSoins = new int[SOINS_NO.length];
        System.out.println("Les statisques ont été réinitialisées avec succès.");
    }

    protected static void majStatsSoins(Dossier dossier) {
        int soinNo;
        Reclamation[] reclamations = dossier.getReclamations();

        for (int i = 0; i < reclamations.length; i++) {
            soinNo = soinDentaireNoMin(Integer.parseInt(reclamations[i].getSoin()));

            if (obtIndexSoinNo(soinNo) >= 0) {
                statsSoins[obtIndexSoinNo(soinNo)]++;
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

    protected static int obtIndexSoinNo(int soinNo) {
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
        afficherEntete(EN_TETE_1_TAB, EN_TETE_2_TAB);
        System.out.printf(PRINT_FORMAT, RECLAMATIONS_VALIDES, statReclamValides);
        System.out.printf(PRINT_FORMAT, RECLAMATIONS_REJETEES, statReclamRejetees);

        afficherEntete(EN_TETE_3_TAB, EN_TETE_2_TAB);
        afficherStatsSoins();
    }

    protected static void afficherEntete(String chaine1, String chaine2) {
        System.out.println(TRAIT_HORIZONTAL);
        System.out.printf(PRINT_FORMAT_ENTETE, chaine1, chaine2);
        System.out.println(TRAIT_HORIZONTAL);
    }

    protected static void afficherStatsSoins() {
        for (int i = 0; i < SOINS_CATEGORIE.length; i++) {
            System.out.printf(PRINT_FORMAT, SOINS_CATEGORIE[i] + PARENTHESES[0]
                    + obtIntervalleSoinsDentaires(SOINS_NO[i]) + PARENTHESES[1], statsSoins[i]);
        }
    }

    protected static String obtIntervalleSoinsDentaires(int soinNoI) {
        //Met en String le No de soin et restore l'intervalle des soins dentaires pour l'affichage
        if (soinNoI == Soin.MIN_SOIN_DENTAIRE) {
            return Soin.MIN_SOIN_DENTAIRE + "..." + Soin.MAX_SOIN_DENTAIRE;
        } else {
            return "" + soinNoI;
        }
    }

}
