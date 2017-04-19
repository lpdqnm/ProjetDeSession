package ProjetSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Leopold Quenum
 * @author JP Rioux
 */
public class StatistiquesTest {
    Remboursement[] rembs = new Remboursement[3];
    Dossier dossier;
    
    @Before
    public void setUp() {
        rembs[0] = new Remboursement("A","0", "2017-01-11", "10.00$", new Mensuelle());
        rembs[1] = new Remboursement("A", "175", "2017-01-14", "15.00$", new Mensuelle());
        rembs[2] = new Remboursement("A", "175", "2017-01-15", "20.00$", new Mensuelle());
        dossier = new Dossier("A100323", "2017-01", null, rembs, null); 
        
    }
    
    @After
    public void tearDown() {
        Statistiques.reinitStats();
    }      
    
    // Test sur fonction : soinDentaireNoMin
    @Test
    public void testSoinDentaireNoMin() {
        System.out.println("soinDentaireNoMin");
        
        int result = Statistiques.soinDentaireNoMin(300);
        assertEquals(300, result);
    }
    @Test
    public void testSoinDentaireNoMinDeux() {
        System.out.println("soinDentaireNoMin");
        
        int result = Statistiques.soinDentaireNoMin(399);
        assertEquals(300, result);
    }
    @Test
    public void testSoinDentaireNoMinTrois() {
        System.out.println("soinDentaireNoMin");
        
        int result = Statistiques.soinDentaireNoMin(350);
        assertEquals(300, result);
    }
    
    // Test sur fonction : obtIndexTbSoinNo
    @Test
    public void testObtIndexSoinNo() {
        System.out.println("obtIndexSoinNo");
        
        int result = Statistiques.obtIndexTbSoinNo(0);
        assertEquals(0, result);
    }
    @Test
    public void testObtIndexSoinNoDeux() {
        System.out.println("obtIndexSoinNo");
       
        int result = Statistiques.obtIndexTbSoinNo(700);
        assertEquals(9, result);
    }
    @Test
    public void testObtIndexSoinNoTrois() {
        System.out.println("obtIndexSoinNo");
        
        int result = Statistiques.obtIndexTbSoinNo(175);
        assertEquals(3, result);
    }
    
    // Test sur fonctions : majStatsSoins , getTabNbrSoins, getTbMaxMonttSoins, getTbTotMonttSoins
    @Test
    public void testMajStatsSoins() {
        System.out.println("majStatsSoins");
        
        Statistiques.majStatsSoins(dossier);

        assertEquals(Statistiques.getTabNbrSoins()[0], 1);
    }
    @Test
    public void testMajStatsSoinsDeux() {
        System.out.println("majStatsSoins");
        
        Statistiques.majStatsSoins(dossier);
        
        assertEquals(Statistiques.getTbMaxMonttSoins()[0], Dollar.StringVersInt(rembs[0]
                .getMontant()));
    }
    @Test
    public void testMajStatsSoinsTrois() {
        System.out.println("majStatsSoins");
        
        Statistiques.majStatsSoins(dossier);

        assertEquals(Statistiques.getTbTotMonttSoins()[0], Dollar.StringVersInt(rembs[0]
                .getMontant()));
    }
    @Test
    public void testMajStatsSoinsQuatre() {
        System.out.println("majStatsSoins");
        
        Statistiques.majStatsSoins(dossier);

        assertEquals(Statistiques.getTabNbrSoins()[3], 2);
    }
    @Test
    public void testMajStatsSoinsCing() {
        System.out.println("majStatsSoins");
        
        Statistiques.majStatsSoins(dossier);

        assertEquals(Statistiques.getTbMaxMonttSoins()[3], Math.max(Dollar.StringVersInt(rembs[1]
                .getMontant()), Dollar.StringVersInt(rembs[2].getMontant())));
    }
    @Test
    public void testMajStatsSoinsSix() {
        System.out.println("majStatsSoins");
        
        Statistiques.majStatsSoins(dossier);

        assertEquals(Statistiques.getTbTotMonttSoins()[3], Dollar.StringVersInt(rembs[1]
                .getMontant())  + Dollar.StringVersInt(rembs[2].getMontant()));
    }
    
}
