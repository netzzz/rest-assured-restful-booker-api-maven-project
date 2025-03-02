package helperfunctions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class HelperFunctions {
	private static final Logger logger = LogManager.getLogger(HelperFunctions.class);
	
	public static JsonPath convertRestAssuredResponseToJson(Response response) {
		JsonPath responseJson = response.jsonPath();
		return responseJson;
	}
	
	public static JSONObject readJsonFromFile(String jsonFilePath) {
		JSONObject jsonObject=null;
		
		try {
		JSONParser jsonParser = new JSONParser();
		FileReader fileReader = new FileReader(jsonFilePath);
		jsonObject = (JSONObject)jsonParser.parse(fileReader);
		}
		catch(FileNotFoundException e) {
			logger.error("Json File is not found: " + e.getMessage());
			e.printStackTrace();
		}
		catch (IOException e) {
			logger.error("Json File is not read successfully." + e.getMessage());
			e.printStackTrace();
		} 
		catch (ParseException e) {
			logger.error("Json File is not parsed successfully." + e.getMessage());
			e.printStackTrace();
		}
		
		return jsonObject;
	}
}
