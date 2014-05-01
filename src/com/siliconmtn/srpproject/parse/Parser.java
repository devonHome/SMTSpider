package com.siliconmtn.srpproject.parse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/****************************************************************************
 * <b>Title</b>: Parser.javaIncomingDataWebService.java
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
 * @since 8:30:05 AM
 *        <p/>
 *        <b>Changes: </b>
 ****************************************************************************/

public class Parser {

	/**
	 * Parses resource for just form tags
	 * 
	 * @return
	 */
	public String getForm(String resource) {
		String form = null;
		List<Integer> startForm = new ArrayList<Integer>();
		List<Integer> endForm = new ArrayList<Integer>();

		startForm = this.getAllLocations(resource, "<form");
		endForm = this.getAllLocations(resource, "</form>");

		for (int i = 0; i < startForm.size(); i++) {

			// Create parameters for a start and end point
			int start = startForm.get(i);
			int end = endForm.get(i);

			form = resource.substring(start, end);
		}

		return form;
	}

	/**
	 * Will parse a form and return a list of all its input parameters
	 * 
	 * @return
	 */
	public List<String> getFormParams(String form) {
		List<String> tags = new ArrayList<String>();
		List<Integer> start = new ArrayList<Integer>();
		List<Integer> end = new ArrayList<Integer>();

		start = this.getAllLocations(form, "<input");
		end = this.getAllLocations(form, "/>");

		for (int i = 0; i < start.size(); i++) {

			// Create parameters for a start and end point
			int begin = start.get(i);
			int finish = end.get(i);

			tags.add(form.substring(begin, finish));

		}

		for (String tag : tags) {
			System.out.println(tag);
		}
		return tags;
	}
	
	/**
	 * Will return a mapping of all parameter's keys and their values
	 * @return
	 */
	public List<String> getPostParams(List<String> parameters){
		List<String> paramList = new ArrayList<String>();
		
		//create indexes
		int start = 0;
		int end = 0;
		//loop through list
		for(String param : parameters){
			
			start = this.getLocation(param, "name='");
			
			end = param.indexOf(param, start);
			String sub = param.substring(start, end);
			System.out.println(sub);
			paramList.add(sub);
		
		}
		return paramList; 
	}
	/**
	 * Will loop through resource and return all locations of given data
	 * 
	 * @param parseInfo
	 * @return
	 */
	public List<Integer> getAllLocations(String resource, String parseInfo) {
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
	 * Parses a single string for a specific string value
	 * @param resource
	 * @param parseInfo
	 * @return number of location for given string value or -1 if false
	 */
	public int getLocation(String resource, String parseInfo){
		int bit = 0;
		
		bit = resource.indexOf(parseInfo, bit);
		return bit;
	}

}
