/*Assignment 2 (BillBox): Lilli Lewis 
 * 9/27/23
 * I confirm that the above list of sources is complete (none) AND that I have 
 *  not talked to anyone else about the solution to this problem.*/
public class BillBox {
	/* private data fields  */
	private int oneDollar;

	/* zero argument constructor */
	public BillBox() {
		oneDollar = 0;
	}

	/**
	 *  Increments number of bills 
	 */
	public void depositBill() {
		this.oneDollar++;
	}
    	
	/** 
	 * Resets the deposited bill in the current transaction
	 */
	public void resetBills() {
		this.oneDollar = 0;
	}
	
	/**
	 * Gets number of recently deposited bills
	 * @return int the number of recently deposited bills 
	 */
	public int getCurrentBills() {
		return this.oneDollar;
	}
	
	
}
