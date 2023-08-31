package ObjectRepositary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.BaseClass;
import genericUtilities.IConstant;

public class LoginPage extends BaseClass{
	
	//Declaration
	@FindBy(id="usernmae")
	private WebElement unTbx;
	@FindBy(id="inputPassword")
	private WebElement pwTbx;
	@FindBy(xpath="//button[.='Sign in']")
	private WebElement lgBtn;
	@FindBy(xpath="//button[.=\"Create Account\"]")
	private WebElement createAccountBtn;
	
	//Initialization
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
    //Utilization
	public WebElement getUnTbx() {
		return unTbx;
	}

	public WebElement getPwTbx() {
		return pwTbx;
	}

	public WebElement getLgBtn() {
		return lgBtn;
	}

	public WebElement getCreateAccountBtn() {
		return createAccountBtn;
	}
	//BusinessLogic
	
	public void loginPage()
	{
		unTbx.sendKeys(IConstant.apiUserName);
		pwTbx.sendKeys(IConstant.apiPassword);
		lgBtn.click();
	}
	public void createAccount()
	{
		createAccountBtn.click();
	}
	
	
	

}
