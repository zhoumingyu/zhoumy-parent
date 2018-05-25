package top.zhoumy.system.dao;

import java.util.List;

import top.zhoumy.common.config.MyMapper;
import top.zhoumy.system.domain.User;
import top.zhoumy.system.domain.UserWithRole;


public interface UserMapper extends MyMapper<User> {

	List<User> findUserWithDept(User user);
	
	List<UserWithRole> findUserWithRole(Long userId);
	
	User findUserProfile(User user);
}