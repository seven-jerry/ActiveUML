package activeUML.UMLData.Helper;

import java.util.ArrayList;
import java.util.List;

import activeUML.util.AttributedStringBuilder;

public class ModifierContainer implements IModifierSetter {
	private int modifierNumber;
	private List<Modifier> modifier;
	
	public ModifierContainer(){
		this.modifier = new ArrayList<Modifier>();
	}
	@Override
	public void setModefierInput(Integer modifierNumber) {
		if(modifierNumber == null){
			return;
		}
		this.modifierNumber = modifierNumber;
		
		this.proccessModifierNumber();
		
	}
	public Modifier getFinal(){
		if( AccessFlag.isFinal(this.modifierNumber)){
			return Modifier.FINAL;
		}
		return null;
	}
	public boolean isInterface(){
		return AccessFlag.isInterface(this.modifierNumber);
	}
	public void proccessModifierNumber(){
		if(AccessFlag.isPublic(this.modifierNumber)){
			this.modifier.add(Modifier.PUBLIC);
		}
		if(AccessFlag.isPrivate(this.modifierNumber)){
			this.modifier.add(Modifier.PRIVATE);
		}
		if(AccessFlag.isProtected(this.modifierNumber)){
			this.modifier.add(Modifier.PROTECTED);
		}
		if(AccessFlag.isStatic(this.modifierNumber)){
			this.modifier.add(Modifier.STATIC);
		}
		if(AccessFlag.isFinal(this.modifierNumber)){
			this.modifier.add(Modifier.FINAL);
		}
	}
	
	public AttributedStringBuilder toUML(){
		AttributedStringBuilder umlString = new AttributedStringBuilder();
		for(Modifier m: this.modifier){
			umlString.append(m.toUML());
		}
		return umlString;
	}
	
}
