package airline;

import application.AirlineSystem;
import application.SimbaAirways;
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
	SimbaAirways simbaAirways = new SimbaAirways();

	@Override
	public String addEconomySeat(String id, String rowNumber, String seatNumber)
	{
		return simbaAirways.addEconomySeat(id, rowNumber, seatNumber);
	}
	
	@Override
	public String addBusinessSeat(String id, String rowNumber, String seatNumber) 
	{
		return "";
	}
	
	@Override
	public String book(String flightId, String seatNumber, String firstName, String lastName)
	{
		return simbaAirways.book(flightId, seatNumber, firstName, lastName);
	}
	
	@Override
	public String checkBaggage(String flightId, String seatNumber,String lastName, double weight)
	{
		return simbaAirways.checkBaggage(flightId, seatNumber, lastName, weight);
	}
	
	@Override
	public String collectBaggage(String flightId, String seatNumber, String lastName, DateTime dateOfCollection)
	{
		return simbaAirways.collectBaggage(flightId, seatNumber, lastName, dateOfCollection);
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
		return simbaAirways.displayAllBookings();		
	}

	@Override
	public String bookBusiness(String id, String firstName, String lastName, int age, String platforms)
	{
		return "";
	}

	@Override
	public void seedData()
	{
		simbaAirways.seedData();
	}

	@Override
	public void writeData()
	{
		
	}

	@Override
	public boolean checkIfBookingExists(String flightId, String seatNumber)
	{
		return simbaAirways.checkIfBookingExists(flightId, seatNumber);
	}

	@Override
	public String displayBooking(String id, String seatId)
	{
		return simbaAirways.displayBooking(id, seatId);
	}

	@Override
	public String displayHistoricalBaggage(String id, String seatId)
	{
		return simbaAirways.displayHistoricalBaggage(id, seatId);
	}

	@Override
	public String isValidId(String id, String seatId)
	{
		return simbaAirways.isValidId(id, seatId);
	}
}
