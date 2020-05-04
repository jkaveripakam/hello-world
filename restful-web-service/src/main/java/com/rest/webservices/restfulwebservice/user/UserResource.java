package com.rest.webservices.restfulwebservice.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
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


@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;
	
	//get all users
	@GetMapping("/users")
	public List<User> retriveUsers(){
		return service.getUsers();
	}
	
	//get a perticular user
	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		User user= service.getUser(id);
		if(user == null)
			throw new UserNotFoundException("id -"+id);
		
		
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveUsers());	
		resource.add(linkTo.withRel("all-users"));;
		return resource;
		
		
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user= service.deleteUser(id);
		if(user == null)
			throw new UserNotFoundException("id -"+id);
		
		
	}
	
	
		@PostMapping("/users")
		public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
			 User savedUser = service.saveUser(user);
			 
			 URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
			 return ResponseEntity.created(location).build();
		}


}
