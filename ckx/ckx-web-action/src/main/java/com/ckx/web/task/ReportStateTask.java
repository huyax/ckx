package com.ckx.web.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 定时任务demo
 * 
 */
public class ReportStateTask extends QuartzJobBean {
	private Log log = LogFactory.getLog(getClass());

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		log.debug("-----------------------------------------------------------coding here");
		log.debug("coding here");
	}

}
