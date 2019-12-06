package com.wenjiaquan.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**   
* @Title: FileUtil.java 
* @Package com.bawei.utils 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com   
* @date 2019年12月5日 下午2:07:30 
* @version V1.0   
*/
public class FileUtil {
	public static String getExtName(String fileName) {
		if(fileName==null || "".equals(fileName)) {
			throw new RuntimeException("文件名不能为空");
		}
		if(fileName.indexOf(".")<=-1) {
			throw new RuntimeException("该文件名没有包扩展名");
		}
		String extName=fileName.substring(fileName.indexOf("."));
		return extName;
	}
	
	public static String getSystemStr() {
		// String property = System.getProperty("user.home"); 
		String property = System.getProperty("java.io.tmpdir");
		return property;
	}
	/**
	 * 读取一行数据
	 * 
	 * @param args
	 */
	public static String readTestFileLine(File file) {
		StringBuffer sb=new StringBuffer();
		BufferedReader br=null;
		try {
			br=new BufferedReader(new FileReader(file));
			while(br.read()!=-1) {
				String readLine = br.readLine();
				sb.append(readLine);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			StreamUtil.closeAll(br);
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		System.out.println(readTestFileLine(new File("D:\\游戏\\aaa.txt")));
		
	}
}
