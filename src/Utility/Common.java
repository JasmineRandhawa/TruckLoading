package Utility;

import java.math.BigDecimal;
import java.math.RoundingMode;

/* Some Common generic Functions */
public class Common 
{
	/* Public Methods */
	
	/* round double value to required number of decimal places */
	public static double RoundDecimal(double value) {
		int places = 2;
		BigDecimal bd = BigDecimal.valueOf(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
	
	/* get a random number within a range */
	public static int GetRandomNumber(int min, int max) 
	{
		return (int) ((Math.random() * (max - min)) + min);
	}

}
