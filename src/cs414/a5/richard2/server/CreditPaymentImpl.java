package cs414.a5.richard2.server;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import cs414.a5.richard2.common.*;


public class CreditPaymentImpl extends PaymentImpl implements CreditPayment{
	private String cardNumber;
    private String expireDate;

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
    
    public boolean isAccountValid() throws RemoteException
    {
		if(isExpireDateValid()&&isCardNumberValid()){
			return true;
		}
		else return false;
	}
	
	public boolean isExpireDateValid() throws RemoteException
	{  //check expire date format
		String ed = expireDate;
		SimpleDateFormat dtfmt = new SimpleDateFormat("MM/yyyy");
		Date date = null;
		try{
			date = dtfmt.parse(ed); 
		}
		catch (ParseException e)
        {
            System.out.println("Date format is invalid");
            return false;
        }
		return true;
	}
	
	public boolean isCardNumberValid() throws RemoteException
	{
		String actNum= cardNumber;
	    for(char c : actNum.toCharArray()) 
	    {
	        if(!Character.isDigit(c)){
	        	System.out.println("\nAccount number format is invalid.\n");
	        	return false;
	        }
	    }
		if(actNum.length()!=16){  
			System.out.println("Account number length is invalid.");
			return false;
		}
		return true;
	}
}
