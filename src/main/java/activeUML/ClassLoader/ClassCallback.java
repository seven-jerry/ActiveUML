package activeUML.ClassLoader;

public class ClassCallback {
	private static ClassCallback instance;
	private ClassCallback(){
		
	}
	public static ClassCallback getInstance(){
		if (ClassCallback.instance == null) {
			ClassCallback.instance = new ClassCallback();
	    }
	    return ClassCallback.instance;
	}
	public void methodCallback(String methodName, Object... args){
		System.out.println(methodName + args);
	}
	public void constructorCallback(String methodName, Object... args){
		System.out.println(methodName + args);

	}
}
