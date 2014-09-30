package com.amortization.helper;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.IllegalFormatException;

/**
 * Created on 9/28/14.
 */
public class AmortizationConsoleUtil {

    private static final String PAYMENT_NUMBER_LABEL="PaymentNumber";
    private static final String PAYMENT_AMOUNT_LABEL =  "PaymentAmount";
    private static final String PAYMENT_INTEREST_LABEL= "PaymentInterest";
    private static final String CURRENT_BALANCE_LABEL="CurrentBalance";
    private static final String TOTAL_PAYMENTS_LABEL="TotalPayments";
    private static final String TOTAL_INTEREST_PAID_LABEL="TotalInterestPaid" ;
    private static final String FORMAT_STRING_LABEL = "%1$-20s%2$-20s%3$-20s%4$s,%5$s,%6$s\n";
    private static final String FORMAT_STRING_DATA = "%1$-20s%2$-20s%3$-20s%4$s,  %5$s,  %6$s\n";

    private static final Console console = System.console();

    /**
     *
     * @param formatString
     * @param args
     */
    private static void printf(String formatString, Object... args) {

        try {
            if (console != null) {
                console.printf(formatString, args);
            } else {
                System.out.print(String.format(formatString, args));
            }
        } catch (IllegalFormatException e) {
            System.err.print("Error printing...\n");
        }
    }

    /**
     *
     * @param args
     */
    public static void printScheduleData( Object... args) {
        printf(FORMAT_STRING_DATA, args);
    }


    /**
     *
     * @param s
     */
    public static void print(String s) {
        printf("%s", s);
    }

    /**
     *
     * @param userPrompt
     * @return String
     * @throws IOException
     */
    public static String readLine(String userPrompt) throws IOException {
        String line;

        if (console != null) {
            line = console.readLine(userPrompt);
        } else {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            print(userPrompt);
            line = bufferedReader.readLine();
        }

        return line.trim();
    }


    /**
     *
     */
    public static void printHeader() {
        printf(FORMAT_STRING_LABEL,
                PAYMENT_NUMBER_LABEL, PAYMENT_AMOUNT_LABEL, PAYMENT_INTEREST_LABEL,
                CURRENT_BALANCE_LABEL, TOTAL_PAYMENTS_LABEL, TOTAL_INTEREST_PAID_LABEL);
    }
}
