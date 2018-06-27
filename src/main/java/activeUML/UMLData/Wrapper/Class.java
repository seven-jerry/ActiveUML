package activeUML.UMLData.Wrapper;

import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import activeUML.Converter.AssociationType;
import activeUML.Renderer.RenderConstants;
import activeUML.UMLData.Helper.ComponentName;
import activeUML.UMLData.Helper.Method;
import activeUML.UMLData.Helper.MethodParameter;
import activeUML.util.AttributedStringBuilder;

public class Class extends Component {
	public Class() {
	}

	@Override
	public void buildUMLContent() {
		super.buildUMLContent();
		this.umlContents.add(this.buildClassName());
		if (this.interfaces.size() > 0) {
			this.umlContents.add(this.buildInterfaces());
		}
		if (this.fields.size() > 0) {
			this.umlContents.add(this.buildFieldNames());
		}
		if (this.methods.size() > 0) {
			this.umlContents.add(this.buildMethodsNames());
		}
		this.addAssosiations();
	}

	private AttributedStringBuilder[] buildClassName() {
		AttributedStringBuilder iterfaceNameBuilder = new AttributedStringBuilder();
		iterfaceNameBuilder.append(this.modifiers.toUML());
		iterfaceNameBuilder.append(this.componentName.toUML(16, TextAttribute.WEIGHT_BOLD));
		if (this.superComponentName != null) {
			iterfaceNameBuilder.append(this.getExtends());
			iterfaceNameBuilder.append(this.superComponentName.toUML(14, TextAttribute.WEIGHT_BOLD));
		}
		iterfaceNameBuilder.append(this.generics.toUML());
		return new AttributedStringBuilder[] { iterfaceNameBuilder };
	}

	private AttributedString getExtends() {
		AttributedString buildString = new AttributedString("extends");
		buildString.addAttribute(TextAttribute.FOREGROUND, RenderConstants.MODIFIER_COLOR, 0, "extends".length());
		buildString.addAttribute(TextAttribute.SIZE, 12, 0, "extends".length());
		buildString.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_SEMIBOLD, 0, "extends".length());
		return buildString;
	}

	private AttributedStringBuilder[] buildInterfaces() {
		AttributedStringBuilder interfaceBuilder = new AttributedStringBuilder();
		interfaceBuilder.append(this.getImplements());
		Iterator<ComponentName> it = this.interfaces.iterator();
		while (it.hasNext()) {
			ComponentName iterface = it.next();
			interfaceBuilder.append(iterface.toUML());
			if (it.hasNext()) {
				interfaceBuilder.append(new AttributedString(","));
			}
		}
		return new AttributedStringBuilder[] { interfaceBuilder };
	}

	private AttributedString getImplements() {
		AttributedString buildString = new AttributedString("implements");
		buildString.addAttribute(TextAttribute.FOREGROUND, RenderConstants.MODIFIER_COLOR, 0, "implements".length());
		buildString.addAttribute(TextAttribute.SIZE, 12, 0, "extends".length());
		buildString.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_SEMIBOLD, 0, "implements".length());
		return buildString;
	}

	private AttributedStringBuilder[] buildFieldNames() {
		List<AttributedStringBuilder> lineField = new ArrayList<AttributedStringBuilder>();

		Iterator<Field> it = this.fields.iterator();
		while (it.hasNext()) {
			Field field = it.next();
			AttributedStringBuilder fieldNameBuilder = new AttributedStringBuilder();
			fieldNameBuilder.append(field.toUML());
			lineField.add(fieldNameBuilder);
		}
		AttributedStringBuilder[] stringArray = new AttributedStringBuilder[lineField.size()];
		return lineField.toArray(stringArray);
	}

	private AttributedStringBuilder[] buildMethodsNames() {
		List<AttributedStringBuilder> lineMethod = new ArrayList<AttributedStringBuilder>();

		Iterator<Method> it = this.methods.iterator();
		while (it.hasNext()) {
			Method method = it.next();
			AttributedStringBuilder methodNameBuilder = new AttributedStringBuilder();
			methodNameBuilder.append(method.toUML());
			lineMethod.add(methodNameBuilder);
		}
		AttributedStringBuilder[] stringArray = new AttributedStringBuilder[lineMethod.size()];
		return lineMethod.toArray(stringArray);

	}

	@Override
	protected void addAssosiations() {

		super.addAssosiations();
		if (this.superComponentName != null) {
			this.addAssoisiation(AssociationType.INHERITANCE, this.superComponentName.getName());
		}
		for (Method method : this.methods) {
			for (MethodParameter paramter : method.getParameters()) {
				if (paramter.isInterface()) {
					this.addAssoisiation(AssociationType.INTERFACE_DECLARATION, paramter.getComponentName().getName());
				} else {
					this.addAssoisiation(AssociationType.DIRECT, paramter.getComponentName().getName());
				}
			}
		}
		for (ComponentName iFace : this.interfaces) {
			this.addAssoisiation(AssociationType.INTERFACE_IMPLEMENTATION, iFace.getName());
		}
	}

}
