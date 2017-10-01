package activeUML.UMLData.Helper;

import java.util.EnumSet;
import java.util.Iterator;

public class ObjectLetterContainer implements IObjectLetterSetter {
	ObjectLetter validLetter;
	public ObjectLetterContainer(){
		
	}
	public boolean hasValidLetter(){
		return this.validLetter != null;
	}
	@Override
	public void setObjectLetterForChar(char inputChar){
		final EnumSet<ObjectLetter> letters = EnumSet.allOf(ObjectLetter.class);
		Iterator<ObjectLetter> it = letters.iterator();
		while(it.hasNext()){
			ObjectLetter everyLetter = it.next();
			if(everyLetter.getLetter() == inputChar){
				this.validLetter = everyLetter;
			}
		}
	}
	
}
