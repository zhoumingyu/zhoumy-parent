package top.zhoumy.system.service;

import java.util.List;

import top.zhoumy.common.service.IService;
import top.zhoumy.system.domain.SysLog;


public interface LogService extends IService<SysLog> {
	
	List<SysLog> findAllLogs(SysLog log);
	
	void deleteLogs(String logIds);
}
