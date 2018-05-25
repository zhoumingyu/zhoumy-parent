package top.zhoumy.system.service;

import java.util.List;

import top.zhoumy.system.domain.UserOnline;


public interface SessionService {

	List<UserOnline> list();

	boolean forceLogout(String sessionId);
}
