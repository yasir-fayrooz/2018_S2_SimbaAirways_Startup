package application;

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
	
	public void run()
	{
		printMenu();
	}
	
	private void printMenu()
	{
		String addBooking = String.format("%-17s %s\n", "Add Booking:", "AB");
	}
	
}
