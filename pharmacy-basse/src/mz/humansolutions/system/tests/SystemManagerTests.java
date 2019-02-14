package mz.humansolutions.system.tests;

import org.junit.Assert;
import org.junit.Test;

import mz.humansolutions.system.SystemFunction;
import mz.humansolutions.system.managers.SystemManager;
import mz.humansolutions.system.managers.SystemManagerImp;

public class SystemManagerTests {
	
	SystemManager system = new SystemManagerImp();
	
	@Test
	public void addFunctionTest() {
		
		SystemFunction function = new SystemFunction();
		function.setName("fecho");
		function.setValue("false");
		
		system.addSystemFunction(function);
		Assert.assertNotNull(function.getId());
		
	}
	
	@Test
	public void findFunctionTest() {
		SystemFunction function = system.getFunction("fecho");
		Assert.assertNotNull(function);
		Assert.assertEquals("false", function.getValue());
	}

}
