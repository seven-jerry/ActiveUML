package activeUML.UMLData.Helper;

public enum ObjectLetter {
	ReferenceObject('L',"ReferenceObject"),
	Integer('I',"Integer");
			
			
	private char letter;
	private String description;
	
	private ObjectLetter(char letter,String desciption){
		this.letter = letter;
		this.description = desciption;
	}
	public char getLetter(){
		return this.letter;
	}
	public String getDesciption(){
		return this.description;
	}
}
