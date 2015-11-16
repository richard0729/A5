package cs414.a5.richard2.common;

import java.rmi.*;
import java.util.ArrayList;
import java.util.List;



public interface ParkingGarage extends java.rmi.Remote{
	/*
	public long add(long a, long b)
            throws java.rmi.RemoteException;
   public long sub(long a, long b)
            throws java.rmi.RemoteException;
	*/
	
	public signStatus getSignStatus() throws RemoteException;
	
	public double getFeeLost() throws RemoteException;
	
	public double getFeeRate() throws RemoteException;
	
	public int getMaxSpaces() throws RemoteException;
	
	public void setMaxSpaces(int newMax) throws RemoteException;
	
	public int getUsedSpaces() throws RemoteException;
	
	public void setUsedSpaces(int newUsed) throws RemoteException;
	
	public List<Ticket> getTicketTrans() throws RemoteException;
	
	public Ticket issueTicket(String m_plateLisence) throws RemoteException, InvalidPlateLisenceExecption, ExistPlateLisenceException, InvalidNoPayException;
	
	public boolean isExistPlateLisence(String m_plateLisence) throws RemoteException;
	
	public Ticket getTicket(int id) throws RemoteException;
	
	public List<Ticket> getActiveTickets() throws RemoteException;
	
	public List<Ticket> getUsageTickets() throws RemoteException;
	
	public void updateSpace() throws RemoteException;
	
	public boolean isFull() throws RemoteException;
	
	public List<Receipt> getReceipts() throws RemoteException;
	
	public void addReceipt(Receipt r) throws RemoteException;
	
	public void printFailed(Ticket ticket) throws RemoteException;
	
	public void enterSuccess(Ticket ticket) throws RemoteException;
	
	public void exitSuccess(Ticket ticket) throws RemoteException;
	
	public Ticket getTickeByID(String id) throws RemoteException, InvalidTicketException;
	
	public Ticket getTickeByPlateLisence(String Plate) throws RemoteException, InvalidPlateLisenceExecption;
	
	public CashPayment PayByCash(String s_AmountDue,String s_AmountCash) throws RemoteException, InvalidDoubleException, InvalidBalanceCashException;
	
	public CreditPayment PayByCredit(String s_AmountDue, String s_NumberAccount, String s_expireDate) throws RemoteException, InvalidDoubleException,InvalidAccountException,InvalidExpireDateException,InvalidMonthException, InvalidLengthCardException;
	
	public Receipt CreateReceipt(Ticket m_ticket, CashPayment m_cash) throws RemoteException;
	
	public Receipt CreateReceipt(Ticket m_ticket, CreditPayment m_credit) throws RemoteException;
	
	public void issueNoPay(Ticket ticket) throws RemoteException;
	
	public Receipt CreateReceipt(Ticket m_ticket) throws RemoteException;
}
