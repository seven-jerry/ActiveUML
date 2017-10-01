package activeUML.Renderer.Position;

import java.util.List;

import activeUML.Renderer.UMLDrawableComponent;

public class DefaultPositionManager implements IPositionManager{

	// arranges the views in a row
	@Override
	public void arrangePosition(List<UMLDrawableComponent> elements) {
		int runningX = 100;
		int runningY = 100;
		for(UMLDrawableComponent element : elements){
			element.setOrgin(runningX, runningY);
			runningX = element.getX() + element.getWidth() + 30;
		}		
	}
}
