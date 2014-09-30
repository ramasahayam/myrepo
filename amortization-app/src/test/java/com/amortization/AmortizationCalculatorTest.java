package com.amortization;

import com.amortization.helper.AmortizationHelper;
import com.amortization.vo.AmortizationScheduleVO;
import com.amortization.vo.AmortizationVO;
import org.junit.Assert;

/**
 * AmortizationCalculator Tester.
 *
 * @author <Authors name>
 * @since <pre>09/28/2014</pre>
 * @version 1.0
 */
public class AmortizationCalculatorTest {


    @org.junit.Test
    public void shouldCalculateNextScheduleAmountDetails(){

        double expectedTotalPayments =856.07;
        double expectedTotalInterestPaid=41.67;

        double expectedBalance=9185.60;



        int paymentNumber  = 1;
        long monthlyPaymentAmount = Math.round(856.07*100);
        double apr=5.0;
        long balance = Math.round(10000*100);
        int maxNumberOfPayments=(12+1);
        long totalPayments =0;
        long totalInterestPaid=0;


        AmortizationVO amortizationVO =  new AmortizationVO.AmortizationVOBuilder(10000.0,1,apr)
                .createVO();

        amortizationVO.setMonthlyPaymentAmount(monthlyPaymentAmount);

        AmortizationCalculator amortizationCalculator = new AmortizationCalculator();

        AmortizationScheduleVO result = amortizationCalculator.generateNextScheduleReport(paymentNumber, amortizationVO,
                balance, maxNumberOfPayments,
                totalPayments, totalInterestPaid);


        Assert.assertEquals(expectedTotalPayments,result.getTotalPayments(),0);
        Assert.assertEquals(expectedTotalInterestPaid,result.getTotalInterestPaid(),0);
        Assert.assertEquals(expectedBalance,result.getCurBalance(),0);

    }
    @org.junit.Test
    public void shouldReturnMonthlyInterestRate(){
        AmortizationCalculator amortizationCalculator = new AmortizationCalculator();

        double rate = amortizationCalculator.calculateMonthlyInterest(5.0);
        Assert.assertEquals(0.004166666666666667, rate,0);
    }

    @org.junit.Test
    public void shouldCalculateMonthlyPaymentAmount(){

        double expectedMonthlyPayment = 856.07;

        AmortizationCalculator amortizationCalculator = new AmortizationCalculator();

        double apr =5.0;
        long borrowedAmount= AmortizationHelper.convertDoubleToLong(10000.0);
        int termInMonths=12;

       long monthlyPayment= amortizationCalculator.calculateMonthlyPayment(apr,termInMonths,borrowedAmount) ;

       Assert.assertEquals(expectedMonthlyPayment, AmortizationHelper.convertLongToDouble(monthlyPayment),0);


    }

    @org.junit.Test
    public void shouldReturnFalseValidateIfMonthlyPaymentAmtIsGreaterThanBorrowedAmt(){

        int paymentNumber  = 1;
        long monthlyPaymentAmount = Math.round(856.07*100);
        double apr=5.0;


        AmortizationVO amortizationVO =  new AmortizationVO.AmortizationVOBuilder(10000.0,paymentNumber,apr)
                .createVO();
        amortizationVO.setMonthlyPaymentAmount(monthlyPaymentAmount);

        AmortizationCalculator amortizationCalculator = new AmortizationCalculator();

        Assert.assertFalse(amortizationCalculator.validateIfMonthlyPaymentAmtIsGreaterThanBorrowedAmt(amortizationVO));

    }



}
