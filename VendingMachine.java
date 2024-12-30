/*Assignment 2 (VendingMachine): Lilli Lewis 
 * 9/27/23
 * I used this website to round my doubles so they wouldn't cause issues such as .04999 not being .05.
 * 	https://www.prepbytes.com/blog/java/how-to-convert-double-to-int-in-java/#:~:text=round()%20method
 * 	%20to%20convert,long%20value%20to%20an%20int.&text=double%20doubleValue%20%3D%2052.6345%3B%20int%20
 * 	intValue,round(doubleValue)%3B
 * I confirm that the above list of sources is complete AND that I have 
 *  not talked to anyone else about the solution to this problem.*/
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class VendingMachine {

	// this ArrayList stores the information for all of the
	// brands of soda for sale in the vending machine.  
	private ArrayList<BrandInventory> inventory = new ArrayList<BrandInventory>();
	private CoinBox ourCoins = new CoinBox();
	private BillBox ourBills = new BillBox();
	private CoinBox depositCoins = new CoinBox();
	private BillBox depositBills = new BillBox();
	

	//constructor
	public VendingMachine() {
		try {//file might not exist or not be able to be read
			// Read the file and create the ArrayList with the information about the sodas in stock
			File f = new File("config.dat"); //I deleted empty last line in config.dat
			if(f.isFile() && f.canRead()) { //if file exists and can be read
				Scanner input = new Scanner(f); //create Scanner for file
				while(input.hasNextLine()) {// load info into inventory array
					String brandID = "";
					float sodaPrice = 0.0f;
					int quantity = 0;
					if(input.hasNext()) {
						brandID = input.next();
					}
					if(input.hasNextFloat()) {
						sodaPrice = input.nextFloat();
					}
					if(input.hasNextInt()) {
						quantity = input.nextInt();
					}
					inventory.add(new BrandInventory(brandID, sodaPrice, quantity));
				}//while
				input.close();//close file
			}//if
		}//try
		catch(FileNotFoundException e){//handle possible exception from opening file
			Display display = new Display();
			display.error("File could not be found.");
		}//catch
		catch(NoSuchElementException e) {
			Display display = new Display();
			display.error("no such element found.");
		}
		// load up initial change in the change box so that you can make change
		// for early transactions ... 10 coins each as a starting point
		this.ourCoins.setQuarters(10);
		this.ourCoins.setNickels(10);
		this.ourCoins.setDimes(10);	
	} // end constructor
	
	

	/**
	 * Checks if the selected ID is valid and is not sold out
	 * @param purchaseID
	 * @return boolean, true or false, if the purchaseID's corresponding count is positive
	 */
	public boolean isAvailable(String purchaseID) {
		boolean available = false; //if ID has a corresponding positive count
		Display display = new Display();
		for(int i = 0; i<inventory.size(); i++) {
			if(purchaseID.equals(inventory.get(i).getID())) {//if purchase ID is in the inventory
				available = (inventory.get(i).getCount() > 0);//check if count is greater than 0
			}//if purchase ID is in the inventory array
		}//for
		if(!available){
			display.show("Incorrect ID or out of stock, please try again.");
			}
		return available;
	}//isAvailable

	/**
	 * Sums amount of bills and value of coins deposited and returns that double
	 * @return double, the amount deposited by the user
	 */
	public double getAmtDeposited() {
		return depositCoins.currentAmt() + (double) depositBills.getCurrentBills();
	}
	
	/**
	 * increments depositBill
	 */
	public void acceptBill() {
		depositBills.depositBill();
 	}
	
	/**
	 * increments depositCoins's quarters
	 */
	public void acceptQuarter() {
		depositCoins.depositQuarter();
    }
  
	/**
	 * increments depositCoins's dimes
	 */
    public void acceptDime() {
    	depositCoins.depositDime();
    }
  
    /**
     * increments depositCoins's nickels
     */
   public void acceptNickel() {
	   depositCoins.depositNickel();
   }
   
   /**
    * increments money accordingly
    * @param str, the input money type
    */
   public void acceptMoney(String str) {
	   if (str.equals("quarter")) {
		   acceptQuarter();
	   } else if (str.equals("dime")) {
		   acceptDime();
	   } else if (str.equals("nickel")) {
		   acceptNickel();
	   }else if (str.equals("bill")) {
		   acceptBill();
	   }else {
		   Display display = new Display();
		   display.error("Improper input, please try again.");
	   }
	
   }
   
   
   /**
    * returns a boolean, ability of machine to make exact change for dollar_amount
    * @param dollar_amount
    * @return boolean, true or false, true if machine can make exact change
    */
   public boolean makeChange(double dollar_amount) {
	   Display display = new Display();
	   int q = ourCoins.getQuarterCount();
	   int d = ourCoins.getDimeCount();
	   int n = ourCoins.getNickelCount();
	   int qi = q;//initial quarter count
	   int di = d;//initial dime count
	   int ni = n;//initial nickel count
	   int cents_amount = (int) Math.round((dollar_amount * 100.00));
	   
	   //loop through and remove quarters until out of quarters or less than 25 cents
	   while((q > 0) && (cents_amount > 24)) {
		   cents_amount -= 25;
		   q--;
	   }
	   ourCoins.setQuarters(q);
	 
	   //loop through and remove dimes until out of dimes or less than 10 cents
	   while((d > 0) && (cents_amount > 9)) {
		   cents_amount -= 10;
		   d--;
	   }
	   ourCoins.setDimes(d);
	 
	   //loop through and remove nickels until out of nickels or less than 5 cents
	   while((n > 0) && (cents_amount > 4)) {
		   cents_amount -= 5;
		   n--;
	   } 
	   ourCoins.setNickels(n);
	 
	   display.takeChange((qi-q), (di-d), (ni-n));
	   
	   //if there weren't the right coins to make exact change
	   if(cents_amount == 0) {
		   return true;
	   }else {
		   display.notExactChange();
		   return false;
	   }
   }
	
   
	/**
	 * diagnostic method to check the stock; prints inventory
	 */
	public void printInventory() {
		Display display = new Display();
		for(int i = 0; i<inventory.size(); i++) {
			display.show(inventory.get(i).toString());
		}
	}
	
	
	/**
	 * print valid user options (brands with a positive count)
	 */
	public void printChoices() {
		Display display = new Display();
		int count = 0;
		for(int i = 0; i<inventory.size(); i++) {
			if(inventory.get(i).getCount() > 0) {//only inventory with positive count
				count++;
				if(count == 1) {//first iteration with options
					display.show("Current selection: ");
				}
				display.show("Brand ID: " + inventory.get(i).getID() + " price: $" + display.toDollarFormat(inventory.get(i).getPrice()));
			}//if
		}//for
		if(count == 0) {
			display.allSoldOut();
		}
	}//printChoices
	

	/**
	 * transfer coins and bills from deposit to change box and reset deposit coin box and bill counter
	 */
	public void completeSale() {
		ourCoins.transferCoinsFrom(depositCoins);
		for(int i = 0; i < depositBills.getCurrentBills(); i++) {
			ourBills.depositBill();
		}
		depositBills.resetBills();
	}
	
	/**
	 * gives price associated with ID
	 * @param id
	 * @return double price
	 */
	public double priceFromInventory(String id) {
		for(int i = 0; i<inventory.size(); i++) {
			if (inventory.get(i).getID().equals(id)){
				return inventory.get(i).getPrice();
			}
		}//if it makes it through, that means incorrect id
		Display display = new Display();
		display.error("Error: incorrect ID input. Please try again.");
		return 0.0;
	}
	
	/**
	 * Decrements the count of the corresponding ID in the inventory array list
	 * @param id
	 */
	public void decrementInventory(String id) {
		boolean found = false;
		for(int i = 0; i<inventory.size(); i++) {
			if (inventory.get(i).getID().equals(id)){
				inventory.get(i).reduceOnHand();
				found = true;
			}
		}//if it makes it through, that means incorrect id
		if(!found) {
			Display display = new Display();
			display.error("Incorrect ID input. Please try again.");
		}
	}

} // end of class
