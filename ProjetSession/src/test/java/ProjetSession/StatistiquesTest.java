package ProjetSession;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Assert;
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
    int[] tabNbrSoins = new int[10];
    int[] tbMaxMonttSoins = new int[10];
    int[] tbTotMonttSoins = new int[10];
    
    private final ByteArrayOutputStream sortieContenu = new ByteArrayOutputStream();    
    
    
    @Before
    public void setUp() {
        
        System.setOut(new PrintStream(sortieContenu));
        rembs[0] = new Remboursement("A","0", "2017-01-11", "10.00$", new Mensuelle());
        rembs[1] = new Remboursement("A", "175", "2017-01-14", "15.00$", new Mensuelle());
        rembs[2] = new Remboursement("A", "175", "2017-01-15", "20.00$", new Mensuelle());
        dossier = new Dossier("A100323", "2017-01", null, rembs, null); 
        
        
        tabNbrSoins[0] = 1;
        tbMaxMonttSoins[0] = 1;
        tbTotMonttSoins[0] = 1;
        
        
    }
    
    @After
    public void tearDown() {   
        Statistiques.reinitStats();
    }      
    
    @Test 
    public void testClassStatistiques() {
        Statistiques statistiques = new Statistiques();
        assertEquals(Statistiques.class, statistiques.getClass());                
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
    
    @Test
    public void testObtIndexTbSoinNo(){
        System.out.println("obtIndexSoinNo");      
        assertEquals(-1, Statistiques.obtIndexTbSoinNo(-1));
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
    
    @Test
    public void testSetGetNbrReclamRejetees(){
        Statistiques.majStatReclamRejetees();
        assertEquals(1,Statistiques.getNbrReclamRejetees());
    }
    
    @Test
    public void testSetGetNbrReclamValides(){
        Statistiques.majStatReclamValides();
        assertEquals(1,Statistiques.getNbrReclamValides());
    }
    
    @Test
    public void testAfficherStats() {                           
        // Vérifie seulement si les procédures d'affichages ne plante pas
       Statistiques.initStats(1, 1, tabNbrSoins, tbMaxMonttSoins, tbTotMonttSoins);
       Statistiques.afficherStats();
       assertEquals(sortieContenu.toString(),sortieContenu.toString());            
    }
    
    @Test
    public void testobtIntervalSoinsDents(){
        assertEquals("300...399",Statistiques.obtIntervalSoinsDents(300));        
        assertEquals("301",Statistiques.obtIntervalSoinsDents(301));
    }
    
    @Test
    public void testInitStats(){
        Statistiques.initStats(1, 1, tabNbrSoins, tbMaxMonttSoins, tbTotMonttSoins);
        assertEquals(1,Statistiques.getNbrReclamValides());
        assertEquals(1,Statistiques.getNbrReclamRejetees());
        Assert.assertArrayEquals(tbMaxMonttSoins,Statistiques.getTbMaxMonttSoins());
        Assert.assertArrayEquals(tbTotMonttSoins,Statistiques.getTbTotMonttSoins());        
    }

}
