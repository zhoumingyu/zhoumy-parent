package top.zhoumy.system.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import top.zhoumy.common.service.impl.BaseService;
import top.zhoumy.system.dao.UserPhoneCodeMapper;
import top.zhoumy.system.domain.UserPhoneCode;
import top.zhoumy.system.service.UserPhoneCodeService;

@Service("userPhoneCodeService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserPhoneCodeServiceImpl extends BaseService<UserPhoneCode> implements UserPhoneCodeService {

	@Autowired
	UserPhoneCodeMapper userPhoneCodeMapper;

	@Override
	public Integer addOrUpdate(String phone, String code) {
		UserPhoneCode query = userPhoneCodeMapper.getUserPhoneCodeByPhone(phone);

		Integer result = 0;
		if (query == null) {
			query = new UserPhoneCode();
			query.setCreateTime(new Date());
			query.setPhone(phone);
			query.setPhoneCode(code);
			// result = this.save(query);
			result = userPhoneCodeMapper.insert(query);
		} else {
			query.setCreateTime(new Date());
			query.setPhoneCode(code);
			result = userPhoneCodeMapper.updateByPrimaryKey(query);
		}
		return result;
	}

	@Override
	public UserPhoneCode getUserPhoneCode(String phone) {
		return userPhoneCodeMapper.getUserPhoneCodeByPhone(phone);
	}
}
