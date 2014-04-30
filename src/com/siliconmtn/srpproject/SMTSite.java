package com.siliconmtn.srpproject;

import com.siliconmtn.srpproject.connection.ServerConnection;
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
	private final String hostName = "www.siliconmtn.com";
	private final int port = 80;
	private ServerConnection svrConn = null;
	private StringBuilder strbuild = null;
	private Parser parser = null;
	
	/**	
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SMTSite smt = new SMTSite();

		smt.initialize();
		smt.run();
	}

	/**
	 * will set up class so it can be used
	 */
	public void initialize() {
		
		String path = "www.siliconmtn.com/login";
		System.out.println("Connecting to " + hostName + " on port " + port);
		
		svrConn = new ServerConnection();
		strbuild = svrConn.connection(hostName, port, path);

	}
	
	/**
	 * will handle business logic required to spider site
	 */
	public void run(){
		
		parser = new Parser();
		
		String form = parser.getForm(strbuild.toString());
		System.out.println(form);
		
		this.getFormParams(form);
		//insert values into it
		
	}
	
	/**
	 * will get all form parameters from page form
	 */
	public void getFormParams(String form){
		

	}
	
}
