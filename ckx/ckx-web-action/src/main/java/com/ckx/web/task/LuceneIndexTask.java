package com.ckx.web.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.ckx.web.core.lucene.LuceneService;

/**
 * 定时任务demo
 * 
 */
public class LuceneIndexTask extends QuartzJobBean
{
	private Log							log	= LogFactory.getLog(getClass());

	private LuceneService	luceneService;

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException
	{
		log.debug("lucene索引文件开始创建...");
		try {
			luceneService.createIndex();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setLuceneService(LuceneService luceneService)
	{
		this.luceneService = luceneService;
	}

}
