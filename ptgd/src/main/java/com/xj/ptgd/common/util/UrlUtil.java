package com.xj.ptgd.common.util;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class UrlUtil {
	
private final static String Charset = "UTF-8";
	
	/** 
	 * 将url参数转换成map 
	 * @param param aa=11&bb=22&cc=33 
	 * @return 
	 */  
	public static Map<String, String> getUrlParams(String param) {  
		
	    Map<String, String> map = new HashMap<String, String>();  
	    if (StringUtils.isBlank(param)) {
	        return null;  
	    }  
	    String[] params = param.split("&");  
	    for (int i = 0; i < params.length; i++) {  
	        String[] p = params[i].split("=");  
	        if (p.length == 2) {  
	            try {
					map.put(p[0],URLDecoder.decode(p[1].toString(),Charset));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}  
	        }  
	    }  
	    return map;
	    
	} 
	  
	/** 
	 * 将map转换成url 
	 * @param map 
	 * @return 
	 */  
	public static String getUrlParamsByMap(Map<String, String> map) {  
		
	    if (map == null) {  
	        return "";  
	    }  
	    StringBuffer sb = new StringBuffer();  
	    for (Map.Entry<String, String> entry : map.entrySet()) {  
	        sb.append(entry.getKey() + "=" + entry.getValue());  
	        sb.append("&");  
	    }  
	    String s = sb.toString();  
	    if (s.endsWith("&")) {  
	        s = StringUtils.substringBeforeLast(s, "&");
	    }  
	    return s;  
	    
	} 
	
	/**
	 * 把数组所有元素排序，并按照参数参数值的模式拼接成字符串
	 * @param params 需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static String paramsSortString(Map<String, String> params) {

		StringBuffer stringBuffer = new StringBuffer();

		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key).toString();

			stringBuffer.append(key).append("=")
			.append(value).append("&");
		}

		// 删掉最后一个 & 字符
		stringBuffer.deleteCharAt(stringBuffer.length() - 1);

		return stringBuffer.toString();
	}
	
	
	
}
