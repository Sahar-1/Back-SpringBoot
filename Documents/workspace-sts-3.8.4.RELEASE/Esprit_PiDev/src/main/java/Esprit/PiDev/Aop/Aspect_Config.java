package Esprit.PiDev.Aop;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class Aspect_Config {
	long First_time;
	long Second_time;
	Logger logAspect = Logger.getLogger(this.getClass().getName());
	@Pointcut("call(* *.*.main())")	
	public void call_Main()  {}
	
	@Before("call_Main()")
	public void Before_Call_Main(JoinPoint thisJoinPoint)
	{
		First_time = System.currentTimeMillis(); logAspect.info("***********");
		logAspect.info("before : " + thisJoinPoint.getSignature());
	}
	
	@After("call_Main()")
	public void After_Call_Main(JoinPoint thisJoinPoint)
	{
		Second_time = System.currentTimeMillis(); logAspect.info("***********");
		logAspect.info("after : " + thisJoinPoint.getSignature());
		logAspect.info("execution time : " +(Second_time - First_time));
	}
		
	
}
