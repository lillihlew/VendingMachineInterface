/*Assignment 2 (VendingMachineSim): Lilli Lewis 
 * 9/27/23
 * I used the provided code and readings. I also met with Jimenez about the while loop.
 * I confirm that the above list of sources is complete AND that I have 
 *  not talked to anyone else about the solution to this problem.*/

import java.util.Scanner;

public class VendingMachineSim {

	public static void main (String [] args){
		boolean runSim = true;
		Display display = new Display();
	
		// remember to give some introduction to the simulation
		display.show("Welcome to the Soda Shack!!");
		display.show("Hang on while I get set up.");
		
		// create the Vending Machine object itself
		VendingMachine vm = new VendingMachine();
		
		//create and initialize scanner
		Scanner sc = new Scanner(System.in);
		String response = "";
		
		while(runSim) {
			//provide options for user
			display.show("Do you want to buy a beverage? (yes or no)");
			
			//store user response and proceed accordingly
			if(sc.hasNext())
				response = sc.nextLine();
			
       		if (response.toLowerCase().equals("yes")) {
       			vm.printChoices(); //display available beverages
       			display.show("Enter the ID of the beverage you want.");
       			if (sc.hasNext())
           			response = sc.nextLine();//store user input
       			if(vm.isAvailable(response)) {//determine if user input is valid and in stock
       				String currentID = response; //save currentID in a String to be used later
       				double itemPrice = vm.priceFromInventory(response);//retrieve price associated with ID
       				/*display amount of input and required money, prompting user to input money until
       				 * required amount is met or surpassed, and updating accordingly*/
       				while(itemPrice > vm.getAmtDeposited()) {
       					display.changeOwed(itemPrice, vm.getAmtDeposited());
       					display.show("Insert bill, quarter, dime, or nickel.");       					
       					if(sc.hasNext())
       						response = sc.nextLine();
       					vm.acceptMoney(response.toLowerCase());
       				}
       				display.sold();//tell user transaction was successful
       				//inform user off change owed back
       				double changeDue = vm.getAmtDeposited() - itemPrice; 
       				display.show("Change due: $" + display.toDollarFormat(changeDue));
       				//complete sale before giving change back so we have max number of coins with which to reimburse
       				vm.completeSale();
       				//make change if necessary
       				if (changeDue > 0.0) 
       					vm.makeChange(changeDue);
       				//update inventory
       				vm.decrementInventory(currentID);
       			}//if beverage is available
       		}  // if user says "yes" to buying a beverage   
       		else {//if user says "no" to buying a beverage
       			runSim = false;
       			display.show("Thank you for visiting the Soda Shack!");
       		}       		
		}	// while 
		sc.close();
	} // main
} // class