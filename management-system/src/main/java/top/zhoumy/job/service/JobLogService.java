package top.zhoumy.job.service;

import java.util.List;

import top.zhoumy.common.service.IService;
import top.zhoumy.job.domain.JobLog;


public interface JobLogService extends IService<JobLog>{

	List<JobLog> findAllJobLogs(JobLog jobLog);

	void saveJobLog(JobLog log);
	
	void deleteBatch(String jobLogIds);
}
