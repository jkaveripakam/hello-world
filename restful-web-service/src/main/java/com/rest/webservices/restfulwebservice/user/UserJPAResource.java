package com.rest.webservices.restfulwebservice.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
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


@RestController
public class UserJPAResource {
	
	@Autowired
	private UserDaoService service;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	
	//get all users
	@GetMapping("/jpa/users")
	public List<User> retriveUsers(){
		return userRepository.findAll();
	}
	
	//get a perticular user
	@GetMapping("/jpa/users/{id}")
	public Resource<User> retrieveUser(@PathVariable int id) {
		Optional<User> user= userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("id -"+id);
		
		
		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retriveUsers());	
		resource.add(linkTo.withRel("all-users"));;
		return resource;
		
		
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
		// if(user == null)
			// throw new UserNotFoundException("id -"+id);
		
		
	}
	
	
		@PostMapping("/jpa/users")
		public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
			User savedUser= userRepository.save(user);		 
			 URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
			 return ResponseEntity.created(location).build();
		}
		
		//get a perticular user
		@GetMapping("/jpa/users/{id}/posts")
		public List<Post> retrieveAllUser(@PathVariable int id) {
			Optional<User> userOptional= userRepository.findById(id);
			if(!userOptional.isPresent())
				throw new UserNotFoundException("id -"+id);
			
			return userOptional.get().getPosts();
			
			
		}
		
		@PostMapping("/jpa/users/{id}/posts")
		public ResponseEntity<Object> addUser(@PathVariable int id, @RequestBody Post post ) {
			Optional<User> userOptional= userRepository.findById(id);
			if(!userOptional.isPresent())
				throw new UserNotFoundException("id -"+id);
			
			User user = userOptional.get();
			post.setUser(user);
			postRepository.save(post);
			
			 URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
			 return ResponseEntity.created(location).build();
		}


}
