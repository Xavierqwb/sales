package com.xavier.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xavier on 2018/1/26.
 */
public class FormToMapUtil {

	public static Map<String, String> formToMap(String form) {
		Map<String, String> result = new HashMap<>();
		String[] props = form.split("&");
		for (String prop : props) {
			int index = prop.indexOf('=');
			result.put(prop.substring(0, index), prop.substring(index + 1));
		}
		return result;
	}
}
