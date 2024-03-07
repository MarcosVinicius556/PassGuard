package br.com.dev.marcos.passguard.services.interfaces;

import java.util.List;

import br.com.dev.marcos.passguard.entities.interfaces.BaseEntity;
import br.com.dev.marcos.passguard.repositories.interfaces.BaseRepository;

public interface BaseService<Entity extends BaseEntity> {

	BaseRepository<Entity> getRepository();
	
	default Entity save(Entity entity) {
		return getRepository().save(entity);
	}
	
	default void delete(Entity entity) {
		getRepository().delete(entity);
	}
	
	default Entity update(Entity entity) {
		return getRepository().update(entity);
	}
	
	default List<Entity> findAll(Entity entity) {
		return getRepository().findAll(entity);
	}
	
	default Entity findById(Entity entity) {
		return getRepository().findById(entity);
	}
	
}
