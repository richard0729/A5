package cs414.a5.richard2.common;

import java.rmi.RemoteException;

public interface CashPayment extends Payment, java.rmi.Remote{

	public double getBalanceCash() throws RemoteException;
}
