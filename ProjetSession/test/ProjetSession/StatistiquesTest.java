/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetSession;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Leopold Quenum
 * @author JP Rioux
 */
public class StatistiquesTest {
    
    public StatistiquesTest() {
    }
    //Varialble d'instance
    Statistiques instance;
    
    @Before
    public void setUp() {
        instance = new Statistiques();
    } 
    
    /**
     * Test of majStatsSoins method, of class Statistiques.
     */
//    @Test
//    public void testMajStatsSoins() {
//        System.out.println("majStatsSoins");
//        Dossier dossier = null;
//        Statistiques instance = null;
//        instance.majStatsSoins(dossier);
//    }

    /**
     * Test of soinDentaireNoMin method, of class Statistiques.
     */
    @Test
    public void testSoinDentaireNoMin() {
        System.out.println("soinDentaireNoMin");
        int soinNo = 300;
        int expResult = 300;
        int result = instance.soinDentaireNoMin(soinNo);
        assertEquals(expResult, result);
    }

    /**
     * Test of obtIndexSoinNo method, of class Statistiques.
     */
    @Test
    public void testObtIndexSoinNo() {
        System.out.println("obtIndexSoinNo");
        int soinNo = 0;
        int expResult = 0;
        int result = instance.obtIndexSoinNo(soinNo);
        assertEquals(expResult, result);
    }

    /**
     * Test of afficherStats method, of class Statistiques.
     */
    @Test
    public void testAfficherStats() {
        System.out.println("afficherStats");
        Statistiques.afficherStats();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
