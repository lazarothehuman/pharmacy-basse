package mz.humansolutions.models.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import mz.humansolutions.models.Transaccao;
import mz.humansolutions.models.dao.TransaccaoDao;
import mz.humansolutions.utils.JPAUtil;

public class TransaccaoJpaDao implements TransaccaoDao {
	
	private EntityManager entityManager = (EntityManager) new JPAUtil().getEntityManager();

	@Override
	public Transaccao find(Long code) {

		entityManager.getTransaction().begin();
		TypedQuery<Transaccao> query = entityManager
				.createQuery("select transaccao from Transaccao transaccao"+ " where transaccao.code = :code", Transaccao.class);
		query.setParameter("code", code);
		List<Transaccao> transaccaos = query.getResultList();
		entityManager.getTransaction().commit();
		if (transaccaos.isEmpty())
			return null;
		Transaccao transaccao = transaccaos.get(0);
		return transaccao;
	
}

	@Override
	public void create(Transaccao transaction) {
		entityManager.getTransaction().begin();
		entityManager.persist(transaction);
		entityManager.getTransaction().commit();
	}

	@Override
	public void update(Transaccao transaccao) {
		entityManager.getTransaction().begin();
		entityManager.merge(transaccao);
		entityManager.getTransaction().commit();
	}

}
