package com.artsoft.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.artsoft.config.ConfigManager;

public class DBOperate {
	
	public static DBOperate dbObj = null;
	private static String driver = null;
	private static String url = null;
	private static String user = null;
	private static String pwd = null;
	
	public Connection conn = null;

	private DBOperate() {
		ConfigManager config = ConfigManager.getInstance();
		driver = config.getConfigValue("driver");
		url = config.getConfigValue("url218");
		user = config.getConfigValue("user218");
		pwd = config.getConfigValue("pwd218");		
		conn = getConn();
	}
	
	public static DBOperate getInstance() {
		if(dbObj == null) {
			dbObj = new DBOperate();
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
		
	/**
	 * 获取表序列 
	 * @return
	 */
	public String getSequence(Connection conn,String strSable){
		
		String strSql = "select "+strSable+".NEXTVAL from dual";
		String strId = (String)getResultValue(conn,strSql);
		return strId;
	}
	
	/**
	 * 执行查询语句
	 */
	public static String getResultValue(Connection conn,String strSql) {
		PreparedStatement select_stm = null;
		ResultSet result = null;
		try {
			String strValue = "-1";
			select_stm = conn.prepareStatement(strSql,java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,java.sql.ResultSet.CONCUR_READ_ONLY);
			result = select_stm.executeQuery();
			if (result.next()) {
				strValue = result.getString(1);
			}
			
			return strValue;
		} catch (Exception e) {
			e.printStackTrace();
			return "-1";
		}finally {
			try {
				if (result != null)
					result.close();
				if (select_stm != null)
					select_stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 数据库添加操作
	 * @param conn		数据库连接
	 * @param strSql	执行语句
	 */
	public static boolean executeRecord(Connection conn,String strSql){
		PreparedStatement insert_stm = null;
		
		try{
			insert_stm=conn.prepareStatement(strSql);
			insert_stm.executeUpdate();
			return true;
		}catch(Exception e){
			return false;
		}finally {
			try {
				if (insert_stm != null)
					insert_stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 添加数据(处理了多个字段问题)
	 * @param Conn		数据库连接
	 * @param strSql	执行语句
	 * @param list		值列表
	 */
	public static boolean insertRecord(Connection conn,String strSql,List list){
		PreparedStatement stmt = null;
		try{
			stmt = conn.prepareStatement(strSql);
			for (int i=0; i<list.size(); i++) {
				String strVal = list.get(i).toString();
				stmt.setString(i+1,strVal);
			}
			stmt.executeUpdate();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<String> getResultListValue(Connection conn,String strSql,int iNum){
		PreparedStatement select_stm = null;
		ResultSet result = null;
		try{
			select_stm=conn.prepareStatement(
					strSql,java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,
					java.sql.ResultSet.CONCUR_READ_ONLY);
			result=select_stm.executeQuery();
			ArrayList<String> listTemp = new ArrayList<String>();
			if(result.next()){
				for(int i=0;i<iNum;i++){
					listTemp.add(result.getString(i+1));
				}
			}
			return listTemp;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally {
			try {
				if (result != null)
					result.close();
				if (select_stm != null)
					select_stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 执行查询语句(处理存在多个字段的问题)
	 * @param Conn				数据库连接
	 * @param strSql			执行语句
	 * @param iNum				查询列数
	 * @return					查询结果链表
	 */
	public static List getResultList(Connection conn,String strSql,int iNum){
		PreparedStatement select_stm = null;
		ResultSet result = null;
		try{
			List list = new ArrayList();
			select_stm=conn.prepareStatement(
					strSql,java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE,
					java.sql.ResultSet.CONCUR_READ_ONLY);
			result=select_stm.executeQuery();
			while(result.next()){
				List listTemp = new ArrayList();
				for(int i=0;i<iNum;i++){
					listTemp.add(result.getString(i+1));
				}
				list.add(listTemp);
			}
			
			//关闭查询对象
			if(result != null)
				result.close();
			
			if(select_stm != null)
				select_stm.close();
			
			return list;
		}catch(Exception e){
			return null;
		}finally {
			try {
				if (result != null)
					result.close();
				if (select_stm != null)
					select_stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	 *  执行查询语句(处理存在多个字段的问题,开始和结束)
	 * @param conn				数据库连接
	 * @param sql				执行语句
	 * @param startRow			开始地址
	 * @param endRow			结束地址
	 * @param iNum				查询列数
	 * @return
	 */
	public static List selectStartTOEnd(Connection conn,String sql, String startRow, String endRow, int iNum) {
		ArrayList<String> listname =  new ArrayList<String>();  
		StringBuffer pagingSelect = new StringBuffer(100);
		pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		pagingSelect.append(sql);
		pagingSelect.append(" ) row_ where rownum <= " + endRow + ") where rownum_ > " + startRow);
		List<String> list =  DBOperate.getResultList(conn, pagingSelect.toString(), iNum);
//		for (Object object : list) {
////			System.out.println(object);
//			List<String> listTemp =  (List<String>) object;
////			for (Object object2 : listTemp) {
////				System.out.println(object2);
////			}
//			System.out.println(listTemp.get(1));
//			listname.add(listTemp.get(1));
//			
//		}
		return  list;

	}
	
	
	
}
