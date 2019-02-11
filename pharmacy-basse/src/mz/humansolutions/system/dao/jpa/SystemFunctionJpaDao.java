package mz.humansolutions.system.dao.jpa;

import javax.persistence.EntityManager;

import mz.humansolutions.system.SystemFunction;
import mz.humansolutions.system.dao.SystemFunctionDao;
import mz.humansolutions.utils.JPAUtil;

public class SystemFunctionJpaDao implements SystemFunctionDao {
	
	private EntityManager entityManager = (EntityManager) new JPAUtil().getEntityManager();

	@Override
	public void create(SystemFunction function) {
		entityManager.getTransaction().begin();
		entityManager.persist(function);
		entityManager.getTransaction().commit();

	}

}
