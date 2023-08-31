package genericUtilities;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebActionUtility {


	/**
	 * it's an implicitly wait Always wait for Element in DOM document & release the
	 * control if element available
	 * 
	 * @param driver
	 * @throws Throwable
	 */
	public void waitForElementInDOM(WebDriver driver) {

		try {driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			
		} catch (Throwable e) {		} 
	}

	/**
	 * it's an Explicitly wait Always wait for Page to be loaded & available in GUI
	 * 
	 * @param driver
	 * @param partailPageURL
	 * @throws Throwable
	 */
	public void waitForPage(WebDriver driver, String partailPageURL)  {
		
		WebDriverWait wait=new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.urlContains(partailPageURL));
	}

	/**
	 * it's an Explicitly wait Always wait for Page to be loaded & available in GUI
	 * 
	 * @param driver
	 * @param partailPageURL
	 * @throws Throwable
	 */
	public void waitForElement(WebDriver driver, WebElement element) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOf(element));
			
		} catch (Throwable e) {
		}	
	}
	
	/**
	 * used to select the value from the dropDwon based on index
	 * 
	 * @param driver
	 * @param index
	 */
	public void select(WebElement element , int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	/**
	 * used to select the value from the dropDwon based on value / option avlaible
	 * in GUI
	 * 
	 * @param element
	 * @param value
	 */
	public void select(WebElement element, String text) {
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}

}
