package org.loststone.toodledo.xml;

import me.gizio.toodledo4j.json.TdError;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ErrorParser extends DefaultHandler {

	String xml; 
	String json; 
	StringBuilder tempVal;
//	String error = null;
	String error = null;
	int depth = 0;
	
	public ErrorParser(String json) {
//		this.xml = xml;
		this.json=json;
	}
	
	/**
	 * Parse the XML and return the Toodledo error text, if any.
	 * Return null if there was no error.
	 * @return the error or null if no error
	 */
	public String getError() {
	try{
	    TdError te = JSON.decode(json, TdError.class);
	    error=te.getErrorDesc();
	}catch (JSONException e){
	    // Do Nothing and then error will stay null.
	}
		return error;
	}
	
	//Event Handlers
	public void startElement(String uri, String localName, String qName,
		Attributes attributes) throws SAXException {
		tempVal = new StringBuilder();
		depth++;
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal.append(ch, start, length);
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		depth--;
		if(qName.equalsIgnoreCase("error") && depth == 0) {
			error = tempVal.toString().trim();
		}
	}


}
