package com.mgr2.configuration;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mgr2.model.User;

public class MyUserPrincipal implements UserDetails, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public User user;
	
	public MyUserPrincipal(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return user.getRoles();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public Integer getId(){
		return user.getId();
	}
	
	public Integer getPoints(){
		return user.getPoints();
	}

}
