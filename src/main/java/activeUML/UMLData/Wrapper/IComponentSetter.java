package activeUML.UMLData.Wrapper;

import activeUML.UMLData.Helper.ComponentName;
import activeUML.UMLData.Helper.IGenericSignitureSetter;
import activeUML.UMLData.Helper.IModifierSetter;
import activeUML.UMLData.Helper.Method;

public interface IComponentSetter extends IModifierSetter, IGenericSignitureSetter {
	public void setComponentName(ComponentName name);

	public void setSuperComponentName(ComponentName name);

	public void addInterfaceName(ComponentName interfaceName);

	public void addConstructor(Method constructor);

	public void addMethod(Method method);

	public void addField(Field field);
}
