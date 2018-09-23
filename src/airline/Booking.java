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
	private double standardFare;
	
	//Name of passenger
	private String firstName;
	private String lastName;	
	
	
	public Booking(String id, String rowNumber, String seatNumber, double fee) 
	{
		baggageId = bookingClassPrefix[0] + id;
		this.rowNumber = rowNumber;
		this.seatNumber = seatNumber;
		this.standardFare = fee;
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
			return getDetails();
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
	
	public String getBaggageHistory()
	{
		String baggageHistory = "";
		
		for(Baggage b : previousBaggagesChecked)
		{
			if(b == null) {}
			else
			{
				baggageHistory += b.getDetails();
				baggageHistory += "--------------------------------------\n";
			}
		}
		if(baggageHistory.equals(""))
			return "Error: No baggage history." +
		           "\nPlease collect baggage to update the history.\n";
		else
			return "         Newest - Oldest\n" +
					baggageHistory;
	}
	
	private String passengerId()
	{
		String firstNameID = "";
		String lastNameID = "";
		if(firstName.length() < 3)
		{
			firstNameID = firstName.substring(0, firstName.length());
		}
		else
		{
			firstNameID = firstName.substring(0, 3);
		}
		if(lastName.length() < 3)
		{
			lastNameID = lastName.substring(0, lastName.length());
		}
		else
		{
			lastNameID = lastName.substring(0, 3);
		}
		String passengerId = firstNameID + lastNameID;
		
		return passengerId.toUpperCase();
	}
	
	public String collectBags(DateTime dateCollected) 
	{	
		for(int i = 0; i < checkedBaggage.length; i++)
		{
			if(checkedBaggage[0] == null)
				{
					return "Error: You cannot collect before baggage has been checked in\n"
							+ "       Or collecting after it has already been collected. \n";
				}
		}
		
		for(int i = 0; i < checkedBaggage.length; i++)
		{
			if(checkedBaggage[i] != null)
			{
				if(checkedBaggage[i].collect(dateCollected) == true)
				{
					if(previousBaggagesChecked[9] != null)
					{
						previousBaggagesChecked[9] = null;
					}
					for(int j = 8; j >= 0; j--)
					{
						if(previousBaggagesChecked[j] != null)
						{
							previousBaggagesChecked[j + 1] = previousBaggagesChecked[j];
							previousBaggagesChecked[j] = null;
						}
					}
					previousBaggagesChecked[0] = checkedBaggage[i];
					checkedBaggage[i] = null;
					
				}
			}
		}
		firstName = null;
		lastName = null;
		return "Baggage collection successfull \n" + toString();
	}
	
	private String exitRowCondition()
	{
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
		return exitRowCondition;
	}
	
	public String getDetails() 
	{
		String id = String.format("%-17s %s\n", "ID:", baggageId);
		String rowNumber = String.format("%-17s %s\n","Row Number:", this.rowNumber);
		String seatNumber = String.format("%-17s %s\n","Seat Number:", this.seatNumber);
		String standardFare = String.format("%-17s %s\n", "Standard Fare:", 
														"$" + this.standardFare);
		
		String exitRow = String.format("%-17s %s\n", "Exit Row:", 
												exitRowCondition());
		
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
									checkedBaggage[i].getWeight() + " kg") +
							String.format("%-17s %s\n", "Check in Date:", 
									checkedBaggage[i].getCheckInDate().getFormattedDate()) +
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
	
	public String toString()
	{
		String checkedBags = "";
		for(int i = 0; i < checkedBaggage.length; i++)
		{
			if(checkedBaggage[i] != null)
			{
				checkedBags += ":" + checkedBaggage[i].toString();
			}
		}
		return baggageId + ":" +
	           rowNumber + ":" +
			   seatNumber + ":" +
	           standardFare + ":" +
			   firstName + ":" +
	           lastName + ":" +
			   exitRowCondition() +
			   checkedBags;		   
	}

	

}


