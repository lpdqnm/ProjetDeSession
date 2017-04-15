package ProjetSession;

import java.io.IOException;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 * Fonctions pour lire dans les fichiers JSON
 *
 * @author Leopold Quenum
 * @author JP Rioux
 */
public class FichierLecture {

    public static final String[] CLES = {"dossier", "mois", "reclamations", "soin", "date",
        "montant", "remboursements", "total", "message"};
    public static final int DOSS = 0;
    public static final int MOIS = 1;
    public static final int RECL = 2;
    public static final int SOIN = 3;
    public static final int DATE = 4;
    public static final int MONT = 5;
    public static final int REMB = 6;
    public static final int TOT = 7;
    public static final int MSG = 8;
    public static String MSG_ERREUR = "Données invalides";
    public static String MSG_ERR_JSON_1 = "La clé '";
    public static String MSG_ERR_JSON_2 = "' est introuvable dans le fichier JSON";

    public static Dossier lireInfosDossier(String nomFichierEntrer, String nomFichierSortie) {
        try {
            return lireInfosDossier(nomFichierEntrer);
        } catch (JSONException je) {
            FichierEcriture.ecrireErreurInfosDossier(nomFichierSortie, MSG_ERR_JSON_1 + obtCleErrJson(je.getMessage()) 
                    + MSG_ERR_JSON_2);
                    
            return null;
        } catch (Exception e) {
            return null;
        }
    }
    
     private static String obtCleErrJson(String msgErrJson) {
         return msgErrJson.substring(msgErrJson.indexOf("\"") + 1, msgErrJson.lastIndexOf("\""));
     }

    private static Dossier lireInfosDossier(String nomFichierEntrer) throws JSONException, Exception {
        JSONObject racine = fichierEnObjJSON(nomFichierEntrer);

        if (racine == null) {
            System.out.println("Fichier d'entrée introuvable!");
            System.exit(1);
        }

        return new Dossier(racine.getString(CLES[DOSS]), racine.getString(CLES[MOIS]),
                obtTabReclam(racine.getJSONArray(CLES[RECL])), null, null);
    }

    private static JSONObject fichierEnObjJSON(String nomFichierEntrer) {
        try {
            //Lire fichier et convertir en JSONObject
            String textJson = Utf8File.loadFileIntoString(nomFichierEntrer);
            return (JSONObject) JSONSerializer.toJSON(textJson);
        } catch (IOException ioe) {
            return null;
        }
    }

    private static Reclamation[] obtTabReclam(JSONArray tabJSON) throws Exception {
        Reclamation[] tabReclam = new Reclamation[tabJSON.size()];

        for (int i = 0; i < tabJSON.size(); i++) {
            tabReclam[i] = obtReclam(tabJSON.getJSONObject(i));

            if (tabReclam[i] == null) {
                return null;
            }
        }

        return tabReclam;
    }

    private static Reclamation obtReclam(JSONObject objJSON) throws JSONException, Exception {
        return new Reclamation(objJSON.getString(CLES[SOIN]), objJSON.getString(CLES[DATE]),
                objJSON.getString(CLES[MONT]));
    }


    public static void lireStats(String fichierStats) {
        JSONObject racine = fichierEnObjJSON(fichierStats);
        
        try {
            int nbReclamVald = racine.getInt(Statistiques.RECLAMATIONS_VALIDES);
            int nbReclamRej = racine.getInt(Statistiques.RECLAMATIONS_REJETEES);
            JSONArray tbJsonNbSoins = racine.getJSONArray(Statistiques.SOINS);
            JSONArray tbJsonMxSoins = racine.getJSONArray(Statistiques.MONTT_MAX_SOINS);
            JSONArray tbJsonTotSoins = racine.getJSONArray(Statistiques.MONTT_TOT_SOINS);

        Statistiques.initStats(nbReclamVald, nbReclamRej, obtTabStatsSoins(tbJsonNbSoins, false), 
                obtTabStatsSoins(tbJsonMxSoins, true), obtTabStatsSoins(tbJsonTotSoins, true));
        } catch (Exception e) {
            Statistiques.initStats(0, 0, new int[Statistiques.SOINS_NO.length],
                    new int[Statistiques.SOINS_NO.length], new int[Statistiques.SOINS_NO.length]);
        }
    }

    protected static int[] obtTabStatsSoins(JSONArray tabJSON, boolean monttDollar) throws Exception {
        int[] tbStatsSoins = new int[tabJSON.size()];

        for (int i = 0; i < tbStatsSoins.length; i++) {
            if (monttDollar) {
                tbStatsSoins[i] = Dollar.StringVersInt(tabJSON.getJSONObject(i).getString("" 
                        + Statistiques.SOINS_NO[i]));
            } else {
                tbStatsSoins[i] = tabJSON.getJSONObject(i).getInt("" + Statistiques.SOINS_NO[i]);                
            }

        }
        return tbStatsSoins;
    }
   
}
