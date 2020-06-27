package tests;

import org.testng.annotations.Test;

import com.qa.hs.keyword.base.Base;
import com.qa.hs.keyword.engine.KeyWordEngine;
/**
 * 
 * @author Mohamed Gamal Abou-Daif
 *
 */
public class LoginTest extends Base{
	
	public KeyWordEngine keyWordEngine;
	
	@Test(enabled = true)
	public void loginTest(){
		Base.childTest = Base.parentTest.createNode("Send Mail");
		keyWordEngine = new KeyWordEngine();
		keyWordEngine.startExecution("login");
	}
	
	@Test(enabled = false)
	public void signUpTest(){
		Base.childTest = Base.parentTest.createNode("Send Mail");
		keyWordEngine = new KeyWordEngine();
		keyWordEngine.startExecution("signup");
	}
	
	
	

}
