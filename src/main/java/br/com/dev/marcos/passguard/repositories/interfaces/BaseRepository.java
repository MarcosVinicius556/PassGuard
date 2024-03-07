package br.com.dev.marcos.passguard.repositories.interfaces;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.dev.marcos.passguard.entities.interfaces.BaseEntity;
import br.com.dev.marcos.passguard.repositories.connection.DatabaseConnection;

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
			System.err.println("Não foi possível salvar a entidade no banco.");
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
			System.err.println("Não foi possível remover a entidade do banco.");
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
			System.err.println("Não foi possível atualizar a entidade no banco.");
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
			System.err.println("Entidade salva com sucesso no banco.");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Não foi possível salvar a entidade no banco.");
		} finally {
			em.clear();
			em.close();
		}
		return entities;
	}
	
	@SuppressWarnings("unchecked")
	default Entity findById(Entity entity) {
		EntityManager em = DatabaseConnection.getDatabaseConnection().createEntityManager();
		try {
			em.getTransaction().begin();
			entity = (Entity) em.find(entity.getEntityClass(), entity);
			em.getTransaction().commit();
			System.err.println("Entidade salva com sucesso no banco.");
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.err.println("Não foi possível salvar a entidade no banco.");
		} finally {
			em.clear();
			em.close();
		}
		return entity;
	}
	
}
