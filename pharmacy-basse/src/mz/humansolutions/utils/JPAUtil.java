package mz.humansolutions.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pharmacy");

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

}
