package com.amortization.client;

import com.amortization.helper.AmortizationConsoleUtil;
import com.amortization.helper.AmortizationHelper;
import com.amortization.AmortizationSchedule;
import com.amortization.vo.AmortizationVO;

import java.io.IOException;

/**
 * Created on 9/28/14.
 */
public class AmortizationClient {

    //
    public static void main(String [] args) {

        String[] userPrompts = {
                "Please enter the amount you would like to borrow: ",
                "Please enter the annual percentage rate used to repay the loan: ",
                "Please enter the term, in years, over which the loan is repaid: "
        };

        String line;
        double amount = 0;
        double apr = 0;
        int years = 0;

        for (int i = 0; i< userPrompts.length; ) {
            String userPrompt = userPrompts[i];
            try {
                line = AmortizationConsoleUtil.readLine(userPrompt);
            } catch (IOException e) {
                AmortizationConsoleUtil.print("An IOException was encountered. Terminating program.\n");
                return;
            }

            boolean isValidValue = true;
            try {
                switch (i) {
                    case 0:
                        amount = Double.parseDouble(line);
                        if (!AmortizationHelper.isValidBorrowAmount(amount)) {
                            isValidValue = false;
                            double range[] = AmortizationHelper.getBorrowAmountRange();
                            AmortizationConsoleUtil.print("Please enter a positive value between " + range[0] + " and " + range[1] + ". ");
                        }
                        break;
                    case 1:
                        apr = Double.parseDouble(line);
                        if (!AmortizationHelper.isValidAPRValue(apr)) {
                            isValidValue = false;
                            double range[] = AmortizationHelper.getAPRRange();
                            AmortizationConsoleUtil.print("Please enter a positive value between " + range[0] + " and " + range[1] + ". ");
                        }
                        break;
                    case 2:
                        years = Integer.parseInt(line);
                        if (!AmortizationHelper.isValidTerm(years)) {
                            isValidValue = false;
                            int range[] = AmortizationHelper.getTermRange();
                            AmortizationConsoleUtil.print("Please enter a positive integer value between " + range[0] + " and " + range[1] + ". ");
                        }
                        break;
                }
            } catch (NumberFormatException e) {
                isValidValue = false;
            }
            if (isValidValue) {
                i++;
            } else {
                AmortizationConsoleUtil.print("An invalid value was entered.\n");
            }
        }

        try {

            AmortizationVO amortizationVO = new AmortizationVO.AmortizationVOBuilder(amount,years,apr)
                    .createVO();

            AmortizationSchedule as = new AmortizationSchedule(amortizationVO);

            as.generateAmortizationSchedule();
        } catch (IllegalArgumentException e) {
            AmortizationConsoleUtil.print("Unable to process the values entered. Terminating program.\n");
        }
    }
}
