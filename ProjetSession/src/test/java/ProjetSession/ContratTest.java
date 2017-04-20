package ProjetSession;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * @author Leopold Quenum
 * @author JP Rioux
 */
public class ContratTest {
    @Test 
    public void testClassContrat() {
        Contrat contrat = new Contrat();
        assertEquals(Contrat.class, contrat.getClass());                
    }
}
