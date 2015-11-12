package cs414.a5.richard2.server;

import java.rmi.*;
import java.rmi.server.*;

import cs414.a5.richard2.server.ParkingGarageImpl;
import cs414.a5.richard2.common.ParkingGarage;

public class ParkingGarageServer {
	private String url;
    public ParkingGarageServer(String url) {
    this.url = url;
          try {
        	  ParkingGarage garage = new ParkingGarageImpl();
        	  Naming.rebind(url, garage);
           } catch (Exception e) {
                   System.out.println("Trouble: " + e);
           }
    }

//run the program using 
//java CalculatorServer <host> <port>

public static void main(String args[]) {
    //String url = new String("rmi://" + args[0] + ":" + args[1] + "/ParkingGarageService");
	String url = new String("rmi://localhost/ParkingGarageService");
    new ParkingGarageServer(url);
}
}
