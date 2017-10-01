package activeUML.FileLoad;

import java.util.ArrayList;

public interface IFileLoader {
	public void fetchClassFileNames();
	public void fetchSourceFileNames();
	
	
	public ArrayList<String> getClassFileNames();
	public ArrayList<String> getSourceFileNames();
	public ArrayList<String> getPackageEncodedClasses();
}
