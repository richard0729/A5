package cs414.a5.richard2.server;
import cs414.a5.richard2.common.*;

class Sign {

  
  private signStatus status;
  
  public Sign()
  {
		status =signStatus.available;
  }
  
  public signStatus getStatus()
  {
	  return status;
	  //return "";
  }
  
  public void updateSign(boolean isFull) {
	  if(isFull)
		  status  = signStatus.full;
	  else
		  status  = signStatus.available;
  }

}
