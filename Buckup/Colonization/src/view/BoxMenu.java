package view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BoxMenu extends Pane{
	static SubMenu subMenu;
	public BoxMenu(SubMenu subMenu){
		BoxMenu.subMenu=subMenu;
		Image opt = new Image(getClass().getResourceAsStream("options.png"));
		ImageView optr = new ImageView(opt);
		optr.setFitHeight(35);
		optr.setFitWidth(35);
		
		Button options = new Button();
		
		options.setGraphic(optr);
		options.setOpacity(1);
		
		options.setTranslateX(800);
		options.setTranslateY(480);
		
		setVisible(true);
		Rectangle rec = new Rectangle(900,600,Color.LIGHTGRAY);
		rec.setOpacity(0.3);
		getChildren().addAll(rec,options,subMenu);
	}
	
	public void setSubMenu(SubMenu subMenu){
		getChildren().remove(BoxMenu.subMenu);
		BoxMenu.subMenu=subMenu;
		getChildren().add(BoxMenu.subMenu);
	}
}
