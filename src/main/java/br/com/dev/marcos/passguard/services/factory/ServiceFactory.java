package br.com.dev.marcos.passguard.services.factory;

import br.com.dev.marcos.passguard.services.PasswordServiceImpl;
import br.com.dev.marcos.passguard.services.UserServiceImpl;
import br.com.dev.marcos.passguard.services.interfaces.PasswordService;
import br.com.dev.marcos.passguard.services.interfaces.UserService;

public class ServiceFactory {

	public static UserService createUserService() {
		return new UserServiceImpl();
	}
	
	public static PasswordService createPassordService() {
		return new PasswordServiceImpl();
	}
	
}
