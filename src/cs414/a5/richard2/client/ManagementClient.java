package cs414.a5.richard2.client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;

import java.text.DecimalFormat;
import java.util.Scanner;


import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.ParseException;
import cs414.a5.richard2.common.*;

public class ManagementClient {

	private ParkingGarage garage;
	private List<Receipt> receipts = new ArrayList<Receipt>();
	private List<Ticket> usageTickets = new ArrayList<Ticket>();
	
	
	private signStatus signStatus;
	private double rate;
	private int capacity;
	private int spaces;
	public RemoteException remoteException;
	
	public ManagementClient(ParkingGarage g) 
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
	
	public void setCapacity(int m_capacity) 
	{
		try
	  	{
	  		garage.setMaxSpaces(m_capacity);  		
	  	}
	  	catch(Exception e)
	  	{
	  		System.out.print("Exeption:" +e);
	  	}
	}
	
	public void setRate(double m_rate) 
	{
		try
	  	{
	  		garage.setFeeRate(m_rate);		
	  	}
	  	catch(Exception e)
	  	{
	  		System.out.print("Exeption:" +e);
	  	}
	}
	
	public void get_garage_status() {
	  	try
	  	{
	  		signStatus = garage.getSignStatus();	  		
	  		rate = garage.getFeeRate();
	  		DecimalFormat money = new DecimalFormat("$0.00");	  		
	  		capacity = garage.getMaxSpaces();	  		
	  		spaces = garage.getMaxSpaces() - garage.getUsedSpaces();
	  		
	  	}
	  	catch(Exception e)
	  	{
	  		System.out.print("Exeption:" +e);
	  	}
	  }
	
	public List<Ticket> getUsageTickets()
	{
		try
	  	{
			return garage.getUsageTickets();
	  	}catch (RemoteException re) {
	  		System.out.print("Exeption:" +re);
	  		return null;
		}
	}
	
	public List<Receipt> getReceipts() 
	{
		try
	  	{
			return garage.getReceipts();
	  	}catch (RemoteException re) {
	  		System.out.print("Exeption:" +re);
	  		return null;
		}
	}
	
	
}
