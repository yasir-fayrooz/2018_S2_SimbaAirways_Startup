package airline;

import utilities.InvalidId;

public class Economy extends Booking
{

	public Economy(String id, String rowNumber, String seatNumber, double fee) throws InvalidId
	{
		super(id, rowNumber, seatNumber, fee);
		aisleSeat();
	}
	
	
	
	private void aisleSeat()
	{
		if(calculateExitRow().equals("YES"))
		{
			setAdditionalFee(40);
		}
	}
	
}
