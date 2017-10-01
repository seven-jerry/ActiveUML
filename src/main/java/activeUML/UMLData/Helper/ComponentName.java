package activeUML.UMLData.Helper;

import java.awt.font.TextAttribute;
import java.text.AttributedString;

public class ComponentName {
	private String name;
	public ComponentName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public String getShortClassName() {
		int pointIndex = this.name.lastIndexOf(".");
		String shortClassName = this.name.substring(pointIndex+1);
		return shortClassName;
	}
	public AttributedString toUML(){
		return this.toUML(12,TextAttribute.WEIGHT_SEMIBOLD);
	}
	
	public AttributedString toUML(int fontSize,float weightSize){
		AttributedString buildString = new AttributedString(this.getShortClassName());
		buildString.addAttribute(TextAttribute.SIZE,fontSize,0,this.getShortClassName().length());
		buildString.addAttribute(TextAttribute.WEIGHT,weightSize,0,this.getShortClassName().length());
		return buildString; 
		
	}
	public AttributedString toUML(int fontSize){
		return this.toUML(fontSize, TextAttribute.WEIGHT_SEMIBOLD);
	}
	
}
