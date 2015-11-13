package cs414.a5.richard2.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cs414.a5.richard2.common.ParkingGarage;
import javax.swing.JLabel;
import javax.swing.JButton;

public class EntryUI extends JFrame {

	private JPanel contentPane;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Current time:");
		lblNewLabel.setBounds(10, 11, 78, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblCurrentTime = new JLabel("time");
		lblCurrentTime.setBounds(98, 11, 112, 14);
		contentPane.add(lblCurrentTime);
		
		JLabel lblNewLabel_1 = new JLabel("Sign :");
		lblNewLabel_1.setBounds(10, 36, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblSign = new JLabel("available");
		lblSign.setBounds(98, 36, 46, 14);
		contentPane.add(lblSign);
		
		JLabel lblNewLabel_2 = new JLabel("Rate :");
		lblNewLabel_2.setBounds(224, 36, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblRate = new JLabel("rate");
		lblRate.setBounds(271, 36, 46, 14);
		contentPane.add(lblRate);
		
		JLabel lblNewLabel_3 = new JLabel("Capacity :");
		lblNewLabel_3.setBounds(10, 61, 67, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblCapacity = new JLabel("capacity");
		lblCapacity.setBounds(98, 61, 46, 14);
		contentPane.add(lblCapacity);
		
		JLabel lblNewLabel_4 = new JLabel("Spaces :");
		lblNewLabel_4.setBounds(212, 61, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblSpaces = new JLabel("Spaces");
		lblSpaces.setBounds(271, 61, 46, 14);
		contentPane.add(lblSpaces);
		
		JButton btnPurchaseTicket = new JButton("Purchase Ticket");
		btnPurchaseTicket.setBounds(25, 136, 119, 38);
		contentPane.add(btnPurchaseTicket);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(198, 136, 119, 38);
		contentPane.add(btnExit);
		
		JLabel lblEntryGate = new JLabel("Entry Gate");
		lblEntryGate.setBounds(198, 11, 85, 14);
		contentPane.add(lblEntryGate);
	}
}
