package cs414.a5.richard2.server;

class Sign {

/*
  private String state = "PARKING AVAILABLE";

  public void available() {
    state = "PARKING AVAILABLE";
  }

  public void full() {
    state = "FULL";
  }

  public String status_as_string() {
    return state;
  }
*/
  
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
