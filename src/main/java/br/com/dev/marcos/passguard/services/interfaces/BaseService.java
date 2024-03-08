package br.com.dev.marcos.passguard.services.interfaces;

import java.util.List;

import br.com.dev.marcos.passguard.entities.interfaces.BaseEntity;
import br.com.dev.marcos.passguard.repositories.interfaces.BaseRepository;
import br.com.dev.marcos.passguard.services.exception.DatabaseException;
import br.com.dev.marcos.passguard.services.exception.NotFoundException;

public interface BaseService<Entity extends BaseEntity> {

	BaseRepository<Entity> getRepository();
	
	default Entity save(Entity entity){
		return getRepository().save(entity);
	}
	
	default void delete(Entity entity) throws DatabaseException, NotFoundException {
		Entity e = getRepository().findById(entity);
		if(e == null)
			throw new NotFoundException(entity.getId());
		
		getRepository().delete(entity);
	}
	
	default Entity update(Entity entity) throws DatabaseException, NotFoundException {
		Entity e = getRepository().findById(entity);
		if(e == null)
			throw new NotFoundException(entity.getId());
		
		return getRepository().update(entity);
	}
	
	default List<Entity> findAll(Entity entity) throws DatabaseException, NotFoundException {
		List<Entity> entities = getRepository().findAll(entity);
		if(entities == null || entities.size() == 0)
			throw new NotFoundException("All");
		
		return entities;
	}
	
	default Entity findById(Entity entity) throws DatabaseException, NotFoundException {
		Entity e = getRepository().findById(entity);
		if(e == null)
			throw new NotFoundException(entity.getId());
		
		return e;
	}
	
}
