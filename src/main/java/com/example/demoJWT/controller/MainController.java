package com.example.demoJWT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demoJWT.common.Result;
import com.example.demoJWT.entity.User;
import com.example.demoJWT.service.user.UserService;

@RestController
public class MainController {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping(value = "/login", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Result> login() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Custom-Header", "foo");
		return new ResponseEntity<Result>(new Result(), headers, HttpStatus.OK);
	}

	@GetMapping(value = "/users/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Result> getUsers(@PathVariable long id) {
		return userService.fetchById(id);
	}

	@PostMapping(value = "/users", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Result> addOrUpdate(@RequestBody User user) {
		user.setPassword_((passwordEncoder.encode(user.getPassword_())));
		return userService.addOrUpdate(user);
	}
}
