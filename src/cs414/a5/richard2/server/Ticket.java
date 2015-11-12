package cs414.a5.richard2.server;

import java.util.Date;


public class Ticket {

  private int id; /** Simple integer-based ticket number */
  private Date entryTime;
  private Date exitTime;
  private boolean isExist = false;
  private boolean isPaid = false;
  private boolean isVoid = false;
  
  public Ticket(int ticketId) {    
	    this.id = ticketId;
	  }

  public boolean getIsVoid() { return isVoid; }
  
  public int getId() { return id; }
  public void setId(int m_Id) { this.id = m_Id; }

  public Date getEntryTime() { return entryTime; }
  
  public void setEntryTime(Date m_entryTime) {
    this.entryTime = m_entryTime;
  }
  
  public Date getExitTime() { return exitTime; }
  
  public void setExitTime(Date m_exitTime) {
    this.exitTime = m_exitTime;
  }
  
  
  public Ticket enterNow(Date m_entryTime) {
	entryTime = m_entryTime;
	setIsExist(true);
    return this;
  }
  
  public Ticket getExitTime(Date new_exit_time) {
	this.exitTime = new_exit_time;
    return this;
  }
  
  public Ticket exitNow(Date m_exitTime) {
	  this.exitTime = m_exitTime;
	  setIsExist(false);
    return this;
  }

  
  public boolean getIsExist() { return this.isExist; }
  public Ticket setIsExist(boolean new_isExist) {
	this.isExist = new_isExist;
    return this;
  }
  

  public boolean isPaid() { return isPaid; }
  public Ticket isPaid(boolean m_isPaid) {
    isPaid = m_isPaid;
    return this;
  }

  public void voidTicket() {
	setIsExist(false);
    this.isVoid = true;
  }

  public double calculateFee(double hourly_rate) {
	  long diff =  exitTime.getTime() - entryTime.getTime();
	  long hourInGarage = diff / (60 * 60 * 1000) % 24 +1;
	  return hourly_rate *hourInGarage;      
  }

}
