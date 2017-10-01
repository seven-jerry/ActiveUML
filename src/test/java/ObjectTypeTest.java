import static org.junit.Assert.*;

import org.junit.Test;

import activeUML.UMLData.Helper.ObjectType;

public class ObjectTypeTest {
	private ObjectType objectType;
	@Test
	public void test() {
		this.objectType = new ObjectType();
		this.objectType.setObjectTypeString("[Ljava.lang.String");
		assertTrue(this.objectType.getArrayDimention() == 1);
		System.out.println(this.objectType.toUML());
	}

}
