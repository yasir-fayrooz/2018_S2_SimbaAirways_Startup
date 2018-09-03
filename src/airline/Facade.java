package airline;

import application.AirlineSystem;
import utilities.DateTime;

/*
 * Class:			Facade
 * Description:		The class is used to separate the menu from the actual system. 
 * Author:			[student name] - [student number]
 * NOTE: 			You will need to modify the method bodies in this class
 * 					to forward calls to the application class.
 * 					You will need to make sure the return statements are also
 * 					updated to return the correct data back to the menu.
 */
public class Facade implements AirlineSystem
{

	@Override
	public String addEconomySeat(String id, String rowNumber, String seatNumber)
	{
		return "";
	}
	
	@Override
	public String addBusinessSeat(String id, String rowNumber, String seatNumber) 
	{
		return "";
	}
	
	@Override
	public String book(String flightId, String seatNumber, String firstName, String lastName)
	{
		return "";
	}
	
	@Override
	public String checkBaggage(String flightId, String seatNumber,String lastName, double weight)
	{
		return "";
	}
	
	@Override
	public String collectBaggage(String flightId, String seatNumber, String lastName, DateTime dateOfCollection)
	{
		return "";
	}
	
	public boolean getItemById(String flightId, String seatNumber)
	{
		return false;
	}

	@Override
	public String bookEconomy(String id, String firstName, String lastName, int age, boolean exitRow)
	{
		return "";
	}

	@Override
	public String displayAllBookings()
	{
		return "";		
	}

	@Override
	public String bookBusiness(String id, String firstName, String lastName, int age, String platforms)
	{
		return "";
	}

	@Override
	public void seedData()
	{
		
	}

	@Override
	public void writeData()
	{
		
	}

	@Override
	public boolean checkIfBookingExists(String flightId, String seatNumber)
	{
		return false;
	}

	@Override
	public String displayBooking(String id, String seatId)
	{
		return "";
	}

	@Override
	public String displayHistoricalBaggage(String id, String seatId)
	{
		return "";
	}

	@Override
	public String isValidId(String id, String seatId)
	{
		return "";
	}
}
