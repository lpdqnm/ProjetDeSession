/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetSession;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Leopold
 */
public class FichierLectureTest {
    
    public FichierLectureTest() {
    }
    
    JSONObject objJSON;
    Reclamation recl;
    
    JSONArray tabJSON;
    boolean monttDollar;
    JSONObject objJSONItem;
    
    Dossier dossier;
            
    @Before
    public void setUp() {
        objJSON = new JSONObject();
	objJSON.accumulate("soin", "0");
	objJSON.accumulate("date", "2017-01-11");
	objJSON.accumulate("montant", "100$");
        
        tabJSON = new JSONArray();
        monttDollar = false;
        
        objJSONItem = new JSONObject();
        objJSONItem.accumulate("0", 2);
        tabJSON.add(objJSONItem);
        
        objJSONItem = new JSONObject();
        objJSONItem.accumulate("100", 0);
        tabJSON.add(objJSONItem);

        objJSONItem = new JSONObject();
        objJSONItem.accumulate("150", 1);
        tabJSON.add(objJSONItem);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test de la méthode obtCleErrJson, de la class FichierLecture.
     */
    @Test
    public void testObtCleErrJson() {
        System.out.println("obtCleErrJson");
        String msgErrJson = "recherche de la clé \"cleJson\" dans cette chaine";
        String result = FichierLecture.obtCleErrJson(msgErrJson);
        assertEquals("cleJson", result);
    }
    
    /**
     * Test de la méthode obtReclam method, de la class FichierLecture.
     */
    @Test
    public void testObtReclam() throws Exception {
        System.out.println("obtReclam");
        
        recl = FichierLecture.obtReclam(objJSON);
        assertEquals(recl.getSoin(), "0");
    }
    @Test
    public void testObtReclamDeux() throws Exception {
        System.out.println("obtReclam");
        
        recl = FichierLecture.obtReclam(objJSON);
        assertEquals(recl.getDate(), "2017-01-11");
    }
    @Test
    public void testObtReclamTrois() throws Exception {
        System.out.println("obtReclam");
        
        recl = FichierLecture.obtReclam(objJSON);
        assertEquals(recl.getMontant(), "100$");
    }
    
    /**
     * Test de la méthode obtTabStatsSoins , de la class FichierLecture.
     */
    @Test
    public void testObtTabStatsSoins() throws Exception {
        System.out.println("obtTabStatsSoins");
        
        int[] expResult = {2,0,1};
        int[] result = FichierLecture.obtTabStatsSoins(tabJSON, monttDollar);
        assertArrayEquals(expResult, result);
    }
    
    /**
     * Test de la méthode fichierEnObjJSONNull , de la class FichierLecture.
     */
    @Test
    public void testFichierEnObjJSONNull() {
        
        objJSON = FichierLecture.fichierEnObjJSON("fichierInexistant");
        assertEquals(objJSON, null);
    }
    @Test
    public void testFichierEnObjJSONUn() {
        
        objJSON = FichierLecture.fichierEnObjJSON(".\\src\\test\\resources\\jsonTest.json");
        assertEquals(objJSON.getString("test"), "JUnit test");
    }
    @Test
    public void testFichierEnObjJSONDeux() {
        
        objJSON = FichierLecture.fichierEnObjJSON(".\\src\\test\\resources\\jsonTest.json");
        assertEquals(objJSON.getString("langage"), "Java");
    }
    @Test
    public void testFichierEnObjJSODossier() {
        
        objJSON = FichierLecture.fichierEnObjJSON(".\\src\\test\\resources\\dossierFichier.json");
        
        String jsonStr = "{\"dossier\":\"A100323\",\"mois\":\"2017-01\",\"reclamations\":"
                + "[{\"soin\":175,\"date\":\"2017-01-11\",\"montant\":\"130.00$\"},{\"soin\":"
                + "175,\"date\":\"2017-01-14\",\"montant\":\"130.00$\"},{\"soin\":175,\"date\":"
                + "\"2017-01-15\",\"montant\":\"130.00$\"},{\"soin\":175,\"date\":\"2017-01-17\","
                + "\"montant\":\"130.00$\"}]}";
        
        assertEquals(objJSON.toString(), jsonStr);
    }
    
    /**
     * Test de la méthode lireInfosDossier , de la class FichierLecture.
     */
    @Test
    public void testLireInfosDossier() throws Exception {
        
        dossier = FichierLecture.lireInfosDossier(".\\src\\test\\resources\\dossierFichier.json");
        
        assertEquals(dossier.getDossierClient(), "A100323");
    }
    
}
