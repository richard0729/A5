package cs414.a5.richard2.server;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreditPayment extends Payment {

	private String cardNumber;
    private String expireDate;

    public CreditPayment(String cardNumber, String expireDate, double amountFee)
    {
        this.cardNumber = cardNumber;
        this.expireDate = expireDate;
        this.datePaid = new Date(); 
        this.amountFee = amountFee;
        this.paymentType = PaymentType.Credit;
    }
    
    public double getAmountFee(){ return this.amountFee;}
    
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
    
    public boolean isAccountValid(){
		if(isExpireDateValid()&&isCardNumberValid()){
			return true;
		}
		else return false;
	}
	
	public boolean isExpireDateValid(){  //check expire date format
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
	public boolean isCardNumberValid(){
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
