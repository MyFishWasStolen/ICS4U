import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SpinnerSimulator {

static final String[] OUTCOMES = {"Miss", "Bronze", "Silver", "Gold", "Jackpot"};
static final int[]    PRIZES   = {0, 1, 3, 5, 10};
static final double[] CUM_PROB = {0.40, 0.70, 0.88, 0.97, 1.00};

static final Random random = new Random();

// Returns outcome index 1–5
public static int spin() {
    double roll = random.nextDouble();
    for (int i = 0; i < CUM_PROB.length; i++) {
        if (roll < CUM_PROB[i]) return i + 1;
    }
    return CUM_PROB.length;
}

public static String getOutcomeName(int spinResult) {
    return OUTCOMES[spinResult - 1];
}

public static int getPrize(int spinResult) {
    return PRIZES[spinResult - 1];
}

public static void main(String[] args) {
    final int TRIALS = 100;
    final double COST = 2.00;
    String filename = "SpinResults.csv";

    // Arrays to track results
    int[]    results      = new int[TRIALS];
    String[] outcomeNames = new String[TRIALS];
    int[]    prizes       = new int[TRIALS];
    int[]    freqCount    = new int[5];

    // Run 100 spins
    System.out.println("=== Spin & Win — 100 Trial Simulation ===\n");
    for (int i = 0; i < TRIALS; i++) {
        int result = spin();
        results[i]      = result;
        outcomeNames[i] = getOutcomeName(result);
        prizes[i]       = getPrize(result);
        freqCount[result - 1]++;

        System.out.printf("Spin %3d → Outcome %d: %-8s | Prize: $%d%n",
                i + 1, result, outcomeNames[i], prizes[i]);
    }

    // Console summary
    System.out.println("\n--- Summary ---");
    double totalPrize = 0;
    for (int p : prizes) totalPrize += p;
    double expEV     = totalPrize / TRIALS;
    double profit    = (COST * TRIALS) - totalPrize;

    System.out.printf("Total Prize Paid Out : $%.2f%n", totalPrize);
    System.out.printf("Experimental E(X)   : $%.4f%n", expEV);
    System.out.printf("Total Revenue       : $%.2f%n", COST * TRIALS);
    System.out.printf("Net Profit          : $%.2f%n", profit);

    // Export to CSV
    try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {

        // ── Sheet Section 1: Raw spin data ──
        pw.println("SPIN RESULTS - Spin & Win (100 Trials)");
        pw.println();
        pw.println("Spin #,Outcome Number (1-5),Outcome Name,Prize ($),Cost ($),Net (Player)");
        for (int i = 0; i < TRIALS; i++) {
            int net = prizes[i] - (int) COST; // player's net gain/loss
            pw.printf("%d,%d,%s,%d,%.0f,%d%n",
                    i + 1, results[i], outcomeNames[i], prizes[i], COST, net);
        }

        pw.println();
        pw.println("--- FREQUENCY SUMMARY ---");
        pw.println("Outcome Number,Outcome Name,Prize ($),Frequency,Exp. Probability,Theoretical Probability,x * P(x)");

        double[] theoProbabilities = {0.40, 0.30, 0.18, 0.09, 0.03};
        for (int i = 0; i < 5; i++) {
            double expProb  = freqCount[i] / (double) TRIALS;
            double xTimesP  = expProb * PRIZES[i];
            pw.printf("%d,%s,%d,%d,%.4f,%.2f,%.4f%n",
                    i + 1, OUTCOMES[i], PRIZES[i],
                    freqCount[i], expProb, theoProbabilities[i], xTimesP);
        }

        pw.println();
        pw.println("--- EXPECTED VALUE & PROFITABILITY ---");
        pw.printf("Experimental E(X) (avg prize per play),%.4f%n", expEV);
        pw.printf("Theoretical E(X),1.5900%n");
        pw.printf("Cost per Play,$%.2f%n", COST);
        pw.printf("Total Revenue,$%.2f%n", COST * TRIALS);
        pw.printf("Total Prizes Paid,$%.2f%n", totalPrize);
        pw.printf("Net Profit,$%.2f%n", profit);

    } catch (IOException e) {
        System.err.println("Error writing CSV: " + e.getMessage());
        return;
    }

    System.out.println("\nResults exported to: " + filename);
    System.out.println("Open in Excel or Google Sheets to view the full table.");
}


}