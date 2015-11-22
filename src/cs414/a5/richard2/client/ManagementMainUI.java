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
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cs414.a5.richard2.common.ParkingGarage;
import cs414.a5.richard2.common.UsageStatus;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagementMainUI extends JFrame {

	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
	private DecimalFormat money = new DecimalFormat("$0.00");
	private JPanel contentPane;
	private ManagementClient manageClient;	
	private ManagementMainUI mainUI;
	private UpdateCapacityUI capacityUI;
	private UpdateRateUI rateUI;
	
	private JLabel lblCurrentTime;
	private JLabel lblRate ;
	private JLabel lblCapacity ;
	private JLabel lblSpaces;
	private JLabel lblEntryGate ;	
	private JLabel lblSign ;
	private JLabel lblGate ;
	
	private String url;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String m_url = new String("rmi://" + args[0] + ":" + args[1]  + "/ParkingGarageService");
					ManagementMainUI frame = new ManagementMainUI(m_url);
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
	public ManagementMainUI(String m_url) {
		mainUI = this;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 554, 447);
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
		
		JButton btnUsageHourly = new JButton("Usage Hourly");
		btnUsageHourly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UsageReportUI rp = new UsageReportUI(manageClient, UsageStatus.hourly);
				rp.setVisible(true);
			}
		});
		btnUsageHourly.setBounds(35, 279, 148, 35);
		contentPane.add(btnUsageHourly);
		
		JButton btnUsageDaily = new JButton("Usage Daily");
		btnUsageDaily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsageReportUI rp = new UsageReportUI(manageClient, UsageStatus.daily);
				rp.setVisible(true);
			}
		});
		btnUsageDaily.setBounds(231, 279, 129, 35);
		contentPane.add(btnUsageDaily);
		
		JButton btnUsageMonthly = new JButton("Usage Monthly");
		btnUsageMonthly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsageReportUI rp = new UsageReportUI(manageClient, UsageStatus.monthly);
				rp.setVisible(true);
			}
		});
		btnUsageMonthly.setBounds(399, 279, 129, 35);
		contentPane.add(btnUsageMonthly);
		
		JButton btnSaleDaily = new JButton("Sale Daily");
		btnSaleDaily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaleReportUI rp = new SaleReportUI(manageClient, UsageStatus.daily);
				rp.setVisible(true);
			}
		});
		btnSaleDaily.setBounds(233, 338, 127, 35);
		contentPane.add(btnSaleDaily);
		
		JButton btnSaleMonthly = new JButton("Sale Monthly");
		btnSaleMonthly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaleReportUI rp = new SaleReportUI(manageClient, UsageStatus.monthly);
				rp.setVisible(true);
			}
		});
		btnSaleMonthly.setBounds(399, 338, 129, 35);
		contentPane.add(btnSaleMonthly);
		
		JButton btnSaleHourly = new JButton("Sale Hourly");
		btnSaleHourly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaleReportUI rp = new SaleReportUI(manageClient, UsageStatus.hourly);
				rp.setVisible(true);
			}
		});
		btnSaleHourly.setBounds(35, 338, 148, 35);
		contentPane.add(btnSaleHourly);
		
		JButton btnRefreshStatus = new JButton("Refresh Status");
		btnRefreshStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setStatus();
			}
		});
		btnRefreshStatus.setBounds(35, 206, 148, 42);
		contentPane.add(btnRefreshStatus);
		
		JButton btnUpdateSpaces = new JButton("Update Spaces");
		btnUpdateSpaces.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				capacityUI = new UpdateCapacityUI(manageClient, mainUI );						
				capacityUI.setVisible(true);
			}
		});
		btnUpdateSpaces.setBounds(231, 206, 129, 42);
		contentPane.add(btnUpdateSpaces);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//dispose();
				System.exit(0);
			}
		});
		btnExit.setBounds(399, 206, 129, 42);
		contentPane.add(btnExit);
		
		//String url = new String("rmi://localhost:2001/ParkingGarageService");		
		//String url = new String("rmi://" + args[0] + ":" + args[1]  + "/ParkingGarageService");
		try {
			this.url = m_url;
			ParkingGarage g = (ParkingGarage) Naming.lookup(url);
			System.out.println("Server is connected sussessful");
			//System.out.print("\t	Max Spaces: " + garage.getMaxSpaces());
			manageClient = new ManagementClient(g);
			setStatus();
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
	}
	
	
	public void setStatus() 
	{
	  	try
	  	{
	  		manageClient.get_garage_status();
	  		Date now = new Date();
	  		this.lblCurrentTime.setText(dateFormat.format(now)); 
	  		this.lblSign.setText(manageClient.getStringStatus());
	  		this.lblRate.setText(money.format(manageClient.getRate()) + "/hr");
	  		this.lblCapacity.setText(String.valueOf(manageClient.getCapacity()) );
	  		this.lblSpaces.setText(String.valueOf(manageClient.getSpaces()) );
	  	}
	  	catch(Exception e)
	  	{
	  		System.out.print("Exeption:" +e);
	  	}
	}
}
