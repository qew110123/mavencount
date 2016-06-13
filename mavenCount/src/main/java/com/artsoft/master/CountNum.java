package com.artsoft.master;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.artsoft.mysql.MysqlOperate;
import com.artsoft.oracle.OracleOpreater;
import com.artsoft.util.TimeTest;

public class CountNum {

	public static void totalplay(String times) {

		List<String> listArray = OracleOpreater.selecthuoqu(times, "0", "0");
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			String ids = listTemp.get(0);
			String datas = listTemp.get(1);
			String counts = listTemp.get(2);
			intoString(ids, datas, counts, "总播放量", "电视剧");
		}

		listArray = OracleOpreater.selecthuoqu(times, "1", "0");
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			String ids = listTemp.get(0);
			String datas = listTemp.get(1);
			String counts = listTemp.get(2);
			intoString(ids, datas, counts, "评分", "电视剧");
		}

		listArray = OracleOpreater.selecthuoqu(times, "2", "0");
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			String ids = listTemp.get(0);
			String datas = listTemp.get(1);
			String counts = listTemp.get(2);
			intoString(ids, datas, counts, "评论量", "电视剧");
		}

		// listArray = OracleOpreater.selecthuoqu(times,"3");
		// for (Object Objstring : listArray) {
		// System.out.println(Objstring);
		// List<String> listTemp = (List<String>) Objstring;
		// String ids = listTemp.get(0);
		// String datas = listTemp.get(1);
		// String counts = listTemp.get(2);
		// intoString(ids, datas, counts,"搜索指数");
		// }
		//
		// listArray = OracleOpreater.selecthuoqu(times,"4");
		// for (Object Objstring : listArray) {
		// System.out.println(Objstring);
		// List<String> listTemp = (List<String>) Objstring;
		// String ids = listTemp.get(0);
		// String datas = listTemp.get(1);
		// String counts = listTemp.get(2);
		// intoString(ids, datas, counts,"媒体关注度");
		// }

	}

	// 网络剧
	private static void totalplaywangluo(String times) {
		// TODO Auto-generated method stub
		List<String> listArray = OracleOpreater.selecthuoqu(times, "0", "1");
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			String ids = listTemp.get(0);
			String datas = listTemp.get(1);
			String counts = listTemp.get(2);
			intoString(ids, datas, counts, "总播放量", "网络剧");
		}

		listArray = OracleOpreater.selecthuoqu(times, "1", "1");
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			String ids = listTemp.get(0);
			String datas = listTemp.get(1);
			String counts = listTemp.get(2);
			intoString(ids, datas, counts, "评分", "网络剧");
		}

		listArray = OracleOpreater.selecthuoqu(times, "2", "1");
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			String ids = listTemp.get(0);
			String datas = listTemp.get(1);
			String counts = listTemp.get(2);
			intoString(ids, datas, counts, "评论量", "网络剧");
		}
	}

	// 电影
	private static void totalplayMovie(String times) {
		// TODO Auto-generated method stub
		List<String> listArray = OracleOpreater.selecthuoqu(times, "0", "3");
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			String ids = listTemp.get(0);
			String datas = listTemp.get(1);
			String counts = listTemp.get(2);
			intoString(ids, datas, counts, "总播放量", "电影");
		}

		listArray = OracleOpreater.selecthuoqu(times, "1", "3");
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			String ids = listTemp.get(0);
			String datas = listTemp.get(1);
			String counts = listTemp.get(2);
			intoString(ids, datas, counts, "评分", "电影");
		}

		listArray = OracleOpreater.selecthuoqu(times, "2", "3");
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			String ids = listTemp.get(0);
			String datas = listTemp.get(1);
			String counts = listTemp.get(2);
			intoString(ids, datas, counts, "评论量", "电影");
		}
	}

	// 猫眼票房网
	private static void maoyanpiaofang(String times) {
		List<String> listArray = OracleOpreater.selecmaoyanpaofang(times, "1");
		// listArray = OracleOpreater.selecthuoqu(times, "2", "3");
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			// String ids = listTemp.get(0);
			String datas = listTemp.get(0);
			String counts = listTemp.get(1);
			intoString("猫眼数据", datas, counts, "", "电影票房");
		}

	}

	// 中国票房网
	private static void zhongguopiaofangwang(String times) {
		List<String> listArray = OracleOpreater.selecmaoyanpaofang(times, "4");
		// listArray = OracleOpreater.selecthuoqu(times, "2", "3");
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			// String ids = listTemp.get(0);
			String datas = listTemp.get(0);
			String counts = listTemp.get(1);
			intoString("中国票房网", datas, counts, "", "电影票房");
		}

	}

	/**
	 * 中国票房网城市 
	 * 2016年5月6日11:15:51
	 * 
	 * @param times
	 */

	private static void zhongguopiaofangwangcity(String times) {
		List<String> listArray = OracleOpreater.seleczhongguopiaofangwangcity(times);
		// listArray = OracleOpreater.selecthuoqu(times, "2", "3");
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			// String ids = listTemp.get(0);
			String datas = listTemp.get(0);
			String counts = listTemp.get(1);
			intoString("中国票房网城市", datas, counts, "", "电影票房");
		}

	}
	/**
	 * 中国票房网指数信息
	 * 2016年5月6日14:52:27
	 * @param times
	 */
	private static void zhongguopiaofangwangindex(String times) {
		// TODO Auto-generated method stub
		List<String> listArray = OracleOpreater.seleczhongguopiaofangwangindex(times);
		// listArray = OracleOpreater.selecthuoqu(times, "2", "3");
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			// String ids = listTemp.get(0);
			String datas = listTemp.get(0);
			String counts = listTemp.get(1);
			intoString("中国票房网指数信息", datas, counts, "", "电影票房");
		}
	}

	// 2016年4月5日15:52:17
	// 360指数 人物数据基本数据
	private static void indexsopeople360(String times) {
		// TODO Auto-generated method stub
		List<String> listArray = OracleOpreater.selectindexsopeople(times);
		// listArray = OracleOpreater.selecthuoqu(times, "2", "3");
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			// String ids = listTemp.get(0);
			String datas = listTemp.get(0);
			String counts = listTemp.get(1);
			intoString("360指数", datas, counts, "相关搜索趋势", "人");
		}

	}

	/**
	 * 2016年4月7日16:32:22 新闻搜索量
	 * 
	 * @param times
	 */

	private static void totalplayPlatform(String times) {
		// TODO Auto-generated method stub
		List<String> listArray = OracleOpreater.selecttotalplayPlatform(times);
		// listArray = OracleOpreater.selecthuoqu(times, "2", "3");
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			String datas = listTemp.get(0);
			String ids = listTemp.get(1);
			String counts = listTemp.get(2);
			intoString(ids, datas, counts, "基本信息", "电视剧");
		}

	}

	/**
	 * 2016年4月12日16:51:08 电视剧基本信息
	 * 
	 * @param times
	 */

	private static void newsnum(String times) {
		// TODO Auto-generated method stub
		List<String> listArray = OracleOpreater.selectnewsnum(times);
		// listArray = OracleOpreater.selecthuoqu(times, "2", "3");
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			// String ids = listTemp.get(0);
			String datas = listTemp.get(0);
			String data_type = listTemp.get(1);
			String source = listTemp.get(2);
			String counts = listTemp.get(3);
			if (data_type.equals("1") && source.equals("1")) {
				intoString("百度", datas, counts, "搜索量", "人");
			}
			if (data_type.equals("2") && source.equals("1")) {
				intoString("百度", datas, counts, "搜索量", "电视剧");
			}
			// intoString("360指数", datas, counts, "相关搜索趋势", "人");
		}

	}

	public static void totalplay360(String times) {

		// 先进性数据去重
		// OracleOpreater.deleteRepeat(times, "3", "0");
		// 数据整理条数 搜索指数
		List<String> listArray = OracleOpreater.selecthuoqu(times, "3", "0");
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			String ids = listTemp.get(0);
			String datas = listTemp.get(1);
			String counts = listTemp.get(2);
			intoString360(ids, datas, counts, "搜索指数");
		}

		// 先进性数据去重 媒体关注度
		// OracleOpreater.deleteRepeat(times, "4", "0");
		// 数据整理条数 媒体关注度
		listArray = OracleOpreater.selecthuoqu(times, "4", "0");
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			String ids = listTemp.get(0);
			String datas = listTemp.get(1);
			String counts = listTemp.get(2);
			intoString360(ids, datas, counts, "媒体关注度");
		}

	}

	public static void totalplaypeople360(String times) {

		// // 先进性数据去重
		// OracleOpreater.deleteRepeatPeople(times, "2");
		// // 数据整理条数 搜索指数
		List<String> listArray = OracleOpreater.selecthuoqupeople(times, "2");
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			String ids = "0";
			String datas = listTemp.get(0);
			String counts = listTemp.get(1);
			intoStringPeople360(ids, datas, counts, "搜索指数");
		}

		// // 先进性数据去重 媒体关注度
		// OracleOpreater.deleteRepeatPeople(times, "3");
		// // 数据整理条数 媒体关注度
		listArray = OracleOpreater.selecthuoqupeople(times, "3");
		for (Object Objstring : listArray) {
			System.out.println(Objstring);
			List<String> listTemp = (List<String>) Objstring;
			String ids = "0";
			String datas = listTemp.get(0);
			String counts = listTemp.get(1);
			intoStringPeople360(ids, datas, counts, "媒体关注度");
		}

	}

	/**
	 * deletedata
	 * 统一删除多余数据
	 * 2016年5月6日14:34:14
	 * @param times
	 */
	public  static void deletedata(String times) {
		// TODO Auto-generated method stub
		// 先进性数据去重
		OracleOpreater.deleteRepeat(times, "3", "0");
		// 先进性数据去重 媒体关注度
		OracleOpreater.deleteRepeat(times, "4", "0");
		// 先进性数据去重
		OracleOpreater.deleteRepeatPeople(times, "2");
		// 先进性数据去重 媒体关注度
		OracleOpreater.deleteRepeatPeople(times, "3");
	}

	public static void intoString360(String ids, String datas, String counts, String class_one) {

		// String sounce=Sourcenum(ids);
		// List<String> listArray = MysqlOperate.selecartcount("总播放量", sounce,
		// datas);
		List<String> listArray = MysqlOperate.selecartcount(class_one, "", datas, "电视剧");
		if (listArray.size() == 0) {
			// MysqlOperate.intoReputation("总播放量", sounce, counts, datas);
			MysqlOperate.intoReputation(class_one, "", counts, datas, "电视剧");
		} else {
			List<String> listTemp = (List<String>) (Object) (listArray.get(0));
			MysqlOperate.updateReputation(counts, listTemp.get(0));
		}
	}

	public static void intoStringPeople360(String ids, String datas, String counts, String class_one) {

		// String sounce=Sourcenum(ids);
		// List<String> listArray = MysqlOperate.selecartcount("总播放量", sounce,
		// datas);
		List<String> listArray = MysqlOperate.selecartcount(class_one, "", datas, "人");
		if (listArray.size() == 0) {
			// MysqlOperate.intoReputation("总播放量", sounce, counts, datas);
			MysqlOperate.intoReputation(class_one, "", counts, datas, "人");
		} else {
			List<String> listTemp = (List<String>) (Object) (listArray.get(0));
			MysqlOperate.updateReputation(counts, listTemp.get(0));
		}
	}

	/**
	 * 
	 * @param ids
	 *            = class_two
	 * @param datas
	 *            =count_data
	 * @param counts=
	 *            count_num
	 * @param class_one
	 *            = class_one
	 * @param class_three
	 *            = class_three
	 */
	public static void intoString(String class_two, String datas, String counts, String class_one, String class_three) {

		String sounce = Sourcenum(class_two);
		// List<String> listArray = MysqlOperate.selecartcount("总播放量", sounce,
		// datas);
		List<String> listArray = MysqlOperate.selecartcount(class_one, sounce, datas, class_three);
		if (listArray.size() == 0) {
			// MysqlOperate.intoReputation("总播放量", sounce, counts, datas);
			MysqlOperate.intoReputation(class_one, sounce, counts, datas, class_three);
		} else {
			List<String> listTemp = (List<String>) (Object) (listArray.get(0));
			MysqlOperate.updateReputation(counts, listTemp.get(0));
		}
	}

	public static String Sourcenum(String numSoutce) {
		// 1优酷 2 爱奇艺 3 腾讯 4 搜狐 5 乐视 6 ppTV 7芒果TV 8 迅雷看看
		String returnString = "";
		try {
			switch (Integer.parseInt(numSoutce)) {
			case 1:
				returnString = "优酷";
				break;
			case 2:
				returnString = "爱奇艺";
				break;
			case 3:
				returnString = "腾讯";
				break;
			case 4:
				returnString = "搜狐";
				break;
			case 5:
				returnString = "乐视";
				break;
			case 6:
				returnString = "ppTV";
				break;
			case 7:
				returnString = "芒果TV";
				break;
			case 8:
				returnString = "迅雷看看";
				break;
			case 9:
				returnString = "豆瓣";
				break;
			default:
				returnString = numSoutce;
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			returnString = numSoutce;
		}

		return returnString;

	}

	/**
	 * 
	 * @param datestr
	 *            日期字符串
	 * @param day
	 *            相对天数，为正数表示之后，为负数表示之前
	 * @return 指定日期字符串n天之前或者之后的日期
	 */
	public static String getBeforeAfterDate(String datestr, int day) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		java.sql.Date olddate = null;
		try {
			df.setLenient(false);
			olddate = new java.sql.Date(df.parse(datestr).getTime());
		} catch (ParseException e) {
			throw new RuntimeException("日期转换错误");
		}
		Calendar cal = new GregorianCalendar();
		cal.setTime(olddate);

		int Year = cal.get(Calendar.YEAR);
		int Month = cal.get(Calendar.MONTH);
		int Day = cal.get(Calendar.DAY_OF_MONTH);

		int NewDay = Day + day;

		cal.set(Calendar.YEAR, Year);
		cal.set(Calendar.MONTH, Month);
		cal.set(Calendar.DAY_OF_MONTH, NewDay);
		// System.out.println(df.format(cal.getTimeInMillis()));

		return df.format(cal.getTimeInMillis());
	}

	public static void runCount() {
		TimeTest tt = new TimeTest();
		// System.out.println("获取当天日期:"+tt.getNowTime("yyyyMMdd"));
		String newtime = tt.getNowTime("yyyyMMdd");
		System.out.println(newtime);
		String times = getBeforeAfterDate(newtime, -10);
		System.out.println(times);
		// 电视剧播放量
		totalplay(times);
		// 网络剧播放量
		totalplaywangluo(times);
		// 电影播放量
		totalplayMovie(times);

		// 猫眼票房
		maoyanpiaofang(times);

		// 中国票房网
		zhongguopiaofangwang(times);

		// 中国票房网 城市基本信息
		zhongguopiaofangwangcity(times);
		
		// 中国票房网  指数信息
		zhongguopiaofangwangindex(times);

		// 360指数 人物数据基本数据
		// 2016年4月5日15:51:10
		indexsopeople360(times);

		// 新闻搜索量
		// 2016年4月7日16:31:01
		newsnum(times);

		// 电视剧基本信息
		// 2016年4月12日16:49:23
		totalplayPlatform(times);

		///// 360指数电视剧
		totalplay360(times);

		// 360指数人
		totalplaypeople360(times);
	}

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runCount();
		// TimeTest tt = new TimeTest();
		// String newtime = tt.getNowTime("yyyyMMdd");
		// System.out.println(newtime);
		// String times = getBeforeAfterDate(newtime, -10);
		// totalplaywangluo(times);.
//		TimeTest tt = new TimeTest();
//		String newtime = tt.getNowTime("yyyyMMdd");
//		System.out.println(newtime);
//		String times = CountNum.getBeforeAfterDate(newtime, -10);
//		System.out.println(times);
//		CountNum.deletedata(times);
	}

}
