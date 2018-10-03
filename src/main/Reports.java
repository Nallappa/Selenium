
package main;

import java.awt.Desktop;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Reports {
	
	
	static String Htmlpath;
	static String newHtmlpath;
	static Xls_Reader datatable;
	static int counter = 0;
	static int passCount = 0;
	static int failCount = 0;
	static int failedFlag = 0;
	static int iterationnumber = 0;
	static int row;
	static Date startDate = null;
	static Date endDate = null;
    static String scrFolder;
    static ExtentReports report;
	public static ExtentTest logger;

	
	public static void createfolder() throws IOException{
			scrFolder = Utility_Component.readValue("Report_Path") + "Report" + new SimpleDateFormat("_dd_MM_yyyy_HH_mm_ss").format(Calendar.getInstance().getTime()).toString();
			datatable = new Xls_Reader(Utility_Component.readValue("TestData_Path"));
		    new File(scrFolder).mkdir();
	}
	
	public static void iterationCount(){
		int rowcount = datatable.getRowCount("Data");
		for( row=1; row<= rowcount; row++)
		{
			if(datatable.getCellData("Data", "Run", row).equalsIgnoreCase("Y"))
			{
				counter++;
			}
		}
	}
	
	public static void initialReport(String title){
		
		try{
		createfolder();
		iterationCount();
		newHtmlpath=scrFolder + "\\report_new.html";
		report = new ExtentReports(newHtmlpath);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void startTestCaseReport(String description) throws IOException{
		iterationnumber++;
		logger = report.startTest(description);
		failedFlag = 0;
	}
	
	public static void writeOutput(String Expected, String Actual, String Status, String savePath) throws IOException{
		if(Status.equalsIgnoreCase("done"))
			{
			logger.log(LogStatus.INFO, Actual + logger.addScreenCapture(savePath));

			}
		else
		{
			failedFlag++;
			logger.log(LogStatus.FAIL, Actual + logger.addScreenCapture(savePath));
		}
		
	}
	
	
	public static void writeOutput(String Expected, String Actual, String Status) throws IOException{
		
		if(Status.equalsIgnoreCase("pass"))
			{
				logger.log(LogStatus.PASS, Actual);
			}
		else
			{
				failedFlag++;
				logger.log(LogStatus.FAIL, Actual);
			}
	}
	
	public static void endTestCaseReport(String strDataSheetName, int row) throws IOException{
	
		if(failedFlag == 0)
		{
			passCount++;
			report.endTest(logger);
		}
		else
		{
			failCount++;
		}
	}
	
	public static void finalHtml() throws IOException{
		
	    endDate = new Date();
	   
	    report.flush();
	    File newhtmlFile = new File(newHtmlpath);
	    Desktop.getDesktop().browse(newhtmlFile.toURI()); 
	}
	
	
	public static void writeOutput(String Comment) throws IOException{
			logger.assignCategory(Comment);

	}


}
