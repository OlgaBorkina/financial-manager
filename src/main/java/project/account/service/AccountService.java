package project.account.service;

import project.account.dto.RoleDto;
import project.account.dto.UpdateUserDto;
import project.account.dto.UserDto;
import project.account.dto.UserRegisterDto;

public interface AccountService {
	
	UserDto userRegister(UserRegisterDto userRegisterDto);
	
	
	
	UserDto deleteUser(String user);
	
	UserDto userUpdate(String user, UpdateUserDto updateUserDto);
	
	RoleDto addRole(String user,String role);
	
	RoleDto deleteRole(String user,String role);
	
	UserDto getUser(String user);
	
	void changePassword(String login, String newPassword);

	
	
	

}
