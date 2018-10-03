
package main;

import java.awt.AWTException;
import java.io.IOException;

import OR.UserDefined_Component;

public class Scenario_Component {
	public static final String TestApplicationName = "KLM AirLinesDemo";
	public static int TransactionNo = 1;
	//public static final String strDataSheetName = "Data";
	public static final String strDataSheetName = "TESTDATA"; 
	public static int row;
	public static String to; 
    public static String from; 
    public static String cc; 
	
  
	public static void execute() throws IOException, InterruptedException, AWTException
		{
	
			//Creating datatable object of Xls_Reader class
			
			Xls_Reader datatable = new Xls_Reader(Utility_Component.readValue("TestData_Path"));
			
			//Creating object of class UserDefined_Component
						
			Reports.initialReport(TestApplicationName);//This will generate the test report table in html file at the beginning of execution

			int rowCount = datatable.getRowCount(strDataSheetName);
			
			int TransactionCount = 3;
			
			String currTxn="";
			
			for(row=1; row<=rowCount; row++)
			  {
//				 for(row=1; row<=rowCount; row++)
				String status = datatable.getCellData(strDataSheetName, "RUN", row);
				if(status.equalsIgnoreCase("Y"))
					{
					Reports.startTestCaseReport(datatable.getCellData(strDataSheetName,"TestCaseNo", row));
					
					try
			 		{
						UserDefined_Component udc = new UserDefined_Component();
						String TransactionName = datatable.getCellData(strDataSheetName, "Transaction", row).trim();
						String initExecStatus=datatable.getCellData(strDataSheetName, "DR_Executionstatus", row).trim();
	//										
						if(TransactionName.equals("")==false && initExecStatus.equalsIgnoreCase("PASSED")==false)
	//										{				
								Reports.writeOutput("Transaction Name : "+ TransactionName );
								udc.Transaction(strDataSheetName,row,TransactionName);
								//Updates the status in the Excel sheet - for transation_i
								Operations_Component.updateExcel("DR_Executionstatus",row,"PASSED");
							 }						 		
		
					 catch(Exception e)
						{
				 		 	e.printStackTrace();
							String Message = e.getMessage();
							Operations_Component.updateExcel("DR_Executionstatus",row,"FAILED");
							break;
						}//End of try catch block
			
					Reports.endTestCaseReport(strDataSheetName,row);
					
					} //End of if
				
				if(row==rowCount)
				{
				Page_Object_Component.browser.quit();
				}
		
			 }//End of row{
			
			try
	 			{
					Reports.finalHtml();
	 			}
			catch(Exception ne)
				{
					Reports.startTestCaseReport("No tests to execute.");
					Reports.endTestCaseReport(strDataSheetName,row);
					Reports.finalHtml();
				}
		
				
	}//End of execute method

}








