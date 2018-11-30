package mz.humansolutions.models.dao;

import java.util.List;

import mz.humansolutions.models.Medicamento;
import mz.humansolutions.models.Movimento;

public interface MedicamentoDao {

	void create(Medicamento medicamento);

	List<Medicamento> findMedicamento(Long id,String fabricante,Boolean active,String nome,Double precoUnitario,
			Integer quadntidadeStock,String paisOrigem);

	void update(Medicamento selectedMedicamento);


}
