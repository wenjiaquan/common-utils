package com.wenjiaquan.utils;

import java.io.File;
import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.wenjiaquan.utils.JSoup;

/**   
* @Title: JSoup.java 
* @Package com.wenjiaquan.utils 
* @Description: TODO(用一句话描述该文件做什么) 
* @author A18ccms A18ccms_gmail_com   
* @date 2020年2月13日 下午3:35:05 
* @version V1.0   
*/
public class JSoup {
	public static void jsoup1() throws IOException {
		int count = 0;
		// 获取连接对象
		Connection connect = Jsoup.connect("https://news.163.com/");
		// 获取文档对象
		Document document = connect.get();
		// 获取当前文档的所有超链接
		Elements ahrefs = document.select("a[href]");
		
		//本地文件存储的路径
		String file_path="D:\\1709DJsoup";
		
		//如果没有当前文件夹的路径，则创建
		File file = new File(file_path);
		
		if(!file.exists()) {
			file.mkdir();
		}
		
		// 遍历元素对象
		for (Element href : ahrefs) {
			// 超链接的url地址
			String url = href.attr("href");
			// 定义表达式 https://news.163.com ***** html

//			String regex = "https://news\\\\.163\\\\.com.*html$";
			
			//以https://news.163.com开头，以html结尾
			//https://news\\.163\\.com.*html$
			// 特殊要求  
			if (url != null && url.startsWith("https://news.163.com") && url.endsWith("html")) {
//			if (url != null && Pattern.matches(regex, url)) {
				// 连接的文本内容
				String title = href.text();
				
				
				// 获取文章的文档对象
				Document articleDoc = Jsoup.connect(url).get();
				// 获取文章的内容元素对象
				Element articleContentElement = articleDoc.getElementById("endText");
				// 判断元素是否为空
				if (articleContentElement != null) {
					System.out.println(url + "@@@@@@@@@" + title);
					
					// 获取纯文本内容
					String content = articleContentElement.text();
					
					//去除标题中的特殊符号
					title = title.replace("?", "").replace("\"", "").replace(":", "").replace("/", "").replace("\\", "").replace("|", "");
					
					//写入到文件中
					FileUtil.writeFile(file_path + "\\" + title + ".txt", content, "utf8");
					
					count++;
				}
			}
		}
		System.out.println("首页中找到了复合条件的网址有：" + count + "篇文章");
	}
	
	
	
	
	
	
	public static void jsoup2() throws IOException {
		int count = 0;
		// 获取连接对象
		Connection connect = Jsoup.connect("http://www.xbiquge.la/10/10489/");
		// 获取文档对象
		Document document = connect.get();
		// 获取当前文档的所有超链接
		Elements ahrefs = document.select("a[href]");
		
		//本地文件存储的路径
		String file_path="D:\\1709DJsoup";
		
		//如果没有当前文件夹的路径，则创建
		File file = new File(file_path);
		
		if(!file.exists()) {
			file.mkdir();
		}
		
		// 遍历元素对象
		for (Element href : ahrefs) {
			// 超链接的url地址
			String url = href.attr("href");
			// 定义表达式 https://news.163.com ***** html

//			String regex = "https://news\\\\.163\\\\.com.*html$";
			
			//以https://news.163.com开头，以html结尾
			//https://news\\.163\\.com.*html$
			// 特殊要求  
			if (url != null && url.startsWith("/10/10489") && url.endsWith("html")) {
//			if (url != null && Pattern.matches(regex, url)) {
				// 连接的文本内容
				String title = href.text();
				url = "http://www.xbiquge.la" + url;
				
				// 获取文章的文档对象
				Document articleDoc = Jsoup.connect(url).get();
				// 获取文章的内容元素对象
				Element articleContentElement = articleDoc.getElementById("content");
				// 判断元素是否为空
				if (articleContentElement != null) {
					System.out.println(url + "@@@@@@@@@" + title);
					
					// 获取纯文本内容
					String content = articleContentElement.text();
					
					//去除标题中的特殊符号
					title = title.replace("?", "").replace("\"", "").replace(":", "").replace("/", "").replace("\\", "").replace("|", "");
					
					//写入到文件中
					FileUtil.writeFile(file_path+"\\" + title + ".txt", content, "utf8");
					
					count++;
				}
			}
		}
		System.out.println("首页中找到了复合条件的网址有：" + count + "篇文章");
	}
	public static void sohu() throws IOException {
		String file_path="D:\\1709DJsoup";
		//模拟浏览器发请求
				Connection connect = Jsoup.connect("http://news.sohu.com/");
				Document doc = connect.get();
//				System.out.println(doc);
				Elements select = doc.select(".list16");
				for (Element list16 : select) {
//					System.out.println(list16);
					Elements select2 = list16.select("a[href]");
					for (Element a : select2) {
//						System.out.println(a);
						String url = a.attr("href");
						System.out.println(url);
						if(!url.startsWith("http")) {
							url="http:"+url;
						}
//						System.out.println(url);
						if(!url.contains("subject")&&!url.contains("sports")) {
							Connection connect2 = Jsoup.connect(url);
							Document document = connect2.get();
							Elements select3 = document.select(".article");
							String content=null;
							for (Element element : select3) {
								//System.out.println(element);
								content = element.text();
//								System.out.println(content);
							}
							String title = a.attr("title");
							 title = title.replace("|", "").replace("*", "").replace("\"", "").replace("?", "").replace("/", "")
							.replace("\\", "").replace(">", "").replace("<", "").replace(":", "");
//							 以标题作为文本的名称,已内容作为文本的内容存在本地磁盘
							 FileUtil.writeFile(file_path + "\\" + title + ".txt", content, "utf8");
						}
					}
				}
	}
}
