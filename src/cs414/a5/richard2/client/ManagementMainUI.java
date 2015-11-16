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
import cs414.a5.richard2.common.UsageStatus;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagementMainUI extends JFrame {

	private JPanel contentPane;
	private ManagementClient manageClient;	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagementMainUI frame = new ManagementMainUI();
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
	public ManagementMainUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnUsageHourly = new JButton("Usage Hourly");
		btnUsageHourly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UsageReportUI rp = new UsageReportUI(manageClient, UsageStatus.hourly);
				rp.setVisible(true);
			}
		});
		btnUsageHourly.setBounds(10, 92, 114, 35);
		contentPane.add(btnUsageHourly);
		
		JButton btnUsageDaily = new JButton("Usage Daily");
		btnUsageDaily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsageReportUI rp = new UsageReportUI(manageClient, UsageStatus.daily);
				rp.setVisible(true);
			}
		});
		btnUsageDaily.setBounds(166, 92, 108, 35);
		contentPane.add(btnUsageDaily);
		
		JButton btnUsageMonthly = new JButton("Usage Monthly");
		btnUsageMonthly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UsageReportUI rp = new UsageReportUI(manageClient, UsageStatus.monthly);
				rp.setVisible(true);
			}
		});
		btnUsageMonthly.setBounds(324, 92, 129, 35);
		contentPane.add(btnUsageMonthly);
		
		JButton btnSaleDaily = new JButton("Sale Daily");
		btnSaleDaily.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaleReportUI rp = new SaleReportUI(manageClient, UsageStatus.daily);
				rp.setVisible(true);
			}
		});
		btnSaleDaily.setBounds(166, 160, 108, 35);
		contentPane.add(btnSaleDaily);
		
		JButton btnSaleMonthly = new JButton("Sale Monthly");
		btnSaleMonthly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaleReportUI rp = new SaleReportUI(manageClient, UsageStatus.monthly);
				rp.setVisible(true);
			}
		});
		btnSaleMonthly.setBounds(324, 160, 129, 35);
		contentPane.add(btnSaleMonthly);
		
		JButton btnSaleHourly = new JButton("Sale Hourly");
		btnSaleHourly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaleReportUI rp = new SaleReportUI(manageClient, UsageStatus.hourly);
				rp.setVisible(true);
			}
		});
		btnSaleHourly.setBounds(10, 160, 114, 35);
		contentPane.add(btnSaleHourly);
		
		String url = new String("rmi://localhost:2001/ParkingGarageService");		
		try {
			ParkingGarage g = (ParkingGarage) Naming.lookup(url);
			System.out.println("Server is connected sussessful");
			//System.out.print("\t	Max Spaces: " + garage.getMaxSpaces());
			manageClient = new ManagementClient(g);
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
}
