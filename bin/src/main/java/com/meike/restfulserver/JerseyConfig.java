package com.meike.restfulserver;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.meike.restfulserver.authority.AuthorizationEndpoint;
import com.meike.restfulserver.authority.filter.TokenValidateFilter;

@Component
@ApplicationPath("api/v1")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(AuthorizationEndpoint.class);
		
	}

}