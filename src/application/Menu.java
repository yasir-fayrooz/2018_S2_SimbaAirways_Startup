package application;

import java.io.Console;
import java.util.Scanner;

import airline.Facade;

/*
 * Class:			Menu
 * Description:		The class represents a menu that the user will use to
 * 					interact with the application class via a facade. 
 * Author:			[Yasir Fayrooz Ali] - [s3742162]
 */
public class Menu
{
	
	Facade facade = new Facade();
	
	public Menu()
	{
		
	}
	public void run()
	{
		printMenu();
		checkUserInput(userInput());
	}
	
	private void printMenu()
	{
		String addBooking = String.format("%-17s %s\n", "Add Booking", "AB");
		String bookSeat = String.format("%-17s %s\n", "Book Seat", "BS");
		String checkInBaggage = String.format("%-17s %s\n", "Checkin Baggage", "CB");
		String pickUpBaggage = String.format("%-17s %s\n", "Pick up Baggage", "PB");
		String displayBookingDetails = String.format("%-17s %s\n", "Display Booking Details", "DB");
		String displayAllBookings = String.format("%-17s %s\n", "Display ALL Bookings", "DA");
		String displayHistoricalBaggage = String.format("%-17s %s\n", "HB", "HB");
		String seedData = String.format("%-17s %s\n", "Seed Data", "SD");
		String exitProgram = String.format("%-17s %s\n", "Exit Program", "EX");

		System.out.println(addBooking +
						   bookSeat +
						   checkInBaggage +
						   pickUpBaggage +
						   displayBookingDetails +
						   displayAllBookings +
						   displayHistoricalBaggage +
						   seedData +
						   exitProgram);
		
		System.out.print("Enter selection:");
		
	}
	
	private String userInput()
	{
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		String[] options = new String[] {"AB", "BS", "CB", "PB", "DB",
										 "DA", "HB", "SD", "EX"};
		
		for (int i = 0; i < options.length; i++)
		{
			if(input.equalsIgnoreCase(options[i]))
			{
				input = options[i];
			}
		}
		scanner.close();
		return input;
		
	}
	
	private void checkUserInput(String userInput)
	{
		switch(userInput)
		{
		case "AB":
			addBooking();
			break;
		case "BS":
			bookSeat();
			break;
		case "CB":
			checkInBaggage();
			break;
		case "PB":
			pickUpBaggage();
			break;
		case "DB":
			displayBookingDetails();
			break;
		case "DA":
			displayAllBookings();
			break;
		case "HB":
			displayHistoricalBaggage();
			break;
		case "SD":
			seedData();
			break;
		case "EX":
			exitProgram();
			break;
			
			default:
				System.out.println("Error: Please enter a valid option from the list above.");
				break;
				
		}
	}
	private void exitProgram()
	{
		// TODO Auto-generated method stub
		
	}
	private void seedData()
	{
		// TODO Auto-generated method stub
		
	}
	private void displayHistoricalBaggage()
	{
		// TODO Auto-generated method stub
		
	}
	private void displayAllBookings()
	{
		// TODO Auto-generated method stub
		
	}
	private void displayBookingDetails()
	{
		// TODO Auto-generated method stub
		
	}
	private void pickUpBaggage()
	{
		// TODO Auto-generated method stub
		
	}
	private void checkInBaggage()
	{
		// TODO Auto-generated method stub
		
	}
	private void bookSeat()
	{
		// TODO Auto-generated method stub
		
	}
	private void addBooking()
	{
		// TODO Auto-generated method stub
		
	}
	
}
