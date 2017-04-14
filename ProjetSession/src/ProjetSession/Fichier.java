package ProjetSession;

import static ProjetSession.TraitementPrincipal.FICHIER_STATS;
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

    public static Dossier lire(String nomFichierEntrer, String nomFichierSortie) {
        try {
            return lire(nomFichierEntrer);
        } catch (JSONException je) {
            ecrireErreur(nomFichierSortie, je.getMessage());
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    private static Dossier lire(String nomFichierEntrer) throws JSONException, Exception {
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
        Statistiques.majStatReclamRejetees();
        Fichier.ecrireStats(FICHIER_STATS);
        System.exit(1);
    }

    private static void objJSONEnFichier(String nomFichierSortie, JSONObject objJSON) {
        try {
            Utf8File.saveStringIntoFile(nomFichierSortie, objJSON.toString(4));
        } catch (IOException ioe) {
            System.out.println("Erreur avec le fichier de sortie !");
        }
    }

    public static void lireStats(String fichierStats) {
        JSONObject racine = fichierEnObjJSON(fichierStats);

        try {
            Statistiques.initStats(racine.getInt(Statistiques.RECLAMATIONS_VALIDES),
                    racine.getInt(Statistiques.RECLAMATIONS_REJETEES),
                    obtTabStatsSoins(racine.getJSONArray(Statistiques.SOINS)));
        } catch (Exception e) {
            Statistiques.initStats(0, 0, new int[Statistiques.SOINS_NO.length]);
        }
    }

    protected static int[] obtTabStatsSoins(JSONArray tabJSON) throws Exception {
        int[] tabStatsSoins = new int[tabJSON.size()];

        for (int i = 0; i < tabStatsSoins.length; i++) {
            tabStatsSoins[i] = tabJSON.getJSONObject(i).getInt("" + Statistiques.SOINS_NO[i]);
        }
        return tabStatsSoins;
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
