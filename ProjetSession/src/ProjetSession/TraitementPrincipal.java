package ProjetSession;

/**
 * Classe principal de l'application
 *
 * @author Leopold Quenum
 * @author JP Rioux
 */
public class TraitementPrincipal {

    public static final String FICHIER_STATS = "statistiques.json";
    public static final String MSG_ERR_AGRS = "Erreur avec le nombre de paramètres."
            + "\nVous devez avoir 2 paramètres."
            + "\n<FICHIERENTREE> <FICHIERSORTIE>"
            + "\nOU"
            + "\nVous devez avoir 1 paramètre."
            + "\n-S (pour afficher les statistiques) ou -SR (pour réinitialiser les statistiques)";
    public static final String MSG_ERR_STATS = "Erreur avec le paramètre."
            + "\nEntrer -S (pour afficher les statistiques) ou -SR (pour "
            + "réinitialiser les statistiques)";

    public static void main(String[] args) {
        Fichier.lireStats(FICHIER_STATS);

        try {
            estValideNbrArgs(args);

            estValideStatistiques(args);

            String ficEntree = args[0];
            String ficSortie = args[1];
            Dossier dossier = Fichier.lire(ficEntree, ficSortie);

            estValideDossier(dossier, ficSortie);

            dossier.setRemboursements(obtTabRemb(dossier));
            dossier.setTotal();

            Statistiques.majStatReclamValides(dossier);
            Statistiques.majStatsSoins(dossier);

            Fichier.ecrire(ficSortie, dossier);
        } catch (Exception ex) {
        }

        Fichier.ecrireStats(FICHIER_STATS);
    }

    public static void estValideNbrArgs(String[] args) throws Exception {
        if (args.length != 2 && args.length != 1) {
            System.out.println(MSG_ERR_AGRS);

            System.exit(1);
        }
    }

    public static void estValideDossier(Dossier dossier, String ficSortie) throws Exception {
        if (!dossier.estValide()) {
            Statistiques.majStatReclamRejetees(dossier);
            Fichier.ecrireStats(FICHIER_STATS);
            Fichier.ecrireErreur(ficSortie, dossier.getErreur());
        }
    }

    private static Remboursement[] obtTabRemb(Dossier dossier) throws Exception {
        Reclamation[] tabReclam = dossier.getReclamations();
        Remboursement[] tabRemb = new Remboursement[tabReclam.length];
        Mensuelle mens = new Mensuelle();

        for (int i = 0; i < tabRemb.length; i++) {
            tabRemb[i] = new Remboursement(dossier.getContrat(), tabReclam[i].getSoin(),
                    tabReclam[i].getDate(), tabReclam[i].getMontant(), mens);
        }

        return tabRemb;
    }

    private static void estValideStatistiques(String[] args) {
        if (args.length == 1) {
            lesStatistiques(args[0]);
            System.exit(1);
        }
    }

    private static void lesStatistiques(String arg) {
        if (arg.equals("-S")) {
            Statistiques.afficherStats();
        } else if (arg.equals("-SR")) {
            Statistiques.reinitStats();
            Fichier.ecrireStats(FICHIER_STATS);
        } else {
            System.out.println(MSG_ERR_STATS);
        }
    }
}
