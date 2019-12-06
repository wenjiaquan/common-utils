package com.wenjiaquan.utils;

import java.util.Random;

/**   
* @Title: RandomUtil.java 
* @Package com.bawei.utils 
* @Description: TODO(随机生成限定的数字和字母) 
* @author A18ccms A18ccms_gmail_com   
* @date 2019年12月5日 下午7:36:23 
* @version V1.0   
*/
public class RandomUtil {
	public static char getRandomAzChar() {
		Random random = new Random();
		int startChar='a'+0;
		char newChar=(char) (startChar+random.nextInt(26));
		return newChar;
	}
	//随机生成字母
	public static String getRandomLetter(int num) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++) {
			char newChar=getRandomAzChar();
			sb.append(newChar);
		}
		return sb.toString();
	}
	public static char getRandomNumber() {
		Random ranom=new Random();
		int startChar='0'+0;
		char newChar=(char) (startChar+ranom.nextInt(10));
		return newChar;
	}
	public static String getRandomNumberchar(int num) {
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < num; i++) {
			char newChar=(char) (getRandomNumber());
			sb.append(newChar);
		}
		return sb.toString();
	}
	public static String getRandomNumberandstr(int num) {
		Random random=new Random();
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < num; i++) {
			if(random.nextInt(36)>15) {
				sb.append(getRandomAzChar());
			}else {
				sb.append(getRandomNumber());
			}
		}
		
		
		return sb.toString();
	}
	
	
}
