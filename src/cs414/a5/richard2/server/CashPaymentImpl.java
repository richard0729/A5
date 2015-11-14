package cs414.a5.richard2.server;

import java.rmi.RemoteException;
import java.util.Date;
import cs414.a5.richard2.common.*;

public class CashPaymentImpl extends PaymentImpl implements CashPayment {

	private double TotalPaid;
	private double balanceCash;
	
	public CashPaymentImpl(int paymentID, double amountFee,double totalPaid) throws RemoteException{
			super();
	        this.amountFee = amountFee;   
	        this.TotalPaid = totalPaid;
	        this.balanceCash = totalPaid - amountFee;
	        this.datePaid = new Date();   
	        this.paymentType = PaymentType.Cash;
	        this.paymentID =paymentID;
	    }

	public double getBalanceCash() throws RemoteException{
		return balanceCash;
	}
}
