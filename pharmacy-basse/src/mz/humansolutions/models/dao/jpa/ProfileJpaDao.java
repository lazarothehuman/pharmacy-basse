package mz.humansolutions.models.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import mz.humansolutions.models.Profile;
import mz.humansolutions.models.dao.ProfileDao;
import mz.humansolutions.utils.JPAUtil;


public class ProfileJpaDao implements ProfileDao {
	
	private EntityManager entityManager = (EntityManager) new JPAUtil().getEntityManager();

	@Override
	public void create(Profile profile) {
		entityManager.getTransaction().begin();
		entityManager.persist(profile);
		entityManager.getTransaction().commit();
		
	}

	@Override
	public Profile find(Long id) {
		entityManager.getTransaction().begin();
		TypedQuery<Profile> query = entityManager.createQuery("select profile from Profile profile" + " where profile.id = :id",
				Profile.class);
		query.setParameter("id", id);
		List<Profile> profiles = query.getResultList();
		entityManager.getTransaction().commit();
		if (profiles.isEmpty())
			return null;
		Profile profile = profiles.get(0);
		return profile;
	}

	@Override
	public Profile findByName(String profilename) {
		entityManager.getTransaction().begin();
		TypedQuery<Profile> query = entityManager.createQuery("select profile from Profile profile" + " where profile.profilename = :profilename",
				Profile.class);
		query.setParameter("profilename", profilename);
		List<Profile> profiles = query.getResultList();
		entityManager.getTransaction().commit();
		if (profiles.isEmpty())
			return null;
		Profile profile = profiles.get(0);
		return profile;
	}

	@Override
	public void update(Profile profile) {
		entityManager.getTransaction().begin();
		entityManager.merge(profile);
		entityManager.getTransaction().commit();
	}

	@Override
	public List<Profile> find(Boolean active) {
		entityManager.getTransaction().begin();
		TypedQuery<Profile> query = entityManager.createQuery("select profile from Profile profile" + " where profile.active = :active",
				Profile.class);
		query.setParameter("active", active);
		List<Profile> profiles = query.getResultList();
		entityManager.getTransaction().commit();
		if (profiles.isEmpty())
			return null;
		return profiles;
	}


}
