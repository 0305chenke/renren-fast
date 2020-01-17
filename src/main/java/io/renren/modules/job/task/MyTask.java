package io.renren.modules.job.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component("myTask")
public class MyTask  implements ITask{
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void run(String params) {
		logger.debug("自定义的定时任务正在执行，参数为：{}", params);
		System.out.println("这是自定义的定时任务");
		for(int i = 0; i<1000; i++) {
			System.out.println("这是自定义的定时任务第"+i+"个执行方法");
		}
		
	}

}
