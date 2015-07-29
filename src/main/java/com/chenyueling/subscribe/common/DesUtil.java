package com.chenyueling.subscribe.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


/**
 * 与IOS同步的加密工具类
 * @author xiezefan
 *
 */
public class DesUtil {
	public static String key = "zhbitacm";
	public static String getKey() {
    	if(key == null){
    		key = "zhbitacm";
    	}
		return key;
	}
	
	/**
	 * 加密
	 * @param encryptString
	 * @return
	 * @throws java.lang.Exception
	 */
	public static String encryptDES(String encryptString) throws Exception {
		if (encryptString == null) {
			return "";
		}
		byte[] encryptedData = null;
		try {
			Security.addProvider(new com.sun.crypto.provider.SunJCE());
	    	Key key = new SecretKeySpec(getKey().getBytes(), "DES");
	    	Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");  
	     	cipher.init(Cipher.ENCRYPT_MODE, key);
	        encryptedData = cipher.doFinal(encryptString.getBytes());  
		} catch (Exception e) {
            e.printStackTrace();
			throw new Exception("明文 " + encryptString + " 加密失败");
		}
        return Base64.encode(encryptedData);  
    }  
	
	/**
	 * 解密
	 * @param decryptString
	 * @return
	 * @throws
	 */
    public static String decryptDES(String decryptString) throws Exception{
    	if (decryptString == null) {
			return "";
		}
    	byte[] decryptedData = null;
    	try {
            byte[] byteMi = Base64.decode(decryptString);     
            SecretKeySpec key = new SecretKeySpec(getKey().getBytes(), "DES");  
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");  
            cipher.init(Cipher.DECRYPT_MODE, key);  
            decryptedData = cipher.doFinal(byteMi);
    	} catch (Exception e) {
    		throw new Exception("密文 " + decryptString + " 解密失败");
    	}
    	return new String(decryptedData);    
    }
}
class Base64 {  
    private static final char[] legalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();  
    /** 
     * data[]进行编码 
     * @param data 
     * @return 
     */  
        public static String encode(byte[] data) {  
            int start = 0;  
            int len = data.length;  
            StringBuffer buf = new StringBuffer(data.length * 3 / 2);  
  
            int end = len - 3;  
            int i = start;  
            int n = 0;  
  
            while (i <= end) {  
                int d = ((((int) data[i]) & 0x0ff) << 16)  
                        | ((((int) data[i + 1]) & 0x0ff) << 8)  
                        | (((int) data[i + 2]) & 0x0ff);  
  
                buf.append(legalChars[(d >> 18) & 63]);  
                buf.append(legalChars[(d >> 12) & 63]);  
                buf.append(legalChars[(d >> 6) & 63]);  
                buf.append(legalChars[d & 63]);  
  
                i += 3;  
  
                if (n++ >= 14) {  
                    n = 0;  
                    buf.append(" ");  
                }  
            }  
  
            if (i == start + len - 2) {  
                int d = ((((int) data[i]) & 0x0ff) << 16)  
                        | ((((int) data[i + 1]) & 255) << 8);  
  
                buf.append(legalChars[(d >> 18) & 63]);  
                buf.append(legalChars[(d >> 12) & 63]);  
                buf.append(legalChars[(d >> 6) & 63]);  
                buf.append("=");  
            } else if (i == start + len - 1) {  
                int d = (((int) data[i]) & 0x0ff) << 16;  
  
                buf.append(legalChars[(d >> 18) & 63]);  
                buf.append(legalChars[(d >> 12) & 63]);  
                buf.append("==");  
            }  
  
            return buf.toString();  
        }  
  
        private static int decode(char c) {  
            if (c >= 'A' && c <= 'Z')  
                return ((int) c) - 65;  
            else if (c >= 'a' && c <= 'z')  
                return ((int) c) - 97 + 26;  
            else if (c >= '0' && c <= '9')  
                return ((int) c) - 48 + 26 + 26;  
            else  
                switch (c) {  
                case '+':  
                    return 62;  
                case '/':  
                    return 63;  
                case '=':  
                    return 0;  
                default:  
                    throw new RuntimeException("unexpected code: " + c);  
                }  
        }  
  
        /** 
         * Decodes the given Base64 encoded String to a new byte array. The byte 
         * array holding the decoded data is returned. 
         */  
  
        public static byte[] decode(String s) {  
  
            ByteArrayOutputStream bos = new ByteArrayOutputStream();  
            try {  
                decode(s, bos);  
            } catch (IOException e) {  
                throw new RuntimeException();  
            }  
            byte[] decodedBytes = bos.toByteArray();  
            try {  
                bos.close();  
                bos = null;  
            } catch (IOException ex) {  
                System.err.println("Error while decoding BASE64: " + ex.toString());  
            }  
            return decodedBytes;  
        }  
  
        private static void decode(String s, OutputStream os) throws IOException {  
            int i = 0;  
  
            int len = s.length();  
  
            while (true) {  
                while (i < len && s.charAt(i) <= ' ')  
                    i++;  
  
                if (i == len)  
                    break;  
  
                int tri = (decode(s.charAt(i)) << 18)  
                        + (decode(s.charAt(i + 1)) << 12)  
                        + (decode(s.charAt(i + 2)) << 6)  
                        + (decode(s.charAt(i + 3)));  
  
                os.write((tri >> 16) & 255);  
                if (s.charAt(i + 2) == '=')  
                    break;  
                os.write((tri >> 8) & 255);  
                if (s.charAt(i + 3) == '=')  
                    break;  
                os.write(tri & 255);  
  
                i += 4;  
            }  
        }  
          
}  
