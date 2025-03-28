package com.lijs.nex.auth.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理器
 * AuthenticationFailureHandler 只会处理 AuthenticationException 及其子类的异常，比如：
 * BadCredentialsException
 * DisabledException
 * LockedException
 * AccountExpiredException
 */
@Component
public class CustomAuthFailureHandler implements AuthenticationFailureHandler {

    private final Logger logger = LoggerFactory.getLogger(CustomAuthFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.error("登录失败...{}", exception.getMessage());
        // 获取当前应用的 contextPath (动态前缀，如 "/web")
        String contextPath = request.getContextPath();
        // 失败后重定向到登录页，并附带错误信息
        response.sendRedirect(contextPath + "/auth/login?error=password error");
    }
}
