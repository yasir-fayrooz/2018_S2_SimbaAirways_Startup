package main;

import airline.Baggage;
import airline.Booking;
import utilities.DateTime;

public class Driver 
 {
	public static void main(String[] args)  
	{
		DateTime Date = new DateTime();
		Baggage baggage = new Baggage("EQF1", "RICCAV", 12.53434, Date);
		System.out.println(baggage.getDetails());
		System.out.println(baggage.toString());
		
		Booking b = new Booking("QF1", "11", "h", 1200);
		System.out.print(b.getDetails());
	}
}
