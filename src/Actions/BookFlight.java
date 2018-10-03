package Actions;

import java.io.IOException;
//import java.util.logging.Logger;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import main.Operations_Component;
import main.Page_Object_Component;
import main.Reports;
import main.Utility_Component;
import main.Xls_Reader;
import OR.Login_OR;
import OR.UserDefined_Component;

import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;


public class BookFlight extends Operations_Component {
	

 public static void Login(String strDataSheetName,int row,WebDriver wd) throws IOException, InterruptedException
 {
	 System.out.println("Entered in to login");
	 
	 
	 Xls_Reader datatable = new Xls_Reader(Utility_Component.readValue("TestData_Path"));
	 OR.Login_OR obj_Login = PageFactory.initElements(wd,OR.Login_OR.class);
	 
	
	 //To click on the Accept button in cookie
	 Wait(8);
     try{ 
    	 switchToWindow();
		 if(obj_Login.wbt_cookie_Accept.isDisplayed())
		 {
		// obj_Login.wbt_cookie_Accept.click();	 
		 performObjectOperation(obj_Login.wbt_cookie_Accept, "CookieAccept");
		}
	 }
	 catch(Exception E)
	 {
		E.printStackTrace();
	 }
	 
	
	 performObjectOperation( obj_Login.wlk_Login, "LoginButton");

	 WaitUntil(obj_Login.wem_Login);
	 
	 
	 //Entering UserName and Password
 try{
		 
		 if(obj_Login.wem_Login.isDisplayed())
		 {
				//Username
				if(datatable.getCellData(strDataSheetName,"username", row).trim().equals("")==false)
				{
					if(obj_Login.web_username.isDisplayed())
					 {
						SetText(obj_Login.web_username,datatable.getCellData(strDataSheetName,"username", row).trim());
					}
				}
				
				
				//Password
				if(datatable.getCellData(strDataSheetName,"password", row).trim().equals("")==false)
				{
					if(obj_Login.web_pwd.isDisplayed())
					 {
						SetText(obj_Login.web_pwd,datatable.getCellData(strDataSheetName,"password", row).trim());
					}
				}
				 
		}
	 }
	 catch(Exception E)
	 {
		E.printStackTrace();
	 }
	 
	
 }
 
 public static void Booking(String strDataSheetName,int row,WebDriver wd) throws IOException, InterruptedException
 {
	 
	try {
	SearchFlight(strDataSheetName,row,wd);
	SelectYourFlight(strDataSheetName,row,wd);
	ExtraLuggage(strDataSheetName,row,wd);
	PersonalDetails(strDataSheetName,row,wd);
	PaymentDetails(strDataSheetName,row,wd);
	 }catch(Exception E){
		E.printStackTrace();}
		
 }
 
 
 public static void SearchFlight(String strDataSheetName,int row,WebDriver wd) throws IOException, InterruptedException {
	 Xls_Reader datatable = new Xls_Reader(Utility_Component.readValue("TestData_Path"));
	 OR.SearchFlight Pg = PageFactory.initElements(Page_Object_Component.browser,OR.SearchFlight.class);
	 Wait(2);
	 try{ 
    	 switchToWindow();
    	 WaitUntil(Pg.wbt_cookie_Accept1);
		 if(Pg.wbt_cookie_Accept1.isDisplayed())
		 {
		// obj_Login.wbt_cookie_Accept.click();	 
		 performObjectOperation(Pg.wbt_cookie_Accept1, "CookieAccept");
		}
	 Wait(4);
	 WaitUntil(Pg.web_From);
	 AutoSelect(Pg.web_From,datatable.getCellData(strDataSheetName,"From", row).trim());
	 AutoSelect(Pg.web_To,datatable.getCellData(strDataSheetName,"To", row).trim());
	 performObjectOperation(Pg.web_depature,"Departuredateclick");
	 Wait(2);
	 performObjectOperation(Pg.web_depature_select,"DeparturedateSelect");
	 Wait(2);
	 performObjectOperation(Pg.web_Passengers,"PassengersInput");
	
	if (VerifyObject_Exists(Pg.wbt_Adultbutton)) {
	 performObjectOperation(Pg.wbt_ok,"PassengersInput");
	}
	 selectValue( Pg.web_Travelclass,datatable.getCellData(strDataSheetName,"Class", row).trim(),"text","TypeofClass");
	 
	 Wait(6);
	performObjectOperation(Pg.wlk_Oneway,"ClickonOnwayLink");
	 
	 }catch(Exception E){
		 E.printStackTrace();}
		
 }	//
 
public static void SelectYourFlight(String strDataSheetName,int row,WebDriver wd) throws IOException, InterruptedException {
	
	try {
	 Xls_Reader datatable = new Xls_Reader(Utility_Component.readValue("TestData_Path"));
	 OR.SearchFlight Pg = PageFactory.initElements(Page_Object_Component.browser,OR.SearchFlight.class);
	 Wait(4);

	 WaitUntil(Pg.wem_BookYourFlight);
	 
		if (VerifyObject_Exists(Pg.wem_BookYourFlight)) {
			Reports.writeOutput("BookYour Flight page should be displayed" , "BookYour Flight page is displayed" , "Pass");
			 System.out.println("Emtered into function");
			 Wait(5);
			//performObjectOperation(Pg.wrb_SelectRadioButton,"SelectYourFlight");
			
//		 List<WebElement> SelectFlight = Page_Object_Component.browser.findElements(By.xpath("//*[@class='est-flight-item ']//span[1]"));
//		 System.out.println("The size is" + SelectFlight.size());
//		 SelectFlight.get(1).click();
//		 
//		 Wait(4);
//		
// for (int i = 1; i<=SelectFlight.size(); i=i+1)
//		 
//	{
////			System.out.println(SelectFlight.get(i).getText());
//	}
//			Wait(2);
          performObjectOperation(Pg.wbt_Continue,"ClickonContinuebutton");
//
	}
			else {
				Reports.writeOutput("BookYour Flight page should be displayed" , "BookYour Flight page is not displayed" , "Fail");
			}
	}catch(Exception E){
		 E.printStackTrace();}
	
 }
 


public static void ExtraLuggage(String strDataSheetName,int row,WebDriver wd) throws IOException, InterruptedException {
	
	try {
	 Xls_Reader datatable = new Xls_Reader(Utility_Component.readValue("TestData_Path"));
	 OR.SearchFlight Pg = PageFactory.initElements(Page_Object_Component.browser,OR.SearchFlight.class);
	 Wait(4);
	 WaitUntil(Pg.wem_ExtraLuggage);
		if (VerifyObject_Exists(Pg.wem_ExtraLuggage)) {
			Reports.writeOutput("ExtraLuggage page should be displayed" , "ExtraLuggage page page is displayed" , "Pass");
			Wait(2);
			performObjectOperation(Pg.wbt_Continue1,"ClickonContinuebutton");
			
		}
			else {
				Reports.writeOutput("ExtraLuggage page should be displayed" , "ExtraLuggage page is not displayed" , "Fail");
			}	
	}catch(Exception E){
		 E.printStackTrace();}
}
 
 public static void PersonalDetails(String strDataSheetName,int row,WebDriver wd) throws IOException, InterruptedException {
	 try {
		 
	 Xls_Reader datatable = new Xls_Reader(Utility_Component.readValue("TestData_Path"));
	 OR.SearchFlight Pg = PageFactory.initElements(Page_Object_Component.browser,OR.SearchFlight.class);
	 Wait(4);
	 WaitUntil(Pg.wem_PersonalDetails);
		if (VerifyObject_Exists(Pg.wem_PersonalDetails)) {
			Reports.writeOutput("Personaldetails should be displayed" , "Personaldetails page is displayed" , "Pass");
				if(datatable.getCellData(strDataSheetName,"Title", row).trim().equals("")==false)
				{
					if(Pg.wlst_PersonalDetails_Title.isDisplayed())
					 {
						selectValue( Pg.wlst_PersonalDetails_Title,datatable.getCellData(strDataSheetName,"Title", row).trim(),"text","Title");
					}
				}
				SetText(Pg.wem_PersonalDetails_First,datatable.getCellData(strDataSheetName,"Firstname", row).trim());
				SetText(Pg.wem_PersonalDetails_Last,datatable.getCellData(strDataSheetName,"Lastname", row).trim());
				SetText(Pg.wem_PersonalDetails_emailAddress,datatable.getCellData(strDataSheetName,"Email", row).trim());
				SetText(Pg.wem_PersonalDetails_phoneNumberFirst,datatable.getCellData(strDataSheetName,"TelephoneNo", row).trim());
				//Pg.wbt_Proceed.click();
				performObjectOperation(Pg.wbt_Proceed,"ClickonProceedbutton");
		}
			else {
				Reports.writeOutput("Personaldetails should be displayed" , "Personaldetails is not displayed" , "Fail");
			}	
	 }catch(Exception E){
		 E.printStackTrace();}
 }

public static void PaymentDetails(String strDataSheetName,int row,WebDriver wd) throws IOException, InterruptedException {
	 
	try {
	 Xls_Reader datatable = new Xls_Reader(Utility_Component.readValue("TestData_Path"));
	 OR.SearchFlight Pg = PageFactory.initElements(Page_Object_Component.browser,OR.SearchFlight.class);
	 Wait(4);
	 WaitUntil(Pg.wem_PaymentDetails);
		if (VerifyObject_Exists(Pg.wem_PaymentDetails)) {
			Reports.writeOutput("PaymentDetails should be displayed" , "PaymentDetails page is displayed" , "Pass");
		
		}
			else {
				Reports.writeOutput("PaymentDetails should be displayed" , "PaymentDetails is not displayed" , "Fail");
			}	
	}catch(Exception E){
		 E.printStackTrace();}
 }
 
 public static void  AutoSelect(WebElement wem,String Text) throws IOException, InterruptedException {
	 try {
	 String searchString = Text.split(" ")[0].trim();
	 SetText(wem,searchString);
	 Wait(2);
	 
	 List<WebElement> list = Page_Object_Component.browser.findElements(By.xpath("//ul[@role='listbox']//li"));
     if(list.size()==0) {
         //System.out.println("autoComplete list NOT found");
         Reports.writeOutput("Autoselect value"+Text+" should be selected" , "Autoselect value"+Text+" is not found in the list" , "Fail");
     }
     else {
         System.out.println("autoComplete list Found with elements: "+list.size());
     }

     for (WebElement ac: list){
         if(ac.getText().contains(Text)){  
             ac.click();
         	Reports.writeOutput("Autoselect value"+Text+" should be selected" , "Autoselect value"+Text+" is selected" , "Pass");
             break;
         }
     }
	 }catch(Exception E){
		 E.printStackTrace();}
 }
 
}// class
