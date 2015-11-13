package cs414.a5.richard2.common;
import java.rmi.*;
import java.util.Date;


public interface Ticket extends java.rmi.Remote{
	
	public boolean getIsVoid() throws RemoteException;
	
	public int getId() throws RemoteException;
	
	public void setId(int m_Id) throws RemoteException;
	
	public String getPlateLisence() throws RemoteException;
	
	public void setPlateLisence(String m_plateLisence) throws RemoteException;
	
	public Date getEntryTime() throws RemoteException;
	
	public void setEntryTime(Date m_entryTime) throws RemoteException;
	
	public Date getExitTime() throws RemoteException;
	
	public void setExitTime(Date m_exitTime) throws RemoteException;
	
	public Ticket enterNow(Date m_entryTime) throws RemoteException;
	
	public Ticket getExitTime(Date new_exit_time) throws RemoteException;
	
	public Ticket exitNow(Date m_exitTime) throws RemoteException;
	
	public boolean getIsExist() throws RemoteException;
	
	public Ticket setIsExist(boolean new_isExist) throws RemoteException;
	
	public boolean isPaid() throws RemoteException;
	
	public Ticket isPaid(boolean m_isPaid) throws RemoteException;
	
	public void voidTicket() throws RemoteException;
	
	public double calculateFee(double hourly_rate) throws RemoteException;
	

}
