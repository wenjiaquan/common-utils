package com.wenjiaquan.utils;
/**   
* @Title: StringUtil.java 
* @Package com.bawei.utils 
* @Description: String工具类
* @author A18ccms A18ccms_gmail_com   
* @date 2019年12月5日 下午1:29:55 
* @version V1.0   
*/
public class StringUtil {
	/*
	 * 判断字符串是否为空
	 */
	public static boolean isBlank(String str) {
		if(str==null) {
			return false;
		}
		str.trim();
		if(str.length()==0) {
			return false;
		}
		return true;
	}
	/**
	 * 判断是否是手机号
	 * @param str
	 * @return
	 */
	public static boolean isPhoneNum(String str) {
		String regex="1[3578]\\d{9}";
		return str.matches(regex);
	}
	
}
