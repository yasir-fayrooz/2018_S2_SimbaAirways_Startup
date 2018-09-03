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
	private double weight;
	private DateTime checkInDate;
	private DateTime collectedDate;
	
	
	public Baggage(String id, String passengerId, double weight, DateTime checkedInDate)
	{
		checkInDate = new DateTime();
		this.id = id + "_" + passengerId + "_" + checkInDate.getEightDigitDate();
	}

}
