package ProjetSession;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Leopold Quenum
 * @author JP Rioux
 */
public class DossierTest {

    private static Dossier dossierValide;
    private static Dossier dossierValideReclamInvalide;
    private static Dossier[] tabDossierInvalide_NumeroClient;
    private static Dossier[] tabDossierInvalide_Contrat;
    private static Dossier[] tabDossierInvalideReclam;
    private static Reclamation[] tabReclamValide;
    private static Reclamation[] tabReclamInvalide;
    private static Reclamation[] tabReclamInvalide_NbSoinsJours;
    private static Remboursement[] tabRemboursementValide;

    @Before
    public void setUp() {

        Mensuelle mens = new Mensuelle();
        tabReclamValide = new Reclamation[4];
        tabReclamValide[0] = new Reclamation("200", "2017-01-11", "69.00$");
        tabReclamValide[1] = new Reclamation("200", "2017-01-11", "69.00$");
        tabReclamValide[2] = new Reclamation("200", "2017-01-11", "69.00$");
        tabReclamValide[3] = new Reclamation("200", "2017-01-11", "69.00$");

        tabReclamInvalide = new Reclamation[1];
        tabReclamInvalide[0] = new Reclamation("200", "2017-01-11", "abc");

        tabRemboursementValide = new Remboursement[4];
        tabRemboursementValide[0] = new Remboursement("E", "200", "2017-01-11", "69.00$", mens);
        tabRemboursementValide[1] = new Remboursement("E", "200", "2017-01-11", "69.00$", mens);
        tabRemboursementValide[2] = new Remboursement("E", "200", "2017-01-11", "69.00$", mens);
        tabRemboursementValide[3] = new Remboursement("E", "200", "2017-01-11", "69.00$", mens);

        tabReclamInvalide_NbSoinsJours = new Reclamation[5];
        tabReclamInvalide_NbSoinsJours[0] = new Reclamation("200", "2017-01-11", "69.00$");
        tabReclamInvalide_NbSoinsJours[1] = new Reclamation("200", "2017-01-11", "69.00$");
        tabReclamInvalide_NbSoinsJours[2] = new Reclamation("200", "2017-01-11", "69.00$");
        tabReclamInvalide_NbSoinsJours[3] = new Reclamation("200", "2017-01-11", "69.00$");
        tabReclamInvalide_NbSoinsJours[4] = new Reclamation("200", "2017-01-11", "69.00$");

        dossierValide = new Dossier("E100323", "2017-01", tabReclamValide, tabRemboursementValide,
                null);

        tabDossierInvalide_NumeroClient = new Dossier[5];
        tabDossierInvalide_NumeroClient[0] = new Dossier("E10323", "2017-01", tabReclamValide, 
                null, null);
        tabDossierInvalide_NumeroClient[1] = new Dossier("E10032311", "2017-01", tabReclamValide, 
                null, null);
        tabDossierInvalide_NumeroClient[2] = new Dossier("E10032A", "2017-01", tabReclamValide, 
                null, null);
        tabDossierInvalide_NumeroClient[3] = new Dossier("EA00323", "2017-01", tabReclamValide, 
                null, null);
        tabDossierInvalide_NumeroClient[4] = new Dossier("E1003231A", "2017-01", tabReclamValide, 
                null, null);

        tabDossierInvalide_Contrat = new Dossier[2];
        tabDossierInvalide_Contrat[0] = new Dossier("Z100323", "2017-01", tabReclamValide, 
                null, null);
        tabDossierInvalide_Contrat[1] = new Dossier("1100323", "2017-01", tabReclamValide, 
                null, null);

        tabDossierInvalideReclam = new Dossier[1];
        tabDossierInvalideReclam[0] = new Dossier("E100323", "2017-01", 
                tabReclamInvalide_NbSoinsJours, null, null);

        dossierValideReclamInvalide = new Dossier("E100323", "2017-01", 
                tabReclamInvalide, null, null);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testEstValide() {
        assertTrue(dossierValide.estValide());
    }

    @Test
    public void testEstValideReclamInvalide() {
        assertFalse(dossierValideReclamInvalide.estValide());
    }

    @Test
    public void testEstValideNumeroClient() {
        for (int i = 0; i < tabDossierInvalide_NumeroClient.length; i++) {
            assertFalse(tabDossierInvalide_NumeroClient[i].estValide());
        }
    }

    @Test
    public void testEstValideContrat() {
        for (int i = 0; i < tabDossierInvalide_Contrat.length; i++) {
            assertFalse(tabDossierInvalide_Contrat[i].estValide());
        }
    }

    @Test
    public void TestEstValideNbSoinsJours() {
        for (int i = 0; i < tabDossierInvalideReclam.length; i++) {
            assertFalse(tabDossierInvalideReclam[i].estValide());
        }
    }

    @Test
    public void TestGetContrat() {
        assertEquals(dossierValide.getContrat(), "E");
    }

    @Test
    public void TestGetDossierClient() {
        assertEquals(dossierValide.getDossierClient(), "E100323");
    }

    @Test
    public void TestGetNumeroClient() {
        assertEquals(dossierValide.getNumeroClient(), "100323");
    }

    @Test
    public void TestGetMois() {
        assertEquals(dossierValide.getMois(), "2017-01");
    }

    @Test
    public void TestSetGetRemboursements() {
        dossierValide.setRemboursements(tabRemboursementValide);
        Remboursement[] rembTemp = dossierValide.getRemboursements();
        for (int i = 0; i < tabRemboursementValide.length; i++) {
            assertEquals(tabRemboursementValide[i], rembTemp[i]);
        }

    }

    @Test
    public void TestSetGetTotal() {
        dossierValide.setTotal();
        assertEquals(dossierValide.getTotal(), "33.12$");

        dossierValideReclamInvalide.setTotal();
        assertEquals(dossierValideReclamInvalide.getTotal(), "0.00$");
    }

    @Test
    public void TestSetGetErreur() {
        dossierValide.setErreur("allo");
        assertEquals(dossierValide.getErreur(), "allo");
    }
}
