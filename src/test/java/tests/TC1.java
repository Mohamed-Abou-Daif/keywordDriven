package tests;

import org.testng.annotations.Test;

import base.Base;
import keyword.engine.KeyWordEngine;
/**
 * 
 * @author Mohamed Gamal Abou-Daif
 *
 */
public class TC1 extends Base{
	
	public KeyWordEngine keyWordEngine;
	
	@Test(enabled = true)
	public void makeOrderTest(){
		Base.childTest = Base.parentTest.createNode("Make an Order");
		keyWordEngine = new KeyWordEngine();
		keyWordEngine.startExecution("TC1");
	}
	

}
