package br.com.dev.marcos.passguard.repositories.connection;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseConnection {

	private static EntityManagerFactory emf = null;

	public static EntityManagerFactory getDatabaseConnection() {
		try {
			if(emf == null) {
				emf = Persistence.createEntityManagerFactory("passGuard");
			}

			System.err.println("Conexão com o banco estabelecida com sucesso!");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} finally {
			System.gc();
		}

		return emf;
	}
	
}
