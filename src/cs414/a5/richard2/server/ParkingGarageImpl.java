package cs414.a5.richard2.server;


import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.rmi.*;
import java.rmi.server.*;

import cs414.a5.richard2.common.*;

public class ParkingGarageImpl extends UnicastRemoteObject implements ParkingGarage{

	public Sign sign;
	public Gate entryGate;
	public Gate exitGate;
	private int maxSpaces = 3;
	public int usedSpaces = 0;
	private double feeRate = 10.0;
	private int numCount =0;
  
	private List<Ticket> ticketTrans = new ArrayList<Ticket>();
	private List<Ticket> activeTickets = new ArrayList<Ticket>();
	private List<Receipt> receipts = new ArrayList<Receipt>();
	private List<Ticket> usageTickets = new ArrayList<Ticket>();

	public ParkingGarageImpl() throws RemoteException{
	    sign  = new Sign();
	    entryGate  = new Gate(GateType.entry);
	    exitGate      = new Gate(GateType.exit);
	}

	public double getFeeRate() throws RemoteException{ return feeRate; }
	public void setFeeRate(double newRate) {
	    feeRate = newRate;
	}
	
	public int getMaxSpaces() throws RemoteException{ return maxSpaces; }
	public void setMaxSpaces(int newMax) throws RemoteException{
	    maxSpaces = newMax;
		updateSpace();
	}
		  
	public int getUsedSpaces() throws RemoteException{ return usedSpaces; }
	public void setUsedSpaces(int newUsed) {
		usedSpaces = newUsed;
	}
	
	public List<Ticket> getTicketTrans() throws RemoteException{
		return this.ticketTrans;
	}

	public Ticket issueTicket() throws RemoteException{
		++numCount;
		Ticket mTicket = new Ticket(numCount);
		Date m_entryTime = new Date();
		mTicket.setEntryTime(m_entryTime);
		this.ticketTrans.add(mTicket);
	    return mTicket;
	}

	public Ticket getTicket(int id) throws RemoteException{
	    try {
	    	for(Ticket ticket : activeTickets){
				if(ticket.getId()==id){
					return ticket;
				}
			}
			return null;
	    }
	    catch (java.lang.IndexOutOfBoundsException e){
	      return null;
	    }
	}

	public List<Ticket> getActiveTickets() throws RemoteException{
	    activeTickets = new ArrayList<Ticket>();
	    for(Ticket t : ticketTrans) {
	      if(t.getIsExist()) {
	        activeTickets.add(t);
	      }
	    }
	    return activeTickets;
	}

	public List<Ticket> getUsageTickets() throws RemoteException
	{
		usageTickets = new ArrayList<Ticket>();
	    for(Ticket t : ticketTrans) {
	      if(!t.getIsVoid()) {
	    	  usageTickets.add(t);
	      }
	    }
	    return usageTickets;
	}	  

	public void updateSpace() throws RemoteException
	{
		  boolean mFull = isFull();
		  sign.updateSign(mFull);
	}
	  
	public boolean isFull() throws RemoteException{
		int msize = getActiveTickets().size();
		setUsedSpaces(msize);
		return msize >= this.maxSpaces;
	}
	
	public List<Receipt> getReceipts() throws RemoteException
	{ return this.receipts;}
	
	public void addReceipt(Receipt r)throws RemoteException
	{
			receipts.add(r);
	}

	public void printFailed(Ticket ticket) throws RemoteException
	{
	    ticket.voidTicket();
	}

	public void enterSuccess(Ticket ticket) throws RemoteException
	{
		entryGate.openGate();
		Date m_entryTime = new Date();
	    ticket.enterNow(m_entryTime);
	    entryGate.closeGate();
	    this.updateSpace();
	}
	  
	  //Exit
	public void exitSuccess(Ticket ticket) throws RemoteException
	{
	  exitGate.openGate();  
	  Date m_exitTime = new Date();
	  ticket.exitNow(m_exitTime);
	  exitGate.closeGate();
	  this.updateSpace();
	}
}
