package br.com.dev.marcos.passguard.services.interfaces;

import java.time.LocalDateTime;

import br.com.dev.marcos.passguard.entities.User;

public interface UserService extends BaseService<User>{

	
	@Override
	default User save(User entity) {
		entity.setCreatedAt(LocalDateTime.now());
		return BaseService.super.save(entity);
	}
	
}
