/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetSession;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author jprioux
 */
public class ContratTest {
    @Test 
    public void testClassContrat() {
        Contrat contrat = new Contrat();
        assertEquals(Contrat.class, contrat.getClass());                
    }
}
