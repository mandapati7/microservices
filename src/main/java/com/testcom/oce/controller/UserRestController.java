package com.testcom.oce.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.testcom.oce.repository.UserDaoService;
import com.testcom.oce.vo.User;

@RestController
public class UserRestController {

	@Autowired
	UserDaoService userDao;

	@GetMapping(path = "/users")
	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	@GetMapping(path = "/users/{id}")
	public Resource<User> getUserById(@PathVariable int id) {
		User user = userDao.findUserById(id);

		if (null == user) {
			throw new UserNotFoundException("id - " + id);
		}

		Resource<User> resource = new Resource<User>(user);

		ControllerLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());

		resource.add(link.withRel("all-user"));

		return resource;
		// return user;
	}

	@PostMapping(path = "/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {

		User userObj = userDao.saveUser(user);

		URI userLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(userObj.getId()).toUri();

		return ResponseEntity.created(userLocation).build();
	}

	@DeleteMapping(path = "/users/{id}")
	public void addUser(@PathVariable int id) {
		User userObj = userDao.deleteUserById(id);

		if (null == userObj) {
			throw new UserNotFoundException("ID -- " + id);
		}

	}

}
