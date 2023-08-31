package genericUtilities;

import java.util.Random;

public class JavaUtilities {

	public int getRandom()
	{
		Random ran=new Random();
		int randomNo=ran.nextInt();
		return randomNo;
	}
}
