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

public class ExitMainUI extends JFrame {

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExitMainUI frame = new ExitMainUI();
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
	public ExitMainUI() {
		setTitle("Exit Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 355);
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
		//End group garage sign
		
		
		JButton btnPaymentTicket = new JButton("Payment Ticket");
		btnPaymentTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PaymentTicketUI payTicketUI = new PaymentTicketUI();
				payTicketUI.exitUIStatus = ExitUIStatus.payTicket;
				payTicketUI.setVisible(true);
			}
		});
		btnPaymentTicket.setBounds(35, 115, 148, 41);
		contentPane.add(btnPaymentTicket);
		
		JButton btnPaymentLost = new JButton("Payment Lost Ticket");
		btnPaymentLost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PaymentTicketUI payTicketUI = new PaymentTicketUI();
				payTicketUI.exitUIStatus = ExitUIStatus.payLostTicket;
				payTicketUI.setInitial();
				payTicketUI.setVisible(true);
			}
		});
		btnPaymentLost.setBounds(208, 115, 148, 41);
		contentPane.add(btnPaymentLost);
		
		JButton btnCannotPayTicket = new JButton("Cannot Pay Ticket");
		btnCannotPayTicket.setBounds(35, 184, 148, 41);
		contentPane.add(btnCannotPayTicket);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(212, 181, 144, 44);
		contentPane.add(btnCancel);
		
		//String url = new String("rmi://" + args[0] + ":" + args[1]  + "/ParkingGarageService");
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
		//entry = new EntryKiosk();
		//entry.get_garage_status();
		setStatus() ;
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
