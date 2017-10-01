package activeUML.UMLData.Helper;

import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import activeUML.Renderer.RenderConstants;
import activeUML.util.AttributedStringBuilder;
import javassist.CtClass;

public class Method implements IMethodSetter{
	//modifers
	private ModifierContainer modifiers;
	//generics
	private GenericSigniture generics;
	//returntype
	private ObjectType returnType;
	//methodname
	private String methodName;
	//parameters
	private List<MethodParameter> parameters;
	
	
	public Method(){
		this.generics = new GenericSigniture();
		this.modifiers = new ModifierContainer();
		this.returnType = new ObjectType();
		this.parameters = new ArrayList<MethodParameter>();
	}
	@Override
	public void setParameters(CtClass[] parameter) {
		if(parameter == null){
			return;
		}
		for(CtClass everyParameter: parameter){
			MethodParameter umlParameter = new MethodParameter();
			umlParameter.setParameterName(new ComponentName(everyParameter.getName()));
			umlParameter.setModefierInput(everyParameter.getModifiers());
			this.parameters.add(umlParameter);
		}
		
	}
	
	public List<MethodParameter> getParameters(){
		return this.parameters;
	}
	
	public AttributedStringBuilder toUML() {
		AttributedStringBuilder umlString = new AttributedStringBuilder();
		umlString.append(this.modifiers.toUML());
		umlString.append(this.generics.toUML());
		umlString.append(this.getReturnType());
		umlString.append(this.getMethodNameUML());
		umlString.append(this.getUMLParameters(),"");
		return umlString;
	}
	private AttributedString getReturnType(){
		String returnString = this.returnType.getShortClassName();
		
		AttributedString returnUML = new AttributedString(returnString);
		returnUML.addAttribute(TextAttribute.SIZE,12,0,returnString.length());
		returnUML.addAttribute(TextAttribute.WEIGHT,TextAttribute.WEIGHT_SEMIBOLD,0,returnString.length());
		if(this.returnType.isVoid()){
			returnUML.addAttribute(TextAttribute.FOREGROUND,RenderConstants.MODIFIER_COLOR,0,returnString.length());
		}
		return returnUML;
	}
	
	private AttributedStringBuilder getMethodNameUML(){
		AttributedStringBuilder parameterUML = new AttributedStringBuilder();

		AttributedString methodUML = new AttributedString(this.methodName);
		methodUML.addAttribute(TextAttribute.SIZE,14,0,this.methodName.length());
		methodUML.addAttribute(TextAttribute.WEIGHT,TextAttribute.WEIGHT_BOLD,0,this.methodName.length());
		parameterUML.append(methodUML);
		return parameterUML;
	}
	
	private AttributedStringBuilder getUMLParameters(){
		AttributedStringBuilder parameterUML = new AttributedStringBuilder();

		if(this.parameters.size() == 0){
			parameterUML.append(new AttributedString("()"),"");
			return parameterUML;
		}
		parameterUML.append(new AttributedString("("),"");
		Iterator<MethodParameter> it = this.parameters.iterator();
		while(it.hasNext()){
			MethodParameter name = it.next();
			parameterUML.append(name.toUML());
			if(it.hasNext()){
				parameterUML.append(new AttributedString(","));
			}
		}
		parameterUML.append(new AttributedString(")"));

		return parameterUML;
	}
	@Override
	public void setModefierInput(Integer modifierNumber) {
		this.modifiers.setModefierInput(modifierNumber);
	}
	@Override
	public void setReturnTypeString(String inputString) {
		this.returnType.setObjectTypeString(inputString);
	}
	
	@Override
	public void setMethodName(String name) {
		this.methodName = name;
	}
	
	@Override
	public void setGenericInputString(String inputString) {
		this.generics.setGenericInputString(inputString);
	}


	
}
