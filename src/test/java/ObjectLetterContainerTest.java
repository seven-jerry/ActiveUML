import static org.junit.Assert.*;

import org.junit.Test;

import activeUML.UMLData.Helper.ObjectLetterContainer;

public class ObjectLetterContainerTest {
	private ObjectLetterContainer container;
	
	@Test
	public void test() {
		this.container = new ObjectLetterContainer();
		this.container.setObjectLetterForChar('L');
		boolean vLetter = this.container.hasValidLetter();		
		assertTrue(vLetter == true);
	}

}
