package org.loststone.toodledo.request;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.loststone.toodledo.response.AuthorizeResponse;
import org.loststone.toodledo.response.Response;

public class AuthorizeRequest extends Request {

	public AuthorizeRequest(String userId, String appId, String appToken) {
		super();
		String sig=getSig(userId, appToken);
		this.url = "http://api.toodledo.com/2/account/token.php?userid="+userId+";appid="+appId+";sig="+sig;
	}

	@Override
	public Response getResponse() {
		this.exec();
		AuthorizeResponse response = new AuthorizeResponse(this.jsonResponse);
		return response;
	}
	
	private String getSig(String userId, String appToken){
	    MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            return digest.digest((userId+appToken).getBytes()).toString();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
	}
}
