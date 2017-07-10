package com.meike.restfulserver.daoservice.service;

import com.meike.restfulserver.dao.po.User;
import com.meike.restfulserver.exception.NotFoundDataFormDataBaseException;

public interface UserService {
	User findByUsername(String username) throws NotFoundDataFormDataBaseException;
}
