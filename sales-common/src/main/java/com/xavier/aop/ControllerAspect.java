package com.xavier.aop;

import com.xavier.common.BaseResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by xavier on 2018/1/29.
 */
@Aspect
@Component
public class ControllerAspect {

	private Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

	@Pointcut("@annotation(com.xavier.annotation.WebController)")
	public void webController(){}

	@Pointcut("@annotation(com.xavier.annotation.RestfulController)")
	public void restController(){}

	@Around("webController()")
	public Object aroundWeb(ProceedingJoinPoint pjp) {
		try {
			return process(pjp);
		} catch (Throwable th) {
			logger.error("There are some errors: {}", th.getMessage());
			return "error";
		}
	}

	@Around("restController()")
	public Object aroundRest(ProceedingJoinPoint pjp) {
		try {
			return process(pjp);
		} catch (Throwable th) {
			logger.error("There are some errors: {}", th.getMessage());
			return BaseResponse.fail();
		}
	}

	private Object process(ProceedingJoinPoint pjp) throws Throwable{
		long startTime = System.currentTimeMillis();
		Signature signature = pjp.getSignature();
		logger.info("Request to: {}.{}", signature.getDeclaringTypeName(), signature.getName());

		Object rst = pjp.proceed();

		long duration = System.currentTimeMillis() - startTime;
		logger.info("Time duration: {} ms.", duration);
		return rst;
	}
}
