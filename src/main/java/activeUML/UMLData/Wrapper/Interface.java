package activeUML.UMLData.Wrapper;

import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import activeUML.Converter.AssociationType;
import activeUML.UMLData.Helper.ComponentName;
import activeUML.UMLData.Helper.Method;
import activeUML.util.AttributedStringBuilder;

public class Interface extends Component {
	public Interface() {
	}

	@Override
	public void buildUMLContent() {
		this.addAssosiations();
		this.umlContents.add(this.buildInterfaceName());
		this.umlContents.add(this.buildMethodsNames());
	}

	@Override
	public Character getRepresetivChar() {
		return 'I';
	}

	private AttributedStringBuilder[] buildInterfaceName() {
		AttributedStringBuilder iterfaceNameBuilder = new AttributedStringBuilder();
		iterfaceNameBuilder.append(this.modifiers.toUML());
		iterfaceNameBuilder.append(this.componentName.toUML());
		if (this.interfaces.size() > 0) {
			iterfaceNameBuilder.append(this.getExtends());
			Iterator<ComponentName> it = this.interfaces.iterator();
			while (it.hasNext()) {
				ComponentName iFaceName = it.next();
				iterfaceNameBuilder.append(iFaceName.toUML());
				if (it.hasNext()) {
					iterfaceNameBuilder.append(new AttributedString(","));
				}
			}
		}
		iterfaceNameBuilder.append(this.generics.toUML());
		return new AttributedStringBuilder[] { iterfaceNameBuilder };
	}

	private AttributedString getExtends() {
		return new AttributedString("extends");
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
		for (ComponentName iFace : this.interfaces) {
			this.addAssoisiation(AssociationType.INHERITANCE, iFace.getName());
		}
	}
}
