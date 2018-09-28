package application;

import airline.Booking;
import airline.Business;
import airline.Economy;
import utilities.DateTime;

/*
 * Class:			AirlineSystem
 * Description:		The airline system manager the manages the 
 *              	collection of data. 
 * Author:			[Yasir Fayrooz Ali] - [s3742162]
 */
public class SimbaAirways
{
	Booking[] bookings = new Booking[0];
	private String EconomyOrBusiness = null;
	
	
	private String toStringIndex(String toString, int startingColonIndex, int endingColonIndex)
	{
		int startingIndex = 0;
		int endingIndex = 0;
		for(int i = 0; i < startingColonIndex; i++)
		{
			startingIndex = toString.indexOf(":", startingIndex + 1);
		}
		startingIndex++;
		for(int i = 0; i < endingColonIndex; i++)
		{
			endingIndex = toString.indexOf(":", endingIndex + 1);
		}
		if(startingColonIndex == 0)
			return toString.substring(startingColonIndex, endingIndex);
		else
			return toString.substring(startingIndex, endingIndex);
	}
	private void updateBookingsArray()
	{
		Booking[] bookings2 = new Booking[(bookings.length + 1)];
		for(int i = bookings.length; i > 0; i--)
		{
			bookings2[i] = bookings[i - 1];
		}
		bookings = bookings2;
	}
	public String addEconomySeat(String id, String rowNumber, String seatNumber)
	{
		if(checkIfBookingExists(id, rowNumber + seatNumber) == true)
		{
			return "Error: Booking has already been added.";
		}
		updateBookingsArray();
		bookings[0] = new Economy("E" + id.toUpperCase(), rowNumber.toUpperCase(), seatNumber, 1200.00);
		return "New Economy booking added successfully for booking id: " + id.toUpperCase();
	}
	public String addBusinessSeat(String id, String rowNumber, String seatNumber)
	{
		if(checkIfBookingExists(id, rowNumber + seatNumber) == true)
		{
			return "Error: Booking has already been added.";
		}
		updateBookingsArray();
		bookings[0] = new Business("B" + id.toUpperCase(), rowNumber.toUpperCase(), seatNumber, 2400.00);
		return "New Business booking added successfully for booking id: " + id.toUpperCase();
		
	}
	
	public boolean bookLimosine(String flightId, String seatId)
	{
		for(Booking b : bookings)
		{
			if(b instanceof Business)
			{
				String seatNo = toStringIndex(b.toString(), 1, 2) + 
						toStringIndex(b.toString(), 2, 3);
				String flightNo = toStringIndex(b.toString(), 0, 1).substring(1, 4);
				
				if(flightNo.equalsIgnoreCase(flightId) &&
				   seatNo.equalsIgnoreCase(seatId))
				{
					((Business) b).setLimosinePickUp(true);
					return true;
				}
			}
		}
		System.out.println("Error: Unspecified limosine error occurred.");
		return false;
	}
	
	public String book(String flightId, String seatNumber, String firstName, String lastName)
	{
		for(Booking b : bookings)
		{
			String seatNo = toStringIndex(b.toString(), 1, 2) + toStringIndex(b.toString(), 2, 3);
			String id = toStringIndex(b.toString(), 0, 1).substring(1, 4);
			String EorB = toStringIndex(b.toString(), 0, 1).substring(0, 1);
			
			if(seatNo.equalsIgnoreCase(seatNumber) &&
			   id.equalsIgnoreCase(flightId))
			{
				if(EorB.equalsIgnoreCase("B") && EconomyOrBusiness == null)
				{
					EconomyOrBusiness = "B";
					return "B";
				} 
				else if(EorB.equalsIgnoreCase("E") && EconomyOrBusiness == null)
				{
					EconomyOrBusiness = "E";
					return "E";
				}
				else 
				{
					if(b.book(firstName, lastName).equalsIgnoreCase(b.toString()))
					{
						EconomyOrBusiness = null;
						return "The flight: " + 
								toStringIndex(b.toString(), 0, 1) + " - " + 
								seatNumber.toUpperCase() + " has been successfully booked.";
					}
					else
					{
						EconomyOrBusiness = null;
						return b.book(firstName, lastName);
					}
					
				}
			}
		}
		return "Error - The booking could not be completed";
	}
	
	
	public boolean checkIfBookingExists(String flightId, String seatNumber)
	{
		for(int i = 0; i < bookings.length; i++)
		{
			String seatNo = toStringIndex(bookings[i].toString(), 1, 2) + 
					toStringIndex(bookings[i].toString(), 2, 3);
			String flightNo = toStringIndex(bookings[i].toString(), 0, 1).substring(1, 4);
			
			if(flightNo.equalsIgnoreCase(flightId) &&
			   seatNo.equalsIgnoreCase(seatNumber))
			{
				return true;
			}
		}
		return false;
	}
	
	public String checkBaggage(String flightId, String seatNumber, String lastName, double weight)
	{
		for(int i = 0; i < bookings.length; i++)
		{
			String seatNo = toStringIndex(bookings[i].toString(), 1, 2) + 
					toStringIndex(bookings[i].toString(), 2, 3);
			String flightNo = toStringIndex(bookings[i].toString(), 0, 1).substring(1, 4);
			String lName = toStringIndex(bookings[i].toString(), 5, 6);
			
			if(flightNo.equalsIgnoreCase(flightId) &&
			   seatNo.equalsIgnoreCase(seatNumber) &&
			   lName.equalsIgnoreCase(lastName) &&
			   weight == 0)
			{
				return "Enter weight:";
			}
			else if(weight != 0)
			{
				System.out.println(bookings[i].checkInBag(lName, weight));
			}
		}
		return "Error: Flight, seat or last name ID is incorrect.\n";
	}
	
	public String collectBaggage(String flightId, String seatNumber, String lastName, DateTime dateOfCollection)
	{
		for(int i = 0; i < bookings.length; i++)
		{
			String seatNo = toStringIndex(bookings[i].toString(), 1, 2) + 
					toStringIndex(bookings[i].toString(), 2, 3);
			String flightNo = toStringIndex(bookings[i].toString(), 0, 1).substring(1, 4);
			String lName = toStringIndex(bookings[i].toString(), 5, 6);
			
			
			if(flightNo.equalsIgnoreCase(flightId) &&
			   seatNo.equalsIgnoreCase(seatNumber) &&
			   lName.equalsIgnoreCase(lastName))
			{
				return bookings[i].collectBags(dateOfCollection);
			}
		}
		return "The booking was not found.";
	}
	
	public String displayBooking(String flightId, String seatId)
	{
		for(int i = 0; i < bookings.length; i++)
		{
			String seatNo = toStringIndex(bookings[i].toString(), 1, 2) + 
					        toStringIndex(bookings[i].toString(), 2, 3);
			String flightNo = toStringIndex(bookings[i].toString(), 0, 1).substring(1, 4);
			
			if(flightNo.equalsIgnoreCase(flightId) &&
			   seatNo.equalsIgnoreCase(seatId))
			{
				return bookings[i].getDetails();
			}
		}
		return "Error: Booking does not exist.";
	}
	
	public String displayAllBookings()
	{
		String details = "";
		for(int i = 0; i < bookings.length; i++)
		{
			details += bookings[i].getDetails();
			details += "--------------------------------\n";
		}
		if(details.equals(""))
			return "Error: No bookings exist.";
		else
			return details;
	}
	
	public String displayHistoricalBaggage(String flightId, String seatId)
	{
		for(int i = 0; i < bookings.length; i++)
		{
			String seatNo = toStringIndex(bookings[i].toString(), 1, 2) + 
			        toStringIndex(bookings[i].toString(), 2, 3);
			String flightNo = toStringIndex(bookings[i].toString(), 0, 1).substring(1, 4);
			
			if(flightNo.equalsIgnoreCase(flightId) &&
			   seatNo.equalsIgnoreCase(seatId))
			{
				return bookings[i].getBaggageHistory();
			}
		}
		return "Error: Booking does not exist.";
	}
	
	public void seedData()
	{
		if(bookings.length > 0)
		{
			System.out.println("Error: Bookings already have data...");
		}
		else
		{
			bookings = new Booking[12];
			//First seed
			bookings[0] = new Economy("ESE1", "A", "1", 1200.00);
			
			//Second seed
			bookings[1] = new Economy("ESE2", "A", "1", 1200.00);
			bookings[1].book("Seed", "Data");
			
			//Third seed
			bookings[2] = new Economy("ESE3", "A", "1", 1200.00);
			bookings[2].book("Seed", "Data");
			bookings[2].checkInBag("Data", 10);
			bookings[2].checkInBag("Data", 5);
			
			//Fourth seed
			bookings[3] = new Economy("ESE4", "A", "1", 1200.00);
			bookings[3].book("Seed", "Data");
			bookings[3].checkInBag("Data", 10);
			bookings[3].checkInBag("Data", 5);
			bookings[3].checkInBag("Data", 7);
			
			//Fifth seed
			bookings[4] = new Economy("ESE5", "A", "1", 1200.00);
			bookings[4].book("Seed", "Data");
			bookings[4].checkInBag("Data", 10);
			bookings[4].checkInBag("Data", 5);
			bookings[4].checkInBag("Data", 5);
			bookings[4].collectBags(new DateTime(1));
			
			//Business seeds:
			
			//Sixth seed
			bookings[5] = new Business("BSB1", "A", "1", 2400.00);
			
			//Seventh seed
			bookings[6] = new Business("BSB2", "A", "1", 2400.00);
			bookings[6].book("Seed", "Data");
			
			//Eighth seed
			bookings[7] = new Business("BSB3", "A", "1", 2400.00);
			bookings[7].book("Seed", "Data");
			bookings[7].checkInBag("Data", 10);
			bookings[7].checkInBag("Data", 5);
			
			//Ninth seed
			bookings[8] = new Business("BSB4", "A", "1", 2400.00);
			bookings[8].book("Seed", "Data");
			bookings[8].checkInBag("Data", 10);
			bookings[8].checkInBag("Data", 5);
			bookings[8].checkInBag("Data", 7);
			
			//Tenth seed
			bookings[9] = new Business("BSB5", "A", "1", 2400.00);
			bookings[9].book("Seed", "Data");
			bookings[9].checkInBag("Data", 10);
			bookings[9].checkInBag("Data", 5);
			bookings[9].checkInBag("Data", 5);
			bookings[9].collectBags(new DateTime(1));
			
			//Eleventh seed
			bookings[10] = new Business("BSB6", "A", "1", 2400.00);
			bookings[10].book("Seed", "Data");
			((Business)bookings[10]).setLimosinePickUp(true);
			
			//Twelfth seed
			bookings[11] = new Business("BSB7", "A", "1", 2400.00);
			bookings[11].book("Seed", "Data");
			((Business)bookings[11]).setLimosinePickUp(false);
			
			System.out.println("\nSeed data added successfully.");
		}
	}
	
	public String isValidId(String id, String seatId)
	{
		boolean validId = false;
		boolean validSeatId = false;
		
		// VALIDATE ID
		if(id.length() == 3)
		{
			char[] inputArray = new char[3];
			
			inputArray = id.substring(0, 3).toCharArray();
			
			if(Character.isLetter(inputArray[0]) &&
			   Character.isLetter(inputArray[1]) &&
			   Character.isDigit(inputArray[2]) &&
			   !id.substring(2, 3).equals("0"))
				validId = true;
			else
				return "Error: Id must be 2 letters followed by a number";
			
		}
		else
		{
			return "Error: Id must be 3 characters";
		}
		
		// VALIDATE SEATID
		if(seatId.length() == 2)
		{
			final String[] seatsIndex = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
			final String[] rowIndex = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
			boolean seatValidated = false; boolean rowValidated = false;
			
			for(int i = 0; i < seatsIndex.length; i++)
			{
				if(seatId.substring(1, 2).equalsIgnoreCase(seatsIndex[i]))
					seatValidated = true;
				if(seatId.substring(0, 1).equalsIgnoreCase(rowIndex[i]))
					rowValidated = true;
			}
			if(seatValidated && rowValidated)
			{
				validSeatId = true;
			}
		}
		else
		{
			return "Error: Row must be between A - I and Seat must be between 1 - 9";
		}
		if(validId && validSeatId)
		{
			return "Validated ID";
		}
		else
			return "Error: Already has been booked or does not exist in system";
		}
}
