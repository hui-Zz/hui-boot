package com.project.common.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTask {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	private int count = 0;

	//@Scheduled(cron = "*/6 * * * * ?")
	//private void process() {
	//	System.out.println("this is scheduler task runing  " + (count++));
	//}

	@Scheduled(fixedRate = 600000)
	public void reportCurrentTime() {
		System.out.println("现在时间：" + dateFormat.format(new Date()));
	}

}
