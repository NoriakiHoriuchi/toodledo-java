package org.loststone.toodledo.util;

import me.gizio.toodledo4j.others.MD5;

import org.joda.time.DateTime;

/**
 * This class holds the key for the usage of one single user.
 * 
 * @author lant
 * 
 */
public class AuthToken {
    private String sessionToken;
    private String key;
    private DateTime date;
    
    /**
     * Creates the key for a user.
     * 
     * @param password
     * @param username
     * @param sessionToken
     */
    public AuthToken(String password, String username, String appToken, String sessionToken) {
        this.sessionToken = sessionToken;
        MD5 digest = new MD5();
        this.key = digest.create(digest.create(password) + appToken + sessionToken);
        this.date = new DateTime().plusHours(4);
    }
    
    /**
     * @return the token
     */
    public String getSessionToken() {
        return sessionToken;
    }
    
    /**
     * The key is what is needed to use the REST API.
     * 
     * @return the key
     */
    public String getKey() {
        return key;
    }
    
    /**
     * @return the date
     */
    public DateTime getDate() {
        return date;
    }
    
    /**
     * Returns the remaining time of validity for this token in seconds.
     * 
     * @return seconds until this token will be canceled.
     */
    public int getRemainingTime() {
        return Math.max(0, this.date.getSecondOfDay() - new DateTime().getSecondOfDay());
    }
}
