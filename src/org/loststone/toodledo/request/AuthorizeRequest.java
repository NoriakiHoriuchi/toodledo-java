package org.loststone.toodledo.request;

import me.gizio.toodledo4j.others.MD5;
import me.gizio.toodledo4j.others.TdConstants;

import org.loststone.toodledo.response.AuthorizeResponse;
import org.loststone.toodledo.response.Response;

public class AuthorizeRequest extends Request {
    
    public AuthorizeRequest(String userId, String appId, String appToken) {
        super();
        MD5 digest = new MD5();
        String sig = digest.create(userId + appToken);
        this.url = "http://api.toodledo.com/2/account/token.php?userid=" + userId + ";appid="
                + appId + ";sig=" + sig;
    }
    
    @Override
    public Response getResponse() {
        AuthorizeResponse response;
        if (TdConstants.DEBUG_MODE) {
            response = new AuthorizeResponse(TdConstants.DEBUG_SESSION_TOKEN);
        } else {
            this.exec();
            response = new AuthorizeResponse(this.jsonResponse);
        }
        return response;
    }
    
}
