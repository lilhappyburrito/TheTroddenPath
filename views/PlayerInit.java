package views;

import controllers.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PlayerInit {
	
	private static String name;
	
	//ask for number of players
	public static void playerNum(String title, String message) {
		Stage prompt = new Stage();
		
		prompt.initModality(Modality.APPLICATION_MODAL);
		prompt.setTitle(title);
		
		Text label = new Text();
		label.setText(message);
		TextField playerNum = new TextField();
		Button submit = new Button("Submit");
		Text error = new Text();
		error.setText("");
		submit.setOnAction(e -> {
			try {
				int number = Integer.parseInt(playerNum.getText());
				if(number<5 && number>0) {
					Controller.initPlayers(number);
					prompt.close();
				}
				else if(number > 4) {
					error.setText("That is too many players");
				}
				else {
					error.setText("That is too few players");
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Not a number");
				error.setText("Are you sure that's a number?");
			}
		});
		
		VBox layout = new VBox(15);
		layout.getChildren().addAll(label, playerNum, submit, error);
		
		Scene scene = new Scene(layout, 200, 250);
		prompt.setScene(scene);
		prompt.show();
	}
	
	//ask for Player name
	public static void playerName(String title, String message) {
		String name ="";
		Stage prompt = new Stage();
		
		prompt.initModality(Modality.APPLICATION_MODAL);
		prompt.setTitle(title);
		
		Text label = new Text();
		label.setText(message);
		TextField playerName = new TextField();
		Button submit = new Button("Submit");
		
		submit.setOnAction(e -> {
			setName(playerName.getText());
			prompt.close();
		});
		
		VBox layout = new VBox(15);
		layout.getChildren().addAll(label, playerName, submit);
		Scene scene = new Scene(layout, 300, 300);
		prompt.setScene(scene);
		prompt.show();
	}
	
	public static String getName() {
		return name;
	}

	public static void setName(String playerName) {
		name = playerName;
	}
}