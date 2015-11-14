package cs414.a5.richard2.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.net.MalformedURLException;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.AbstractAction;


import cs414.a5.richard2.common.*;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;


public class PaymentTicketUI extends JFrame {

	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
	private DecimalFormat money = new DecimalFormat("$0.00");
	private ExitClient exitClient;
	
	private JPanel contentPane;
	private JLabel lblCurrentTime;
	private JLabel lblRate ;
	private JLabel lblCapacity ;
	private JLabel lblSpaces;
	private JLabel lblEntryGate ;	
	private JLabel lblSign ;
	private JLabel lblGate ;
	
	public ExitUIStatus exitUIStatus = ExitUIStatus.payTicket;
	private JLabel lblTicket;
	private JTextField txtTicket;
	private JLabel lblAmountDue ;
	private JLabel lblFee ;
	
	private JButton btnPayment ;
	private JButton btnBack ;
	private JButton btnCredit;
	private JButton btnCancel;
	
	private Ticket ticket;
	private JButton btnCash;
	
	private double  amountDue;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentTicketUI frame = new PaymentTicketUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PaymentTicketUI() {
		setTitle("Payment Ticket");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 625, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//group garage sign
		JLabel lblNewLabel = new JLabel("Current time:");
		lblNewLabel.setBounds(10, 11, 78, 14);
		contentPane.add(lblNewLabel);
		
		lblCurrentTime = new JLabel("time");
		lblCurrentTime.setBounds(98, 11, 112, 14);
		contentPane.add(lblCurrentTime);
		
		JLabel lblNewLabel_1 = new JLabel("Sign :");
		lblNewLabel_1.setBounds(10, 36, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		lblSign = new JLabel("available");
		lblSign.setBounds(98, 36, 85, 14);
		contentPane.add(lblSign);
		
		JLabel lblNewLabel_2 = new JLabel("Rate :");
		lblNewLabel_2.setBounds(271, 36, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		lblRate = new JLabel("rate");
		lblRate.setBounds(339, 36, 78, 14);
		contentPane.add(lblRate);
		
		JLabel lblNewLabel_3 = new JLabel("Capacity :");
		lblNewLabel_3.setBounds(10, 61, 67, 14);
		contentPane.add(lblNewLabel_3);
		
		lblCapacity = new JLabel("capacity");
		lblCapacity.setBounds(98, 61, 85, 14);
		contentPane.add(lblCapacity);
		
		JLabel lblNewLabel_4 = new JLabel("Spaces :");
		lblNewLabel_4.setBounds(259, 61, 58, 14);
		contentPane.add(lblNewLabel_4);
		
		lblSpaces = new JLabel("Spaces");
		lblSpaces.setBounds(339, 61, 78, 14);
		contentPane.add(lblSpaces);
		
		lblEntryGate = new JLabel("Exit Gate :");
		lblEntryGate.setBounds(244, 11, 78, 14);
		contentPane.add(lblEntryGate);
		
		lblGate = new JLabel("close");
		lblGate.setBounds(337, 11, 46, 14);
		contentPane.add(lblGate);
		
		lblTicket = new JLabel("Ticket ID :");
		lblTicket.setBounds(10, 111, 93, 14);
		contentPane.add(lblTicket);
		
		txtTicket = new JTextField();
		txtTicket.setBounds(124, 108, 123, 20);
		contentPane.add(txtTicket);
		txtTicket.setColumns(10);
		
		btnPayment = new JButton("Payment");
		btnPayment.addActionListener(new PaymentClickAction());
		btnPayment.setBounds(294, 107, 89, 23);		
		contentPane.add(btnPayment);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(415, 107, 89, 23);
		contentPane.add(btnBack);
		
		lblAmountDue = new JLabel("Amount Due :");
		lblAmountDue.setBounds(10, 147, 85, 14);
		contentPane.add(lblAmountDue);
		
		lblFee = new JLabel("$0.00");
		lblFee.setBounds(124, 147, 46, 14);
		contentPane.add(lblFee);
		
		btnCredit = new JButton("Credit");		
		btnCredit.setBounds(124, 189, 89, 23);
		contentPane.add(btnCredit);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(271, 189, 89, 23);
		contentPane.add(btnCancel);
		
		btnCash = new JButton("Cash");
		btnCash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				try {
					CashPaymentUI cashUI = new CashPaymentUI(ticket, amountDue, exitClient );
					cashUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnCash.setBounds(14, 189, 89, 23);
		contentPane.add(btnCash);
		//End group garage sign
		String url = new String("rmi://localhost:2001/ParkingGarageService");		
		try {
			ParkingGarage g = (ParkingGarage) Naming.lookup(url);
			System.out.println("Server is connected sussessful");
			//System.out.print("\t	Max Spaces: " + garage.getMaxSpaces());
			exitClient = new ExitClient(g);
		}

		catch (MalformedURLException murle) {
              System.out.println("MalformedURLException");
              System.out.println(murle);
          } 
		catch (RemoteException re) {
              System.out.println("RemoteException"); 
              System.out.println(re);
          } 
		catch (NotBoundException nbe) {
              System.out.println("NotBoundException");
              System.out.println(nbe);
          } 
		catch (java.lang.ArithmeticException ae) {
               System.out.println("java.lang.ArithmeticException");
               System.out.println(ae);
          }
		
		setButtonText();
				
	}
	
	public void ShowError()
	{
		if (exitClient.error == ErrorExit.errorRemote)
			JOptionPane.showMessageDialog(null, "Remote Exception" +exitClient.remoteException );
		else if (exitClient.error == ErrorExit.invalidTicket)
			JOptionPane.showMessageDialog(null, "ERROR: You are input invalid of Ticket ID");
		else if (exitClient.error == ErrorExit.invalidPlate)
			JOptionPane.showMessageDialog(null, "ERROR: You are input invalid Plate Lisence ");
	
	}
	
	 private class PaymentClickAction extends AbstractAction 
	 {	       
	        @Override
	        public void actionPerformed(ActionEvent e) 
	        {
	        	if( exitUIStatus == ExitUIStatus.payTicket)
	        	{
	        		try
	        	  	{
		        		String ticketID = txtTicket.getText();
		        		ticket = exitClient.getTicket(ticketID, true);
		        		
		        		if(ticket == null )
		        		{
		        			System.out.println("ERROR");
		        			ShowError();
		        			return;
		        		}
		        		
		        		System.out.println("\nticket ID: " +ticket.getId() +" Plate:" +ticket.getPlateLisence() );
		        		
		        		System.out.println("\nRate  " +exitClient.getRate() );
			        	Date now = new Date();
			        	ticket.setExitTime(now );
		        		amountDue = ticket.calculateFee(exitClient.getRate());
		        		System.out.println("\nAmount   " +amountDue );
		        		lblFee.setText(money.format(amountDue));
	        	  	}
	        		catch(Exception ex)
	        	  	{
	        	  		System.out.print("Exeption:" +ex);
	        	  	}
	        		//ticket =garage.getTicket(ticketID);
	        		
	        	/*
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
				*/
	        	}
	        }
	 }
	 	 

	public void setButtonText()
	{
		if( exitUIStatus == ExitUIStatus.payTicket)
    	{
			setStatus() ;
			this.setTitle("Payment Ticket");
			/*
			model.clear();
			txtPlateLicense.setText("");
			txtPlateLicense.setVisible(true);
			this.btnPurchaseTicket.enable();
			this.btnPurchaseTicket.setVisible(true);
			this.btnPurchaseTicket.setText("Purchase Ticket");
			this.btnCancel.setText("Cancel");
			lblGate.setText("close");
			*/
    	}
		else if( exitUIStatus == ExitUIStatus.payLostTicket)
    	{
			setStatus() ;
			this.setTitle("Payment Lost Ticket");
    	}
	}
	
	public void setStatus() 
	{
	  	try
	  	{
	  		exitClient.get_garage_status();
	  		Date now = new Date();
	  		this.lblCurrentTime.setText(dateFormat.format(now)); 
	  		this.lblSign.setText(exitClient.getStringStatus());
	  		this.lblRate.setText(money.format(exitClient.getRate()) + "/hr");
	  		this.lblCapacity.setText(String.valueOf(exitClient.getCapacity()) );
	  		this.lblSpaces.setText(String.valueOf(exitClient.getSpaces()) );
	  	}
	  	catch(Exception e)
	  	{
	  		System.out.print("Exeption:" +e);
	  	}
	}
}
