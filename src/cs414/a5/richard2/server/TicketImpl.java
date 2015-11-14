package cs414.a5.richard2.server;

import java.util.Date;
import cs414.a5.richard2.common.Ticket;
import java.rmi.*;
//import java.rmi.server.*;

public class TicketImpl implements Ticket{

	private int id; /** Simple integer-based ticket number */
	  private Date entryTime;
	  private Date exitTime;
	  private boolean isExist = false;
	  private boolean isPaid = false;
	  private boolean isVoid = false;
	  
	  private String plateLisence;
	  
	  public TicketImpl(int ticketId,String m_plateLisence) throws RemoteException{ 
		  	super();
		    this.id = ticketId;
		    this.plateLisence = m_plateLisence;
	  }

	  public boolean getIsVoid() throws RemoteException{ return isVoid; }
	  
	  public int getId() throws RemoteException{ return id; }
	  
	  public void setId(int m_Id) throws RemoteException{ this.id = m_Id; }
	  
	  public String getPlateLisence() throws RemoteException{ return plateLisence; }
	  
	  public void setPlateLisence(String m_plateLisence) throws RemoteException{ this.plateLisence = m_plateLisence; }

	  public Date getEntryTime() throws RemoteException{ return entryTime; }
	  
	  public void setEntryTime(Date m_entryTime) throws RemoteException{
	    this.entryTime = m_entryTime;
	  }
	  
	  public Date getExitTime() throws RemoteException{ return exitTime; }
	  
	  public void setExitTime(Date m_exitTime) throws RemoteException{
	    this.exitTime = m_exitTime;
	  }
	  
	  
	  public Ticket enterNow(Date m_entryTime) throws RemoteException{
		entryTime = m_entryTime;
		setIsExist(true);
	    return this;
	  }
	  
	  public Ticket getExitTime(Date new_exit_time) throws RemoteException{
		this.exitTime = new_exit_time;
	    return this;
	  }
	  
	  public Ticket exitNow(Date m_exitTime) throws RemoteException{
		  this.exitTime = m_exitTime;
		  setIsExist(false);
	    return this;
	  }

	  
	  public boolean getIsExist() throws RemoteException{ return this.isExist; }
	  public Ticket setIsExist(boolean new_isExist) throws RemoteException{
		this.isExist = new_isExist;
	    return this;
	  }
	  

	  public boolean isPaid() throws RemoteException{ return isPaid; }
	  public Ticket isPaid(boolean m_isPaid) throws RemoteException{
	    isPaid = m_isPaid;
	    return this;
	  }

	  public void voidTicket() throws RemoteException{
		setIsExist(false);
	    this.isVoid = true;
	  }

	  public double calculateFee(double hourly_rate) throws RemoteException{
		  long diff =  exitTime.getTime() - entryTime.getTime();
		  long hourInGarage = diff / (60 * 60 * 1000) % 24 +1;
		  return hourly_rate *hourInGarage;      
	  }
	  
	  
}




