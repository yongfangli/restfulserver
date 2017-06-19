package com.meike.restfulserver;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.meike.restfulserver.authority.AuthorizationEndpoint;

@Component
@ApplicationPath("api/v1")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(AuthorizationEndpoint.class);
		
	}

}