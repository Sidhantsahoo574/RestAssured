package ObjectRepositary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.BaseClass;

public class HomePage extends BaseClass {

	//Declaration
	@FindBy(xpath="//a[.='Projects']")
	private WebElement projectsModule;

	@FindBy(xpath="//td[text()='\"+proj+\"']")
	private WebElement getProjName;

	@FindBy(xpath="//td[text()='\"+proj+\"']/preceding-sibling::td")
	private WebElement getIdName;

	//	Initialization
	public HomePage(WebDriver driver)
	{

		PageFactory.initElements(driver, this);

	}

	//Utilization
	public WebElement getProjectsModule() {
		return projectsModule;
	}

	public WebElement getProj()
	{
		return getProjName;
	}

	public WebElement getId()
	{
		return getIdName;
	}
	//Business Logic
	public void homePage()
	{
		projectsModule.click();
//		getProjName.getText();
//		getIdName.getText();
	}

}
