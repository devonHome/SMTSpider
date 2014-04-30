package com.siliconmtn.srpproject.connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;

/****************************************************************************
 * <b>Title</b>: SocketConnection.javaIncomingDataWebService.java
 * <p/>
 * <b>Project</b>: SMTSpiderRAMDataFeed
 * <p/>
 * <b>Description: </b> <b>Copyright:</b> Copyright (c) 2014
 * <p/>
 * <b>Company:</b> Silicon Mountain Technologies
 * <p/>
 * 
 * @author Devon
 * @version 1.0
 * @since 8:26:38 AM
 *        <p/>
 *        <b>Changes: </b>
 ****************************************************************************/

public class ServerConnection {

	private Socket clientSocket = null;
	private StringBuilder data = null;

	/**
	 * makes connection to host and returns response
	 */
	public StringBuilder connection(String host, int port, String url) {

		try {

			InetAddress addr = InetAddress.getByName(host);
			clientSocket = new Socket(addr, port);
			// logger
			this.writeGET(url);
			this.readSocket();

		} catch (UnknownHostException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return data;
	}

	/**
	 * Will write a request to current socket using GET method
	 */
	protected void writeGET(String request) {
		// Create print stream so can write to source/data/server
		PrintStream clientPrinter = null;

		try {
			clientPrinter = new PrintStream(clientSocket.getOutputStream());
			clientPrinter.println("GET http://" + request);
			clientPrinter.println(""); // Make sure to end request here
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Uses a socket for connection
	 */
	protected void writeToSocket() {
		try {

			// Send headers
			BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(
					clientSocket.getOutputStream(), "UTF-8"));

			wr.write("GET /login HTTP/1.0rn");
			wr.write("Content-Type: application/x-www-form-urlencodedrn");
			wr.write("rn");

			StringBuilder parameters = new StringBuilder();
			// set parameters for form
			parameters.append("actionId=c0a8021ebdcce96eb5b6dae8874aaa46");
			parameters.append("&requestType=reqBuild");
			parameters.append("&pmid=c0a80241f25605d1f95007fe3b1d785e");
			parameters.append("&destUrl=");
			parameters.append(URLEncoder.encode("/login?logOff=true", "UTF-8"));
			parameters.append("&reqType=");
			parameters.append("&emailAddress=");
			parameters.append(URLEncoder
					.encode("devon@siliconmtn.com", "UTF-8"));
			parameters.append("&password=");
			parameters.append(URLEncoder.encode("Srcp2014nice!", "UTF-8"));
			parameters.append("&remember=1");
			parameters.append("&smt_formValidated=40371");

			// Send parameters
			wr.write(parameters.toString());
			wr.flush();

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error Occurred. Please check your resources");
			e.printStackTrace();
		}

	}

	/**
	 * Will read input coming from server
	 */
	protected void readSocket() {

		data = new StringBuilder();

		try {
			InputStream myReader = clientSocket.getInputStream();
			int c = 0;

			while ((c = myReader.read()) > -1) {
				data.append((char) c);
			}
			myReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * connects to server using urlConnection send post request
	 */
	public void sendPost() throws Exception {

		String url = "http://www.siliconmtn.com/login";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setUseCaches(false);
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
		con.setRequestProperty("Cookie", "c0a8010f:0");
		
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
		parameters.append("&smt_formValidated=20737");

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

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + parameters);
		System.out.println("Response Code : " + responseCode);

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

}
