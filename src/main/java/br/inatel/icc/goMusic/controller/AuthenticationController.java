package br.inatel.icc.goMusic.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.inatel.icc.goMusic.config.security.TokenService;
import br.inatel.icc.goMusic.controller.dto.TokenDto;
import br.inatel.icc.goMusic.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	private AuthenticationManager authManager;
	private TokenService tokenService;

	@Autowired
	public AuthenticationController(AuthenticationManager authManager, TokenService tokenService) {
		this.authManager = authManager;
		this.tokenService = tokenService;
	}

	@PostMapping("/login")
	public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken loginData = form.convertToLoginData();

		try {
			Authentication authentication = authManager.authenticate(loginData);

			String token = tokenService.generateToken(authentication);

			return ResponseEntity.status(200).body(new TokenDto(token, "Bearer"));
		} catch (AuthenticationException e) {
			return ResponseEntity.status(400).build();
		}

	}

}
