package project.account.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import project.account.dto.RoleDto;
import project.account.dto.UpdateUserDto;
import project.account.dto.UserDto;
import project.account.dto.UserRegisterDto;
import project.account.service.AccountService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController  {
	
	final AccountService accountService;

	@PostMapping("/register")
	public UserDto userRegister(@RequestBody UserRegisterDto userRegisterDto) {
		return accountService.userRegister(userRegisterDto);
	}
	
	@PostMapping("/login")
	public UserDto login(Principal principal ) {
		return accountService.getUser(principal.getName());
	}


	@DeleteMapping("/user/{user}")
	public UserDto deleteUser(@PathVariable String user) {
		return accountService.deleteUser(user);
	}

	@PutMapping("/user/{user}")
	public UserDto userUpdate(@PathVariable String user,@RequestBody UpdateUserDto updateUserDto) {
		return accountService.userUpdate(user, updateUserDto);
	}

	@PutMapping("/user/{user}/role/{role}")
	public RoleDto addRole(@PathVariable String user,@PathVariable String role) {
		return accountService.addRole(user, role);
	}

	@DeleteMapping("/user/{user}/role/{role}")
	public RoleDto deleteRole( @PathVariable String user, @PathVariable String role) {
		return accountService.deleteRole(user, role);
	}

	
	@GetMapping("user/{user}")
	public UserDto getUser(@PathVariable String user) {
		return accountService.getUser(user);
	}
	@PutMapping("/password")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public  void changePassword (Principal principal, @RequestHeader("X-Password") String newPassword ) {
		accountService.changePassword(principal.getName(), newPassword);
	}
	
	
	

}