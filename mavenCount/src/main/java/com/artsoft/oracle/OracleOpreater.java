package com.artsoft.oracle;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.artsoft.util.TimeTest;

public class OracleOpreater {
	public static void intoReputation(String tyPlayName, String source, String dataAmount, String vodeoType,
			String upDatedate, String playUrl, String tvType, String dataType) {
		Connection conn = DBOperate.getInstance().getConnection();

		System.out.println(tyPlayName + source + dataAmount + vodeoType + upDatedate + playUrl + tvType + dataType);
		String strSql = "INSERT INTO ODS.TEM_NETWORK_REPUTATION t (t.TVPLAY_NAME,t.SOURCE ,t.DATA_AMOUNT,t.VIDEO_TYPE,t.DATE_DATE"
				+ ",t.PLAY_URL,t.TV_TYPE,t.DATA_TYPE) VALUES (?,?,?,?,?,?,?,?)";

		List<Comparable> list = new ArrayList();
		list.add(tyPlayName);// 这里是将对象加入到list中
		list.add(Integer.parseInt(source));
		list.add(Double.parseDouble(dataAmount));
		list.add(Integer.parseInt(vodeoType));
		// list.add(TimeTest.getNowTime("yyyyMMdd"));
		list.add(TimeTest.getNowTime("yyyyMMdd"));
		list.add(playUrl);
		list.add(Integer.parseInt(tvType));
		list.add(Integer.parseInt(dataType));
		boolean bb = DBOperate.insertRecord(conn, strSql, list);
		System.out.println(bb);

	}

	/**
	 * * 数据的整体更细 查询
	 * 
	 * @param date_date
	 * @param data_type
	 *            0 总播放量/ 1 评分/ 2 评论量/ 3 搜索指数/ 4 媒体关注度
	 * @param tv_type
	 *            0 电视剧 /1 网络剧
	 * @return
	 */

	public static List selecthuoqu(String date_date, String data_type, String tv_type) {
		Connection conn = DBOperate.getInstance().getConnection();
		String sql = "select t.source,date_date ,count（*） from ODS.TEM_NETWORK_REPUTATION t where t.date_date >'"
				+ date_date + "' and t.data_type=" + data_type + "and t.tv_type=" + tv_type
				+ " group by t.date_date,t.source";
		// sql = "select t1.person_id,t1.person_name ,t1.person_url from
		// ODS.TEM_TVPLAY_ACTORS t1,(select distinct t.person_url from
		// ODS.TEM_TVPLAY_ACTORS t where t.person_id >=16873 and t.person_url is
		// not null) t2 where t1.person_url = t2.person_url";
		sql = "select t.source, t.date_date, count（ *）  from (select t1.tvplay_name, t1.date_date, t1.source,count(*) from ODS.TEM_NETWORK_REPUTATION t1  where t1.date_date > '"
				+ date_date + "'  and t1.data_type = " + data_type + " and t1.tv_type = " + tv_type
				+ "  group by t1.tvplay_name, t1.date_date, t1.source) t group by t.date_date, t.source";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}

	/**
	 * 2016年5月12日11:36:21
	 * 猫眼票房
	 * 
	 * @return
	 */

	public static List selecmaoyanpaofang(String date_date, String source) {
		Connection conn = DBOperate.getInstance().getConnection();
		String sql = "SELECT to_char(t.into_date,'yyyymmdd'),count(*) FROM ods.tem_dim_film_boxoffice t where to_char(t.into_date,'yyyymmdd')> '"
				+ date_date + "' group by to_char(t.into_date,'yyyymmdd')";

		sql = "SELECT to_char(t.into_date,'yyyymmdd'),count(*) FROM ods.tem_film_daily_boxoffice t where to_char(t.into_date,'yyyymmdd')> '"
				+ date_date + "' and source=" + source + " group by to_char(t.into_date,'yyyymmdd')";
		
		
		sql="SELECT tt.into_date,count(tt.into_date) FROM (select to_char(t.into_date,'yyyymmdd')into_date  from ods.tem_film_daily_boxoffice t where to_char(t.into_date,'yyyymmdd')> '"
				+ date_date + "' and t.source=" + source + " group by t.fid,to_char(t.into_date,'yyyymmdd'))tt group by tt.into_date";

		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
		List<String> list = DBOperate.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}

	/**
	 * 中国票房网城市数据的基本信息
	 * 
	 * @param date_date
	 * @return
	 */
	public static List seleczhongguopiaofangwangcity(String date_date) {
		Connection conn = DBOperate.getInstance().getConnection();
		String sql = "select ttt.data_date,count(ttt.data_date) from (select tt.fid , tt.data_date  from (select t.fid , t.data_date,t.into_date from"
				+ " ods.TEM_FILM_CITY  t where t.source=4 and t.data_date>'"
				+ date_date + "'  and t.city_name='全国') tt where tt.data_date=to_char(tt.into_date,'yyyymmdd')"
				+ " group by  tt.fid , tt.data_date)ttt group by ttt.data_date";

		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
		List<String> list = DBOperate.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}
	
	/**
	 * 中国票房网城市数据的基本信息
	 * 
	 * @param date_date
	 * @return
	 */
	public static List seleczhongguopiaofangwangindex(String date_date) {
		Connection conn = DBOperate.getInstance().getConnection();
		String sql = "select tt.data_date,count(tt.data_date) from (select  t.film_id , t.data_date from ods.tem_film_index t WHERE  t.source=4 and  t.data_date>'"
				+ date_date + "' and t.data_date=to_char(t.into_date,'yyyymmdd') group by  t.film_id , t.data_date) tt group by tt.data_date";

		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
		List<String> list = DBOperate.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}

	// /**
	// * 中国票房网
	// *
	// * @return
	// */
	//
	// public static List seleczhongguopiaofangwang(String date_date) {
	// Connection conn = DBOperate.getInstance().getConnection();
	// String sql="SELECT to_char(t.into_date,'yyyymmdd'),count(*) FROM
	// ods.tem_film_daily_boxoffice t where to_char(t.into_date,'yyyymmdd')> '"
	// + date_date + "' and group by to_char(t.into_date,'yyyymmdd')";
	//
	// ArrayList<String> listname = new ArrayList<String>();
	// int iNum = 2;
	// List<String> list = DBOperate.getResultList(conn, sql, iNum);
	// return (ArrayList<String>) list;
	// }

	/**
	 * 2016年4月5日15:54:05 进行数据的360指数 人物数据基本数据
	 * 
	 * @return
	 */

	public static List selectindexsopeople(String date_date) {
		Connection conn = DBOperate.getInstance().getConnection();
		String sql = "select data_date,count(*) from( select t.PERSON_ID,t.data_date from ods.tem_person_relevant_keyword t where t.data_date>'"
				+ date_date + "' group by t.data_date , t.PERSON_ID)  tt  group by tt.data_date";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
		List<String> list = DBOperate.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}

	/**
	 * 2016年4月12日16:52:02 电视剧基本信息
	 * 
	 * @return
	 */

	public static List selecttotalplayPlatform(String date_date) {
		Connection conn = DBOperate.getInstance().getConnection();
		String sql = "select t.update_time ,t.source,count(*) from  ods.TEM_DIM_TVPLAY_PLATFORM t  where t.update_time>'"
				+ date_date + "' group by t.update_time ,t.source";

		sql = "select  t1.update_time ,t1.source,count(*)  from (select t.update_time ,t.source from  ods.TEM_DIM_TVPLAY_PLATFORM t  where t.update_time>'"
				+ date_date
				+ "' group by t.update_time ,t.source ,t.tvplay_name,t.Tvplay_url) t1  group by t1.update_time ,t1.source";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 3;
		List<String> list = DBOperate.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}

	/**
	 * 2016年4月7日16:33:16 新闻量数据
	 * 
	 * @return
	 */

	public static List selectnewsnum(String date_date) {
		Connection conn = DBOperate.getInstance().getConnection();
		String sql = "select data_date ,data_type,source,count(*)  from ods.TEM_NEWS_NUM t where t.data_date>'"
				+ date_date + "'  group by t.data_date , t.data_type,t.source";

		sql = "select data_date ,data_type,source,count(*) from  (select  data_date ,data_type,source,count(*)   from ods.TEM_NEWS_NUM t where t.data_date>'"
				+ date_date
				+ "' group by  t.bid ,t.data_date  , t.data_type,t.source) tt  group by tt.data_date , tt.data_type,tt.source";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 4;
		List<String> list = DBOperate.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}

	/**
	 * * 数据的整体更细 查询
	 * 
	 * @param date_date
	 * @param DATE_TYPE
	 *            数据类型，1 微博数据(搜索指数为空) 2 搜索指数 (微博相关数据为空) 3 媒体关注度
	 * 
	 * @return
	 */

	public static List selecthuoqupeople(String date_date, String data_type) {
		Connection conn = DBOperate.getInstance().getConnection();
		// String sql = "select t.source,date_date ,count（*） from
		// ODS.TEM_NETWORK_REPUTATION t where t.date_date >'"+date_date+"' and
		// t.data_type="+data_type+"and t.tv_type="+tv_type+" group by
		// t.date_date,t.source";
		// sql = "select t1.person_id,t1.person_name ,t1.person_url from
		// ODS.TEM_TVPLAY_ACTORS t1,(select distinct t.person_url from
		// ODS.TEM_TVPLAY_ACTORS t where t.person_id >=16873 and t.person_url is
		// not null) t2 where t1.person_url = t2.person_url";
		String sql = "select date_date,count(*) from  ods.person_network_popularity t where t.date_date>'" + date_date
				+ "' and t.date_type='" + data_type + "'  group by date_date";

		sql = "select tt.date_date,count(tt.date_date) from (select date_date from ods.person_network_popularity t  where t.date_date>'"
				+ date_date + "' and t.date_type=" + data_type
				+ "  group by t.date_date , t.person_id) tt group by tt.date_date";
		ArrayList<String> listname = new ArrayList<String>();
		int iNum = 2;
		List<String> list = DBOperate.getResultList(conn, sql, iNum);
		return (ArrayList<String>) list;
	}

	/**
	 * 删除重复数据
	 * 
	 * @param date_date
	 * @param data_type
	 *            0 总播放量/ 1 评分/ 2 评论量/ 3 搜索指数/ 4 媒体关注度
	 * @param tv_type
	 *            0 电视剧 /1 网络剧
	 * @return
	 */
	public static boolean deleteRepeat(String date_date, String data_type, String tv_type) {
		Connection conn = DBOperate.getInstance().getConnection();
		String sql = "delete ODS.TEM_NETWORK_REPUTATION t where t.DATA_TYPE=" + data_type + " and t.tv_type=" + tv_type
				+ " and t.date_date>'" + date_date
				+ "'and (t.tvplay_id,t.date_date)  in (select t1.tvplay_id,t1.date_date from ODS.TEM_NETWORK_REPUTATION t1 where t1.DATA_TYPE="
				+ data_type + " and t1.tv_type=" + tv_type + " and t1.date_date>'" + date_date
				+ "' group by t1.tvplay_id,t1.date_date having count(*) > 1)  and rowid not in (select min(rowid) from ODS.TEM_NETWORK_REPUTATION t2 where t2.DATA_TYPE="
				+ data_type + " and t2.tv_type=" + tv_type + " and t2.date_date>'" + date_date
				+ "' group by t2.tvplay_id,t2.date_date having count(*)>1) ";
		boolean bb = DBOperate.executeRecord(conn, sql);
		System.out.println(bb);
		return bb;
	}

	/**
	 * 删除重复数据 ren
	 * 
	 * @param date_date
	 * @param DATE_TYPE
	 *            数据类型，1 微博数据(搜索指数为空) 2 搜索指数 (微博相关数据为空) 3 媒体关注度
	 * @return
	 */
	public static boolean deleteRepeatPeople(String date_date, String date_type) {
		Connection conn = DBOperate.getInstance().getConnection();
		// String sql = "delete ODS.TEM_NETWORK_REPUTATION t where
		// t.DATA_TYPE="+data_type+" and t.tv_type="+tv_type+" and
		// t.date_date>'"+date_date+"'and (t.tvplay_id,t.date_date) in (select
		// t1.tvplay_id,t1.date_date from ODS.TEM_NETWORK_REPUTATION t1 where
		// t1.DATA_TYPE="+data_type+" and t1.tv_type="+tv_type+" and
		// t1.date_date>'"+date_date+"' group by t1.tvplay_id,t1.date_date
		// having count(*) > 1) and rowid not in (select min(rowid) from
		// ODS.TEM_NETWORK_REPUTATION t2 where t2.DATA_TYPE="+data_type+" and
		// t2.tv_type="+tv_type+" and t2.date_date>'"+date_date+"' group by
		// t2.tvplay_id,t2.date_date having count(*)>1) ";
		String sql = "delete ods.person_network_popularity t where t.date_type=" + date_type + " and  t.date_date>'"
				+ date_date
				+ "' and (t.person_id,t.date_date) in (select t1.person_id ,t1.date_date from ods.person_network_popularity t1  where t1.date_type="
				+ date_type + " and  t1.date_date>'" + date_date
				+ "' group by t1.person_id,t1.date_date  having count(*) > 1) and rowid not in (select min(rowid) from ods.person_network_popularity t2 where t2.date_type="
				+ date_type + " and  t2.date_date>'" + date_date
				+ "' group by t2.person_id,t2.date_date having count(*)>1) ";
		boolean bb = DBOperate.executeRecord(conn, sql);
		System.out.println(bb);
		return bb;

	}

	public static void main(String[] args) {
		// OracleOpreater.selecthuoqu(times, "3", "0");
		// List<String> listArray =selecthuoqu("20160104","3","0");
		// for (Object Objstring : listArray) {
		// System.out.println(Objstring);
		// List<String> listTemp = (List<String>) Objstring;
		// System.out.println(listTemp.get(0));
		// }
		deleteRepeat("20160105", "4", "0");
	}

}
