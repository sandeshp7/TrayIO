
package test.java.org.trayio;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.http.HttpStatus;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class TrayIOTest  {
  
    @Rule
    public ErrorCollector collector = new ErrorCollector();
    static CommonMethods cm = null;
    static Properties prop = null;
    
    @BeforeClass
    public static void setUp() {
    	prop = new Properties();
    	cm = new CommonMethods();
    	
		try {
			prop.load(TrayIOTest.class.getResourceAsStream("/testdata.properties"));  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    /**
     *  Method Name: pingTest
     *  Purpose: To test if the connector is operating. 
     */
	@Test
    public void pingTest() throws IOException { 
        StringEntity params = new StringEntity(prop.getProperty("getUser"));
        assertEquals(cm.execute(params).getStatusLine().getStatusCode(), HttpStatus.SC_OK);       
    }
	
	/**
     *  Method Name: testGetUser
     *  Purpose: To test if get_user is returning a valid response to a valid input. 
     */
	@Test
    public void testGetUser() throws IOException {
		StringEntity params = new StringEntity(prop.getProperty("getUser"));
		String result = EntityUtils.toString(cm.execute(params).getEntity());
		
		cm.checkAPIError(result);
		JSONObject jsonResult = cm.returnJSONBody(result);
		
		collector.checkThat(jsonResult.getString("name"), equalTo("Ali Russell"));
		collector.checkThat(jsonResult.getString("company"), equalTo("tray.io"));
		collector.checkThat(jsonResult.getString("location"), equalTo("London, UK"));
		collector.checkThat(jsonResult.getString("twitter"), equalTo("https://twitter.com/alirussell"));
	
	}
	
	/**
     *  Method Name: testIsURL
     *  Purpose: To test if is_url is returning a valid response to a valid input. 
     */
	@Test
    public void testIsURL() throws  IOException {
		StringEntity params = new StringEntity(prop.getProperty("isURL_valid"));
		String result = EntityUtils.toString(cm.execute(params).getEntity());

		cm.checkAPIError(result);
		JSONObject jsonResult = cm.returnJSONBody(result);
		
		collector.checkThat(jsonResult.getBoolean("result"), equalTo(true));
		
		params =new StringEntity(prop.getProperty("isURL_invalid"));
		result = EntityUtils.toString(cm.execute(params).getEntity());

		jsonResult = cm.returnJSONBody(result);
		
		collector.checkThat(jsonResult.getBoolean("result"), equalTo(false));
	
	}
	
	/**
     *  Method Name: testIsEmail
     *  Purpose: To test if is_email is returning a valid response to a valid input. 
     */
	@Test
    public void testIsEmail() throws  IOException {
		StringEntity params = new StringEntity(prop.getProperty("isEmail_valid"));
		String result = EntityUtils.toString(cm.execute(params).getEntity());
	
		cm.checkAPIError(result);
		JSONObject jsonResult = cm.returnJSONBody(result);
		
		collector.checkThat(jsonResult.getBoolean("result"), equalTo(true));
		
		params =new StringEntity(prop.getProperty("isEmail_invalid"));
		result = EntityUtils.toString(cm.execute(params).getEntity());

		jsonResult = cm.returnJSONBody(result);
		
		collector.checkThat(jsonResult.getBoolean("result"), equalTo(false));
	
	}
	
	/**
     *  Method Name: testIsNumeric
     *  Purpose: To test if is_numeric is returning a valid response to a valid input. 
     */
	@Test
    public void testIsNumeric() throws  IOException {
		StringEntity params = new StringEntity(prop.getProperty("isNumeric_valid"));
		String result = EntityUtils.toString(cm.execute(params).getEntity());
		
		cm.checkAPIError(result);
		JSONObject jsonResult = cm.returnJSONBody(result);
		
		collector.checkThat(jsonResult.getBoolean("result"), equalTo(true));
		
		params = new StringEntity(prop.getProperty("isNumeric_invalid"));
		result = EntityUtils.toString(cm.execute(params).getEntity());

		jsonResult = cm.returnJSONBody(result);
		
		collector.checkThat(jsonResult.getBoolean("result"), equalTo(false));
	
	}
	
	/**
     *  Method Name: testIsGenericDomain
     *  Purpose: To test if is_generic_domain is returning a valid response to a valid input. 
     */
	@Test
    public void testIsGenericDomain() throws  IOException {
		StringEntity params = new StringEntity(prop.getProperty("isGenericDomain_valid"));
		String result = EntityUtils.toString(cm.execute(params).getEntity());
		
		cm.checkAPIError(result);
		JSONObject jsonResult = cm.returnJSONBody(result);
		
		collector.checkThat(jsonResult.getBoolean("result"), equalTo(true));
		
		params = new StringEntity(prop.getProperty("isGenericDomain_invalid"));
		result = EntityUtils.toString(cm.execute(params).getEntity());

		jsonResult = cm.returnJSONBody(result);
		
		collector.checkThat(jsonResult.getBoolean("result"), equalTo(false));
	
	}
	
	/**
     *  Method Name: testContains
     *  Purpose: To test if contains is returning a valid response to a valid input. 
     */
	@Test
    public void testContains() throws  IOException {
		StringEntity params = new StringEntity(prop.getProperty("isContains_valid"));
		String result = EntityUtils.toString(cm.execute(params).getEntity());
		
		cm.checkAPIError(result);
		JSONObject jsonResult = cm.returnJSONBody(result);
		
		collector.checkThat(jsonResult.getBoolean("result"), equalTo(true));
		
		params = new StringEntity(prop.getProperty("isContains_invalid"));
		result = EntityUtils.toString(cm.execute(params).getEntity());

		jsonResult = cm.returnJSONBody(result);
		
		collector.checkThat(jsonResult.getBoolean("result"), equalTo(false));
	
	}


}
