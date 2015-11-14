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

public class ExitClient {

	private ParkingGarage garage;
	private signStatus signStatus;
	private double rate;
	private int capacity;
	private int spaces;
	public ErrorExit error =null;
	public ErrorPayment errorPayment =null;
	public RemoteException remoteException;
	
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
	public Ticket getTicket(String findId, boolean flag) 
	{
		Ticket ticket = null;
		error = null;		
	  	try
	  	{	  		
	  		//ticket = garage.issueTicket(m_plateLisence);
	  		if(flag) // Find by Ticket ID
	  		{
		  		ticket = garage.getTickeByID(findId);
		  		return ticket;
	  		}
	  		else
	  		{
		  		ticket = garage.getTickeByPlateLisence(findId);
		  		return ticket;
	  		}
	  		
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
		CashPayment cash = null;
		error = null;		
	  	try
	  	{	  		
	  		
	  			cash = garage.PayByCash(s_AmountDue, s_AmountCash);
		  		return cash;	  		
	  		
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
}
