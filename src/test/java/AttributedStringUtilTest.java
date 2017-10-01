import static org.junit.Assert.assertEquals;

import java.awt.font.TextAttribute;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.CharacterIterator;

import org.junit.Test;

import activeUML.util.AttributedStringUtil;

public class AttributedStringUtilTest {
	private AttributedStringUtil util;
	@Test
	public void test() {
		AttributedString str = new AttributedString("test");
		str.addAttribute(TextAttribute.SIZE,20,0,"test".length());
		
		AttributedString str1 = new AttributedString("software");
		str1.addAttribute(TextAttribute.WEIGHT,10,0,"software".length());
	
		
		
		 AttributedString combined = AttributedStringUtil.concat(str, str1);
		AttributedCharacterIterator it = combined.getIterator();
		
		int counter = 1;
		char s = it.current();
		while(s != CharacterIterator.DONE){
			System.out.println(it.getAttributes().toString());
			if(counter == 1){
				assertEquals(it.getAttributes().containsKey(TextAttribute.SIZE),true);
			}
			if(counter == 7){
				assertEquals(it.getAttributes().containsKey(TextAttribute.WEIGHT),true);
			}
			s = it.next();
			counter++;
		}
	
	}

}
