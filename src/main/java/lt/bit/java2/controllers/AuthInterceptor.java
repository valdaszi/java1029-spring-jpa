package lt.bit.java2.controllers;

import lt.bit.java2.ann.Auth;
import lt.bit.java2.entities.Account;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod)handler;
//            Method method = handlerMethod.getMethod();
//            Auth auth = method.getAnnotation(Auth.class);
            Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
            if (auth != null) {
                System.out.println("Yra Auth!!!");
                HttpSession session = request.getSession(false);
                if (session == null) {
                    response.sendRedirect("/mvc/auth/login");
                    return false;
                }
                Object acc = session.getAttribute("user");
                Account account = acc instanceof Account ? (Account)acc : null;
                if (!"ADMIN".equals(account.getRole()) &&
                        auth.value().length > 0 &&
                        !Arrays.asList(auth.value()).contains(account.getRole())) {
                    response.sendRedirect("/mvc/auth/login");
                    return false;
                }

            } else {
                System.out.println("Nera Auth!!!");
            }
        }
        return true;
    }
}
