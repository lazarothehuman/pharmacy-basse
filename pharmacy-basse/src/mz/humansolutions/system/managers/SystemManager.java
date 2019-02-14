package mz.humansolutions.system.managers;

import mz.humansolutions.system.SystemFunction;
import mz.humansolutions.system.dao.SystemFunctionDao;
import mz.humansolutions.system.dao.jpa.SystemFunctionJpaDao;

public class SystemManager {
	
	SystemFunctionDao sistemaDao = new SystemFunctionJpaDao();

	public void addSystemFunction(SystemFunction function) {
		if (function != null) {
			sistemaDao.create(function);
		}
	}

	public SystemFunction getFunction(String function) {
		return sistemaDao.find(function);
	}

}
