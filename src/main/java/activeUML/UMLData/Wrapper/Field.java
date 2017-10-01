package activeUML.UMLData.Wrapper;

import java.awt.font.TextAttribute;

import activeUML.UMLData.Helper.ComponentName;
import activeUML.UMLData.Helper.IFieldSetter;
import activeUML.UMLData.Helper.ModifierContainer;
import activeUML.UMLData.Helper.ObjectType;
import activeUML.util.AttributedStringBuilder;

public class Field implements IFieldSetter {
	// modifers
	private ModifierContainer modifiers;
	// Type
	private ObjectType objectType;
	// Name
	private ComponentName name;

	public Field() {
		this.modifiers = new ModifierContainer();
		this.objectType = new ObjectType();
	}

	public boolean isInterface() {
		return this.modifiers.isInterface();
	}

	@Override
	public void setModefierInput(Integer modifierNumber) {
		this.modifiers.setModefierInput(modifierNumber);
	}

	@Override
	public void setObjectTypeString(String inputString) {
		this.objectType.setObjectTypeString(inputString);
	}

	@Override
	public void setComponentName(ComponentName name) {
		this.name = name;
	}

	public ComponentName getComponentName() {
		return this.name;
	}

	public AttributedStringBuilder toUML() {
		AttributedStringBuilder buildString = new AttributedStringBuilder();
		buildString.append(this.modifiers.toUML());
		buildString.append(this.objectType.toUML());
		buildString.append(this.name.toUML(12, TextAttribute.WEIGHT_BOLD));
		return buildString;
	}

}
