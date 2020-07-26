package co.simplon.masterpiece.config;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import co.simplon.masterpiece.dtos.UserAuthDto;
import co.simplon.masterpiece.entities.Role;

public class CustomUserDetails extends User {

	private static final long serialVersionUID = 5803283930339051994L;

	private Long id;

	public CustomUserDetails(UserAuthDto user) {
		super(user.getLogin(), user.getPassword(), true, true, true, true,
				buildAuthorities(user.getRoles()));
		id = user.getId();
	}

	private static Set<GrantedAuthority> buildAuthorities(Set<Role> roles) {
		return roles.stream().map(r -> new SimpleGrantedAuthority(r.getCode()))
				.collect(Collectors.toUnmodifiableSet());
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", authorities=" + getAuthorities()
				+ ", password=[PROTECTED], username=" + getUsername() + ", enabled="
				+ isEnabled() + ", accountNonExpired=" + isAccountNonExpired()
				+ ", accountNonLocked=" + isAccountNonLocked()
				+ ", credentialsNonExpired=" + isCredentialsNonExpired() + "}";
	}
}
