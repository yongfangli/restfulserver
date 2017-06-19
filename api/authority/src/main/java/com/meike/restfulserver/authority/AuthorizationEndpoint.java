package com.meike.restfulserver.authority;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jersey.ResourceConfigCustomizer;
import org.springframework.core.ErrorCoded;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meike.restfulserver.authority.jwt.JWTConstance;
import com.meike.restfulserver.authority.jwt.JWTGenerator;
import com.meike.restfulserver.common.BeanValidate;
import com.meike.restfulserver.common.ErrorCode;
import com.meike.restfulserver.common.RestResResult;
import com.meike.restfulserver.dao.mapper.UserMapper;
import com.meike.restfulserver.dao.po.User;

import javassist.expr.NewArray;

@Component
@Path("/authority")
public class AuthorizationEndpoint {
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationEndpoint.class);
	@Autowired
	private UserMapper usermapper;

	@POST
	@Path("/token")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResResult login(LoginWrapper loginWrapper) throws FileNotFoundException {
		RestResResult resResult = new RestResResult<>();
		String msg = BeanValidate.validateModel(loginWrapper);
		if (!StringUtils.isEmpty(msg)) {
			resResult.setHeadContentEx(1, null);
		}
		User user = usermapper.selectByPrimaryKey(1);
		resResult.setHeadContentEx(ErrorCode.PASSWORD_ERR, null);
		resResult.setBody(JWTGenerator.generator(user));
		// 生成jwt
		return resResult;
	}

	@POST
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResResult test(LoginWrapper loginWrapper) throws FileNotFoundException {
		String msg = BeanValidate.validateModel(loginWrapper);
		RestResResult<User> resResult = new RestResResult<>();
		User user = usermapper.selectByPrimaryKey(1);
		resResult.setBody(user);
		// 生成jwt
		return resResult;
	}
}
