package com.artsoft.config;

import java.io.FileOutputStream;
import java.io.IOException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.*;



public class ConfigManager{
	public Document m_xmlDoc = null; /**主文档对象*/
	static ConfigManager config = null;
	
	/***测试日志分隔线（起始）*/
	private static String start_link = "\r\n\r\n\r\n************************************************************\r\n";
	/***测试日志分隔线（结束）*/
	private static String end_link = "\r\n************************************************************\r\n";
	
		
	public static ConfigManager getInstance(){
		if(config == null)
			config = new ConfigManager();
		return config;
	}
	
	private ConfigManager() {
        try {  
        	SAXReader reader = new SAXReader();      	
        	m_xmlDoc = reader.read("config/config.xml");
        } catch (DocumentException e) {
        	e.printStackTrace();
            return;
        }
	}
	
	public String getStartLink(){
		return start_link;
	}
	
	public String getEndLink(){
		return end_link;
	}
	
	/**
	 * 获取配置文件信息
	 * @param strParam
	 * @return
	 */
	public String getConfigValue(String strParam) {
		Element root = m_xmlDoc.getRootElement();
		Node data = root.selectSingleNode("//" + strParam);
		return data.getText();
	}
	
	/**
	 * 设置配置文件值
	 * lusd 2010-03-31
	 */
	public void setConfigValue(String strKey,String strVal) {
		Element root = m_xmlDoc.getRootElement();
		Node data = root.selectSingleNode(strKey);
		data.setText(strVal);
		write();
	}
	
	
	public void write() {
        // 指定文件 
        XMLWriter writer = null;
        try {
            // 美化格式
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("gb2312");
            writer = new XMLWriter(new FileOutputStream("config/config.xml"), format);
            writer.write(m_xmlDoc);
            writer.close();
        } catch (IOException e) {
        }
    }
}
