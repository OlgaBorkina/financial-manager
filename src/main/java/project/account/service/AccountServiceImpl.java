package project.account.service;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import project.account.dao.AccountRepository;
import project.account.dto.RoleDto;
import project.account.dto.UpdateUserDto;
import project.account.dto.UserDto;
import project.account.dto.UserRegisterDto;
import project.account.dto.exception.AccountNotException;
import project.account.dto.exception.AlreadyExists;
import project.account.model.Account;


@Controller
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService, CommandLineRunner {

	final AccountRepository accountRepository;
	final ModelMapper modelMapper;

	@Override
	public UserDto userRegister(UserRegisterDto userRegisterDto) {

		if (accountRepository.existsById(userRegisterDto.getLogin())) {
			throw new AlreadyExists();
		}

		Account account = modelMapper.map(userRegisterDto, Account.class);
		String password = BCrypt.hashpw(userRegisterDto.getPassword(), BCrypt.gensalt());
		account.setPassword(password);
		account.addRole("USER");
		account = accountRepository.save(account);
		return modelMapper.map(account, UserDto.class);
	}

	@Override
	public void changePassword(String login, String newPassword) {
		Account account = accountRepository.findById(login).orElseThrow(AccountNotException::new);
		String password = BCrypt.hashpw(newPassword, BCrypt.gensalt());
		account.setPassword(password);
		accountRepository.save(account);
	}



	@Override
	public UserDto deleteUser(String user) {
		Account account = accountRepository.findById(user).orElseThrow(AccountNotException::new);
		accountRepository.deleteById(user);
		return modelMapper.map(account, UserDto.class);
	}

	@Override
	public UserDto userUpdate(String user, UpdateUserDto updateUserDto) {
		Account account = accountRepository.findById(user).orElseThrow(AccountNotException::new);
		if (updateUserDto.getFirstName() != null) {
			account.setFirstName(updateUserDto.getFirstName());
		}
		if (updateUserDto.getLastName() != null) {
			account.setLastName(updateUserDto.getLastName());
		}
		accountRepository.save(account);
		return modelMapper.map(account, UserDto.class);
	}

	@Override
	public RoleDto addRole(String user, String role) {
		Account account = accountRepository.findById(user).orElseThrow(AccountNotException::new);
		account.addRole(role);
		accountRepository.save(account);
		return modelMapper.map(account, RoleDto.class);
	}

	@Override
	public RoleDto deleteRole(String user, String role) {
		Account account = accountRepository.findById(user).orElseThrow(AccountNotException::new);
		account.removeRole(role);
		accountRepository.save(account);
		return modelMapper.map(account, RoleDto.class);
	}

	@Override
	public UserDto getUser(String user) {
		Account account = accountRepository.findById(user).orElseThrow(AccountNotException::new);
		return modelMapper.map(account, UserDto.class);
	}
	
	@Override
	public void run(String... args) throws Exception {
		if (!accountRepository.existsById("admin")) {
			String password = BCrypt.hashpw("admin", BCrypt.gensalt());
			Account userAccount =  new Account("admin", "", "", password);
			userAccount.addRole("MODERATOR");
			userAccount.addRole("ADMINISTRATOR");
			accountRepository.save(userAccount);
		}

	}

}
