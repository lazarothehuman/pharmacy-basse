package mz.humansolutions.tests;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;

import mz.humansolutions.models.Mes;
import mz.humansolutions.models.Profile;

import mz.humansolutions.models.Transaccao;
import mz.humansolutions.models.User;

public class DataManagerImpTest {

	DataManager dataManager = new DataManagerImp();

	@Test
	public void testCreateProfile() {// runned
		Profile profile = new Profile();
		profile.setProfilename("Administrador informatico");
		dataManager.createProfile(profile);
		Assert.assertNotNull(profile.getId());
	}

	@Test
	public void testFindProfileById() {
		Profile profile = dataManager.findProfile(1l);
		Assert.assertNotNull(profile);
		Assert.assertEquals("Administrador informatico", profile.getProfilename());
	}

	@Test
	public void testCreateUser() {// done

		Profile profile = dataManager.findProfile("Administrador informatico");
		User user = new User();
		user.setUsername("lazaro");
		user.setName("Lazaro Mathe Junior");
		user.setProfile(profile);
		user.setPassword("1234");
		user.setTelefone("+258825004957");
		profile.addUser(user);
		

		try {
			dataManager.createUser(user);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(user.getId());
	}

	@Test
	public void testCreateTransaction() {
		Transaccao transaction = new Transaccao();
		transaction.setCode(201l);
		transaction.setUrl("/application/forms/Add-Membro.fxml");
		dataManager.createTransaction(transaction);
		Assert.assertNotNull(transaction.getId());
	}

	@Test
	public void testFindTransactionByCode() {
		Transaccao transaccao = dataManager.findTransaccao(201l);
		Assert.assertNotNull(transaccao);
		Assert.assertEquals("/application/forms/Add-Membro.fxml", transaccao.getUrl());
	}

	@Test
	public void testCreateTransactionProfile() {// failed
		Profile profile = dataManager.findProfile(1l);
		Transaccao transaccao = dataManager.findTransaccao(201l);
		profile.getTransaccoes().add(transaccao);
		transaccao.addProfile(profile);
		dataManager.updateProfile(profile);
		dataManager.updateTransaccao(transaccao);
		Assert.assertEquals(2, profile.getTransaccoes().size());
		Assert.assertEquals(2, transaccao.getProfiles().size());
	}



}
