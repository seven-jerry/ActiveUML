package activeUML.Renderer.Position;

import activeUML.Renderer.DrawBox;

public interface IPositionStore extends IUserChangedPosition{
	public void setup();
	public void changeToStoredPosition(DrawBox sourceBox, String key );
}
