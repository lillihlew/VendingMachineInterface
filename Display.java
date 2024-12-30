/*Assignment 2 (Display): Lilli Lewis 
 * 9/27/23
 * I confirm that the above list of sources is complete (none) AND that I have 
 *  not talked to anyone else about the solution to this problem.*/
public class Display {
	
	/**
	 * Displays the message passed in from calling method
	 * @param message
	 */
	public void show(String message) {
		System.out.println(message);
	}
	
	
	/**
	 * Message that informs user of an error
	 * @param message
	 */
	public void error(String message) {
		System.out.println("Error: " + message);
	}
	
	
	/**
	 * Informs user of change returned via printing to terminal
	 * @param q (quarters)
	 * @param d (dimes)
	 * @param n (nickels)
	 */
	public void takeChange(int q, int d, int n) {
		System.out.println("Please take your change:");
		System.out.println(q + " quarters " + d + " dimes " + n + " nickels.");
	}
	
	
	/**
	 * Inform user that specific brand is sold out
	 */
	public void soldOut() {
		System.out.println("Sorry, that brand is sold out.");
	}
	
	/**
	 * Inform user that everything is sold out
	 */
	public void allSoldOut() {
		System.out.println("Sorry, everything is sold out.");
	}
	

	/**
	 * Inform user that sale was successful
	 */
	public void sold() {
		System.out.println("Sale successful. Please take your beverage.");
	}
	
	
	/**
	 * Inform user machine cannot make exact change
	 */
	public void notExactChange() {
		System.out.println("Sorry, cannot make exact change!");
	}
	
	
	/**
	 * instruct user how much change there is vs how much is owed
	 */
	public void changeOwed(double price, double amtDeposited) {
		System.out.println("Price: $" + toDollarFormat(price) + " Deposited: $" + toDollarFormat(amtDeposited));
	}
	
	/**
	 * convert fl to formatted String
	 * @param fl
	 * @return String, fl in dollar format
	 */
	public String toDollarFormat(double fl) {
		return String.format("%.2f", fl);
	}

}