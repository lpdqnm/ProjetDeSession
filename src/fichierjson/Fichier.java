/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fichierjson;

import java.io.IOException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 *
 * @author Leopold
 */
public class Fichier {
    
    //Methodes d'instance
    public Dossier lire(String nomFichierEntrer) throws IOException {
        Dossier dossier = null;
        String client;
        String contrat;
        String mois ;
        JSONArray tabObjJson;
        JSONObject item;
        int lgTab;
         Reclamation [] reclamations;
        int soin;
         String date;
         String montant;
        String textJson = Utf8File.loadFileIntoString(nomFichierEntrer);
        JSONObject racine = (JSONObject) JSONSerializer.toJSON(textJson);
        
        if (racine != null) {
            client = racine.getString("client");
            contrat = racine.getString("contrat");
            mois = racine.getString("mois");
            tabObjJson = racine.getJSONArray("reclamations");
            lgTab = tabObjJson.size();
            reclamations = new Reclamation[lgTab];

            for (int i = 0; i < lgTab; i++) {
                item = tabObjJson.getJSONObject(i);
                soin = item.getInt("soin");
                date = item.getString("date");
                montant = item.getString("montant");
                reclamations[i] = new Reclamation(soin, date, montant);
            }
            dossier = new Dossier(client, contrat, mois, reclamations, null);
        }
        
        return dossier;
    }
    
    public void ecrire(String nomFichierSortie, Dossier dossier) 
            throws IOException {
        JSONObject jsonObj = new JSONObject();
        JSONArray jsonArr = new JSONArray();
        JSONObject item;
        
        if (dossier != null) {
            jsonObj.accumulate("client", dossier.getNumeroClient());
            jsonObj.accumulate("mois", dossier.getMois());
            Remboursement [] remboursements = dossier.getRemboursements();

            for (int i = 0; i < remboursements.length; i++) {
                item = new JSONObject();
                item.accumulate("soin", remboursements[i].getSoin());
                item.accumulate("date", remboursements[i].getDate());
                item.accumulate("montant", remboursements[i].getMontant());

                jsonArr.add(item);
            }

             jsonObj.accumulate("remboursements", jsonArr);

             Utf8File.saveStringIntoFile(nomFichierSortie, jsonObj.toString(4));
         }
    }
    
    public void ecrireErreur(String nomFichierSortie) throws IOException {
        JSONObject jsonObj = new JSONObject();
        jsonObj.accumulate("message", "Données invalides");
         Utf8File.saveStringIntoFile(nomFichierSortie, jsonObj.toString(4));
    }
    
    
}
