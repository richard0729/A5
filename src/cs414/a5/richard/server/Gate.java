package cs414.a5.richard2.server;

class Gate {
	private GateType type;
	private boolean isOpen;
	public Gate(GateType t){
		type = t;	
		isOpen = false;
	}

	void closeGate(){	
		isOpen = false;
		System.out.println(type + " gate closed.\n");
	}
	void openGate(){
		isOpen = true;
		System.out.println(type + " gate open.");
		if(type ==GateType.entry)
			System.out.println(" You have entered garage.");
		else
			System.out.println(" You have exited garage.");
	}	
	public GateType getType(){
		return type;
	}
	
	public String getState() {
		if (isOpen)
			return "open";
		else
			return "close";
	  }
	
	public boolean getIsOpen(){
		return isOpen;
	}
	
	public void autoOpenClose() {
		openGate();
		closeGate();
	}
  
}
