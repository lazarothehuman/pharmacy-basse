package mz.humansolutions.models.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import mz.humansolutions.models.Medicamento;
import mz.humansolutions.models.dao.MedicamentoDao;
import mz.humansolutions.utils.JPAUtil;

public class MedicamentoJpaDao implements MedicamentoDao {
	private EntityManager entityManager = (EntityManager) new JPAUtil().getEntityManager();

	@Override
	public void create(Medicamento medicamento) {
		entityManager.getTransaction().begin();
		entityManager.persist(medicamento);
		entityManager.getTransaction().commit();

	}

	@Override
	public List<Medicamento> findMedicamento(Long id, String fabricante, Boolean active, String nome,
			Double precoUnitario, Integer quadntidadeStock, String paisOrigem) {
		entityManager.getTransaction().begin();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Medicamento> query = criteriaBuilder.createQuery(Medicamento.class);

		Root<Medicamento> root = query.from(Medicamento.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		Path<Long> idPath = root.<Long>get("id");
		Path<String> nomePath = root.<String>get("nome");
		Path<String> fabricantePath = root.<String>get("fabricante");
		Path<String> paisOrigemPath = root.<String>get("paisOrigem");
		Path<Double> precoUnitarioPath = root.<Double>get("precoUnitario");
		Path<Integer> quantidadeStockPath = root.<Integer>get("quantidadeStock");
		Path<Boolean> activePath = root.<Boolean>get("active");

		if (id != null) {
			System.out.println("ID: " + id);
			Predicate predicate = criteriaBuilder.equal(idPath, id);
			predicates.add(predicate);
		}

		if (nome != null) {
			if (!nome.isEmpty()) {
				Predicate predicate = criteriaBuilder.like(nomePath, "%" + nome + "%");
				predicates.add(predicate);
			}
		}

		if (fabricante != null) {
			if (!fabricante.isEmpty()) {
				Predicate predicate = criteriaBuilder.like(fabricantePath, "%" + fabricante + "%");
				predicates.add(predicate);
			}
		}

		if (paisOrigem != null) {
			if (!paisOrigem.isEmpty()) {
				Predicate predicate = criteriaBuilder.like(paisOrigemPath, "%" + paisOrigem + "%");
				predicates.add(predicate);
			}
		}

		if (precoUnitario != null) {
			Predicate predicate = criteriaBuilder.equal(precoUnitarioPath, precoUnitario);
			predicates.add(predicate);
		}

		if (quadntidadeStock != null) {
			Predicate predicate = criteriaBuilder.equal(precoUnitarioPath, quantidadeStockPath);
			predicates.add(predicate);
		}

		if (active != null) {
			Predicate predicate = criteriaBuilder.equal(activePath, active);
			predicates.add(predicate);
		}

		query.where(predicates.toArray(new Predicate[predicates.size()]));

		TypedQuery<Medicamento> typedQuery = entityManager.createQuery(query);
		entityManager.getTransaction().commit();
		return typedQuery.getResultList();
	}

	@Override
	public void update(Medicamento medicamento) {
		entityManager.getTransaction().begin();
		entityManager.merge(medicamento);
		entityManager.getTransaction().commit();
	}
}
