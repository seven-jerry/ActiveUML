package activeUML.FileLoad;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class FileSystemLoader implements IPathFileLoader {
	private String classPath;
	private String sourcePath;
	private ArrayList<String> classFilePaths;
	private ArrayList<String> sourceFilePaths;
	private ArrayList<String> classPackageNames;
	private String exclude = "activeUML";

	public FileSystemLoader(){
		this.classFilePaths = new ArrayList<String>();
		this.sourceFilePaths = new ArrayList<String>();
		this.classPackageNames = new ArrayList<String>();
	}
	public void setClassPath(String path) {
		this.classPath = path;
	}
	public void setSourcePath(String path) {
		this.sourcePath = path;
	}
	public void fetchClassFileNames() {


		this.fillClassArrayForPath(classPath);
		this.removeClassPath(classFilePaths);
		this.removeExclude(classFilePaths);
		this.fillPackageClassNames();
	}
	public void fetchSourceFileNames() {
		this.fillSourceArrayForPath(sourcePath);
		this.removeSourcePath(sourceFilePaths);
		this.removeExclude(sourceFilePaths);
	}



	private void fillClassArrayForPath(final String path){

		File folder = new File(path);
		for (File file : folder.listFiles()) {
		    if (file.isFile() && file.getName().contains(".class")) {
		    	classFilePaths.add(file.getPath());
		    } else if (file.isDirectory()) {
		        this.fillClassArrayForPath(path +"/"+file.getName());
		    }
		}
	}

	private void fillSourceArrayForPath(final String path){
		File folder = new File(path);
		for (File file : folder.listFiles()) {
		    if (file.isFile() && file.getName().contains(".java")) {
		    	this.sourceFilePaths.add(file.getPath());
		    } else if (file.isDirectory()) {
		        this.fillSourceArrayForPath(path +"/"+file.getName());
		    }
		}
	}

	private void removeClassPath(ArrayList<String> entries){
		for(String entry : entries){
			entry = entry.replace(this.classPath, "");
		}
		for (int index =0; index < entries.size(); index++){
			String entry = entries.get(index).replace(this.classPath, "");
			entries.set(index, entry);
		}
	}
	private void removeSourcePath(ArrayList<String> entries){
		for (int index =0; index < entries.size(); index++){
			String entry = entries.get(index).replace(this.sourcePath, "");
			entries.set(index, entry);
		}
	}
	private void removeExclude(ArrayList<String> entries){
		Iterator<String> i = entries.iterator();
		while(i.hasNext()){
			String entry = i.next();
			if(entry.contains(this.exclude)){
				i.remove();
			}
		}
	}
	private void fillPackageClassNames(){
		for(String entry : this.classFilePaths){
			String packageString = entry.replaceAll("/", ".");
			packageString = packageString.replace(".class", "");
			this.classPackageNames.add(packageString);
		}
	}


	public ArrayList<String> getClassFileNames() {
		return this.classFilePaths;
	}
	public ArrayList<String> getSourceFileNames() {
		return this.sourceFilePaths;
	}

	public ArrayList<String> getPackageEncodedClasses(){
		return this.classPackageNames;
	}


}
