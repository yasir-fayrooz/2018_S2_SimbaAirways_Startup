package main;

import airline.Baggage;
import utilities.DateTime;

public class Driver 
 {
	public static void main(String[] args)  
	{
		DateTime Date = new DateTime();
		Baggage baggage = new Baggage("EQF1", "RICCAV", 12.53434, Date);
		System.out.println(baggage.getDetails());
		System.out.println(baggage.toString());
	}
}
