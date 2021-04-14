package com.hhgs.app.filter;

import com.alibaba.fastjson.JSONObject;
import com.hhgs.app.common.BaseResponse;
import com.hhgs.app.model.DO.user.Role;
import com.hhgs.app.model.DO.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@Component
public class PermissionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;
        //如果是登录相关的，直接放行
        String url = req.getServletPath();
        System.out.println("--------------------------------" + url);
        if ((!url.contains("login")) && (!url.contains("webjars")) && (!url.contains("swagger-ui")) && (!url.contains("configuration")) && (!url.contains("swagger")) && (!url.contains("v2"))&&(!url.contains("websocket"))) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                //如果通过第一种方式没有获取到，则使用第二种方式获取
                SecurityContextImpl context = (SecurityContextImpl) req.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
                if (context == null) {
                    Writer out = rep.getWriter();
                    rep.setStatus(999);
                    rep.setContentType("application/json;charset=UTF-8");
                    out.write("no login,please login");
                    out.close();
                    return;
                }
                authentication = context.getAuthentication();
                if (authentication == null) {
                    Writer out = rep.getWriter();
                    rep.setStatus(999);
                    rep.setContentType("application/json;charset=UTF-8");
                    out.write("no login,please login");
                    out.close();
                    return;
                }
            }

            User user = (User) authentication.getPrincipal();

            List<String> urls = new ArrayList<>();
            List<Role> roles = user.getRoles();
            roles.forEach(ele -> {
                ele.getPermissions().forEach(permission -> {
                    urls.add(permission.getUrl());
                });
            });

            if (urls.indexOf(url) == -1) {
                Writer out = rep.getWriter();
                rep.setContentType("application/json;charset=UTF-8");
                rep.setStatus(401);
                out.write("User " + user.getUsername() + " has no permission to the " + url);
                out.close();
                return;
            }

            filterChain.doFilter(request, response);

        } else {
            filterChain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
