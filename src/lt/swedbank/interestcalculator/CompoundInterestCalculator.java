package lt.swedbank.interestcalculator;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by p998zuh on 2018.03.05.
 */
public class CompoundInterestCalculator {

    public static void main(String[] args) {
        double originalAmount;
        double annualInterestRate=1;
        int lengthOfTimeInterestIsApplied;
        double compoundingFrequency;
        List<Double> tableOfInterestInputs = new ArrayList<Double>();
        double[][] outputArray;

        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Amount: ");
        originalAmount = inputScanner.nextInt();

        int arraySizeCounter=1;
        double tempRatesTable[]=new double[1];

        while (annualInterestRate!=0) {
            System.out.println("Please enter (anual) interest rate (%) ");
            annualInterestRate=inputScanner.nextDouble();
            if(annualInterestRate==0||annualInterestRate>=100){
                break;
            }
            tableOfInterestInputs.add(annualInterestRate);
        }

        System.out.println("Please enter overall length of time the interest is applied (in years) ");
        lengthOfTimeInterestIsApplied = inputScanner.nextInt();

        System.out.println("Please enter Compound frequency ");
        compoundingFrequency = checkComputingFrequency(inputScanner.next().charAt(0));

        outputArray= new double[tableOfInterestInputs.size()][(int)(lengthOfTimeInterestIsApplied*compoundingFrequency)];

        for (int i=0; i<tableOfInterestInputs.size(); i++) {
            for(int x=1; x<=(int)(lengthOfTimeInterestIsApplied*compoundingFrequency); x++){
                double percentDifference=calculateInterest(originalAmount, tableOfInterestInputs.get(i), compoundingFrequency, x);
                outputArray[i][x-1]=calculatePercentDifference(percentDifference,originalAmount);
            }
        }

        System.out.println(Arrays.deepToString(outputArray));

        double compoundInterest=calculateInterest(originalAmount,tableOfInterestInputs.get(0), compoundingFrequency, lengthOfTimeInterestIsApplied);
    }

    public static double calculatePercentDifference(double difference, double oldValue){
        return (difference/oldValue)*100;
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

    public static double calculateInterest(double originalAmount, double interestRate,double compoundingFrequency, int years){
        return originalAmount * Math.pow(1+((interestRate / 100)/compoundingFrequency),(compoundingFrequency*(double)years))-originalAmount;
    }

}
