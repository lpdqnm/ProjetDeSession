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
            + "\nVous devez avoir 3 paramètres."
            + "\n -p <FICHIERENTREE> <FICHIERSORTIE> (pour les traitements sans statistiques)"
            + "\n               OU"
            + "\nVous devez avoir 2 paramètres."
            + "\n<FICHIERENTREE> <FICHIERSORTIE>"
            + "\n               OU"
            + "\nVous devez avoir 1 paramètre."
            + "\n-S (pour afficher les statistiques) ou -SR (pour réinitialiser les statistiques)";
    public static final String MSG_ERR_STATS = "Erreur avec le paramètre."
            + "\nEntrer -S (pour afficher les statistiques) ou -SR (pour "
            + "réinitialiser les statistiques)";
    public static final String MSG_ERR_ARG_P = "Erreur avec le paramètre -p."
            + "\nVous devez avoir 3 paramètres."
            + "\n -p <FICHIERENTREE> <FICHIERSORTIE>";

    public static void main(String[] args) {
        FichierLecture.lireStats(FICHIER_STATS);

        try {
            switch(args.length) {
                case 1:
                    lesStatistiques(args[0]);
                    break;
                case 2:
                    traitementDossierStats(args);
                    break;
                case 3:
                    traitementDossierPrediction(args);
                    break;
                default:
                    System.out.println(MSG_ERR_AGRS);
                    System.exit(1);
                    break;
            }
        } catch (Exception ex) {
        }

        FichierEcriture.ecrireStats(FICHIER_STATS);
    }
    
    public static void traitementDossierStats(String[] args)  throws Exception {
        Dossier dossierTraite = traitementDossier(args[0], args[1], null);
        
        if (dossierTraite.estValide()) {
            Statistiques.majStatReclamValides();
            Statistiques.majStatsSoins(dossierTraite);
        } else {
            Statistiques.majStatReclamRejetees();
            FichierEcriture.ecrireStats(FICHIER_STATS);
            FichierEcriture.ecrireErreurInfosDossier(args[1], dossierTraite.getErreur());
        }
    }
     
    protected static void traitementDossierPrediction(String[] args) throws Exception{
        arg1IemeInvalide(args[0]);
        
        Dossier dossierTraite = traitementDossier(args[1],args[2], args[0]);
        
        if (!dossierTraite.estValide()) {
            FichierEcriture.ecrireStats(FICHIER_STATS);
            FichierEcriture.ecrireErreurInfosDossier(args[2], dossierTraite.getErreur());
        }
    }
    
    protected static Dossier traitementDossier(String ficEntree, String ficSortie, String mode) throws Exception{
        Dossier dossier = FichierLecture.lireInfosDossier(ficEntree, ficSortie, mode);
        
        if (!dossier.estValide()) {                        
            return dossier;
        }

        dossier.setRemboursements(obtTabRemb(dossier));
        dossier.setTotal();
        
        FichierEcriture.ecrireInfosRembDossier(ficSortie, dossier);
        return dossier;
    }

    protected static Remboursement[] obtTabRemb(Dossier dossier) throws Exception {
        Reclamation[] tabReclam = dossier.getReclamations();
        Remboursement[] tabRemb = new Remboursement[tabReclam.length];
        Mensuelle mens = new Mensuelle();

        for (int i = 0; i < tabRemb.length; i++) {
            tabRemb[i] = new Remboursement(dossier.getContrat(), tabReclam[i].getSoin(),
                    tabReclam[i].getDate(), tabReclam[i].getMontant(), mens);
        }

        return tabRemb;
    }

    protected static void lesStatistiques(String arg) {
        if (arg.equals("-S")) {
            Statistiques.afficherStats();
            FichierEcriture.ecrireStats(FICHIER_STATS);
        } else if (arg.equals("-SR")) {
            Statistiques.reinitStats();
            FichierEcriture.ecrireStats(FICHIER_STATS);
        } else {
            System.out.println(MSG_ERR_STATS);
        }
        System.exit(1);
    }
    
    protected static void arg1IemeInvalide(String arg) {
        if (!arg.equals("-p")) {
            System.out.println(MSG_ERR_ARG_P);
            System.exit(1);
        }
    }
}
