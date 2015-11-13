package cs414.a5.richard2.client;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;

import cs414.a5.richard2.common.*;

public class EntryKiosk {

	private ParkingGarage garage;
	private String signStatus;
	private double rate;
	private int capacity;
	private int spaces;
	
	public void connectServer()
	{
		String url = new String("rmi://localhost:2001/ParkingGarageService");

		//String url = new String("rmi://" + args[0] + ":" + args[1]  + "/ParkingGarageService");
		try {
			//ParkingGarage garage = (ParkingGarage) Naming.lookup(url);
			garage = (ParkingGarage) Naming.lookup(url);
			System.out.println("Server is connected sussessful");
			System.out.print("\t	Max Spaces: " + garage.getMaxSpaces());
			
			
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
	
	public void print_garage_status() {
	  	try
	  	{
			//Date now = new Date();
		  	
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
	
}
