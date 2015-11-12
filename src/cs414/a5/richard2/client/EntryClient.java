package cs414.a5.richard2.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.DecimalFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;

import cs414.a5.richard2.common.*;

public class EntryClient extends JFrame {

	private JPanel contentPane;
	private JLabel lblSign;
	final  JLabel lblMaxSpaces= new JLabel("New label");
	
	private ParkingGarage garage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EntryClient frame = new EntryClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		//print_garage_status();
		
	}

	/**
	 * Create the frame.
	 */
	public EntryClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblSign = new JLabel("Sign:");
		lblSign.setBounds(5, 5, 424, 14);
		contentPane.add(lblSign);
		
		//JLabel lblMaxSpaces = new JLabel("New label");
		lblMaxSpaces.setBounds(5, 30, 285, 14);
		contentPane.add(lblMaxSpaces);
		
		connectServer();
		print_garage_status();
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
		    lblMaxSpaces.setText("Max Spaces: " + garage.getMaxSpaces());
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
