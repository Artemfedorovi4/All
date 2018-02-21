package view;

import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MenuItem extends StackPane{
	public MenuItem(String name){
		Rectangle bg = new Rectangle(300,50,Color.WHITE);
		bg.setOpacity(0.5);
		
		Text text = new Text(name);
		text.setFill(Color.WHITE);
		text.setFont(Font.font("Arial",FontWeight.BOLD,20));
		setAlignment(Pos.CENTER);
		getChildren().addAll(bg,text);
		FillTransition st = new FillTransition(Duration.seconds(0.8),bg);
		
		setOnMouseEntered(event->{
			st.setToValue(Color.LIGHTSALMON);
			st.setFromValue(Color.DARKGREY);
			st.setCycleCount(Animation.INDEFINITE);
			st.play();
		});
		
		setOnMouseExited(event->{
			st.stop();
			bg.setFill(Color.WHITE);
		});;
	}
}

