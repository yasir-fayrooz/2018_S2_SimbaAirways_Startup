package airline;

import utilities.DateTime;

/*
 * Class:			Booking
 * Description:		The class represents a seat on a flight that can be booked 
 * Author:			[Yasir Fayrooz Ali] - [s3742162]
 */
public class Booking
{
	//Baggage information
	private String baggageId;
	private Baggage[] checkedBaggage;
	private Baggage[] previousBaggagesChecked = new Baggage[10];
	
	
	//Seat information
	private String rowNumber;
	private String seatNumber;
	private final String economyBooking = "E";
	private final String businessBooking = "B";
	private boolean isExitRowSeat = false;
	
	//Booking cost
	private double bookingFare;
	
	//Name of passenger
	private String firstName;
	private String lastName;	
	
	
	public Booking(String id, String rowNumber, String seatNumber, double fee) 
	{
		if(checkRowNumberSeatNumber(rowNumber, seatNumber) == false)
		{
			System.out.print("Error: Booking row # or seat # out of range.");
			return;
		}
		else
		{
			this.rowNumber = rowNumber;
			this.seatNumber = seatNumber;
			switch(seatNumber)
			{
			case "3":
				isExitRowSeat = true;
				break;
			case "4":
				isExitRowSeat = true;
				break;
			case "6":
				isExitRowSeat = true;
				break;
			case "7":
				isExitRowSeat = true;
				break;
			}
		}
	}
	
	private boolean checkRowNumberSeatNumber(String rowNumber, String seatNumber)
	{
		String[] seatsIndex = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
		String[] rowIndex = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
		boolean wrongInput = false;
		
		for(int i = 0; i < seatsIndex.length; i++)
		{
			if(!seatNumber.equalsIgnoreCase(seatsIndex[i]))
			{
				wrongInput = true;
			}
		}
		for(int i = 0; i < rowIndex.length; i++)
		{
			if(!rowNumber.equals(rowIndex[i]))
			{
				wrongInput = true;
			}
		}
		
		if(wrongInput)
			return false;
		else
			return true;
	}
	
	
	public String book(String firstName, String lastName)
	{
		if(this.firstName.equals(firstName) && 
		   this.lastName.equals(lastName))
		{
			return "Error: You have already been booked.";
		}
		else 
		{
			return toString();
		}
	}
	
	public String checkInBag(String lastName, double weight)
	{
		double totalWeight = weight;
		for(Baggage b : checkedBaggage)
		{
			totalWeight += b.getWeight();
		}
		if(checkedBaggage.length > 3)
		{
			return "Error: Number of bags checked must not exceed 3.";
		}
		else if(totalWeight > 20.00)
		{
			return "Error: Total weight of bags must not exceed 20kg";
		}
		else if(!lastName.equalsIgnoreCase(this.lastName))
		{
			return "Error: Name does not match name of passeneger of the booking.";
		}
		else
			return toString();
	}
	
	public String collectBags(DateTime dateCollected) 
	{
		for(Baggage b : checkedBaggage)
		{
			b.collect(dateCollected);
		}
	}
}


