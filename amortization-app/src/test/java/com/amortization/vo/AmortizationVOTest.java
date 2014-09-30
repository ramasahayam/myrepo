package com.amortization.vo;

import com.amortization.vo.AmortizationVO;
import org.junit.Assert;

/**
 * AmortizationVO Tester.
 *
 * @author <Authors name>
 * @since <pre>09/28/2014</pre>
 * @version 1.0
 */
public class AmortizationVOTest  {



    @org.junit.Test
    public void shouldPopulateAmortizationVO(){
        AmortizationVO amortizationVO = new AmortizationVO.AmortizationVOBuilder(1000,1,10)
                .createVO();
        Assert.assertEquals(amortizationVO.getAmountBorrowed() / 100, 1000);
        Assert.assertEquals(amortizationVO.getInitialTermMonths(), 12);
        Assert.assertEquals(amortizationVO.getApr(), 10.0,0);


    }


    @org.junit.Test(expected = IllegalArgumentException.class)
    public void shouldFailInputValidation(){
        new AmortizationVO.AmortizationVOBuilder(-10,1,10)
                .createVO();



    }


}
