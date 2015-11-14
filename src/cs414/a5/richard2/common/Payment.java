package cs414.a5.richard2.common;

import java.util.Date;
import java.rmi.*;

public interface Payment extends java.rmi.Remote{

	public int getPaymentID() throws RemoteException;
	
	public void setPaymentID(int m_paymentID) throws RemoteException;
	
	public double getAmountFee() throws RemoteException;
	
	public double getOriginalAmountFee() throws RemoteException;
	
	public void setAmountFee(double amountFee) throws RemoteException;
	
	public Date getDatePaid() throws RemoteException;
	
	public void setDatePaid(Date datePaid) throws RemoteException;
	
	public PaymentType getPaymentType() throws RemoteException;
	
}
