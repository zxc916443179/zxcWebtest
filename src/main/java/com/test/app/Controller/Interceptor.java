package com.test.app.Controller;

import com.test.app.Entity.User.UserAuth;
import com.test.app.Service.UserServices.UserAuthService;
import com.test.app.Utils.SpringUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Interceptor implements HandlerInterceptor {
    private UserAuthService userAuthService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession httpSession = httpServletRequest.getSession(true);
        if (httpServletRequest.getMethod().equals("OPTIONS")) {
            return true;
        }
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:8081");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST");
        String path = httpServletRequest.getRequestURI();
        if (httpSession.getAttribute(httpSession.getId()) == null) {
            httpServletResponse.setStatus(200);
            throw new RETException(101);
        }
        userAuthService = (UserAuthService)SpringUtil.getBean("userAuthServiceImpl");
        String sessionId = httpSession.getId();
        String account = (String)httpSession.getAttribute(sessionId);
        UserAuth auth = userAuthService.findByAccount(account).get(0);
        String role = auth.getUser().getRole().getRole();
        if (role.equals("militaryBase")) {
            if (path.indexOf("dataBaseManager") != -1 || path.indexOf("centralManager") != -1) throw new RETException(105);
        } else if (role.equals("centralManager")) {
            if (path.indexOf("militaryBase") != -1 || path.indexOf("dataBaseManager") != -1) throw new RETException(105);
        } else if (role.equals("dataBaseManager")) {
            if (path.indexOf("militaryBase") != -1 || path.indexOf("centralManager") != -1) throw new RETException(105);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
