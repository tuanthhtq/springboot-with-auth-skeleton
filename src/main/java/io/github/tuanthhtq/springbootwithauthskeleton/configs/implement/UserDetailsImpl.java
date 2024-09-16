package io.github.tuanthhtq.springbootwithauthskeleton.configs.implement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.tuanthhtq.springbootwithauthskeleton.entities.Users;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @author io.github.tuanthhtq
 */

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {

	@Getter
	private String id;

	private String username;

	@Getter
	private String phone;

	@JsonIgnore
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(String id, String phone, String password,
	                       Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = phone;
		this.phone = phone;
		this.password = password;
		this.authorities = authorities;
	}

	public UserDetailsImpl() {
	}

	@Transactional
	public UserDetailsImpl build(Users user) {
//		List<GrantedAuthority> authorities = user.getRoles().stream()
//				.map(role -> new SimpleGrantedAuthority(role.getRoleName()))
//				.collect(Collectors.toList());

		return new UserDetailsImpl(
//				user.getId(),
//				user.getPhone(),
//				user.getPassword(),
//				authorities
		);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
