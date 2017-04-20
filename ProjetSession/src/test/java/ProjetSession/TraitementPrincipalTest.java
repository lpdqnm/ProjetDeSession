/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Leopold
 * @author JP Rioux
 */
public class TraitementPrincipalTest {
   
    TraitementPrincipal traitement;
    Reclamation[] reclams = new Reclamation[3];
    Dossier dossier;
    Remboursement[] rembs = new Remboursement[3];
    
    String[] args;
        
    @Before
    public void setUp() {
        
        traitement = new TraitementPrincipal(); 
                
        reclams[0] = new Reclamation("0", "2017-01-11", "10.00$");
        reclams[1] = new Reclamation( "175", "2017-01-14", "15.00$");
        reclams[2] = new Reclamation( "175", "2017-01-15", "20.00$");
        dossier = new Dossier("A100323", "2017-01", reclams, null, null); 
        
        rembs[0] = new Remboursement("A","0", "2017-01-11", "10.00$", new Mensuelle());
        rembs[1] = new Remboursement("A", "175", "2017-01-14", "15.00$", new Mensuelle());
        rembs[2] = new Remboursement("A", "175", "2017-01-15", "20.00$", new Mensuelle());
    }
    
    @After
    public void tearDown() {
    }

    @Test 
    public void testClassTraitementPrincipal() {       
        assertEquals(TraitementPrincipal.class, traitement.getClass());                
    }
    
    
    /**
     * Test de la méthode obtTabRemb de la  classe TraitementPrincipal.
     */
    @Test
    public void testObtTabRemb() throws Exception {
        System.out.println("obtTabRemb");

        Remboursement[] tabRemb = TraitementPrincipal.obtTabRemb(dossier);
        assertEquals(rembs[0].getMontant(), tabRemb[0].getMontant());
    }
    @Test
    public void testObtTabRembDate() throws Exception {
        System.out.println("obtTabRemb");

        Remboursement[] tabRemb = TraitementPrincipal.obtTabRemb(dossier);
        assertEquals(rembs[1].getDate(), tabRemb[1].getDate());
    }
    @Test
    public void testObtTabRembSoin() throws Exception {
        System.out.println("obtTabRemb");

        Remboursement[] tabRemb = TraitementPrincipal.obtTabRemb(dossier);
        assertEquals(rembs[2].getSoin(), tabRemb[2].getSoin());
    }
    
}
