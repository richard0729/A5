package cs414.a5.richard2.common;

import java.rmi.RemoteException;

public interface CreditPayment extends Payment, java.rmi.Remote{
	
	public double getAmountFee() throws RemoteException;
	
	public String getCardNumber() throws RemoteException;
	
	public void setCardNumber(String cardNumber) throws RemoteException;
	
	public String getExpireDate() throws RemoteException;	
	
	//public boolean isAccountValid() throws RemoteException;
	
	public boolean isMonthValid(String m_expireDate) throws RemoteException;
	
	public boolean isExpireDateValid(String m_expireDate) throws RemoteException;
	
	public boolean isCardNumberValid(String m_cardNumber) throws RemoteException;
	
	public boolean isLengthValid(String m_cardNumber) throws RemoteException;
	
	

}
