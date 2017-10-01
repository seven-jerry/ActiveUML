package activeUML.UMLData.Helper;

public interface IObjectType extends IObjectTypeSetter {
	public String getClassName();
	public String getShortClassName();
	public String getInputString();
	public int getArrayDimention();
	public boolean isObject();
}
