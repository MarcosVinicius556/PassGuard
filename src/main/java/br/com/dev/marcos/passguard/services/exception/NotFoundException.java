package br.com.dev.marcos.passguard.services.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NotFoundException(Object id) {
		super("Não foi possível encontrar nenhum registro com o ID. " + id);
	}
}
