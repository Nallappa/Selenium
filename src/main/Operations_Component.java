
package main;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class Operations_Component extends Page_Object_Component {

	//To set a value to the textbox
	public static void SetText(WebElement textfield,String Text)
	{
//		browser.findElement(By.id("")).click();
		//textfield.sendKeys(Text);
		textfield.sendKeys(Text);
	//	JavascriptExecutor myExecutor=((JavascriptExecutor) Page_Object_Component.browser);
		//myExecutor.executeScript("arguments[0].value='"+Text+"';", textfield);
	}
	
	//To set a value to the listbox	
	public static void SelectValue(WebElement listbox,String Value)
	{
		Select myselect=new Select(listbox); 
		myselect.selectByVisibleText(Value);
	}
		
	
	//static String parentwindow = Page_Object_Component.browser.getWindowHandle();
		static Page_Object_Component poc  = new Page_Object_Component();	
		
		public static Xls_Reader datatable;
			
		public static void performObjectOperation(WebElement Obj, String Objectname) throws IOException{
			String Expected = "";
			String Actual = "";
			if(Obj.isDisplayed()){			
				String Type = Obj.getAttribute("type");
//				String objprop = Obj.toString().split("->")[1].replace(":", "(\"");
//				String objfinal =objprop.substring(0, objprop.length()-1)+"\")]";
				
				if(Obj.getTagName().equals("label"))
				{								
						Expected = "Should click '" + Objectname + "' label";
						//Expected = "Click operation should be done successfully on '" + Obj.getText() + "' with property'[By." + Obj.toString().split("->")[1] + "'";
						Actual = "Clicked on '" + Objectname + "' label successfully";
					
				}
				else if(Obj.getTagName().equals("a"))
				{
					Expected = "Should click '" + Objectname + "'link";
					//Expected = "Click operation should be done successfully on '" + Obj.getText() + "' with property'[By." + Obj.toString().split("->")[1] + "'";
					Actual = "Clicked on '" + Objectname + "'link successfully";	
					
				}
				else{
					switch (Type.toLowerCase())
					{
						case "radio":
							
							Expected =  "Should select '"+Objectname+" 'radiobutton";
							//Expected = "Click operation should be done successfully on '" + Obj.getAttribute("value") + "' with property'[By." + Obj.toString().split("->")[1] + "'";
							Actual = "' "+Objectname + "' radiobutton successfully selected";
							break;
						
						case "checkbox":
						
							Expected = "Should check '"+Objectname+" 'checkbox";
							//Expected = "Click operation should be done successfully on '" + Obj.getAttribute("value") + "' with property'[By." + Obj.toString().split("->")[1] + "'";
							Actual = "' "+Objectname + "' checkbox checked successfully";
							break;
						
						default:					
							Expected = "Should Click on '" + Objectname + "' button";
							//Expected = "Click operation should be done successfully on '" + Obj.getAttribute("value") + "' with property'[By." + Obj.toString().split("->")[1] + "'";
							Actual = "Clicked on '" + Objectname + "' button successfully";
							break;
					}
				}
				Actions action = new Actions(Page_Object_Component.browser);
				action.moveToElement(Obj).click().perform();
				//Obj.click();

				Reports.writeOutput(Expected , Actual , "Pass");
			}
			else{
				Expected = "Object should be Present";
				Actual = "Object Not Present";
				Reports.writeOutput(Expected, Actual, "Fail");
				}
		}
		
		//''''''''''''''''Added by AHMES31 on 18/11/2015 - to deal with multiple values of checkboxes and RadioButtons 
		//To select the radio box/ check box based on value
		public static void performObjectOperation(List<WebElement> Obj,String Value,String Objectname) throws IOException{
			
			String Expected = "";
			String Actual = "";
			int k,objflag=0;
			if(Obj.get(0).isDisplayed())
				{
					String Type=Obj.get(0).getAttribute("type");
					//Iterator<WebElement> itr=Obj.iterator();	
					for(k=0;k<Obj.size();k++)
								{
								 if(Obj.get(k).getAttribute("value").equalsIgnoreCase(Value))
								 	{
										/*switch (Type.toLowerCase())
										{
											case "radio":*/
												
												Expected =  "Should select '"+Objectname+" 'radiobutton";
												//Expected = "Click operation should be done successfully on '" + Obj.getAttribute("value") + "' with property'[By." + Obj.toString().split("->")[1] + "'";
												Actual = "' "+Objectname + "' radiobutton successfully selected";
												Actions action = new Actions(Page_Object_Component.browser);
												action.moveToElement(Obj.get(k)).click().perform();
												//Obj.click();
												Reports.writeOutput(Expected , Actual , "Pass");
												objflag=1;
												break;
											
												
											
										/*	case "checkbox":
											
												Expected = "Should check '"+Objectname+" 'checkbox";
												//Expected = "Click operation should be done successfully on '" + Obj.getAttribute("value") + "' with property'[By." + Obj.toString().split("->")[1] + "'";
												Actual = "' "+Objectname + "' checkbox checked successfully";
												break;*/
										//}
										
										
								 	}
								 
								 }
					 if(objflag==0)
					    {
							 Expected = "Correct value in Datasheet for Object " +Objectname;
							 Actual = "Incorrect value in Datasheet for Object " +Objectname;
							 Reports.writeOutput(Expected, Actual, "Fail");
					    }
					 	
					
				}
			else
				{
					Expected = "Object should be Present";
					Actual = "Object Not Present";
					Reports.writeOutput(Expected, Actual, "Fail");
				}
				
			}

		//------------------------------------------------------------------------------------------------------------
		public static void clickImage(WebElement Obj,String Objectname) throws IOException
		{
			String Expected = "";
			String Actual = "";
			if(Obj.isDisplayed()){
			
				
//				String objprop = Obj.toString().split("->")[1].replace(":", "(\"");
//				String objfinal =objprop.substring(0, objprop.length()-1)+"\")]";
				
				Expected = "Should Click image ' " + Objectname + " '";
				//Expected = "Click operation should be done successfully on '" + Obj.getAttribute("value") + "' with property'[By." + Obj.toString().split("->")[1] + "'";
				Actual = "Clicked on image ' " + Objectname + " '";
				//Obj.click();
				Actions action = new Actions(Page_Object_Component.browser);
				action.moveToElement(Obj).click().perform();
				Reports.writeOutput(Expected , Actual , "Pass");
				
			}
		}
		
		public static void setObjectValue(WebElement Obj, String value,String Objectname) throws IOException {
		
			String Expected = "";
			String objprop = Obj.toString().split("->")[1].replace(":", "(\"");
			String objfinal =objprop.substring(0, objprop.length()-1)+"\")]";
			if(Obj.isDisplayed())
			{
				Expected = "Value '" + value + "' should be entered in ' " + Objectname + " ' textbox";
				//Expected = "Value" + value + " should be entered on '[By." + Obj.toString().split("->")[1] + "' successfully";
				//Obj.sendKeys(value);
				JavascriptExecutor myExecutor=((JavascriptExecutor) Page_Object_Component.browser);
				myExecutor.executeScript("arguments[0].value='"+value+"';", Obj);
				String Actual = "Value '" + value + "'  entered in ' " + Objectname + " ' textbox.";
				Reports.writeOutput(Expected , Actual , "Pass");
			}	
			else{
				Expected = "";
				String Actual = "operation failed";
				Reports.writeOutput(Obj.toString(), Actual, "Fail");
			}
			
		}
		
		

		
		public static void selectValue(WebElement Obj, String value, String option,String Objectname) throws IOException{
			
			String objprop = Obj.toString().split("->")[1].replace(":", "(\"");
			String objfinal =objprop.substring(0, objprop.length()-1)+"\")]";
			String Expected =""; //"value " + value + " should be selected from listbox '[By." + objfinal + "' successfully";
			//String Expected = "value" + value + " should be selected from listbox '[By." + Obj.toString().split("->")[1] + "' successfully";
			Select listBox = new Select(Obj);
			if(Obj.isDisplayed())
			{
				switch (option)
				{
				case "index":
					listBox.selectByIndex(Integer.parseInt(value));
					break;
				case "text":
					listBox.selectByVisibleText(value);
					break;
				default :
					listBox.selectByValue(value);
					break;
				
				}
				
				Expected = "Value '" + value + "' should be selected from listbox '"+Objectname+"' ";
				String Actual = "Value '" + value + "' selected from listbox '"+Objectname+"' ";
				Reports.writeOutput(Expected , Actual , "Pass");
			}	
			else{
				String Actual = "select operation failed";
				Reports.writeOutput(Expected, Actual, "Fail");
			}
			
		}
		public static void captureScreenshot( String fileName) throws IOException{

			TakesScreenshot screenshot = (TakesScreenshot) Page_Object_Component.browser;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			String savePath = Reports.scrFolder + "//"+fileName + new SimpleDateFormat("_dd_MM_yyyy_HH_mm_ss").format(Calendar.getInstance().getTime()).toString() + ".png";
			FileUtils.copyFile(screenshotAs, new File(savePath));
			Reports.writeOutput(fileName+"Screenshot should be taken", fileName+"Screenshot taken", "Done",savePath);
		}
		
		//WebTable Operations added by AHMES31 --- on 14/12/2015---------Start 
		public static String GetCellData(WebElement webtable,int webtablerow,int webtablecolumn)
		{
			
			//IMPORTANT :the data always begins from row = 3 (table size =3) as there may be a possibility that row 2 is unused
			String returntext="";
			List<WebElement> rows=webtable.findElements(By.tagName("tr"));
			List<WebElement> cols=rows.get(webtablerow).findElements(By.tagName("td"));
			if(cols.size()>0)
				{
				 returntext=cols.get(webtablecolumn).getText();
				}
			return returntext;
		}
		
		public static int GetRowCount(WebElement webtable)
		{
			
			//IMPORTANT :the data always begins from row = 3 (table size =3) as there may be a possibility that row 2 is unused
			List<WebElement> rows=webtable.findElements(By.tagName("tr"));
			return rows.size();
			
		}
		
		public static int GetColCount(WebElement webtable,int webtablerow)
		{
			
			//IMPORTANT :the data always begins from row = 3 (table size =3) as there may be a possibility that row 2 is unused
			List<WebElement> rows=webtable.findElements(By.tagName("tr"));
			List<WebElement> cols=rows.get(webtablerow).findElements(By.tagName("td"));
			return cols.size();
			
		}
		
		
		public static void SelectCellData(WebElement webtable,int webtablerow,int webtablecolumn)
		{
			//IMPORTANT :the data always begins from row = 3 (table size =3) as there may be a possibility that row 2 is unused
			List<WebElement> rows=webtable.findElements(By.tagName("tr"));
			List<WebElement> cols=rows.get(webtablerow).findElements(By.tagName("td"));
			cols.get(webtablecolumn).click();
			
		}
		
		//Getting the childitem of particular cell by tag name 
		public static WebElement ChildCellData(WebElement webtable,int webtablerow,int webtablecolumn,String tagname,int index)
		{
			//IMPORTANT :the data always begins from row = 3 (table size =3) as there may be a possibility that row 2 is unused
			List<WebElement> rows=webtable.findElements(By.tagName("tr"));
			List<WebElement> cols=rows.get(webtablerow).findElements(By.tagName("td"));
			List<WebElement> childitems=cols.get(webtablecolumn).findElements(By.tagName(tagname));
			
//			if((tagname.equalsIgnoreCase("img"))==true){
//				List<WebElement> childitems1=cols.get(webtablecolumn).findElements(By.tagName("a"));
//				List<WebElement> childitems2=cols.get(webtablecolumn).findElements(By.tagName(tagname));
//				return childitems2.get(index);
//			}
//			else
//			{
			return childitems.get(index);
//			}
			
		}
		
		//Getting the childitem of particular cell by id 
		public static WebElement ChildCellDataById(WebElement webtable,int webtablerow,int webtablecolumn,String idno,int index)
		{
			//IMPORTANT :the data always begins from row = 3 (table size =3) as there may be a possibility that row 2 is unused
			List<WebElement> rows=webtable.findElements(By.tagName("tr"));
			List<WebElement> cols=rows.get(webtablerow).findElements(By.tagName("td"));
			List<WebElement> childitems=cols.get(webtablecolumn).findElements(By.id(idno));
			return childitems.get(index);
			
		}
		
		//WebTable Operations added by AHMES31 --- on 14/12/2015---------End
		
		public static void captureFailedScreenshot(String savePath, String Message) throws IOException{
			TakesScreenshot screenshot = (TakesScreenshot) Page_Object_Component.browser;
			File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
			savePath = savePath +"//Failed_Screen" + new SimpleDateFormat("_dd_MM_yyyy_HH_mm_ss").format(Calendar.getInstance().getTime()).toString() + ".png";
			FileUtils.copyFile(screenshotAs, new File(savePath));
			Reports.writeOutput("Operation on the object should be successfull",Message.split("}")[0], "Fail", savePath);
		}
		
		public boolean validate(String expected,String actual){
			
			if(expected.equals(actual)){
				
				return true;
			}
			else
				
			{
				return false;
			}
		}
		
		public  static void updateExcel(String columnname,int row,String Data) throws IOException
		{
			String Expected = "Table cell should be updated with value '" +Data+ "' successfully";
			datatable = new Xls_Reader(Utility_Component.readValue("TestData_Path"));
			//strDataSheetName=Scenario_Component.strDataSheetName; // All the columns which will have to be updated should be in "Data" sheet
			boolean cellData = datatable.setCellData(Scenario_Component.strDataSheetName, columnname, row, Data);
			String Actual;
			if(cellData)
			{ 
				Actual = "Table cell updated with value '" +Data+ "'";
				Reports.writeOutput(Expected, Actual, "Pass");
			}
			else
			{
				
				Actual = "The column name '" + columnname + "' Does not exist in the datasheet '" + Scenario_Component.strDataSheetName + "'";
				Reports.writeOutput(Expected, Actual, "Fail");
				Page_Object_Component.browser.quit();
			}
			
			
		}
		
		public static void switchToFrame(WebElement Obj,String  Objectname) throws IOException
		
		{
			String Expected = "";
			String Actual="";
			if(Obj.isDisplayed())
			{
				String objprop = Obj.toString().split("->")[1].replace(":", "(\"");
				String objfinal =objprop.substring(0, objprop.length()-1)+"\")]";
				Expected = "Control should move to the frame with property "+ "[By." + objfinal + " 'successfully";
				Actual = "Control  moved to the frame with property "+ "[By." + objfinal + " 'successfully";
				Page_Object_Component.browser.switchTo().frame(Obj);
				
				//WebDriverWait mswait = new WebDriverWait(Page_Object_Component.browser,30);
				//mswait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(Obj));
							
				Reports.writeOutput(Expected , Actual , "Pass");
			}
			
		}
		
		public static void switchToWindow() throws IOException
		{
			String Expected = "";
			String Actual="";
			String childwindow = "";
			Set<String> windowHandles = Page_Object_Component.browser.getWindowHandles();
			for(String window:windowHandles)
			{
				if(window.equals(Page_Object_Component.parentwindow) == false)
				{
					childwindow = window;
					break;
				}
			}
			if(childwindow.isEmpty() == false)
			{
				Expected = "Control should move to the window with id "+ childwindow + "' successfully";
				Actual = "Control  moved to window with id '"+  childwindow + "' successfully";
				Page_Object_Component.browser.switchTo().window(childwindow);
				Reports.writeOutput(Expected , Actual , "Pass");
				
			}
				else
			{
				Expected = "Control should move to the window with id "+ childwindow + "' successfully";
				Actual = "Control cant be moved to window with id '"+  childwindow + "' successfully as the window cant be found";
				Reports.writeOutput(Expected , Actual , "Fail");
			}
			
		}
		
		public static void  handleAlert(String value) throws IOException{
			
			/*try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			try{
				
					WebDriverWait mswait = new WebDriverWait(Page_Object_Component.browser,20);
					mswait.until(ExpectedConditions.alertIsPresent());
					Alert alert = Page_Object_Component.browser.switchTo().alert();
					
					if(value.equalsIgnoreCase("OK"))
					{
						alert.accept();
						Reports.writeOutput("Alert box should be  present ", "Alert box   present and clicked on OK" , "Pass");
					}
					else
						{
						alert.dismiss();
						Reports.writeOutput("Alert box should be  present" , "Alert box  present and clicked on CANCEL" , "Pass");
						}
				}
			catch(Exception e)
			{
				Reports.writeOutput("Alert box should be  present" , "Alert box is not present" , "Fail");
			}
			
			
		}
		
		public static void keyboardactions(WebElement Obj) throws IOException
		{
		if(Obj.isDisplayed())
			{
			Actions action = new Actions(Page_Object_Component.browser);
			action.moveToElement(Obj).click().perform();
			Reports.writeOutput("Click operation should be done", "Click operation done successfully" , "Pass");
			}
		}
		
		public static void fnHighlightMe(WebDriver driver,WebElement element) throws InterruptedException{
			  //Creating JavaScriptExecuter Interface
			   JavascriptExecutor js = (JavascriptExecutor)driver;
			   for (int iCnt = 0; iCnt < 3; iCnt++) {
			      //Execute javascript
			         js.executeScript("arguments[0].style.border='4px groove green'", element);
			         Thread.sleep(1000);
			         js.executeScript("arguments[0].style.border=''", element);
			   }
		}
		
		public static void sendMail(String to,String from,String cc) throws IOException{    
		      String host = "outlook.015d.mgd.msft.net";//or IP address
		     //Get the session object  
		      Properties properties = System.getProperties();  
		      properties.setProperty("mail.smtp.host", host);  
		      
		      Session session = Session.getDefaultInstance(properties,null);
		      
		      try{  
		          MimeMessage message = new MimeMessage(session);  
		          message.setFrom(new InternetAddress(from));
		          message.addRecipients(Message.RecipientType.TO,InternetAddress.parse(to));  
		          if(cc!=null)
		          message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
		          message.setSubject("Execution_Report");
		          // Create the message part 
		          BodyPart messageBodyPart = new MimeBodyPart();

		          // Fill the message
		          messageBodyPart.setText("PFA for execution report");
		          
		          // Create a multipar message
		          Multipart multipart = new MimeMultipart();

		          // Set text message part
		          multipart.addBodyPart(messageBodyPart);

		          // Part two is attachment
		          messageBodyPart = new MimeBodyPart();
		          String filename = Reports.scrFolder+"//Report.html";
		          DataSource source = new FileDataSource(filename);
		          messageBodyPart.setDataHandler(new DataHandler(source));
		          messageBodyPart.setFileName("Report.html");
		          multipart.addBodyPart(messageBodyPart);

		          // Send the complete message parts
		          message.setContent(multipart );
		          Transport.send(message);  
		          
		         // System.out.println("message sent successfully....");  
		   
		       }catch (MessagingException mex) {mex.printStackTrace();}  
		  
		 }
		
		
		//new functions 
		
		public static void writeFail() throws IOException
		{
			Reports.writeOutput("Object should be Present", "Object Not Present", "Fail");
		}
		
		//Checks if the element is present or not on the page
		public static boolean VerifyObject_Exists(WebElement obj)
		{
			try{
				
				obj.isDisplayed();
				return true;
				
				
			}
			catch(NoSuchElementException e) {
					return false;
				
			 }
			}
		
		
		//Waits
		
		public static void Wait(int timeInSeconds) throws InterruptedException
		{
			Thread.sleep(timeInSeconds*1000);
//			//sPage_Object_Component.browser.manage().timeouts().implicitlyWait(timeInSeconds,TimeUnit.SECONDS);
		}
		
		//Wait Until
		
		public static void WaitUntil(WebElement obj) throws InterruptedException
		{
			WebDriverWait mswait = new WebDriverWait(Page_Object_Component.browser,50);
			mswait.until(ExpectedConditions.visibilityOf(obj));
		}
		
		public static void WaitUntilElementToBeClickable(WebElement obj) throws InterruptedException
		{
			WebDriverWait mswait = new WebDriverWait(Page_Object_Component.browser,30);
			mswait.until(ExpectedConditions.elementToBeClickable(obj));
		}
		
		public static void ScrollByPixel() {
			JavascriptExecutor js = (JavascriptExecutor) Page_Object_Component.browser;
			js.executeScript("window.scrollBy(0,1000)");
			//x-pixels is the number at x-axis, it moves to the left if number is positive and it move to the right if number is negative .y-pixels is the number at y-axis, it moves to the down if number is positive and it move to the up if number is in negative .
		}
		public static void ScrollByvisibleElement(WebElement obj) {
			JavascriptExecutor js = (JavascriptExecutor) Page_Object_Component.browser;
			js.executeScript("arguments[0].scrollIntoView();", obj);
		}
		public static void Scrolltobottom(WebElement obj) {
			JavascriptExecutor js = (JavascriptExecutor) Page_Object_Component.browser;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		}
		//The Desired Capabilities class will help to set an environment to define the behaviour of the browser/environment on which the test can be executed.


		
		
		
}
