

package main;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;



public class Page_Object_Component  {
	
	public static WebDriver browser;
	static String parentwindow;
	public static Robot robot;
	
	
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

	}	  
	
	 	
}
