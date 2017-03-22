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
        
        Fichier.lireStats("statistiques.json");
        
        try {
            estValideNbrArgs(args);
            
            lesStatistiques(args);
            
            ficEntree = args[0];
            ficSortie = args[1];
            dossier = Fichier.lire(ficEntree, ficSortie);

            estValideDossier(dossier, ficSortie);
            
            dossier.setRemboursements(obtTabRemb(dossier));
            dossier.setTotal();
            
            Statistiques.majStatReclamValides(dossier);
            Statistiques.majStatsSoins(dossier);

            Fichier.ecrire(ficSortie, dossier);
        } catch (Exception ex) {
        }
        
        Fichier.ecrireStats("statistiques.json");

    }

    public static void estValideNbrArgs(String[] args) throws Exception {
        if (args.length != 2 && args.length != 1) {
            System.out.println("Erreur avec le nombre de paramètres.");
            System.out.println("Vous devez avoir 2 paramètres.");
            System.out.println("<FICHIERENTREE> <FICHIERSORTIE>");
            System.out.println("OU");
            System.out.println("Vous devez avoir 1 paramètre.");
            System.out.println("-S (pour afficher les statistiques) ou -SR"
                    + " (pour réinitialiser les statistiques)");
            System.exit(1);
        }
    }
    
    public static void estValideDossier(Dossier dossier, String ficSortie) throws Exception {
            if (!dossier.estValide()) {
                Statistiques.majStatReclamRejetees(dossier);
                Statistiques.majStatsSoins(dossier);
                Fichier.ecrireStats("statistiques.json");
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
    
    private static void lesStatistiques(String [] args){
        String arg;
        if (args.length == 1) {
            arg = args[0];
            if (arg.equalsIgnoreCase("-S")) {
                Statistiques.afficherStats();
            } else if (arg.equalsIgnoreCase("-SR")) {
                Statistiques.reinitStats();
                Fichier.ecrireStats("statistiques.json");
            } else {
            System.out.println("Erreur avec le paramètre.");
            System.out.println("Entrer -S (pour afficher les statistiques) ou -SR"
                    + " (pour réinitialiser les statistiques)");
            }
            System.exit(1);
        }
    }
}
