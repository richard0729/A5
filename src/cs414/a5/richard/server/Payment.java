package cs414.a5.richard2.server;

import java.util.Date;

public class Payment {

	protected double amountFee;
	protected double originalAmountFee;
	protected Date datePaid;
	protected PaymentType paymentType;
	
	public Payment(){}
   
	public Payment(double ad, PaymentType pt){
		datePaid = new Date();
		paymentType = pt;
		originalAmountFee = ad;
		amountFee = ad;
	}
	
    public double getAmountFee() {
        return amountFee;
    }
    public double getOriginalAmountFee() {
        return originalAmountFee;
    }

    
    public void setAmountFee(double amountFee) {
        this.amountFee = amountFee;
    }


    public Date getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }
    
    public PaymentType getPaymentType(){
		return paymentType;
	}
}
