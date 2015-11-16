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
	
	public ManagementClient(ParkingGarage g) 
	{
		
		garage = g;		
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
