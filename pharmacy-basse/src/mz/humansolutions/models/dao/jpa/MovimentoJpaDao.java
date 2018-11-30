package mz.humansolutions.models.dao.jpa;
import javax.persistence.EntityManager;

import mz.humansolutions.models.Movimento;
import mz.humansolutions.models.dao.MovimentoDao;
import mz.humansolutions.utils.JPAUtil;
public class MovimentoJpaDao implements MovimentoDao{

	
	private EntityManager entityManager = (EntityManager) new JPAUtil().getEntityManager();
	
	@Override
	public void create(Movimento movimento) {
		entityManager.getTransaction().begin();
		entityManager.persist(movimento);
		entityManager.getTransaction().commit();
		
	}

}
