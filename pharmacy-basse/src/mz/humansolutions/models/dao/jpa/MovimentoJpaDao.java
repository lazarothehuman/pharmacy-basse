package mz.humansolutions.models.dao.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import mz.humansolutions.models.Cliente;
import mz.humansolutions.models.Movimento;
import mz.humansolutions.models.Tipo;
import mz.humansolutions.models.dao.MovimentoDao;
import mz.humansolutions.utils.JPAUtil;

public class MovimentoJpaDao implements MovimentoDao {

	private EntityManager entityManager = (EntityManager) new JPAUtil().getEntityManager();

	@Override
	public void create(Movimento movimento) {
		entityManager.getTransaction().begin();
		entityManager.persist(movimento);
		entityManager.getTransaction().commit();

	}

	@Override
	public List<Movimento> findMovimento(Long id, Boolean active) {
		entityManager.getTransaction().begin();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Movimento> query = criteriaBuilder.createQuery(Movimento.class);

		Root<Movimento> root = query.from(Movimento.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		Path<Long> idPath = root.<Long>get("id");
		Path<Boolean> activePath = root.<Boolean>get("active");
		if (id != null) {
			Predicate predicate = criteriaBuilder.equal(idPath, id);
			predicates.add(predicate);
		}

		if (active != null) {
			Predicate predicate = criteriaBuilder.equal(activePath, active);
			predicates.add(predicate);
		}

		query.where(predicates.toArray(new Predicate[predicates.size()]));

		TypedQuery<Movimento> typedQuery = entityManager.createQuery(query);
		entityManager.getTransaction().commit();
		return typedQuery.getResultList();
	}

	/*
	 * @Override public List<Movimento> findMovimento(Long id, Boolean active) {
	 * entityManager.getTransaction().begin(); TypedQuery<Movimento> query =
	 * entityManager.createQuery( "select movimento from Movimento movimento" +
	 * " where movimento.id = :id and movimento.active= :active", Movimento.class);
	 * query.setParameter("id", id); query.setParameter("active", active);
	 * List<Movimento> movimentos = query.getResultList();
	 * entityManager.getTransaction().commit(); if (movimentos.isEmpty()) return
	 * null; return movimentos; }
	 */

	@Override
	public List<Movimento> findMedicamento(Long id, Boolean active) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movimento> findMovimento(Long id, Tipo tipo, Date startDate, Date endDate, Cliente cliente, Boolean active) {
		entityManager.getTransaction().begin();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Movimento> query = criteriaBuilder.createQuery(Movimento.class);

		Root<Movimento> root = query.from(Movimento.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		Path<Long> idPath = root.<Long>get("id");
		Path<Boolean> activePath = root.<Boolean>get("active");
		Path<Tipo> tipoPath = root.<Tipo>get("tipo");
		Path<Cliente> clientePath = root.<Cliente>get("cliente");
		
		if (id != null) {
			Predicate predicate = criteriaBuilder.equal(idPath, id);
			predicates.add(predicate);
		}
		if (tipo != null) {
			Predicate predicate = criteriaBuilder.equal(tipoPath, tipo);
			predicates.add(predicate);
		}

		
		if (cliente != null) {
			Predicate predicate = criteriaBuilder.equal(clientePath, cliente);
			predicates.add(predicate);
		}

		if (startDate != null) {
			Predicate greaterThanOrEqualPredicate = criteriaBuilder
					.greaterThanOrEqualTo(root.<Date>get("dataRealizacao"), startDate);
			predicates.add(greaterThanOrEqualPredicate);
		}
		if (endDate != null) {
			Predicate lessThanPredicate = criteriaBuilder.lessThan(root.<Date>get("dataRealizacao"), endDate);
			predicates.add(lessThanPredicate);
		}

		if (active != null) {
			Predicate predicate = criteriaBuilder.equal(activePath, active);
			predicates.add(predicate);
		}

		query.where(predicates.toArray(new Predicate[predicates.size()]));

		TypedQuery<Movimento> typedQuery = entityManager.createQuery(query);
		entityManager.getTransaction().commit();
		return typedQuery.getResultList();
	}

}
