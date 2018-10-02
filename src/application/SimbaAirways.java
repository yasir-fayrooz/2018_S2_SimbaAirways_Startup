package application;

import java.io.*;
import java.util.Scanner;

import airline.Baggage;
import airline.Booking;
import airline.Business;
import airline.Economy;
import utilities.DateTime;
import utilities.InvalidDate;
import utilities.InvalidId;
import utilities.Wrapper;

/*
 * Class:			AirlineSystem
 * Description:		The airline system manager the manages the 
 *              	collection of data. 
 * Author:			[Yasir Fayrooz Ali] - [s3742162]
 */
public class SimbaAirways
{
	private String EconomyOrBusiness = null;
	Wrapper Wrapper = new Wrapper();
	
	public SimbaAirways()
	{
		loadData("data.txt");
	}
	
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
	
	public String addEconomySeat(String id, String rowNumber, String seatNumber)
	{
		if(checkIfBookingExists(id, rowNumber + seatNumber) == true)
		{
			return "Error: Booking has already been added.";
		}
		try{Wrapper.addBooking(new Economy(("E" + id.toUpperCase()), rowNumber.toUpperCase(), seatNumber, 1200.00));}
		catch (InvalidId e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return "New Economy booking added successfully for booking id: " + id.toUpperCase();
	}
	public String addBusinessSeat(String id, String rowNumber, String seatNumber)
	{
		if(checkIfBookingExists(id, rowNumber + seatNumber) == true)
		{
			return "Error: Booking has already been added.";
		}
		try{Wrapper.addBooking(new Business(("B" + id.toUpperCase()), rowNumber.toUpperCase(), seatNumber, 2400.00));}
		catch (InvalidId e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return "New Business booking added successfully for booking id: " + id.toUpperCase();
		
	}
	
	public boolean bookLimosine(String flightId, String seatId)
	{
		for(Booking b : Wrapper.getBookings())
		{
			if(b != null)
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
		}
		System.out.println("Error: Unspecified limosine error occurred.");
		return false;
	}
	
	public String book(String flightId, String seatNumber, String firstName, String lastName)
	{
		if(flightId.equals("returnToMenu"))
		{
			EconomyOrBusiness = null;
			return "";
		}
		for(Booking b : Wrapper.getBookings())
		{
			if(b != null)
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
		}
		return "Error - The booking could not be completed";
	}
	
	
	public boolean checkIfBookingExists(String flightId, String seatNumber)
	{
		for(int i = 0; i < Wrapper.getBookings().length; i++)
		{
			if(Wrapper.getBookings()[i] != null)
			{
				String seatNo = toStringIndex(Wrapper.getBookings()[i].toString(), 1, 2) + 
						toStringIndex(Wrapper.getBookings()[i].toString(), 2, 3);
				String flightNo = toStringIndex(Wrapper.getBookings()[i].toString(), 0, 1).substring(1, 4);
				
				if(flightNo.equalsIgnoreCase(flightId) &&
				   seatNo.equalsIgnoreCase(seatNumber))
				{
					return true;
				}
				
			}
		}
		return false;
	}
	
	public String checkBaggage(String flightId, String seatNumber, String lastName, double weight)
	{
		for(int i = 0; i < Wrapper.getBookings().length; i++)
		{
			if(Wrapper.getBookings()[i] != null)
			{
				String seatNo = toStringIndex(Wrapper.getBookings()[i].toString(), 1, 2) + 
						toStringIndex(Wrapper.getBookings()[i].toString(), 2, 3);
				String flightNo = toStringIndex(Wrapper.getBookings()[i].toString(), 0, 1).substring(1, 4);
				String lName = toStringIndex(Wrapper.getBookings()[i].toString(), 5, 6);
				
				if(flightNo.equalsIgnoreCase(flightId) &&
				   seatNo.equalsIgnoreCase(seatNumber) &&
				   lName.equalsIgnoreCase(lastName) &&
				   weight == 0)
				{
					return "Enter weight:";
				}
				else if(flightNo.equalsIgnoreCase(flightId) &&
						seatNo.equalsIgnoreCase(seatNumber) &&
						lName.equalsIgnoreCase(lastName) &&
					    weight != 0)
				{
					System.out.println(Wrapper.getBookings()[i].checkInBag(lName, weight));
				}	
			}
		}
		return "Error: Flight, seat or last name ID is incorrect.\n";
	}
	
	public String collectBaggage(String flightId, String seatNumber, String lastName, DateTime dateOfCollection)
	{
		for(int i = 0; i < Wrapper.getBookings().length; i++)
		{
			if(Wrapper.getBookings()[i] != null)
			{
				String seatNo = toStringIndex(Wrapper.getBookings()[i].toString(), 1, 2) + 
						toStringIndex(Wrapper.getBookings()[i].toString(), 2, 3);
				String flightNo = toStringIndex(Wrapper.getBookings()[i].toString(), 0, 1).substring(1, 4);
				String lName = toStringIndex(Wrapper.getBookings()[i].toString(), 5, 6);
				
				
				if(flightNo.equalsIgnoreCase(flightId) &&
				   seatNo.equalsIgnoreCase(seatNumber) &&
				   lName.equalsIgnoreCase(lastName))
				{
					try {
						return Wrapper.getBookings()[i].collectBags(dateOfCollection);
					} catch (InvalidDate e){
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
				}
			}
		}
		return "The booking was not found.";
	}
	
	public String displayBooking(String flightId, String seatId)
	{
		for(int i = 0; i < Wrapper.getBookings().length; i++)
		{
			if(Wrapper.getBookings()[i] != null)
			{
				String seatNo = toStringIndex(Wrapper.getBookings()[i].toString(), 1, 2) + 
				        toStringIndex(Wrapper.getBookings()[i].toString(), 2, 3);
				String flightNo = toStringIndex(Wrapper.getBookings()[i].toString(), 0, 1).substring(1, 4);
		
				if(flightNo.equalsIgnoreCase(flightId) &&
				   seatNo.equalsIgnoreCase(seatId))
				{
					return Wrapper.getBookings()[i].getDetails();
				}
			}
		}
		return "Error: Booking does not exist.";
	}
	
	public String displayAllBookings()
	{
		String details = "";
		for(int i = 0; i < Wrapper.getBookings().length; i++)
		{
			if(Wrapper.getBookings()[i] != null)
			{
				details += Wrapper.getBookings()[i].getDetails();
				details += "--------------------------------\n";
			}
		}
		if(details.equals(""))
			return "Error: No bookings exist.";
		else
			return details;
	}
	
	public String displayHistoricalBaggage(String flightId, String seatId)
	{
		for(int i = 0; i < Wrapper.getBookings().length; i++)
		{
			if(Wrapper.getBookings()[i] != null)
			{
				String seatNo = toStringIndex(Wrapper.getBookings()[i].toString(), 1, 2) + 
				        toStringIndex(Wrapper.getBookings()[i].toString(), 2, 3);
				String flightNo = toStringIndex(Wrapper.getBookings()[i].toString(), 0, 1).substring(1, 4);
				
				if(flightNo.equalsIgnoreCase(flightId) &&
				   seatNo.equalsIgnoreCase(seatId))
				{
					return Wrapper.getBookings()[i].getBaggageHistory();
				}
			}
		}
		return "Error: Booking does not exist.";
	}
	
	public void seedData()
	{
		if(Wrapper.getBookings().length > 0)
		{
			System.out.println("Error: Bookings already have data...");
		}
		else
		{
			//First seed
			try{Wrapper.addBooking(new Economy("ESE1", "A", "1", 1200.00));} catch (InvalidId e) {}
			
			//Second seed
			try{Wrapper.addBooking(new Economy("ESE2", "A", "1", 1200.00));} catch (InvalidId e) {}
			Wrapper.getBookings()[0].book("Seed", "Data");
			
			//Third seed
			try{Wrapper.addBooking(new Economy("ESE3", "A", "1", 1200.00));} catch (InvalidId e) {}
			Wrapper.getBookings()[0].book("Seed", "Data");
			Wrapper.getBookings()[0].checkInBag("Data", 10);
			Wrapper.getBookings()[0].checkInBag("Data", 5);
			
			//Fourth seed
			try{Wrapper.addBooking(new Economy("ESE4", "A", "1", 1200.00));} catch (InvalidId e) {}
			Wrapper.getBookings()[0].book("Seed", "Data");
			Wrapper.getBookings()[0].checkInBag("Data", 10);
			Wrapper.getBookings()[0].checkInBag("Data", 5);
			Wrapper.getBookings()[0].checkInBag("Data", 7);
			
			//Fifth seed
			try{Wrapper.addBooking(new Economy("ESE5", "A", "1", 1200.00));} catch (InvalidId e) {}
			Wrapper.getBookings()[0].book("Seed", "Data");
			Wrapper.getBookings()[0].checkInBag("Data", 10);
			Wrapper.getBookings()[0].checkInBag("Data", 5);
			Wrapper.getBookings()[0].checkInBag("Data", 5);
			try {
				Wrapper.getBookings()[0].collectBags(new DateTime(1));
			} catch (InvalidDate e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			//Business seeds:
			
			//Sixth seed
			try{Wrapper.addBooking(new Business("BSB1", "A", "1", 2400.00));} catch (InvalidId e) {}
			
			//Seventh seed
			try{Wrapper.addBooking(new Business("BSB2", "A", "1", 2400.00));} catch (InvalidId e) {}
			Wrapper.getBookings()[0].book("Seed", "Data");
			
			//Eighth seed
			try{Wrapper.addBooking(new Business("BSB3", "A", "1", 2400.00));} catch (InvalidId e) {}
			Wrapper.getBookings()[0].book("Seed", "Data");
			Wrapper.getBookings()[0].checkInBag("Data", 10);
			Wrapper.getBookings()[0].checkInBag("Data", 5);
			
			//Ninth seed
			try{Wrapper.addBooking(new Business("BSB4", "A", "1", 2400.00));} catch (InvalidId e) {}
			Wrapper.getBookings()[0].book("Seed", "Data");
			Wrapper.getBookings()[0].checkInBag("Data", 10);
			Wrapper.getBookings()[0].checkInBag("Data", 5);
			Wrapper.getBookings()[0].checkInBag("Data", 7);
			
			//Tenth seed
			try{Wrapper.addBooking(new Business("BSB5", "A", "1", 2400.00));} catch (InvalidId e) {}
			Wrapper.getBookings()[0].book("Seed", "Data");
			Wrapper.getBookings()[0].checkInBag("Data", 10);
			Wrapper.getBookings()[0].checkInBag("Data", 5);
			Wrapper.getBookings()[0].checkInBag("Data", 5);
			try {
				Wrapper.getBookings()[0].collectBags(new DateTime(1));
			} catch (InvalidDate e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			//Eleventh seed
			try{Wrapper.addBooking(new Business("BSB6", "A", "1", 2400.00));} catch (InvalidId e) {}
			Wrapper.getBookings()[0].book("Seed", "Data");
			((Business)Wrapper.getBookings()[0]).setLimosinePickUp(true);
			
			//Twelfth seed
			try{Wrapper.addBooking(new Business("BSB7", "A", "1", 2400.00));} catch (InvalidId e) {}
			Wrapper.getBookings()[0].book("Seed", "Data");
			((Business)Wrapper.getBookings()[0]).setLimosinePickUp(false);
			
			System.out.println("\nSeed data added successfully.");
		}
	}
	
	public void writeData()
	{
		try
		{
			PrintWriter out = new PrintWriter("data.txt");
			for(Booking b : Wrapper.getBookings())
			{
				if(b != null)
				{
					out.println(b.toString());
					out.flush();	
				}
			}
			out.println("Previous Baggage History:");
			for(Booking b : Wrapper.getBookings())
			{
				if(b != null)
				{
					for(int i = 0; i < b.getPreviousBaggagesChecked().length; i++)
					{
						try 
						{
							out.println(b.getPreviousBaggagesChecked()[i].toString());
							out.flush();
						} catch (Exception e) {}
					}	
				}
			}
			out.close();
			writeBackUpData(new File("data.txt"));
		}catch (FileNotFoundException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	public void writeBackUpData(File data)
	{
		File b_data = new File("Backup\\b_data.txt");
		String line;
		BufferedReader reader;
		PrintWriter writer;
		
		try
		{
			if(b_data.createNewFile() || !b_data.createNewFile())
			{
				reader = new BufferedReader(new FileReader(data));
				writer = new PrintWriter(new FileWriter(b_data));
				
				while((line = reader.readLine()) != null)
				{
					writer.println(line);
				}
				
				reader.close();
				writer.close();
			}
		}
		catch (IOException e)
		{
			System.out.println("Error: Could not create/update backup file.");
		}
	}
	
	public void loadData(String data)
	{
		Scanner inputStream = null;	
		try
		{
			inputStream = new Scanner(new File(data));
			while(inputStream.hasNextLine())
			{
				String toStringData = inputStream.nextLine();
				if(toStringData.equals("Previous Baggage History:") ||
				   toStringData.equals("")) {break;}
				loadAddBooking(toStringData);
				loadBookSeat(toStringData);
				loadLimo(toStringData);
				loadCheckedBaggage(toStringData);
			}
			while(inputStream.hasNextLine())
			{
				String baggageHistory = inputStream.nextLine();
				loadPreviousBaggage(baggageHistory);
			}
			inputStream.close();
		} catch(FileNotFoundException e) {
			loadFromBackUpFile();
		}
	}
	
	private void loadFromBackUpFile()
	{
		Scanner inputStream = null;
		try
		{
			inputStream = new Scanner(new File("Backup/b_data.txt"));
			while(inputStream.hasNextLine())
			{
				String toStringData = inputStream.nextLine();
				if(toStringData.equals("Previous Baggage History:") ||
				   toStringData.equals("")) {break;}
				loadAddBooking(toStringData);
				loadBookSeat(toStringData);
				loadLimo(toStringData);
				loadCheckedBaggage(toStringData);
			}
			while(inputStream.hasNextLine())
			{
				String baggageHistory = inputStream.nextLine();
				loadPreviousBaggage(baggageHistory);
			}
			System.out.println("Data loaded from back up file.");
			inputStream.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("No Booking data was loaded.");
		}
	}
	
	private void loadAddBooking(String toStringData)
	{
		String EorB = toStringData.substring(0, 1);
		if(EorB.equals("E"))
		{
			try{Wrapper.addBooking(new Economy(toStringIndex(toStringData, 0, 1), 
				      toStringIndex(toStringData, 1, 2), 
				      toStringIndex(toStringData, 2, 3), 
				      Double.valueOf(toStringIndex(toStringData, 3, 4))));}
			catch (InvalidId e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			}
		} else if(EorB.equals("B"))
		{
			try{Wrapper.addBooking(new Business(toStringIndex(toStringData, 0, 1), 
				      toStringIndex(toStringData, 1, 2), 
				      toStringIndex(toStringData, 2, 3), 
				      Double.valueOf(toStringIndex(toStringData, 3, 4))));}
			catch (InvalidId e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			}
		}
	}
	
	private void loadBookSeat(String toStringData)
	{
		String firstName = "";
		String lastName = "";
		try
		{
			if(!toStringIndex(toStringData, 4, 5).equals("null") &&
			   !toStringIndex(toStringData, 5, 6).equals("null"))
			{
				firstName = toStringIndex(toStringData, 4, 5);
				lastName = toStringIndex(toStringData, 5, 6);
				Wrapper.getBookings()[0].book(firstName, lastName);
			}
		} catch (Exception e) {
			
		}
	}
	
	private void loadLimo(String toStringData)
	{
		String EorB = toStringData.substring(0, 1);
		if(EorB.equals("B"))
		{
			if(toStringIndex(toStringData, 6, 7).equals("YES"))
				((Business)Wrapper.getBookings()[0]).setLimosinePickUp(true);
			else
				((Business)Wrapper.getBookings()[0]).setLimosinePickUp(false);
		}
	}
	
	private void loadCheckedBaggage(String toStringData)
	{
		int indexer = 0;
		for(int i = 0; i < 3; i++)
		{
			try
			{
				double weight = Double.valueOf(toStringIndex(toStringData, (8 + indexer), (9 + indexer)));
				int day = Integer.valueOf(toStringIndex(toStringData, (9 + indexer), (10 + indexer)).substring(0, 2));
				int month = Integer.valueOf(toStringIndex(toStringData, (9 + indexer), (10 + indexer)).substring(2, 4));
				int year = Integer.valueOf(toStringIndex(toStringData, (9 + indexer), (10 + indexer)).substring(4, 8));
				DateTime checkInDate = new DateTime(day, month, year);
				if(toStringIndex(toStringData, (10 + indexer), (11 + indexer)).equals("NO"))
				{
					Wrapper.getBookings()[0].loadCheckedBaggage(weight, checkInDate);
				}
				else 
				{
					int c_day = Integer.valueOf(toStringIndex(toStringData, (10 + indexer), (11 + indexer)).substring(0, 2));
					int c_month = Integer.valueOf(toStringIndex(toStringData, (10 + indexer), (11 + indexer)).substring(2, 4));
					int c_year = Integer.valueOf(toStringIndex(toStringData, (10 + indexer), (11 + indexer)).substring(4, 8));
					DateTime collectedDate = new DateTime(c_day, c_month, c_year);
					Wrapper.getBookings()[0].loadCheckedBaggage(weight, checkInDate);
					Wrapper.getBookings()[0].collectBags(collectedDate);
				}
				indexer += 4;
			} catch(Exception e) {break;}
		}
	}
	
	private void loadPreviousBaggage(String baggageHistory)
	{
		for(Booking b : Wrapper.getBookings())
		{
			String flightId = toStringIndex(baggageHistory, 0, 1).substring(0, 4);
			String passengerId = toStringIndex(baggageHistory, 0, 1).substring((toStringIndex(baggageHistory, 0, 1).indexOf("_") + 1),
																			   toStringIndex(baggageHistory, 0, 1).lastIndexOf("_"));
			Double weight = Double.valueOf(toStringIndex(baggageHistory, 1, 2));
			
			int day = Integer.valueOf(toStringIndex(baggageHistory, 2, 3).substring(0, 2));
			int month = Integer.valueOf(toStringIndex(baggageHistory, 2, 3).substring(2, 4));
			int year = Integer.valueOf(toStringIndex(baggageHistory, 2, 3).substring(4, 8));
			DateTime checkInDate = new DateTime(day, month, year);
			
			int c_day = Integer.valueOf(toStringIndex(baggageHistory, 3, 4).substring(0, 2));
			int c_month = Integer.valueOf(toStringIndex(baggageHistory, 3, 4).substring(2, 4));
			int c_year = Integer.valueOf(toStringIndex(baggageHistory, 3, 4).substring(4, 8));
			DateTime collectedDate = new DateTime(c_day, c_month, c_year);
			
			if(b != null &&
			   toStringIndex(b.toString(), 0, 1).equals(flightId))
			{
				b.updatePreviousBaggage(new Baggage(flightId, passengerId, weight, checkInDate), collectedDate);
			}
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
