package activeUML.Renderer.Assosiation;

import java.awt.Color;
import java.awt.Graphics2D;

public class DirektAssosiation extends DrawableAssosiation{
	@Override
	public void paint(Graphics2D g) {
		g.setColor(Color.red);
		super.paint(g);
		g.setColor(Color.red);
	}
}
