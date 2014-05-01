package com.siliconmtn.srpproject.connection;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/****************************************************************************
 * <b>Title</b>: HttpURLManager.javaIncomingDataWebService.java
 * <p/>
 * <b>Project</b>: SpiderProjectRAMDataFeed
 * <p/>
 * <b>Description: </b> <b>Copyright:</b> Copyright (c) 2014
 * <p/>
 * <b>Company:</b> Silicon Mountain Technologies
 * <p/>
 * 
 * @author Devon
 * @version 1.0
 * @since 3:15:50 PM
 *        <p/>
 *        <b>Changes: </b>
 ****************************************************************************/

public class HttpURLManager {

	private List<String> cookies = null;
	private HttpURLConnection con = null;

	public String getPage(String url) throws Exception {

		URL obj = new URL(url);
		con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		// act like a browser
		con.setUseCaches(false);
		con.setRequestProperty("User-Agent",
				"Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:28.0) Gecko/20100101 Firefox/28.0");
		con.setRequestProperty("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		
		//check for any cookies
		if (cookies != null) {
			for (String cookie : this.cookies) {
				con.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
			}
		}
		
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// Get the response cookies
		setCookies(con.getHeaderFields().get("Set-Cookie"));

		return response.toString();

	}

	/**
	 * connects to server using urlConnection send post request
	 */
	public void sendPost() throws Exception {

		String url = "http://www.siliconmtn.com/login";
		URL obj = new URL(url);
		con = (HttpURLConnection) obj.openConnection();

		con.setUseCaches(false);
		CookieHandler.setDefault(new CookieManager());
		// add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept",
				"text/html, application/xhtml+xml,application/xml;q=0.9,*/*;q=0.");
		con.setRequestProperty("Accept-Encoding", "deflate");
		con.setRequestProperty("Connection", "keep-alive");
		con.setRequestProperty("Host", "www.siliconmtn.com");
		con.setRequestProperty("Referer", "	http://www.siliconmtn.com/login");
		con.setRequestProperty("User-Agent",
				"Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:28.0) Gecko/20100101 Firefox/28.0");

		StringBuilder parameters = new StringBuilder();
		// set parameters for form
		parameters.append("actionId=c0a8021ebdcce96eb5b6dae8874aaa46");
		parameters.append("&requestType=reqBuild");
		parameters.append("&pmid=c0a80241f25605d1f95007fe3b1d785e");
		parameters.append("&destUrl=");
		parameters.append(URLEncoder.encode("/login?logOff=true", "UTF-8"));
		parameters.append("&reqType=");
		parameters.append("&emailAddress=");
		parameters.append(URLEncoder.encode("devon@siliconmtn.com", "UTF-8"));
		parameters.append("&password=");
		parameters.append(URLEncoder.encode("Srcp2014nice!", "UTF-8"));
		parameters.append("&remember=1");
		parameters.append("&smt_formValidated=13326");

		con.setDoOutput(true);
		con.setDoInput(true);

		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		con.setRequestProperty("Content-Length",
				Integer.toString(parameters.toString().length()));

		con.setInstanceFollowRedirects(false);

		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(parameters.toString());
		wr.flush();
		wr.close();

		boolean redirect = false;

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + parameters);
		System.out.println("Response Code : " + responseCode);

		if (responseCode != HttpURLConnection.HTTP_OK) {
			if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP
					|| responseCode == HttpURLConnection.HTTP_MOVED_PERM
					|| responseCode == HttpURLConnection.HTTP_SEE_OTHER)
				redirect = true;
		}

		if (redirect) {

			// get redirect url from "location" header field
			String newUrl = con.getHeaderField("Location");

		}

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuilder sb = new StringBuilder();

		while ((inputLine = in.readLine()) != null) {
			sb.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(sb);

	}

	/**
	 * @return the cookies
	 */
	public List<String> getCookies() {
		return cookies;
	}

	/**
	 * @param cookies
	 *            the cookies to set
	 */
	public void setCookies(List<String> cookies) {
		this.cookies = cookies;
	}

}
