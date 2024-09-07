package com.vvsdcpolicies.policies.utilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.vvsdcpolicies.policies.exception.VvsdcException;

@Aspect
@Component
public class LoggingAspect {
	private static final Log LOGGER = LogFactory.getLog(LoggingAspect.class);
	
	@AfterThrowing(pointcut="execution(* com.vvsdcpolicies.policies.service.PolicyServiceImpl.*(..))",throwing="exception")
	public void logServiceException(VvsdcException exception) {
		LOGGER.error(exception.getMessage(),exception);
	}
}
