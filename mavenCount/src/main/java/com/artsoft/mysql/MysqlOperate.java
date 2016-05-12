package com.artsoft.mysql;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.artsoft.oracle.DBOperate;
import com.artsoft.util.TimeTest;

public class MysqlOperate {

	public static List selecthuoqu(String date_date) {
		Connection conn = DBmysqlOperate.getInstance().getConnection();
		String sql = "select art_count.id, art_count.class_one,class_two from art_count";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}

	/**
	 * 查询数据中是否有重复的数据,并且进行修改操作
	 * 
	 * @param class_one
	 * @param class_two
	 * @param count_data
	 * @return
	 */
	public static List selecartcount(String class_one, String class_two, String count_data,String class_three) {
		Connection conn = DBmysqlOperate.getInstance().getConnection();
		String sql = "SELECT * from art_count where art_count.class_one='总播放量'and art_count.class_two='优酷' and art_count.count_data='20151214' order by id";
		sql = "SELECT * from art_count where art_count.class_one='" + class_one + "'and art_count.class_two='"
				+ class_two + "' and art_count.count_data='" + count_data + "' and art_count.class_three='"+class_three+"' order by id LIMIT 1";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}

	
	public static boolean intoReputation(String class_one, String class_two, String count_num, String count_data,String class_three) {
		Connection conn = DBmysqlOperate.getInstance().getConnection();

		String strSql = "INSERT INTO art_count (art_count.class_one,art_count.class_two,art_count.count_num,art_count.count_data,art_count.class_three)  VALUES(?,?,?,?,?)";

		List<Comparable> list = new ArrayList();
		list.add(class_one);// 这里是将对象加入到list中
		list.add(class_two);
		list.add(Double.parseDouble(count_num));
		list.add(count_data);
		list.add(class_three);
		// list.add(TimeTest.getNowTime("yyyyMMdd"));
		// list.add(TimeTest.getNowTime("yyyyMMdd"));
		boolean bb = DBOperate.insertRecord(conn, strSql, list);
		System.out.println(bb);
		return bb;

	}

	public static boolean updateReputation(String count_num, String id) {
		Connection conn = DBmysqlOperate.getInstance().getConnection();

		String strSql = "update art_count SET art_count.count_num=?,art_count.updata=? where art_count.id=?";

		List<Comparable> list = new ArrayList();
		list.add(Double.parseDouble(count_num));
		list.add(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		list.add(Integer.parseInt(id));
		// list.add(TimeTest.getNowTime("yyyyMMdd"));
		// list.add(TimeTest.getNowTime("yyyyMMdd"));
		boolean bb = DBOperate.insertRecord(conn, strSql, list);
		System.out.println(bb);
		return bb;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		List<String> listArray = selecartcount("总播放量", "优酷", "20151214");
//		for (Object Objstring : listArray) {
//			System.out.println(Objstring);
//			List<String> listTemp = (List<String>) Objstring;
//			System.out.println(listTemp.get(0));
//		}
		//[总播放量, 优酷, 11532.0, 20151207]
//		intoReputation("总播放量", "优酷", "11532", "20151207","电视剧");
	}

}
