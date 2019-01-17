package mz.humansolutions.tests;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Cliente;
import mz.humansolutions.models.Medicamento;
import mz.humansolutions.models.Movimento;
import mz.humansolutions.models.Profile;
import mz.humansolutions.models.Sexo;
import mz.humansolutions.models.Tipo;
import mz.humansolutions.models.Transaccao;
import mz.humansolutions.models.User;

public class DataManagerImpTest {

	DataManager dataManager = new DataManagerImp();

	@Test
	public void testCreateProfile() {// runned
		Profile profile = new Profile();
		profile.setProfilename("Caixa");
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

		Profile profile = dataManager.findProfile("Caixa");
		User user = new User();
		user.setUsername("caixa");
		user.setName("caixa");
		user.setProfile(profile);
		user.setPassword("1234");
		user.setTelefone("+258");
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
		transaction.setCode(207l);
		transaction.setUrl("/application/forms/Add-Fornecedor.fxml");
		dataManager.createTransaction(transaction);
		Assert.assertNotNull(transaction.getId());
	}

	@Test
	public void testFindTransactionByCode() {// works but lazy
		Transaccao transaccao = dataManager.findTransaccao(203l);
		Assert.assertNotNull(transaccao);
		Assert.assertEquals("/application/forms/Add-Movimento.fxml", transaccao.getUrl());
	}

	@Test
	public void testCreateTransactionProfile() {// failed
		Profile profile = dataManager.findProfile(5l);
		Transaccao transaccao = dataManager.findTransaccao(203l);
		profile.getTransaccoes().add(transaccao);
		transaccao.addProfile(profile);
		dataManager.updateProfile(profile);
		dataManager.updateTransaccao(transaccao);
		Assert.assertEquals(8, profile.getTransaccoes().size());
		Assert.assertEquals(8, transaccao.getProfiles().size());
	}
	
	@Test
	public void testFindProfileTransaction() {
		Profile profile = dataManager.findProfile(1l);
		List<Transaccao> transaccoes = profile.getTransaccoes();
		Assert.assertEquals(1, transaccoes.size());
	}
	
	@Test
	public void testFindTransaccaoProfile() {
		Transaccao transaccao = dataManager.findTransaccao(203l);
		List<Profile> profiles = transaccao.getProfiles();
		Assert.assertEquals(1, profiles.size());
	}
	
	@Test
	public void testCreateCliente() {
		Cliente cliente = new Cliente();
		cliente.setNome("Sheridan Oliveira");
		cliente.setDataNascimento(new Date());
		cliente.setSexo(Sexo.MASCULINO);
		dataManager.createCliente(cliente);
		Assert.assertNotNull(cliente.getId());
	}
	
	@Test
	public void testFindMedicamentoByCode() {//works
		Medicamento medicamento = dataManager.findMedicamentoByCode("1231314");
		Assert.assertNotNull(medicamento);
		Assert.assertEquals("xarope", medicamento.getNome());
	}
	
	@Test
	public void testFindClienteById() {
		Cliente cliente = dataManager.findCliente(1l);
		Assert.assertNotNull(cliente);
	}
	
	@Test
	public void testCreateVenda() {//works but needs to validate se tem stock para fazer o move
		Medicamento medicamento = dataManager.findMedicamentoByCode("1231314");
		Cliente cliente = dataManager.findCliente(1l);
		User registador = dataManager.findUser(1l);
		Movimento venda = new Movimento();
		venda.setDataRealizacao(new Date());
		venda.setQuantidade(5);
		venda.setTipo(Tipo.SAIDA);
		venda.setMedicamento(medicamento);
		venda.setCliente(cliente);
		venda.setRegistador(registador);
		dataManager.createMovimento(venda);
		System.out.println(medicamento.getMovimentos().size());
		for (Movimento moves : medicamento.getMovimentos()) 
			System.out.println(moves.getTipo());
		
		Assert.assertNotNull(venda.getId());
		
	}
	
	@Test
	public void testCreateEntrada() {//works
		Medicamento medicamento = dataManager.findMedicamentoByCode("1231314");
		Cliente cliente = dataManager.findCliente(1l);
		User registador = dataManager.findUser(1l);
		Movimento entrada = new Movimento();
		entrada.setDataRealizacao(new Date());
		entrada.setQuantidade(4);
		entrada.setTipo(Tipo.SAIDA);
		entrada.setMedicamento(medicamento);
		entrada.setCliente(cliente);
		entrada.setRegistador(registador);
		dataManager.createMovimento(entrada);
		System.out.println(medicamento.getQuantidadeStock());
		Assert.assertNotNull(entrada.getId());
		
	}



}
