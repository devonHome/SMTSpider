package com.siliconmtn.srpproject.parse;

import java.util.ArrayList;
import java.util.List;


/****************************************************************************
 * <b>Title</b>: Parser.javaIncomingDataWebService.java <p/>
 * <b>Project</b>: SMTSpiderRAMDataFeed <p/>
 * <b>Description: </b>
 * <b>Copyright:</b> Copyright (c) 2014<p/>
 * <b>Company:</b> Silicon Mountain Technologies<p/>
 * @author Devon
 * @version 1.0
 * @since 8:30:05 AM<p/>
 * <b>Changes: </b>
 ****************************************************************************/

public class Parser {

	
	/**
	 * Parses resource for just form tags
	 * @return
	 */
	public String getForm(String resource){
		String form = null;
		List<Integer> startForm = new ArrayList<Integer>();
		List<Integer> endForm = new ArrayList<Integer>();
		
		startForm = this.getLocation(resource,"<form");
		endForm = this.getLocation(resource, "</form>");
		
		System.out.println(startForm);
		System.out.println(endForm);
		
		for (int i = 0; i < startForm.size(); i++) {

			// Create parameters for a start and end point
			int start = startForm.get(i);
			int end = endForm.get(i);
		
			form = resource.substring(start, end);
		}
		
		return form;
	}

	
	/**
	 * Will loop through resource and return all locations of given data
	 * @param parseInfo
	 * @return
	 */
	public List<Integer> getLocation(String resource, String parseInfo) {
		List<Integer> values = new ArrayList<Integer>();

		int bit = 0;
		while (bit > -1) {
			bit = resource.indexOf(parseInfo, bit);

			// If it finds a match will put location into list
			if (bit > -1) {
				values.add(bit);
				bit++;
			}
		}
		return values;
	}
	
	/**
	 * Will parse a form and insert a given value
	 * @param resource
	 * @param value
	 */
	public void insertValue(String form, String value, String startLoc, String endLoc){
		
		List<Integer> start = new ArrayList<Integer>();
		List<Integer> end = new ArrayList<Integer>();
		
		//parse form to get email and password fields
		start = this.getLocation(form, startLoc);
		end = this.getLocation(form, endLoc);
		
		for (int i = 0; i < start.size(); i++) {

			// Create parameters for a start and end point
			int begin = start.get(i);
			int finish = form.indexOf(end.get(i), begin);
		
			//insert into form based off of start and end
			 String formStart = form.substring(0, begin);
			 String formEnd = form.substring(finish);
			 String newForm = formStart + value + formEnd;
			 
			 System.out.println(newForm);
		}
	}
	
}
