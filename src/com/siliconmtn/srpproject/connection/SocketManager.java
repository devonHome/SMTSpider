package com.siliconmtn.srpproject.connection;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

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

public class SocketManager {

	private Socket clientSocket = null;
	private StringBuilder data = null;
	private List<String> cookies = null;

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
	 * Override method makes connection to host and returns response
	 */
	public StringBuilder connection(String host, int port, String url,
			List<String> formParams) {

		try {

			InetAddress addr = InetAddress.getByName(host);
			clientSocket = new Socket(addr, port);
			// logger
			this.writePost(formParams);
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
			clientPrinter.println("GET http://" + request + "HTTP/1.0\r\n");
			clientPrinter.println(""); // Make sure to end request here

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Uses a socket for connection
	 */
	protected void writePost(List<String> formParams) {
		try {

			// Send headers
			BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(
					clientSocket.getOutputStream(), "UTF-8"));

			wr.write("POST /login HTTP/1.0\r\n");
			wr.write("Content-Length: " + formParams.size() + "\r\n");
			wr.write("Content-Type: application/x-www-form-urlencoded\r\n");
			wr.write("\r\n");

			// Send parameters
			for (String param : formParams) {
				wr.write(param);
			}
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
	 * 
	 * @return
	 */
	public List<String> getCookies() {
		return cookies;
	}

	public void setCookies(List<String> cookies) {
		this.cookies = cookies;
	}

}
