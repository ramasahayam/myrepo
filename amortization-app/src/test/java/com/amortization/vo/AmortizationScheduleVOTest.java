package com.amortization.vo;

import com.amortization.vo.AmortizationScheduleVO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

/**
 * AmortizationScheduleVO Tester.
 *
 * @author <Authors name>
 * @since <pre>09/28/2014</pre>
 * @version 1.0
 */
public class AmortizationScheduleVOTest{

    private AmortizationScheduleVO amortizationScheduleVO=null;

     @Before
     public void setupVO(){
         int expectedPaymentNumber=1;
         double expectedCurMonthlyPaymentAmount =17.55 ;
         double expectedCurMonthlyInterest =1.67 ;
         double expectedCurBalance = 384.12;
         double expectedTotalPayments =17.55 ;
         double expectedTotalInterestPaid= 1.67;

          amortizationScheduleVO=   new
                 AmortizationScheduleVO.AmortizationScheduleVOBuilder().paymentNumber(expectedPaymentNumber)
                 .curMonthlyPaymentAmount(expectedCurMonthlyPaymentAmount)
                 .curMonthlyInterest(expectedCurMonthlyInterest)
                 .curBalance(expectedCurBalance)
                 .totalPayments(expectedTotalPayments)
                 .totalInterestPaid(expectedTotalInterestPaid)
                 .createScheduleVO();


     }
    @After
    public void tearDown(){
        amortizationScheduleVO = null;
    }

    public void testGetPaymentNumber() throws Exception {
        Assert.assertNotNull(amortizationScheduleVO.getPaymentNumber());
    }

    public void testGetCurMonthlyPaymentAmount() throws Exception {
        Assert.assertEquals(amortizationScheduleVO.getCurMonthlyPaymentAmount(), 17.55,0);

    }

    public void testGetCurMonthlyInterest() throws Exception {
        Assert.assertEquals(amortizationScheduleVO.getCurMonthlyInterest(),1.67,0);
    }

    public void testGetCurBalance() throws Exception {
        Assert.assertEquals(amortizationScheduleVO.getCurBalance(),384.12,0);
    }

    public void testGetTotalPayments() throws Exception {
        Assert.assertNotNull(amortizationScheduleVO.getTotalPayments());
    }





    @org.junit.Test
    public void shouldCreateAmortizationScheduleVO(){

        int expectedPaymentNumber=1;
        double expectedCurMonthlyPaymentAmount =17.55 ;
        double expectedCurMonthlyInterest =1.67 ;
        double expectedCurBalance = 384.12;
        double expectedTotalPayments =17.55 ;
        double expectedTotalInterestPaid= 1.67;

     AmortizationScheduleVO amortizationScheduleVO=   new
                AmortizationScheduleVO.AmortizationScheduleVOBuilder().paymentNumber(expectedPaymentNumber)
                .curMonthlyPaymentAmount(expectedCurMonthlyPaymentAmount)
                .curMonthlyInterest(expectedCurMonthlyInterest)
                .curBalance(expectedCurBalance)
                .totalPayments(expectedTotalPayments)
                .totalInterestPaid(expectedTotalInterestPaid)
                .createScheduleVO();


        Assert.assertNotNull(amortizationScheduleVO);
        Assert.assertEquals(expectedPaymentNumber,amortizationScheduleVO.getPaymentNumber());
        Assert.assertEquals(expectedCurMonthlyPaymentAmount,amortizationScheduleVO.getCurMonthlyPaymentAmount(),0);
        Assert.assertEquals(expectedCurMonthlyInterest,amortizationScheduleVO.getCurMonthlyInterest(),0);
        Assert.assertEquals(expectedCurBalance,amortizationScheduleVO.getCurBalance(),0);
        Assert.assertEquals(expectedTotalPayments,amortizationScheduleVO.getTotalPayments(),0);
        Assert.assertEquals(expectedTotalInterestPaid,amortizationScheduleVO.getTotalInterestPaid(),0);


    }




}
