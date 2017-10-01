package activeUML;


import java.util.ArrayList;
import java.util.List;

import activeUML.ClassLoader.JBClassLoader;
import activeUML.Converter.Assosiation;
import activeUML.Converter.Converter;
import activeUML.FileLoad.IFileLoader;
import activeUML.Renderer.Context;
import activeUML.Renderer.UMLDrawableComponent;
import activeUML.Renderer.Position.IPositionManager;
import activeUML.Renderer.Position.IPositionStore;
import activeUML.UMLData.Wrapper.Component;
/*
 * Fassade object for ActiveUML
 *	1. get the class files 
 *	2. convert them to data-objects
 *	3. convert the data-objects to drawable objects
 *	4. display them to the gui
 * */
public class ActiveUML {
	//singleton
	private static ActiveUML instance;
	IFileLoader fileLoader;
	JBClassLoader classLoader;
	Context context;
	Converter converter;
	IPositionManager positionManager;
	IPositionStore positionStore;
	private ActiveUML(){
		this.context = Context.getInstance();
	}
	public static ActiveUML getInstance () {
		if (ActiveUML.instance == null) {
			ActiveUML.instance = new ActiveUML();
	    }
	    return ActiveUML.instance;
	}
	public void setFileLoader(IFileLoader loader){
		this.fileLoader = loader;
	}
	public void setClassLoader(JBClassLoader loader){
		this.classLoader = loader;
	}
	public void setPositionManager(IPositionManager manager){
		this.positionManager = manager;
	}
	public void setPositionStore(IPositionStore positionStore){
		this.positionStore = positionStore;
	}
	public void execute(){
		fileLoader.fetchClassFileNames();
		fileLoader.fetchSourceFileNames();
		ArrayList<String> packageClasses = fileLoader.getPackageEncodedClasses();
	
		for(String packageClass : packageClasses){
			this.classLoader.rewrite(packageClass);
		}
		List<UMLDrawableComponent> drawableContet = this.convert();
		List<Assosiation> componentAssertions = this.converter.getAssosiations();
		this.draw(drawableContet,componentAssertions);
	}
	public List<UMLDrawableComponent> convert(){
		List<Component> components = this.classLoader.getUMLComponents();
		this.converter = new Converter();
		this.converter.setInput(components);
		this.converter.convert();
		return this.converter.getOutput();
	}
	public void draw(List<UMLDrawableComponent> drawableContet,List<Assosiation> componentAssertions){
		this.positionManager.arrangePosition(drawableContet);
		
		this.positionStore.setup();
		for(UMLDrawableComponent element : drawableContet){
			this.positionStore.changeToStoredPosition(element.getComponentBox(), element.getAssosiationMapableContent());
			element.buildPosition();
		}
		this.context.getCanavsPanel().setUserPositionReciever(this.positionStore);
		this.context.draw(drawableContet,componentAssertions);
	}
}
