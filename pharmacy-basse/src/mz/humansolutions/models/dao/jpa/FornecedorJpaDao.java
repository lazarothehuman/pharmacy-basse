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
import mz.humansolutions.models.Fornecedor;
import mz.humansolutions.models.Sexo;
import mz.humansolutions.models.dao.ClienteDao;
import mz.humansolutions.models.dao.FornecedorDao;
import mz.humansolutions.utils.JPAUtil;

public class FornecedorJpaDao implements FornecedorDao {
	
	private EntityManager entityManager = (EntityManager) new JPAUtil().getEntityManager();


	@Override
	public void create(Fornecedor fornecedor) {
		entityManager.getTransaction().begin();
		entityManager.persist(fornecedor);
		entityManager.getTransaction().commit();
	}


	@Override
	public void update(Fornecedor fornecedor) {
		entityManager.getTransaction().begin();
		entityManager.merge(fornecedor);
		entityManager.getTransaction().commit();
		
	}


	@Override
	public List<Fornecedor> find(Long id, String nome, String telefone, String email, String endereco,Boolean active) {
		entityManager.getTransaction().begin();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Fornecedor> query = criteriaBuilder.createQuery(Fornecedor.class);

		Root<Fornecedor> root = query.from(Fornecedor.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		Path<Long> idPath = root.<Long>get("id");
		Path<String> nomePath = root.<String>get("nome");
		Path<String> emailPath = root.<String>get("email");
		Path<String> telefonePath = root.<String>get("telefone");
		Path<String> enderecoPath = root.<String>get("endereco");
		Path<Boolean> activePath = root.<Boolean>get("active");

		if (id != null) {
			Predicate predicate = criteriaBuilder.equal(idPath, id);
			predicates.add(predicate);
		}

		if (nome != null) {
			if (!nome.isEmpty()) {
				Predicate predicate = criteriaBuilder.like(nomePath, "%" + nome + "%");
				predicates.add(predicate);
			}
		}

		if (email != null) {
			if (!email.isEmpty()) {
				Predicate predicate = criteriaBuilder.like(emailPath, "%" + email + "%");
				predicates.add(predicate);
			}
		}

		if (telefone != null) {
			if (!telefone.isEmpty()) {
				Predicate predicate = criteriaBuilder.like(telefonePath, "%" + telefone + "%");
				predicates.add(predicate);
			}
		}
		
		if (endereco != null) {
			if (!endereco.isEmpty()) {
				Predicate predicate = criteriaBuilder.like(enderecoPath, "%" + endereco + "%");
				predicates.add(predicate);
			}
		}



		if (active != null) {
			Predicate predicate = criteriaBuilder.equal(activePath, active);
			predicates.add(predicate);
		}

		query.where(predicates.toArray(new Predicate[predicates.size()]));

		TypedQuery<Fornecedor> typedQuery = entityManager.createQuery(query);
		entityManager.getTransaction().commit();
		return typedQuery.getResultList();
	}

}
