package top.zhoumy.job.dao;

import java.util.List;

import top.zhoumy.common.config.MyMapper;
import top.zhoumy.job.domain.Job;


public interface JobMapper extends MyMapper<Job> {
	
	List<Job> queryList();
}