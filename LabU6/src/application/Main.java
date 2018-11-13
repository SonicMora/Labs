package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import modelo.Evento;

public class Main extends Application {
	
	private static Evento evento;
	
	public Main() {
		evento=new Evento();
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			TabPane root = (TabPane)FXMLLoader.load(getClass().getResource("EventoVideojuegos.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Evento getEvento() {
		return evento;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
