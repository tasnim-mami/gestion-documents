

import static org.junit.Assert.assertEquals;





import org.junit.jupiter.api.Test;

import LoginUI;

class LoginTest {


	@Test
	void LoginSuccesful() {
		String username="hjhjh";
		String password="jjhjhjhgj";
		String msg=LoginUI.LoginResult(username,password);
		assertEquals("Login successful!",msg);
	}
	@Test
	void LoginFailed1() {
		String username="amine";
		String password="chakroun";
		String msg=LoginUI.LoginResult(username,password);
		assertEquals("Invalid username or password",msg);
	}
	@Test
	void LoginFailed2() {
		String username="";
		String password="";
		String msg=LoginUI.LoginResult(username,password);
		assertEquals("Invalid username or password",msg);
	}



}
