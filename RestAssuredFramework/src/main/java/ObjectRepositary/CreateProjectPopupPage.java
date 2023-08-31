package ObjectRepositary;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericUtilities.JavaUtilities;

public class CreateProjectPopupPage {

	@FindBy(xpath="//span[.='Create Project']")
	private WebElement createProjectBtn;
	
	@FindBy(name="projectName")
	private WebElement projTbx;
	
	@FindBy(name="createdBy")
	private WebElement createByTbx;
	
	@FindBy(xpath="(//select[@name='status'])[2]")
	private WebElement dropdown;
	
	@FindBy(xpath="//input[@value='Add Project']")
	private WebElement addProjBtn;
	
	//Initialization
	public CreateProjectPopupPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public WebElement getCreateProjectBtn() {
		return createProjectBtn;
	}

	public WebElement getProjTbx() {
		return projTbx;
	}

	public WebElement getCreateByTbx() {
		return createByTbx;
	}

	public WebElement getDropdown() {
		return dropdown;
	}

	public WebElement getAddProjBtn() {
		return addProjBtn;
	}
	
	//Business Logic
	JavaUtilities j=new JavaUtilities();
	public void createProjPage(String projectName,String createBy,WebDriver driver)
	{
		createProjectBtn.click();
		projTbx.sendKeys(projectName+j.getRandom());
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("document.getElementsByName('teamSize').value='10'");
		createByTbx.sendKeys(createBy);
		dropdown.click();
		addProjBtn.click();
	}
	
	
}
