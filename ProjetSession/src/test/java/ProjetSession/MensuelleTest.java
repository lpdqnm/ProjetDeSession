package ProjetSession;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jprioux
 */
public class MensuelleTest {
    
    Mensuelle mens;
    Mensuelle osteo;
    Mensuelle med;
    Mensuelle psycho;
    Mensuelle chiro;
    Mensuelle physio;
    static int MAX_OSTEO = 25000;
    static int MAX_OSTEO_MOINS_DIX = 24000;
    static int MAX_MED = 20000;
    static int MAX_PSYCHO = 25000;
    static int MAX_CHIRO = 15000;
    static int MAX_PHYSIO = 30000;
        
    @Before
    public void setUp() {
        mens = new Mensuelle();       
        osteo = new Mensuelle(Soin.OSTEOPATHIE, MAX_OSTEO, 0);
        med = new Mensuelle(Soin.MEDECIN_GENERALISTE_PRIVE, MAX_MED, 0);
        psycho = new Mensuelle(Soin.PSYCHOLOGIE_INDIVIDUELLE, MAX_PSYCHO, 0);
        chiro = new Mensuelle(Soin.CHIROPRATIE, MAX_CHIRO, 0);
        physio = new Mensuelle(Soin.PHYSIOTHERAPIE, MAX_PHYSIO, 0); 
    }
    
    @After
    public void tearDown() {
    }      
        
    @Test
    public void testGetInstanceSoin(){                 
        assertEquals(mens.getInstanceSoin(Soin.OSTEOPATHIE).soin,osteo.soin);
        assertEquals(mens.getInstanceSoin(Soin.OSTEOPATHIE).max,osteo.max);
        
        assertEquals(mens.getInstanceSoin(Soin.MEDECIN_GENERALISTE_PRIVE).soin,med.soin);  
        assertEquals(mens.getInstanceSoin(Soin.MEDECIN_GENERALISTE_PRIVE).max,med.max);  
        
        assertEquals(mens.getInstanceSoin(Soin.PSYCHOLOGIE_INDIVIDUELLE).soin,psycho.soin);  
        assertEquals(mens.getInstanceSoin(Soin.PSYCHOLOGIE_INDIVIDUELLE).max,psycho.max);  
        
        assertEquals(mens.getInstanceSoin(Soin.CHIROPRATIE).soin,chiro.soin);  
        assertEquals(mens.getInstanceSoin(Soin.CHIROPRATIE).max,chiro.max);  
        
        assertEquals(mens.getInstanceSoin(Soin.PHYSIOTHERAPIE).soin,physio.soin);  
        assertEquals(mens.getInstanceSoin(Soin.PHYSIOTHERAPIE).max,physio.max);  
        
        assertEquals(mens.getInstanceSoin(911),null);
    }
    
    @Test
    public void testEstMaxAtteintPourSoin(){
        mens.additionerMontantPourSoin(Soin.OSTEOPATHIE, MAX_OSTEO);
        assertTrue(mens.estMaxAtteintPourSoin(Soin.OSTEOPATHIE));
    }
    
    @Test
    public void testGetMaxPourSoin(){              
        assertEquals(mens.getMaxPourSoin(Soin.OSTEOPATHIE,10000),10000);
        
        mens.additionerMontantPourSoin(Soin.OSTEOPATHIE, 0);
        assertEquals(mens.getMaxPourSoin(Soin.OSTEOPATHIE,10000),10000);
                
        mens.additionerMontantPourSoin(Soin.OSTEOPATHIE, MAX_OSTEO_MOINS_DIX);
        assertEquals(mens.getMaxPourSoin(Soin.OSTEOPATHIE,10000),1000);
        
        mens.additionerMontantPourSoin(Soin.OSTEOPATHIE, MAX_OSTEO);
        assertEquals(mens.getMaxPourSoin(Soin.OSTEOPATHIE,10000),0);
    }
    
    
    

    
}
