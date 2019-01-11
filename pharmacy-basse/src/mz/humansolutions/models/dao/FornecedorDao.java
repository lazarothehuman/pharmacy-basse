package mz.humansolutions.models.dao;

import java.util.Date;
import java.util.List;

import mz.humansolutions.models.Cliente;
import mz.humansolutions.models.Fornecedor;
import mz.humansolutions.models.Sexo;

public interface FornecedorDao {

	void create(Fornecedor fornecedor);
	void update(Fornecedor fornecedor);
	List<Fornecedor> find(Long id, String nome, String telefone, String email, String endereco,Boolean active);

}
