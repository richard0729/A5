package cs414.a5.richard2.server;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import cs414.a5.richard2.common.*;


public class CreditPaymentImpl extends PaymentImpl implements CreditPayment{
	private String cardNumber;
    private String expireDate;
    
    public CreditPaymentImpl( )throws RemoteException
    {
    	super();
    }

    public CreditPaymentImpl(int paymentID,String cardNumber, String expireDate, double amountFee )throws RemoteException
    {
    	super();
        this.cardNumber = cardNumber;
        this.expireDate = expireDate;
        this.datePaid = new Date(); 
        this.amountFee = amountFee;
        this.paymentType = PaymentType.Credit;
    }
    
    public double getAmountFee() throws RemoteException
    { return this.amountFee;}
    
    public String getCardNumber() throws RemoteException
    {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) throws RemoteException
    {
        this.cardNumber = cardNumber;
    }

    public String getExpireDate() throws RemoteException
    {
        return expireDate;
    }

    public void setExpireDate(String expireDate) throws RemoteException
    {
        this.expireDate = expireDate;
    }
    
    /*
    public boolean isAccountValid() throws RemoteException
    {
		if(isExpireDateValid()&&isCardNumberValid()){
			return true;
		}
		else return false;
	}
	*/
	
	public boolean isMonthValid(String m_expireDate) throws RemoteException
	{  //check expire date format
		String ed = m_expireDate;
		SimpleDateFormat dtfmt = new SimpleDateFormat("MM/yyyy");
		try{
			dtfmt.parse(ed); 
		}
		catch (ParseException e)
        {
            System.out.println("Date format is invalid");
            return false;
        }
		return true;
	}
	
	public boolean isExpireDateValid(String m_expireDate) throws RemoteException
	{  //check expire date format
		Calendar calExpire = Calendar.getInstance();
		Calendar calNow = Calendar.getInstance();
		SimpleDateFormat dtfmt = new SimpleDateFormat("MM/yyyy");
		Date dateExpire = null;
		try{
			dateExpire = dtfmt.parse(m_expireDate); 
			calExpire.setTime(dateExpire);
			calNow.setTime( new Date());
			
			calExpire.set(Calendar.DAY_OF_MONTH, 10);
			calNow.set(Calendar.DAY_OF_MONTH, 5);
			
			if(calExpire.compareTo(calNow) >=0)
				return true;
			else
				return false;					
		}
		catch (ParseException e)
        {
            System.out.println("Date format is invalid");
            return false;
        }
	}
	
	public boolean isCardNumberValid(String m_cardNumber) throws RemoteException
	{
		String actNum= m_cardNumber;
	    for(char c : actNum.toCharArray()) 
	    {
	        if(!Character.isDigit(c)){
	        	System.out.println("\nAccount number ("+m_cardNumber+") format is invalid.\n");
	        	return false;
	        }
	    }
		return true;
	}
	
	public boolean isLengthValid(String m_cardNumber) throws RemoteException
	{
		if(m_cardNumber.length()!=16){  
			System.out.println("Account number ("+m_cardNumber+") length is invalid.");
			return false;
		}
		return true;
	}
	
	
}
