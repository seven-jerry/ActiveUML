package activeUML.Renderer;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import activeUML.ClassLoader.JBClassLoader;
import activeUML.Converter.Assosiation;
import activeUML.FileLoad.IFileLoader;
import activeUML.Renderer.Assosiation.DrawableAssosiation;

public class Context {
	private static Context instance;
	IFileLoader fileLoader;
	JBClassLoader classLoader;
	public JFrame window;
	private JScrollPane scrollpane;
	private CanvasPanel canvasPanel;

	private Context() {
		this.window = new JFrame();
		this.canvasPanel = new CanvasPanel();
		this.canvasPanel.setPreferredSize(new Dimension(10000, 10000));
		this.scrollpane = new JScrollPane(this.canvasPanel);
		this.scrollpane.setSize(1000, 1000);

		this.canvasPanel.setScrollPane(this.scrollpane);
		this.window.add(this.scrollpane);
		this.window.pack();
		this.window.setVisible(true);
	}

	public static Context getInstance() {
		if (Context.instance == null) {
			Context.instance = new Context();
		}
		return Context.instance;
	}

	public CanvasPanel getCanavsPanel() {
		return this.canvasPanel;
	}
	public void draw(List<UMLDrawableComponent> content, List<Assosiation> componentAssertions) {
		for (UMLDrawableComponent everyContent : content) {
			this.canvasPanel.addComponent(everyContent);
		}
		for (Assosiation everyAssosiation : componentAssertions) {
			UMLDrawableComponent startComponent = this.getMapableEntry(content, everyAssosiation.getSource());
			UMLDrawableComponent endComponent = this.getMapableEntry(content, everyAssosiation.getComponentString());
			DrawableAssosiation assosiation = DrawableAssosiation.fromType(everyAssosiation.getType());
			assosiation.setStartPoint(startComponent);
			assosiation.setEndPoint(endComponent);
			this.canvasPanel.addAssosiation(assosiation);
		}

		this.window.repaint();
	}

	private UMLDrawableComponent getMapableEntry(List<UMLDrawableComponent> content, String entry) {
		for (UMLDrawableComponent everyContent : content) {
			if (everyContent.getAssosiationMapableContent().equals(entry)) {
				return everyContent;
			}
		}
		return null;
	}

}
