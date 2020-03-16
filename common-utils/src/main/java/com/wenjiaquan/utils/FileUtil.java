package com.wenjiaquan.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

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
	
	/*
	 * 方法1：批量关闭流，参数能传无限个。(3分) 可变参数 例如传入FileInputStream对象、JDBC中Connection对象都可以关闭。
	 */
	public static void closeAll(AutoCloseable... autoCloseable) {

		if (null != autoCloseable && autoCloseable.length > 0) {
			for (AutoCloseable autoCloseable2 : autoCloseable) {
				try {
					autoCloseable2.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}
	/*
	 * 方法2：传入一个文本文件对象，默认为UTF-8编码，返回该文件内容(3分)，要求方法内部调用上面第1个方法关闭流(3分)
	 */
	public static String readTextFile(InputStream src) {

		byte[] b = new byte[1024];
		int x = 0;
		String str = "";
		try {
			while ((x = src.read(b)) != -1) {
				str += new String(b, 0, x, "utf-8");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {// 关流
			closeAll(src);
		}
		return str;
	}

	/*
	 * 方法3：传入文本文件对象，返回该文件内容(3分)，并且要求内部调用上面第2个方法(5分)。* 这是典型的方法重载，记住了吗？少年…
	 */
	public static String readTextFileByLine(File txtFile) throws FileNotFoundException {
		return readTextFile(new FileInputStream(txtFile));
	}
	public static String readTextFileByLine(String pathname) throws FileNotFoundException {
		return readTextFileByLine(new File(pathname));
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
	/***
	 * @Title: writeFile * @Description: 按照指定的编码把内容写入指定的文件中 * @param path * @param
	 *         content * @param charset * @throws IOException * @return: void
	 */
	public static void writeFile(String path, String content, String charset) throws IOException {
		// 创建写入的文件
		File file = new File(path);
		// 创建输出流对象
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
		bw.write(content);
		bw.flush();
		bw.close();

	}
	
	/**
	 *  上传文件
	 * @param file
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static String processFile(MultipartFile file) throws IllegalStateException, IOException {

		// 原来的文件名称
		System.out.println("file.isEmpty() :" + file.isEmpty()  );
		System.out.println("file.name :" + file.getOriginalFilename());
		
		if(file.isEmpty()||"".equals(file.getOriginalFilename()) || file.getOriginalFilename().lastIndexOf('.')<0 ) {
			return "";
		}
			
		String originName = file.getOriginalFilename();
		String suffixName = originName.substring(originName.lastIndexOf('.'));
		SimpleDateFormat sdf=  new SimpleDateFormat("yyyyMMdd");
		String path = "d:/pic/" + sdf.format(new Date());
		File pathFile = new File(path);
		if(!pathFile.exists()) {
			pathFile.mkdir();
		}
		String destFileName = 		path + "/" +  UUID.randomUUID().toString() + suffixName;
		File distFile = new File( destFileName);
		file.transferTo(distFile);//文件另存到这个目录下边
		return destFileName.substring(7);
	}
	
	/**
	 * 
	 * @param response
	 * @param file下载
	 * @throws FileNotFoundException 
	 */
	public static void downLoad(HttpServletResponse response, String filename) throws FileNotFoundException {
		 /* // 下载本地文件
	    String fileName = "Operator.doc".toString(); // 文件的默认保存名
*/	    // 读到流中
	    InputStream inStream = new FileInputStream("d:\\pic\\"+filename);// 文件的存放路径
	    // 设置输出的格式
	    response.reset();
	    response.setContentType("bin");
	    response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
	   
	    // 循环取出流中的数据
	    byte[] b = new byte[1024];
	    int len;
	    try {
	      while ((len = inStream.read(b)) > 0)
	        response.getOutputStream().write(b, 0, len);
	      inStream.close();
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	
	}
}
