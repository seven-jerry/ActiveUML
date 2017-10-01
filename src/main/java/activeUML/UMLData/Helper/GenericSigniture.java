package activeUML.UMLData.Helper;

import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import activeUML.util.AttributedStringBuilder;

public class GenericSigniture implements IGenericSignitureSetter {
	private String inputString;
	private List<GenericLetter> generics;
	public GenericSigniture(){
		this.generics = new ArrayList<GenericLetter>();
	}
	@Override
	public void setGenericInputString(String inputString) {
		if(inputString == null){
			return;
		}
		this.inputString = inputString;		
		this.proccessInputString();
	}
	private void proccessInputString(){
		int startIndex = this.inputString.indexOf("<");
		this.inputString = this.inputString.substring(startIndex);
		String proccessString =  this.inputString.substring(1);
		while(proccessString.indexOf(":") > -1){
			int pointIndex = this.inputString.indexOf(":");
			int semiIndex = this.inputString.indexOf(";");
			
			String letter = proccessString.substring(0, pointIndex-1);
			String objectType = proccessString.substring(pointIndex, semiIndex);
			proccessString = proccessString.substring(semiIndex);
			GenericLetter buildLetter = new GenericLetter();
			buildLetter.setLetter(letter);
			buildLetter.setObjectTypeString(objectType);
			this.generics.add(buildLetter);
		}
	}
	public AttributedStringBuilder toUML(){
		AttributedStringBuilder umlString = new AttributedStringBuilder();
		if(this.generics.size() == 0){
			return null;
		}
		umlString.append(new AttributedString("<"),"");
		Iterator<GenericLetter> it = this.generics.iterator();
		while(it.hasNext()){
			GenericLetter letter = it.next();
			umlString.append(letter.toUML(),"");
			if(it.hasNext()){
				umlString.append(new AttributedString(","),"");
			}
		}
		umlString.append(new AttributedString(">"),"");
		return umlString;
	}
	
	
}
