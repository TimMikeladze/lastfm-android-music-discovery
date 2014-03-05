
package com.mikeladze.musicdiscovery.http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class RestClient {
	
	private static AsyncHttpClient client = new AsyncHttpClient();
	
	public static void get(String url, AsyncHttpResponseHandler responseHandler) {
		client.get(url, responseHandler);
	}
}
