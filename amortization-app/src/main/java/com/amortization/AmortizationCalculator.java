package com.amortization;

import com.amortization.helper.AmortizationConsoleUtil;
import com.amortization.helper.AmortizationHelper;
import com.amortization.vo.AmortizationScheduleVO;
import com.amortization.vo.AmortizationVO;

/**
 * Created on 9/28/14.
 */
public class AmortizationCalculator {

    private static final double monthlyInterestDivisor = 12d * 100d;


    /**
     *
     * @param apr
     * @param termInMonths
     * @param amountBorrowed
     * @return   long
     */
    long calculateMonthlyPayment(double apr, int termInMonths, long amountBorrowed) {



       // M = P * (J / (1 - (Math.pow(1/(1 + J), N))));
        //
        // Where:
        // P = Principal
        // I = Interest
        // J = Monthly Interest in decimal form:  I / (12 * 100)
        // N = Number of months of loan
        // M = Monthly Payment Amount
        //

        // calculate J
        double monthlyInterest= calculateMonthlyInterest(apr);

        // this is 1 / (1 + J)
        double tmp = Math.pow(1d + monthlyInterest, -1);

        // this is Math.pow(1/(1 + J), N)
        tmp = Math.pow(tmp, termInMonths);

        // this is 1 / (1 - (Math.pow(1/(1 + J), N))))
        tmp = Math.pow(1d - tmp, -1);

        // M = P * (J / (1 - (Math.pow(1/(1 + J), N))));
        double rc = amountBorrowed * monthlyInterest * tmp;

        return Math.round(rc);
    }

    /**
     *
     * @param apr
     * @return  double
     */
    double calculateMonthlyInterest(double apr) {
        // M = P * (J / (1 - (Math.pow(1/(1 + J), N))));
        // Where:
        // I = Interest
        // J = Monthly Interest in decimal form:  I / (12 * 100)


        // calculate J
        return (apr / monthlyInterestDivisor);



    }

    /**
     *
     * @param amortizationVO
     */
    void calculateAmortizationScheduleDetail(AmortizationVO amortizationVO){

        //validate monthlyPayment against amountBorrowed
        // the following shouldn't happen with the available valid ranges
        // for borrow amount, apr, and term; however, without range validation,
        // monthlyPaymentAmount as calculated by calculateMonthlyPayment()
        // may yield incorrect values with extreme input values
        if (validateIfMonthlyPaymentAmtIsGreaterThanBorrowedAmt(amortizationVO)) {
            throw new IllegalArgumentException("AmortizationSchedule ::outputAmortizationSchedule:: [Invalid data: monthly payment is greater than borrowed amount");
        }

        long balance = amortizationVO.getAmountBorrowed();
        int paymentNumber = 1;
        long totalPayments = 0;
        long totalInterestPaid = 0;

        final int maxNumberOfPayments = amortizationVO.getInitialTermMonths()+1;

        //print header
        AmortizationConsoleUtil.printHeader();


        while ((balance > 0) && (paymentNumber <= maxNumberOfPayments)) {

            AmortizationScheduleVO totalsVO = generateNextScheduleReport(paymentNumber++, amortizationVO, balance, maxNumberOfPayments,
                    totalPayments, totalInterestPaid);
            totalPayments = AmortizationHelper.convertDoubleToLong(totalsVO.getTotalPayments());
            totalInterestPaid = AmortizationHelper.convertDoubleToLong(totalsVO.getTotalInterestPaid());

            // Set P equal to Q and go back to Step 1:  loop around until the value Q (and hence P) goes to zero.
            balance = AmortizationHelper.convertDoubleToLong(totalsVO.getCurBalance());
        }
    }


    /**
     *
      * @param paymentNumber
     * @param amortizationVO
     * @param balance
     * @param maxNumberOfPayments
     * @param totalPayments
     * @param totalInterestPaid
     * @return AmortizationScheduleVO
     */
    protected AmortizationScheduleVO generateNextScheduleReport(int paymentNumber, AmortizationVO amortizationVO, long balance, int maxNumberOfPayments,
                                                           long totalPayments,long totalInterestPaid){

         //
         // To create the amortization table, create a loop in your program and follow these steps:
         // 1.      Calculate H = P x J, this is your current monthly interest
         // 2.      Calculate C = M - H, this is your monthly payment minus your monthly interest, so it is the amount of principal you pay for that month
         // 3.      Calculate Q = P - C, this is the new balance of your principal of your loan.
         // 4.      Set P equal to Q and go back to Step 1:  loop around until the value Q (and hence P) goes to zero.
         //

         // Calculate H = P x J, this is your current monthly interest
         long curMonthlyInterest = Math.round(((double) balance) * calculateMonthlyInterest(amortizationVO.getApr()));

         // the amount required to payoff the loan
         long curPayoffAmount = balance + curMonthlyInterest;

         // the amount to payoff the remaining balance may be less than the calculated monthlyPaymentAmount
         long curMonthlyPaymentAmount = Math.min(amortizationVO.getMonthlyPaymentAmount(), curPayoffAmount);

         // it's possible that the calculated monthlyPaymentAmount is 0,
         // or the monthly payment only covers the interest payment - i.e. no principal
         // so the last payment needs to payoff the loan
         if ((paymentNumber == maxNumberOfPayments) &&
                 ((curMonthlyPaymentAmount == 0) || (curMonthlyPaymentAmount == curMonthlyInterest))) {
             curMonthlyPaymentAmount = curPayoffAmount;
         }

         // Calculate C = M - H, this is your monthly payment minus your monthly interest,
         // so it is the amount of principal you pay for that month
         long curMonthlyPrincipalPaid = curMonthlyPaymentAmount - curMonthlyInterest;

         // Calculate Q = P - C, this is the new balance of your principal of your loan.
         long curBalance = balance - curMonthlyPrincipalPaid;

         totalPayments += curMonthlyPaymentAmount;
         totalInterestPaid += curMonthlyInterest;


         AmortizationScheduleVO amortizationScheduleVO =  AmortizationHelper.constructAmortizationScheduleVO(paymentNumber, curMonthlyPaymentAmount,
                 curMonthlyInterest,
                 curBalance, totalPayments, totalInterestPaid);

        AmortizationHelper.formatAndPrint(amortizationScheduleVO);

        return amortizationScheduleVO;
      }



    /**
     *
     * @return boolean
     * @throws IllegalArgumentException
     */
    boolean validateIfMonthlyPaymentAmtIsGreaterThanBorrowedAmt(AmortizationVO amortizationVO) throws IllegalArgumentException{

        long monthlyPaymentAmount = calculateMonthlyPayment(
                amortizationVO.getApr(), amortizationVO.getInitialTermMonths(),
                amortizationVO.getAmountBorrowed());

        amortizationVO.setMonthlyPaymentAmount(monthlyPaymentAmount);

        return monthlyPaymentAmount > amortizationVO.getAmountBorrowed();
    }




}
