package com.servlet.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter("/*")
public class KoreaFilter implements Filter {


    public KoreaFilter() {

    }


	public void destroy() {

	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response; 
		
		req.setCharacterEncoding("UTF-8");
		
		String fileUri = req.getRequestURI();
		
		if(fileUri.contains(".css")) {
			resp.setContentType("text/css; charset=UTF-8");
		}else if(fileUri.contains(".js")){
			resp.setContentType("text/js; charset=UTF-8");
		}else if(fileUri.contains(".png")){
			resp.setContentType("image/png; charset=UTF-8");
		}else if(fileUri.contains(".jpg")){
			resp.setContentType("image/jpg; charset=UTF-8");
		}else {
			resp.setContentType("text/html; charset=UTF-8");
		}
			
		chain.doFilter(request, response);
	}


	public void init(FilterConfig fConfig) throws ServletException {

	}

}