package lt.swedbank.interestcalculator;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by p998zuh on 2018.03.05.
 */
public class CompoundInterestCalculator {

    public static void main(String[] args) {
        double originalAmount;
        double annualInterestRate;
        double lengthOfTimeInterestIsApplied;
        double oneYearTotal = 0;
        char compoundingFrequency;
        double compoundRecalc;
        double interestAmounts[];


        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Please enter original (principal) amount ");
        originalAmount = inputScanner.nextInt();
        System.out.println("Please enter (annual) interest rate (%) ");
        annualInterestRate = inputScanner.nextDouble();
        System.out.println("Please enter overall length of time the interest is applied (in years) ");
        lengthOfTimeInterestIsApplied = inputScanner.nextInt();
        System.out.println("Please enter Compound frequency ");
        compoundingFrequency = inputScanner.next().charAt(0);

        compoundRecalc = (double) checkComputingFrequency(compoundingFrequency);
        interestAmounts = new double[(int) lengthOfTimeInterestIsApplied * (int) compoundRecalc];

        for (int i = 1; i <= interestAmounts.length; i++) {
            double compoundIteration = (calculateIterativeCompoundInterest(originalAmount, annualInterestRate, i, compoundRecalc));
            interestAmounts[i - 1] = Math.floor(compoundIteration * 100) / 100;
        }

        showOutput(annualInterestRate, (int) lengthOfTimeInterestIsApplied, originalAmount, oneYearTotal, compoundingFrequency, interestAmounts);

    }

    public static double calculateIterativeCompoundInterest(double amount, double interestRate, double lenghtOfTime, double compoundRecalc) {
        return (amount * Math.pow((1 + ((interestRate / 100) / compoundRecalc)), (lenghtOfTime * compoundRecalc))) - amount - (interestRate * ((int) lenghtOfTime - 1));
    }

    public static void showOutput(double interestRate, int lengthOfTimeInterestIsApplied,
                                  double originalAmount, double oneYearTotal, char compoundingFrequency, double[] interestAmounts) {
        System.out.printf("Interest rate: " + "%.2f\n", interestRate);
        System.out.println("Period length years: " + (int) lengthOfTimeInterestIsApplied);
        System.out.printf("Total amount: " + "%.2f\n", (originalAmount + oneYearTotal));
        System.out.printf("Compound frequency: " + compoundingFrequency + "\n");
        System.out.println(Arrays.toString(interestAmounts));
    }

    public static int checkComputingFrequency(char validationString) {
        switch (validationString) {
            case 'D':
                return 365;
            case 'W':
                return 52;
            case 'M':
                return 12;
            case 'Q':
                return 4;
            case 'H':
                return 2;
            default:
                return 1;
        }

    }
}