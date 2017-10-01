package activeUML.Renderer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import activeUML.Converter.IAssosiationMapable;
import activeUML.util.AttributedStringBuilder;

public class UMLDrawableComponent implements IAssosiationMapable, IDrawableAssosiationPoint {

	private Character represetivChar;
	private DrawBox represetivCharBox;
	private List<DrawBox> boxes;
	private DrawBox componentBox;
	private String assosiation;

	public UMLDrawableComponent() {
		this.componentBox = new DrawBox(0, 0, 300, 1);
		this.boxes = new ArrayList<DrawBox>();
	}

	public void setRepresetivChar(Character representivChar) {
		if (representivChar == null) {
			return;
		}
		this.represetivChar = representivChar;
		this.represetivCharBox = new DrawBox(0, 0, 0, 0);
		this.represetivCharBox.setFont(new Font("Verdana", Font.PLAIN, 18));
		this.represetivCharBox.setContent(representivChar);
		this.represetivCharBox.setHeight(30);
		this.represetivCharBox.setWidth(30);
	}

	public void addBox(DrawBox box) {
		if (this.boxes.size() == 0) {
			box.setFont(new Font("Verdana", Font.BOLD, 16));
		}
		this.boxes.add(box);
		this.buildHeight();
		this.buildWidth();
	}

	public boolean containsOrgin(int x, int y) {
		if (x < this.componentBox.getX() || x > this.componentBox.getX() + this.componentBox.getWidth()) {
			return false;
		}
		if (y < this.componentBox.getY() || y > this.componentBox.getY() + this.componentBox.getHeight()) {
			return false;
		}
		return true;
	}

	public int getComponentX() {
		return this.componentBox.getX();
	}

	public DrawBox getComponentBox() {
		return this.componentBox;
	}

	public int getComponentWidth() {
		return this.componentBox.getWidth();
	}

	public void addOrgin(int x, int y) {
		System.out.println(x);

		this.componentBox.addX(x);
		this.componentBox.addY(y);
		if (this.represetivCharBox != null) {
			this.represetivCharBox.addX(x);
			this.represetivCharBox.addY(y);
		}
		for (DrawBox box : this.boxes) {
			box.addX(x);
			box.addY(y);
		}
		System.out.println(this.componentBox.getX());
	}

	public void setOrgin(int x, int y) {
		this.componentBox.setX(x);
		this.componentBox.setY(y);
		this.buildPosition();
	}

	public void setAssosiationMapableContent(String content) {
		this.assosiation = content;
	}

	@Override
	public String getAssosiationMapableContent() {
		return this.assosiation;
	}

	public void buildPosition() {
		if (this.represetivCharBox != null) {
			this.represetivCharBox.setX(this.componentBox.getX());
			this.represetivCharBox.setY(this.componentBox.getY());
		}
		this.buildHeight();
		this.buildWidth();
	}

	private void buildWidth() {
		this.componentBox.setWidth(0);
		for (DrawBox box : this.boxes) {
			if (box.getWidth() > this.componentBox.getWidth()) {
				this.componentBox.setWidth(box.getWidth());
			}
		}
		this.componentBox.setX(this.componentBox.getX() + 20);
		this.componentBox.setWidth(this.componentBox.getWidth() + 20);

		for (DrawBox box : this.boxes) {
			box.setX(this.componentBox.getX());
			box.setWidth(this.componentBox.getWidth());
		}
	}

	private void buildHeight() {
		int runningHeight = 0;
		for (DrawBox box : this.boxes) {
			box.setY(runningHeight + this.componentBox.getY());
			runningHeight += box.getHeight();
			runningHeight += 20;
		}
		this.componentBox.setHeight(runningHeight);
	}

	public void paint(Graphics g) {
		g.setColor(Color.DARK_GRAY);

		if (this.represetivCharBox != null) {
			g.drawRect(this.represetivCharBox.getX() + 20,
					this.represetivCharBox.getY() - this.represetivCharBox.getHeight(),
					this.represetivCharBox.getWidth(), this.represetivCharBox.getHeight());
			g.setFont(this.represetivCharBox.getFont());
			g.drawString(this.represetivChar.toString(), this.represetivCharBox.getX() + 30,
					this.represetivCharBox.getY() - 6);
		}
		/* DEBUG AttributedString atr = new AttributedString(this.componentBox.toString());
		atr.addAttribute(TextAttribute.SIZE, 40, 0, this.componentBox.toString().length());
		g.drawString(atr.getIterator(), this.componentBox.getX(), this.componentBox.getY());*/
		for (DrawBox box : this.boxes) {
			g.setColor(Color.DARK_GRAY);

			g.drawRect(box.getX(), box.getY(), box.getWidth(), box.getHeight() + 20);
			g.setFont(box.getFont());
			int lineY = box.getY() + box.getLineHeight() + 5;

			for (AttributedStringBuilder s : box.getContent()) {
				g.setColor(Color.DARK_GRAY);

				g.drawString(s.getBuilStirng().getIterator(), box.getX() + 10, lineY);
				lineY += box.getLineHeight();
			}
		}
	}

	@Override
	public int getX() {
		return this.componentBox.getX();
	}

	@Override
	public int getY() {
		return this.componentBox.getY();
	}

	@Override
	public int getWidth() {
		return this.componentBox.getWidth();
	}

	@Override
	public int getHeight() {
		return this.componentBox.getHeight();
	}

}
