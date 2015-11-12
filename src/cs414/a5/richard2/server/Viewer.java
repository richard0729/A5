package cs414.a5.richard2.server;

public class Viewer {

	/*
	public static void clearscreen() {
	    // This only works on POSIX
	    System.out.print("\033[2J\033[;H");
	  }
	*/
	public static String getInput() {
	    try {
	      java.io.BufferedReader stdin = new java.io.BufferedReader(
	        new java.io.InputStreamReader(System.in)
	      );
	      String input = stdin.readLine();
	      return input;
	    }
	    catch (java.io.IOException e) { System.out.println(e); }
	    return "";
	  }

	  public static int printMenu(String title, String[] choices) {
		System.out.println("");
		System.out.println("");
		System.out.println("MENU: " + title);
	    System.out.println("");
	    for(int n = 1; n <= choices.length; n++) {
	      System.out.println( n + ") " + choices[n - 1] );
	    }
	    System.out.println("");
	    System.out.print(">> ");
	    String input = getInput();
	    try {
	      int choice = Integer.parseInt(input);
	      return choice;
	    }
	    catch (java.lang.NumberFormatException e) {
	      return 0;
	    }
	  }

	  public static void printContinue() {
	    System.out.print("[Press key to exit]");
	    getInput();
	  }

	  public static String printString(String mString, String default_choice) {
	    System.out.print(mString);
	    String in = getInput();
	    if(in.equals("")) {
	      return default_choice;
	    } else {
	      return in.toLowerCase();
	    }
	  }

}
