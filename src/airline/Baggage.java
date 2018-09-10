package airline;

import utilities.DateTime;

/*
 * Class:			Baggage
 * Description:		The class represents a piece of luggage 
 * Author:			[Yasir Fayrooz Ali] - [s3742162]
 */
public class Baggage
{
	private String id;
	private double weight = 0;
	private DateTime checkInDate;
	private DateTime collectedDate;
	
	
	public Baggage(String id, String passengerId, double weight, DateTime checkedInDate)
	{
		this.checkInDate = checkedInDate;
		this.id = id + "_" + passengerId + "_" + checkInDate.getEightDigitDate();
		this.weight = weight;
	}
	
	
	public boolean collect(DateTime collectionDate)
	{
		if(collectedDate != null ||
		   DateTime.diffDays(collectionDate, collectedDate) == 0)
		{
			return false;
		}
		else
		{
			collectedDate = collectionDate;
			return true;
		}
	}
	
	public String getDetails()
	{
		String hireId = String.format("%-17s %s\n", "Hire ID:", id);
		String baggageId = String.format("%-17s %s\n","Baggage Id:", id);
		String weight = String.format("%-17s %s\n","Weight:", this.weight + "kg");
		
		String checkedIn = String.format("%-17s %s\n", "Checked in:", 
										checkInDate.getFormattedDate());

		
		if(collectedDate != null)
		{
			String collected = String.format("%-17s %s\n","Collected:", 
					collectedDate.getFormattedDate());
			
			String checkedCollected = hireId + 
					  weight + 
					  checkedIn + 
					  collected;
			
			return checkedCollected;
		}
		else
		{
			
			String checkedNotCollected = baggageId + 
					 weight + 
					 checkedIn;
			
			return checkedNotCollected;
		}
		
	}
	
	public String toString()
	{
		if(collectedDate != null)
		{
			String checkedCollected = 
				   id + 
				   ":" + weight + 
				   ":" + checkInDate.getEightDigitDate() + 
				   ":" + collectedDate.getEightDigitDate();
			
			return checkedCollected;
		}
		else
		{
			String checkedNotCollected = 
				   id + 
				   ":" + weight + 
				   ":" + checkInDate.getEightDigitDate() +
				   ":" + "NO";
			
			return checkedNotCollected;	
		}
	}

	public double getWeight()
	{
		return weight;
	}
	public String getId()
	{
		return id;
	}
	public DateTime getCheckInDate() 
	{
		return checkInDate;
	}
}
