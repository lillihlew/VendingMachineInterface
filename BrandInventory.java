/*Assignment 2 (BrandInventory): Lilli Lewis 
 * 9/27/23
 * I recycled some code Ganga and I worked on from our TypeValuePair lab
 * I confirm that the above list of sources is complete (none) AND that I have 
 *  not talked to anyone else about the solution to this problem.*/
public class BrandInventory {
// this class keeps track of the information for each separate brand stocked
// in the vending machine.  The machine will keep an ArrayList of these objects
	
	//  private data fields
	private String id;
	private float price;
	private int numItems;
	
	//constructor
	public BrandInventory(String id, float price, int numItems) {
		this.id = id;
		this.price = price;
		this.numItems = numItems;
	}

	/**
	 * ID accessor method
	 * @return String ID
	 */
	public String getID() {
		return this.id;
	}
	
	/**
	 * Price accessor method
	 * @return float price
	 */
	public float getPrice() {
		return this.price;
	}
	
	/**
	 * numItems accessor method
	 * @return int numItems
	 */
	public int getCount() {
		return this.numItems;
	}
	

	/**
	 * updates the available number of sodas after a successful purchase.
	 */
	 public void reduceOnHand() {
		 this.numItems--;
	 }
	 
	
	/**
	* Returns true if the number on hand for this brand has dropped to 0
	* @return boolean
	*/
	public boolean isSoldOut() {
		return (this.numItems == 0);
	}
	
	
	/**
	 * Returns brand and price of a soda
	 * @return String, the brand ID and price for a soda
	 */
	@Override
	public String toString() {
		Display display = new Display();
		return this.getID() + ": $" + display.toDollarFormat(this.getPrice()) + " Quantity: " + this.getCount();
	}


}