package br.com.dev.marcos.passguard.repositories.interfaces;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.dev.marcos.passguard.entities.interfaces.BaseEntity;
import br.com.dev.marcos.passguard.repositories.connection.DatabaseConnection;
import br.com.dev.marcos.passguard.services.exception.DatabaseException;

/**
 * @author Marcos Vinicius Angeli Costa
 * @apiNote Defini métodos base para serem utilizados na camada de acesso a banco de dados
 * @param <Entity>
 */
public interface BaseRepository<Entity extends BaseEntity> {

	default Entity save(Entity entity) {
		EntityManager em = DatabaseConnection.getDatabaseConnection().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			System.err.println("Entidade salva com sucesso no banco.");
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new DatabaseException("Não foi possível inserir o registro de "+entity.getEntityName()+" no banco. Motivo: " + e.getMessage());
		} finally {
			em.clear();
			em.close();
		}
		return entity;
	}
	
	default void delete(Entity entity) {
		EntityManager em = DatabaseConnection.getDatabaseConnection().createEntityManager();
		try {
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();
			System.err.println("Entidade removida com sucesso do banco.");
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new DatabaseException("Não foi possível remover o registro de "+entity.getEntityName()+" do banco. Motivo: " + e.getMessage());
		} finally {
			em.clear();
			em.close();
		}
	}
	
	default Entity update(Entity entity) {
		EntityManager em = DatabaseConnection.getDatabaseConnection().createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
			System.err.println("Entidade atualizada com sucesso no banco.");
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new DatabaseException("Não foi possível atualizar o registro de "+entity.getEntityName()+" do banco. Motivo: " + e.getMessage());
		} finally {
			em.clear();
			em.close();
		}
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	default List<Entity> findAll(Entity entity) {
		EntityManager em = DatabaseConnection.getDatabaseConnection().createEntityManager();
		TypedQuery<Entity> queryAll = null;
		List<Entity> entities = null;
		try {
			queryAll = (TypedQuery<Entity>) em.createQuery("SELECT e FROM "+entity.getEntityName()+" e");
			
			em.getTransaction().begin();
			entities = queryAll.getResultList();
			em.getTransaction().commit();
			System.err.println("Entidades no banco encontradas com sucesso no banco.");
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new DatabaseException("Não foi possível buscar os registros de "+entity.getEntityName()+" do banco. Motivo: " + e.getMessage());
		} finally {
			em.clear();
			em.close();
		}
		return entities;
	}
	
	@SuppressWarnings("unchecked")
	default Entity findById(Entity entity) {
		EntityManager em = DatabaseConnection.getDatabaseConnection().createEntityManager();
		Entity newEntity = null;
		try {
			em.getTransaction().begin();
			newEntity = (Entity) em.find(entity.getEntityClass(), entity.getId());
			em.getTransaction().commit();
			System.err.println("Entidade encontrada com sucesso no banco.");
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new DatabaseException("Não foi possível buscar o registro de "+entity.getEntityName()+" do banco. Motivo: " + e.getMessage());
		} finally {
			em.clear();
			em.close();
		}
		
		return newEntity;
	}
	
}
