package cs414.a5.richard2.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cs414.a5.richard2.Exception.*;
//import cs414.a5.richard2.common.*;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class UpdateCapacityUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtCapacity;
	private ManagementClient manageClient;
	private UpdateCapacityUI capacityUI;
	private ManagementMainUI mainUI;
	private JButton btnExit;

	/**
	 * Create the frame.
	 */
	public UpdateCapacityUI(ManagementClient m_manageClient, ManagementMainUI m_mainUI) {
		setTitle("Manage New Capacity");
		manageClient =m_manageClient;
		mainUI = m_mainUI;
		capacityUI =this;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 346, 246);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Capacity :");
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
	        		String s_capacity = txtCapacity.getText();
	        		int capacity = Integer.parseInt(s_capacity);
	        		if(capacity <=0 )
	        		{
	        			JOptionPane.showMessageDialog(capacityUI, "The capacity must be larger than 0 !" );
	        			return;
	        		}
	        		manageClient.setCapacity(capacity);
	        		JOptionPane.showMessageDialog(capacityUI, "The new  capacity is :" + s_capacity);
	        		btnExit.doClick();
	        	}
	        	catch (NumberFormatException ef) {
	        		JOptionPane.showMessageDialog(capacityUI, "The number of capacity is invalid !" );
	        	}
	        	catch(Exception ex)
	    	  	{
	    	  		System.out.print("Exeption:" +ex);
	    	  		JOptionPane.showMessageDialog(capacityUI, "Exeption:" +ex );
	    	  	}
	        }
	 }
}
