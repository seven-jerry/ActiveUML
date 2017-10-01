package activeUML.UMLData.Wrapper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import activeUML.Converter.Assosiation;
import activeUML.Converter.AssosiationType;
import activeUML.Converter.IAssosiationMapable;
import activeUML.Converter.IAssosiationMapper;
import activeUML.UMLData.Helper.ComponentName;
import activeUML.UMLData.Helper.GenericSigniture;
import activeUML.UMLData.Helper.Method;
import activeUML.UMLData.Helper.ModifierContainer;
import activeUML.util.AttributedStringBuilder;

abstract public class Component implements IComponentSetter, IAssosiationMapable, IAssosiationMapper {

	protected ModifierContainer modifiers;
	protected ComponentName componentName;
	protected ComponentName superComponentName;
	protected List<ComponentName> interfaces;
	protected GenericSigniture generics;
	protected List<Method> constructors;
	protected List<Method> methods;
	protected List<Field> fields;
	protected List<Assosiation> assosiations;
	public List<AttributedStringBuilder[]> umlContents;

	public Component() {
		this.umlContents = new LinkedList<AttributedStringBuilder[]>();
		this.modifiers = new ModifierContainer();
		this.interfaces = new ArrayList<ComponentName>();
		this.generics = new GenericSigniture();
		this.constructors = new ArrayList<Method>();
		this.methods = new ArrayList<Method>();
		this.fields = new ArrayList<Field>();
		this.assosiations = new ArrayList<Assosiation>();
	}

	@Override
	public void setComponentName(ComponentName name) {
		this.componentName = name;
	}

	@Override
	public void setSuperComponentName(ComponentName name) {
		this.superComponentName = name;
	}

	@Override
	public void setModefierInput(Integer modifierNumber) {
		this.modifiers.setModefierInput(modifierNumber);
	}

	@Override
	public void addConstructor(Method constructor) {
		this.constructors.add(constructor);
	}

	@Override
	public void addMethod(Method method) {
		this.methods.add(method);
	}

	@Override
	public void addField(Field field) {
		this.fields.add(field);
	}

	@Override
	public void addInterfaceName(ComponentName interfaceName) {
		this.interfaces.add(interfaceName);
	}

	@Override
	public void setGenericInputString(String inputString) {
		this.generics.setGenericInputString(inputString);
	}

	public List<AttributedStringBuilder[]> getUmlContent() {
		return this.umlContents;
	}

	public Character getRepresetivChar() {
		return null;
	}

	public void buildUMLContent() {

	}

	@Override
	public List<Assosiation> getAssosiationMappperContent() {

		return this.assosiations;
	}

	@Override
	public String getAssosiationMapableContent() {
		return this.componentName.getName();
	}

	protected void addAssosiations() {
		for (Field field : this.fields) {
			if (field.isInterface()) {
				this.addAssoisiation(AssosiationType.INTERFACE_DECLARATION, field.getComponentName().getName());
			} else {
				this.addAssoisiation(AssosiationType.DIREKT, field.getComponentName().getName());
			}
		}
	}

	protected void addAssoisiation(AssosiationType type, String value) {
		Assosiation buildAssosiation = new Assosiation();
		buildAssosiation.setType(type);
		buildAssosiation.setComponentString(value);
		buildAssosiation.setSource(this.getAssosiationMapableContent());
		this.assosiations.add(buildAssosiation);
	}
}
