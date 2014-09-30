package com.amortization;

import com.amortization.helper.AmortizationOutputFormatter;
import com.amortization.vo.AmortizationScheduleVO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * AmortizationOutputFormatter Tester.
 *
 * @author <Authors name>
 * @since <pre>09/28/2014</pre>
 * @version 1.0
 */
public class AmortizationOutputFormatterTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    private AmortizationScheduleVO amortizationScheduleVO= null;

    @Before
    public void setUpData() throws Exception {
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
        System.setOut(new PrintStream(outputStream));

    }


    @After
    public void cleanOutStream(){
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setOut(null);
    }



     @org.junit.Test
     public void shouldFormatAmortizationScheduleConsoleOutput(){

       String expectedConsoleOutput  = "1                   $17.55              $1.67               $384.12,  $17.55,  $1.67";

         AmortizationOutputFormatter.formatAndPrint(amortizationScheduleVO);
         Assert.assertEquals(expectedConsoleOutput, outputStream.toString().trim());

     }

}
