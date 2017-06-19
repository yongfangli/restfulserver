package com.meike.restfulserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import com.meike.restfulserver.authority.jwt.JWTConstance;

@SpringBootApplication
public class Server {
	
	public static void main(String[] args) {
		SpringApplication.run(Server.class, args);
	}

}
