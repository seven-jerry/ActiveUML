package activeUML.UMLData.Helper;


import activeUML.util.AttributedStringBuilder;

public class MethodParameter implements  IMethodParameterSetter{
	//modifers
	private ModifierContainer modifiers;
	//Name
	private ComponentName parameterName;
	
	public MethodParameter(){
			this.modifiers = new ModifierContainer();
	}
	
	@Override
	public void setModefierInput(Integer modifierNumber) {
		this.modifiers.setModefierInput(modifierNumber);
		
	}
	@Override
	public void setParameterName(ComponentName name) {
		this.parameterName = name;	
	}
	public boolean isInterface(){
		return this.modifiers.isInterface();
	}
	public ComponentName getComponentName(){
		return this.parameterName;
	}
	public AttributedStringBuilder toUML(){
		AttributedStringBuilder buildString = new AttributedStringBuilder();
		if(this.modifiers.getFinal() != null){
			buildString.append(this.modifiers.getFinal().toUML());
		}
		buildString.append(this.parameterName.toUML());
		return buildString;
	}

}
