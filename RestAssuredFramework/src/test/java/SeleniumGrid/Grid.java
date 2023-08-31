package SeleniumGrid;

import org.testng.annotations.Test;

import genericUtilities.BaseClass2;

public class Grid extends BaseClass2{

	@Test
	public void grid()
	{
		driver.get("https://www.amazon.in/");
	}
}
