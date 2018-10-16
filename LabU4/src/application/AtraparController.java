package application;

import java.security.SecureRandom;
import java.util.Optional;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.util.Duration;
import modelo.Gimnasio;

public class AtraparController {
	
	public static final int POS_X_BANDERA=80;
	
	@FXML private ImageView cancha;
	@FXML private ImageView banner;
	@FXML private ImageView flag;
	@FXML private ImageView pokemon;
	
	@FXML private Label labPuntos;
	
	@FXML private Button butGuardar;
	@FXML private Button butPuntos;
	@FXML private Button butNombres;
	@FXML private Button butBuscar;

	private int vel;
	
	public AtraparController () {
	}
	
	public void initialize() {
		
		accionarLabel();
		
		labPuntos.setFont(new Font("ARCADECLASSIC", 30));
		
		butBuscar.setOnMouseClicked(e->{
			buscar();
		});
		
		butGuardar.setOnMouseClicked(e->{
			Main.guardar();
		});
		
		butNombres.setOnAction(e->{
			Main.darJugadores(1);
		});
		
		butPuntos.setOnAction(e->{
			Main.darJugadores(-1);
		});
		
		SecureRandom random=new SecureRandom();

		vel=20+random.nextInt(70);
		
		banner.setImage(new Image("data/Pokemon_Banner.jpg"));
		banner.setFitWidth(640); 
		
		cancha.setImage(new Image("data/cancha.jpg"));		
		cancha.setOnMouseClicked(e->{
			if(Main.atraparPokemon((int)e.getSceneX(), (int)e.getSceneY())==true){
				reiniciar();
			}else {
				
			}
		});
		imageView2();
	}
	
	public void accionarLabel() {
		Timeline accionLab=new Timeline(new KeyFrame(Duration.millis(15), e->{
			labPuntos.setText(Integer.toString(Gimnasio.getActual().getPuntos()));
		}));
		accionLab.setCycleCount(Timeline.INDEFINITE);
		accionLab.play();
	}
	
	public void reiniciar(){
		Main.darGimnasio().getPokeBall().getPokemon().setPosX(500);
	}
	
	public void buscar() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog with Custom Actions");
		alert.setHeaderText("¿Que desea buscar?");
		alert.setContentText("Choose your option.");
		
		ButtonType buttonTypeUsuario = new ButtonType("Jugador");
		ButtonType buttonTypePokemon = new ButtonType("Pokemon");
		
		alert.getButtonTypes().setAll(buttonTypeUsuario, buttonTypePokemon);
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get()==buttonTypeUsuario) {
			Main.buscar(1);
		}else if(result.get()==buttonTypePokemon){
			Main.buscar(-1);
		}
	}
	
	public String posiblesPokemones() {		
		String wea="";
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog with Custom Actions");
		alert.setHeaderText("Seleccione un pokemon");
		alert.setContentText("Choose your option.");

		ButtonType buttonTypeCharizard = new ButtonType("Charizard");
		ButtonType buttonTypeJolteon = new ButtonType("Jolteon");
		ButtonType buttonTypeSorpresa =new ButtonType("Sorpresa");

		alert.getButtonTypes().setAll(buttonTypeCharizard, buttonTypeJolteon, buttonTypeSorpresa);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeCharizard){
			Main.darGimnasio().getPokeBall().getPokemon().setRuta("/data/charizard.gif");
			wea=Main.darGimnasio().getPokeBall().getPokemon().darRuta();
			pokemon.setOnMouseClicked(e->{
			});
			return wea;
		} else if (result.get() == buttonTypeJolteon) {
			Main.darGimnasio().getPokeBall().getPokemon().setRuta("/data/weird.gif");
			wea=Main.darGimnasio().getPokeBall().getPokemon().darRuta();
			pokemon.setOnMouseClicked(e->{
			});
		    return wea;
		}else if(result.get() == buttonTypeSorpresa) {
			Main.darGimnasio().getPokeBall().getPokemon().setRuta("/data/pato.gif");
			wea=Main.darGimnasio().getPokeBall().getPokemon().darRuta();
			pokemon.setOnMouseClicked(e->{
			});
		    return wea;
		}
		return null;
	}
	
	public void imageView2(){
		flag.setImage(new Image("data/bandera.png"));
		flag.setFitWidth(50);
		flag.setFitWidth(50);
		flag.setX(80);
		flag.setY(210);
		imageView3();
	}
	
	public void imageView3() {
		pokemon.setImage(new Image(posiblesPokemones()));
		pokemon.setX(Main.darGimnasio().getPokeBall().getPokemon().getPosX());
		pokemon.setY(Main.darGimnasio().getPokeBall().getPokemon().getPosY());
		pokemon.setFitWidth(100);
		pokemon.setFitWidth(100);
		
		Timeline animacion=new Timeline(new KeyFrame(Duration.millis(vel), e->{
			Main.darGimnasio().getPokeBall().getPokemon().avanzar(5);
			
			pokemon.setX(Main.darGimnasio().getPokeBall().getPokemon().getPosX());
		}));
		
		animacion.setCycleCount(Timeline.INDEFINITE);
		animacion.play();
		
		pokemon.setOnMouseClicked(e->{
			if(Main.atraparPokemon((int)e.getSceneX(), (int)e.getSceneY())==true) {
				reiniciar();
			}
		});
	}

}
