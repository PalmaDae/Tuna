package ru.init.fisd.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import ru.init.fisd.entity.UserEntity;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String uri = request.getRequestURI();
        if (uri.endsWith("/login") || uri.endsWith("/register") || uri.endsWith("/main") || uri.contains("/html/")) {
            chain.doFilter(req, res);
            return;
        }

        HttpSession session = request.getSession();

        UserEntity userEntity = (session != null) ? (UserEntity) session.getAttribute("user") : null;

        if (userEntity == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }


        if (uri.endsWith("/admin") && !userEntity.getRole().equals("ADMIN")) {
            response.sendRedirect(request.getContextPath() + "/main");
            return;
        }

        chain.doFilter(request,response);
    }
}
