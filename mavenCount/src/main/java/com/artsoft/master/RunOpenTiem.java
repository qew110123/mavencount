package com.artsoft.master;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.artsoft.util.CommonUtil;
import com.artsoft.util.TimeTest;

public class RunOpenTiem {
	
	public static void runstatic(){
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":开 始");

		if (TimeTest.getNowTime("HH").equals("09") || TimeTest.getNowTime("HH").equals("12") || TimeTest.getNowTime("HH").equals("23")) {
			CountNum.runCount();
		}
		
		if (TimeTest.getNowTime("HH").equals("23")) {
			TimeTest tt = new TimeTest();
			String newtime = tt.getNowTime("yyyyMMdd");
			System.out.println(newtime);
			String times = CountNum.getBeforeAfterDate(newtime, -10);
			System.out.println(times);
			CountNum.deletedata(times);
			
		}
		System.out.println(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss"));
		
		CommonUtil.setLog(TimeTest.getNowTime("yyyy-MM-dd HH:mm:ss") + ":结 束");
	}

//判断数据开始时间
	public static void TimingTime(int hh , int mm ,int ss) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(Calendar.HOUR_OF_DAY, hh); // 控制时  
        calendar.set(Calendar.MINUTE, mm);       // 控制分  
        calendar.set(Calendar.SECOND, ss);       // 控制秒  
  
        Date time = calendar.getTime();         // 得出执行任务的时间,此处为今天的12：00：00  
  
        Timer timer = new Timer();  
        timer.schedule(new TimerTask() {  
            public void run() {  
                System.out.println("-------设定要指定任务--------");  
                try {
					
                	runstatic();
				} catch (Exception e) {
					// TODO: handle exception
				}
            } 
        }, time, 1000 * 60 * 60 * 1);// 这里设定将延时每天固定执行  
    }

}
