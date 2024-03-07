package br.com.dev.marcos.passguard.services;

import br.com.dev.marcos.passguard.entities.Password;
import br.com.dev.marcos.passguard.repositories.factory.RepositoryFactory;
import br.com.dev.marcos.passguard.repositories.interfaces.BaseRepository;
import br.com.dev.marcos.passguard.repositories.interfaces.PasswordRepository;
import br.com.dev.marcos.passguard.services.interfaces.PasswordService;

public class PasswordServiceImpl implements PasswordService {

	private PasswordRepository repository;
	
	public PasswordServiceImpl() {
		getRepository();
	}
	
	@Override
	public BaseRepository<Password> getRepository() {
		if(repository == null)
			repository = RepositoryFactory.createPasswordRepository();
		return repository;
	}

}
