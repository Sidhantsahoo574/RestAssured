package genericUtilities;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;

import com.beust.jcommander.Parameter;

public class BaseClass2 {
	
	public RemoteWebDriver driver;
	@Parameter()
	@BeforeClass
	public void configBeforeClass(String BROWSER) throws MalformedURLException
	{
		URL url=new URL("http://192.168.38.187:4444/wd/hub");
		DesiredCapabilities cap=new DesiredCapabilities();
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			cap.setPlatform(Platform.WINDOWS);
			cap.setBrowserName("chrome");
		}
		else if(BROWSER.equalsIgnoreCase("firefox")){
			cap.setPlatform(Platform.WINDOWS);
			cap.setBrowserName("firefox");
		}
		driver=new RemoteWebDriver(url, cap);
	}

}
