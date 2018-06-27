package activeUML.Renderer.Assosiation;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

import activeUML.Converter.AssociationType;
import activeUML.Renderer.IDrawableAssosiationPoint;

public abstract class DrawableAssosiation {
	protected IDrawableAssosiationPoint startPoint;
	protected IDrawableAssosiationPoint endPoint;

	public static DrawableAssosiation fromType(AssociationType type) {
		switch (type) {
		case DIRECT:
			return new DirektAssosiation();
		case INTERFACE_IMPLEMENTATION:
			return new InterfaceImplementaionAssosiation();
		case INTERFACE_DECLARATION:
			return new InterfaceDeclarationAssosiation();
		case INHERITANCE:
			return new InheritanceAssosiation();
		default:
			return null;
		}

	}

	public DrawableAssosiation() {

	}

	public IDrawableAssosiationPoint getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(IDrawableAssosiationPoint startPoint) {
		this.startPoint = startPoint;
	}

	public IDrawableAssosiationPoint getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(IDrawableAssosiationPoint endPoint) {
		this.endPoint = endPoint;
	}

	protected void drawLineEnding(Graphics2D g) {

	}

	public void paint(Graphics2D g) {
		g.setStroke(new BasicStroke(3));

		g.drawLine(this.startPoint.getX() + this.startPoint.getWidth(),
				this.startPoint.getY() + this.startPoint.getHeight() / 2, this.endPoint.getX(),
				this.endPoint.getY() + this.endPoint.getHeight() / 2);
	}

}
