package view;

import javafx.scene.layout.VBox;

public class SubMenu extends VBox{
	public SubMenu(MenuItem...items){
		setSpacing(15);
		for(MenuItem item: items){
			setTranslateY(190);
			setTranslateX(299);
			getChildren().add(item);
		}
	}
}
