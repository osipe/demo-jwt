package com.example.demoJWT.service.user;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demoJWT.common.Result;
import com.example.demoJWT.entity.User;
import com.example.demoJWT.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<Result> fetchById(long id) {
		Result result = new Result();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Custom-Header", "foo");
		try {
			JSONObject data = new JSONObject();
			User user = userRepository.getOne(id);
			data.put("Screenname", user.getSreenname());
			result.setStatusCode(HttpStatus.OK.value());
			result.setData(data.toString());
		} catch (Exception e) {
			result.setStatusCode(HttpStatus.BAD_REQUEST.value());
			result.setMessage(e.getMessage());
		}
		return new ResponseEntity<Result>(result, headers, HttpStatus.OK);
	}
	public ResponseEntity<Result> addOrUpdate(User user) {
		Result result = new Result();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Custom-Header", "foo");
		try {
			JSONObject data = new JSONObject();
			User userBD = userRepository.save(user);
			data.put("Screenname", userBD.getSreenname());
			data.put("Id", userBD.getId());
			result.setStatusCode(HttpStatus.OK.value());
			result.setData(data.toString());
		} catch (Exception e) {
			result.setStatusCode(HttpStatus.BAD_REQUEST.value());
			result.setMessage(e.getMessage());
		}
		return new ResponseEntity<Result>(result, headers, HttpStatus.OK);
	}
}
