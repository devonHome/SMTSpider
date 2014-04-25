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
	
	private StringBuilder resource = null;
	
	/**
	 * Pass in collection of data to be parsed by class
	 * @param data
	 */
	public Parser(StringBuilder data) {
		this.resource = data;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<String> getTableData(){
		List<String> td = new ArrayList<String>();
		
		//parse resource to get all tds
		String[] rows = resource.toString().split("<td");
		
		for(String r : rows){
			td.add(r);
		}
		//put each td into a list
		return td;
	}
	
}
