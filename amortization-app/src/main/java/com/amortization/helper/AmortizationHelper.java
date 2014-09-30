package com.amortization.helper;

import com.amortization.vo.AmortizationScheduleVO;

/**
 * Created on 9/28/14.
 */
public class AmortizationHelper {

    private static final double[] borrowAmountRange = new double[] { 0.01d, 1000000000000d };
    private static final double[] aprRange = new double[] { 0.000001d, 100d };
    private static final int[] termRange = new int[] { 1, 1000000 };
    private static final double HUNDRED_D = 100d;



    public static boolean isValidBorrowAmount(double amount) {
        double range[] = getBorrowAmountRange();
        return ((range[0] <= amount) && (amount <= range[1]));
    }

    public static boolean isValidAPRValue(double rate) {
        double range[] = getAPRRange();
        return ((range[0] <= rate) && (rate <= range[1]));
    }

    public static boolean isValidTerm(int years) {
        int range[] = getTermRange();
        return ((range[0] <= years) && (years <= range[1]));
    }

    public static final double[] getBorrowAmountRange() {
        return borrowAmountRange;
    }

    public static final double[] getAPRRange() {
        return aprRange;
    }

    public static final int[] getTermRange() {
        return termRange;
    }


    /**
     *
     * @param paymentNumber
     * @param curMonthlyPaymentAmount
     * @param curMonthlyInterest
     * @param curBalance
     * @param totalPayments
     * @param totalInterestPaid
     * @return AmortizationScheduleVO
     */
 public static AmortizationScheduleVO constructAmortizationScheduleVO(int paymentNumber, long curMonthlyPaymentAmount,
                                         long curMonthlyInterest, long
            curBalance, long totalPayments, long totalInterestPaid) {

            return new AmortizationScheduleVO.AmortizationScheduleVOBuilder()
                .paymentNumber(paymentNumber)
                .curMonthlyPaymentAmount((((double) curMonthlyPaymentAmount) / HUNDRED_D))
                .curMonthlyInterest((((double) curMonthlyInterest) / HUNDRED_D))
                .curBalance((((double) curBalance) / HUNDRED_D))
                .totalPayments((((double) totalPayments) / HUNDRED_D))
                .totalInterestPaid(((double) totalInterestPaid) / HUNDRED_D)
                .createScheduleVO();


    }

    // The output should include:
    //	The first column identifies the payment number.
    //	The second column contains the amount of the payment.
    //	The third column shows the amount paid to interest.
    //	The fourth column has the current balance.  The total payment amount and the interest paid fields.

    public static void formatAndPrint(AmortizationScheduleVO amortizationScheduleVO) {

        //print data
        AmortizationOutputFormatter.formatAndPrint(amortizationScheduleVO) ;

    }


    public static long convertDoubleToLong(double value){

        return Math.round(value*100);

    }

    public static double convertLongToDouble(long value){

        return (((double) value) / HUNDRED_D);

    }


}
