package cs414.a5.richard2.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import cs414.a5.richard2.common.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CardPaymentUI extends JFrame {

	private DecimalFormat df = new DecimalFormat("0.00");
	private JPanel contentPane;
	
	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
	private DecimalFormat money = new DecimalFormat("$0.00");
	private JLabel lblId ;
	private JLabel lblPlate ;
	private JLabel lblFee ;
	private JLabel lblEntrytime ;
	private JLabel lblExittime ;
	
	private  Ticket ticket;
	private  double fee;

	public Receipt receipt;
	private ExitClient exitClient;
	private CreditPayment creditPayment;
	private JButton btnPayment;
	private JButton btnCancel ;
	private PaymentTicketUI mainJFrame;
	
	public boolean result;
	private JTextField txtNumber;
	private JTextField txtExpire;
	private CardPaymentUI cardUI;
	
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CashPaymentUI frame = new CashPaymentUI();
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
	public CardPaymentUI(Ticket m_ticket, double m_fee, ExitClient m_exitClient, PaymentTicketUI m_mainJFrame) {
		cardUI = this;
		ticket = m_ticket;
		fee = m_fee;
		result = false;
		exitClient = m_exitClient;
		mainJFrame = m_mainJFrame;
		setTitle("Credit /debit Payment");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 503, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTicketId = new JLabel("Ticket ID :");
		lblTicketId.setBounds(20, 21, 72, 14);
		contentPane.add(lblTicketId);
		
		lblId = new JLabel("ID");
		lblId.setBounds(114, 21, 46, 14);
		contentPane.add(lblId);
		
		JLabel lblPlateLisence = new JLabel("Plate Lisence :");
		lblPlateLisence.setBounds(255, 21, 94, 14);
		contentPane.add(lblPlateLisence);
		
		lblPlate = new JLabel("Plate");
		lblPlate.setBounds(372, 21, 89, 14);
		contentPane.add(lblPlate);
		
		JLabel lblAmountFee = new JLabel("Total Fee :");
		lblAmountFee.setBounds(20, 124, 72, 14);
		contentPane.add(lblAmountFee);
		
		lblFee = new JLabel("Fee");
		lblFee.setBounds(194, 124, 46, 14);
		contentPane.add(lblFee);
		
		JLabel lblNewLabel = new JLabel("Entry Time: ");
		lblNewLabel.setBounds(20, 48, 84, 14);
		contentPane.add(lblNewLabel);
		
		lblEntrytime = new JLabel("EntryTime");
		lblEntrytime.setBounds(114, 48, 125, 14);
		contentPane.add(lblEntrytime);
		
		JLabel lblNewLabel_1 = new JLabel("Exit Time :");
		lblNewLabel_1.setBounds(255, 46, 86, 14);
		contentPane.add(lblNewLabel_1);
		
		lblExittime = new JLabel("ExitTime");
		lblExittime.setBounds(368, 46, 109, 14);
		contentPane.add(lblExittime);
		
		btnPayment = new JButton("Payment");
		btnPayment.addActionListener(new PaymentClickAction());
		btnPayment.setBounds(20, 228, 89, 23);
		contentPane.add(btnPayment);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new CancelClickAction());
		btnCancel.setBounds(150, 228, 89, 23);
		contentPane.add(btnCancel);
		
		JLabel lblCardNumber = new JLabel("Card Number :");
		lblCardNumber.setBounds(20, 156, 89, 14);
		contentPane.add(lblCardNumber);
		
		txtNumber = new JTextField();
		txtNumber.setBounds(194, 149, 235, 20);
		contentPane.add(txtNumber);
		txtNumber.setColumns(10);
		
		JLabel lblExpireDatemmyyyy = new JLabel("Expire date (mm/yyyy):");
		lblExpireDatemmyyyy.setBounds(20, 192, 163, 14);
		contentPane.add(lblExpireDatemmyyyy);
		
		txtExpire = new JTextField();
		txtExpire.setBounds(193, 189, 109, 20);
		contentPane.add(txtExpire);
		txtExpire.setColumns(10);
		
		init();
	}
	
	public void init()
	{
		try
	  	{
			this.lblId.setText(String.valueOf(ticket.getId()));
			this.lblPlate.setText(ticket.getPlateLisence());
	  		this.lblExittime.setText(dateFormat.format(ticket.getExitTime())); 
	  		this.lblEntrytime.setText(dateFormat.format(ticket.getEntryTime())); 
	  		this.lblFee.setText(df.format(fee));
	  	}
	  	catch(Exception e)
	  	{
	  		System.out.print("Exeption:" +e);
	  	}
	}
	
	public void ShowError()
	{
		if (exitClient.errorPayment == ErrorPayment.errorRemote)
			JOptionPane.showMessageDialog(null, "Remote Exception" +exitClient.remoteException );
		else if (exitClient.errorPayment == ErrorPayment.invalidDouble)
			JOptionPane.showMessageDialog(null, "ERROR: You are invalid fee");
		else if (exitClient.errorPayment == ErrorPayment.invalidAccount)
			JOptionPane.showMessageDialog(null, "ERROR: The number of card is invalid format. It must be length of 16 digits ");
		else if (exitClient.errorPayment == ErrorPayment.invalidLength)
			JOptionPane.showMessageDialog(null, "ERROR: The number of card is invalid length. It must be length of 16 digits ");
		else if (exitClient.errorPayment == ErrorPayment.invalidMonth)
			JOptionPane.showMessageDialog(null, "ERROR: The expire date of card is invalid format (mm/yyyy) ");
		else if (exitClient.errorPayment == ErrorPayment.invalidExpire)
			JOptionPane.showMessageDialog(null, "ERROR: The expire date of card must be larger than now ");
	
	}
	
	 private class PaymentClickAction extends AbstractAction 
	 {	       
	    @Override
	    public void actionPerformed(ActionEvent e) 
	    {
	    	try
		  	{
	    		
	        	String s_AmountDue = lblFee.getText();
	        	//String s_AmountCash = txtCash.getText();
	        	String s_AccountNumber = txtNumber.getText();
	        	String s_ExpireDate = txtExpire.getText();
	        	
	        	System.out.println("\t	s_AmountDue: " +s_AmountDue);
	        	System.out.println("\t	AccountNumber: " +s_AccountNumber);
	        	System.out.println("\t	s_ExpireDate: " +s_ExpireDate);
	        	
	        	
	        	creditPayment = exitClient.issuePaymentByCredit(s_AmountDue, s_AccountNumber, s_ExpireDate);
	        	
	        	if(creditPayment ==null )
	        	{
	        		System.out.println("ERROR");
	    			ShowError();
	    			return;
	        	}
	        	
	        	receipt = exitClient.issueCardReceipt(ticket,creditPayment);

	        	JOptionPane.showMessageDialog(cardUI, "Payment by Card is accepted! " );
	        	result = true;
	        	
	        	//btnPayment.setVisible(false);
	        	//btnCancel.setText("Continue");
	        	//change cancel
	        	setVisible(false);
		    	mainJFrame.setVisible(true);
		    	mainJFrame.setReceiptExit();
		  	}
			catch(Exception ex)
		  	{
		  		System.out.print("Exeption 1:" +ex);
		  	}
	    }
	 }	        

	 private class CancelClickAction extends AbstractAction 
	 {	       
	    @Override
	    public void actionPerformed(ActionEvent e) 
	    {
	    	try
		  	{
	    		/*
		    	setVisible(false);
		    	mainJFrame.setVisible(true);
		    	mainJFrame.setReceiptExit();
		    	*/
	    		//dispose();
	    		setVisible(false);
	    		mainJFrame.setVisible(true);
		  	}
	    	catch(Exception ex)
		  	{
		  		System.out.print("Exeption :" +ex);
		  	}
			//dispose(); 
	    }
	 }
}
