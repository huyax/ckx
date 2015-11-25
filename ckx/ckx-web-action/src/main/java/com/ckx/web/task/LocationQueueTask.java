package com.ckx.web.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ckx.web.core.location.LocationService;

/**
 * 定时任务demo
 * 
 */
public class LocationQueueTask extends QuartzJobBean
{
	private Log							log	= LogFactory.getLog(getClass());

	private LocationService	locationService;

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException
	{
		log.debug("redis定位队列扫描任务启动，开始扫描...");
		locationService.scanQueue();
	}

	public void setLocationService(LocationService locationService)
	{
		this.locationService = locationService;
	}

}
