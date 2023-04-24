package projet_gl;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class test_code {

	@Test
	void test_same_name_users(String user,String[] liste_users) {
		boolean test=false;
		for (int i=0;liste_users[i]!="";i++) {
			if (liste_users[i]==user)
			{
				test=true;
				break;
			}
		}
		assertEquals(test,false);
	}
	@Test
	void test_same_name_docs(String doc,String[] liste_docs) {
		boolean test=false;
		for (int i=0;liste_docs[i]!="";i++) {
			if (liste_docs[i]==doc)
			{
				test=true;
				break;
			}
		}
		assertEquals(test,false);
	}
	
	@Test
	void empty_name_user(String user) {
		boolean test=false;
		test=(user=="");
		
		assertEquals(test,false);
	}
@Test
void empty_name_doc(String doc) {
	boolean test=false;
	test=(doc=="");
	
	assertEquals(test,false);
	
}
@Test
void test_find_docs(String doc,String[] liste_docs) {
	boolean test=false;
	for (int i=0;liste_docs[i]!="";i++) {
		if (liste_docs[i]==doc)
		{
			test=true;
			break;
		}
	}
	assertEquals(test,true);
}

@Timeout(2)

}
