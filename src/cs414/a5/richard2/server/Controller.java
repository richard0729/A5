package cs414.a5.richard2.server;

import java.text.DecimalFormat;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.ParseException;
import cs414.a5.richard2.common.*;

public class Controller extends Viewer  {

	private DecimalFormat df = new DecimalFormat("0.00");
	//private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
	  
	private Scanner input = new Scanner(System.in);
	private Receipt receipt;
	private int ticketID;
	private Ticket ticket;
	private double amountDue;
	private UsageReport report;
	private Date dateReport;
	private long n;	  
	private Garage garage;


	public Controller(Garage garage) {
	    this.garage = garage;
	    report = new UsageReport(garage);
	}

	  public void print_status() {
		  this.print_garage_status();
	  }
	  
	  public void print_garage_status() {
		  	Date now = new Date();
		    int capacity = garage.getMaxSpaces() - garage.getUsedSpaces();
		    System.out.println("");
		    System.out.println("");
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
		    System.out.println("");
		    System.out.println("");
		  }


	  public void setRate() {
	    String input = printString("New rate ($/hr): ", "");
	    double newRate;
	    try {
	    	newRate = Double.parseDouble(input);
	    }
	    catch (NumberFormatException e) {
	      System.out.println("Invalid rate!");
	      printContinue();
	      return;
	    }
	    if(newRate <= 0.0) {
	      System.out.println("Invalid rate!");
	      printContinue();
	      return;
	    }
	    garage.setFeeRate(newRate);
	  }

	  public void SetMaxSpaces() {
	    String input = printString("New max Spaces: ", "");
	    int newMax;
	    try {
	    	newMax = Integer.parseInt(input);
	    }
	    catch (NumberFormatException e) {
	      System.out.println("Invalid max Spaces!");
	      printContinue();
	      return;
	    }
	    if(newMax <= 0) {
	      System.out.println("Invalid max Spaces!");
	      printContinue();
	      return;
	    }
	    garage.setMaxSpaces(newMax);
	  }
	  
	  public void UsageMenu() {
		    while(true) {		    
		      int choice =printMenu("Garage Usage Report ", new String[]{
		        "Hourly",
		        "Daily",
		        //"Weekly",
		        "Monthly",
		        "Return to Main Menu"
		      });
		      switch (choice) {
		        case 1:
		        	String expected2 = "MM/dd/yyyy";
		            SimpleDateFormat formatter2 = new SimpleDateFormat(expected2);
		        	String dateInput2 = printString("Enter day (MM/dd/yyyy) for hourly report: ", "");		        	
		        	try{
		        		dateReport = formatter2.parse(dateInput2);
		        	}
		        	catch(ParseException e)
		        	{
		        		System.out.println("Input wrong hourly (MM/dd/yyyy)");
		        		printContinue();
		        		return;
		        	}
		        	System.out.println("Garage usage in hourly of "+formatter2.format(dateReport) );
		        	report.reportHourly(dateReport);		        	
		        	printContinue();
			        break;
		        case 2:
		        	String expectedPattern = "MM/yyyy";
		            SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
		        	String dateInput = printString("Enter month (MM/yyyy) for daily report: ", "");
		        	
		        	try{
		        		dateReport = formatter.parse(dateInput);
		        	}
		        	catch(ParseException e)
		        	{
		        		System.out.println("Input wrong month (MM/yyyy)");
		        		printContinue();
		        		return;
		        	}
		        	System.out.println("Garage usage daily in month "+formatter.format(dateReport));
		        	report.reportDaily(dateReport);
		        	
		        	printContinue();
			        break;
		        case 3:
		        	String expected1 = "yyyy";
		            SimpleDateFormat formatter1 = new SimpleDateFormat(expected1);
		        	String dateInput1 = printString("Enter year (yyyy) for monthly report: ", "");		        	
		        	try{
		        		dateReport = formatter1.parse(dateInput1);
		        	}
		        	catch(ParseException e)
		        	{
		        		System.out.println("Input wrong year (yyyy)");
		        		printContinue();
		        		return;
		        	}
		        	System.out.println("Garage usage monthly report in year "+formatter1.format(dateReport));
		        	report.reportMonthly(dateReport);
		        	
		        	printContinue();
			        break;
		        case 4:
		          return;
		        default:
		          System.out.println("Unknown option.");
		          break;
		      }
		    }
		  }

	  public void garage_management() {
	    while(true) {
	      print_status();
	      int choice = printMenu("Garage Management", new String[]{
	        "Modify rate",
	        "Modify maximum capacity",
	        "Usage reports",
	        "[Return to Main Menu]"
	      });

	      switch (choice) {
	        case 1:
	          setRate();
	          break;
	        case 2:
	          SetMaxSpaces();
	          break;
	        case 3:
	          UsageMenu();
	          break;
	        case 4:
	          return;
	        default:
	          System.out.println("Unknown option.");
	          break;
	      }
	    }
	  }

	  public void main_menu() {
	    while(true) {
	      print_status();
	      int choice = printMenu("Main", new String[]{
	        "Garage Entry ",
	        "Garage Exit ",
	        "Garage Management",
	        "Exit Program"
	      });

	      switch (choice) {
	        case 1: 
	        	entryMenu();
	        	break;
	        case 2:
	        	exitMenu();
	        	break;
	        case 3:
	        	garage_management();
	        	break;
	        case 4:
	        	return;
	        default:
	          System.out.println("Unknown option.");
	          break;
	      }
	    }
	  }
	  
	  public void entryMenu() {
		    while(true) {
		    	this.print_status();
		      int choice =printMenu("Garage Entry ", new String[]{
		        "Purchase Ticket",
		        "Return to Main Menu"
		      });
		      switch (choice) {
		        case 1:
		          purchaseTicket();
		          break;
		        case 2:
		          return;
		        default:
		          System.out.println("Unknown option.");
		          break;
		      }
		    }
		  }


	  public void purchaseTicket() {
		  try{
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
		  	else
		  	{
		  		System.out.println("Garage is FULL.");
			    printContinue();
			    return;
		  	}
		  }
		  catch(Exception e){}
		  }
	  
	  public void exitMenu() {
		  try{
		    while(true) {
		    	this.print_status();
		    	int choice =printMenu("Garage Exit ", new String[]{
		        "Payment Ticket",
		        "Return to Main Menu"
		    	});
		    	
		    	switch (choice) {
			        case 1:
			        	getTicketID();
			        	ticket =garage.getTicket(ticketID);
			        	if(ticket == null) 
			        	{
			        		System.out.println("\nInvalid ticket ID.\n"); 
			        		printContinue(); 
			        		break;
			        	}
			        	Date now = new Date();
			        	ticket.setExitTime(now );
						amountDue = ticket.calculateFee(garage.getFeeRate());
						System.out.println("\nAmount due: " + df.format(amountDue) + "\n"); 
						paymentTicket(amountDue, ticket, false);
						printContinue();
						break;
						
			        case 2:
			          return;
			          
			        default:
			          System.out.println("Unknown option.");
			          break;
		    	}
		    }
		  }
		  catch(Exception e){}
		  
	  }
	  public int getTicketID(){
		  /*
			System.out.println("Enter ticket ID: ");
			ticketID = input.nextInt();
			return ticketID;
			*/
			boolean checkInvalid= false;
			do
			{
			
				String input = printString("Enter ticket ID: ", "");
				try {
					//ticketID = Double.parseDouble(input);
					ticketID = Integer.parseInt(input);
			    }
			    catch (NumberFormatException e) {
			      System.out.println("Invalid ticket ID");
			      checkInvalid =true;
			    }				
			}while( checkInvalid);
			return ticketID;
		}
	  
	  public void paymentTicket(double amountDue, Ticket ticket, boolean isFlatRate){
			
			int choice =printMenu("Please Select an option Payment", new String[]{
			        "Cash Payment",
			        "Credit Payment",
			        "Return to Main Menu"
			      });
			switch(choice)
			{
				case 1: 
					//System.out.println("Please enter cash amount: ");
					//double paymentAmount = input.nextInt();
					double paymentAmount ;
					do
					{
							
						//System.out.println("Please enter again cash amount: ");
						//paymentAmount = input.nextInt();
						
						String input = printString("Please enter cash amount: ", "");
						try {
							paymentAmount = Double.parseDouble(input);
					    }
					    catch (NumberFormatException e) {
					      System.out.println("Invalid cash amount!");
					      return;
					    }
						if(paymentAmount < amountDue )
							System.out.println("Cash amount is less than amount due: $"+df.format(amountDue));
					}while( paymentAmount < amountDue);
					CashPayment cp = new CashPayment(amountDue, paymentAmount);
					System.out.println("Please take balance change cash: $" + df.format(cp.getBalanceCash())); 
					printContinue();
					receipt = new Receipt(cp);
					receipt.printReceipt();
					garage.addReceipt(receipt);
					garage.exitSuccess(ticket);
			       // System.out.println("[You have exited garage.]");
					break;	
				case 2: 
					System.out.println("Please enter account number (16 digits no dashes): ");
					String accountNum = input.next();
					System.out.println("Please enter expiration date (format MM/yyyy): ");
					String expDate = input.next();					
					CreditPayment ep = new CreditPayment( accountNum, expDate,amountDue);
					while(!ep.isAccountValid()){
						System.out.println("Please enter again account number (16 digits no dashes): ");
						accountNum = input.next();
						System.out.println("Please enter again expiration date (format MM/yyyy): ");
						expDate = input.next();
						
						ep = new CreditPayment( accountNum, expDate,amountDue);
					}
					receipt = new Receipt(ep);
					receipt.printReceipt();
					garage.addReceipt(receipt); 
					garage.exitSuccess(ticket);
			        //System.out.println(" You have exited garage.");
					break;
				case 3: break;
				default:
					System.out.println("Invalid selection.\n");
					//paymentTicket(amountDue, ticket, isFlatRate); 
					break;
			}
		}
	  
	public static void main(String[] args) {
		Garage garage = new Garage();
		Controller mController = new Controller(garage);
		mController.main_menu();
	  }

	
}
