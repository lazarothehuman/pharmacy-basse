package mz.humansolutions.models.dao;

import java.util.List;

import mz.humansolutions.models.Movimento;

public interface MovimentoDao {

	void create(Movimento movimento);

	List<Movimento> findMedicamento(Long id,Boolean active);

	List<Movimento> findMovimento(Long id, Boolean active);

	//List<Movimento> findMovimento2(Long id, Boolean active);

}
