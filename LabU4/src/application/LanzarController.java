package application;

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

public class LanzarController {

	public static final int POS_X_BANDERA=80;
	public static final int POS_I_POKEMON=500;
			
	@FXML private ImageView cancha;
	@FXML private ImageView banner;
	@FXML private ImageView flag;
	
	@FXML private Label labPuntos;
	
	@FXML private Button butGuardar;
	@FXML private Button butPuntos;
	@FXML private Button butNombres;
	@FXML private Button butBuscar;
	
	@FXML private ImageView pokeActual;
	
	@FXML private Timeline animacion;
	
	public LanzarController() {
		
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
		
		pokeActual.setImage(new Image(Main.darGimnasio().getPokeBall().getImagen()));
		pokeActual.setX(Main.darGimnasio().getPokeBall().getPokemon().getPosX());
		pokeActual.setY(Main.darGimnasio().getPokeBall().getPokemon().getPosY());
		
		pokeActual.setOnMouseClicked(e->{
			aparecerPokemon(posiblesPokemones());
		});		
		
		banner.setImage(new Image("data/Pokemon_Banner.jpg"));
		banner.setFitWidth(640);
		
		cancha.setImage(new Image("data/cancha.jpg"));
		imageView2();
	}
	
	public void accionarLabel() {
		Timeline accionLab=new Timeline(new KeyFrame(Duration.millis(15), e->{
			labPuntos.setText(Integer.toString(Gimnasio.getActual().getPuntos()));
		}));
		accionLab.setCycleCount(Timeline.INDEFINITE);
		accionLab.play();
	}
	
	public void imageView2(){
		flag.setImage(new Image("data/bandera.png"));
		flag.setFitWidth(50);
		flag.setFitWidth(50);
		flag.setX(80);
		flag.setY(210);
	}
	
	public void queComienceLaFiesta() {
		animacion=new Timeline(new KeyFrame(Duration.millis(30), e->{
			Main.darGimnasio().getPokeBall().getPokemon().avanzar(5);
			pokeActual.setX(Main.darGimnasio().getPokeBall().getPokemon().getPosX());
			reiniciar();
		}));
		
		animacion.setCycleCount(Timeline.INDEFINITE);
		animacion.play();
	}
	
	public void reiniciar() {
		if(pokeActual.getX()<POS_X_BANDERA-50) {
			Main.darGimnasio().getPokeBall().getPokemon().setPosX(500);
			Gimnasio.getActual().setPuntos(Gimnasio.getActual().getPuntos()+10);
		}
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
			pokeActual.setOnMouseClicked(e->{
			});
			return wea;
		} else if (result.get() == buttonTypeJolteon) {
			Main.darGimnasio().getPokeBall().getPokemon().setRuta("/data/weird.gif");
			wea=Main.darGimnasio().getPokeBall().getPokemon().darRuta();
			pokeActual.setOnMouseClicked(e->{
			});
		    return wea;
		}else if(result.get() == buttonTypeSorpresa) {
			Main.darGimnasio().getPokeBall().getPokemon().setRuta("/data/pato.gif");
			wea=Main.darGimnasio().getPokeBall().getPokemon().darRuta();
			pokeActual.setOnMouseClicked(e->{
			});
		    return wea;
		}
		return null;
	}
	
	public void aparecerPokemon(String ruta) {
		pokeActual.setImage(new Image(ruta));
		pokeActual.setFitWidth(150);
		pokeActual.setFitWidth(150);
		pokeActual.setX(Main.darGimnasio().getPokeBall().getPokemon().getPosX());
		pokeActual.setY(Main.darGimnasio().getPokeBall().getPokemon().getPosY());
		
		queComienceLaFiesta();
	}
	
}
