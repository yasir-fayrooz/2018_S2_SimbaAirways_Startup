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
	private Baggage[] checkedBaggage = new Baggage[3];
	private Baggage[] previousBaggagesChecked = new Baggage[10];
	
	
	//Seat information
	private String rowNumber;
	private String seatNumber;
	private final String[] bookingClassPrefix = {"E","B"};
	
	//Booking cost
	private final double standardFare = 1200;
	
	//Name of passenger
	private String firstName;
	private String lastName;	
	
	
	public Booking(String id, String rowNumber, String seatNumber, double fee) 
	{
		baggageId = bookingClassPrefix[0] + id;
		checkRowNumberSeatNumber(rowNumber, seatNumber);
	}
	
	private boolean checkRowNumberSeatNumber(String rowNumber, String seatNumber)
	{
		String[] seatsIndex = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
		String[] rowIndex = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
		boolean invalidSeat = false;
		boolean invalidRow = false;
		
		for(int i = 0; i < seatsIndex.length; i++)
		{
			if(seatNumber.equalsIgnoreCase(seatsIndex[i]))
			{
				invalidSeat = false;
				break;
			}
			else
				invalidSeat = true;
		}
		for(int i = 0; i < rowIndex.length; i++)
		{
			if(rowNumber.equals(rowIndex[i]))
			{
				invalidRow = false;
				break;
			}
			else
				invalidRow = true;
		}
		
		if(invalidSeat || invalidRow)
		{
			System.out.print("Error: Booking row # or seat # out of range.\n");
			return false;
		}
		else
		{
			this.rowNumber = rowNumber;
			this.seatNumber = seatNumber;
			return true;
		}
	}
	
	
	public String book(String firstName, String lastName)
	{
		if(this.firstName == null &&
		   this.lastName == null)
		{
			this.firstName = firstName;
			this.lastName = lastName;
			return toString();
		}
		else if(this.firstName.equals(firstName) && 
		   this.lastName.equals(lastName))
		{
			return "Error: You have already been booked.\n";
		}
		else
		{
			return "Error: This booking has already been booked by someone.\n";
		}
	}
	
	public String checkInBag(String lastName, double weight)
	{
		double totalWeight = weight;
		for(int i = 0; i < checkedBaggage.length; i++)
		{
			if(checkedBaggage[i] != null)
			{
				totalWeight += checkedBaggage[i].getWeight();
			}
		}
		if(checkedBaggage[0] != null &&
		   checkedBaggage[1] != null &&
		   checkedBaggage[2] != null)
		{
			return "Error: Number of bags checked must not exceed 3.\n";
		}
		else if(totalWeight > 20.00)
		{
			return "Error: Total weight of bags must not exceed 20kg\n";
		}
		else if(!lastName.equalsIgnoreCase(this.lastName))
		{
			return "Error: Name does not match name of passeneger of the booking.\n";
		}
		else
			updateCheckedBaggage(weight);
			return toString();
	}
	
	private void updateCheckedBaggage(double weight)
	{
		for(int i = 0; i < checkedBaggage.length; i++)
		{
			if(checkedBaggage[i] == null)
			{
				checkedBaggage[i] = new Baggage(baggageId, passengerId(), weight, new DateTime());
				break;
			}
		}
	}
	
	private String passengerId()
	{
		String passengerId = "YASFAY";
		
		return passengerId;
	}
	
	public String collectBags(DateTime dateCollected) 
	{	
		for(int i = 0; i < checkedBaggage.length; i++)
		{
			if(checkedBaggage[i].collect(dateCollected) == false)
			{
				return "Error: You cannot collect before baggage has been checked in \n "
						+ "Or collecting after it has already been collected. \n";
			}
			else
			{
				for(int j = 9; j >= 0; j--)
				{
					if(previousBaggagesChecked[j] != null)
					{
						previousBaggagesChecked[j - 1] = previousBaggagesChecked[j];
					}
				}
				previousBaggagesChecked[0] = checkedBaggage[i];
				checkedBaggage[i] = null;
			}
		}
		firstName = null;
		lastName = null;
		return toString();
	}
	
	public String getDetails() 
	{
		String id = String.format("%-17s %s\n", "ID:", baggageId);
		String rowNumber = String.format("%-17s %s\n","Row Number:", this.rowNumber);
		String seatNumber = String.format("%-17s %s\n","Seat Number:", this.seatNumber);
		String standardFare = String.format("%-17s %s\n", "Standard Fare:", 
														this.standardFare);
		String exitRowCondition = "NO";
		if(this.rowNumber != null)
		{
			switch(this.rowNumber)
			{
			case "3":
				exitRowCondition = "YES";
				break;
			case "4":
				exitRowCondition = "YES";
				break;
			case "6":
				exitRowCondition = "YES";
				break;
			case "7":
				exitRowCondition = "YES";
				break;
			}
		}
		String exitRow = String.format("%-17s %s\n", "Exit Row:", 
												exitRowCondition);
		
		if(this.rowNumber == null || this.seatNumber == null)
		{
			return "";
		}
		else if(firstName == null)
		{	
			return id +
				   rowNumber +
				   seatNumber +
			       standardFare +
				   exitRow;
		}
		else if(checkedBaggage[0] == null)
		{
			String firstName = String.format("%-17s %s\n", "First Name:", 
					this.firstName);
			String lastName = String.format("%-17s %s\n", "Last Name:", 
					this.lastName);
			
			return id +
					   rowNumber +
					   seatNumber +
				       standardFare +
				       firstName +
				       lastName +
					   exitRow;
		}
		else
		{
			String firstName = String.format("%-17s %s\n", "First Name:", 
					this.firstName);
			String lastName = String.format("%-17s %s\n", "Last Name:", 
					this.lastName);
			
			String baggage = "";
			
			for(int i = 0; i < checkedBaggage.length; i++)
			{
				if(checkedBaggage[i] != null)
				{
					baggage += "________________________________________\n" + 
							String.format("%-17s %s\n", "Baggage ID:", 
									checkedBaggage[i].getId()) +
							String.format("%-17s %s\n", "Weight:", 
									checkedBaggage[i].getWeight()) +
							String.format("%-17s %s\n", "Check in Date:", 
									checkedBaggage[i].getCheckInDate()) +
								"________________________________________\n";
				}
			}
			
			return id +
					   rowNumber +
					   seatNumber +
				       standardFare +
				       firstName +
				       lastName +
					   exitRow +
					   baggage;
		}
		
	}
	
}


