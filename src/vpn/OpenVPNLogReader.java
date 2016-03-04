package vpn;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * This class is made to read a certain type of log and filter specific fields.
 * 
 *
 */


public class OpenVPNLogReader {

	final String absolutePathOfLog;
	
	
	public OpenVPNLogReader(final String absolutePath) {
		absolutePathOfLog = absolutePath;
	}

	/**
	 * Extracts ALL the row with the information from the vpn log;
	 *
	 * @return list of Object[], suites for a Jtable defaultmodel
	 */
	public List<Object[]> getListOfVpnInformationRows()
	{
		//Open the file on disk.
		File logFile = new File(absolutePathOfLog);
		//Create an arraylist to store the results.
		ArrayList<Object[]> listOfInformationRows = new ArrayList<Object[]>();
		
		//Open it as a stream so we can go line by line.
		try {
			
			final BufferedReader bReader = new BufferedReader(new java.io.FileReader(logFile)); 
		    String line;
		    boolean extractNextLines = false;
		    
		    //The line preceding the FIRST client we want ALWAYS IS "Common Name,Real Address,Bytes Received,Bytes Sent,Connected Since"
		    while ((line = bReader.readLine()) != null) {
		        
		    	if(line.startsWith("ROUTING"))
		    	{ //When we see the line starts with routing we've passed all clients.
		     		extractNextLines = false;
		    	}
		    	else if(extractNextLines)
		        {
		    		//When the line is not starting with ROUTING and we've passed the header;
		        	final Object[] extractedData = extractVPNDataFromLine(line);
		        	listOfInformationRows.add(extractedData);
		        }
		        else if(line.startsWith("Common"))
		        {
		        	extractNextLines = true;
		        }
		    }

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listOfInformationRows;
	}

	//format client1,192.168.2.5:49493,11122,4509,Fri Jan 22 14:05:38 2016
	private Object[] extractVPNDataFromLine(final String line) {
		String[] data = line.split(",");
		
		//Implicit cast to Object;
		return data;
	}
}
