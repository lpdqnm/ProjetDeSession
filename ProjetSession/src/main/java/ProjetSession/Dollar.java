package ProjetSession;

/**
 * Classe servant au traitement de monnaie pour éviter la perte de précision
 *
 * @author Leopold Quenum
 * @author JP Rioux
 */
public final class Dollar {

    public static int StringVersInt(String montant) {
        double montantReclame;
        int montantFormat;

        try {
            if (montant == null) {
                montant = "0";
            }
            montantReclame = Double.parseDouble(montant.replace("$", "").replace(",", "."));
        } catch (NumberFormatException ex) {
            montantReclame = 0.00;
        }

        //Un montant en cents
        montantFormat = (int) Math.round(montantReclame * 100);

        return montantFormat;
    }

    public static String IntVersString(int montantEnCents) {
        if (montantEnCents < 0) {
            montantEnCents = 0;
        }

        String cents = String.valueOf(montantEnCents % 100);

        if (cents.length() < 2) {
            cents = cents + "0";
        }
        // ex: Entre 550 --> sort 5.50       
        return (montantEnCents / 100) + "." + cents;
    }

    public static int multiplication(int montantEnCents, double multiplicateur) {
        int dollar;
        int cents;

        if (montantEnCents < 0) {
            montantEnCents = 0;
        }

        dollar = (int) Math.round(((montantEnCents * multiplicateur) / 100));
        cents = (int) Math.round((montantEnCents * multiplicateur) % 100);

        return (dollar * 100) + cents;
    }
}
