package OR;

import main.Page_Object_Component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Login_OR {

	public static WebElement ele= null;
	
	@FindBy(xpath="//button[@class='cookiebar-agree-button-agree']")
	public WebElement wbt_cookie_Accept;
	
	@FindBy(partialLinkText="Log")
	public WebElement wlk_Login;
	
	@FindBy(xpath="//h1[@class='g-h1 mya-r-padd-top20']")
	public WebElement wem_Login;
	
	@FindBy(id="emailorfbnumber")
	public WebElement web_username;
	
	@FindBy(id="passwordpincode")
	public WebElement web_pwd;
	
	
public static void Login()  {
	
	
}


}