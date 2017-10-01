package activeUML.UMLData.Wrapper;

import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.Iterator;

import activeUML.util.AttributedStringBuilder;

public class Enum extends Component {

	public Enum() {

	}

	@Override
	public Character getRepresetivChar() {
		return 'E';
	}

	@Override
	public void buildUMLContent() {
		super.addAssosiations();
		this.umlContents.add(this.builldEnumName());
		this.umlContents.add(this.buildFieldNames());
	}

	private AttributedStringBuilder[] builldEnumName() {
		AttributedStringBuilder enumNameBuilder = new AttributedStringBuilder();
		enumNameBuilder.append(this.modifiers.toUML());
		enumNameBuilder.append(this.componentName.toUML(14, TextAttribute.WEIGHT_BOLD));
		return new AttributedStringBuilder[] { enumNameBuilder };
	}

	private AttributedStringBuilder[] buildFieldNames() {
		AttributedStringBuilder fieldNameBuilder = new AttributedStringBuilder();
		this.fields.remove(this.fields.size() - 1);
		Iterator<Field> it = this.fields.iterator();

		while (it.hasNext()) {
			Field field = it.next();
			fieldNameBuilder.append(field.getComponentName().toUML(14, TextAttribute.WEIGHT_BOLD));
			if (it.hasNext()) {
				fieldNameBuilder.append(new AttributedString(","));
			}
		}
		return new AttributedStringBuilder[] { fieldNameBuilder };
	}
}
