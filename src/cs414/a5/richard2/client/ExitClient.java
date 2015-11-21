package cs414.a5.richard2.client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;

import java.text.DecimalFormat;
import java.util.Scanner;


import java.text.DecimalFormat;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.ParseException;
import cs414.a5.richard2.common.*;
import cs414.a5.richard2.Exception.*;

public class ExitClient {

	private ParkingGarage garage;
	private signStatus signStatus;
	private double rate;
	private int capacity;
	private int spaces;
	public ErrorExit error =null;
	public ErrorPayment errorPayment =null;
	public RemoteException remoteException;
	
	private Ticket ticket ;
	private CashPayment cashPayment;
	private CreditPayment creditPayment;
	public Receipt receipt;
	
	public ExitClient(ParkingGarage g) 
	{
		
		garage = g;		
	}
	
	public int getCapacity() 
	{
		return capacity;
	}
	
	public int getSpaces() 
	{
		return spaces;
	}
	
	public signStatus getSignStatus() 
	{
		return signStatus;
	}
	
	public String getStringStatus() 
	{
		return signStatus.toString();
	}
	
	public double getRate() 
	{
		return rate;
	}
	
	public void get_garage_status() 
	{
	  	try
	  	{
	  		//connectServer();
			//Date now = new Date();
	  		signStatus = garage.getSignStatus();
	  		//System.out.print("\tSign Garage: " + signStatus);
	  		rate = garage.getFeeRate();
	  		//DecimalFormat money = new DecimalFormat("$0.00");
	  		//System.out.print("\t     Rate: " + money.format(rate) + "/hr");
	  		capacity = garage.getMaxSpaces();
	  		//System.out.print("\t	Max Spaces: " + capacity);
	  		spaces = garage.getMaxSpaces() - garage.getUsedSpaces();
	  		//System.out.print("\t	Spaces: " +spaces);

	  	}
	  	catch(Exception e)
	  	{
	  		System.out.print("Exeption:" +e);
	  	}
	}
	
	//flag (true: pay Ticket ID, false: pay lost ticket Plate Lisence
	public Ticket getTicket(String findId, ExitUIStatus status) 
	{
		ticket = null;
		error = null;		
	  	try
	  	{	  		
	  		//ticket = garage.issueTicket(m_plateLisence);
	  		if(status == ExitUIStatus.payTicket) // Find by Ticket ID
	  		{
		  		ticket = garage.getTickeByID(findId);
		  		return ticket;
	  		}
	  		else if(status == ExitUIStatus.payLostTicket)
	  		{
		  		ticket = garage.getTickeByPlateLisence(findId);
		  		return ticket;
	  		}
	  		return null;
	  	}catch (RemoteException re) {
	  		error = ErrorExit.errorRemote;
	  		remoteException =re;
	  		return null;
		} catch (InvalidTicketException ite) {
			error = ErrorExit.invalidTicket;
			return null;
		}
	  	catch (InvalidPlateLisenceExecption ipe) {
			error = ErrorExit.invalidPlate;
			return null;
		}
	}
	
	public CashPayment issuePaymentByCash (String s_AmountDue,String s_AmountCash) 
	{
		cashPayment = null;
		error = null;		
	  	try
	  	{	  		
	  		
	  		cashPayment = garage.PayByCash(s_AmountDue, s_AmountCash);
		  	return cashPayment;	  		
	  		
	  	}catch (RemoteException re) {
	  		errorPayment = ErrorPayment.errorRemote;
	  		remoteException =re;
	  		return null;
		} catch (InvalidDoubleException ide) {
			errorPayment = ErrorPayment.invalidDouble;
			return null;
		}
	  	catch (InvalidBalanceCashException ipe) {
	  		errorPayment = ErrorPayment.invalidBalance;
			return null;
		}
	}
	
	public Receipt issueCashReceipt (Ticket m_ticket, CashPayment m_cash) 
	{
		try
	  	{
			receipt = null;
			if(m_ticket != null && m_cash != null )
				receipt =this.garage.CreateReceipt(m_ticket, m_cash);
			return receipt;
	  	}catch (RemoteException re) {
	  		errorPayment = ErrorPayment.errorRemote;
	  		remoteException =re;
	  		return null;
		}
	}
	
	public CreditPayment issuePaymentByCredit (String s_AmountDue,String s_AccountNumber, String s_ExpireDate) 
	{
		creditPayment = null;
		error = null;		
	  	try
	  	{	  		
	  		
	  		creditPayment = garage.PayByCredit(s_AmountDue, s_AccountNumber, s_ExpireDate);
		  	return creditPayment;	  		
	  		
	  	}catch (RemoteException re) {
	  		errorPayment = ErrorPayment.errorRemote;
	  		remoteException =re;
	  		return null;
		} catch (InvalidDoubleException ide) {
			errorPayment = ErrorPayment.invalidDouble;
			return null;
		}
	  	catch (InvalidAccountException iae) {
	  		errorPayment = ErrorPayment.invalidAccount;
			return null;
		}
	  	catch (InvalidLengthCardException ile) {
	  		errorPayment = ErrorPayment.invalidLength;
			return null;
		}
	  	catch (InvalidMonthException ime) {
	  		errorPayment = ErrorPayment.invalidMonth;
			return null;
		}
	  	catch (InvalidExpireDateException iee) {
	  		errorPayment = ErrorPayment.invalidExpire;
			return null;
		}
	}
	
	public Receipt issueCardReceipt (Ticket m_ticket, CreditPayment m_credit) 
	{
		try
	  	{
			receipt = null;
			if(m_ticket != null && m_credit != null )
				receipt =this.garage.CreateReceipt(m_ticket, m_credit);
			return receipt;
	  	}catch (RemoteException re) {
	  		errorPayment = ErrorPayment.errorRemote;
	  		remoteException =re;
	  		return null;
		}
	}
	
	public Receipt issueNoPayReceipt (Ticket m_ticket) 
	{
		try
	  	{
			receipt = null;
			if(m_ticket != null )
				receipt =this.garage.CreateReceipt(m_ticket);
			return receipt;
	  	}catch (RemoteException re) {
	  		errorPayment = ErrorPayment.errorRemote;
	  		remoteException =re;
	  		return null;
		}
	}
	
	public void ExitSuceess(Ticket m_ticket)
	{
		try
		{
		garage.exitSuccess(m_ticket);
		}catch (RemoteException re) {
	  		errorPayment = ErrorPayment.errorRemote;
	  		remoteException =re;	  		
		}
	}
	
	public void issueNoPay(Ticket m_ticket)
	{
		try
		{
		garage.issueNoPay(m_ticket);
		}catch (RemoteException re) {
	  		errorPayment = ErrorPayment.errorRemote;
	  		remoteException =re;	  		
		}
	}
}
