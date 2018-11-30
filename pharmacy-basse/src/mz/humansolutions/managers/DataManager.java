package mz.humansolutions.managers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;

import mz.humansolutions.models.Medicamento;
import mz.humansolutions.models.Profile;
import mz.humansolutions.models.Transaccao;
import mz.humansolutions.models.User;



public interface DataManager {
	
	public void createUser(User user) throws UnsupportedEncodingException, GeneralSecurityException;
	public User findUser(String username);
	public List<User> findAllUsers(Boolean active);
	public boolean checkPassword(String password, String pass) throws GeneralSecurityException, IOException;
	public String encryptPassword(String password) throws UnsupportedEncodingException, GeneralSecurityException;
	public User findUser(Long id);
	public void setSelectedUser(User user);
	public User findCurrentUser();
	public void updateUser(User user) throws UnsupportedEncodingException, GeneralSecurityException;
	public Profile findProfile(String string);

	public List<Profile> findProfiles(Boolean active);
	public void createProfile(Profile profile);
	public Profile findProfile(Long id);
	public Transaccao findTransaccao(Long code);
	public void createTransaction(Transaccao transaction);
	public void updateProfile(Profile profile);
	public void updateTransaccao(Transaccao transaccao);
	public List<User> findUsers(String username, String nome, Profile profile, Boolean activee);
	public void addMedicamento(Medicamento medicamento);
	public List<Medicamento> findMedicamento(Long id,String fabricante,Boolean active,String nome,Double precoUnitario,
			Integer quadntidadeStock,String paisOrigem);
	void updateMedicamento(Medicamento selectedMedicamento);


}
