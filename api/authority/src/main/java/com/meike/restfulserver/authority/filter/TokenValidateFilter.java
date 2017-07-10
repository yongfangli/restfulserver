package com.meike.restfulserver.authority.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.ResolverUtil.IsA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meike.restfulserver.authority.AuthorizationEndpoint;
import com.meike.restfulserver.authority.jwt.JWTChecker;
import com.meike.restfulserver.common.JsonUtils;
import com.meike.restfulserver.common.RestResResult;

/**
 * token验证的过滤器，负责校验请求头里的token
 * @author lyf
 *
 */
@Component
public class TokenValidateFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(TokenValidateFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("tokenValidateFilter initiate....");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		logger.info("request url is " + req.getRequestURL().toString());
		HttpServletResponse res = (HttpServletResponse) response;
		JWTChecker jwtChecker = new JWTChecker(req);
		if (!notAuthorizedUrl(request)) {
			if (!jwtChecker.checkJWTFromHeader(req)) {
				// 返回错误信息
				RestResResult resResult = RestResResult.Ok();
				resResult.setHeadContent(1001, "invalidate token,please refresh the token");
				try {
					String result = JsonUtils.obj2json(resResult);
					res.getWriter().write(result);
					return;
				} catch (Exception e) {
					logger.info("a exception has occur,details as follow" + e.getMessage());
					res.getWriter().close();
				}
			}
		}
		chain.doFilter(request, response);
	}

	private boolean notAuthorizedUrl(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURL().toString();
		logger.debug("request url:" + url);
		if (url.indexOf("token") > 0 || url.indexOf("captcha") > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void destroy() {
	}

}
