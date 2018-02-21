package application;

import javax.xml.parsers.ParserConfigurationException;

import controller.GeneratePerson;
import controller.OpenMenu;
import controller.SaveMenu;
import javafx.application.Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Person;
import model.SaxParser;
import model.DataStore;
import model.PagedTable;
import view.AddDialog;
import view.DelDialog;
import view.FindDialog;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {

	public void AlertMessage(String message) {
		Alert alert = new Alert(AlertType.ERROR, message);
		alert.showAndWait();
	}

	DataStore data = new DataStore();
	VBox box = new VBox();
	
	public void start(Stage primaryStage) {
		try {
			OpenMenu openMenu = new OpenMenu();
			data.setAllData(openMenu.getHiddenData());
			
			FxApp app = new FxApp(primaryStage);

			primaryStage.setScene(app.getAppScene());
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public class FxApp {
		Scene scene;

		public FxApp(Stage stage) {
			VBox root = new VBox();

			scene = new Scene(root, 580, 450);

			PagedTable page = new PagedTable();
			box = page.pageOut(data);
			
			MenuBar menuBar = new MenuBar();

			Menu file = new Menu("File");
			Label labla = new Label();
			updateLabel(labla);
			MenuItem save = new MenuItem("Save");
			save.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent t) {
					try {
						SaveMenu.saveDoc(stage, data);
					} catch (ParserConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			MenuItem open = new MenuItem("Open");
			open.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent t) {
					OpenMenu openMenu = new OpenMenu(stage);

					ObservableList<Person> temp = openMenu.getData();
					data.getDataList().clear();
					data.setAllData(temp);
					updateLabel(labla);
					// updateTable();
				}
			});
			MenuItem close = new MenuItem("Close");
			close.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent t) {
					stage.close();
				}
			});

			Menu func = new Menu("Function");

			MenuItem add = new MenuItem("Add");
			add.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent t) {
					AddDialog.AddWindow(data);
					updateLabel(labla);
					root.getChildren().remove(box);
					box = page.pageOut(data);
					root.getChildren().add(box);
				}
			});

			MenuItem find = new MenuItem("Find");
			find.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent t) {
					FindDialog.AddWindow(data);
				}
			});

			MenuItem del = new MenuItem("Delete");
			del.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent t) {
					DelDialog.AddWindow(data);
					updateLabel(labla);
					root.getChildren().remove(box);
					box = page.pageOut(data);
					root.getChildren().add(box);
				}
			});

			file.getItems().addAll(save, open, close);
			func.getItems().addAll(add, find, del);

			menuBar.getMenus().addAll(file, func);

			root.getChildren().add(menuBar);
			
			root.getChildren().add(labla);
			root.getChildren().add(box);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		}

		public void updateLabel(Label label) {
			label.setText("Количество записей " + data.getColPers());
		}

		public Scene getAppScene() {
			return scene;
		}
	}

}
