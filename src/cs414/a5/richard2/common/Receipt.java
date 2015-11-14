package cs414.a5.richard2.common;

import java.rmi.*;

public interface Receipt extends java.rmi.Remote{

	public Ticket getTicket() throws RemoteException;
	
	public CashPayment getCashPayment() throws RemoteException;
	
	public CreditPayment getCreditPayment() throws RemoteException;
	
	public PaymentType getPaymentType() throws RemoteException;
}
