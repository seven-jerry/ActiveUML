package activeUML.ClassLoader;

import java.util.ArrayList;
import java.util.List;

import activeUML.UMLData.Helper.ComponentName;
import activeUML.UMLData.Helper.Method;
import activeUML.UMLData.Wrapper.Class;
import activeUML.UMLData.Wrapper.Component;
import activeUML.UMLData.Wrapper.Enum;
import activeUML.UMLData.Wrapper.Field;
import activeUML.UMLData.Wrapper.Interface;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

public class JBClassLoader {
	private static JBClassLoader instance;
	private List<Component> components;

	
	private JBClassLoader(){
		this.components = new ArrayList<Component>();
	}
	public static JBClassLoader getInstance () {
		if (JBClassLoader.instance == null) {
	    	JBClassLoader.instance = new JBClassLoader();
	    }
	    return JBClassLoader.instance;
	}

	public List<Component> getUMLComponents(){
		return this.components;
	}

	public void rewrite(String packageClass)  {
		try {
			 ClassPool pool = ClassPool.getDefault();
			  CtClass clazz = pool.get(packageClass);
			  Component umlComponent = null;
			  
			  if(clazz.isInterface()){
				  umlComponent = new Interface();
			  }
			  else if(clazz.isEnum()){
				  umlComponent = new Enum();
			  } 
			  else{
				  umlComponent = new Class();
			  }
			  umlComponent.setComponentName(new ComponentName(clazz.getName()));
			  umlComponent.setSuperComponentName(new ComponentName(clazz.getSuperclass().getName()));
			  umlComponent.setModefierInput(clazz.getModifiers());
			  umlComponent.setGenericInputString(clazz.getGenericSignature());
			  this.addInterfaces(umlComponent, clazz);
			  this.addMethods(umlComponent, clazz);
			  this.addConstructors(umlComponent, clazz);
			  this.addFields(umlComponent, clazz);
			  umlComponent.buildUMLContent();
			  this.components.add(umlComponent);
			  this.addCallbackHook(clazz);
			 clazz.toClass();
		}
		catch (NotFoundException e){
			e.printStackTrace();
		}
		catch(CannotCompileException e) {
			e.printStackTrace();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private void addMethods(Component umlComponent,CtClass clazz) throws NotFoundException{		
		 CtMethod[] methods = clazz.getDeclaredMethods();
		 
		 for(CtMethod method: methods){
			  Method umlMethod = new Method();
			  umlMethod.setModefierInput(method.getModifiers());
			  umlMethod.setGenericInputString(method.getGenericSignature());
			  umlMethod.setReturnTypeString(method.getReturnType().getName());
			  umlMethod.setMethodName(method.getName());
			  umlMethod.setParameters(method.getParameterTypes());
			  umlComponent.addMethod(umlMethod);
		  }
	}
	
	private void addConstructors(Component umlComponent,CtClass clazz) throws NotFoundException{
		  CtConstructor[] cons = clazz.getConstructors();
		  
		  for(CtConstructor con: cons){
			  Method umlMethod = new Method();
			  umlMethod.setModefierInput(con.getModifiers());
			  umlMethod.setGenericInputString(con.getGenericSignature());
			  umlMethod.setReturnTypeString("");
			  umlMethod.setMethodName(con.getName());
			  umlMethod.setParameters(con.getParameterTypes());
			  umlComponent.addConstructor(umlMethod);
		  }
	}	  
	
	private void addFields(Component umlComponent,CtClass clazz) throws NotFoundException{
		  CtField[] fields = clazz.getDeclaredFields();
		  
		  for(CtField field : fields){
			  Field umlField = new Field();
			  umlField.setModefierInput(field.getModifiers());
			  umlField.setObjectTypeString(field.getSignature());
			  umlField.setComponentName(new ComponentName(field.getName()));
			  umlComponent.addField(umlField);
		  }
	}
	
	
	private void addInterfaces(Component umlComponent,CtClass clazz) throws NotFoundException{
		  CtClass[] interfaces = clazz.getInterfaces();
		  
		  for(CtClass iFace: interfaces){			  
			  umlComponent.addInterfaceName(new ComponentName(iFace.getName()));
		  }
	}	
	
	
	public void addCallbackHook(CtClass clazz) throws CannotCompileException{
		 CtMethod[] methods = clazz.getDeclaredMethods();
		 CtConstructor[] cons = clazz.getConstructors();
		 
		 for(CtMethod method: methods){
			  String s = method.getName();
			  s = String.format("activeUML.ClassLoader.ClassCallback.getInstance().methodCallback(\"%s\",$args );",s);
			  method.insertBefore(s);
		  }
		 for(CtConstructor con: cons){
			  String s = con.getName();
			  s = String.format("activeUML.ClassLoader.ClassCallback.getInstance().constructorCallback(\"%s\",$args );",s);
			  con.insertAfter(s);
		  }
	}
	
}
