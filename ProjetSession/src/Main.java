import java.io.IOException;
/**
* Classe principal de l'application
* 
* @author Leopold Quenum
* @author JP Rioux
* @version 1.0
* @since   2017-02-27 
*/
public class Main {
    
    public static void main(String[] args) {
        
        Fichier fichierJSON = new Fichier();
        Dossier dossier;
        Remboursement [] remboursements;
        Reclamation [] reclamations;
        Reclamation item;
        
        try {
            dossier = fichierJSON.lire(args[0]);
            //dossier = fichierJSON.lire("inputFile.json");
            
            if (dossier != null && dossier.estValide()) {
                reclamations = dossier.getReclamations();
                remboursements = new Remboursement[reclamations.length];

                for (int i = 0; i < remboursements.length; i++) {
                    item = reclamations[i];
                    remboursements[i] = new Remboursement(dossier.getContrat(), 
                            item.getSoin(), item.getDate(), item.getMontant());
                }
                dossier.setRemboursements(remboursements);
                fichierJSON.ecrire(args[1], dossier);
                //fichierJSON.ecrire("outputFile.json", dossier);
            } else {
                fichierJSON.ecrireErreur(args[1]);
                //fichierJSON.ecrireErreur("outputFile.json");
            }
        } catch (IOException ex) {
            try {
                fichierJSON.ecrireErreur(args[1]);
                //fichierJSON.ecrireErreur("outputFile.json");
            } catch (IOException ex1) {
                System.out.println("Erreur avec le fichier de sortie : "
                        + ex.getLocalizedMessage());
            }
            System.exit(1);       
        }

    }
    
}
