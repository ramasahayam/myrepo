package com.amortization;

import com.amortization.vo.AmortizationScheduleVOTest;
import com.amortization.vo.AmortizationVOTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created on 9/29/14.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AmortizationCalculatorTest.class,
        AmortizationVOTest.class,
        AmortizationScheduleVOTest.class,
        AmortizationOutputFormatterTest.class,

})
public class AmortizationTestSuite {




}
