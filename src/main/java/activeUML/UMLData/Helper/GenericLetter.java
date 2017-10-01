package activeUML.UMLData.Helper;

import java.text.AttributedString;

import activeUML.util.AttributedStringBuilder;

public class GenericLetter implements IObjectTypeSetter{
	private ObjectType objectType;
	private String letter;
	GenericLetter(){
		this.objectType = new ObjectType();
	}
	public void setLetter(String letter){
		this.letter = letter;
	}
	@Override
	public void setObjectTypeString(String inputString) {
		this.objectType.setObjectTypeString(inputString);
	}
	public AttributedStringBuilder toUML(){
		AttributedStringBuilder buildString = new AttributedStringBuilder();
		buildString.append(new AttributedString(this.letter),"");
		if(this.objectType.isObject() == true){
			return buildString;
		}
		buildString.append(new AttributedString(":"),"");
		buildString.append(this.objectType.toUML(),"");
		return buildString;
	}
}
