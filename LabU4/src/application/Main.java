package application;
	
import java.util.ArrayList;
import java.util.Optional;

import excepciones.PokemonNoEncontrado;
import excepciones.UsuarioNoEncontrado;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modelo.Gimnasio;
import modelo.Jugador;

public class Main extends Application {
	
	private static Gimnasio gimnasio;

	public Main() { 
		aDarle();
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource(opciones()));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene); 
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void guardar() {
		darGimnasio().guardar();
	}
	
	public void aDarle() {
		gimnasio=new Gimnasio();
		empezarJuego();
	}
	
	public void empezarJuego() {
//		try {
			String aja=iniciar();
			darGimnasio().aja(aja);
//		} catch (NombreRepetidoException e) {
//			Alert excepcion=new Alert(AlertType.CONFIRMATION);
//			excepcion.setHeaderText(null);
//			excepcion.setTitle("Jugadores");
//			excepcion.setContentText(e.getMessage());
//		}
	}
	
	public String iniciar() {		
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Registrar usuario");
		dialog.setHeaderText(null);
		dialog.setContentText("Escribe tu nombre:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			return result.get();
		}else {
			return null;
		}
	}
	
	public String opciones() {		
		String wea="";
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog with Custom Actions");
		alert.setHeaderText("Seleccione un tipo de entrenmiento");
		alert.setContentText("Choose your option.");

		ButtonType buttonTypeLanzar = new ButtonType("Lanzar");
		ButtonType buttonTypeAtrapar = new ButtonType("Atrapar");

		alert.getButtonTypes().setAll(buttonTypeLanzar, buttonTypeAtrapar);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeLanzar){
			wea="/application/Lanzar.fxml";
			return wea;
		} else if (result.get() == buttonTypeAtrapar) {
		    wea="/application/Atrapar.fxml";
		    return wea;
		}else {
			System.exit(0);
			return null;
		}
	}
	
	public static Gimnasio darGimnasio() {
		return gimnasio;	
	}

	public static boolean atraparPokemon(int x, int y) {
		if(darGimnasio().getPokeBall().getPokemon().atrapado(x, y)==true) {
			Gimnasio.getActual().setPuntos(Gimnasio.getActual().getPuntos()+10);
			System.out.println(Gimnasio.getActual().getPuntos());
			return true;
		}else {
			Gimnasio.getActual().setPuntos(Gimnasio.getActual().getPuntos()-10);
			return false;
		}
	}
	
	public static void darJugadores(int x) {
		Alert jugadores=new Alert(AlertType.CONFIRMATION);
		jugadores.setHeaderText(null);
		jugadores.setTitle("Jugadores");
		if(x>0) {
			jugadores.setContentText(acomodarWeasN());
		}else {
			jugadores.setContentText(acomodarWeasP());
		}
		jugadores.show();
	}
	
	public static String acomodarWeasN() {
		ArrayList<Jugador> arreglo=darGimnasio().ordenNombresJ(darGimnasio().getJugadores());
		String end="";
		for(int m=0;m<arreglo.size();m++) {
			int j=m+1;
			end+=""+j+")"+arreglo.get(m);
			end+="\n";
		}
		return end;
	}
	
	public static String acomodarWeasP() {
		ArrayList<Jugador> arreglo=darGimnasio().ordenPuntos(darGimnasio().getJugadores());
		String end="";
		for(int m=0;m<arreglo.size();m++) {
			int j=m+1;
			end+=""+j+")"+arreglo.get(m);
			end+="\n";
		}
		return end;
	}
	
	public static void buscar(int x) {
		TextInputDialog cuadro=new TextInputDialog("");
		cuadro.setTitle("Buscar");
		cuadro.setHeaderText(null);
		cuadro.setContentText("Ingrese el nombre a buscar");
		Optional<String> result = cuadro.showAndWait();
		if(x<0) {
//			Pokemon
			try {
				mostrarBuscado(darGimnasio().busquedaBinariaP(result.get()));
			} catch (PokemonNoEncontrado e) {
				mostrarBuscado(e.getMessage());
			}
		}else if(x>0) {
//			Jugador
			try {
				mostrarBuscado(darGimnasio().busquedaBinariaJ(result.get(), darGimnasio().getJugadores()));
			} catch (UsuarioNoEncontrado e) {
				mostrarBuscado(e.getMessage());
			}
		}
	}
	
	public static void mostrarBuscado(String buscado) {
		Alert resultado=new Alert(AlertType.CONFIRMATION);
		resultado.setTitle("Resultado");
		resultado.setHeaderText(null);
		resultado.setContentText(buscado);
		
		resultado.showAndWait();
	}
	
	static void main(String[] args) {
		launch(args);
	}
	
	
}
