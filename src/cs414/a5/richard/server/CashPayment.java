package cs414.a5.richard2.server;

import java.util.Date;

public class CashPayment extends Payment {
	
	private double TotalPaid;
	private double balanceCash;
	
	public CashPayment(double amountFee,double totalPaid){
	        this.amountFee = amountFee;   
	        this.TotalPaid = totalPaid;
	        this.balanceCash = totalPaid - amountFee;
	        this.datePaid = new Date();   
	        this.paymentType = PaymentType.Cash;
	    }

	public double getBalanceCash(){
		return balanceCash;
	}
}
