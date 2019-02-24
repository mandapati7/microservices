package com.testcom.oce.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.testcom.oce.entity.Post;
import com.testcom.oce.entity.User;
import com.testcom.oce.repository.PostJPARepository;
import com.testcom.oce.repository.UserJPARepository;

@RestController
public class UserJPARestController {

	@Autowired
	UserJPARepository userJpaRepo;

	@Autowired
	PostJPARepository postJpaRepo;

	@GetMapping(path = "/jpa/users")
	public List<User> getAllUsers() {
		return userJpaRepo.findAll();
	}

	@GetMapping(path = "/jpa/users/{id}")
	public Resource<User> getUserById(@PathVariable int id) {
		Optional<User> userOptionalObj = userJpaRepo.findById(id);

		if (!userOptionalObj.isPresent()) {
			throw new UserNotFoundException("id - " + id);
		}

		User user = userOptionalObj.get();

		Resource<User> resource = new Resource<User>(user);

		ControllerLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());

		resource.add(link.withRel("all-user"));

		return resource;
		// return user;
	}

	@PostMapping(path = "/jpa/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {

		User userObj = userJpaRepo.save(user);

		URI userLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(userObj.getId()).toUri();

		return ResponseEntity.created(userLocation).build();
	}

	@DeleteMapping(path = "/jpa/users/{id}")
	public void addUser(@PathVariable int id) {
		userJpaRepo.deleteById(id);

		/*
		 * if (null == userObj) { throw new UserNotFoundException("ID -- " + id); }
		 */
	}

	@GetMapping(path = "/jpa/users/{id}/post")
	public List<Post> getPostsByUserId(@PathVariable int id) {
		Optional<User> userOptionalObj = userJpaRepo.findById(id);

		if (!userOptionalObj.isPresent()) {
			throw new UserNotFoundException("id - " + id);
		}

		User user = userOptionalObj.get();

		return user.getPosts();
	}

	@PostMapping(path = "/jpa/users/{id}/post")
	public ResponseEntity<Object> addPostForUser(@PathVariable int id, @RequestBody Post post) {

		Optional<User> userOptionalObj = userJpaRepo.findById(id);

		if (!userOptionalObj.isPresent()) {
			throw new UserNotFoundException("id - " + id);
		}

		User user = userOptionalObj.get();

		post.setUser(user);

		Post newPost = postJpaRepo.save(post);

		URI postLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newPost.getPostId()).toUri();

		return ResponseEntity.created(postLocation).build();
	}

}
