package com.wenjiaquan.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**   
* @Title: CookieUtil.java 
* @Package com.wenjiaquan.utils 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com   
* @date 2019年12月23日 下午1:00:53 
* @version V1.0   
*/
public class CookieUtil {
	/**
	 * 添加Cookie
	 * @param response
	 * @param name
	 * @param value
	 * @param domain
	 * @param path
	 * @param maxAge
	 */
	public static void addCookie(HttpServletResponse response,String name,String value,String domain,String path,Integer maxAge) {
		Cookie cookie = new Cookie(name,value);
		if(StringUtil.isBlank(path)){
			cookie.setPath("/");
		}else{
			cookie.setPath(path);
		}
		if(maxAge == null || maxAge<=0){
			cookie.setMaxAge(-1);
		}else{
			cookie.setMaxAge(maxAge.intValue());
		}
		response.addCookie(cookie);
	}
	
	
	/**
	 * 添加Cookie
	 * @param response
	 * @param cookieMap
	 * @param maxAge
	 */
	public static void addCookie(HttpServletResponse response,Map<String,String> cookieMap,Integer maxAge) {
		Set<String> keySet = cookieMap.keySet();
		String[] fields = new String[keySet.size()];
		keySet.toArray(fields);
		for(String cookieName : fields){
			addCookie(response, cookieName, cookieMap.get(cookieName), null, null, maxAge);
		}
	}
	/**
	 * 获取Cookie
	 * @param request
	 * @return
	 */
	private static Map<String,String> getCookieMap(HttpServletRequest request){
	    Map<String,String> cookieMap = new HashMap<String, String>();
	    Cookie[] cookies = request.getCookies();
	    if(null!=cookies){
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie.getValue());
	        }
	    }
	    return cookieMap;
	}
	/**
	 * 获取Cookie
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookieByName(HttpServletRequest request,String name){
	    Map<String,String> cookieMap = getCookieMap(request);
	    return cookieMap.get(name);
	}
}
