import java.util.Scanner;

class PiCalculator {

    // Private data member
    private double piValue;

    // Public method to calculate Pi
    public void calculatePi(int terms) {
        piValue = 0.0;

        for (int i = 0; i < terms; i++) {
            if (i % 2 == 0)
                piValue += 4.0 / (2 * i + 1);
            else
                piValue -= 4.0 / (2 * i + 1);
        }
    }

    // Public method
    public void displayResult() {
        System.out.println("Public Method - Displaying Result:");
        System.out.println("Approximated value of Pi: " + piValue);
    }

    // Protected method
    protected void displayPrecision(int terms) {
        System.out.println("Protected Method - Displaying Precision Info:");
        System.out.println("Precision used: " + terms + " terms");
        System.out.println("Series used: Leibniz Series (4/1 - 4/3 + 4/5 - 4/7 + 4/9 ...)");
    }

    // Public method to access private data
    public void displayPrivateData() {
        System.out.println("Private Data - Accessed only within class:");
        System.out.println("Raw computed value (private): " + piValue);
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=== Pi Calculator using Access Specifiers ===");
         System.out.println();
        System.out.print("Enter the number of terms for Pi approximation: ");
        int terms = sc.nextInt();
        PiCalculator pi = new PiCalculator();
         System.out.println();
        System.out.println("Calculating Pi using Leibniz Series...");

        pi.calculatePi(terms);
         System.out.println();
        pi.displayResult();
         System.out.println();
        pi.displayPrecision(terms);
         System.out.println();
        pi.displayPrivateData();
         System.out.println();

        sc.close();
    }
}
