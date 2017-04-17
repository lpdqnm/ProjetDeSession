/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetSession;

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
public class FichierEcritureTest {
    
    public FichierEcritureTest() {
    }
    
    Remboursement remb;
    
    JSONArray tbJSON;
    int[] tbStatsSoins;
            
    @Before
    public void setUp() {
        remb = new Remboursement("A","0", "2017-01-11", "10.00$", new Mensuelle());
        
        int[] tabInit = {3,1,4};
        tbStatsSoins = tabInit;
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test de la méthode obtObjJSONRemb , de la class FichierEcriture.
     */
    @Test
    public void testObtObjJSONRemb() {
        System.out.println("obtObjJSONRemb");
        
        JSONObject result = FichierEcriture.obtObjJSONRemb(remb);
        assertEquals(result.get("soin"), "0");
    }
    @Test
    public void testObtObjJSONRembDeux() {
        System.out.println("obtObjJSONRemb");
        
        JSONObject result = FichierEcriture.obtObjJSONRemb(remb);
        assertEquals(result.get("date"), "2017-01-11");
    }
    
    @Test
    public void testObtObjJSONRembTrois() {
        System.out.println("obtObjJSONRemb");
        
        JSONObject result = FichierEcriture.obtObjJSONRemb(remb);
        assertEquals(result.get("montant"), remb.getMontant());
    }
    
    @Test
    public void testObtObjJSONRembQuatre() {
        System.out.println("obtObjJSONRemb");               
        assertEquals(FichierEcriture.obtObjJSONRemb(null),null);
    }
    
    /**
     * Test de la méthode obtTabJSONSoins, de la class FichierEcriture.
     */
    @Test
    public void testObtTabJSONSoins() {
        System.out.println("obtTabJSONSoins");
        
        JSONArray result = FichierEcriture.obtTabJSONSoins(tbStatsSoins, false);
        assertEquals(3, result.getJSONObject(0).getInt("0"));
    }
    @Test
    public void testObtTabJSONSoinsDeux() {
        System.out.println("obtTabJSONSoins");
        
        JSONArray result = FichierEcriture.obtTabJSONSoins(tbStatsSoins, false);
        assertEquals(1, result.getJSONObject(1).getInt("100"));
    }
    @Test
    public void testObtTabJSONSoinsTrois() {
        System.out.println("obtTabJSONSoins");
        
        JSONArray result = FichierEcriture.obtTabJSONSoins(tbStatsSoins, false);
        assertEquals(4, result.getJSONObject(2).getInt("150"));
    }
}
