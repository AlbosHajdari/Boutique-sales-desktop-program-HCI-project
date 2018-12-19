import java.math.*;
import java.security.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import java.security.*;
public class Encryption {
	
	final static String  STR_RANDOM = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	
	public static String generateSalt()
	{
		StringBuilder salt = new StringBuilder();
		int karakteri;
		for(int i=0;i<10;i++)
		{
			karakteri = (int)(Math.random()*STR_RANDOM.length());
			
			salt.append(STR_RANDOM.charAt(karakteri));
		}

		return salt.toString();
	}
	
	
	
	public static String SHA1(String str)
	{
		try
		{
			MessageDigest md = MessageDigest.getInstance("SHA1");
			byte[] result = md.digest(str.getBytes());
			StringBuffer sb = new StringBuffer();
			for(int i= 0;i<result.length;i++)
			{
				sb.append(Integer.toString((result[i]&0xff)+0x100,16).substring(1));
			}
			return sb.toString();
		}
		catch(Exception ex)
		{
			return "";
		}
	}
	
	public static void main(String[] args)
	{
		System.out.println(generateSalt());
	}
}
