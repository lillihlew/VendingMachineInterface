/*Assignment 2 (CoinBox): Lilli Lewis 
 * 9/27/23
 * I confirm that the above list of sources is complete (none) AND that I have 
 *  not talked to anyone else about the solution to this problem.*/
public class CoinBox {
	 //fields
	protected int nickels;
	protected int dimes;
	protected int quarters;
	// no pennies or dollar coins in this implementation
  

	//zero parameter constructor initializing number of coins to zero
	public CoinBox() {
		this.nickels = 0;
		this.dimes = 0;
		this.quarters = 0;
	}
	
	/**
	 * Calculates and returns total amount of coins in dollar format
	 * @return double, current amount of cents
	 */
	public double currentAmt() {
		return ((double)this.nickels*.05) + ((double)this.dimes * .1) + ((double)this.quarters * .25); 
	}
  
 
	/**
	 * Increments number of quarters
	 */
	public void depositQuarter() {
		this.quarters++;
	}
	
	/**
	 * Increments number of dimes
	 */
	public void depositDime() {
		this.dimes++;
	}
	
	/**
	 * Increments number of nickels
	 */
	public void depositNickel() {
		this.nickels++;
	}
    
	/**
     * Methods for returning the deposited
     * quarters. 
     * @return deposited quarters
     */ 
	public int getQuarterCount() {
		return this.quarters;
	}
 
	/**
	 * Methods for returning the deposited
	 * dimes. 
	 * @return deposited dimes
	 */ 
	public int getDimeCount() {
		return this.dimes;
	}
	
	/**
	 * Methods for returning the deposited
	 * nickels. 
	 * @return deposited nickels
	 */ 
	public int getNickelCount() {
		return this.nickels;
	}


	/**
	 * Sets the number of quarters to num_coins
	 * @param num_coins, the new number of quarters
	 */
  public void setQuarters(int num_coins) {
	  if (num_coins > -1)
		  this.quarters = num_coins;
  }

	/**
	 * Sets the number of dimes to num_coins
	 * @param num_coins, the new number of dimes
	 */
  public void setDimes(int num_coins) {
	  if (num_coins > -1)
		  this.dimes = num_coins;
  }
  
	/**
	 * Sets the number of nickels to num_coins
	 * @param num_coins, the new number of nickels
	 */
  public void setNickels(int num_coins) {
	  if (num_coins > -1)
		  this.nickels = num_coins;
  }
 
  /**
   * Transfer coins from a coin box to another coin box.
   * @param other, the CoinBox that accepts coins from user. 
   */ 
  public void transferCoinsFrom(CoinBox other) {
	  // take from the other box and deposit in this box
	  this.quarters += other.getQuarterCount();
	  this.nickels += other.getNickelCount();
	  this.dimes += other.getDimeCount();
	  other.resetBox();
  }
  
  /**
   * Reset the number of coins in the coin box to be all zeros
   */
  public void resetBox() {
	  this.setQuarters(0);
	  this.setNickels(0);
	  this.setDimes(0);
	  }
  } // end class
  

