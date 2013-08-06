package net.team.omega.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class CryptUtils
{

    private static String convertToHex(byte[] data)
    {
	StringBuffer buf = new StringBuffer();
	for (int i = 0; i < data.length; i++)
	{
	    int halfbyte = (data[i] >>> 4) & 0x0F;
	    int two_halfs = 0;
	    do
	    {
		if ((0 <= halfbyte) && (halfbyte <= 9))
		    buf.append((char) ('0' + halfbyte));
		else
		    buf.append((char) ('a' + (halfbyte - 10)));
		halfbyte = data[i] & 0x0F;
	    } while (two_halfs++ < 1);
	}
	return buf.toString();
    }

    public static String securePassword(String password)
    {
	MessageDigest mDigest;
	try
	{
	    mDigest = MessageDigest.getInstance("SHA-1");
	    byte[] hash = new byte[40];
	    mDigest.update(password.getBytes("iso-8859-1"), 0, password.length());
	    hash = mDigest.digest();

	    return convertToHex(hash);
	} catch (NoSuchAlgorithmException | UnsupportedEncodingException e)
	{
	    LogHandler.severe("SHA-1 hash error.");
	    LogHandler.severe("Cause : " + e.getMessage());

	    BasicUtils.onErrorExit();
	}
	
	return null;
    }
    
}
