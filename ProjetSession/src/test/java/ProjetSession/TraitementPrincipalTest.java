package ProjetSession;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import sun.tools.jar.Main;

/**
 *
 * @author jprioux
 */
public class TraitementPrincipalTest {
    String[] args;
   
    Remboursement remb;
    
    @Before
    public void setUp() {
        args = new String[4];     
   
    }
    
    @After
    public void tearDown() {
    }      
        
    @Test
    public void testLesStatistiques(){        
        args[0] = "-S";
        TraitementPrincipal.main(args);
    }
    
}
