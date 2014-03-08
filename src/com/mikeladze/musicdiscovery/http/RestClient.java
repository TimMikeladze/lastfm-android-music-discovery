
package com.mikeladze.musicdiscovery.http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * The RestClient is used to access RESTful APIs in an async manner.
 * 
 * @author Tim Mikeladze
 */
public class RestClient {
	
	private static AsyncHttpClient client = new AsyncHttpClient();
	
	/**
	 * Gets the data at given url.
	 * 
	 * @param url the url
	 * @param responseHandler the response handler
	 */
	public static void get(String url, AsyncHttpResponseHandler responseHandler) {
		client.get(url, responseHandler);
	}
}
