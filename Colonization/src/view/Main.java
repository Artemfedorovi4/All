package view;
	
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;



public class Main extends Application {
	
	private int height = 550;
	private int width = 900;
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
			MenuItem rules = new MenuItem("Правила");
			MenuItem exitGame = new MenuItem("Выход");
			
			SubMenu mainMenu = new SubMenu(game,rules,exitGame);
			
			BoxMenu boxMenu = new BoxMenu(mainMenu);
			Map map = new Map();
			RulesMenu rul = new RulesMenu(boxMenu,height,width);
			
			rules.setOnMouseClicked(e->{
				boxMenu.setVisible(false);
				rul.setVisible(true);
			});
			
			exitGame.setOnMouseClicked(event->{
				System.exit(0);
			});
			
			game.setOnMouseClicked(event->{
				boxMenu.setVisible(false);
				map.setVisible(true);
				map.loadTitle();
			});
			
			root.getChildren().addAll(map,boxMenu,rul);
			
			Scene scene = new Scene(root,width,height);
			scene.setOnKeyPressed(event->{
				if(event.getCode()==KeyCode.ESCAPE){
					FadeTransition ft = new FadeTransition(Duration.seconds(1),boxMenu);
					if(!boxMenu.isVisible()){
						ft.setFromValue(0);
						ft.setToValue(1);
						ft.play();
						boxMenu.setVisible(true);
						map.setVisible(false);
					}
					else{
						ft.setFromValue(1);
						ft.setToValue(0);
						ft.setOnFinished(evt-> boxMenu.setVisible(false));
						ft.play();
						map.setVisible(true);
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
