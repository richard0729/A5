package cs414.a5.richard2.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;

import cs414.a5.richard2.Exception.ExistPlateLisenceException;
import cs414.a5.richard2.Exception.InvalidNoPayException;
import cs414.a5.richard2.Exception.InvalidPlateLisenceExecption;
import cs414.a5.richard2.common.EntryError;
import cs414.a5.richard2.common.ParkingGarage;
import cs414.a5.richard2.common.Ticket;
import cs414.a5.richard2.common.signStatus;

public class EntryClient {

	private ParkingGarage garage;
	private signStatus signStatus;
	private double rate;
	private int capacity;
	private int spaces;
	public EntryError error =null;
	public RemoteException remoteException;
	
	//public EntryKiosk(ParkingGarage g) throws RemoteException 
	public EntryClient(ParkingGarage g) 
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
	  	catch (InvalidNoPayException ite) {
			error = EntryError.noPay;
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
	
	
}
