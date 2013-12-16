package org.loststone.toodledo.response;

import me.gizio.toodledo4j.json.TdError;
import net.arnx.jsonic.JSON;

import org.loststone.toodledo.exception.ToodledoApiException;

public class AuthorizeResponse extends Response {
    
    public AuthorizeResponse(String response) {
        super(response);
    }
    
    public String getResponseContent() throws ToodledoApiException {
        // check for errors:
        if (!this.succeeded()) {
            TdError te = JSON.decode(response, TdError.class);
            throw new ToodledoApiException("errorCode: " + te.getErrorCode() + ", errorDesc: "
                    + te.getErrorDesc());
        } else {
            TdToken tt = JSON.decode(response, TdToken.class);
            return tt.token;
        }
    }
    
    private class TdToken {
        private String token;
        
        public String getToken() {
            return token;
        }
        
        public void setToken(String token) {
            this.token = token;
        }
    }
}
