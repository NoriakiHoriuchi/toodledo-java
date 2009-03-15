package org.loststone.toodledo.response;

import org.loststone.toodledo.ToodledoApiException;


public class AddContextResponse extends Response {

	public AddContextResponse(String resp) {
		super(resp);
		if (response.contains("error")) 
			this.succeed = false;
		else
			this.succeed = true;
	}
	
	@Override
	public int getResponseResult() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// TODO refactor to the parent class.
	public String getResponseContent() throws ToodledoApiException {
		String result = null;		
		// check for errors:
		if (response.contains("error")) {
			this.succeed = false;
			throw new ToodledoApiException(response.substring(response.indexOf("<error>")+7, 
					response.indexOf("</error>")));
		}
		
		result = response.substring(response.indexOf("<added>")+7, response.indexOf("</added>"));
		this.succeed = true;
		return result; 
	}

}