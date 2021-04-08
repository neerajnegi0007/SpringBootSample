package com.example.demo.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.controllers.exceptions.UserNotFoundException;
import com.example.demo.dao.PostsDao;
import com.example.demo.dao.UserDao;
import com.example.demo.model.Posts;
import com.example.demo.model.User;

@RestController
public class UserController {

	@Autowired
	UserDao userdao;
	@Autowired
	PostsDao postsDao;

	@GetMapping("/getUsers")
	public List<User> getUsers() {
		return userdao.findAll();

	}

	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable int id) {
		userdao.deleteById(id);			 

	}

	@GetMapping("/getUsers/{id}")
	public User getUsersById(@PathVariable int id) throws UserNotFoundException {
		Optional<User> user = userdao.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User not present");
		}
		return user.get();			 

	}

	@GetMapping("/getUsers/{userID}/getPosts")
	public List<Posts> getPostsById(@PathVariable int userID) throws UserNotFoundException {
		Optional<User> user = userdao.findById(userID);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User not present");
		}
		return user.get().getPostList();			 

	}

	@PostMapping("/createUser")
	public User createUser(@PathVariable User user){
		User u =  userdao.save(user);
		/*
		 * URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/").
		 * buildAndExpand(u.getId()).toUri(); return
		 * ResponseEntity.created(location).build();
		 */
		return u;
	}

	/**
	 * @param userID
	 * @param posts
	 * @return
	 * @throws UserNotFoundException
	 */
	@PostMapping("/getUsers/{userID}/createPost")
	public ResponseEntity<Posts> createPost(@PathVariable int userID, @RequestBody Posts posts) throws UserNotFoundException {
		Optional<User> user =userdao.findById(userID);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User not present");
		}
		posts.setUser(user.get());
		postsDao.save(posts);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/").
				buildAndExpand(posts.getPostId()).toUri();
		
		return	ResponseEntity.created(location).build();
				
	}

}
