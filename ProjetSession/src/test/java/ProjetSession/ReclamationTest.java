
package ProjetSession;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jprioux
 */
public class ReclamationTest {
    
    Reclamation reclamValide;
    Reclamation[] tabReclamInvalide_Soin;
    Reclamation[] tabReclamInvalide_Montant;
    Reclamation[] tabReclamInvalide_DateVsDemande;
    Reclamation[] tabReclamInvalide_DateFormat;
    Reclamation reclamInvalide_MaxMontant;
    Reclamation reclamInvalide_MinMontant;
    Reclamation reclamInvalide_Jour;
    static String DATE_TRAITEMENT = "2017-01";    
    static final String MONTANT_MAXIMUM_ERR = "500.01$";
    static final String MONTANT_MINIMUM_ERR = "0.00$";
    
     
    @Before
    public void setUp() {
        reclamValide = new Reclamation("200","2017-01-11","69.00$");
        
        tabReclamInvalide_Soin = new Reclamation[2];
        tabReclamInvalide_Soin[0] = new Reclamation("ABC","2017-01-11","69.00$");
        tabReclamInvalide_Soin[1] = new Reclamation("991","2017-01-11","69.00$");         
        
        tabReclamInvalide_Montant = new Reclamation[3];
        tabReclamInvalide_Montant[0] = new Reclamation("200","2017-01-11","69..00$");
        tabReclamInvalide_Montant[1] = new Reclamation("200","2017-01-11","a.00$"); 
        tabReclamInvalide_Montant[2] = new Reclamation("200","2017-01-11","69.0b$"); 
        
        tabReclamInvalide_DateVsDemande = new Reclamation[2];
        tabReclamInvalide_DateVsDemande[0] = new Reclamation("200","2017-02-11","69.00$");
        tabReclamInvalide_DateVsDemande[1] = new Reclamation("200","2018-01-11","69.00$");
        
        tabReclamInvalide_DateFormat = new Reclamation[8];
        tabReclamInvalide_DateFormat[0] = new Reclamation("200","201-02-11","69.00$");
        tabReclamInvalide_DateFormat[1] = new Reclamation("200","2017-13-11","69.00$");  
        tabReclamInvalide_DateFormat[2] = new Reclamation("200","2017-01-32","69.00$");
        tabReclamInvalide_DateFormat[3] = new Reclamation("200","2017-02-30","69.00$");
        tabReclamInvalide_DateFormat[4] = new Reclamation("200","2017-2-3","69.00$");
        tabReclamInvalide_DateFormat[5] = new Reclamation("200","2017-2-3","69.00$");
        tabReclamInvalide_DateFormat[6] = new Reclamation("200","2017-2-3A","69.00$");
        tabReclamInvalide_DateFormat[7] = new Reclamation("200","2017-0B-01","69.00$");
        
        reclamInvalide_MaxMontant = new Reclamation("200","2017-01-11",MONTANT_MAXIMUM_ERR);
        reclamInvalide_MinMontant = new Reclamation("200","2017-01-11",MONTANT_MINIMUM_ERR);
        
        reclamInvalide_Jour = new Reclamation("200","2017-01-ab","69.00$");
    }
    
    @After
    public void tearDown() {
    }      
        
    @Test
    public void testEstValide(){  
        assertTrue(reclamValide.estValide(DATE_TRAITEMENT));
    }
    
    @Test
    public void testEstValideSoin(){  
        for (int i = 0; i < tabReclamInvalide_Soin.length; i++) {
            assertFalse(tabReclamInvalide_Soin[i].estValide(DATE_TRAITEMENT));            
        }        
    }
    
    @Test
    public void testEstValideMontant(){  
        for (int i = 0; i < tabReclamInvalide_Montant.length; i++) {
            assertFalse(tabReclamInvalide_Montant[i].estValide(DATE_TRAITEMENT));            
        }        
    }
    
    @Test
    public void testEstValideDateReclamVsDateDemande(){  
        for (int i = 0; i < tabReclamInvalide_DateVsDemande.length; i++) {
            assertFalse(tabReclamInvalide_DateVsDemande[i].estValide(DATE_TRAITEMENT));            
        }        
    }
    
    @Test
    public void testEstValideFormatDate(){  
        for (int i = 0; i < tabReclamInvalide_DateFormat.length; i++) {
            assertFalse(tabReclamInvalide_DateFormat[i].estValide(DATE_TRAITEMENT));            
        }        
    }
    
    @Test
    public void testEstValideMaxMontant(){  
          assertFalse(reclamInvalide_MaxMontant.estValide(DATE_TRAITEMENT));  
    }
    
    @Test
    public void testEstValideMinMontant(){  
          assertFalse(reclamInvalide_MinMontant.estValide(DATE_TRAITEMENT));  
    }
    
    @Test
    public void testSetGetErreur(){  
        reclamValide.setErreur("allo");
        assertEquals(reclamValide.getErreur(),"allo");
    }
    
    @Test
    public void testGetJour(){  
        assertEquals(reclamValide.getJour(),11);
        assertEquals(reclamInvalide_Jour.getJour(),1);
    }
    
    
}
