package org.loststone.toodledo.response;

import net.arnx.jsonic.JSON;

import org.loststone.toodledo.exception.ToodledoApiException;

public class AuthorizeResponse extends Response {
    
    public AuthorizeResponse(String response) {
        super(response);
    }
    
    public String getResponseContent() throws ToodledoApiException {
        // check for errors:
        if (!this.succeeded()) {
            TdError te = new TdError();
            te = JSON.decode(response);
            throw new ToodledoApiException("errorCode: " + te.errorCode + ", errorDesc: "
                    + te.errorDesc);
        } else {
            TdToken tt = new TdToken();
            tt = JSON.decode(response);
            return tt.token;
        }
    }
    
    private class TdError {
        String errorCode;
        String errorDesc;
    }
    
    private class TdToken {
        String token;
    }
}
