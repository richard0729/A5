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

public class CashPaymentUI extends JFrame {


	private DecimalFormat df = new DecimalFormat("0.00");
	private JPanel contentPane;
	
	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
	private DecimalFormat money = new DecimalFormat("$0.00");
	private JTextField txtCash;
	private JLabel lblId ;
	private JLabel lblPlate ;
	private JLabel lblFee ;
	private JLabel lblEntrytime ;
	private JLabel lblExittime ;
	private JLabel lblChange ;
	
	private  Ticket ticket;
	private  double fee;

	private Receipt receipt;
	private ExitClient exitClient;
	private CashPayment cash;
	
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
	public CashPaymentUI(Ticket m_ticket, double m_fee, ExitClient m_exitClient) {
		ticket = m_ticket;
		fee = m_fee;
		exitClient = m_exitClient;
		setTitle("Cash Payment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTicketId = new JLabel("Ticket ID :");
		lblTicketId.setBounds(20, 21, 72, 14);
		contentPane.add(lblTicketId);
		
		lblId = new JLabel("ID");
		lblId.setBounds(150, 21, 46, 14);
		contentPane.add(lblId);
		
		JLabel lblPlateLisence = new JLabel("Plate Lisence :");
		lblPlateLisence.setBounds(211, 21, 94, 14);
		contentPane.add(lblPlateLisence);
		
		lblPlate = new JLabel("Plate");
		lblPlate.setBounds(315, 21, 89, 14);
		contentPane.add(lblPlate);
		
		JLabel lblAmountFee = new JLabel("Total Fee :");
		lblAmountFee.setBounds(20, 83, 72, 14);
		contentPane.add(lblAmountFee);
		
		lblFee = new JLabel("Fee");
		lblFee.setBounds(124, 83, 46, 14);
		contentPane.add(lblFee);
		
		JLabel lblAmountCash = new JLabel("Cash Tend:");
		lblAmountCash.setBounds(20, 119, 98, 14);
		contentPane.add(lblAmountCash);
		
		txtCash = new JTextField();
		txtCash.setBounds(110, 116, 86, 20);
		contentPane.add(txtCash);
		txtCash.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Entry Time: ");
		lblNewLabel.setBounds(20, 48, 84, 14);
		contentPane.add(lblNewLabel);
		
		lblEntrytime = new JLabel("EntryTime");
		lblEntrytime.setBounds(114, 48, 82, 14);
		contentPane.add(lblEntrytime);
		
		JLabel lblNewLabel_1 = new JLabel("Exit Time :");
		lblNewLabel_1.setBounds(211, 48, 86, 14);
		contentPane.add(lblNewLabel_1);
		
		lblExittime = new JLabel("ExitTime");
		lblExittime.setBounds(315, 46, 109, 14);
		contentPane.add(lblExittime);
		
		JLabel lblChangeDue = new JLabel("Change Due :");
		lblChangeDue.setBounds(20, 157, 72, 14);
		contentPane.add(lblChangeDue);
		
		lblChange = new JLabel("$0.00");
		lblChange.setBounds(114, 157, 46, 14);
		contentPane.add(lblChange);
		
		JButton btnPayment = new JButton("Payment");
		btnPayment.addActionListener(new PaymentClickAction());
		btnPayment.setBounds(20, 200, 89, 23);
		contentPane.add(btnPayment);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CashPaymentUI.DISPOSE_ON_CLOSE;
				setVisible(false);
				dispose(); 
			}
		});
		btnCancel.setBounds(150, 200, 89, 23);
		contentPane.add(btnCancel);
		
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
			JOptionPane.showMessageDialog(null, "ERROR: You are input invalid Cash");
		else if (exitClient.errorPayment == ErrorPayment.invalidBalance)
			JOptionPane.showMessageDialog(null, "ERROR: Total fee is less than Cash ");
	
	}
	
	 private class PaymentClickAction extends AbstractAction 
	 {	       
	        @Override
	        public void actionPerformed(ActionEvent e) 
	        {
	        	try
        	  	{
		        	String s_AmountDue = lblFee.getText();
		        	String s_AmountCash = txtCash.getText();
		        	
		        	System.out.print("\t	s_AmountDue: " +s_AmountDue);
		        	System.out.print("\t	s_AmountCash: " +s_AmountCash);
		        	
		        	cash = exitClient.issuePaymentByCash(s_AmountDue, s_AmountCash);
		        	
		        	if(cash ==null )
		        	{
		        		System.out.println("ERROR");
	        			ShowError();
	        			return;
		        	}
		        	
		        	JOptionPane.showMessageDialog(null, "Please take balance change cash: $" + df.format(cash.getBalanceCash()));
        	  	}
        		catch(Exception ex)
        	  	{
        	  		System.out.print("Exeption:" +ex);
        	  	}
	        }
	 }	        

}