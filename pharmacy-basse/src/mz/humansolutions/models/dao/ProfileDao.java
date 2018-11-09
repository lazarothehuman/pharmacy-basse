package mz.humansolutions.models.dao;

import java.util.List;

import mz.humansolutions.models.Profile;

public interface ProfileDao {

	void create(Profile profile);

	Profile find(Long id);

	Profile findByName(String profilename);

	void update(Profile profile);

	List<Profile> find(Boolean active);

}
