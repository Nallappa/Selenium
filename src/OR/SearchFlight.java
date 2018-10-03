package OR;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchFlight {

	public static WebElement element = null;
	
	@FindBy(xpath="//input[@class='g-search-form--input g-forms-locationpicker g-locationpicker--origin g-forms-text g-hc-transparent-background']")
	public WebElement web_From;
	
	@FindBy(xpath="//input[@class='g-search-form--input g-forms-locationpicker g-locationpicker--destination g-forms-text g-hc-transparent-background']")
	public WebElement web_To;
	
	@FindBy(xpath="//input[@class='g-search-form--input g-pax-selector g-forms-text']")
	public WebElement web_Passengers;

	@FindBy(xpath="//div[@id='g-pax-selector-g-select-form1']//div")
	public WebElement web_Passengerform;
	
	@FindBy(xpath="//label[@class='g-search-form--inputset g-search-form--date']/input")
	public WebElement web_depature;

	@FindBy(xpath="//*[@role='gridcell' and @data-id='59']/a")
	public WebElement web_depature_select;
	
	@FindBy(xpath="//label[@id='pax-adults-label']/../div/button[2]")
	public WebElement wbt_Adultbutton;
	
	@FindBy(xpath="//label[@id='pax-children-label']/../div/button[2]")
	public WebElement wbt_children_button;
	
	@FindBy(xpath="//*[@class='g-btn g-pax-selector--close']")
	public WebElement wbt_ok;
	
	@FindBy(xpath="//*[@class='g-search-form--inputset g-search-form--dropdown']/select")
	public WebElement web_Travelclass;
	
	@FindBy(partialLinkText="One way")
	public WebElement wlk_Oneway;

	//Select Your Flight
	@FindBy(xpath="//*[@class='est-title est-clear']")
	public WebElement wem_BookYourFlight;
	
	@FindBy(xpath="//*[@class='est-flight-item']/li[1]/div/div[1]/div[1]")
	public WebElement wrb_SelectRadioButton;
	
	//*[@role='button'][2]
	
	@FindBy(xpath="//button[@class='g-btn g-btn-primary g-forms-next']")
	public WebElement wbt_Continue1;
	
	@FindBy(partialLinkText="Continue")
	public WebElement wbt_Continue;
	
	//button[@class='g-btn g-btn-primary g-forms-next']
	//Extra Luggage
	@FindBy(id="ps-page-product-list-booking__product-list__title")
	public WebElement wem_ExtraLuggage;
	
	@FindBy(xpath="//h1[@class='g-h1 g-steps-title']")
	public WebElement wem_PersonalDetails;
	
	//Personal Details:
	//Title
	@FindBy(name="title")
	public WebElement wlst_PersonalDetails_Title;
	
	@FindBy(name="firstName")
	public WebElement wem_PersonalDetails_First;
	
	@FindBy(name="lastName")
	public WebElement wem_PersonalDetails_Last;
	
	@FindBy(name="emailAddress")
	public WebElement wem_PersonalDetails_emailAddress;
	
	@FindBy(name="phoneNumberFirst")
	public WebElement wem_PersonalDetails_phoneNumberFirst;
	
	@FindBy(xpath="//select[@name='phoneNumberSecondCountry']")
	public WebElement wem_PersonalDetails_CountryCode;
	
	@FindBy(id="checkout-step-submit")
	public WebElement wbt_Proceed;
	
	//Fill your Payment Details
	@FindBy(xpath="//div[@class='checkout-section']/h2")
	public WebElement wem_PaymentDetails;

	@FindBy(xpath="//button[@class='cookiebar-agree-button-agree']")
	public WebElement wbt_cookie_Accept1;
}
	
	