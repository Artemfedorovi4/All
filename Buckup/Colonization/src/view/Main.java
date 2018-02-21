package view;
	
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;



public class Main extends Application {
	
	int height = 550;
	int width = 900;
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			primaryStage.setTitle("Colonization");

			Image image = new Image(getClass().getResourceAsStream("hex.jpg"));
			ImageView img = new ImageView(image);
			img.setFitHeight(height+10);
			img.setFitWidth(width+10);
			root.getChildren().add(img);
			
			MenuItem game = new MenuItem("Играть");
			//MenuItem options = new MenuItem("Настройки");
			MenuItem exitGame = new MenuItem("Выход");
			
			SubMenu mainMenu = new SubMenu(game/*,options*/,exitGame);
			
			MenuItem PlvsPl = new MenuItem("Ч. vs Ч.");
			MenuItem PlvsCm = new MenuItem("Ч. vs К.");
			MenuItem backMenu = new MenuItem("НАЗАД");
			
			SubMenu newGameMenu = new SubMenu(PlvsPl,PlvsCm,backMenu);
			
			
			BoxMenu boxMenu = new BoxMenu(mainMenu);
			Map title = new Map();
			
			game.setOnMouseClicked(event->{
				boxMenu.setSubMenu(newGameMenu);
			});
			backMenu.setOnMouseClicked(event->{
				boxMenu.setSubMenu(mainMenu);
			});
			exitGame.setOnMouseClicked(event->{
				System.exit(0);
			});
			
			PlvsPl.setOnMouseClicked(event->{
				boxMenu.setVisible(false);
				title.setVisible(true);
			});
			
			root.getChildren().addAll(title,boxMenu);
			
			Scene scene = new Scene(root,width,height);
			scene.setOnKeyPressed(event->{
				if(event.getCode()==KeyCode.ESCAPE){
					FadeTransition ft = new FadeTransition(Duration.seconds(1),boxMenu);
					if(!boxMenu.isVisible()){
						ft.setFromValue(0);
						ft.setToValue(1);
						ft.play();
						boxMenu.setVisible(true);
						title.setVisible(false);
					}
					else{
						ft.setFromValue(1);
						ft.setToValue(0);
						ft.setOnFinished(evt-> boxMenu.setVisible(false));
						ft.play();
						title.setVisible(true);
					}
				}
			});
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
