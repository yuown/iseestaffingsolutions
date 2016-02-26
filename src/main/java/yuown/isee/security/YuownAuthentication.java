package yuown.isee.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class YuownAuthentication implements Authentication {

	private static final long serialVersionUID = 2640815854559046081L;

	private User user;

	private boolean authenticated = true;

	public YuownAuthentication(User user) {
		this.user = user;
	}

	public String getName() {
		return user.getUsername();
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities();
	}

	public Object getCredentials() {
		return user.getPassword();
	}

	public User getDetails() {
		return user;
	}

	public Object getPrincipal() {
		return user.getUsername();
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
		this.authenticated = authenticated;
	}
}
