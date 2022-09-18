package com.maybank.maybank.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

public class LoggingFilter extends OncePerRequestFilter {
	
	private static final Logger logger = Logger.getLogger(LoggingFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ContentCachingRequestWrapper reqWrapper = new ContentCachingRequestWrapper(request);
		ContentCachingResponseWrapper respWrapper = new ContentCachingResponseWrapper(response);
		
		filterChain.doFilter(request, response);
		
		String reqBody = getStringValue(reqWrapper.getContentAsByteArray(), request.getCharacterEncoding());
		String respBody = getStringValue(respWrapper.getContentAsByteArray(), response.getCharacterEncoding());
	
		logger.info(response.getStatus());
	
		respWrapper.copyBodyToResponse();
	}
	
	private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
		try {
			return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
		}
		catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return "";
	}

}
