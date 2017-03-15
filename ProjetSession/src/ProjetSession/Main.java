package ProjetSession;

/**
 * Classe principal de l'application
 *
 * @author Leopold Quenum
 * @author JP Rioux
 */
public class Main {

    public static void main(String[] args) {
        String ficEntree;
        String ficSortie;
        Dossier dossier;

        try {
            estValideNbrArgs(args);
            ficEntree = args[0];
            ficSortie = args[1];
            dossier = Fichier.lire(ficEntree, ficSortie);

            if (!dossier.estValide()) {
                Fichier.ecrireErreur(ficSortie, dossier.getErreur());
                System.exit(1);
            }

            dossier.setRemboursements(obtTabRemb(dossier));
            dossier.setTotal();

            Fichier.ecrire(ficSortie, dossier);
        } catch (Exception ex) {
        }
    }

    public static void estValideNbrArgs(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Erreur avec le nombre de paramètres.");
            System.out.println("Vous devez avoir 2 paramètres.");
            System.out.println("<FICHIERENTREE> <FICHIERSORTIE>");
            System.exit(1);
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
}
