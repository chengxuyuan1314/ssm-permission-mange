package com.jlyk.controller;

import com.jlyk.domian.SysLog;
import com.jlyk.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    private Date visitTime;
    private Class clazz;
    private Method method;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;
    //前置通知获取开始时间
    @Before("execution(* com.jlyk.controller.*.*(..))"+
    "&& !execution(public * com.jlyk.controller.SysLogController.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
        clazz = jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        if (args==null|| args.length==0){
            method=clazz.getMethod(methodName);
        }else {
            Class[] classArgs =new Class[args.length];
            for (int i = 0; i <args.length ; i++) {
                classArgs[i]=args[i].getClass();
            }
            method=clazz.getMethod(methodName,classArgs);
        }


    }

    @After("execution(* com.jlyk.controller.*.*(..))"+
            "&& !execution(public * com.jlyk.controller.SysLogController.*(..))")
    public void doAfter(JoinPoint jp){
        long time = new Date().getTime() - visitTime.getTime();
        String url="";
        if (clazz!=null && method!=null&&clazz!= SysLog.class){
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (clazzAnnotation!=null){
                String s = clazzAnnotation.value()[0];

                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation!=null){
                    String s2 = methodAnnotation.value()[0];
                    url =s+s2;

                    //获取访问ip
                    String ip =request.getRemoteAddr();

                    //获取当前用户

                    SecurityContext context = SecurityContextHolder.getContext();
                    Authentication authentication = context.getAuthentication();
                    User user = (User) authentication.getPrincipal();
                    String username = user.getUsername();
                    SysLog sysLog = new SysLog();
                    sysLog.setVisitTime(visitTime);
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setMethod("[类名]" + clazz.getName() + "[方法名]"+ method.getName());

                    System.out.println(sysLog);
                    sysLogService.saveSysLog(sysLog);

                }
            }
        }


    }

}
