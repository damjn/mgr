package com.mgr2.configuration;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

public class LoginListener implements ApplicationListener<AuthenticationSuccessEvent> {

	  @Override
	  public void onApplicationEvent(final AuthenticationSuccessEvent event) {
		  System.out.println("elo pozdro");
	  }

	}