package com.artsoft.admin.mavenCount;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Jsoupdom {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String strUrl = "http://c.wanfangdata.com.cn/periodical/bjfyj/2008-5.aspx";
//		String strHtml = DownloadUtil.getHtmlText(strUrl, 1000*30, "utf-8", null, null);
		Document doc = Jsoup.connect(strUrl).get();
		
		Elements links = doc.getElementsByAttributeValue("class", "qkcontent_name");
		
		for (Element element : links) {
//			System.out.println(element.toString());
//			String url = element.select("a").get(1).attr("href");  
			
			String strLinkUrl = element.attr("href");
			System.out.println(strLinkUrl);
		}

	}

}
