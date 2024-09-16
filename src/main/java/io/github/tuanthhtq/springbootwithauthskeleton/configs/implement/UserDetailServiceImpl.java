package io.github.tuanthhtq.springbootwithauthskeleton.configs.implement;

import io.github.tuanthhtq.springbootwithauthskeleton.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
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
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Users u = userRepository.findById(id).orElse(null);
//		return u == null ? null : new UserDetailsImpl().build(u);
		return null;
	}

}