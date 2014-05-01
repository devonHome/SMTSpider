package com.siliconmtn.srpproject;

import java.util.ArrayList;
import java.util.List;

import com.siliconmtn.srpproject.connection.HttpURLManager;
import com.siliconmtn.srpproject.parse.Parser;

/****************************************************************************
 * <b>Title</b>: SMTHttpURL.javaIncomingDataWebService.java
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
 * @since 3:19:13 PM
 *        <p/>
 *        <b>Changes: </b>
 ****************************************************************************/

public class SMTSiteURL {

	private HttpURLManager httpMangr = null;
	private String loginURL = "http://www.siliconmtn.com/login";
	private String sitePage = null;
	private Parser parser = null;
	private String password = "Srcp2014nice!";
	private String email = "devon@siliconmtn.com";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SMTSiteURL smt = new SMTSiteURL();

		smt.init();
		
		smt.runSpider();

	}

	/**
	 * initializes class to be used
	 */
	public void init() {

		httpMangr = new HttpURLManager();
		parser = new Parser();
	
		try {
			sitePage = httpMangr.getPage(loginURL);
			System.out.println(sitePage);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void runSpider(){
		List<String> params;
		
		//get the form then get it's parameters
		String form = parser.getForm(sitePage);
		params = parser.getFormParams(form);
		System.out.println(params);
		
		parser.getPostParams(params);
		//loop through list and url encode them
		
		//create stringbuilder and make it a string at the end
		
		//pass string to post method
//		List<String>updatedParams = new ArrayList<String>();
//		
//		// check parameters for email and password fields
//		for (String param : params) {
//			if (param.indexOf("name='emailAddress", 0) != -1) {
//				param += " value='" + email + "'";
//			}
//			if (param.indexOf("name='password", 0) != -1) {
//				param += " value='" + password + "'";
//
//			}
//			// add updated parameters
//			updatedParams.add(param);
//
//		}
//
//		for (String tag : updatedParams) {
//			System.out.println(tag);
//		}
	}

}
