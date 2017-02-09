import java.io.IOException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
/**
* Fonctions pour lire et écrire dans les fichiers JSON
*
* @author Leopold Quenum
* @author JP Rioux
* @version 1.0
* @since   2017-02-27 
*/
public class Fichier {
    
   /**
   * Lit un fichier .json et retourne l'objet Dossier
   * @param nomFichierEntrer Le nom du fichier à lire
   * @return Dossier Retourne l'objet Dossier.
   */
    public Dossier lire(String nomFichierEntrer){
        Dossier dossier = null;
        String client, contrat, mois, soin, date, montant;
        JSONArray tabObjJson;
        JSONObject item, racine;
        int lgTab;
        Reclamation [] reclamations;
        
        try{
            //Lire fichier et convertir en JSONObject
            String textJson = Utf8File.loadFileIntoString(nomFichierEntrer);
            racine = (JSONObject) JSONSerializer.toJSON(textJson);
        }catch(Exception ex){
            return null;
        }
        
        if (racine != null) {
            try{
                //Obtenir les éléments à la racine
                client = racine.getString("client");
                contrat = racine.getString("contrat");
                mois = racine.getString("mois");
                //Obtenir le tableau de réclamations
                tabObjJson = racine.getJSONArray("reclamations");
            }catch(Exception ex){
                return null;
            }
            
            lgTab = tabObjJson.size();
            reclamations = new Reclamation[lgTab];
            
            //Boucle sur les réclamations trouvées
            for (int i = 0; i < lgTab; i++) {
                try{
                    item = tabObjJson.getJSONObject(i);
                    soin = item.getString("soin");
                    date = item.getString("date");
                    montant = item.getString("montant");
                }catch(Exception ex){
                    return null;
                }
                
                reclamations[i] = new Reclamation(soin, date, montant);
            }
            
            dossier = new Dossier(client, contrat, mois, reclamations, null);
        }
        
        return dossier;
    }
    
   /**
   * Écrit le résultat de l'objet dossier dans un fichier .json
   * @param nomFichierSortie Le nom du fichier à écrire
   * @param dossier L'objet Dossier
   */
    public void ecrire(String nomFichierSortie, Dossier dossier) throws IOException {
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
    
    /**
    * Écrit une erreur dans le fichier .json passé en paramètre.
    * @param nomFichierSortie Le nom du fichier à écrire
    */   
    public void ecrireErreur(String nomFichierSortie) throws IOException {
        JSONObject jsonObj = new JSONObject();
        jsonObj.accumulate("message", "Données invalides");
         Utf8File.saveStringIntoFile(nomFichierSortie, jsonObj.toString(4));
    }
    
    
}
