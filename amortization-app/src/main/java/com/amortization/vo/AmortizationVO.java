package com.amortization.vo;
import com.amortization.helper.AmortizationHelper;

/**
 * Created on 9/28/14.
 */
public class AmortizationVO {

    private long amountBorrowed = 0;        // in cents
    private double apr = 0d;
    private int initialTermMonths = 0;
    private long monthlyPaymentAmount = 0;    // in cents


    private AmortizationVO(AmortizationVOBuilder amortizationVOBuilder) {
        this.amountBorrowed= amortizationVOBuilder.amountBorrowed;
        this.apr = amortizationVOBuilder.apr;
        this.initialTermMonths =amortizationVOBuilder.initialTermMonths;
    }

    public long getMonthlyPaymentAmount() {
        return monthlyPaymentAmount;
    }

    public void setMonthlyPaymentAmount(long monthlyPaymentAmount) {
        this.monthlyPaymentAmount = monthlyPaymentAmount;
    }

    public long getAmountBorrowed() {
        return amountBorrowed;
    }



    public double getApr() {
        return apr;
    }


    public int getInitialTermMonths() {
        return initialTermMonths;
    }




    public static class AmortizationVOBuilder {
        private long amountBorrowed = 0;        // in cents
        private double apr = 0d;
        private int initialTermMonths = 0;
        private long monthlyPaymentAmount = 0;    // in cents


        public AmortizationVO createVO() {

            return new AmortizationVO(this);
        }


        public AmortizationVOBuilder amountBorrowed(double amount) {
            this.amountBorrowed = Math.round(amount * 100);
            return this;
        }

        public AmortizationVOBuilder initialTermMonths(int years) {
            this.initialTermMonths = years * 12;
            return this;
        }

        public AmortizationVOBuilder apr(double apr) {
            this.apr = apr;
            return this;
        }

        public AmortizationVOBuilder monthlyPaymentAmount(long monthlyPaymentAmount) {
            this.monthlyPaymentAmount = monthlyPaymentAmount;
            return this;
        }



        public AmortizationVOBuilder(double amount,int years,double apr) {
            if ((!AmortizationHelper.isValidBorrowAmount(amount)) ||
                    (!AmortizationHelper.isValidAPRValue(apr)) ||
                    (!AmortizationHelper.isValidTerm(years))) {
                throw new IllegalArgumentException();
            }
            this.amountBorrowed = Math.round(amount * 100);
            this.initialTermMonths = years * 12;
            this.apr = apr;


        }




    }

}
