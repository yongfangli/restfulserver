package com.meike.restfulserver.daoservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meike.restfulserver.dao.mapper.UserMapper;
import com.meike.restfulserver.dao.po.User;
import com.meike.restfulserver.dao.po.UserExample;
import com.meike.restfulserver.daoservice.service.UserService;
import com.meike.restfulserver.exception.NotFoundDataFormDataBaseException;

@Component
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper usermapper;

	@Override
	public User findByUsername(String username) throws NotFoundDataFormDataBaseException {
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<User> users = usermapper.selectByExample(example);
		if (users.size() > 0) {
			return users.get(0);
		} else {
			throw new NotFoundDataFormDataBaseException("not found data");
		}
	}
}
