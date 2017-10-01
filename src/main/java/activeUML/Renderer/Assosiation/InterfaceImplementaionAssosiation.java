package activeUML.Renderer.Assosiation;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class InterfaceImplementaionAssosiation extends DrawableAssosiation{
	@Override
	public void paint(Graphics2D g) {
		g.setStroke(new BasicStroke(3));
		g.setColor(Color.green);
		g.drawLine(this.startPoint.getX()+this.startPoint.getWidth()/2, this.startPoint.getY(),this.endPoint.getX()+this.endPoint.getWidth()/2, this.endPoint.getY()+this.endPoint.getHeight());
		g.setColor(Color.black);

	}
}
