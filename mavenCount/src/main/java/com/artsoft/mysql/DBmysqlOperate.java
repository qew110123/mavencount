package com.artsoft.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.artsoft.config.ConfigManager;

public class DBmysqlOperate {
	
	
	public static DBmysqlOperate dbObj = null;
	private static String driver = null;
	private static String url = null;
	private static String user = null;
	private static String pwd = null;
	
	public Connection conn = null;

	private DBmysqlOperate() {
		ConfigManager config = ConfigManager.getInstance();
		driver = config.getConfigValue("drivermysql16");
		url = config.getConfigValue("urlmysql16");
		user = config.getConfigValue("usermysql16");
		pwd = config.getConfigValue("pwdmysql16");
		conn = getConn();
	}
	
	public static DBmysqlOperate getInstance() {
		if(dbObj == null) {
			dbObj = new DBmysqlOperate();
		}
		return dbObj;
	}
	
	public Connection getConnection() {
		try {
			if (conn == null || conn.isClosed())
				conn = getConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 得到数据库连接
	 * @return 数据库连接				
	 */
	public Connection getConn() {
		try {
			
			Class.forName(driver);
			
			return DriverManager.getConnection(url, user, pwd);
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
