package com.meike.restfulserver.user;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meike.restfulserver.common.RestResResult;

@Path("user")
public class UserApiEndpoint {
	private static final Logger logger = LoggerFactory.getLogger(UserApiEndpoint.class);

	/**
	 * add a new user,in other words,to register a new user
	 * @return
	 */
	@POST
	@Path("users")
	public RestResResult register() {
		
		return null;
	}
}
