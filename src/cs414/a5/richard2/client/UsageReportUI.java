package cs414.a5.richard2.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
import java.util.List;
import java.util.ArrayList;
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

public class UsageReportUI extends JFrame {

	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
	private DecimalFormat money = new DecimalFormat("$0.00");
	private ManagementClient manageClient;
	private UsageStatus status;
	private List<Ticket> tickets;
	private int count;
	
	private Calendar calEntry = Calendar.getInstance();
	private Calendar calExit = Calendar.getInstance();
	private Calendar calReport = Calendar.getInstance();
	private Date dateReport;
	
	private JPanel contentPane;
	private JTextField txtDate;
	private DefaultListModel model;
	private JLabel lblDate;

	
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UsageReportUI frame = new UsageReportUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the frame.
	 */
	public UsageReportUI(ManagementClient m_manageClient, UsageStatus m_status) {
		manageClient =m_manageClient;
		status =m_status;
		 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 560, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		lblDate = new JLabel("Date :");
		lblDate.setBounds(10, 33, 124, 14);
		contentPane.add(lblDate);
		
		txtDate = new JTextField();
		txtDate.setBounds(174, 30, 138, 20);
		contentPane.add(txtDate);
		txtDate.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new SearchClickAction());
		btnSearch.setBounds(354, 29, 89, 23);
		contentPane.add(btnSearch);
		
		model = new DefaultListModel();
		JList list = new JList(model);
		JScrollPane pane = new JScrollPane(list);
		pane.setBounds(32, 81, 454, 370);
		contentPane.add(pane);
		
		initalText();
	}
	
	
	private void initalText()
	{
		if(status == UsageStatus.hourly)
    	{
			this.setTitle("Usage Report Hourly");
			lblDate.setText("Day (MM/dd/yyyy) :");
    	}
		else if(status == UsageStatus.daily)
    	{
			this.setTitle("Usage Report Daily");
			lblDate.setText("Month (MM/yyyy) :");
    	}
		else if(status == UsageStatus.weekly)
    	{
			this.setTitle("Usage Report Weekly");
			lblDate.setText("Month (MM/yyyy) :");
    	}
		else if(status == UsageStatus.monthly)
    	{
			this.setTitle("Usage Report Monthly");
			lblDate.setText("Year (yyyy) :");
    	}
	}
	
	private class SearchClickAction extends AbstractAction 
	 {	       
	        @Override
	        public void actionPerformed(ActionEvent e) 
	        {
	        	if (!model.isEmpty()) {
	                model.clear();
	            }
	        	
	        	if(status == UsageStatus.hourly)
	        	{
		        	String expected2 = "MM/dd/yyyy";
		            SimpleDateFormat formatter2 = new SimpleDateFormat(expected2);	        			        	
		        	try{
		        		dateReport = formatter2.parse(txtDate.getText());
		        	}
		        	catch(ParseException pe)
		        	{
		        		JOptionPane.showMessageDialog(null, "ERROR: Wrong format date (MM/dd/yyyy)" );
		        		return;
		        	}
		        	
		        	reportHourly(dateReport);
	        	}
	        	
	        	else if(status == UsageStatus.daily)
	        	{
		        	String expected2 = "MM/yyyy";
		            SimpleDateFormat formatter2 = new SimpleDateFormat(expected2);	        			        	
		        	try{
		        		dateReport = formatter2.parse(txtDate.getText());
		        	}
		        	catch(ParseException pe)
		        	{
		        		JOptionPane.showMessageDialog(null, "ERROR: Wrong format month (MM/yyyy)" );
		        		return;
		        	}
		        	
		        	reportDaily(dateReport);
	        	}
	        	
	        	else if(status == UsageStatus.monthly)
	        	{
		        	String expected2 = "yyyy";
		            SimpleDateFormat formatter2 = new SimpleDateFormat(expected2);	        			        	
		        	try{
		        		dateReport = formatter2.parse(txtDate.getText());
		        	}
		        	catch(ParseException pe)
		        	{
		        		JOptionPane.showMessageDialog(null, "ERROR: Wrong format year (yyyy)" );
		        		return;
		        	}
		        	
		        	reportMonthly(dateReport);
	        	}
	        	
	        }
	 }
	
	
	
	public void reportHourly(Date daily)
	{
		try{
			//count =0;		
			tickets = manageClient.getUsageTickets();
			calReport.setTime(daily);
			
			calReport.set(Calendar.MINUTE, 5);
			
			int i;
			for(i=0; i<24;i++)
			{
				List<Ticket> subTickets = new ArrayList<Ticket>();
				
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
					{
						++count;
						subTickets.add(ticket);
					}
				}
				//System.out.println("Garage usage in hour from "+i +" to "+ (i+1)+ " is: " +count);
				
				if (count >0)
				{
					model.addElement("Garage usage in hour from "+i +" to "+ (i+1)+ " is: " +count);
					for(Ticket t : tickets)
					{
						if (t.getExitTime() !=null)
							model.addElement("    "+ t.getId()+"  "+t.getPlateLisence()+"  "+dateFormat.format( t.getEntryTime()) +" - "+ dateFormat.format( t.getExitTime()));
						else 
							model.addElement("    "+ t.getId()+"  "+t.getPlateLisence()+"  "+dateFormat.format( t.getEntryTime()) );
					}
				}
			}
			//return count;
		}
		  catch(Exception e){}
	}
	
	public void reportDaily(Date daily)
	{
		try{
			tickets = manageClient.getUsageTickets();
			calReport.setTime(daily);
			
			calReport.set(Calendar.HOUR_OF_DAY, 5);
			
			int i;
			for(i=1; i<=calReport.getActualMaximum(Calendar.DAY_OF_MONTH);i++)
			{
				List<Ticket> subTickets = new ArrayList<Ticket>();
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
					{
						++count;
						subTickets.add(ticket);
					}								
				}
				//System.out.println("Garage usage in day  "+i +" is: " +count);
				
				if (count >0)
				{
					model.addElement("Garage usage in day "+i + " is: " +count);
					for(Ticket t : tickets)
					{
						if (t.getExitTime() !=null)
							model.addElement("    "+ t.getId()+"  "+t.getPlateLisence()+"  "+dateFormat.format( t.getEntryTime()) +" - "+ dateFormat.format( t.getExitTime()));
						else 
							model.addElement("    "+ t.getId()+"  "+t.getPlateLisence()+"  "+dateFormat.format( t.getEntryTime()) );
					}
				}
			}
			//return count;
		}
	  catch(Exception e){}
	}
	
	public void reportMonthly(Date daily)
	{
		try{		
			tickets = manageClient.getUsageTickets();
			calReport.setTime(daily);
			
			calReport.set(Calendar.DAY_OF_MONTH, 5);
			
			int i;
			for(i=0; i<=calReport.getActualMaximum(Calendar.MONTH) ;++i)
			{
				count=0;
				List<Ticket> subTickets = new ArrayList<Ticket>();
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
					{
						++count;
						subTickets.add(ticket);
					}								
				}
				//System.out.println("Garage usage report in month  "+ (i+1) +" is: " +count);
				
				if (count >0)
				{
					model.addElement("Garage usage in month "+i + " is: " +count);
					for(Ticket t : tickets)
					{
						if (t.getExitTime() !=null)
							model.addElement("    "+ t.getId()+"  "+t.getPlateLisence()+"  "+dateFormat.format( t.getEntryTime()) +" - "+ dateFormat.format( t.getExitTime()));
						else 
							model.addElement("    "+ t.getId()+"  "+t.getPlateLisence()+"  "+dateFormat.format( t.getEntryTime()) );
					}
				}
			}
			//return count;
		}
		  catch(Exception e){}
	}
	
	
}
