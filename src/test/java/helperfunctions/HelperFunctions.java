package helperfunctions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Reporter;


public class HelperFunctions {
	private static final Logger logger = LogManager.getLogger(HelperFunctions.class);
	
	
	public static JSONObject readJson(String jsonFilePath) {
		try {
			return readJsonFromFile(jsonFilePath);
		}
		catch(FileNotFoundException e) {
			logException(e);
		}
		catch (IOException e) {
			logException(e);
		} 
		catch (ParseException e) {
			logException(e);
		}
		return null;
	}
	
	public static JSONObject readJsonFromFile(String jsonFilePath) 
			throws FileNotFoundException, IOException, ParseException{
		JSONParser jsonParser = new JSONParser();
		FileReader fileReader = new FileReader(jsonFilePath);
		JSONObject jsonObject = (JSONObject)jsonParser.parse(fileReader);
		return jsonObject;
	}
	
	public static void logException(Exception e) {
		logger.error("Json File is not parsed successfully." + e.getMessage());
		e.printStackTrace();
	}
	
	public static void logToReportAndLog4j(String message) {
		logger.info(message);
		Reporter.log("INFO: " + message);
	}
	
	public static void logToReportAndLog4j(String message, Throwable t) {
		logger.error(message, t);
		Reporter.log("ERROR: " + message + "; "+ t.getMessage());
	}
}
