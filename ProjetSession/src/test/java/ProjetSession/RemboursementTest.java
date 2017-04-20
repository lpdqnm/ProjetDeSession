package ProjetSession;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jprioux
 */
public class RemboursementTest {
    
    Remboursement rembValide;
    Remboursement rembValideChiro;
    Remboursement rembValideKine;
    Remboursement rembValideMasso;
    Remboursement rembValideDent;
    Remboursement rembValideMed;
    Remboursement rembValideNat;
    Remboursement rembValideOrtho;
    Remboursement rembValideOsteo;
    Remboursement rembValidePhysio;
    Remboursement rembValidePsycho;
    Remboursement rembValideContratInexistant;
    Remboursement tabRemValide_montant[];
    Remboursement tabRembMasso[];    
    String tabRembMassoAttendu[];
    Remboursement tabRembOsteo[];    
    String tabRembOsteoAttendu[];
    Remboursement tabRembPsycho[];    
    String tabRembPsychoAttendu[];
    Remboursement tabRembDent[];    
    String tabRembDentAttendu[];
    Remboursement tabRembNaturo[];    
    String tabRembNaturoAttendu[];
    Remboursement tabRembChiro[];    
    String tabRembChiroAttendu[];
    Remboursement tabRembPhysio[];    
    String tabRembPhysioAttendu[];
    Remboursement tabRembKine[];    
    String tabRembKineAttendu[];
    Remboursement tabRembOrtho[];    
    String tabRembOrthoAttendu[];
    Remboursement tabRembMed[];    
    String tabRembMedAttendu[];    
    
    static final String REMB_CONTRAT_E_SOIN_CHIRO = "20.00$";
    static final String REMB_CONTRAT_E_SOIN_KINE = "15.00$";
    static final String REMB_CONTRAT_E_SOIN_MASSO = "15.00$";
    static final String REMB_CONTRAT_E_SOIN_DENT = "60.00$";
    static final String REMB_CONTRAT_E_SOIN_MED = "20.00$";
    static final String REMB_CONTRAT_E_SOIN_NAT = "15.00$";
    static final String REMB_CONTRAT_E_SOIN_ORTHO = "22.00$";
    static final String REMB_CONTRAT_E_SOIN_OSTEO = "25.00$";
    static final String REMB_CONTRAT_E_SOIN_PHYSIO = "15.00$";
    static final String REMB_CONTRAT_E_SOIN_PSYCHO = "12.00$";
    static final String REMB_CONTRAT_INEXISTANT = "0.00$";
    
    
    
    
    
    @Before
    public void setUp() {
        Mensuelle mens = new Mensuelle(); 
        
        rembValide = new Remboursement("E","200","2017-01-11","100.00$",mens);
                
        rembValideChiro = new Remboursement("E",String.valueOf(Soin.CHIROPRATIE),"2017-01-11","100.00$",mens);
        rembValideKine = new Remboursement("E",String.valueOf(Soin.KINESITHERAPIE),"2017-01-11","100.00$",mens);
        rembValideMasso = new Remboursement("E",String.valueOf(Soin.MASSOTHERAPIE),"2017-01-11","100.00$",mens);
        rembValideDent = new Remboursement("E",String.valueOf(Soin.MAX_SOIN_DENTAIRE),"2017-01-11","100.00$",mens);
        rembValideMed = new Remboursement("E",String.valueOf(Soin.MEDECIN_GENERALISTE_PRIVE),"2017-01-11","100.00$",mens);
        rembValideNat = new Remboursement("E",String.valueOf(Soin.NATUROPATHIE_ACUPONCTURE),"2017-01-11","100.00$",mens);
        rembValideOrtho = new Remboursement("E",String.valueOf(Soin.ORTHOPHONIE_ERGOTHERAPIE),"2017-01-11","100.00$",mens);
        rembValideOsteo = new Remboursement("E",String.valueOf(Soin.OSTEOPATHIE),"2017-01-11","100.00$",mens);
        rembValidePhysio = new Remboursement("E",String.valueOf(Soin.PHYSIOTHERAPIE),"2017-01-11","100.00$",mens);
        rembValidePsycho = new Remboursement("E",String.valueOf(Soin.PSYCHOLOGIE_INDIVIDUELLE),"2017-01-11","100.00$",mens);
        rembValideContratInexistant = new Remboursement("Z",String.valueOf(Soin.CHIROPRATIE),"2017-01-11","100.00$",mens);
        
        tabRembMasso = new Remboursement[7];
        tabRembMassoAttendu = new String[7];
        tabRembMasso[0] = new Remboursement("A",String.valueOf(Soin.MASSOTHERAPIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembMassoAttendu[0] = "25.00$";
        tabRembMasso[1] = new Remboursement("B",String.valueOf(Soin.MASSOTHERAPIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembMassoAttendu[1] = "40.00$";
        tabRembMasso[2] = new Remboursement("B",String.valueOf(Soin.MASSOTHERAPIE),"2017-01-11","10.00$",new Mensuelle());
        tabRembMassoAttendu[2] = "5.00$";
        tabRembMasso[3] = new Remboursement("C",String.valueOf(Soin.MASSOTHERAPIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembMassoAttendu[3] = "90.00$";
        tabRembMasso[4] = new Remboursement("D",String.valueOf(Soin.MASSOTHERAPIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembMassoAttendu[4] = "85.00$";
        tabRembMasso[5] = new Remboursement("D",String.valueOf(Soin.MASSOTHERAPIE),"2017-01-11","10.00$",new Mensuelle());
        tabRembMassoAttendu[5] = "10.00$";
        tabRembMasso[6] = new Remboursement("E",String.valueOf(Soin.MASSOTHERAPIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembMassoAttendu[6] = "15.00$";
        
        tabRembOsteo = new Remboursement[7];
        tabRembOsteoAttendu = new String[7];
        tabRembOsteo[0] = new Remboursement("A",String.valueOf(Soin.OSTEOPATHIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembOsteoAttendu[0] = "35.00$";
        tabRembOsteo[1] = new Remboursement("B",String.valueOf(Soin.OSTEOPATHIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembOsteoAttendu[1] = "50.00$";
        tabRembOsteo[2] = new Remboursement("B",String.valueOf(Soin.OSTEOPATHIE),"2017-01-11","10.00$",new Mensuelle());
        tabRembOsteoAttendu[2] = "5.00$";
        tabRembOsteo[3] = new Remboursement("C",String.valueOf(Soin.OSTEOPATHIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembOsteoAttendu[3] = "95.00$";
        tabRembOsteo[4] = new Remboursement("D",String.valueOf(Soin.OSTEOPATHIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembOsteoAttendu[4] = "75.00$";
        tabRembOsteo[5] = new Remboursement("D",String.valueOf(Soin.OSTEOPATHIE),"2017-01-11","10.00$",new Mensuelle());
        tabRembOsteoAttendu[5] = "10.00$";
        tabRembOsteo[6] = new Remboursement("E",String.valueOf(Soin.OSTEOPATHIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembOsteoAttendu[6] = "25.00$";
        
        tabRembPsycho = new Remboursement[6];
        tabRembPsychoAttendu = new String[6];
        tabRembPsycho[0] = new Remboursement("A",String.valueOf(Soin.PSYCHOLOGIE_INDIVIDUELLE),"2017-01-11","100.00$",new Mensuelle());
        tabRembPsychoAttendu[0] = "25.00$";
        tabRembPsycho[1] = new Remboursement("B",String.valueOf(Soin.PSYCHOLOGIE_INDIVIDUELLE),"2017-01-11","100.00$",new Mensuelle());
        tabRembPsychoAttendu[1] = "100.00$";
        tabRembPsycho[2] = new Remboursement("C",String.valueOf(Soin.PSYCHOLOGIE_INDIVIDUELLE),"2017-01-11","100.00$",new Mensuelle());
        tabRembPsychoAttendu[2] = "90.00$";
        tabRembPsycho[3] = new Remboursement("D",String.valueOf(Soin.PSYCHOLOGIE_INDIVIDUELLE),"2017-01-11","110.00$",new Mensuelle());
        tabRembPsychoAttendu[3] = "100.00$";
        tabRembPsycho[4] = new Remboursement("D",String.valueOf(Soin.PSYCHOLOGIE_INDIVIDUELLE),"2017-01-11","10.00$",new Mensuelle());
        tabRembPsychoAttendu[4] = "10.00$";
        tabRembPsycho[5] = new Remboursement("E",String.valueOf(Soin.PSYCHOLOGIE_INDIVIDUELLE),"2017-01-11","100.00$",new Mensuelle());
        tabRembPsychoAttendu[5] = "12.00$";
        
        tabRembDent = new Remboursement[5];
        tabRembDentAttendu = new String[5];
        tabRembDent[0] = new Remboursement("A",String.valueOf(Soin.MAX_SOIN_DENTAIRE),"2017-01-11","100.00$",new Mensuelle());
        tabRembDentAttendu[0] = "0.00$";
        tabRembDent[1] = new Remboursement("B",String.valueOf(Soin.MAX_SOIN_DENTAIRE),"2017-01-11","100.00$",new Mensuelle());
        tabRembDentAttendu[1] = "50.00$";
        tabRembDent[2] = new Remboursement("C",String.valueOf(Soin.MAX_SOIN_DENTAIRE),"2017-01-11","100.00$",new Mensuelle());
        tabRembDentAttendu[2] = "90.00$";
        tabRembDent[3] = new Remboursement("D",String.valueOf(Soin.MAX_SOIN_DENTAIRE),"2017-01-11","100.00$",new Mensuelle());
        tabRembDentAttendu[3] = "100.00$";
        tabRembDent[4] = new Remboursement("E",String.valueOf(Soin.MAX_SOIN_DENTAIRE),"2017-01-11","100.00$",new Mensuelle());
        tabRembDentAttendu[4] = "60.00$";
        
        tabRembNaturo = new Remboursement[7];
        tabRembNaturoAttendu = new String[7];
        tabRembNaturo[0] = new Remboursement("A",String.valueOf(Soin.NATUROPATHIE_ACUPONCTURE),"2017-01-11","100.00$",new Mensuelle());
        tabRembNaturoAttendu[0] = "0.00$";
        tabRembNaturo[1] = new Remboursement("B",String.valueOf(Soin.NATUROPATHIE_ACUPONCTURE),"2017-01-11","100.00$",new Mensuelle());
        tabRembNaturoAttendu[1] = "0.00$";
        tabRembNaturo[2] = new Remboursement("C",String.valueOf(Soin.NATUROPATHIE_ACUPONCTURE),"2017-01-11","100.00$",new Mensuelle());
        tabRembNaturoAttendu[2] = "90.00$";
        tabRembNaturo[3] = new Remboursement("D",String.valueOf(Soin.NATUROPATHIE_ACUPONCTURE),"2017-01-11","100.00$",new Mensuelle());
        tabRembNaturoAttendu[3] = "65.00$";
        tabRembNaturo[4] = new Remboursement("D",String.valueOf(Soin.NATUROPATHIE_ACUPONCTURE),"2017-01-11","10.00$",new Mensuelle());
        tabRembNaturoAttendu[4] = "10.00$";
        tabRembNaturo[5] = new Remboursement("E",String.valueOf(Soin.NATUROPATHIE_ACUPONCTURE),"2017-01-11","100.00$",new Mensuelle());
        tabRembNaturoAttendu[5] = "15.00$";
        tabRembNaturo[6] = new Remboursement("E",String.valueOf(Soin.NATUROPATHIE_ACUPONCTURE),"2017-01-11","10.00$",new Mensuelle());
        tabRembNaturoAttendu[6] = "2.50$";
        
        tabRembChiro = new Remboursement[7];
        tabRembChiroAttendu = new String[7];
        tabRembChiro[0] = new Remboursement("A",String.valueOf(Soin.CHIROPRATIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembChiroAttendu[0] = "25.00$";
        tabRembChiro[1] = new Remboursement("B",String.valueOf(Soin.CHIROPRATIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembChiroAttendu[1] = "50.00$";
        tabRembChiro[2] = new Remboursement("B",String.valueOf(Soin.CHIROPRATIE),"2017-01-11","10.00$",new Mensuelle());
        tabRembChiroAttendu[2] = "5.00$";        
        tabRembChiro[3] = new Remboursement("C",String.valueOf(Soin.CHIROPRATIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembChiroAttendu[3] = "90.00$";
        tabRembChiro[4] = new Remboursement("D",String.valueOf(Soin.CHIROPRATIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembChiroAttendu[4] = "100.00$";
        tabRembChiro[5] = new Remboursement("E",String.valueOf(Soin.CHIROPRATIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembChiroAttendu[5] = "20.00$";
        tabRembChiro[6] = new Remboursement("E",String.valueOf(Soin.CHIROPRATIE),"2017-01-11","10.00$",new Mensuelle());
        tabRembChiroAttendu[6] = "3.00$";
        
        tabRembPhysio = new Remboursement[6];
        tabRembPhysioAttendu = new String[6];
        tabRembPhysio[0] = new Remboursement("A",String.valueOf(Soin.PHYSIOTHERAPIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembPhysioAttendu[0] = "40.00$";
        tabRembPhysio[1] = new Remboursement("B",String.valueOf(Soin.PHYSIOTHERAPIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembPhysioAttendu[1] = "100.00$";       
        tabRembPhysio[2] = new Remboursement("C",String.valueOf(Soin.PHYSIOTHERAPIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembPhysioAttendu[2] = "75.00$";
        tabRembPhysio[3] = new Remboursement("D",String.valueOf(Soin.PHYSIOTHERAPIE),"2017-01-11","110.00$",new Mensuelle());
        tabRembPhysioAttendu[3] = "100.00$";
        tabRembPhysio[4] = new Remboursement("D",String.valueOf(Soin.PHYSIOTHERAPIE),"2017-01-11","10.00$",new Mensuelle());
        tabRembPhysioAttendu[4] = "10.00$";
        tabRembPhysio[5] = new Remboursement("E",String.valueOf(Soin.PHYSIOTHERAPIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembPhysioAttendu[5] = "15.00$";
        
        tabRembOrtho = new Remboursement[6];
        tabRembOrthoAttendu = new String[6];
        tabRembOrtho[0] = new Remboursement("A",String.valueOf(Soin.ORTHOPHONIE_ERGOTHERAPIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembOrthoAttendu[0] = "0.00$";
        tabRembOrtho[1] = new Remboursement("B",String.valueOf(Soin.ORTHOPHONIE_ERGOTHERAPIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembOrthoAttendu[1] = "70.00$";       
        tabRembOrtho[2] = new Remboursement("C",String.valueOf(Soin.ORTHOPHONIE_ERGOTHERAPIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembOrthoAttendu[2] = "90.00$";
        tabRembOrtho[3] = new Remboursement("D",String.valueOf(Soin.ORTHOPHONIE_ERGOTHERAPIE),"2017-01-11","110.00$",new Mensuelle());
        tabRembOrthoAttendu[3] = "90.00$";
        tabRembOrtho[4] = new Remboursement("D",String.valueOf(Soin.ORTHOPHONIE_ERGOTHERAPIE),"2017-01-11","10.00$",new Mensuelle());
        tabRembOrthoAttendu[4] = "10.00$";
        tabRembOrtho[5] = new Remboursement("E",String.valueOf(Soin.ORTHOPHONIE_ERGOTHERAPIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembOrthoAttendu[5] = "22.00$";
        
        tabRembKine = new Remboursement[6];
        tabRembKineAttendu = new String[6];
        tabRembKine[0] = new Remboursement("A",String.valueOf(Soin.KINESITHERAPIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembKineAttendu[0] = "0.00$";
        tabRembKine[1] = new Remboursement("B",String.valueOf(Soin.KINESITHERAPIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembKineAttendu[1] = "0.00$";       
        tabRembKine[2] = new Remboursement("C",String.valueOf(Soin.KINESITHERAPIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembKineAttendu[2] = "85.00$";
        tabRembKine[3] = new Remboursement("D",String.valueOf(Soin.KINESITHERAPIE),"2017-01-11","170.00$",new Mensuelle());
        tabRembKineAttendu[3] = "150.00$";
        tabRembKine[4] = new Remboursement("D",String.valueOf(Soin.KINESITHERAPIE),"2017-01-11","10.00$",new Mensuelle());
        tabRembKineAttendu[4] = "10.00$";
        tabRembKine[5] = new Remboursement("E",String.valueOf(Soin.KINESITHERAPIE),"2017-01-11","100.00$",new Mensuelle());
        tabRembKineAttendu[5] = "15.00$";
        
        tabRembMed = new Remboursement[6];
        tabRembMedAttendu = new String[6];
        tabRembMed[0] = new Remboursement("A",String.valueOf(Soin.MEDECIN_GENERALISTE_PRIVE),"2017-01-11","100.00$",new Mensuelle());
        tabRembMedAttendu[0] = "50.00$";
        tabRembMed[1] = new Remboursement("B",String.valueOf(Soin.MEDECIN_GENERALISTE_PRIVE),"2017-01-11","100.00$",new Mensuelle());
        tabRembMedAttendu[1] = "75.00$";       
        tabRembMed[2] = new Remboursement("C",String.valueOf(Soin.MEDECIN_GENERALISTE_PRIVE),"2017-01-11","100.00$",new Mensuelle());
        tabRembMedAttendu[2] = "90.00$";
        tabRembMed[3] = new Remboursement("D",String.valueOf(Soin.MEDECIN_GENERALISTE_PRIVE),"2017-01-11","100.00$",new Mensuelle());
        tabRembMedAttendu[3] = "95.00$";
        tabRembMed[4] = new Remboursement("E",String.valueOf(Soin.MEDECIN_GENERALISTE_PRIVE),"2017-01-11","100.00$",new Mensuelle());
        tabRembMedAttendu[4] = "20.00$"; 
        tabRembMed[5] = new Remboursement("E",String.valueOf(Soin.MEDECIN_GENERALISTE_PRIVE),"2017-01-11","10.00$",new Mensuelle());
        tabRembMedAttendu[5] = "2.50$";        
        
        
 
        
      
    }
    
    @After
    public void tearDown() {
    }   
    
            
    @Test
    public void testFormatSoin(){  
        assertEquals(rembValide.formatSoin("100"),100);
        assertEquals(rembValide.formatSoin("abc"),-1);
    }
    
    @Test
    public void testSetGetMontant(){  
        assertEquals(rembValideChiro.getMontant(),REMB_CONTRAT_E_SOIN_CHIRO);
        assertEquals(rembValideKine.getMontant(),REMB_CONTRAT_E_SOIN_KINE);
        assertEquals(rembValideMasso.getMontant(),REMB_CONTRAT_E_SOIN_MASSO);
        assertEquals(rembValideDent.getMontant(),REMB_CONTRAT_E_SOIN_DENT);
        assertEquals(rembValideMed.getMontant(),REMB_CONTRAT_E_SOIN_MED);
        assertEquals(rembValideNat.getMontant(),REMB_CONTRAT_E_SOIN_NAT);
        assertEquals(rembValideOrtho.getMontant(),REMB_CONTRAT_E_SOIN_ORTHO);
        assertEquals(rembValideOsteo.getMontant(),REMB_CONTRAT_E_SOIN_OSTEO);
        assertEquals(rembValidePhysio.getMontant(),REMB_CONTRAT_E_SOIN_PHYSIO);
        assertEquals(rembValidePsycho.getMontant(),REMB_CONTRAT_E_SOIN_PSYCHO);     
       rembValideContratInexistant.setMontant(9999, REMB_CONTRAT_E_SOIN_NAT);
        assertEquals(rembValideContratInexistant.getMontant(),REMB_CONTRAT_E_SOIN_NAT);
    }
    
    @Test
    public void testGetMontantMasso(){  
        for (int i = 0; i < tabRembMasso.length; i++) {
            assertEquals(tabRembMasso[i].getMontant(),tabRembMassoAttendu[i]);
        }
    }
    
    @Test
    public void testGetMontantOsteo(){  
        for (int i = 0; i < tabRembOsteo.length; i++) {
            assertEquals(tabRembOsteo[i].getMontant(),tabRembOsteoAttendu[i]);
        }
    }

    @Test
    public void testGetMontantPsycho(){  
        for (int i = 0; i < tabRembPsycho.length; i++) {
            assertEquals(tabRembPsycho[i].getMontant(),tabRembPsychoAttendu[i]);
        }
    } 
    
    @Test
    public void testGetMontantDent(){  
        for (int i = 0; i < tabRembDent.length; i++) {
            assertEquals(tabRembDent[i].getMontant(),tabRembDentAttendu[i]);
        }
    }
    
    @Test
    public void testGetMontantNaturo(){  
        for (int i = 0; i < tabRembNaturo.length; i++) {
            assertEquals(tabRembNaturo[i].getMontant(),tabRembNaturoAttendu[i]);
        }
    }
    
    @Test
    public void testGetMontantChiro(){  
        for (int i = 0; i < tabRembChiro.length; i++) {
            assertEquals(tabRembChiro[i].getMontant(),tabRembChiroAttendu[i]);
        }
    }
    
    @Test
    public void testGetMontantPhysio(){  
        for (int i = 0; i < tabRembPhysio.length; i++) {
            assertEquals(tabRembPhysio[i].getMontant(),tabRembPhysioAttendu[i]);
        }
    }
    
    @Test
    public void testGetMontantOrtho(){  
        for (int i = 0; i < tabRembOrtho.length; i++) {
            assertEquals(tabRembOrtho[i].getMontant(),tabRembOrthoAttendu[i]);
        }
    }
    
    @Test
    public void testGetMontantKine(){  
        for (int i = 0; i < tabRembKine.length; i++) {
            assertEquals(tabRembKine[i].getMontant(),tabRembKineAttendu[i]);
        }
    }
    
    @Test
    public void testGetMontantMed(){  
        for (int i = 0; i < tabRembMed.length; i++) {
            assertEquals(tabRembMed[i].getMontant(),tabRembMedAttendu[i]);
        }
    }
    

    
    
    
    
}
