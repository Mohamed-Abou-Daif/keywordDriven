package tests;

import org.testng.annotations.Test;

import com.qa.hs.keyword.base.Base;
import com.qa.hs.keyword.engine.KeyWordEngine;
/**
 * 
 * @author Mohamed Gamal Abou-Daif
 *
 */
public class TC1 extends Base{
	
	public KeyWordEngine keyWordEngine;
	
	@Test(enabled = true)
	public void searchTest(){
		Base.childTest = Base.parentTest.createNode("Send Mail");
		keyWordEngine = new KeyWordEngine();
		keyWordEngine.startExecution("TC1");
	}
	
	@Test(enabled = false)
	public void signUpTest(){
		Base.childTest = Base.parentTest.createNode("Send Mail");
		keyWordEngine = new KeyWordEngine();
		keyWordEngine.startExecution("signup");
	}
	
	
	

}
