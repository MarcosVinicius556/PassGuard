package br.com.dev.marcos.passguard.entities.interfaces;

public interface BaseEntity {
	Object getId();
	String getEntityName();
	Class<? extends BaseEntity> getEntityClass();
}
