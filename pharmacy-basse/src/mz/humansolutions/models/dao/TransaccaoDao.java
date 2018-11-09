package mz.humansolutions.models.dao;

import mz.humansolutions.models.Transaccao;

public interface TransaccaoDao {

	Transaccao find(Long code);

	void create(Transaccao transaction);

	void update(Transaccao transaccao);

}
