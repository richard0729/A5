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

public class EntryKiosk {

	private ParkingGarage garage;
	private signStatus signStatus;
	private double rate;
	private int capacity;
	private int spaces;
	public EntryError error =null;
	public RemoteException remoteException;
	
	//public EntryKiosk(ParkingGarage g) throws RemoteException 
	public EntryKiosk(ParkingGarage g) 
	{
		
		garage = g;
		//get_garage_status();
		
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
	
	
	public void connectServer()
	{
		String url = new String("rmi://localhost:2001/ParkingGarageService");

		//String url = new String("rmi://" + args[0] + ":" + args[1]  + "/ParkingGarageService");
		try {
			//ParkingGarage garage = (ParkingGarage) Naming.lookup(url);
			garage = (ParkingGarage) Naming.lookup(url);
			System.out.println("Server is connected sussessful");
			System.out.print("\t	Max Spaces: " + garage.getMaxSpaces());
			
			get_garage_status();
		}

		catch (MalformedURLException murle) {
              System.out.println("MalformedURLException");
              System.out.println(murle);
          } catch (RemoteException re) {
              System.out.println("RemoteException"); 
              System.out.println(re);
          } catch (NotBoundException nbe) {
              System.out.println("NotBoundException");
              System.out.println(nbe);
          } catch (java.lang.ArithmeticException ae) {
               System.out.println("java.lang.ArithmeticException");
               System.out.println(ae);
          }
	}
	public Ticket issueTicket(String m_plateLisence) {
		Ticket ticket = null;
		error = null;
	  	try
	  	{
	  		if (garage.isFull())
	  		{
	  			error = EntryError.full;
	  			return null;
	  		}	  		
	  		ticket = garage.issueTicket(m_plateLisence);
	  		return ticket;
	  		/*
	  		if(garage.sign.getStatus()==signStatus.available)
		  	{
		  		Ticket ticket = garage.issueTicket();		  		
			    System.out.println("Generated ticket ID # " + ticket.getId());
			    System.out.println("Entry Time: " + dateFormat.format(ticket.getEntryTime()));
			    String ticket_printed = printString("Is Ticket printed? [Y/N] ", "y");
			    if(!ticket_printed.equals("y") && !ticket_printed.equals("Y")) {
			    	garage.printFailed(ticket);
			    	System.out.println("ERROR Print Ticket - Ticket is VOIDED.");
			    } else {		      
			    	garage.enterSuccess(ticket);
			        //System.out.println(" You have entered garage.");
			    }
			    printContinue();
			}
			*/
	  	}catch (RemoteException re) {
	  		error = EntryError.errorRemote;
	  		remoteException =re;
	  		return null;
		} catch (InvalidPlateLisenceExecption ite) {
			error = EntryError.invalidPlateLisence;
			return null;
		}
	  	catch (ExistPlateLisenceException ite) {
			error = EntryError.errorExist;
			return null;
		}
	}
	
	public boolean printFail(Ticket ticket)
	{
		try
	  	{	  		  	
			garage.printFailed(ticket);
			return true;

	  	}catch (RemoteException re) {
	  		error = EntryError.errorRemote;
	  		remoteException =re;
	  		return false;
		}
	}
	
	public boolean enterSuccess(Ticket ticket)
	{
		try
	  	{	  		  	
			garage.enterSuccess(ticket);
			return true;

	  	}catch (RemoteException re) {
	  		error = EntryError.errorRemote;
	  		return false;
		}
	}
	
	public void get_garage_status() {
	  	try
	  	{
	  		//connectServer();
			//Date now = new Date();
	  		signStatus = garage.getSignStatus();
	  		System.out.print("\tSign Garage: " + signStatus);
	  		rate = garage.getFeeRate();
	  		DecimalFormat money = new DecimalFormat("$0.00");
	  		System.out.print("\t     Rate: " + money.format(rate) + "/hr");
	  		capacity = garage.getMaxSpaces();
	  		System.out.print("\t	Max Spaces: " + capacity);
	  		spaces = garage.getMaxSpaces() - garage.getUsedSpaces();
	  		System.out.print("\t	Spaces: " +spaces);
		    //int capacity = garage.getMaxSpaces() - garage.getUsedSpaces();
		    //lblSign.setText("Sign Garage: " + garage.sign.getStatus());
	  		//int max = garage.getMaxSpaces();
	  		//System.out.print("\t	Max Spaces: " + garage.getMaxSpaces());
		    
	  		//lblMaxSpaces.setText("Max Spaces: " );
		    /*
		    System.out.print("Entry Gate: " + garage.entryGate.getState());
		    System.out.print("\tExit Gate: " + garage.exitGate.getState());
		    System.out.print("\tSign Garage: " + garage.sign.getStatus());
		    System.out.println("");
		    System.out.println("Current time: " + dateFormat.format(now));
		    DecimalFormat money = new DecimalFormat("$0.00");
		    double rate = garage.getFeeRate();
		    System.out.print("\t     Rate: " + money.format(rate) + "/hr");
		    System.out.print("\t	Max Spaces: " + garage.getMaxSpaces());
		    System.out.print("\t	Capacity: " + capacity );
			*/
	  	}
	  	catch(Exception e)
	  	{
	  		System.out.print("Exeption:" +e);
	  	}
	  }
	
	/*
	public static void main(String[] args) 
	{
		String url = new String("rmi://localhost:2001/ParkingGarageService");

		//String url = new String("rmi://" + args[0] + ":" + args[1]  + "/ParkingGarageService");
		try {
			//ParkingGarage garage = (ParkingGarage) Naming.lookup(url);
			ParkingGarage g = (ParkingGarage) Naming.lookup(url);
			System.out.println("Server is connected sussessful");
			//System.out.print("\t	Max Spaces: " + garage.getMaxSpaces());
			
			new EntryKiosk(g);
		}

		catch (MalformedURLException murle) {
              System.out.println("MalformedURLException");
              System.out.println(murle);
          } catch (RemoteException re) {
              System.out.println("RemoteException"); 
              System.out.println(re);
          } catch (NotBoundException nbe) {
              System.out.println("NotBoundException");
              System.out.println(nbe);
          } catch (java.lang.ArithmeticException ae) {
               System.out.println("java.lang.ArithmeticException");
               System.out.println(ae);
          }
	}
	*/
}
