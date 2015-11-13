package cs414.a5.richard2.server;

import java.text.DecimalFormat;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import cs414.a5.richard2.common.*;

public class Receipt {
	//private Date paymentDate;
	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
	private Date paymentDate;
	private PaymentType paymentType;
	private double amount;
	private DecimalFormat df = new DecimalFormat("0.00");
	
	public Receipt(Payment pay){
		paymentDate = pay.getDatePaid();
		amount = pay.getAmountFee();
		paymentType = pay.getPaymentType();
	}
	
	public void printReceipt(){
		System.out.println("\nReceipt: \nThank you for paid Ticket.\nDate: "
				+dateFormat.format(paymentDate)+
				"\nTotal: $" + df.format(amount) + "\nPayment Type: " + paymentType + "\n");
	}
}
