package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by xavier on 2018/1/21.
 */
public class Security {

	public static String md5(String textPwd) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("md5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		if (messageDigest != null) {
			byte[] md5Bytes = messageDigest.digest(textPwd.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte md5Byte : md5Bytes) {
				String toHexStr = Integer.toHexString(md5Byte & 0xff);
				sb.append(toHexStr.length() == 1 ? "0" + toHexStr : toHexStr);
			}
			return sb.toString();
		}

		// TODO: throw some exception
		return " ";
	}
}
