package cs414.a5.richard2.server;

import java.util.Date;
import java.rmi.*;
import cs414.a5.richard2.common.*;

public class PaymentImpl implements Payment{

	protected double amountFee;
	protected double originalAmountFee;
	protected Date datePaid;
	protected PaymentType paymentType;
	protected int paymentID;
	
	public PaymentImpl() throws RemoteException
	{
		super();
	}
   
	public PaymentImpl(double ad, PaymentType pt) throws RemoteException{
		super();
		datePaid = new Date();
		paymentType = pt;
		originalAmountFee = ad;
		amountFee = ad;
	}
	
	public int getPaymentID() throws RemoteException{
        return paymentID;
    }
	
    public void setPaymentID(int m_paymentID) throws RemoteException{
         this.paymentID =m_paymentID;                
    }
    
    public double getAmountFee() throws RemoteException{
        return this.amountFee;
    }
    
    public double getOriginalAmountFee() throws RemoteException{
        return originalAmountFee;
    }

    
    public void setAmountFee(double amountFee) throws RemoteException{
        this.amountFee = amountFee;
    }


    public Date getDatePaid() throws RemoteException{
        return datePaid;
    }

    public void setDatePaid(Date datePaid) throws RemoteException{
        this.datePaid = datePaid;
    }
    
    public PaymentType getPaymentType() throws RemoteException{
		return paymentType;
	}
}
