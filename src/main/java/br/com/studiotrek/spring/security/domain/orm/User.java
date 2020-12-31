package br.com.studiotrek.spring.security.domain.orm;

import br.com.studiotrek.spring.security.domain.enumerable.JwtRoles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class User implements UserDetails {

	private String id;
	private String password;
	private Boolean enabled;
	private List<JwtRoles> roles;

	public User() {
	}

	public User(String username) {
		this.id = username;
	}

	public User(String username, String password, Boolean enabled, List<JwtRoles> roles) {
		this.id = username;
		this.password = password;
		this.enabled = enabled;
		this.roles = roles;
	}

	@Override
	public String getUsername() {
		return id;
	}

	public void setUsername(String username) {
		this.id = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<JwtRoles> getRoles() {
		return roles;
	}

	public void setRoles(List<JwtRoles> roles) {
		this.roles = roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream().map(authority -> new SimpleGrantedAuthority(authority.name())).collect(Collectors.toList());
	}

}
