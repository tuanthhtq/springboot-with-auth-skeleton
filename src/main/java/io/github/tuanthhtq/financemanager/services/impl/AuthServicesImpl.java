package io.github.tuanthhtq.financemanager.services.impl;

import com.mongodb.MongoException;
import io.github.tuanthhtq.financemanager.configs.implement.UserDetailServiceImpl;
import io.github.tuanthhtq.financemanager.configs.implement.UserDetailsImpl;
import io.github.tuanthhtq.financemanager.dtos.AuthReqDto;
import io.github.tuanthhtq.financemanager.dtos.AuthResDto;
import io.github.tuanthhtq.financemanager.dtos.Response;
import io.github.tuanthhtq.financemanager.entities.Users;
import io.github.tuanthhtq.financemanager.repositories.UserRepository;
import io.github.tuanthhtq.financemanager.services.AuthServices;
import io.github.tuanthhtq.financemanager.utils.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author io.github.tuanthhtq
 */

@Slf4j
@Service
public class AuthServicesImpl implements AuthServices {

	private final UserRepository userRepo;
	private final PasswordEncoder passwordEncoder;
	private final UserDetailServiceImpl userDetailService;
	private final AuthenticationManager authManager;

	@Autowired
	public AuthServicesImpl(
			UserRepository userRepo,
			PasswordEncoder passwordEncoder,
			UserDetailServiceImpl userDetailService,
			AuthenticationManager authManager
	) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
		this.userDetailService = userDetailService;
		this.authManager = authManager;
	}


	@Override
	public Response<AuthResDto> login(AuthReqDto req) {

		Map<String, String> errors = new HashMap<>();
		Response<AuthResDto> response = new Response<>();

		response.setMessage("Login failed");

		try {
			//fetch database
			Users user = userRepo.findByUsername(req.username());

			//check username
			if (user == null) {
				errors.put("username", "Username not found");
				response.setError(errors);
				return response;
			} else {
				//check password
				if (!passwordEncoder.matches(req.password(), user.getPassword())) {
					errors.put("password", "Wrong password");
					response.setError(errors);
					return response;
				} else {
					//success login

					//initialize userDetails
					UserDetailsImpl ud = userDetailService.loadUserByUsername(user.getUsername());

					//create authentication
					Authentication auth = authManager.authenticate(
							new UsernamePasswordAuthenticationToken(req.username(), req.password())
					);

					//authorize this user
					SecurityContextHolder.getContext().setAuthentication(auth);

					//create access token
					String accessToken = JwtProvider.create(ud);

					//set response
					response.setData(new AuthResDto(
							user.getUsername(),
							user.getDisplayName(),
							accessToken
					));
					response.setMessage("Login successful");
					return response;
				}
			}
		} catch (Exception e) {
//			errors.put("login", e.getLocalizedMessage());
			errors.put("login", "Error occurred, please try again");
			response.setError(errors);
			return response;
		}
	}

	@Override
	public Response<AuthResDto> register(AuthReqDto req) {

		Map<String, String> errors = new HashMap<>();
		Response<AuthResDto> response = new Response<>();

		response.setMessage("Register failed");

		try {
			//fetch database
			Users user = userRepo.findByUsername(req.username());


			//check username
			if (user != null) {
				errors.put("username", "Username has already been used");
				response.setError(errors);
				return response;
			} else {
				//check password
				if (req.password().length() < 4) {
					errors.put("password", "Password must be at least 4 characters");
					response.setError(errors);
					return response;
				} else if (!req.password().equals(req.passwordConfirmation())) {
					errors.put("password", "Wrong password");
					response.setError(errors);
					return response;
				} else {
					//create account
					Users newUser = new Users();
					newUser.setUsername(req.username());
					newUser.setPassword(passwordEncoder.encode(req.password()));
					newUser.setDisplayName(req.displayName().isEmpty() ? req.username() : req.displayName());

					Users savedUser = userRepo.save(newUser);

					//initialize userDetails
					UserDetailsImpl ud = userDetailService.loadUserByUsername(newUser.getUsername());
					
					//create access token
					String accessToken = JwtProvider.create(ud);

					//set response
					response.setData(new AuthResDto(
							savedUser.getUsername(),
							savedUser.getDisplayName(),
							accessToken
					));
					response.setMessage("Register successful");
					return response;
				}
			}
		} catch (MongoException e) {
			errors.put("register", "Error occurred, please try again");
			response.setError(errors);
			return response;
		}
	}
}
