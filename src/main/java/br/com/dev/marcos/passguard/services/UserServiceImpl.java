package br.com.dev.marcos.passguard.services;

import br.com.dev.marcos.passguard.entities.User;
import br.com.dev.marcos.passguard.repositories.factory.RepositoryFactory;
import br.com.dev.marcos.passguard.repositories.interfaces.BaseRepository;
import br.com.dev.marcos.passguard.repositories.interfaces.UserRepository;
import br.com.dev.marcos.passguard.services.interfaces.UserService;

public class UserServiceImpl implements UserService {

	private UserRepository repository;
	
	public UserServiceImpl() {
		getRepository();
	}
	
	@Override
	public BaseRepository<User> getRepository() {
		if(repository == null)
			repository = RepositoryFactory.createUserRepository();
		return repository;
	}
	
}
