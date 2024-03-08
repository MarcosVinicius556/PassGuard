package br.com.dev.marcos.passguard.entities.interfaces;

public interface BaseEntity {
	/**
	 * @apiNote Retorna o ID da entidade
	 * @return
	 */
	Object getId();
	
	/**
	 * @apiNote Retorna o nome da classe da entidade
	 * @return
	 */
	String getEntityName();
	
	/**
	 * @apiNote Retorna a classe da entidade
	 * @return
	 */
	Class<? extends BaseEntity> getEntityClass();
	
}
