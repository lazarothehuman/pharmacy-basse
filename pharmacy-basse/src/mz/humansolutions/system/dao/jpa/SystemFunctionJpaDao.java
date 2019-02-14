package mz.humansolutions.system.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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

	@Override
	public SystemFunction find(String function) {
		entityManager.getTransaction().begin();
		TypedQuery<SystemFunction> query = entityManager
				.createQuery("select systemFunction from SystemFunction systemFunction " + " where systemFunction.name = :function", SystemFunction.class);
		query.setParameter("function", function);
		List<SystemFunction> systemFunctions = query.getResultList();
		entityManager.getTransaction().commit();
		if (systemFunctions.isEmpty())
			return null;
		SystemFunction systemFunction = systemFunctions.get(0);
		return systemFunction;
	}

}
