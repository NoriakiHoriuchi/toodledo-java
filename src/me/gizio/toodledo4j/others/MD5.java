package me.gizio.toodledo4j.others;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    public String create(String source) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            
            byte[] data = source.getBytes();
            md.update(data);
            
            byte[] digest = md.digest();
            
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                int b = (0xFF & digest[i]);
                if (b < 16)
                    sb.append("0");
                sb.append(Integer.toHexString(b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        
    }
}
