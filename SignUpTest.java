

import static org.junit.Assert.assertEquals;





import org.junit.jupiter.api.Test;


import Project.SignUpUI;

class SignUpTest {

	@Test
	void SignupSuccesful() {
		String firstname ="dgf";
		String lastname ="dgf";
		String niv ="sss";
		String username="aaa";
		String password="jjhjhjhgj";
		String msg=SignUpUI.SignUpResult(firstname, lastname, niv, username, password);
		assertEquals("Compte peut etre Créer",msg);
	}
	@Test
	void SignUpFailed1() {
		String firstname ="";
		String lastname ="";
		String niv ="";
		String username="";
		String password="";
		String msg=SignUpUI.SignUpResult(firstname, lastname, niv, username, password);
		assertEquals("Please Fill all the Fields",msg);
	}
	@Test
	void SignUpFailed2() {
		String firstname ="dgf";
		String lastname ="dgf";
		String niv ="dgf";
		String username="fdfjhjh";
		String password="jjhjhjhgj";
		String msg=SignUpUI.SignUpResult(firstname, lastname, niv, username, password);
		assertEquals("Username Used!",msg);
	}



}

