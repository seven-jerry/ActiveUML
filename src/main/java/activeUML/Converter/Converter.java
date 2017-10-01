package activeUML.Converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import activeUML.Renderer.DrawBox;
import activeUML.Renderer.UMLDrawableComponent;
import activeUML.UMLData.Wrapper.Component;
import activeUML.util.AttributedStringBuilder;

public class Converter {
	private List<Component> input;
	private List<UMLDrawableComponent> output;
	private List<Assosiation> mapperContent;
	private List<Assosiation> assosiations;
	private HashSet<String> mapableContent;
	
	public Converter(){
		this.input = new ArrayList<Component>();
		this.output = new ArrayList<UMLDrawableComponent>();
		this.assosiations = new ArrayList<Assosiation>();
	}
	public void setInput(List<Component> input){
		this.input = input;
	}
	public List<UMLDrawableComponent> getOutput(){
		return this.output;
	}
	public List<Assosiation> getAssosiations(){
		return this.assosiations;
	}
	
	public void convert(){
		Iterator<Component> it = this.input.iterator();
		while(it.hasNext()){
			Component everyComponent = it.next();
			UMLDrawableComponent drawableContent = new UMLDrawableComponent();
			drawableContent.setRepresetivChar(everyComponent.getRepresetivChar());
			drawableContent.setAssosiationMapableContent(everyComponent.getAssosiationMapableContent());
			Iterator<AttributedStringBuilder[]>componentIt = everyComponent.getUmlContent().iterator();
			while(componentIt.hasNext()){
				drawableContent.addBox(new DrawBox(componentIt.next()));
			}
			this.output.add(drawableContent);
		}
		this.buildMapableContent();
		this.buildMappers();
		this.buildAssosiations();
	}
	
	private void buildMappers(){
		this.mapperContent = new ArrayList<Assosiation>();
		Iterator<Component> it = this.input.iterator();
		while(it.hasNext()){
			Component everyComponent = it.next();
			this.mapperContent.addAll(everyComponent.getAssosiationMappperContent());
		}
	}
	
	private void buildMapableContent(){
		this.mapableContent = new HashSet<String>();
		
		Iterator<Component> it = this.input.iterator();
		while(it.hasNext()){
			Component everyComponent = it.next();
			this.mapableContent.add(everyComponent.getAssosiationMapableContent());
		}
	}
	
	private void buildAssosiations(){
		Objects.requireNonNull(this.mapperContent);
		Objects.requireNonNull(this.mapableContent);
		
		for(Assosiation mapper : this.mapperContent){
			if(this.mapableContent.contains(mapper.getComponentString()) == true ){
				this.assosiations.add(mapper);
			}
		}
		
	}
	
}
