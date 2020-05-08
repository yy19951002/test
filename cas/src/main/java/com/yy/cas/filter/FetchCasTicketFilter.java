package com.yy.cas.filter;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class FetchCasTicketFilter implements Filter {

    public static  Map<String, String> map = new HashMap<String, String>();



    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String queryString = request.getQueryString();
        if(queryString != null && queryString.contains("ticket")){
            System.out.println(queryString);
            String ticket = queryString.substring(queryString.indexOf("=") + 1, queryString.length());
            System.out.print(ticket);
            map.put("ticket",ticket);
            request.getSession().setAttribute("ticket_", ticket);
        }

        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }



}
