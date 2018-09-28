package application;

import java.util.Scanner;

import airline.Facade;
import utilities.DateTime;

/*
 * Class:			Menu
 * Description:		The class represents a menu that the user will use to
 * 					interact with the application class via a facade. 
 * Author:			[Yasir Fayrooz Ali] - [s3742162]
 */
public class Menu
{
	
	private Facade facade = new Facade();
	private Scanner scanner = new Scanner(System.in);
	private boolean exitProgram = false;
	private boolean returnToMenu = false;
	private String input = "";
	
	public Menu()
	{
		
	}
	public void run()
	{
		while(exitProgram == false)
		{
			printMenu();
			checkMenuInput();
		}
		scanner.close();
	}
	
	private void printMenu()
	{
		returnToMenu = false;
		String simbaMenu = "* Simba Airways System Menu *\n\n";
		String addBooking = String.format("%-26s %s\n", "Add Booking", "AB");
		String bookSeat = String.format("%-26s %s\n", "Book Seat", "BS");
		String checkInBaggage = String.format("%-26s %s\n", "Checkin Baggage", "CB");
		String pickUpBaggage = String.format("%-26s %s\n", "Pick up Baggage", "PB");
		String displayBookingDetails = String.format("%-26s %s\n", "Display Booking Details", "DB");
		String displayAllBookings = String.format("%-26s %s\n", "Display ALL Bookings", "DA");
		String displayHistoricalBaggage = String.format("%-26s %s\n", "Display Historical Baggage", "HB");
		String seedData = String.format("%-26s %s\n", "Seed Data", "SD");
		String exitProgram = String.format("%-26s %s\n", "Exit Program", "EX");

		System.out.println(simbaMenu +
						   addBooking +
						   bookSeat +
						   checkInBaggage +
						   pickUpBaggage +
						   displayBookingDetails +
						   displayAllBookings +
						   displayHistoricalBaggage +
						   seedData +
						   exitProgram);
	}
	
	private void checkMenuInput()
	{
		boolean menuChecked = false;
		do
		{
			System.out.print("Enter selection:");
			input = scanner.nextLine();
			String[] options = new String[] {"AB", "BS", "CB", "PB", "DB",
											 "DA", "HB", "SD", "EX"};
			
			for (int i = 0; i < options.length; i++)
			{
				if(input.equalsIgnoreCase(options[i]))
				{
					input = options[i];
				}
			}
			switch(input)
			{
			case "AB":
				menuChecked = true;
				addBooking();
				break;
			case "BS":
				menuChecked = true;
				bookSeat();
				break;
			case "CB":
				menuChecked = true;
				checkInBaggage();
				break;
			case "PB":
				menuChecked = true;
				pickUpBaggage();
				break;
			case "DB":
				menuChecked = true;
				displayBookingDetails();
				break;
			case "DA":
				menuChecked = true;
				displayAllBookings();
				break;
			case "HB":
				menuChecked = true;
				displayHistoricalBaggage();
				break;
			case "SD":
				menuChecked = true;
				seedData();
				break;
			case "EX":
				menuChecked = true;
				exitProgram();
				break;
				
				default:
					System.out.println("\nError: Please enter a valid option from the list above.");
					break;
			}
		}
		while(menuChecked == false);
	}
	private void exitProgram()
	{
		exitProgram = true;
	}
	private void seedData()
	{
		facade.seedData();
		System.out.print("Press enter to go back to the menu...\n");
		input = scanner.nextLine();			
	}
	private void displayHistoricalBaggage()
	{
		String flightId = "Must be 2 letters followed by a number (excluding 0)";
		String seatId = "Must be a letter between A and I & a number between 1 and 9";
		String goBack = "To go back to the menu, hit the enter key\n\n";
		
		String displayHistoricalBaggageMenu = "\n* Display historical baggage: *\n";
		
		
		System.out.println(displayHistoricalBaggageMenu +
		 		   goBack +
		 		   String.format("%-26s %s\n", "Flight ID:", flightId) +
		 		   String.format("%-26s %s\n", "Seat:", seatId));
		
		
		//Validates the flight and seat ID.
		while(!facade.isValidId(flightId, seatId).equalsIgnoreCase("Validated ID") ||
				  facade.checkIfBookingExists(flightId, seatId) == false)
		{
			System.out.print("Enter Flight ID:");
			flightId = scanner.nextLine(); if(c(flightId)) break;
			System.out.print("Enter Seat & Row:");
			seatId = scanner.nextLine(); if(c(seatId)) break;
			if(!facade.isValidId(flightId, seatId).equals("Validated ID"))
			System.out.println(facade.isValidId(flightId, seatId));
			
			System.out.println(facade.displayHistoricalBaggage(flightId, seatId));
		}	
		if(returnToMenu == false)
		{
			System.out.print("Press enter to go back to the menu...\n");
			input = scanner.nextLine();
		}
		
	}
	private void displayAllBookings()
	{
		System.out.println(facade.displayAllBookings());
		System.out.print("Press enter to go back to the menu...\n");
		input = scanner.nextLine();
	}
	private void displayBookingDetails()
	{
		String flightId = "Must be 2 letters followed by a number (excluding 0)";
		String seatId = "Must be a letter between A and I & a number between 1 and 9";
		String goBack = "To go back to the menu, hit the enter key\n\n";
		
		String displayBookingDetailsMenu = "\n* Display booking details: *\n";
		
		
		System.out.println(displayBookingDetailsMenu +
		 		   goBack +
		 		   String.format("%-26s %s\n", "Flight ID:", flightId) +
		 		   String.format("%-26s %s\n", "Seat:", seatId));
		
		
		//Validates the flight and seat ID.
		while(!facade.isValidId(flightId, seatId).equalsIgnoreCase("Validated ID") ||
				  facade.checkIfBookingExists(flightId, seatId) == false)
		{
			System.out.print("Enter Flight ID:");
			flightId = scanner.nextLine(); if(c(flightId)) break;
			System.out.print("Enter Seat & Row:");
			seatId = scanner.nextLine(); if(c(seatId)) break;
			if(!facade.isValidId(flightId, seatId).equals("Validated ID"))
			System.out.println(facade.isValidId(flightId, seatId));
			
			System.out.println(facade.displayBooking(flightId, seatId));
		}	
		if(returnToMenu == false)
		{
			System.out.print("Press enter to go back to the menu...\n");
			input = scanner.nextLine();
		}
		
	}
	private void pickUpBaggage()
	{
		String flightId = "Must be 2 letters followed by a number (excluding 0)";
		String seatId = "Must be a letter between A and I & a number between 1 and 9";
		String lastName = "Enter your last name";
		String goBack = "To go back to the menu, hit the enter key\n\n";
		
		String addCheckInBaggage = "\n* Pick up baggage *\n";
		
		
		System.out.println(addCheckInBaggage +
		 		   goBack +
		 		   String.format("%-26s %s\n", "Flight ID:", flightId) +
		 		   String.format("%-26s %s\n", "Seat:", seatId) +
		 		   String.format("%-26s %s\n", "Last Name:", lastName));
		
		
		//Validates the flight and seat ID.
		while(!facade.isValidId(flightId, seatId).equalsIgnoreCase("Validated ID") ||
				  facade.checkIfBookingExists(flightId, seatId) == false)
		{
			System.out.print("Enter Flight ID:");
			flightId = scanner.nextLine(); if(c(flightId)) break;
			System.out.print("Enter Seat & Row:");
			seatId = scanner.nextLine(); if(c(seatId)) break;
			if(facade.checkIfBookingExists(flightId, seatId) == false)
			{System.out.println("Error: Booking does not exist");}
			else
				System.out.println(facade.isValidId(flightId, seatId));
		}
		
		
		while(validateCheckInBag(lastName) == false && returnToMenu == false)
		{
			System.out.print("Enter Last Name:");
			lastName = scanner.nextLine(); if(c(lastName)) break;
		}
		if(returnToMenu == false)
		{
			System.out.println(facade.collectBaggage(flightId, seatId, lastName, new DateTime()));
			System.out.print("Press enter to go back to the menu...\n");
			input = scanner.nextLine();
		}
		
	}
	private void checkInBaggage()
	{
		String flightId = "Must be 2 letters followed by a number (excluding 0)";
		String seatId = "Must be a letter between A and I & a number between 1 and 9";
		String lastName = "Enter your last name";
		String goBack = "To go back to the menu, hit the enter key\n\n";
		
		String addCheckInBaggage = "\n* Check in baggage *\n";
		
		
		System.out.println(addCheckInBaggage +
		 		   goBack +
		 		   String.format("%-26s %s\n", "Flight ID:", flightId) +
		 		   String.format("%-26s %s\n", "Seat:", seatId) +
		 		   String.format("%-26s %s\n", "Last Name:", lastName));
		
		
		//Validates the flight and seat ID.
		while(!facade.isValidId(flightId, seatId).equalsIgnoreCase("Validated ID") ||
				  facade.checkIfBookingExists(flightId, seatId) == false)
		{
			System.out.print("Enter Flight ID:");
			flightId = scanner.nextLine(); if(c(flightId)) break;
			System.out.print("Enter Seat & Row:");
			seatId = scanner.nextLine(); if(c(seatId)) break;
			if(facade.checkIfBookingExists(flightId, seatId) == false)
			{System.out.println("Error: Booking does not exist");}
			else
				System.out.println(facade.isValidId(flightId, seatId));
		}
		
		
		while(validateCheckInBag(lastName) == false && returnToMenu == false)
		{
			System.out.print("Enter Last Name:");
			lastName = scanner.nextLine(); if(c(lastName)) break;
		}
		if(returnToMenu == false)
			System.out.print(facade.checkBaggage(flightId, seatId, lastName, 0));
		if(facade.checkBaggage(flightId, seatId, lastName, 0).equals("Enter weight:") && returnToMenu == false)
		{
			double weight = scanner.nextDouble();
			facade.checkBaggage(flightId, seatId, lastName, weight);
			System.out.print("Press enter to go back to the menu...\n");
			input = scanner.nextLine(); input = scanner.nextLine();
		}
	}
	private void bookSeat()
	{
		String flightId = "Must be 2 letters followed by a number (excluding 0)";
		String seatId = "Must be a letter between A and I & a number between 1 and 9";
		String firstName = "Enter your first name";
		String lastName = "Enter your last name";
		String goBack = "To go back to the menu, hit the enter key\n\n";
		
		String addBookingMenu = "\n* Book Seat *\n";
		
		
		System.out.println(addBookingMenu +
		 		   goBack +
		 		   String.format("%-26s %s\n", "Flight ID:", flightId) +
		 		   String.format("%-26s %s\n", "Seat:", seatId) +
		 		   String.format("%-26s %s\n", "First Name:", firstName) +
		 		   String.format("%-26s %s\n", "Last Name:", lastName));
		
		
		//Validates the flight and seat ID.
		while(!facade.isValidId(flightId, seatId).equalsIgnoreCase("Validated ID") ||
				  facade.checkIfBookingExists(flightId, seatId) == false)
		{
			System.out.print("Enter Flight ID:");
			flightId = scanner.nextLine(); if(c(flightId)) break;
			System.out.print("Enter Seat & Row:");
			seatId = scanner.nextLine(); if(c(seatId)) break;
			if(facade.checkIfBookingExists(flightId, seatId) == false)
			{System.out.println("Error: Booking does not exist or is already booked");}
			else
				System.out.println(facade.isValidId(flightId, seatId));
		}
		
		while(validateFirstOrLastName(firstName, lastName) == false && returnToMenu == false)
		{
			System.out.print("Enter First Name:");
			firstName = scanner.nextLine(); if(c(firstName)) break;
			System.out.print("Enter Last Name:");
			lastName = scanner.nextLine(); if(c(lastName)) break;
		}
		
		if(returnToMenu == false && 
		   facade.book(flightId, seatId, firstName, lastName).equals("B"))
		{
			while(!input.equalsIgnoreCase("Y") && !input.equalsIgnoreCase("N"))
			{
				System.out.print("Would you like to book a limosine? (Y/N):");
				input = scanner.nextLine();
			}
			if(input.equalsIgnoreCase("Y"))
			{
				bookLimosine(flightId, seatId);	
			}
		}
		
		if(returnToMenu == false)
		{
			System.out.println(facade.book(flightId, seatId, firstName, lastName));
			System.out.print("Press enter to go back to the menu...\n\n");
			input = scanner.nextLine();
		}
	}
	
	private boolean bookLimosine(String flightId, String seatId)
	{
		return facade.bookLimosine(flightId, seatId);
	}
	
	private void addBooking()
	{
		String id = "Must be 2 letters followed by a number (excluding 0)";
		String rowNumber = "Must be a letter between A and I";
		String seatNumber = "Must be a number between 1 and 9";
		String economyOrBusiness = "Must be the letter \"E\" or \"B\"";
		String goBack = "To go back to the menu, hit the enter key\n\n";
		
		String addBookingMenu = "\n* Add booking *\n";
		
		
		printBookingMenu(addBookingMenu, goBack, id, rowNumber, seatNumber, economyOrBusiness);
		
		//Added the rowNumber.length() condition to the while loop because i had a bug where 
		//you could enter the whole seat ID in just the rowNumber and it would validate
		while(!facade.isValidId(id, rowNumber + seatNumber).equals("Validated ID") || rowNumber.length() > 1)
		{
			System.out.print("Enter ID:");
			id = scanner.nextLine(); if(c(id)) break;
			System.out.print("Enter Row Number:");
			rowNumber = scanner.nextLine(); if(c(rowNumber)) break;
			System.out.print("Enter Seat Number:");
			seatNumber = scanner.nextLine(); if(c(seatNumber)) break;
			if(rowNumber.length() > 1)
				System.out.println("Error: Row must be between A - I");
			else if(!facade.isValidId(id, rowNumber + seatNumber).equals("Validated ID"))
				System.out.println(facade.isValidId(id, rowNumber + seatNumber));
		}
		
		if(returnToMenu == false)
		economyOrBusiness = validateEconomyOrBusiness();
		
		if(returnToMenu == false)
		{
			switch(economyOrBusiness)
			{
			case "E":
				System.out.println(facade.addEconomySeat(id, rowNumber, seatNumber));
				break;
			case "B":
				System.out.println(facade.addBusinessSeat(id, rowNumber, seatNumber));
				break;
			default:
				System.out.println("Error: Unspecified booking error...");
			}
			System.out.print("Press enter to go back to the menu...\n");
			input = scanner.nextLine();
		}
		
	}
	
	private void printBookingMenu(String addBookingMenu, 
								  String goBack, 
								  String id, 
								  String rowNumber, 
								  String seatNumber, 
								  String economyOrBusiness)
	{
		System.out.println(addBookingMenu +
		 		   goBack +
		 		   String.format("%-26s %s\n", "ID:", id) +
		 		   String.format("%-26s %s\n", "Row Number:", rowNumber) +
		 		   String.format("%-26s %s\n", "Seat Number:", seatNumber) +
		 		   String.format("%-26s %s\n", "Economy or Business (E/B):", economyOrBusiness));
	}
	
	private String validateEconomyOrBusiness()
	{
		String[] EcoOrBusIndex = {"E", "B"};
		boolean EcoOrBusValidated = false;
		do
		{
			System.out.print("Enter Economy or Business (E/B):");
			input = scanner.nextLine();
			if(input.equalsIgnoreCase("")) {returnToMenu = true; break;}
			
			for(int i = 0; i < EcoOrBusIndex.length; i ++)
			{
				if(input.equalsIgnoreCase(EcoOrBusIndex[i]))
				{
					EcoOrBusValidated = true;
				}
			}
		}
		while(EcoOrBusValidated == false);
		return input.toUpperCase();
	}
	
	private boolean validateFirstOrLastName(String firstName, String lastName)
	{
		if(firstName.equalsIgnoreCase("Enter your first name") || 
		   lastName.equalsIgnoreCase("Enter your last name"))
		{
			return false;
		}
		char[] firstNameArray = new char[firstName.length()];
		char[] lastNameArray = 	new char[lastName.length()];
		firstNameArray = firstName.toCharArray();
		lastNameArray = lastName.toCharArray();

		for(int i = 0; i < firstNameArray.length; i++)
		{
			if(Character.isDigit(firstNameArray[i]))
			{
				System.out.println("Error: Name must be letters only");
				return false;
			}
		}
		for(int i = 0; i < lastNameArray.length; i++)
		{
			if(Character.isDigit(lastNameArray[i]))
			{
				System.out.println("Error: Name must be letters only");
				return false;
			}
		}
		
		return true;
	}
	private boolean validateCheckInBag(String lastName)
	{
		if(lastName.equalsIgnoreCase("Enter your first name") || 
		   lastName.equalsIgnoreCase("Enter your last name"))
		{
			return false;
		}
		char[] firstOrLastNameArray = new char[lastName.length()];
		firstOrLastNameArray = lastName.toCharArray();

		for(int i = 0; i < firstOrLastNameArray.length; i++)
		{
			if(Character.isDigit(firstOrLastNameArray[i]))
			{
				System.out.println("Error: Name must be letters only");
				return false;
			}
		}
		return true;
	}

	private boolean c(String checker)
	{
		if(returnToMenu)
			return true;
		else
			if(checker.equalsIgnoreCase(""))
				{
				returnToMenu = true;
				return true;
				}
			else return false;
	}
}
