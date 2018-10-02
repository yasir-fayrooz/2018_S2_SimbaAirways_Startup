package utilities;

import airline.Booking;

public class Wrapper
{
	Booking[] bookings = new Booking[0];
	
	public Wrapper()
	{
		
	}
	
	private void updateBookingArray()
	{
		Booking[] bookings2;
		final int LAST_ELEMENT = bookings.length - 1;
		final int DOUBLE_ARRAY_SIZE = bookings.length * 2;
		
		if(bookings.length == 0)
		{
			bookings = new Booking[2];
		}
		else if(bookings[LAST_ELEMENT] != null)
		{
			bookings2 = new Booking[(DOUBLE_ARRAY_SIZE)];
			
			for(int i = bookings.length; i > 0; i--)
			{
				bookings2[i] = bookings[i - 1];
			}
			bookings = bookings2;
		}
		else
		{
			bookings2 = new Booking[(bookings.length)];
			
			for(int i = bookings.length; i > 1; i--)
			{
				bookings2[i - 1] = bookings[i - 2];
			}
			bookings = bookings2;
		}
	}

	public void addBooking(Booking booking)
	{
		updateBookingArray();
		bookings[0] = booking;
	}
	
	public Booking[] getBookings()
	{
		return bookings;
	}

}
