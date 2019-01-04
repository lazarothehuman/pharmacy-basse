package mz.humansolutions.models.dao.jpa;

import javax.persistence.EntityManager;

import mz.humansolutions.models.Cliente;
import mz.humansolutions.models.dao.ClienteDao;
import mz.humansolutions.utils.JPAUtil;

public class ClienteJpaDao implements ClienteDao {
	
	private EntityManager entityManager = (EntityManager) new JPAUtil().getEntityManager();


	@Override
	public void create(Cliente cliente) {
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
	}


	@Override
	public void update(Cliente cliente) {
		entityManager.getTransaction().begin();
		entityManager.merge(cliente);
		entityManager.getTransaction().commit();
		
	}

}
