package activeUML;

import activeUML.ClassLoader.JBClassLoader;
import activeUML.FileLoad.FileSystemLoader;
import activeUML.FileLoad.IPathFileLoader;
import activeUML.Renderer.Position.DefaultPositionManager;
import activeUML.Renderer.Position.XMLPositionStore;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import sun.management.FileSystem;

import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
 * Entry Point for the uml
 * must be called first 
 * any classes that are loaded before cannot be drawn
 * */
public class Main {

	public static void main(String[] args) throws Exception {
		IPathFileLoader loader = new FileSystemLoader();
		loader.setClassPath("target/outs/");
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
