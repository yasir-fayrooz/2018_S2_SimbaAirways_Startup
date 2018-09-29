package airline;

import utilities.InvalidId;

public class Business extends Booking
{
	private boolean limosinePickUp = false;

	public Business(String id, String rowNumber, String seatNumber, double fee) throws InvalidId
	{
		super(id, rowNumber, seatNumber, fee);
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
	
	private String printAlacarteMenu()
	{
		return "Beef Burgandy, Cheese Platter, Glass of Wine,\n "
				+ "                 Potato and Eggplant Curry, Chocolate Mousse, Fresh Juice";
	}
	
	public void setLimosinePickUp(boolean limosine)
	{
		limosinePickUp = limosine;
	}
	private String calculateLimosine()
	{
		if(limosinePickUp == true)
			return "YES";
		else
			return "NO";
	}

	@Override
	public String getDetails() 
	{
		String id = String.format("%-17s %s\n", "ID:", toStringIndex(super.toString(), 0, 1));
		String rowNumber = String.format("%-17s %s\n","Row Number:", toStringIndex(super.toString(), 1, 2));
		String seatNumber = String.format("%-17s %s\n","Seat Number:", toStringIndex(super.toString(), 2, 3));
		String standardFare = String.format("%-17s %s\n", "Standard Fare:", 
														"$" + toStringIndex(super.toString(), 3, 4));
		

		Baggage[] checkedBaggage = getCheckedBaggage();
		
		String menu = String.format("%-17s %s\n", "Menu:", printAlacarteMenu());
		String limo = String.format("%-17s %s\n", "Limosine:", calculateLimosine());
		
		String firstName = null;
		String lastName = null;
		
		
		try
		{
			firstName = toStringIndex(super.toString(), 4, 5);
			lastName = toStringIndex(super.toString(), 5, 6);
		} catch(Exception e) {}
		
		
		if(firstName.equals("null"))
		{	
			return id +
				   rowNumber +
				   seatNumber +
			       standardFare +
				   menu;
		}
		else if(checkedBaggage[0] == null)
		{
			String pFirstName = String.format("%-17s %s\n", "First Name:", 
					firstName);
			String pLastName = String.format("%-17s %s\n", "Last Name:", 
					lastName);
			
			return id +
					   rowNumber +
					   seatNumber +
				       standardFare +
				       pFirstName +
				       pLastName +
					   menu +
					   limo;
		}
		else
		{
			String pFirstName = String.format("%-17s %s\n", "First Name:", 
					firstName);
			String pLastName = String.format("%-17s %s\n", "Last Name:", 
					lastName);
			
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
				       pFirstName +
				       pLastName +
					   menu +
					   limo +
					   baggage;
		}
		
	}

	public String toString()
	{
		String id = toStringIndex(super.toString(), 0, 1);
		String rowNumber = toStringIndex(super.toString(), 1, 2);
		String seatNumber = toStringIndex(super.toString(), 2, 3);
		String standardFare = toStringIndex(super.toString(), 3, 4);
		
		String checkedBags = "";
		Baggage[] checkedBaggage = getCheckedBaggage();
		
		String firstName = null;
		String lastName = null;
		
		
		try
		{
			firstName = toStringIndex(super.toString(), 4, 5);
			lastName = toStringIndex(super.toString(), 5, 6);
		} catch(Exception e) {}
		
		for(int i = 0; i < checkedBaggage.length; i++)
		{
			if(checkedBaggage[i] != null)
			{
				checkedBags += checkedBaggage[i].toString() + ":";
			}
		}
		return id + ":" +
	           rowNumber + ":" +
			   seatNumber + ":" +
	           standardFare + ":" +
			   firstName + ":" +
	           lastName + ":" +
			   calculateLimosine() + ":" +
			   checkedBags;		   
	}
}
