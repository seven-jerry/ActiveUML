package activeUML.Renderer.Assosiation;

import java.awt.Color;
import java.awt.Graphics2D;

public class InterfaceDeclarationAssosiation extends DrawableAssosiation {
	public void paint(Graphics2D g) {
		g.setColor(Color.blue);
		super.paint(g);
		g.setColor(Color.black);
	}
}
