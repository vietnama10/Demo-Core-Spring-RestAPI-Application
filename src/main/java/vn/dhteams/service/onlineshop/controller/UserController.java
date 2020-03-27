package vn.dhteams.service.onlineshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.dhteams.service.onlineshop.domain.User;
import vn.dhteams.service.onlineshop.service.UserService;
import vn.dhteams.service.onlineshop.utils.Messages;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello message";
	}
	
	@GetMapping("/principal")
	public Map<String, Object> getAuthInfo() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Map<String, Object> result = new HashMap<>();
			result.put("result", authentication.getPrincipal());
			result.put("status", true);
			return result;
		} catch (Exception e) {
			Map<String, Object> err = new HashMap<>();
			err.put("msg", Messages.ERROR_FETCH);
			err.put("status", false);
			return err;
		}
	}
	
	@GetMapping("/me")
	public Map<String, Object> getMine() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Map<String, Object> result = new HashMap<>();
			result.put("result", userService.getByUserName(authentication.getName()));
			result.put("status", true);
			return result;
		} catch (Exception e) {
			Map<String, Object> err = new HashMap<>();
			err.put("msg", Messages.ERROR_FETCH);
			err.put("status", false);
			return err;
		}
	}
	
	@GetMapping("/{id}")
	public Map<String, Object> getById(@PathVariable Long id) {
		try {
			Map<String, Object> result = new HashMap<>();
			result.put("result", userService.getById(id));
			result.put("status", true);
			return result;
		} catch (Exception e) {
			Map<String, Object> err = new HashMap<>();
			err.put("msg", Messages.ERROR_FETCH);
			err.put("status", false);
			return err;
		}
	}
	
	@GetMapping("/username/{userName}")
	public Map<String, Object> getByUserName(@PathVariable String userName) {
		try {
			Map<String, Object> result = new HashMap<>();
			result.put("result", userService.getByUserName(userName));
			result.put("status", true);
			return result;
		} catch (Exception e) {
			Map<String, Object> err = new HashMap<>();
			err.put("msg", Messages.ERROR_FETCH);
			err.put("status", false);
			return err;
		}
	}
	
	@GetMapping("")
	public Map<String, Object> getAllUser(
		@RequestParam(required = false) Integer page,
		@RequestParam(required = false) Integer pageSize,
		@RequestParam(required = false) String sortData,
		@RequestParam(required = false) String searchData
	) {
		try {
			return userService.getAllUser(page, pageSize, sortData, searchData);
		} catch(Exception e) {
			Map<String, Object> err = new HashMap<>();
			err.put("msg", Messages.ERROR_FETCH);
			err.put("status", false);
			return err;
		}
		
	}
	
	@PostMapping("")
	public Map<String, Object> createUser(@RequestBody User user) {
		try {
			return userService.createUser(user);
		} catch(Exception e) {
			Map<String, Object> err = new HashMap<>();
			err.put("msg", Messages.FAILURE_CREATE);
			err.put("status", false);
			return err;
		}
	}
	
	@PutMapping("")
	public Map<String, Object> updateUser(@RequestBody User user) {
		try {
			return userService.updateUser(user);
		} catch(Exception e) {
			Map<String, Object> err = new HashMap<>();
			err.put("msg", Messages.FAILURE_UPDATE);
			err.put("status", false);
			return err;
		}
	}
	
	@DeleteMapping("")
	public Map<String, Object> deleteUser(@RequestBody List<Long> userIds) {
		try {
			return userService.deleteUser(userIds);
		} catch(Exception e) {
			Map<String, Object> err = new HashMap<>();
			err.put("msg", Messages.FAILURE_DELETE);
			err.put("status", false);
			return err;
		}
	}
}
