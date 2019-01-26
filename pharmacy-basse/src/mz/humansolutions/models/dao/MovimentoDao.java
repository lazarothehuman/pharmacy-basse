package mz.humansolutions.models.dao;

import java.util.Date;
import java.util.List;

import mz.humansolutions.models.Cliente;
import mz.humansolutions.models.Movimento;
import mz.humansolutions.models.Tipo;

public interface MovimentoDao {

	void create(Movimento movimento);

	List<Movimento> findMedicamento(Long id,Boolean active);

	List<Movimento> findMovimento(Long id, Boolean active);

	List<Movimento> findMovimento(Long id, Tipo tipo, Date startDate, Date endDate, Cliente cliente, Boolean active);

	//List<Movimento> findMovimento2(Long id, Boolean active);

}
