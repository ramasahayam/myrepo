package com.amortization.vo;

/**
 * Created on 9/28/14.
 */
public class AmortizationScheduleVO {


    private int paymentNumber;
    private double curMonthlyPaymentAmount;
    private double curMonthlyInterest;
    private double curBalance;
    private double totalPayments;
    private double totalInterestPaid;

    public int getPaymentNumber() {
        return paymentNumber;
    }

    public double getCurMonthlyPaymentAmount() {
        return curMonthlyPaymentAmount;
    }

    public double getCurMonthlyInterest() {
        return curMonthlyInterest;
    }

    public double getCurBalance() {
        return curBalance;
    }

    public double getTotalPayments() {
        return totalPayments;
    }

    public double getTotalInterestPaid() {
        return totalInterestPaid;
    }

    /** AmortizationScheduleVO --private constructor and using builder to create object
     *
     */
    private AmortizationScheduleVO(AmortizationScheduleVOBuilder voBuilder) {

        this.paymentNumber = voBuilder.paymentNumber;
        this.curMonthlyPaymentAmount = voBuilder.curMonthlyPaymentAmount;
        this.curMonthlyInterest = voBuilder.curMonthlyInterest;
        this.curBalance = voBuilder.curBalance;
        this.totalPayments = voBuilder.totalPayments;
        this.totalInterestPaid = voBuilder.totalInterestPaid;

    }



    /**
     * Builder to create AmortizationScheduleVO
     */
    public static class AmortizationScheduleVOBuilder {
        private int paymentNumber;
        private double curMonthlyPaymentAmount;
        private double curMonthlyInterest;
        private double curBalance;
        private double totalPayments;
        private double totalInterestPaid;

        public AmortizationScheduleVO createScheduleVO() {

            return new AmortizationScheduleVO(this);
        }

        public AmortizationScheduleVOBuilder(){

        }

        public AmortizationScheduleVOBuilder paymentNumber(int paymentNumber) {
            this.paymentNumber = paymentNumber;
            return this;
        }

        public AmortizationScheduleVOBuilder curMonthlyPaymentAmount(double curMonthlyPaymentAmount) {
            this.curMonthlyPaymentAmount = curMonthlyPaymentAmount;
            return this;
        }

        public AmortizationScheduleVOBuilder curMonthlyInterest(double curMonthlyInterest) {
            this.curMonthlyInterest = curMonthlyInterest;
            return this;
        }

        public AmortizationScheduleVOBuilder curBalance(double curBalance) {
            this.curBalance = curBalance;
            return this;
        }

        public AmortizationScheduleVOBuilder totalPayments(double totalPayments) {
            this.totalPayments = totalPayments;
            return this;
        }

        public AmortizationScheduleVOBuilder totalInterestPaid(double totalInterestPaid) {
            this.totalInterestPaid = totalInterestPaid;
            return this;
        }


    }


}
