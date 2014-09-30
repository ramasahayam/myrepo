package com.amortization.helper;

import com.amortization.helper.AmortizationConsoleUtil;
import com.amortization.vo.AmortizationScheduleVO;

import java.text.NumberFormat;

/**
 * Created on 9/28/14.
 */
public class AmortizationOutputFormatter {

    public static void formatAndPrint(AmortizationScheduleVO amortizationScheduleVO) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();

        // output is in dollars
        AmortizationConsoleUtil.printScheduleData(amortizationScheduleVO.getPaymentNumber(),
                nf.format(amortizationScheduleVO.getCurMonthlyPaymentAmount()),
                nf.format(amortizationScheduleVO.getCurMonthlyInterest()),
                nf.format(amortizationScheduleVO.getCurBalance()),
                nf.format(amortizationScheduleVO.getTotalPayments()),
                nf.format(amortizationScheduleVO.getTotalInterestPaid()));
    }


}
