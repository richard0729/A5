package cs414.a5.richard2.common;

import java.rmi.RemoteException;

public interface CreditPayment extends Payment, java.rmi.Remote{
	
	public double getAmountFee() throws RemoteException;
	
	public String getCardNumber() throws RemoteException;
	
	public void setCardNumber(String cardNumber) throws RemoteException;
	
	public String getExpireDate() throws RemoteException;
	
	public void setExpireDate(String expireDate) throws RemoteException;
	
	public boolean isAccountValid() throws RemoteException;
	
	public boolean isExpireDateValid() throws RemoteException;
	
	public boolean isCardNumberValid() throws RemoteException;
	
	

}
