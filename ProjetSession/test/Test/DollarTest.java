package Test;

import ProjetSession.Dollar;
import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * Classe pour tester la classe Dollar
 *
 * @author Leopold Quenum
 * @author JP Rioux
 */
public class DollarTest {
    
    @Test 
    public void testMultiZeroSurMontant() {
        assertEquals(0, Dollar.multiplication(100, 0));
    }

    @Test 
    public void testMultiZeroSurMulti() {
        assertEquals(0, Dollar.multiplication(0, 20));
    }    
    
    @Test 
    public void testMultiPetitMulti() {
        assertEquals(1, Dollar.multiplication(100, 0.01));
    }
    
    @Test 
    public void testMultiPetitMulti2() {
        assertEquals(1, Dollar.multiplication(51, 0.01));
    }
    
    @Test 
    public void testMultiPetitMulti3() {
        assertEquals(0, Dollar.multiplication(49, 0.01));
    }
    
    @Test 
    public void testMultiPetitMontant() {
        assertEquals(5, Dollar.multiplication(1, 5));
    } 
    
    @Test 
    public void testMultiNormalEntier() {
        assertEquals(550, Dollar.multiplication(550, 1));
    } 
    
    @Test 
    public void testMultiNormalPourcentage() {
        assertEquals(110, Dollar.multiplication(550, 0.2));
    }
    
    @Test 
    public void testMultiNegatif() {
        assertEquals(0, Dollar.multiplication(-550, 0.2));
    }
    
    @Test 
    public void testIntVersStringZero() {
        assertEquals("0.00", Dollar.IntVersString(0));
    }
    
    @Test 
    public void testIntVersStringUnDecimal() {
        assertEquals("5.50", Dollar.IntVersString(550));
    }
    
    @Test 
    public void testIntVersStringNormal() {
        assertEquals("75.55", Dollar.IntVersString(7555));
    }
    
    @Test 
    public void testIntVersStringNeg() {
        assertEquals("0.00", Dollar.IntVersString(-7555));
    }
    
    @Test 
    public void testStringVersIntSansDollar() {
        assertEquals(550, Dollar.StringVersInt("5.5"));
    }
    
    @Test 
    public void testStringVersIntAvecDollar() {
        assertEquals(550, Dollar.StringVersInt("5.5$"));
    }
    
    @Test 
    public void testStringVersIntAvecVirgule() {
        assertEquals(550, Dollar.StringVersInt("5,5$"));
    }
    
    @Test 
    public void testStringVersIntAvecDeuxDecimal() {
        assertEquals(505, Dollar.StringVersInt("5.05$"));
    }
    
    @Test 
    public void testStringVersIntAvecNull() {
        assertEquals(0, Dollar.StringVersInt(null));
    }
    
    @Test 
    public void testStringVersIntAvecLettre() {
        assertEquals(0, Dollar.StringVersInt("5aa.5$"));
    }
    
    @Test 
    public void testStringVersIntAvecPlusieursPoint() {
        assertEquals(0, Dollar.StringVersInt("5.5.$"));
    }
    
    @Test 
    public void testStringVersIntAvecNegatif() {
        assertEquals(-550, Dollar.StringVersInt("-5.5$"));
    }


}
