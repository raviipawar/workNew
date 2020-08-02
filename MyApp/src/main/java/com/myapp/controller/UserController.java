/**
 * 'Jul 31, 2020
 */
package com.myapp.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.model.Users;
import com.myapp.repository.UserRepository;

/**
 * @author Ravindra Pawar
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	private List<Users> users;

	@GetMapping("/all")
	public List<Users> getUsers() {
		users = userRepository.findAll();
		return users;

	}

	@PostMapping("/add")
	public String createUser(@RequestBody Users user) {
		userRepository.save(user);
		return "Hi " + user.getFirstName() + " You're successfully Registered";
	}

	@PutMapping("/edit")
	public ResponseEntity<Users> updateUser(@RequestBody Users user) {
		Optional<Users> existingUsers = userRepository.findById(user.getId());
		if (existingUsers.isPresent()) {
			userRepository.save(user);
			return ResponseEntity.ok().build();
		} else
			return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Users> deleteById(@PathVariable ObjectId id) {
		Optional<Users> existingUsers = userRepository.findById(id);
		if (existingUsers.isPresent()) {
			userRepository.delete(existingUsers.get());
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/maxAge")
	public Users getMaxAge() {
		Comparator<Users> comparator = Comparator.comparing(Users::getAge);
		List<Users> user = userRepository.findAll();
		Users maxAgeUser = user.stream().max(comparator).get();
		return maxAgeUser;

	}

	@GetMapping("/minAge")
	public Users getMinAge() {
		Comparator<Users> comparator = Comparator.comparing(Users::getAge);
		List<Users> user = userRepository.findAll();
		Users minAgeUser = user.stream().min(comparator).get();
		return minAgeUser;
	}

	@GetMapping("/findUser/{email}")
	public List<Users> findUser(@PathVariable String email) {
		return userRepository.findByEmail(email);
	}
}
