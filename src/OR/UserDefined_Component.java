package OR;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;

import main.Operations_Component;
import main.Page_Object_Component;
import main.Utility_Component;
import main.Xls_Reader;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import Actions.BookFlight;



public class UserDefined_Component extends Operations_Component  {
	
	public static Xls_Reader datatable;
	static String[] inceptiondatearray;
	static String Browser_Type;
	public static String curr_transaction; 
	static String URL;
	public static String TESTENV;
	static boolean contentsflag = false;

	
	public static void Transaction(String strDataSheetName,int row,String TransactionName) throws IOException, InterruptedException, AWTException{
					
		 datatable =  new Xls_Reader(Utility_Component.readValue("TestData_Path"));
		
		curr_transaction=TransactionName.toUpperCase();
		LaunchBrowser(strDataSheetName,row);
	
		switch(curr_transaction)
			{
				case "BOOKFLIGHT":
					BookFlight.Booking(strDataSheetName, row,Page_Object_Component.browser);
				break;			
			}	
	}

	public static void LaunchBrowser(String strDataSheetName, int row) throws IOException, InterruptedException, AWTException {
		URL = datatable.getCellData(strDataSheetName, "URL", row).trim();
		Browser_Type =Utility_Component.readValue("Browser_type");
		Page_Object_Component.setBrowser(Browser_Type);
		Thread.sleep(1000);
		UserDefined_Component.browser.navigate().to(URL);

	}

	public static void setBrowser(String Browser) throws IOException, InterruptedException, AWTException{
			 
		 robot = new Robot();
		 String browsertype = Browser;
		 switch (browsertype)
		 {
			 case "Firefox":
				 browser = new FirefoxDriver();
				 browser.manage().window().maximize();
	 			 break;
	 			 
			 case "Chrome":
				 if(browser==null)
      			 {
					 System.setProperty("webdriver.chrome.driver", Utility_Component.readValue("Chrome_Path"));
					 browser = new ChromeDriver();
					 browser.manage().window().maximize();
      			 }
				 break;
			 
			 case "InternetExplorer":
			 	
      			 if(browser==null)
      			 {
      				 System.setProperty("webdriver.ie.driver", Utility_Component.readValue("IE_Path"));
	   				 DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
	   				 ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
         			 ieCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
      				 browser = new InternetExplorerDriver(ieCapabilities);
      			 }

				break;
		 }

	}	  //SetBrowser
	
}
