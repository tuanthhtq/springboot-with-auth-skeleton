package io.github.tuanthhtq.financemanager.controllers;

import io.github.tuanthhtq.financemanager.dtos.AuthReqDto;
import io.github.tuanthhtq.financemanager.dtos.AuthResDto;
import io.github.tuanthhtq.financemanager.dtos.Response;
import io.github.tuanthhtq.financemanager.services.AuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author io.github.tuanthhtq
 */

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

	private final AuthServices authServices;

	@Autowired
	public AuthController(
			AuthServices authServices
	) {
		this.authServices = authServices;
	}

	@PostMapping("/login")
	public ResponseEntity<Response<AuthResDto>> login(@RequestBody AuthReqDto request) {
		return new ResponseEntity<>(
				authServices.login(request),
				HttpStatus.OK
		);
	}

	@PostMapping("/register")
	public ResponseEntity<Response<AuthResDto>> register(@RequestBody AuthReqDto request) {
		return new ResponseEntity<>(
				authServices.register(request),
				HttpStatus.OK
		);
	}
}
