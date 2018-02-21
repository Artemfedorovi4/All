package application;

import application.Main.Person;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;

public class Main extends Application  {
	
	public void attent(String error){
		Alert dil = new Alert(AlertType.INFORMATION);
		dil.setTitle("Info");
		dil.setHeaderText(error);
		dil.showAndWait();
	}
	private TableView<Person> table = new TableView<Person>();
    private final ObservableList<Person> data =
            FXCollections.observableArrayList( 
            		new Person("Emma", "Jones"),
                    new Person("Michael", "Brown"));
	
	@Override
	public void start(Stage primaryStage) {
    	OwnClass scene = new OwnClass();
    	primaryStage.setScene(scene.getScene());
    	
		Task task = new Task<Void>() {
		    @Override public Void call()throws Exception {
		    	System.out.println("Bliat");
		    	 Platform.runLater(new Runnable() {
	                 @Override public void run() {
	                	 primaryStage.show();
	                     // etc
	                 }
	             });
		    	
				return null;
		    }
		};
		
		Thread th = new Thread(task);
    	th.setPriority(Thread.NORM_PRIORITY);
		
		
		
		
		Task task2 = new Task<Void>() {
		    @Override public Void call()throws Exception {
		    	System.out.println("Robit");
				String str = "Hello";
				int size = 300;
				String str2="";
				for(int i =0; i<size/5.1;i++) str=str+" "; 
				
				while(true)
				{
					if(str.length()>=size/5.1)
					{
						for(int i=0; i<size/5.1;i++){
						str2=str.substring((int)(size/5.1-1),(int)(size/5.1));
						str = str2 + str.substring(0,(int)(size/5.1));
						
						System.out.println(str);
						try {
					primaryStage.setTitle(str);
							Thread.sleep(50);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							System.out.println(e);
							}
						}
					}
					else{
						
						System.out.println(str);
						try {
							primaryStage.setTitle(str);
							Thread.sleep(80);
						} catch (InterruptedException e) {
							System.out.println(e);
						}
					}
				}
				//return null;
		    }
		};
		Thread th2 = new Thread(task2);
    	th2.setPriority(Thread.MAX_PRIORITY);
    	th.start();
		th2.start();
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public class OwnClass{
		Scene scene;
		public Scene getScene(){
			return scene;
		};
		public OwnClass(){
			String error = "Неверный ввод";
			int xSize = 765;
			int ySize = 400;
			GridPane root = new GridPane();// создание сетки GridPane
			root.setPadding(new Insets(10,10,10,10));
			root.setHgap(5);
			root.setVgap(5);
			
			//mSecondThread = new Boom("Nakanecta",xSize,primaryStage);
			
			ToggleGroup group = new ToggleGroup();//настройка группы RadioButton
			// Часть 1
			
			Label lb1 = new Label("Path 1"); 
			GridPane.setConstraints(lb1,0,0);
			root. getChildren().add(lb1);
			
			TextField fir = new TextField();
			GridPane.setConstraints(fir,0,1);
			root.getChildren().add(fir);
			
			Button button = new Button("Push to combobox");
			GridPane.setConstraints(button,1,1);
			root. getChildren().add(button);
			
			ComboBox comboBox = new ComboBox();
			GridPane.setConstraints(comboBox,2,1);
			root.getChildren().add(comboBox);
			
			ObservableList items = comboBox.getItems();
			
			button.setOnAction(event->{
				if(!fir.getText().isEmpty()){
					if(comboBox.getItems().isEmpty()){
						comboBox.getItems().add(fir.getText());
					}else{
					for(int i =0; i<items.size();i++){
						if(!fir.getText().toString().equals(items.get(i).toString())){
							comboBox.getItems().add(fir.getText());
							break;
						} else{
							attent(error);
							break;
						}
					}
				}}else{
					attent(error);
				}
				
			});
			
			// Часть 2
			Label ph1 = new Label("Path 2");
			GridPane.setConstraints(ph1,0,2);
			root.getChildren().add(ph1);
			
			TextField name = new TextField();//создание окна с возможностью ввода
			GridPane.setConstraints(name,0,3);
			root.getChildren().add(name);

			Button btn1 = new Button("click 1");
			GridPane.setConstraints(btn1,1,3);
			root.getChildren().add(btn1);
			
			Button btn2 = new Button("click 2");
			GridPane.setConstraints(btn2,2,3);
			root.getChildren().add(btn2);
			
			//обработкак событий
			btn1.setOnAction(event->{
				btn1.setText(name.getText());
			});
			
			Label test = new Label();//создание временного хранилища
			
			btn2.setOnAction(event->{
				test.setText(btn2.getText());
				btn2.setText(btn1.getText());
				btn1.setText(test.getText());
			});
			// Часть 3
			Label ph2 = new Label("Path 3");
			GridPane.setConstraints(ph2,0,5);
			root.getChildren().add(ph2);
			
			TextField name2 = new TextField();
			GridPane.setConstraints(name2,0,6);
			name2.setPromptText("Введите название");
			root.getChildren().add(name2);
			
			
			
			Button but = new Button("Push!");
			GridPane.setConstraints(but,0,7);
			root.getChildren().add(but);
			
			RadioButton rb1 = new RadioButton("1");
			GridPane.setConstraints(rb1,1,6);
			root.getChildren().add(rb1);
			
			RadioButton rb2 = new RadioButton("2");
			GridPane.setConstraints(rb2,1,7);
			root.getChildren().add(rb2);
			
			RadioButton rb3 = new RadioButton("3");
			GridPane.setConstraints(rb3,1,8);
			root.getChildren().add(rb3);
			
			rb1.setToggleGroup(group);
			rb2.setToggleGroup(group);
			rb3.setToggleGroup(group);
			
			rb1.setUserData("1");
			rb2.setUserData("2");
			rb3.setUserData("3");
			
			but.setOnAction(event->{
				if(name2.getText().equals(rb1.getUserData())){
					rb1.setSelected(true);;
				} else if(name2.getText().equals(rb2.getUserData())){
					rb2.setSelected(true);
				}else if(name2.getText().equals(rb3.getUserData())){
					rb3.setSelected(true);
					System.out.println("year");
				}else {
					attent(error);
				}
			});
			
			Label label = new Label("Path 4");
			GridPane.setConstraints(label,4,0);
			root.getChildren().add(label);
			
			TextField textline = new TextField();
			GridPane.setConstraints(textline,4,1);
			root.getChildren().add(textline);
			
			Button but2 = new Button("Push");
			GridPane.setConstraints(but2,4,2);
			root.getChildren().add(but2);
			
			CheckBox[] checkBox ={
				new CheckBox("1"),
				new CheckBox("2"),
				new CheckBox("3")
			};
			
			String[] dataStr ={
				new String("1"),
				new String("2"),
				new String("3")
			};
			
			for(int i = 0; i<3;i++){
				checkBox[i].setUserData(dataStr[i]);
				GridPane.setConstraints(checkBox[i],5,i+1);
				root.getChildren().add(checkBox[i]);
			}
			
			but2.setOnAction(event->{
				if(!textline.getText().isEmpty()){
					for(int i =0; i<3;i++){
						boolean norm = checkBox[i].isSelected();
						if(textline.getText().equals(checkBox[i].getUserData())){
							checkBox[i].setSelected(!norm);
						}
					}
				}else{
					attent(error);
				}
			});
			
			table.setEditable(true);
	        
	        TableColumn firstNameCol = new TableColumn("First Name");
	        firstNameCol.setMinWidth(100);
	        firstNameCol.setCellValueFactory(
	                new PropertyValueFactory<Person, String>("firstName"));

	        TableColumn lastNameCol = new TableColumn("Last Name");
	        lastNameCol.setMinWidth(100);
	        lastNameCol.setCellValueFactory(
	                new PropertyValueFactory<Person, String>("lastName"));

	        table.setItems(data);
	        table.getColumns().addAll(firstNameCol, lastNameCol);
	 
	        final TextField addFColumn = new TextField();
	        addFColumn.setPromptText("First Name");
	        addFColumn.setMaxWidth(firstNameCol.getPrefWidth());
	        GridPane.setConstraints(addFColumn,8,9);

	        final Button addButton = new Button("Add");
	        addButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	                data.add(new Person(
	                		addFColumn.getText()));
	                addFColumn.clear();
	            }
	        });
	        
	        final Button moveNext = new Button("MoveNext");
	        moveNext.setOnAction(e-> {
	        	table.getSelectionModel().selectLast();
	            	if(!table.getSelectionModel().getSelectedItem().getFirstName().isEmpty()){
	            	String info = table.getSelectionModel().getSelectedItem().getFirstName();
	            	data.add(new Person(null,info));
	            	table.getItems().remove(table.getSelectionModel().getSelectedIndex());}
	        });
	        
	        final Button movePrev = new Button("MovePrev");
	        movePrev.setOnAction(e-> {
	        	table.getSelectionModel().selectLast();
	        	if(!table.getSelectionModel().getSelectedItem().getLastName().isEmpty()){
	        	String info = table.getSelectionModel().getSelectedItem().getLastName();
	        	data.add(new Person(info,null));
	        	table.getItems().remove(table.getSelectionModel().getSelectedIndex());}
	        });
	        
	        //GridPane.setConstraints(runningText,10,6);
	        GridPane.setConstraints(addButton,8,6);
	        GridPane.setConstraints(moveNext,8,7);
	        GridPane.setConstraints(movePrev,8,8);
			root.getChildren().addAll(table,addFColumn, addButton, moveNext, movePrev);
			
			GridPane.setConstraints(table,10,10);
			scene = new Scene(root, xSize, ySize);
		}
	}
	
	public static class Person {
		 
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
 

        
        private Person(String fName, String lName) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
        }
        private Person(String fName) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty("");
        }
 
        public String getFirstName() {
            return firstName.get();
        }
 
        public void setFirstName(String fName) {
            firstName.set(fName);
        }
 
        public String getLastName() {
            return lastName.get();
        }
 
        public void setLastName(String fName) {
            lastName.set(fName);
        }
    }
}