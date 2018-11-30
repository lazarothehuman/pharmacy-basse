package mz.humansolutions.managers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;

import mz.humansolutions.models.Medicamento;
import mz.humansolutions.models.Movimento;
import mz.humansolutions.models.Profile;
import mz.humansolutions.models.Transaccao;
import mz.humansolutions.models.User;
import mz.humansolutions.models.dao.MedicamentoDao;
import mz.humansolutions.models.dao.MovimentoDao;
import mz.humansolutions.models.dao.ProfileDao;
import mz.humansolutions.models.dao.SessionHelperDao;
import mz.humansolutions.models.dao.TransaccaoDao;
import mz.humansolutions.models.dao.UserDao;
import mz.humansolutions.models.dao.jpa.MedicamentoJpaDao;
import mz.humansolutions.models.dao.jpa.MovimentoJpaDao;
import mz.humansolutions.models.dao.jpa.ProfileJpaDao;
import mz.humansolutions.models.dao.jpa.SessionHelperJpaDao;
import mz.humansolutions.models.dao.jpa.TransaccaoJpaDao;
import mz.humansolutions.models.dao.jpa.UserJpaDao;
import mz.humansolutions.utils.ProtectedConfigFile;

public class DataManagerImp implements DataManager {

	ProfileDao profileDao = new ProfileJpaDao();
	UserDao userDao = new UserJpaDao();
	SessionHelperDao sessionHelperDao = new SessionHelperJpaDao();
	TransaccaoDao transaccaoDao = new TransaccaoJpaDao();
	MedicamentoDao medicamentoDao = new MedicamentoJpaDao();
	private MovimentoDao movimentoDao=new MovimentoJpaDao();

	@Override
	public void createUser(User user) throws UnsupportedEncodingException, GeneralSecurityException {
		if (user != null) {
			user.setPassword(encryptPassword(user.getPassword()));
			userDao.create(user);
		}
	}

	@Override
	public User findUser(String username) {
		return userDao.find(username);
	}

	@Override
	public List<User> findAllUsers(Boolean active) {
		return userDao.findAll(active);
	}

	@Override
	public boolean checkPassword(String password, String pass) throws GeneralSecurityException, IOException {
		String p = ProtectedConfigFile.decrypt(password);
		if (p.equals(pass))
			return true;
		else
			return false;
	}

	@Override
	public String encryptPassword(String password) throws UnsupportedEncodingException, GeneralSecurityException {
		return ProtectedConfigFile.encrypt(password);
	}

	@Override
	public User findUser(Long id) {
		return userDao.find(id);
	}

	@Override
	public void setSelectedUser(User user) {
		if (user != null)
			sessionHelperDao.set(user);

	}

	@Override
	public User findCurrentUser() {
		return sessionHelperDao.get();
	}

	@Override
	public void updateUser(User user) throws UnsupportedEncodingException, GeneralSecurityException {
		if (user != null) {
			if (user.getPassword().length() != 128)
				user.setPassword(encryptPassword(user.getPassword()));
			userDao.update(user);
		}

	}

	@Override
	public Profile findProfile(String profilename) {
		return profileDao.findByName(profilename);
	}

	@Override
	public List<Profile> findProfiles(Boolean active) {
		return profileDao.find(Boolean.TRUE);
	}

	@Override
	public void createProfile(Profile profile) {
		if (profile != null) {
			profileDao.create(profile);
		}

	}

	@Override
	public Profile findProfile(Long id) {
		return profileDao.find(id);
	}

	@Override
	public Transaccao findTransaccao(Long code) {
		return transaccaoDao.find(code);
	}

	@Override
	public void createTransaction(Transaccao transaction) {
		if (transaction != null) {
			transaccaoDao.create(transaction);
		}

	}

	@Override
	public void updateProfile(Profile profile) {
		if (profile != null)
			profileDao.update(profile);

	}

	@Override
	public void updateTransaccao(Transaccao transaccao) {
		transaccaoDao.update(transaccao);

	}

	public List<User> findUsers(String username, String nome, Profile profile, Boolean active) {
		return userDao.find(username, nome, profile, active);
	}

	@Override
	public void addMedicamento(Medicamento medicamento) {
		if (medicamento != null) {
			medicamentoDao.create(medicamento);
		}

	}

	@Override
	public List<Medicamento> findMedicamento(Long id, String fabricante, Boolean active, String nome,
			Double precoUnitario, Integer quadntidadeStock, String paisOrigem) {

		return medicamentoDao.findMedicamento(id, fabricante, active, nome, precoUnitario, quadntidadeStock,
				paisOrigem);
	}

	@Override
	public void updateMedicamento(Medicamento selectedMedicamento) {
		if (selectedMedicamento != null) {
			medicamentoDao.update(selectedMedicamento);
		}

	}

	public void addMovimento(Movimento movimento) {
		if(movimento!=null) {
			movimentoDao.create(movimento);
		}
		
	}

}
