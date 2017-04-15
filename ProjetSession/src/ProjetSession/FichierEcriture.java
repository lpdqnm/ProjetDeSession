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
public class FichierEcriture {

    public static final String[] CLES = FichierLecture.CLES;
    public static final int DOSS = FichierLecture.DOSS;
    public static final int MOIS = FichierLecture.MOIS;
    public static final int RECL = FichierLecture.RECL;
    public static final int SOIN = FichierLecture.SOIN;
    public static final int DATE = FichierLecture.DATE;
    public static final int MONT = FichierLecture.MONT;
    public static final int REMB = FichierLecture.REMB;
    public static final int TOT = FichierLecture.TOT;
    public static final int MSG = FichierLecture.MSG;
    public static String MSG_ERREUR = FichierLecture.MSG_ERREUR;

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

    public static void ecrireErreur(String nomFichierSortie, String msgErr) {
        if (msgErr == null || msgErr.isEmpty()) {
            msgErr = MSG_ERREUR;
        }

        JSONObject jsonObj = new JSONObject();
        jsonObj.accumulate(CLES[MSG], msgErr);
        objJSONEnFichier(nomFichierSortie, jsonObj);
        System.exit(1);
    }

    private static void objJSONEnFichier(String nomFichierSortie, JSONObject objJSON) {
        try {
            Utf8File.saveStringIntoFile(nomFichierSortie, objJSON.toString(4));
        } catch (IOException ioe) {
            System.out.println("Erreur avec le fichier de sortie !");
        }
    }

    public static void ecrireStats(String fichierStats) {
        JSONObject racine = new JSONObject();

        racine.accumulate(Statistiques.RECLAMATIONS_VALIDES, Statistiques.getStatReclamValides());
        racine.accumulate(Statistiques.RECLAMATIONS_REJETEES, Statistiques.getStatReclamRejetees());
        racine.accumulate(Statistiques.SOINS, obtTabJSONSoins(Statistiques.getStatsSoins()));

        objJSONEnFichier(fichierStats, racine);
    }

    protected static JSONArray obtTabJSONSoins(int[] tabStatsSoins) {
        JSONArray tabObjJSON = new JSONArray();
        JSONObject objJSON;

        for (int i = 0; i < tabStatsSoins.length; i++) {
            objJSON = new JSONObject();
            objJSON.accumulate("" + Statistiques.SOINS_NO[i], tabStatsSoins[i]);
            tabObjJSON.add(objJSON);
        }
        return tabObjJSON;
    }

}
