package com.meike.restfulserver.authority;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.meike.restfulserver.authority.jwt.JWTGenerator;
import com.meike.restfulserver.authority.validateCode.RestfulCaptchaService;
import com.meike.restfulserver.authority.wrap.LoginWrapper;
import com.meike.restfulserver.common.BeanValidate;
import com.meike.restfulserver.common.ErrorCode;
import com.meike.restfulserver.common.RestResResult;
import com.meike.restfulserver.dao.po.User;
import com.meike.restfulserver.daoservice.impl.UserServiceImpl;
import com.meike.restfulserver.exception.NotFoundDataFormDataBaseException;

@Component
@Path("authority")
public class AuthorizationEndpoint {
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationEndpoint.class);
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private RestfulCaptchaService restfulCaptchaService;

	@POST
	@Path("token")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResResult login(LoginWrapper loginWrapper, @Context HttpServletRequest request)
			throws FileNotFoundException {
		RestResResult resResult = new RestResResult<>();
		String msg = BeanValidate.validateModel(loginWrapper);
		if (!StringUtils.isEmpty(msg)) {
			resResult.setHeadContentEx(1, null);
		} else if (!restfulCaptchaService.isValidateCode(request, loginWrapper.getCode())) {
			resResult.setHeadContentEx(ErrorCode.CAPTCHA_ERR);
		} else {
			try {
				User user = userService.findByUsername(loginWrapper.getUsername());
				if (!user.getPassword().equals(loginWrapper.getPassword())) {
					resResult.setHeadContentEx(ErrorCode.PASSWORD_ERR);
				}
				// 生成jwt
				Map<String, Object> tokenMap = new HashMap<>();
				tokenMap.put("token", JWTGenerator.generator(user));
				resResult.setBody(tokenMap);
			} catch (NotFoundDataFormDataBaseException e) {
				logger.info(e.getMessage());
				resResult.setHeadContentEx(ErrorCode.USER_NOT_FOUND);
			}
		}
		logger.info(loginWrapper.getUsername() + " login success");
		return resResult;
	}

	@POST
	@Path("test")
	@Produces(MediaType.APPLICATION_JSON)
	public RestResResult test(LoginWrapper loginWrapper) throws FileNotFoundException {
		String msg = BeanValidate.validateModel(loginWrapper);
		RestResResult<User> resResult = new RestResResult<>();
		return resResult;
	}

	@GET
	@Path("captcha")
	public void getCaptcha(@Context HttpServletResponse response, @Context HttpServletRequest req) {
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		BufferedImage bufferedImage = restfulCaptchaService.generateCaptchaImage(req);
		ServletOutputStream servletOutputStream = null;
		try {
			servletOutputStream = response.getOutputStream();
			// write the image to the servlet output stream
			logger.info("tring to output buffered image to servlet output stream");
			ImageIO.write(bufferedImage, "jpg", servletOutputStream);
			servletOutputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				servletOutputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	public 
}
