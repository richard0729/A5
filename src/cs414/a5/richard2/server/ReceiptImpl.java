package cs414.a5.richard2.server;

import java.rmi.RemoteException;
import java.util.Date;

import cs414.a5.richard2.common.*;

public class ReceiptImpl implements Receipt{
	
	private Ticket ticket;
	private CashPayment cash = null;
	private CreditPayment credit = null;
	private PaymentType type;
	
	public ReceiptImpl(Ticket m_ticket, CashPayment m_cash) throws RemoteException{
		super();
		ticket = m_ticket;
		cash =m_cash;
		credit =null;
		type = PaymentType.Cash;
	}
	
	public ReceiptImpl(Ticket m_ticket, CreditPayment m_credit) throws RemoteException{
		super();
		ticket = m_ticket;
		cash =null;
		credit =m_credit;
		type = PaymentType.Credit;
	}
	
	public Ticket getTicket() throws RemoteException{
        return ticket;
    }
	
	public CashPayment getCashPayment() throws RemoteException{
        return cash;
    }
	
	public CreditPayment getCreditPayment() throws RemoteException{
        return this.credit;
    }
	
	public PaymentType getPaymentType() throws RemoteException{
        return this.type;
    }

}
