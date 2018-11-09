package mz.humansolutions.models.dao;

import mz.humansolutions.models.User;

public interface SessionHelperDao {

	void set(User user);
	User get();

}
