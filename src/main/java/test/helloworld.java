package test;

import activeUML.UMLData.Helper.IObjectLetterSetter;

public class helloworld<T,K,V>{
	public String a;
	public static String a1;
	public final String a2 = "";
	public static final String a3 = "";
	
	public helloworld(){
		
	}
	public <A,B,C> void  printHello(){
		System.out.println("HelloWorld.printHello");
	}
	public final void printHelloWith(String string){
		System.out.println("HelloWorld.printHelloWith " + string);
	}
	public void somethig(IObjectLetterSetter d){
		
	}
	public void setObjectLetterForChar(char inputChar) {
		// TODO Auto-generated method stub		
	}
	
}
