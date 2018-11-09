package mz.humansolutions.models.dao;

import java.util.List;

import mz.humansolutions.models.Profile;
import mz.humansolutions.models.User;

public interface UserDao {
	
	void create(User user);
	User find(String username);
	List<User> find(String username, String nome, Profile profile, Boolean active);
	List<User> findAll(Boolean active);
	void update(User user);
	User find(Long id);

}
