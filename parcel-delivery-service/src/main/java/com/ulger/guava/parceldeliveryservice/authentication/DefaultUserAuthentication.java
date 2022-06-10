package com.ulger.guava.parceldeliveryservice.authentication;

import com.ulger.usermanager.api.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class DefaultUserAuthentication implements PDUserAuthentication {

	private final User user;
	private final Authentication authentication;

	public DefaultUserAuthentication(User user, Authentication authentication) {
		this.user = user;
		this.authentication = authentication;
	}

	@Override
	public Long getUserId() {
		return user.getId();
	}

	@Override
	public String getDisplayName() {
		return user.getDisplayName();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authentication.getAuthorities();
	}

	@Override
	public Object getCredentials() {
		return authentication.getCredentials();
	}

	@Override
	public Object getDetails() {
		return authentication.getDetails();
	}

	@Override
	public Object getPrincipal() {
		return authentication.getPrincipal();
	}

	@Override
	public boolean isAuthenticated() {
		return authentication.isAuthenticated();
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		authentication.setAuthenticated(isAuthenticated);
	}

	@Override
	public String getName() {
		return authentication.getName();
	}
}