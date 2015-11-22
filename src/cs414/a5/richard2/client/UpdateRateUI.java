package cs414.a5.richard2.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class UpdateRateUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtCapacity;
	private ManagementClient manageClient;
	private UpdateRateUI rateUI;
	private ManagementMainUI mainUI;
	private JButton btnExit;

	
	/**
	 * Create the frame.
	 */
	public UpdateRateUI(ManagementClient m_manageClient, ManagementMainUI m_mainUI) {
		setTitle("Manage New Rate");
		manageClient =m_manageClient;
		mainUI = m_mainUI;
		rateUI =this;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 346, 246);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Rate :");
		lblNewLabel.setBounds(30, 28, 81, 24);
		contentPane.add(lblNewLabel);
		
		txtCapacity = new JTextField();
		txtCapacity.setBounds(150, 30, 86, 20);
		contentPane.add(txtCapacity);
		txtCapacity.setColumns(10);
		txtCapacity.setText("");
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new UpdateClickAction());
		btnUpdate.setBounds(30, 101, 89, 23);
		contentPane.add(btnUpdate);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				mainUI.setStatus();
				mainUI.setVisible(true);
			}
		});
		btnExit.setBounds(163, 101, 89, 23);
		contentPane.add(btnExit);
	}
	
	private class UpdateClickAction extends AbstractAction 
	 {	       
	        @Override
	        public void actionPerformed(ActionEvent e) 
	        {
	        	try
	        	{
	        		
	        		String s_rate = txtCapacity.getText();
	        		
	        		double rate = Double.parseDouble(s_rate);
	        		if(rate <=0 )
	        		{
	        			JOptionPane.showMessageDialog(rateUI, "The rate must be larger than 0 !" );
	        			return;
	        		}
	        		JOptionPane.showMessageDialog(rateUI, "New" +s_rate);
	        		manageClient.setRate(rate);
	        		JOptionPane.showMessageDialog(rateUI, "The new  rate is :" + s_rate);
	        		btnExit.doClick();
	        	}
	        	catch (NumberFormatException ef) {
	        		JOptionPane.showMessageDialog(rateUI, "The number of capacity is invalid !" );
	        	}
	        	catch(Exception ex)
	    	  	{
	    	  		System.out.print("Exeption:" +ex);
	    	  		JOptionPane.showMessageDialog(rateUI, "Exeption:" +ex );
	    	  	}
	        }
	 }
}


