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
	private JButton btnCredit;
	private JButton btnCancel;
	
	private Ticket ticket;
	private CashPayment cashPayment;
	private CreditPayment creditPayment;
	private JButton btnCash;
	private DefaultListModel model;
	private CashPaymentUI cashUI;
	private CardPaymentUI cardUI;
	
	private ExitUIStatus cancelStatus = ExitUIStatus.cancel;
	
	private double  amountDue;
	
	final PaymentTicketUI frameMain;

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
		frameMain= this;
		setTitle("Payment Ticket");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 625, 585);
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
		
		lblAmountDue = new JLabel("Status:");
		lblAmountDue.setBounds(10, 147, 85, 14);
		contentPane.add(lblAmountDue);
		
		btnCredit = new JButton("Credit");		
		btnCredit.addActionListener(new CardPaymentClickAction());
		btnCredit.setBounds(137, 513, 89, 23);
		contentPane.add(btnCredit);
		
		
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new CancelClickAction());

		btnCancel.setBounds(271, 513, 89, 23);
		contentPane.add(btnCancel);
		
		btnCash = new JButton("Cash");
		btnCash.addActionListener(new CashPaymentClickAction());
		btnCash.setBounds(14, 513, 89, 23);
		contentPane.add(btnCash);
		
		model = new DefaultListModel();
		JList list = new JList(model);
		
		
		list.setBounds(10, 172, 350, 316);
		contentPane.add(list);
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
		
		setInitial();
				
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
	
	 private class CashPaymentClickAction extends AbstractAction 
	 {	       
	        @Override
	        public void actionPerformed(ActionEvent e) 
	        {
	        	if( exitUIStatus == ExitUIStatus.payTicket)
	        	{
	        		try
	        	  	{
		        		String ticketID = txtTicket.getText();
		        		ticket = exitClient.getTicket(ticketID, ExitUIStatus.payTicket);
		        		
		        		if(ticket == null )
		        		{
		        			System.out.println("ERROR");
		        			ShowError();
		        			return;
		        		}
			        	Date now = new Date();
			        	ticket.setExitTime(now );
		        		amountDue = ticket.calculateFee(exitClient.getRate());
		        		System.out.println("\nAmount   " +amountDue );
		        		//lblFee.setText(money.format(amountDue));
		        		
		        		setVisible(false);
						cashUI = new CashPaymentUI(ticket, amountDue, exitClient, frameMain  );						
						cashUI.setVisible(true);
	        	  	}
	        		catch(Exception ex)
	        	  	{
	        	  		System.out.print("Exeption:" +ex);
	        	  	}	        		
	        	}
	        	else if( exitUIStatus == ExitUIStatus.payLostTicket)
	        	{
	        		try
	        	  	{
		        		String plate = txtTicket.getText();
		        		ticket = exitClient.getTicket(plate, ExitUIStatus.payLostTicket);
		        		
		        		if(ticket == null )
		        		{
		        			System.out.println("ERROR");
		        			ShowError();
		        			return;
		        		}
			        	Date now = new Date();
			        	ticket.setExitTime(now );
		        		amountDue = ticket.calculateFee(exitClient.getRate());
		        		System.out.println("\nAmount   " +amountDue );
		        		//lblFee.setText(money.format(amountDue));
		        		
		        		setVisible(false);
						cashUI = new CashPaymentUI(ticket, amountDue, exitClient, frameMain  );						
						cashUI.setVisible(true);
	        	  	}
	        		catch(Exception ex)
	        	  	{
	        	  		System.out.print("Exeption:" +ex);
	        	  	}	        		
	        	}
	        	else if( exitUIStatus == ExitUIStatus.noPayTicket)
	        	{
	        		try
	        	  	{
		        		String ticketID = txtTicket.getText();
		        		ticket = exitClient.getTicket(ticketID, ExitUIStatus.payTicket);
		        		
		        		if(ticket == null )
		        		{
		        			System.out.println("ERROR");
		        			ShowError();
		        			return;
		        		}
			        	Date now = new Date();
			        	ticket.setExitTime(now );
			        	int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure cannot pay this ticket? You will not parking in garage next time.","Warning",JOptionPane.YES_NO_OPTION);
			        	if(dialogResult == JOptionPane.YES_OPTION){
			        		exitClient.issueNoPay(ticket);
			        		exitClient.issueNoPayReceipt(ticket);
			        		setReceiptExit();
			        	}

	        	  	}
	        		catch(Exception ex)
	        	  	{
	        	  		System.out.print("Exeption:" +ex);
	        	  	}	        		
	        	}
	        	
	        }
	 }
	 
	 private class CardPaymentClickAction extends AbstractAction 
	 {	       
	        @Override
	        public void actionPerformed(ActionEvent e) 
	        {
	        	if( exitUIStatus == ExitUIStatus.payTicket)
	        	{
	        		try
	        	  	{
		        		String ticketID = txtTicket.getText();
		        		ticket = exitClient.getTicket(ticketID, ExitUIStatus.payTicket);
		        		
		        		if(ticket == null )
		        		{
		        			System.out.println("ERROR");
		        			ShowError();
		        			return;
		        		}
			        	Date now = new Date();
			        	ticket.setExitTime(now );
		        		amountDue = ticket.calculateFee(exitClient.getRate());
		        		System.out.println("\nAmount   " +amountDue );
		        		//lblFee.setText(money.format(amountDue));
		        		
		        		setVisible(false);
		        		cardUI = new CardPaymentUI(ticket, amountDue, exitClient, frameMain  );						
		        		cardUI.setVisible(true);
	        	  	}
	        		catch(Exception ex)
	        	  	{
	        	  		System.out.print("Exeption:" +ex);
	        	  	}	        		
	        	}
	        	else if( exitUIStatus == ExitUIStatus.payLostTicket)
	        	{
	        		try
	        	  	{
		        		String plate = txtTicket.getText().toUpperCase();
		        		ticket = exitClient.getTicket(plate, ExitUIStatus.payLostTicket);
		        		
		        		if(ticket == null )
		        		{
		        			System.out.println("ERROR");
		        			ShowError();
		        			return;
		        		}
			        	Date now = new Date();
			        	ticket.setExitTime(now );
		        		amountDue = ticket.calculateFee(exitClient.getRate());
		        		System.out.println("\nAmount   " +amountDue );
		        		//lblFee.setText(money.format(amountDue));
		        		
		        		setVisible(false);
		        		cardUI = new CardPaymentUI(ticket, amountDue, exitClient, frameMain  );						
						cashUI.setVisible(true);
	        	  	}
	        		catch(Exception ex)
	        	  	{
	        	  		System.out.print("Exeption:" +ex);
	        	  	}	        		
	        	}
	        }
	 }
	
	 public void setReceiptExit()
	 {
		try
 	  	{
			//System.out.println(" Run Test");
			//if(this.exitClient.receipt !=null || exitUIStatus == ExitUIStatus.noPayTicket)
			if(this.exitClient.receipt !=null )
	 		{
	 			if (!model.isEmpty()) {
	                model.clear();
	            }

	 			txtTicket.disable();
	 			this.btnCash.disable();
	 			this.btnCash.setVisible(false);
	 			this.btnCredit.disable();
	 			this.btnCredit.setVisible(false);
	 			this.btnCancel.setText("Continue");
	 			
	 			model.addElement("Printing receipt : " );
	 			model.addElement(" " );
	 			model.addElement("Ticket ID : " + ticket.getId());
				model.addElement("Plate Lisence : " + ticket.getPlateLisence());
				Date entryTime = ticket.getEntryTime();
				model.addElement("Entry Time : " + dateFormat.format(entryTime));
				Date exitTime = ticket.getExitTime();
				model.addElement("Exit Time : " + dateFormat.format(exitTime));
				
				if (exitUIStatus == ExitUIStatus.noPayTicket)
				{
					model.addElement("You are choose to cannot pay ticket ");
					model.addElement("");
					model.addElement("Plate Lisence : " + ticket.getPlateLisence()+" will not parking in garage next time!");
					model.addElement("");					
				}
				else
				{
					if(this.exitClient.receipt.getPaymentType() == PaymentType.Cash )
					{
						cashPayment =this.exitClient.receipt.getCashPayment();
						model.addElement("Total : " + money.format(cashPayment.getAmountFee()));
						model.addElement("Cash Tend : " + money.format(cashPayment.getTotalPaid()));
						model.addElement("Change : " + money.format(cashPayment.getBalanceCash()));
					}
					else if(this.exitClient.receipt.getPaymentType() == PaymentType.Credit )
					{
						creditPayment =this.exitClient.receipt.getCreditPayment();
						model.addElement("Total : " + money.format(creditPayment.getAmountFee()));
						model.addElement("Ticket is payment by card");					
					}
				}
				model.addElement(" " );
				model.addElement("Entry gate is opened.");
    			lblGate.setText("open");
    			//frameMain.wait(10000);
    			
    			//Thread.sleep(5000);
    			
    			model.addElement("Please exit garage. Press button Continue"); 
    			cancelStatus = ExitUIStatus.waitExit;
	 		}
 	  	}
		catch(Exception ex)
	  	{
	  		System.out.print("Exeption 5:" +ex);
	  	}
	 }
	 
	 private class CancelClickAction extends AbstractAction 
	 {	       
	        @Override
	        public void actionPerformed(ActionEvent e) 
	        {
	        	if( cancelStatus == ExitUIStatus.cancel) // cancel purchase
	        	{
	        		setInitial();
	        	}
	        	else if( cancelStatus == ExitUIStatus.waitExit) // cancel purchase
	        	{
	        		//JOptionPane.showMessageDialog(null, "Please exit garage. Press Continue");
	    			exitClient.ExitSuceess(ticket);
	    			model.addElement("You have exit garage.");
	    			model.addElement("Entry gate is closed.");   
	    			model.addElement("Please press continue.");   
	    			lblGate.setText("close");	    			
	    			setStatus();
	    			cancelStatus = ExitUIStatus.waitContinue;
	        	}
	        	
	        	else if( cancelStatus == ExitUIStatus.waitContinue) // cancel purchase
	        	{
	        		//btnCancel.setText("Cancel");
	        		cancelStatus = ExitUIStatus.cancel;
	        		setInitial();
	        	}
	        		
	        }
	        
	 }

	public void setInitial()
	{
		if( exitUIStatus == ExitUIStatus.payTicket)
    	{
			setStatus() ;
			if (!model.isEmpty()) {
                model.clear();
            }
			this.setTitle("Payment Ticket");
			this.lblTicket.setText("Ticket ID:");
			
			this.txtTicket.enable();
			this.txtTicket.setText("");
			this.btnCash.setText("Cash");
			this.btnCash.setVisible(true);
			this.btnCash.enable();
			this.btnCredit.enable();
			this.btnCredit.setVisible(true);
			this.btnCancel.setText("Cancel");

    	}
		else if( exitUIStatus == ExitUIStatus.payLostTicket)
    	{
			setStatus() ;
			if (!model.isEmpty()) {
                model.clear();
            }
			this.lblTicket.setText("Plate lisence:");
			this.setTitle("Payment Lost Ticket");			
			this.txtTicket.enable();
			this.txtTicket.setText("");
			this.btnCash.setText("Cash");
			this.btnCash.setVisible(true);
			this.btnCash.enable();
			this.btnCredit.setVisible(true);
			this.btnCredit.enable();
			this.btnCancel.setText("Cancel");
    	}
		
		else if( exitUIStatus == ExitUIStatus.noPayTicket)
    	{
			setStatus() ;
			if (!model.isEmpty()) {
                model.clear();
            }
			this.lblTicket.setText("Ticket ID:");
			this.setTitle("Cannot Pay Ticket");			
			this.txtTicket.enable();
			this.txtTicket.setText("");
			this.btnCash.setText("Cannot Pay");
			this.btnCash.setVisible(true);
			this.btnCash.enable();
			this.btnCredit.setVisible(false);
			this.btnCredit.disable();;
			this.btnCancel.setText("Cancel");
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
