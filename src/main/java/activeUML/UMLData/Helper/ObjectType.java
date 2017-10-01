package activeUML.UMLData.Helper;

import java.awt.font.TextAttribute;
import java.text.AttributedString;

public class ObjectType implements IObjectType {
	private String inputString;
	private String className;
	private ArrayDimention arrayDimentions;
	private ObjectLetterContainer letters;
	
	public ObjectType(){
		this.inputString = "";
		this.className = "void";
		this.arrayDimentions = new ArrayDimention();
		this.letters = new ObjectLetterContainer();
	}
	
	@Override
	public void setObjectTypeString(String inputString) {
		if(inputString == null){
			return;
		}
		this.inputString = inputString;
		this.proccessInputString();
	}
	@Override
	public String getClassName() {
		return this.className;
	}

	@Override
	public String getShortClassName() {
		int pointIndex = this.className.lastIndexOf(".");
		String shortClassName = this.className.substring(pointIndex+1);
		return shortClassName;
	}

	@Override
	public String getInputString() {
		return this.inputString;
	}
	
	public boolean isVoid(){
		return this.inputString == "void";
	}
	
	@Override
	public int getArrayDimention() {
		return this.arrayDimentions.arrayDimention();
	}
	@Override
	public boolean isObject(){
		return this.getShortClassName().equals("Object");
		
	}
	
	private void proccessInputString(){
		if(this.inputString == null){
			return;
		}
		if(this.inputString == "void"){
			return;
		}

		System.out.println(this.inputString);
		char[] charArray = this.inputString.toCharArray();
		int positionIndex;
		
		for(positionIndex = 0;positionIndex<charArray.length;positionIndex++){
			char everyChar = charArray[positionIndex];
			this.arrayDimentions.addArrayDimention(everyChar);
			this.letters.setObjectLetterForChar(everyChar);
			if(this.letters.hasValidLetter()){
				break;
			}
		}
		if(this.letters.hasValidLetter() == false){
			this.className = this.inputString;
			return;
		}
		this.className = this.inputString.substring(positionIndex+1);
		this.className = this.className.replace(";","");
		this.className = this.className.replaceAll("/",".");
	}

	public AttributedString toUML(){
		String umlString = this.getShortClassName() + this.arrayDimentions.toUML();
		AttributedString buildString = new AttributedString(umlString);
		if(umlString.length() == 0){
			return buildString;
		}
		buildString.addAttribute(TextAttribute.WEIGHT,TextAttribute.WEIGHT_SEMIBOLD,0,umlString.length());
		return buildString;
	}
}
