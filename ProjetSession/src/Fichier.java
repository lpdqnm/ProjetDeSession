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
    
    //CONSTANTES
    public static final String [] CLES = {"dossier", "mois", "reclamations",
            "soin", "montant", "date", "total"};
    public static final int DOSS = 0;
    public static final int MOIS = 1;
    public static final int RECL = 2;
    public static final int SOIN = 3;
    public static final int MONT = 4;
    public static final int DATE = 5;
    public static final int TOT = 6;  
    public static final int NB_INFOS = 2;
    
   /**
   * Lit un fichier .json et retourne l'objet Dossier
   * @param nomFichierEntrer Le nom du fichier à lire
   * @return Dossier Retourne l'objet Dossier.
   */
    public static Dossier lire(String nomFichierEntrer){
        JSONObject racine = fichierEnObjJSON(nomFichierEntrer);
        
        if (racine == null)
            return null;
        
        String [] infosClient = obtInfosClient(racine);
        JSONArray tabObjJSON = racine.getJSONArray(CLES[RECL]);
        
        if (tabObjJSON == null)
            return null;
        
        return new Dossier(infosClient[DOSS], infosClient[MOIS],
                obtTabReclam(tabObjJSON), null, null);
    }
    
    private static JSONObject fichierEnObjJSON(String nomFichierEntrer){
        try{
            //Lire fichier et convertir en JSONObject
            String textJson = Utf8File.loadFileIntoString(nomFichierEntrer);
            return (JSONObject) JSONSerializer.toJSON(textJson);
        }catch(Exception ex){
            return null;
        }
    }
    
    private static Reclamation [] obtTabReclam(JSONArray tabJSON){
        Reclamation [] tabReclam = null;
            
        //Boucle sur les réclamations trouvées
        tabReclam = new Reclamation[tabJSON.size()];
        for (int i = 0; i < tabJSON.size(); i++) {
                tabReclam[i] = obtReclam(tabJSON.getJSONObject(i));
                if (tabReclam[i] == null)
                    return null;
        }
        return tabReclam;
    }
    
    private static  Reclamation obtReclam(JSONObject objJSON){
        try{
            return new Reclamation(objJSON.getString(CLES[SOIN]),
                    objJSON.getString(CLES[DATE]), objJSON.getString(CLES[MONT]));
        }catch(Exception ex){
            return null;
        }
    }
    
    private static String [] obtInfosClient(JSONObject objJSON){
        //Permet d'avoir les valeurs du "dossier" et du "mois" des réclamations
        String [] tabInfos = new String[NB_INFOS];
        try {
            tabInfos[DOSS] = objJSON.getString(CLES[DOSS]);
            tabInfos[MOIS] = objJSON.getString(CLES[MOIS]);
        } catch (Exception e) {
            return null;
        }
        return tabInfos;
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
