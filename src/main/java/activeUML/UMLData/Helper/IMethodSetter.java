package activeUML.UMLData.Helper;

import javassist.CtClass;

public interface IMethodSetter extends IModifierSetter,IGenericSignitureSetter{
	public void setReturnTypeString(String inputString);
	public void setMethodName(String name);
	public void setParameters(CtClass[] parameter);
}
