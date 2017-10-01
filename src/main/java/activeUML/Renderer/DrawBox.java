package activeUML.Renderer;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import activeUML.util.AttributedStringBuilder;

public class DrawBox {
	private int boxWidth;
	private int boxHeight;
	private int lineHeight;
	private int boxXPos;
	private int boxYPos;
	private Font font;
	private AttributedStringBuilder[] content;

	public DrawBox(AttributedStringBuilder[] stringBuilder) {
		this.boxXPos = 0;
		this.boxYPos = 0;
		this.boxWidth = 0;
		this.boxHeight = 0;
		this.content = stringBuilder;
		this.setFont(new Font("Verdana", Font.PLAIN, 12));
	}

	public DrawBox(int x, int y, int width, int height) {
		this.boxXPos = x;
		this.boxYPos = y;
		this.boxWidth = width;
		this.boxHeight = height;
		this.font = new Font("Verdana", Font.PLAIN, 12);
		this.content = new AttributedStringBuilder[0];
	}

	public void setFont(Font font) {
		this.font = font;
		this.buildWidthForContent();
		this.buildHeightForContent();
	}

	public Font getFont() {
		return this.font;
	}

	public void setContent(AttributedStringBuilder[] content) {
		this.content = content;
		this.setFont(this.font);
	}

	public void setContent(Character charContent) {
		this.content = new AttributedStringBuilder[] { new AttributedStringBuilder(charContent) };
	}

	public AttributedStringBuilder[] getContent() {
		return this.content;
	}

	public void setX(int x) {
		this.boxXPos = x;
	}

	public void addX(int x) {
		this.boxXPos += x;
	}

	public int getX() {
		return this.boxXPos;
	}

	public void setY(int y) {
		this.boxYPos = y;
	}

	public void addY(int y) {
		this.boxYPos += y;
	}

	public int getY() {
		return this.boxYPos;
	}

	public void setWidth(int width) {
		this.boxWidth = width;
	}

	public int getWidth() {
		return this.boxWidth;
	}

	public void setHeight(int height) {
		this.boxHeight = height;
	}

	public int getHeight() {
		return this.boxHeight;
	}

	public int getLineHeight() {
		return this.lineHeight;
	}

	private void buildWidthForContent() {
		this.boxWidth = 0;
		for (AttributedStringBuilder content : this.content) {
			int width = content.toString().length() * RenderConstants.CHARACTERLENGTH;
			if (width > this.boxWidth) {
				this.boxWidth = width;
			}
		}
	}

	private void buildHeightForContent() {
		this.boxHeight = 0;
		for (AttributedStringBuilder content : this.content) {
			int height = (int) font
					.getStringBounds(content.toString(), new FontRenderContext(font.getTransform(), false, false))
					.getBounds().getHeight();
			this.boxHeight += height;
			this.lineHeight = height;
		}
	}

	@Override
	public String toString() {
		return "x: " + this.getX() + " y: " + this.getY() + " width : " + this.getWidth() + " height: "
				+ this.getHeight();
	}
}
