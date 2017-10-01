package activeUML.util;

import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.text.CharacterIterator;

public class AttributedStringUtil {

	public static AttributedString concat(AttributedString first, AttributedString secound, String seperation) {
		String firstString = AttributedStringUtil.getString(first);
		String secoundString = AttributedStringUtil.getString(secound);
		String resultString = firstString + seperation + secoundString;
		AttributedString result = new AttributedString(resultString);
		AttributedStringUtil.addAttributes(result, first, secound, seperation.length());
		return result;
	}

	public static AttributedString concat(AttributedString first, AttributedString secound) {
		return AttributedStringUtil.concat(first, secound, "");
	}

	private static void addAttributes(AttributedString result, AttributedString first, AttributedString secound,
			int seperationOffset) {
		AttributedCharacterIterator resultIterator = result.getIterator();
		AttributedCharacterIterator firstIterator = first.getIterator();
		AttributedCharacterIterator secoundIterator = secound.getIterator();

		char resultCharacter = resultIterator.current();
		int truePosition = 0;
		int usePosition = 0;

		while (resultCharacter != CharacterIterator.DONE) {
			usePosition = truePosition;
			AttributedCharacterIterator it = AttributedStringUtil.getIterator(firstIterator, secoundIterator);
			if (it == null) {
				break;
			}
			if (it == secoundIterator) {
				usePosition += seperationOffset;
			}
			result.addAttributes(it.getAttributes(), usePosition, usePosition + 1);
			resultCharacter = resultIterator.next();
			it.next();
			truePosition++;
		}
	}

	private static AttributedCharacterIterator getIterator(AttributedCharacterIterator firstIterator,
			AttributedCharacterIterator secoundIterator) {
		if (firstIterator.current() != CharacterIterator.DONE) {
			return firstIterator;
		}
		if (secoundIterator.current() != CharacterIterator.DONE) {
			return secoundIterator;
		}
		return null;

	}

	public static String getString(AttributedString attributedString) {
		AttributedCharacterIterator it = attributedString.getIterator();
		StringBuilder stringBuilder = new StringBuilder();

		char ch = it.current();
		while (ch != CharacterIterator.DONE) {
			stringBuilder.append(ch);
			ch = it.next();
		}
		return stringBuilder.toString();
	}
}
