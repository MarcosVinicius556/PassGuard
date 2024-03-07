package br.com.dev.marcos.passguard.repositories.factory;

import br.com.dev.marcos.passguard.repositories.PasswordRepositoryImpl;
import br.com.dev.marcos.passguard.repositories.UserRepositoryImpl;
import br.com.dev.marcos.passguard.repositories.interfaces.PasswordRepository;
import br.com.dev.marcos.passguard.repositories.interfaces.UserRepository;

public class RepositoryFactory {

	public static UserRepository createUserRepository() {
		return new UserRepositoryImpl();
	}
	
	public static PasswordRepository createPasswordRepository() {
		return new PasswordRepositoryImpl();
	}
	
}
