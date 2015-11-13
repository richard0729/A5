package cs414.a5.richard2.server;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import cs414.a5.richard2.common.*;


public class UsageReport {

	private List<Ticket> tickets;
	private long count;
	private Calendar calEntry = Calendar.getInstance();
	private Calendar calExit = Calendar.getInstance();
	private Calendar calReport = Calendar.getInstance();
	private Garage garage;
	
	public UsageReport(Garage m_garage)
	{
		garage = m_garage;
		
	}
	
	public void reportHourly(Date daily)
	{
		try{
		count =0;		
		tickets = garage.getUsageTickets();
		calReport.setTime(daily);
		
		calReport.set(Calendar.MINUTE, 5);
		
		int i;
		for(i=0; i<24;i++)
		{
			count=0;
			calReport.set(Calendar.HOUR_OF_DAY, i);
			for(Ticket ticket : tickets)
			{
				calEntry.setTime(ticket.getEntryTime());
				calEntry.set(Calendar.MINUTE, 0);
				if(ticket.getExitTime() != null )
					calExit.setTime(ticket.getExitTime());
				else
					calExit = Calendar.getInstance();
				calExit.set(Calendar.MINUTE, 10);
				if(calReport.compareTo(calEntry) >=0 && calReport.compareTo(calExit) <=0)
					++count;								
			}
			System.out.println("Garage usage in hour from "+i +" to "+ (i+1)+ " is: " +count);
		}
		//return count;
		}
		  catch(Exception e){}
	}
	
	public void reportDaily(Date daily)
	{
	try{
		count =0;		
		tickets = garage.getUsageTickets();
		calReport.setTime(daily);
		
		calReport.set(Calendar.HOUR_OF_DAY, 5);
		
		int i;
		for(i=1; i<=calReport.getActualMaximum(Calendar.DAY_OF_MONTH);i++)
		{
			count=0;
			calReport.set(Calendar.DAY_OF_MONTH, i);
			for(Ticket ticket : tickets)
			{
				//calEntry.setTime(dateFormat.parse(ticket.getEntryTime().toString()));
				calEntry.setTime(ticket.getEntryTime());
				calEntry.set(Calendar.HOUR_OF_DAY, 1);
				if(ticket.getExitTime() != null )
					calExit.setTime(ticket.getExitTime());
				else
					calExit = Calendar.getInstance();
				calExit.set(Calendar.HOUR_OF_DAY, 10);
				if(calReport.compareTo(calEntry) >=0 && calReport.compareTo(calExit) <=0)
					++count;								
			}
			System.out.println("Garage usage in day  "+i +" is: " +count);
		}
		//return count;
	}
	  catch(Exception e){}
	}
	
	public void reportMonthly(Date daily)
	{
		try{
		count =0;		
		tickets = garage.getUsageTickets();
		calReport.setTime(daily);
		
		calReport.set(Calendar.DAY_OF_MONTH, 5);
		
		int i;
		for(i=0; i<=calReport.getActualMaximum(Calendar.MONTH) ;++i)
		{
			count=0;
			calReport.set(Calendar.MONTH, i);
			for(Ticket ticket : tickets)
			{
				calEntry.setTime(ticket.getEntryTime());
				calEntry.set(Calendar.DAY_OF_MONTH, 1);
				if(ticket.getExitTime() != null )
					calExit.setTime(ticket.getExitTime());
				else
					calExit = Calendar.getInstance();
				calExit.set(Calendar.DAY_OF_MONTH, 10);
				if(calReport.compareTo(calEntry) >=0 && calReport.compareTo(calExit) <=0)
					++count;								
			}
			System.out.println("Garage usage report in month  "+ (i+1) +" is: " +count);
		}
		//return count;
		}
		  catch(Exception e){}
	}
}
