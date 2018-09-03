package application;

import utilities.DateTime;

/*
 * Interface:		AirlineSystem
 * Description:		The interface defines the methods that must be implemented
 * 					by the facade. 
 * Author:			[student name] - [student number]
 * NOTE:			You should not modify this file in any way.
 */
public interface AirlineSystem
{
	public String addEconomySeat(String id, String rowNumber, String seatNumber);
	
	public String addBusinessSeat(String id, String rowNumber, String seatNumber);
	
	public String book(String flightId, String seatNumber, String firstName, String lastName);

	public String checkBaggage(String flightId, String seatNumber, String lastName, double weight);
	
	public boolean checkIfBookingExists(String flightId, String seatNumber);
	
	public String collectBaggage(String id, String seatNumber, String lastName, DateTime dateOfCollection);
	
	public String bookEconomy(String id, String firstName, String lastName, int age, boolean exitRow);

	public String bookBusiness(String id, String firstName, String lastName, int age, String platforms);

	public String displayAllBookings();
	
	public String displayBooking(String id, String seatId);
	
	public String displayHistoricalBaggage(String id, String seatid);
	
	public void seedData();
	
	public void writeData();
	
	public String isValidId(String id, String seatId);

}