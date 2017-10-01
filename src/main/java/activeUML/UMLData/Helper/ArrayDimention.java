package activeUML.UMLData.Helper;

import java.util.ArrayList;
import java.util.List;

public class ArrayDimention {
	List<Character> dimention;
	char validChar = '[';
	public ArrayDimention(){
		this.dimention = new ArrayList<Character>();
	}
	//if the char is a valid char [ add one dimension
	public void addArrayDimention(char inputChar){
		if(inputChar == this.validChar){
			this.dimention.add(inputChar);
		}
	}
	public int arrayDimention(){
		return this.dimention.size();
	}
	public String toUML(){
		StringBuilder buildString = new StringBuilder();
		for(int i = 1; i <= this.dimention.size(); i++){
			buildString.append("[]");
		}
		return buildString.toString();
	}
}
