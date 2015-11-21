package cs414.a5.richard2.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cs414.a5.richard2.common.*;
import java.awt.event.ActionListener;


public class SaleReportUI extends JFrame {

	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
	private DecimalFormat money = new DecimalFormat("$0.00");
	private ManagementClient manageClient;
	private UsageStatus status;
	//private List<Ticket> tickets;
	private List<Receipt> receipts;
	private int count;
	private int totalSale;
	
	private Calendar calEntry = Calendar.getInstance();
	private Calendar calExit = Calendar.getInstance();
	private Calendar calReport = Calendar.getInstance();
	private Date dateReport;
	
	private JPanel contentPane;
	private JTextField txtDate;
	private DefaultListModel model;
	private JLabel lblDate;
	private JButton btnExit;

	
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
	public SaleReportUI(ManagementClient m_manageClient, UsageStatus m_status) {
		manageClient =m_manageClient;
		status =m_status;
		 
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 526, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		lblDate = new JLabel("Date :");
		lblDate.setBounds(10, 33, 124, 14);
		contentPane.add(lblDate);
		
		txtDate = new JTextField();
		txtDate.setBounds(125, 30, 132, 20);
		contentPane.add(txtDate);
		txtDate.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new SearchClickAction());
		btnSearch.setBounds(286, 29, 89, 23);
		contentPane.add(btnSearch);
		
		model = new DefaultListModel();
		JList list = new JList(model);
		JScrollPane pane = new JScrollPane(list);
		pane.setBounds(32, 81, 454, 370);
		contentPane.add(pane);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(397, 29, 89, 23);
		contentPane.add(btnExit);
		
		initalText();
	}
	
	
	private void initalText()
	{
		if(status == UsageStatus.hourly)
    	{
			this.setTitle("Sale Report Hourly");
			lblDate.setText("Day (MM/dd/yyyy) :");
    	}
		else if(status == UsageStatus.daily)
    	{
			this.setTitle("Sale Report Daily");
			lblDate.setText("Month (MM/yyyy) :");
    	}
		else if(status == UsageStatus.weekly)
    	{
			this.setTitle("Sale Report Weekly");
			lblDate.setText("Month (MM/yyyy) :");
    	}
		else if(status == UsageStatus.monthly)
    	{
			this.setTitle("Sale Report Monthly");
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

			receipts= manageClient.getReceipts();
			calReport.setTime(daily);
			
			calReport.set(Calendar.MINUTE, 10);
			
			//JOptionPane.showMessageDialog(null, "input" );
			int i;
			for(i=0; i<24;i++)
			{
				List<Receipt> subReceipts = new ArrayList<Receipt>();				
				count=0;
				totalSale =0;
				
				calReport.set(Calendar.HOUR_OF_DAY, i);
				for(Receipt receipt : receipts)
				{			
					calEntry.setTime(receipt.getTicket().getExitTime());
					calEntry.set(Calendar.MINUTE, 0);
					
					calExit.setTime(receipt.getTicket().getExitTime());
					calExit.set(Calendar.MINUTE, 10);
					if(calReport.compareTo(calEntry) >=0 && calReport.compareTo(calExit) <=0)
					{
						++count;
						totalSale += receipt.getFee();
						subReceipts.add(receipt);
					}
				}
				//System.out.println("Garage usage in hour from "+i +" to "+ (i+1)+ " is: " +count);
				//JOptionPane.showMessageDialog(null, "input" );
				
				if (count >0)
				{
					model.addElement("Ticket sale in hour from "+i +":00 to "+ i+ ":59 is: " +money.format(totalSale));
					for(Receipt receipt : subReceipts)
					{
						
						Ticket t = receipt.getTicket();
						if(t !=null )
						{
							//model.addElement("test    ");
							if(receipt.getPaymentType() == PaymentType.Cash )	
								//model.addElement("test    ");
								model.addElement("    "+ t.getId()+"  "+t.getPlateLisence()+"  "+dateFormat.format( t.getEntryTime()) +" - "+ dateFormat.format( t.getExitTime()) +" " +money.format(receipt.getFee()) + " By Cash");
							else if(receipt.getPaymentType() == PaymentType.Credit )
								//model.addElement("test    ");
								model.addElement("    "+ t.getId()+"  "+t.getPlateLisence()+"  "+dateFormat.format( t.getEntryTime()) +" - "+ dateFormat.format( t.getExitTime()) +" " +money.format(receipt.getFee()) + " By Card");
							else if(receipt.getPaymentType() == PaymentType.NoPay )
								
								model.addElement("    "+ t.getId()+"  "+t.getPlateLisence()+"  "+dateFormat.format( t.getEntryTime()) +" - "+ dateFormat.format( t.getExitTime()) +" $0.00"  + " By Cannot Pay");
							
						}
					}
				}
				
			}
			//return count;
			/*
			if (model.isEmpty()) {
				model.addElement("No data for this day ");
            }
            */
		}
		  catch(Exception e){
			  System.out.println("Exception:" +e);
		  }
	}
	
	public void reportDaily(Date daily)
	{
		try{
			receipts= manageClient.getReceipts();
			calReport.setTime(daily);
			
			calReport.set(Calendar.HOUR_OF_DAY, 5);
			
			int i;
			for(i=1; i<=calReport.getActualMaximum(Calendar.DAY_OF_MONTH);i++)
			{
				List<Receipt> subReceipts = new ArrayList<Receipt>();				
				count=0;
				totalSale =0;
				
				calReport.set(Calendar.DAY_OF_MONTH, i);
				for(Receipt receipt : receipts)
				{
					//calEntry.setTime(dateFormat.parse(ticket.getEntryTime().toString()));
					calEntry.setTime(receipt.getTicket().getExitTime());										
					calExit.setTime(receipt.getTicket().getExitTime());
					
					calEntry.set(Calendar.HOUR_OF_DAY, 1);
					calExit.set(Calendar.HOUR_OF_DAY, 10);
					if(calReport.compareTo(calEntry) >=0 && calReport.compareTo(calExit) <=0)
					{
						++count;
						totalSale += receipt.getFee();
						subReceipts.add(receipt);
					}								
				}
				//System.out.println("Garage usage in day  "+i +" is: " +count);
				
				if (count >0)
				{					
					model.addElement("Ticket sale in day  "+i + " is: " +money.format(totalSale));
					for(Receipt receipt : subReceipts)
					{
						Ticket t = receipt.getTicket();
						if(receipt.getPaymentType() == PaymentType.Cash )						
							model.addElement("    "+ t.getId()+"  "+t.getPlateLisence()+"  "+dateFormat.format( t.getEntryTime()) +" - "+ dateFormat.format( t.getExitTime()) +" " +money.format(receipt.getFee()) + " By Cash");
						else if(receipt.getPaymentType() == PaymentType.Credit )
							model.addElement("    "+ t.getId()+"  "+t.getPlateLisence()+"  "+dateFormat.format( t.getEntryTime()) +" - "+ dateFormat.format( t.getExitTime()) +" " +money.format(receipt.getFee()) + " By Card");
						else if(receipt.getPaymentType() == PaymentType.NoPay )
							model.addElement("    "+ t.getId()+"  "+t.getPlateLisence()+"  "+dateFormat.format( t.getEntryTime()) +" - "+ dateFormat.format( t.getExitTime()) +" $0.00"  + " By Cannot Pay");
					}
				}
			}
			//return count;
			if (model.isEmpty()) {
				model.addElement("No data for this month ");
            }
		}
	  catch(Exception e){}
	}
	
	public void reportMonthly(Date daily)
	{
		try{		
			receipts= manageClient.getReceipts();
			calReport.setTime(daily);
			
			calReport.set(Calendar.DAY_OF_MONTH, 5);
			
			int i;
			for(i=0; i<=calReport.getActualMaximum(Calendar.MONTH) ;++i)
			{
				List<Receipt> subReceipts = new ArrayList<Receipt>();				
				count=0;
				totalSale =0;
				
				calReport.set(Calendar.MONTH, i);
				for(Receipt receipt : receipts)
				{
					calEntry.setTime(receipt.getTicket().getExitTime());										
					calExit.setTime(receipt.getTicket().getExitTime());
					
					calEntry.set(Calendar.DAY_OF_MONTH, 1);
					calExit.set(Calendar.DAY_OF_MONTH, 10);
					if(calReport.compareTo(calEntry) >=0 && calReport.compareTo(calExit) <=0)
					{
						++count;
						totalSale += receipt.getFee();
						subReceipts.add(receipt);
					}							
				}
				//System.out.println("Garage usage report in month  "+ (i+1) +" is: " +count);
				
				if (count >0)
				{
					//model.addElement("Garage usage in month "+i + " is: " +count);
					model.addElement("Ticket sale in month  "+i + " is: " +money.format(totalSale));
					for(Receipt receipt : subReceipts)
					{
						Ticket t = receipt.getTicket();
						if(receipt.getPaymentType() == PaymentType.Cash )						
							model.addElement("    "+ t.getId()+"  "+t.getPlateLisence()+"  "+dateFormat.format( t.getEntryTime()) +" - "+ dateFormat.format( t.getExitTime()) +" " +money.format(receipt.getFee()) + " By Cash");
						else if(receipt.getPaymentType() == PaymentType.Credit )
							model.addElement("    "+ t.getId()+"  "+t.getPlateLisence()+"  "+dateFormat.format( t.getEntryTime()) +" - "+ dateFormat.format( t.getExitTime()) +" " +money.format(receipt.getFee()) + " By Card");
						else if(receipt.getPaymentType() == PaymentType.NoPay )
							model.addElement("    "+ t.getId()+"  "+t.getPlateLisence()+"  "+dateFormat.format( t.getEntryTime()) +" - "+ dateFormat.format( t.getExitTime()) +" $0.00"  + " By Cannot Pay");
					}
				}
			}
			//return count;
			if (model.isEmpty()) {
				model.addElement("No data for this year ");
            }
		}
		  catch(Exception e){}
	}

}
