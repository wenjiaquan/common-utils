package com.wenjiaquan.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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
		String str=null;
		try {
			br=new BufferedReader(new FileReader(file));
			while((str=br.readLine())!=null) {
				sb.append(str+"\r\n");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			StreamUtil.closeAll(br);
		}
		return sb.toString();
	}
	public static List<String> readTestFileLineList(File file) {
		List<String> list=new ArrayList<String>();
		BufferedReader br=null;
		String str=null;
		try {
			br=new BufferedReader(new FileReader(file));
			while((str=br.readLine())!=null) {
				list.add(str);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			StreamUtil.closeAll(br);
		}
		return list;
	}
	/**
	 * 递归删除文件
	 * @param args
	 */
	public static void deleteFile(File file) {
		if(file.isDirectory()) {
			File[] listFiles = file.listFiles();
			for (File file2 : listFiles) {
				deleteFile(file2);
			}
			file.delete();
		}else {
			file.delete();
		}
	}
	/**
	 * @Title: getFileSize   
	 * @Description: 获得文件大小
	 * 返回文件以指定单位大小表示
	 * File a.txt=2k  
	 * @param: @param file
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String getFileSize(File file) {
		long length = file.length();
		double len = length/1024.0;
//		return Math.round((length/1024.0))+"kb";
		return String.format("%.2f",len)+"kb";
	}
	
	public static String getFileSize(String fileFullName) {
		return getFileSize(new File(fileFullName));
	}
	
	public static void main(String[] args) {
		List<String> list = readTestFileLineList(new File("D:\\游戏\\aaa.txt"));
		for (String string : list) {
			System.out.println(string);
		}
		System.out.println(readTestFileLine(new File("D:\\游戏\\aaa.txt")));
	}
}
