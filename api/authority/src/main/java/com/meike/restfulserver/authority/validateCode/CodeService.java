package com.meike.restfulserver.authority.validateCode;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.authenticator.SavedRequest;

/**
 * api login code interface
 * 
 * @author lyf
 *
 */
public interface CodeService {

	boolean isValidateCode(HttpServletRequest req, String code);

}
