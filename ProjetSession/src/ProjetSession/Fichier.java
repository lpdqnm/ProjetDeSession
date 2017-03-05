package ProjetSession;
import java.io.IOException;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 * Fonctions pour lire et écrire dans les fichiers JSON
 *
 * @author Leopold Quenum
 * @author JP Rioux
 */
public class Fichier {

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

    /**
     * Lit un fichier .json et retourne l'objet Dossier
     *
     * @param nomFichierEntrer Le nom du fichier à lire
     * @return Dossier Retourne l'objet Dossier.
     */
    public static Dossier lire(String nomFichierEntrer, String nomFichierSortie) {
        try {
            return lire(nomFichierEntrer);
        } catch (JSONException je) {
            ecrireErreur(nomFichierSortie, je.getMessage());
            System.exit(1);
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private static Dossier lire(String nomFichierEntrer) throws JSONException, Exception {
        JSONObject racine = fichierEnObjJSON(nomFichierEntrer);
        
        if (racine == null){
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
        Reclamation[] tabReclam = null;
        tabReclam = new Reclamation[tabJSON.size()];

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

    /**
     * Écrit le résultat de l'objet dossier dans un fichier .json
     *
     * @param nomFichierSortie Le nom du fichier à écrire
     * @param dossier L'objet Dossier
     */
    public static void ecrire(String nomFichierSortie, Dossier dossier) {
        JSONObject racine = new JSONObject();

        if (dossier != null) {
            racine.accumulate(CLES[DOSS], dossier.getDossierClient());
            racine.accumulate(CLES[MOIS], dossier.getMois());
            racine.accumulate(CLES[REMB], obtTabJSONRemb(dossier.getRemboursements()));
            racine.accumulate(CLES[TOT], dossier.getTotal());
        }

        objJSONEnFichier(nomFichierSortie, racine);
    }

    private static JSONArray obtTabJSONRemb(Remboursement[] tabRemb) {
        if (tabRemb == null) {
            return null;
        }

        JSONArray tabJSON = new JSONArray();

        for (int i = 0; i < tabRemb.length; i++) {
            if (obtObjJSONRemb(tabRemb[i]) == null) {
                return null;
            }

            tabJSON.add(obtObjJSONRemb(tabRemb[i]));
        }

        return tabJSON;
    }

    private static JSONObject obtObjJSONRemb(Remboursement remboursement) {
        if (remboursement == null) {
            return null;
        }

        JSONObject objJSON = new JSONObject();
        objJSON.accumulate(CLES[SOIN], remboursement.getSoin());
        objJSON.accumulate(CLES[DATE], remboursement.getDate());
        objJSON.accumulate(CLES[MONT], remboursement.getMontant());
        return objJSON;
    }

    /**
     * Écrit une erreur dans le fichier .json passé en paramètre.
     *
     * @param nomFichierSortie Le nom du fichier à écrire
     */
    public static void ecrireErreur(String nomFichierSortie, String msgErr) {
        if (msgErr == null || msgErr.isEmpty()) {
            msgErr = MSG_ERREUR;
        }

        JSONObject jsonObj = new JSONObject();
        jsonObj.accumulate(CLES[MSG], msgErr);
        objJSONEnFichier(nomFichierSortie, jsonObj);
    }

    private static void objJSONEnFichier(String nomFichierSortie, JSONObject objJSON) {
        try {
            Utf8File.saveStringIntoFile(nomFichierSortie, objJSON.toString(4));
        } catch (IOException ioe) {
            System.out.println("Erreur avec le fichier de sortie !");
        }
    }

}
