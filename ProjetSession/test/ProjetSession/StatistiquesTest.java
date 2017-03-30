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
    
    Statistiques instance;
    
    @Before
    public void setUp() {
        instance = new Statistiques();
    } 
    
    // Test sur fonction : soinDentaireNoMin
    @Test
    public void testSoinDentaireNoMin() {
        System.out.println("soinDentaireNoMin");
        int soinNo = 300;
        int expResult = 300;
        int result = instance.soinDentaireNoMin(soinNo);
        assertEquals(expResult, result);
    }

    // Test sur fonction : obtIndexSoinNo
    @Test
    public void testObtIndexSoinNo() {
        System.out.println("obtIndexSoinNo");
        int soinNo = 0;
        int expResult = 0;
        int result = instance.obtIndexSoinNo(soinNo);
        assertEquals(expResult, result);
    }

    // Test sur fonction : afficherStats
    @Test
    public void testAfficherStats() {
        System.out.println("afficherStats");
        Statistiques.afficherStats();
    }   
}
