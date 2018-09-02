package top.zhoumy.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import top.zhoumy.common.service.impl.BaseService;
import top.zhoumy.system.dao.UserWaliMapper;
import top.zhoumy.system.domain.UserWali;
import top.zhoumy.system.service.UserWaliService;

@Service("userWaliService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserWaliServiceImpl extends BaseService<UserWali> implements UserWaliService {

	@Autowired
	UserWaliMapper userWaliMapper;

	@Override
	public UserWali getUser(String phone) {
		return userWaliMapper.getUserPhoneCodeByPhone(phone);
	}

	@Override
	public List<UserWali> findUserWali(UserWali user) {
		return userWaliMapper.findUserWali(user);
	}

}
