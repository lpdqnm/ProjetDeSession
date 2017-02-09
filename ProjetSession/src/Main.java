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
            if(args.length != 2){
                System.out.println("Erreur avec le nombre de paramètres.");
                System.out.println("Vous devez avoir 2 paramètres.");
                System.out.println("<FICHIERENTREE> <FICHIERSORTIE>");
                System.exit(1); 
            }
            
            dossier = fichierJSON.lire(args[0]);

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
            } else {
                fichierJSON.ecrireErreur(args[1]);
            }
        } catch (IOException ex) {
            try {
                fichierJSON.ecrireErreur(args[1]);
            } catch (IOException ex1) {
                System.out.println("Erreur avec le fichier de sortie : "
                        + ex.getLocalizedMessage());
            }
            System.exit(1);       
        }

    }
    
}
