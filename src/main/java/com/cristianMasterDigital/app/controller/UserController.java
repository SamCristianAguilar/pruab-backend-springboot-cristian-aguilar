package com.cristianMasterDigital.app.controller;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cristianMasterDigital.app.entity.User;
import com.cristianMasterDigital.app.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	// Create an user
	@PostMapping("save")
	public ResponseEntity<?> create(@RequestBody User user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}

	// Read all users
	@GetMapping
	public List<User> readAll() {
		List<User> users = StreamSupport
				.stream(userService.findAll().spliterator(), false)
				.collect(Collectors.toList());

		return users;
	}

	// Read an user
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable(value = "id") Long userId) {
		Optional<User> oUser = userService.findById(userId);

		if (!oUser.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		//oUser.get().getOrders().clear();

		return ResponseEntity.ok(oUser);
	}

	// Update an user
	@PutMapping("update/{id}")
	public ResponseEntity<?> update(@RequestBody User userDetails, @PathVariable(value = "id") Long userId) {
		Optional<User> user = userService.findById(userId);

		if (!user.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		user.get().setName(userDetails.getName());
		user.get().setSurname(userDetails.getSurname());
		user.get().setNumberId(userDetails.getNumberId());
		user.get().setAddress(userDetails.getAddress());

		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user.get()));

	}

	// Delete an user
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long userId) {

		if (!userService.findById(userId).isPresent()) {
			return ResponseEntity.notFound().build();
		}

		userService.deleteById(userId);

		return ResponseEntity.ok().build();
	}

}
