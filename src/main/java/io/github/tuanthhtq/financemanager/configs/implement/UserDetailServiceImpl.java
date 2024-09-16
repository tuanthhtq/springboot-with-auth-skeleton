package io.github.tuanthhtq.financemanager.configs.implement;

import io.github.tuanthhtq.financemanager.entities.Users;
import io.github.tuanthhtq.financemanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author io.github.tuanthhtq
 */

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public UserDetailServiceImpl(UserRepository ur) {
		this.userRepository = ur;
	}

	@Override
	public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
		Users u = userRepository.findByUsername(username);
		return u == null ? null : new UserDetailsImpl().build(u);
	}

}