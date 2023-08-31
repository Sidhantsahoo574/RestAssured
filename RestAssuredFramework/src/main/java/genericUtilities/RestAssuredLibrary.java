package genericUtilities;

import io.restassured.response.Response;

/**
 * Consist of methods of RestAssured
 * @author Sidhanta
 *
 */
public class RestAssuredLibrary {
	/**
	 * this method will return json data from corresponding response body
	 * @author Sidhanta
	 * @param response
	 * @param path
	 * @return
	 */

	public String getJsonData(Response response,String path)
	{
		String jsonData=response.jsonPath().get(path);
		return jsonData;
	}
}
