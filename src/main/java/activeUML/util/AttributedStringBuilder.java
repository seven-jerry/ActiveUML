package activeUML.util;

import java.text.AttributedString;

public class AttributedStringBuilder {
	private AttributedString builString;

	public AttributedStringBuilder() {
		this.builString = new AttributedString("");
	}

	public AttributedStringBuilder(Character c) {
		this.builString = new AttributedString(c.toString());
	}

	public void append(AttributedStringBuilder strings, String seperationString) {
		if (strings == null) {
			return;
		}
		this.append(strings.getBuilStirng(), seperationString);

	}

	public void append(AttributedString string, String seperationString) {
		if (string == null) {
			return;
		}
		this.builString = AttributedStringUtil.concat(this.builString, string, seperationString);
	}

	public void append(AttributedString string) {
		this.append(string, " ");
	}

	public void append(AttributedStringBuilder string) {
		this.append(string, " ");
	}

	public AttributedString getBuilStirng() {
		return this.builString;
	}

	@Override
	public String toString() {
		return AttributedStringUtil.getString(this.builString);
	}

}
