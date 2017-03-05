package ProjetSession;

/**
 * Classe servant au developpement
 *
 * @author Leopold Quenum
 * @author JP Rioux
 */
public class MainDev {

    public static void main(String[] args) {
        String ficEntree;
        String ficSortie;
        Dossier dossier;

//        try {
//            estValideNbrArgs(args);
//            ficEntree = args[0];
//            ficSortie = args[1];
        //Test Fichier.lire()
        ficEntree = "inputFileDDC1.json";//test
        ficSortie = "outputFileDDC1.json";//test

        dossier = Fichier.lire(ficEntree, ficSortie);

        if (!dossier.estValide()) {
            Fichier.ecrireErreur(ficSortie, dossier.getErreur());
            System.exit(1);
        }

        System.out.println(dossier);//test

        try {
            //Test Fichier.ecrire()
            dossier.setRemboursements(obtTabRemb(dossier));
            dossier.setTotal();
        } catch (Exception ex) {
        }

        System.out.println(dossier);//test

        Fichier.ecrire(ficSortie, dossier);

//            if (dossier != null && dossier.estValide()) {
//                reclamations = dossier.getReclamations();
//                remboursements = new Remboursement[reclamations.length];
//
//                for (int i = 0; i < remboursements.length; i++) {
//                    item = reclamations[i];
//                    remboursements[i] = new Remboursement(dossier.getContrat(), 
//                            item.getSoin(), item.getDate(), item.getMontant());
//                }
//                dossier.setRemboursements(remboursements);
//                fichierJSON.ecrire(args[1], dossier);
//            } else {
//                fichierJSON.ecrireErreur(args[1]);
//            }
//        } catch (IOException ex) {
//            try {
//                fichierJSON.ecrireErreur(args[1]);
//            } catch (IOException ex1) {
//                System.out.println("Erreur avec le fichier de sortie : "
//                        + ex.getLocalizedMessage());
//            }
//            System.exit(1);       
//        }
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

        for (int i = 0; i < tabRemb.length; i++) {
            tabRemb[i] = new Remboursement(dossier.getContrat(), tabReclam[i]
                    .getSoin(), tabReclam[i].getDate(), tabReclam[i].getMontant());
        }

        return tabRemb;
    }
}
