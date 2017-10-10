/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fichierjson;

import java.io.IOException;
/**
 *
 * @author Leopold
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
            
            if (dossier != null) {
                reclamations = dossier.getReclamations();
                remboursements = new Remboursement[reclamations.length];

                for (int i = 0; i < remboursements.length; i++) {
                    item = reclamations[i];
                    remboursements[i] = new Remboursement(item.getSoin(), 
                            item.getDate(), item.getMontant());
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
