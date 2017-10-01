package activeUML.UMLData.Helper;

import java.awt.font.TextAttribute;
import java.text.AttributedString;

import activeUML.Renderer.RenderConstants;

public enum Modifier {
	PUBLIC(1,"public"),
	PRIVATE(2,"private"),
	PROTECTED(4,"protected"),
	STATIC(8,"static"),
	FINAL(10,"final"),
	INTERFACE(200,"inteface"),
	ABSTRACT(400,"abstract"),
	ANNOTATION(2000,"annotation"),
	ENUM(4000,"enum");
	
	
	private int  flagNumber;
	private String name;
	private Modifier(int flagNumber,String name){
		this.flagNumber = flagNumber;
		this.name = name;
	}
	public int getFlagNumber(){
		return this.flagNumber;
	}
	public String getName(){
		return this.name;
	}
	public AttributedString toUML(){
		AttributedString buildString = new AttributedString(this.getName());
		buildString.addAttribute(TextAttribute.FOREGROUND,RenderConstants.MODIFIER_COLOR,0,this.getName().length());
		buildString.addAttribute(TextAttribute.SIZE,12,0,this.getName().length());
		buildString.addAttribute(TextAttribute.WEIGHT,TextAttribute.WEIGHT_SEMIBOLD,0,this.getName().length());

		return buildString;
	}
}
