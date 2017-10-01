package activeUML.Renderer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import activeUML.Renderer.Assosiation.DrawableAssosiation;
import activeUML.Renderer.Position.IUserChangedPosition;

public class CanvasPanel extends JPanel
		implements MouseMotionListener, MouseListener, MouseWheelListener, AdjustmentListener {
	private static final long serialVersionUID = 5079910335903223899L;
	private List<UMLDrawableComponent> components;
	private List<DrawableAssosiation> assosiations;
	private UMLDrawableComponent dragComponent;
	private int dragXPos, dragYPos;
	private double scale;
	private JScrollPane scrollPane;
	private IUserChangedPosition userPositionReciever;
	private Point mousePoint;
	private Rectangle rect;

	CanvasPanel() {
		this.scale = 0.1;
		this.components = new ArrayList<UMLDrawableComponent>();
		this.assosiations = new ArrayList<DrawableAssosiation>();
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);
		this.addMouseListener(this);
	}

	public void setScrollPane(JScrollPane scrollpane2) {
		this.scrollPane = scrollpane2;
		this.scrollPane.getHorizontalScrollBar().addAdjustmentListener(this);
		this.scrollPane.getVerticalScrollBar().addAdjustmentListener(this);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1000000, 1000000);
	}

	public void addComponent(UMLDrawableComponent umlComponent) {
		this.components.add(umlComponent);
	}

	public void addAssosiation(DrawableAssosiation assosiation) {
		this.assosiations.add(assosiation);
	}

	public void setUserPositionReciever(IUserChangedPosition positionReciever) {
		this.userPositionReciever = positionReciever;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g.create();
		AffineTransform at = new AffineTransform();
		at.scale(this.scale, this.scale);
		at.translate(this.getConvertedX(-this.scrollPane.getHorizontalScrollBar().getValue()),
				this.getConvertedY(-this.scrollPane.getVerticalScrollBar().getValue()));
		g2d.setTransform(at);

		for (UMLDrawableComponent component : this.components) {
			component.paint(g2d);
		}
		for (DrawableAssosiation assosiation : this.assosiations) {
			assosiation.paint(g2d);
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {

		if (this.dragComponent == null) {
			this.dragComponent = this.getUMLComponent(this.getConvertedX(e.getX()), getConvertedY(e.getY()));
		}
		if (this.dragComponent == null) {
			return;
		}
		if (this.dragXPos == 0) {
			this.dragXPos = this.getConvertedX(e.getX());
			this.dragYPos = this.getConvertedY(e.getY());
		}
		this.dragComponent.addOrgin(this.getConvertedX(e.getX()) - this.dragXPos,
				getConvertedY(e.getY()) - this.dragYPos);
		this.dragXPos = this.getConvertedX(e.getX());
		this.dragYPos = this.getConvertedY(e.getY());
		this.repaint();
	}

	private int getConvertedX(int input) {
		return new Double((input
				+ this.scrollPane.getHorizontalScrollBar().getValue() / this.getWidth() / this.scrollPane.getWidth())
				/ this.scale).intValue();

	}

	private int getConvertedY(int input) {
		return new Double((input
				+ this.scrollPane.getVerticalScrollBar().getValue() / this.getHeight() / this.scrollPane.getHeight())
				/ this.scale).intValue();
	}

	private UMLDrawableComponent getUMLComponent(int x, int y) {
		for (UMLDrawableComponent component : this.components) {
			if (component.containsOrgin(x, y) == true) {
				return component;
			}
		}
		return null;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.dragXPos = 0;
		this.dragYPos = 0;
		this.dragComponent = null;
		this.mousePoint = e.getPoint();
		//int x = this.mousePoint.x + this.scrollPane.getVerticalScrollBar().getValue();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.isAltDown()) {
			double oldScale = this.scale;
			this.scale += e.getWheelRotation() / 10.0;

			if (this.scale < 0.1) {
				this.scale = 0.1;
			}
			if (this.scale > 3.0) {
				this.scale = 3.0;
			}
			double diff = this.scale / oldScale;
			if (diff == 0) {
				return;
			}
			JScrollBar xBar = this.scrollPane.getHorizontalScrollBar();
			int xBarValue = xBar.getValue();

			JScrollBar yBar = this.scrollPane.getVerticalScrollBar();
			int yBarValue = yBar.getValue();

			int totalMouseX = xBarValue + this.mousePoint.x;
			int totalMouseY = yBarValue + this.mousePoint.y;
		
			Double scaledMouseX = totalMouseX * diff;
			Double scaledMouseY = totalMouseY * diff;
			
			this.rect = new Rectangle(10, 10);
			this.rect.setLocation(scaledMouseX.intValue(), scaledMouseY.intValue());

			scaledMouseX = scaledMouseX - this.mousePoint.x;
			scaledMouseY = scaledMouseY - this.mousePoint.y;

			xBar.setValue(scaledMouseX.intValue());
			yBar.setValue(scaledMouseY.intValue());
			this.repaint();
			return;
		}
		if (e.isShiftDown()) {
			JScrollBar bar = this.scrollPane.getHorizontalScrollBar();
			int currentValue = bar.getValue();
			Float moveFactor = new Float(e.getWheelRotation() * this.scale * 50 / this.scale);
			bar.setValue(currentValue + moveFactor.intValue());
			return;
		}
		JScrollBar bar = this.scrollPane.getVerticalScrollBar();
		int currentValue = bar.getValue();
		Float moveFactor = new Float(e.getWheelRotation() * this.scale * 50 / this.scale);
		bar.setValue(currentValue + moveFactor.intValue());

	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (this.dragComponent != null) {
			this.userPositionReciever.setPosition(this.dragComponent.getComponentBox(),
					this.dragComponent.getAssosiationMapableContent());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
