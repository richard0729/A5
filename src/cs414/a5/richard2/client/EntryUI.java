package cs414.a5.richard2.client;

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

public class EntryUI extends JFrame {

	//private DecimalFormat df = new DecimalFormat("0.00");	
	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
	private DecimalFormat money = new DecimalFormat("$0.00");
	
	private JPanel contentPane;
	private JLabel lblCurrentTime;
	private JLabel lblRate ;
	private JLabel lblCapacity ;
	private JLabel lblSpaces;
	private JLabel lblEntryGate ;
	private JLabel lblSign ;
	private JLabel lblGate ;
	
	
	private JTextField txtPlateLicense;
	private JList lstShow ;
	private DefaultListModel model;
	private JButton btnPurchaseTicket;
	private JButton btnCancel ;
	
	private Ticket ticket;
	private EntryKiosk entry;
	
	private EntryUIStatus entryUIStatus; 
	/**
	 * Launch the application.
	 */
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EntryUI frame = new EntryUI();
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
	public EntryUI() {
		setTitle("Entry Gate");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 476);
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
		
		lblEntryGate = new JLabel("Entry Gate :");
		lblEntryGate.setBounds(244, 11, 78, 14);
		contentPane.add(lblEntryGate);
		
		lblGate = new JLabel("close");
		lblGate.setBounds(337, 11, 46, 14);
		contentPane.add(lblGate);
		//End group garage sign
		
		btnPurchaseTicket = new JButton("Purchase Ticket");
		btnPurchaseTicket.addActionListener(new PurchaseClickAction());
		
		btnPurchaseTicket.setBounds(10, 400, 132, 38);
		contentPane.add(btnPurchaseTicket);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(386, 400, 119, 38);
		contentPane.add(btnExit);
		

		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new CancelClickAction());
		btnCancel.setBounds(228, 400, 105, 38);
		contentPane.add(btnCancel);
		
		JLabel lblPlateLicense = new JLabel("Plate License :");
		lblPlateLicense.setBounds(10, 110, 78, 14);
		contentPane.add(lblPlateLicense);
		
		txtPlateLicense = new JTextField();
		txtPlateLicense.setBounds(98, 107, 141, 20);
		contentPane.add(txtPlateLicense);
		txtPlateLicense.setColumns(10);
		
		model = new DefaultListModel();
		lstShow = new JList(model);
		lstShow.setBounds(10, 144, 468, 245);
		contentPane.add(lstShow);
		
		
		
		String url = new String("rmi://localhost:2001/ParkingGarageService");

		//String url = new String("rmi://" + args[0] + ":" + args[1]  + "/ParkingGarageService");
		try {
			ParkingGarage g = (ParkingGarage) Naming.lookup(url);
			System.out.println("Server is connected sussessful");
			//System.out.print("\t	Max Spaces: " + garage.getMaxSpaces());
			entry = new EntryKiosk(g);
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
		//entry = new EntryKiosk();
		//entry.get_garage_status();
		setStatus() ;
		entryUIStatus = EntryUIStatus.purchase;
	}
	
	 private class PurchaseClickAction extends AbstractAction 
	 {	       
	        @Override
	        public void actionPerformed(ActionEvent e) 
	        {
	        	if( entryUIStatus == EntryUIStatus.purchase)
	        	{
	        		String m_plateLisence = txtPlateLicense.getText();
	        		
	        		ticket =entry.issueTicket(m_plateLisence);
	        		if(ticket !=null)
	        		{
	        			if (!model.isEmpty()) {
			                model.clear();
			            }
	        			try
	        			{
		        			int ticketID = ticket.getId();
		        			model.addElement("Ticket ID : " + ticketID);
		        			model.addElement("Plate Lisence : " + m_plateLisence);
		        			Date entryTime = ticket.getEntryTime();
		        			model.addElement("Entry Time : " + dateFormat.format(entryTime));
		        			model.addElement("");
		        			model.addElement("Is ticket printing?");
		        			entryUIStatus =EntryUIStatus.print;
		        			setButtonText();
		        			//setStatus() ;
				            //model.addElement("Time: " + dateFormat.format(date));
	        			}
	        			catch (RemoteException re) 
	        			{
	        				model.addElement(re);
	        			}
	        		}
	        		else
	        		{
	        			if(entry.error == EntryError.invalidPlateLisence)
	        			{
	        				model.clear();
	        				model.addElement("Invalid Plate Lisence of car (6-7 character or number)");
	        			}
	        			else if(entry.error == EntryError.errorRemote)
	        			{
	        				model.clear();
	        				model.addElement("Remote Exception:" + entry.remoteException);
	        			}
	        			else if(entry.error == EntryError.full)
	        			{
	        				model.clear();
	        				model.addElement("Parking is full now. Please waiting!");
	        			}
	        			else if(entry.error == EntryError.errorExist)
	        			{
	        				model.clear();
	        				model.addElement("Plate Lisence " +m_plateLisence+" is parking in garage!" );
	        			}	        				        			
	        		}
	        	}// end if entryUIStatus
	        	else if( entryUIStatus == EntryUIStatus.print)
	        	{
	        		model.addElement("Yes");
        			model.addElement("\n");
        			model.addElement("Please get ticket.");
        			model.addElement("Entry gate is opened.");
        			lblGate.setText("open");
        			model.addElement("Are you enter to garage?");
        			entryUIStatus =EntryUIStatus.openGate;
        			setButtonText();	        		
	        	}// end if entryUIStatus
	        	
	        	else if( entryUIStatus == EntryUIStatus.openGate)
	        	{
	        		model.addElement("Yes");
        			model.addElement("\n");       		
        			model.addElement("You have entered garage.");
        			model.addElement("Entry gate is closed.");   
        			lblGate.setText("close");
        			entry.enterSuccess(ticket);
        			entryUIStatus =EntryUIStatus.closeGate;
        			setButtonText();	        		
	        	}// end if entryUIStatus
	        }
	 }
	 
	 private class CancelClickAction extends AbstractAction 
	 {	       
	        @Override
	        public void actionPerformed(ActionEvent e) 
	        {
	        	if( entryUIStatus == EntryUIStatus.purchase) // cancel purchase
	        	{
	        		ticket =null;	        		
	        		model.clear();
	        		txtPlateLicense.setText("");
	        		setButtonText();
	        		
	        		
	        	}// end if entryUIStatus
	        	else if( entryUIStatus == EntryUIStatus.print) // print fail
	        	{	        		
	        		if(ticket !=null)
	        		{

	        				entry.printFail(ticket);
		        			model.addElement("ERROR Print Ticket - Ticket is VOIDED.");
		        			entryUIStatus =EntryUIStatus.printFail;
		        			setButtonText();

	        		}
        			setButtonText();	        		
	        	}// end if entryUIStatus
	        	else if( entryUIStatus == EntryUIStatus.openGate) // open fail
	        	{	        		
	        		if(ticket !=null)
	        		{
	        				
	        				entry.printFail(ticket);
		        			model.addElement("Customer does not enter garage - Ticket is VOIDED.");
		        			model.addElement("Entry gate is closed.");
		        			entryUIStatus =EntryUIStatus.openFail;
		        			setButtonText();
		        			lblGate.setText("close");
	        		}
        			setButtonText();	        		
	        	}// end if entryUIStatus
	        	else if( entryUIStatus == EntryUIStatus.closeGate) // close gate
	        	{	        		
	        		entryUIStatus =EntryUIStatus.purchase;
        			setButtonText();	        		
	        	}// end if entryUIStatus
	        	else if( entryUIStatus == EntryUIStatus.printFail) // print fail
	        	{	        		
	        		entryUIStatus =EntryUIStatus.purchase;
        			setButtonText();	        		
	        	}// end if entryUIStatus
	        	else if( entryUIStatus == EntryUIStatus.openFail) // print fail
	        	{	        		
	        		entryUIStatus =EntryUIStatus.purchase;
        			setButtonText();	        		
	        	}// end if entryUIStatus
	        }
	 }
	 
	public void setButtonText()
	{
		if( entryUIStatus == EntryUIStatus.purchase)
    	{
			setStatus() ;
			model.clear();
			txtPlateLicense.setText("");
			txtPlateLicense.setVisible(true);
			this.btnPurchaseTicket.enable();
			this.btnPurchaseTicket.setVisible(true);
			this.btnPurchaseTicket.setText("Purchase Ticket");
			this.btnCancel.setText("Cancel");
			lblGate.setText("close");
    	}
		else if( entryUIStatus == EntryUIStatus.print)
    	{
			txtPlateLicense.setVisible(false);
			this.btnPurchaseTicket.setText("Yes");
			this.btnCancel.setText("No");
    	}
		else if( entryUIStatus == EntryUIStatus.openGate)
    	{
			txtPlateLicense.setVisible(false);
			this.btnPurchaseTicket.setText("Yes");
			this.btnCancel.setText("No");
    	}
		else if( entryUIStatus == EntryUIStatus.closeGate)
    	{
			txtPlateLicense.setVisible(false);
			this.btnPurchaseTicket.setVisible(false);
			this.btnPurchaseTicket.disable();
			this.btnCancel.setText("Continue");
    	}
		else if( entryUIStatus == EntryUIStatus.printFail)
    	{
			this.btnPurchaseTicket.setVisible(false);
			this.btnPurchaseTicket.disable();
			this.btnCancel.setText("Continue");
    	}
		else if( entryUIStatus == EntryUIStatus.openFail)
    	{
			this.btnPurchaseTicket.setVisible(false);
			this.btnPurchaseTicket.disable();
			this.btnCancel.setText("Continue");
    	}
	}
	
	public void setStatus() 
	{
	  	try
	  	{
	  		entry.get_garage_status();
	  		Date now = new Date();
	  		this.lblCurrentTime.setText(dateFormat.format(now)); 
	  		this.lblSign.setText(entry.getStringStatus());
	  		this.lblRate.setText(money.format(entry.getRate()) + "/hr");
	  		this.lblCapacity.setText(String.valueOf(entry.getCapacity()) );
	  		this.lblSpaces.setText(String.valueOf(entry.getSpaces()) );
	  	}
	  	catch(Exception e)
	  	{
	  		System.out.print("Exeption:" +e);
	  	}
	}
}
