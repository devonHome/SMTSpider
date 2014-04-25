package com.siliconmtn.srpproject;

import java.util.List;

import com.siliconmtn.srpproject.connection.SocketConnection;
import com.siliconmtn.srpproject.parse.Parser;

/****************************************************************************
 * <b>Title</b>: SMTSite.javaIncomingDataWebService.java
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
 * @since 4:25:25 PM
 *        <p/>
 *        <b>Changes: </b>
 ****************************************************************************/

public class SMTSite {

	// should have parser, connection, and file variables
	private static final String HOST_NAME = "www.siliconmtn.com";
	private static final int PORT = 80;
	private SocketConnection sckConn = null;
	private Parser parser = null;
	private StringBuilder strbuild = null;
	private List<String> parsedList = null;
	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SMTSite smt = new SMTSite();

		smt.initialize();
	}

	/**
	 * will set up class so it can be used
	 */
	public void initialize() {
		String path = "www.siliconmtn.com/login";
		System.out.println("Connecting to " + HOST_NAME + " on port " + PORT);
		sckConn = new SocketConnection();
		strbuild = sckConn.connection(HOST_NAME, PORT, path);
		System.out.println(strbuild);
		parser = new Parser(strbuild);
		parsedList = parser.getTableData();
		for (String pl : parsedList) {
			System.out.println("1" + pl);
		}
	}
}
