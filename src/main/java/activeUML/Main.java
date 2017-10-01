package activeUML;

import activeUML.ClassLoader.JBClassLoader;
import activeUML.FileLoad.FileSystemLoader;
import activeUML.FileLoad.IPathFileLoader;
import activeUML.Renderer.Position.DefaultPositionManager;
import activeUML.Renderer.Position.XMLPositionStore;
/*
 * Entry Point for the uml
 * must be called first 
 * any classes that are loaded before cannot be drawn
 * */
public class Main {

	public static void main(String[] args) {
		IPathFileLoader loader = new FileSystemLoader();
		loader.setClassPath("target/classes/");
		loader.setSourcePath("src/main/java/");
		
		JBClassLoader classLoader = JBClassLoader.getInstance();
		ActiveUML uml = ActiveUML.getInstance();
		uml.setFileLoader(loader);
		uml.setClassLoader(classLoader);
		uml.setPositionManager(new DefaultPositionManager());
		uml.setPositionStore(new XMLPositionStore());
		uml.execute();
		
		// the actual main
		test.lifeTest.Main.main(args);

	}

}
