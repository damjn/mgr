package com.mgr2.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.mgr2.service.UserTaskService;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	UserTaskService userTaskService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //do what you want with 
    	MyUserPrincipal user = (MyUserPrincipal) authentication.getPrincipal();
     //   String responseMsg = userTaskService.handle24hLoginTask(user.getId());
     //   System.out.println("suuuuukces " + responseMsg);
        redirectStrategy.sendRedirect(request, response, "/index_l");
    }
}
