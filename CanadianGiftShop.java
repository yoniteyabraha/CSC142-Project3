import uwcse.io.Input; // use the Input class
import java.text.DecimalFormat; // to format the output

/**
 * Homework 3 <br>
 * Simulating a purchase in Canada paid in US dollars
 * 
 * @author <Yonas Abraha>
 */

public class CanadianGiftShop {

	// Constants
	/** Exchange rate 1 US dollar = RATE Canadian dollar */

	public final double RATE = 1.16;

	/** Price of a jar of maple syrup in Canadian dollars before taxes */
	public final double JAR_PRICE = 5.95;
	/**
	 * Price of photograph of the city of Victoria in Canadian dollars before taxes
	 */
	public final double PHOTO_PRICE = 7.65;

	/** Price of a beaver hat in Canadian dollars before taxes */
	public final double HAT_PRICE = 16.35;

	/** Maximum allowed number of purchased items for each item */
	public final int MAX_ITEM = 100;

	/** Tax rate */
	public final double TAX_RATE = 0.093;

	// instance variables
	// number of purchased jars of maple syrup
	private int jarNumber;

	// number of purchased photographs of the city of Victoria
	private int photoNumber;

	// number of purchased beaver hats
	private int hatNumber;

	// 2 digits after the decimal point for doubles
	private DecimalFormat twoDigits = new DecimalFormat("0.00");

	// total prices of the purchased items before tax
	private double totalJarPrice, totalPhotoPrice, totalHatPrice;

	private double payUS, costCA;

	/**
	 * Takes and processes the order from the customer
	 */
	public void takeAndProcessOrder() {
		// Here is a possible series of steps: call some other (private)
		// methods to do each step.

		// Display the items and their prices
		itemList();
		// Get the Customer's order
		input();
		// Get the user's USD payment
		changeinCAD(payUS, costCA);
		// Give the change back in Canadian dollars
	}

	// Some ideas for some private methods
	// You don't have to use exactly these same methods.

	/**
	 * Displays the items for sale and their prices in Canadian dollars
	 */
	private void itemList() {
		System.out.println("                Welcome to Canadian Wonders\n                 *********************************");
		System.out.println("Here is a price list of our most popular products (in Canadian dollars)\n");
		System.out.println("Jar of Maple Syrup:  $" + JAR_PRICE);
		System.out.println("Photograph of Victoria:  $" + PHOTO_PRICE);
		System.out.println("Beaver Hat:  $" + HAT_PRICE);
		System.out.println("\nThe above prices don't include taxes. The tax rate is  " + twoDigits.format(TAX_RATE * 100) + "%");
		System.out.println("Our exchange rate is 1 US dollar = " + RATE + " Canadian dollars");
	}

	/**
	 * Gets the customer's order Precondition: none Postcondition: jarNumber,
	 * photoNumber and hatNumber are initialized to a value between 0 and MAX_ITEM
	 */
	private void input() {
		Input in = new Input();
		// read an integer from the keyboard
		jarNumber = in.readInt("\nHow many jars of maple syrup would you like? ");
		// If a number of items is not between 0 and 100, set it to 0.
		if (jarNumber < 0) {
			System.out.println(jarNumber + " is not a valid number of items!");
			//System.exit(0);
		}
		if (jarNumber > MAX_ITEM) {
			System.out.println("Sorry, we don't have that many in stock");
			//System.exit(0);
		}
		photoNumber = in.readInt("How many photographs of Victoria would you like? ");
		// If a number of items is not between 0 and 100, set it to 0.
		if (photoNumber < 0) {
			System.out.println(photoNumber + " is not a valid number of items!");
		}
		if (photoNumber > MAX_ITEM) {
			System.out.println("Sorry, we don't have that many in stock");
		}
		hatNumber = in.readInt("How many beaver hats would you like? ");
		// If a number of items is not between 0 and 100, set it to 0.
		if (hatNumber < 0) {
			System.out.println(hatNumber + " is not a valid number of items!");
		}
		if (hatNumber > MAX_ITEM) {
			System.out.println("Sorry, we don't have that many in stock");
		}
	}

	/**
	 * Given a purchase in canadian dollars and a payment in US dollars, displays
	 * the change amount in canadian dollars and cents
	 * 
	 * @param payUS  payment in US dollars
	 * @param costCA purchase amount in Canadia dollars
	 */

	private void changeinCAD(double payUS, double costCA) {
		// get the total prices before tax if the number of items are between 0 and 100
		
		//get the total jar price before tax
		if (jarNumber >= 0 && jarNumber <= MAX_ITEM) {
			totalJarPrice = jarNumber * JAR_PRICE;
		}
		//get the total photo price before tax
		if (photoNumber >= 0 && photoNumber <= MAX_ITEM) {
			totalPhotoPrice = photoNumber * PHOTO_PRICE;
		}
		//get the total hat price before tax
		if (hatNumber >= 0 && hatNumber <= MAX_ITEM) {
			totalHatPrice = hatNumber * HAT_PRICE;
		}
		
		double totalPurchasedBeforeTax = (totalJarPrice + totalPhotoPrice + totalHatPrice);     //total price before tax
		double totalPurchasedIncludingTax = totalPurchasedBeforeTax + totalPurchasedBeforeTax * TAX_RATE;      //total price after tax
		
		//print out the total price to the customer in Canadian dollars
		System.out.println( "\nYour purchase total is  $" + twoDigits.format(totalPurchasedIncludingTax) + " CA (tax included)");
		
		// read an integer from the keyboard
		Input in = new Input();
		payUS = in.readDouble("\nPlease, enter the US dollar amount to pay for it: ");     //amount of money in US dollars
		if (totalPurchasedIncludingTax > payUS * RATE) {
			System.out.println("\nSorry, this is not enough");
		} else {
			System.out.println("\nYou gave $" + twoDigits.format(Math.round(payUS * 100) / 100.0) + " US, which is " + twoDigits.format(payUS * RATE) + " CA");
			
			//get the change after purchase
			double change = payUS * RATE - totalPurchasedIncludingTax;
			System.out.println("\nHere is your change:  $" + twoDigits.format(change));     //prints the change

			// get decimal part of the change
			String string = twoDigits.format((Math.round(change * 1000) / 1000.0 - (int) change));

			// change the string to double
			double d = Double.parseDouble(string);

			// change the double to int
			int deciInt = (int) (d * 100);

			// break down the change to 20's, 10's 5's and 1's bills
			int CADollars = (int) change;
			int twenties = CADollars / 20;
			int dollars1 = CADollars % 20;
			int tens = dollars1 / 10;
			int dollars2 = dollars1 % 10;
			int fives = dollars2 / 5;
			int dollars3 = dollars2 % 5;
			int ones = dollars3;
			
			// break down the cents to 25c,10c,5c,1c
			int centsChange = deciInt;
			int quarters = centsChange / 25;
			int cents1 = centsChange % 25;
			int dimes = cents1 / 10;
			int cents2 = cents1 % 10;
			int nickels = cents2 / 5;
			int cents3 = cents2 % 5;
			int pennies = cents3;
			
			//break down the change

			if (twenties != 0) {
				if (twenties==1) {
					System.out.println(twenties + "  $20 bill");
				}else {
				System.out.println(twenties + " $20 bills");
				}
		  }if (tens != 0) {
				System.out.println(tens + " $10 bill");
			}
			if (fives != 0) {
				System.out.println(fives + " $5 bill");
			}
			if (ones > 0) {
					System.out.println(ones + " $1 bill" + ((ones>1)? "s": ""));
		   }if (quarters != 0) {
			   if(quarters==1) {
				   System.out.println(quarters + " 25c coin");
			   }else {
				System.out.println(quarters + " 25c coins");
			}
		   }if (dimes != 0) {
			   if(dimes==1) {
				   System.out.println(dimes + " 10c coin");
			   }else {
				System.out.println(dimes + " 10c coins");
			}
		   }if (nickels != 0) {
				System.out.println(nickels + " 5c coin");
			}
			if (pennies != 0) {
				if(pennies==1) {
					System.out.println(pennies + " 1c coin");
				}else {
				System.out.println(pennies + " 1c coins");
			}
			}System.out.println("\nThank you for your business!");
		}
	}
	/**
	 * Entry point of the program
	 */
	public static void main(String[] args) {
		new CanadianGiftShop().takeAndProcessOrder();
	}

}