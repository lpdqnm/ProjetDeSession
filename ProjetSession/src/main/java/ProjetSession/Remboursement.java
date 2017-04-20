package ProjetSession;

/**
 * Tout ce qui a rapport au remboursement
 *
 * @author Leopold Quenum
 * @author JP Rioux
 */
public class Remboursement {

    private static final String DOLLARS = "$";
    private String contrat;
    private String soin;
    private String date;
    private String montant;
    private Mensuelle mensuelle;

    public Remboursement(String contrat, String soin, String date, String montant,
            Mensuelle mensuelle) {
        this.contrat = contrat;
        this.soin = soin;
        this.date = date;
        this.mensuelle = mensuelle;
        setMontant(formatSoin(soin), montant);
    }

    protected int formatSoin(String soin) {
        try {
            return Integer.parseInt(soin);
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    public void setMontant(int soin, String montant) {
        if (soin == Soin.MASSOTHERAPIE) {
            this.montant = Dollar.IntVersString(this.getMontantMasso(this.contrat,
                    Dollar.StringVersInt(montant)));
        } else if (soin == Soin.OSTEOPATHIE) {
            this.montant = Dollar.IntVersString(this.getMontantOsteo(this.contrat,
                    Dollar.StringVersInt(montant)));
        } else if (soin == Soin.KINESITHERAPIE) {
            this.montant = Dollar.IntVersString(this.getMontantKine(this.contrat,
                    Dollar.StringVersInt(montant)));
        } else if (soin == Soin.MEDECIN_GENERALISTE_PRIVE) {
            this.montant = Dollar.IntVersString(this.getMontantMedGen(this.contrat,
                    Dollar.StringVersInt(montant)));
        } else if (soin >= Soin.MIN_SOIN_DENTAIRE && soin <= Soin.MAX_SOIN_DENTAIRE) {
            this.montant = Dollar.IntVersString(this.getMontantDentaire(this.contrat,
                    Dollar.StringVersInt(montant)));
        } else if (soin == Soin.PSYCHOLOGIE_INDIVIDUELLE) {
            this.montant = Dollar.IntVersString(this.getMontantPsycho(this.contrat,
                    Dollar.StringVersInt(montant)));
        } else if (soin == Soin.NATUROPATHIE_ACUPONCTURE) {
            this.montant = Dollar.IntVersString(this.getMontantNaturo(this.contrat,
                    Dollar.StringVersInt(montant)));
        } else if (soin == Soin.CHIROPRATIE) {
            this.montant = Dollar.IntVersString(this.getMontantChiro(this.contrat,
                    Dollar.StringVersInt(montant)));
        } else if (soin == Soin.PHYSIOTHERAPIE) {
            this.montant = Dollar.IntVersString(this.getMontantPhysio(this.contrat,
                    Dollar.StringVersInt(montant)));
        } else if (soin == Soin.ORTHOPHONIE_ERGOTHERAPIE) {
            this.montant = Dollar.IntVersString(this.getMontantOrtho(this.contrat,
                    Dollar.StringVersInt(montant)));
        } else {
            this.montant = Dollar.IntVersString( Dollar.StringVersInt(montant));
        }
    }

    private int getMontantMasso(String contrat, int montant) {
        int montantRemb = 0;

        if (contrat.equals(Contrat.A)) {
            montantRemb = Dollar.multiplication(montant, 0.25);
        } else if (contrat.equals(Contrat.B)) {
            montantRemb = Dollar.multiplication(montant, 0.5) < Dollar.StringVersInt("40.00$")
                    ? Dollar.multiplication(montant, 0.5) : Dollar.StringVersInt("40.00$");
        } else if (contrat.equals(Contrat.C)) {
            montantRemb = Dollar.multiplication(montant, 0.9);
        } else if (contrat.equals(Contrat.D)) {
            montantRemb = montant < Dollar.StringVersInt("85.00$") ? montant
                    : Dollar.StringVersInt("85.00$");
        } else if (contrat.equals(Contrat.E)) {
            montantRemb = Dollar.multiplication(montant, 0.15);
        }

        return montantRemb;
    }

    private int getMontantOsteo(String contrat, int montant) {
        int montantRemb = 0;

        if (contrat.equals(Contrat.A)) {
            montantRemb = Dollar.multiplication(montant, 0.35);
        } else if (contrat.equals(Contrat.B)) {
            montantRemb = Dollar.multiplication(montant, 0.5) < Dollar.StringVersInt("50.00$")
                    ? Dollar.multiplication(montant, 0.5) : Dollar.StringVersInt("50.00$");
        } else if (contrat.equals(Contrat.C)) {
            montantRemb = Dollar.multiplication(montant, 0.95);
        } else if (contrat.equals(Contrat.D)) {
            montantRemb = montant < Dollar.StringVersInt("75.00$") ? montant
                    : Dollar.StringVersInt("75.00$");
        } else if (contrat.equals(Contrat.E)) {
            montantRemb = Dollar.multiplication(montant, 0.25);
        }

        montantRemb = this.mensuelle.getMaxPourSoin(Soin.OSTEOPATHIE, montantRemb);
        this.mensuelle.additionerMontantPourSoin(Soin.OSTEOPATHIE, montantRemb);

        return montantRemb;
    }

    private int getMontantPsycho(String contrat, int montant) {
        int montantRemb = 0;

        if (contrat.equals(Contrat.A)) {
            montantRemb = Dollar.multiplication(montant, 0.25);
        } else if (contrat.equals(Contrat.B)) {
            montantRemb = montant;
        } else if (contrat.equals(Contrat.C)) {
            montantRemb = Dollar.multiplication(montant, 0.9);
        } else if (contrat.equals(Contrat.D)) {
            montantRemb = montant < Dollar.StringVersInt("100.00$") ? montant
                    : Dollar.StringVersInt("100.00$");
        } else if (contrat.equals(Contrat.E)) {
            montantRemb = Dollar.multiplication(montant, 0.12);
        }

        montantRemb = this.mensuelle.getMaxPourSoin(Soin.PSYCHOLOGIE_INDIVIDUELLE, montantRemb);
        this.mensuelle.additionerMontantPourSoin(Soin.PSYCHOLOGIE_INDIVIDUELLE, montantRemb);

        return montantRemb;
    }

    private int getMontantDentaire(String contrat, int montant) {
        int montantRemb = 0;

        if (contrat.equals(Contrat.A)) {
            montantRemb = 0;
        } else if (contrat.equals(Contrat.B)) {
            montantRemb = Dollar.multiplication(montant, 0.5);
        } else if (contrat.equals(Contrat.C)) {
            montantRemb = Dollar.multiplication(montant, 0.9);
        } else if (contrat.equals(Contrat.D)) {
            montantRemb = montant;
        } else if (contrat.equals(Contrat.E)) {
            montantRemb = Dollar.multiplication(montant, 0.6);
        }

        return montantRemb;
    }

    private int getMontantNaturo(String contrat, int montant) {
        int montantRemb = 0;

        if (contrat.equals(Contrat.A)) {
            montantRemb = 0;
        } else if (contrat.equals(Contrat.B)) {
            montantRemb = 0;
        } else if (contrat.equals(Contrat.C)) {
            montantRemb = Dollar.multiplication(montant, 0.9);
        } else if (contrat.equals(Contrat.D)) {
            montantRemb = montant < Dollar.StringVersInt("65.00$") ? montant
                    : Dollar.StringVersInt("65.00$");
        } else if (contrat.equals(Contrat.E)) {
            montantRemb = Dollar.multiplication(montant, 0.25) < Dollar.StringVersInt("15.00$")
                    ? Dollar.multiplication(montant, 0.25) : Dollar.StringVersInt("15.00$");
        }

        return montantRemb;
    }

    private int getMontantChiro(String contrat, int montant) {
        int montantRemb = 0;

        if (contrat.equals(Contrat.A)) {
            montantRemb = Dollar.multiplication(montant, 0.25);
        } else if (contrat.equals(Contrat.B)) {
            montantRemb = Dollar.multiplication(montant, 0.5) < Dollar.StringVersInt("50.00$")
                    ? Dollar.multiplication(montant, 0.5) : Dollar.StringVersInt("50.00$");
        } else if (contrat.equals(Contrat.C)) {
            montantRemb = Dollar.multiplication(montant, 0.9);
        } else if (contrat.equals(Contrat.D)) {
            montantRemb = montant;
        } else if (contrat.equals(Contrat.E)) {
            montantRemb = Dollar.multiplication(montant, 0.3) < Dollar.StringVersInt("20.00$")
                    ? Dollar.multiplication(montant, 0.3) : Dollar.StringVersInt("20.00$");
        }

        montantRemb = this.mensuelle.getMaxPourSoin(Soin.CHIROPRATIE, montantRemb);
        this.mensuelle.additionerMontantPourSoin(Soin.CHIROPRATIE, montantRemb);

        return montantRemb;
    }

    private int getMontantPhysio(String contrat, int montant) {
        int montantRemb = 0;

        if (contrat.equals(Contrat.A)) {
            montantRemb = Dollar.multiplication(montant, 0.4);
        } else if (contrat.equals(Contrat.B)) {
            montantRemb = montant;
        } else if (contrat.equals(Contrat.C)) {
            montantRemb = Dollar.multiplication(montant, 0.75);
        } else if (contrat.equals(Contrat.D)) {
            montantRemb = montant < Dollar.StringVersInt("100.00$") ? montant
                    : Dollar.StringVersInt("100.00$");
        } else if (contrat.equals(Contrat.E)) {
            montantRemb = Dollar.multiplication(montant, 0.15);
        }

        montantRemb = this.mensuelle.getMaxPourSoin(Soin.PHYSIOTHERAPIE, montantRemb);
        this.mensuelle.additionerMontantPourSoin(Soin.PHYSIOTHERAPIE, montantRemb);

        return montantRemb;
    }

    private int getMontantOrtho(String contrat, int montant) {
        int montantRemb = 0;

        if (contrat.equals(Contrat.A)) {
            montantRemb = 0;
        } else if (contrat.equals(Contrat.B)) {
            montantRemb = Dollar.multiplication(montant, 0.7);
        } else if (contrat.equals(Contrat.C)) {
            montantRemb = Dollar.multiplication(montant, 0.9);
        } else if (contrat.equals(Contrat.D)) {
            montantRemb = montant < Dollar.StringVersInt("90.00$") ? montant
                    : Dollar.StringVersInt("90.00$");
        } else if (contrat.equals(Contrat.E)) {
            montantRemb = Dollar.multiplication(montant, 0.22);
        }

        return montantRemb;
    }

    private int getMontantKine(String contrat, int montant) {
        int montantRemb = 0;

        if (contrat.equals(Contrat.A) || contrat.equals(Contrat.B)) {
            montantRemb = 0;
        } else if (contrat.equals(Contrat.C)) {
            montantRemb = Dollar.multiplication(montant, 0.85);
        } else if (contrat.equals(Contrat.D)) {
            montantRemb = montant < Dollar.StringVersInt("150.00$") ? montant
                    : Dollar.StringVersInt("150.00$");
        } else if (contrat.equals(Contrat.E)) {
            montantRemb = Dollar.multiplication(montant, 0.15);
        }

        return montantRemb;
    }

    private int getMontantMedGen(String contrat, int montant) {
        int montantRemb = 0;

        if (contrat.equals(Contrat.A)) {
            montantRemb = Dollar.multiplication(montant, 0.5);
        } else if (contrat.equals(Contrat.B)) {
            montantRemb = Dollar.multiplication(montant, 0.75);
        } else if (contrat.equals(Contrat.C)) {
            montantRemb = Dollar.multiplication(montant, 0.9);
        } else if (contrat.equals(Contrat.D)) {
            montantRemb = Dollar.multiplication(montant, 0.95);
        } else if (contrat.equals(Contrat.E)) {
            montantRemb = Dollar.multiplication(montant, 0.25) < Dollar.StringVersInt("20.00$")
                    ? Dollar.multiplication(montant, 0.25) : Dollar.StringVersInt("20.00$");
        }

        montantRemb = this.mensuelle.getMaxPourSoin(Soin.MEDECIN_GENERALISTE_PRIVE, montantRemb);
        this.mensuelle.additionerMontantPourSoin(Soin.MEDECIN_GENERALISTE_PRIVE, montantRemb);
        return montantRemb;
    }

    public String getSoin() {
        return soin;
    }

    public String getDate() {
        return date;
    }

    public String getMontant() {
        return montant + DOLLARS;
    }
}
