package com.giang.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.giang.mapper.UserMapper;
import com.giang.model.User;
import com.giang.util.FactoryUtil;

@Component("UserService")
public class UserService {
	//Check login of Service
	//if the user exists, then returns true else return false
	public boolean checkLoginService(User user) {
		SqlSession session = FactoryUtil.openSession();
		try {
			UserMapper mapper = session.getMapper(UserMapper.class);
			if (mapper.checkLogin(user) == null) {
				return false;
			} else {
				return true;
			}
		} finally {

			session.close();
		}
	}
}
