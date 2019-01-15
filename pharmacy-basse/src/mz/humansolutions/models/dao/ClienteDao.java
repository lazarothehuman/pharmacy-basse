package mz.humansolutions.models.dao;

import java.util.Date;
import java.util.List;

import mz.humansolutions.models.Cliente;
import mz.humansolutions.models.Sexo;

public interface ClienteDao {

	void create(Cliente cliente);
	void update(Cliente cliente);
	List<Cliente> find(Long id, String nome, String email, String telefone, Date selectedStartDate,
			Date selectedEndDate, Sexo sexo, Boolean activee);
	Cliente find(Long id);

}
