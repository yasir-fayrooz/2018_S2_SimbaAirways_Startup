package airline;

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
	private Baggage[] previousBaggagesChecked;
	
	
	//Seat information
	private String rowNumber;
	private String seatNumber;
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
		
		for(int i = 0; i < seatsIndex.length; i++)
		{
			if(!seatNumber.equals(seatsIndex[i]))
			{
				return false;
			}
		}
		for(int i = 0; i < rowIndex.length; i++)
		{
			if(!rowNumber.equals(rowIndex[i]))
			{
				return false;
			}
		}
		
		return true;
	}
	
}


