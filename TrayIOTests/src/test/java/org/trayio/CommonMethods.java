package test.java.org.trayio;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

public class CommonMethods {
	
	/**
     *  Method Name: execute
     *  Purpose: This method will read the URL from the properties file and make an HTTP Post call and return the response. 
     */
	public HttpResponse execute(StringEntity params) throws ClientProtocolException, IOException {
		Properties prop = new Properties();
		HttpResponse response = null;
		HttpPost request = null;
		HttpClient httpClient = null;
		try {
			
			// load a properties file
			prop.load(CommonMethods.class.getResourceAsStream("/config.properties")); 
			request = new HttpPost(prop.getProperty("BASE_URL") + prop.getProperty("CONNECTOR"));
		    httpClient = HttpClientBuilder.create().build();
	       	request.addHeader("content-type", "application/x-www-form-urlencoded");
	        request.setEntity(params);
	        
	        response = httpClient.execute(request); 
	        
	    	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
    }
    
	/**
     *  Method Name: returnJSONBody
     *  Purpose: This method will parse the response string and extract the body from it.
     *  		 It will return a JSON Object 
     */
	public JSONObject returnJSONBody(String result) {
    	JSONArray jsonArray = new JSONArray(result);
    	return jsonArray.getJSONObject(0).getJSONObject("body");   	
    }
    
	/**
     *  Method Name: returnJSONHeader
     *  Purpose: This method will parse the response string and extract the header from it.
     *  		 It will return a JSON Object 
     */
    public JSONObject returnJSONHeader(String result) {
    	JSONArray jsonArray = new JSONArray(result);
    	return jsonArray.getJSONObject(0).getJSONObject("header");
    	
    }
    
    /**
     *  Method Name: checkAPIError
     *  Purpose: This method will check if the Response has an error.
     */
    public void checkAPIError(String result){  	
    	JSONObject jsonHeader = returnJSONHeader(result);	
		assertTrue("API ERROR",jsonHeader.length()==0);
    }
}
