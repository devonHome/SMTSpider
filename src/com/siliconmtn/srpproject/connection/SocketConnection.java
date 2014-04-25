package com.siliconmtn.srpproject.connection;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
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

public class SocketConnection {

	private Socket clientSocket = null;
	private StringBuilder data = null;

	/**
	 * makes connection to host and returns response
	 */
	public StringBuilder connection(String host, int port, String url) {

		try {
			
			InetAddress addr = InetAddress.getByName(host);
			clientSocket = new Socket(addr, port);
			System.out.println("Connection success");
			this.writeSocket(url);
			this.readSocket();

		} catch (UnknownHostException e) {
			System.out.println("Unknown host. Please check host name");
			e.printStackTrace();

		} catch (IOException e) {
			System.out.println("Error Occurred. Please check your resources");
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
	 * Will write a request to current socket
	 */
	protected void writeSocket(String request) {
		// Create print stream so can write to source/data/server
		PrintStream clientPrinter = null;

		try {
			clientPrinter = new PrintStream(clientSocket.getOutputStream());
			clientPrinter.println("GET http://" + request);
			clientPrinter.println(""); // Make sure to end request here
			System.out.println("Successfully got " + request);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	protected void writeToSocket(String value1, String value2) {
		try {
			StringBuilder parameters = new StringBuilder();
			parameters.append(URLEncoder.encode("actionId", "UTF-8"));
			parameters.append("=");
			parameters.append(URLEncoder.encode("c0a8021ebdcce96eb5b6dae8874aaa46", "UTF-8"));
			parameters.append("&");
			parameters.append(URLEncoder.encode(value1, "UTF-8"));
			parameters.append("&");
			parameters.append(URLEncoder.encode("password", "UTF-8"));
			parameters.append("=");
			parameters.append(URLEncoder.encode(value2, "UTF-8"));

			// Send headers

			BufferedWriter wr =

			new BufferedWriter(new OutputStreamWriter(
					clientSocket.getOutputStream(), "UTF8"));

			wr.write("POST /login HTTP/1.0rn");
			wr.write("Content-Type: application/x-www-form-urlencodedrn");
			wr.write("Content-Length: " + parameters.toString().length() + "rn");

			wr.write("rn");

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
			data.append("value='devon@siliconmtn.com'");
			myReader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
