package com.siliconmtn.srpproject;

import java.util.ArrayList;
import java.util.List;

import com.siliconmtn.srpproject.connection.SocketManager;
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

public class SMTSiteSocket {

	// should have parser, connection, and file variables
	private final String hostName = "www.siliconmtn.com";
	private final int port = 80;
	private SocketManager sckMngr = null;
	private StringBuilder strbuild = null;
	private Parser parser = null;
	private String password = "Srcp2014nice!";
	private String email = "devon@siliconmtn.com";
	private String path = "www.siliconmtn.com/login";

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SMTSiteSocket smt = new SMTSiteSocket();
		
		 smt.initialize();
		 smt.run();
	}

	/**
	 * will set up class so it can be used
	 */
	public void initialize() {

		System.out.println("Connecting to " + hostName + " on port " + port);

		sckMngr = new SocketManager();
		strbuild = sckMngr.connection(hostName, port, path);
		parser = new Parser();
	}

	/**
	 * will get all form parameters from page form
	 */
	public void run() {
		List<String> params = new ArrayList<String>();
		List<String> updatedParams = new ArrayList<String>();

		String form = parser.getForm(strbuild.toString());
		System.out.println(form);
		params = parser.getFormParams(form);
		System.out.println();

		// check parameters for email and password fields
		for (String param : params) {
			if (param.indexOf("name='emailAddress", 0) != -1) {
				param += " value='" + email + "'";
			}
			if (param.indexOf("name='password", 0) != -1) {
				param += " value='" + password + "'";

			}
			// add updated parameters
			updatedParams.add(param);

		}

		for (String tag : updatedParams) {
			System.out.println(tag);
		}

		sckMngr = new SocketManager();
		strbuild = sckMngr.connection(hostName, port, path, updatedParams);
		System.out.println(strbuild);
	}

}
