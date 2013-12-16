package org.loststone.toodledo.response;

import me.gizio.toodledo4j.json.TdError;
import net.arnx.jsonic.JSON;

import org.loststone.toodledo.exception.ToodledoApiException;

public class AddTodoResponse extends Response {
    
    public AddTodoResponse(String resp) {
        super(resp);
    }
    
    public String getResponseContent() throws ToodledoApiException {
        // check for errors:
        if (!this.succeeded()) {
            TdError te = JSON.decode(response, TdError.class);
            throw new ToodledoApiException("errorCode: " + te.getErrorCode() + ", errorDesc: "
                    + te.getErrorDesc());
        } else {
            // TdTask tt=JSON.decode(response,TdTask.class);
            TdTask[] tt = JSON.decode(response, TdTask[].class);
            return String.valueOf(tt[0].getId());
        }
    }
    
    private class TdTask {
        private int id;
        private String title;
        private String modified;
        private int completed;
        
        public int getId() {
            return id;
        }
        
        public void setId(int id) {
            this.id = id;
        }
        
        public String getTitle() {
            return title;
        }
        
        public void setTitle(String title) {
            this.title = title;
        }
        
        public String getModified() {
            return modified;
        }
        
        public void setModified(String modified) {
            this.modified = modified;
        }
        
        public int getCompleted() {
            return completed;
        }
        
        public void setCompleted(int completed) {
            this.completed = completed;
        }
        
    }
}
