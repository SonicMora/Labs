package application;

import java.io.File;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import modelo.Programador;

public class EventoCotroller {

	@FXML private Button butExplorar;
	@FXML private Button butCargar;
	@FXML private Button butBuscarProgramador;
	@FXML private Button butBuscarParticipante;
	@FXML private Button butProgramadores;
	@FXML private Button butParticipantes;
	
	@FXML private TextField txtNombre;
	@FXML private TextField txtApellido;
	@FXML private TextField txtCorreo;
	@FXML private TextField txtGenero;
	
	@FXML private TextField txtRuta;
	@FXML private TextField txtProgramador;
	@FXML private TextField txtParticipante;
	
	@FXML private Label labTiempoProgrmador;
	@FXML private Label labFalloProgramador;
	@FXML private Label labTiempoParticipate;
	@FXML private Label labFalloParticipante;
	@FXML private Label labFalloCarga;
	
	@FXML private ImageView imgAvatar;
	
	@FXML private Canvas pintarEstructuras;
	
	public void EventoController() {
		
	}
	
	public void initialize() {
		butExplorar.setOnAction(e->{
			seleccionarDatos();
		});
		butCargar.setOnAction(e->{
			cargarArchivos();
		});
		butBuscarProgramador.setOnAction(e->{
			buscarProgramador();
		});
		butProgramadores.setOnAction(e->{
			pintarArbol(pintarEstructuras.getGraphicsContext2D());
		});
	}
	
	public void buscarProgramador() {
		long principio=System.currentTimeMillis();
		try {
			ponerAvatar(Main.getEvento().getArbol().buscar(Main.getEvento().getArbol().getRaiz(), Long.parseLong(txtProgramador.getText())).getAvatar());
			txtNombre.setText(Main.getEvento().getArbol().buscar(Main.getEvento().getArbol().getRaiz(), Long.parseLong(txtProgramador.getText())).getNombre());
			txtApellido.setText(Main.getEvento().getArbol().buscar(Main.getEvento().getArbol().getRaiz(), Long.parseLong(txtProgramador.getText())).getApellido());
			txtCorreo.setText(Main.getEvento().getArbol().buscar(Main.getEvento().getArbol().getRaiz(), Long.parseLong(txtProgramador.getText())).getCorreo());
			txtGenero.setText(Main.getEvento().getArbol().buscar(Main.getEvento().getArbol().getRaiz(), Long.parseLong(txtProgramador.getText())).getGenero());
		} catch (Exception e) {
			labFalloProgramador.setText(e.getMessage());
		}
		long fin=System.currentTimeMillis();
		labTiempoProgrmador.setText(Long.toString(fin-principio));
		
	}
	
	public void ponerAvatar(String avatar) {
		imgAvatar.setImage(new Image(avatar));
	}
	
	public void seleccionarDatos() {
		Stage stage=new Stage();
		FileChooser ajam=new FileChooser();
		ajam.setTitle("Seleccione los datos que desea cargar");
		ajam.setInitialDirectory(new File("src/datos"));
		File file=ajam.showOpenDialog(stage);
		txtRuta.setText(file.getAbsolutePath());
	}
	
	public void cargarArchivos() {
		try {
			Main.getEvento().cargarDatos(new File(txtRuta.getText()));
			labFalloCarga.setText("Los datos se cargaron correctamente");
		} catch (Exception e1) {
			e1.printStackTrace();
			labFalloCarga.setText("Ha ocurrido un error al cargar los datos");
		}
	}
	
	public void pintarArbol(GraphicsContext g) {
		ArrayList<Programador> rutas=Main.getEvento().programadores();
		for(Programador epa : rutas) {
			ImageView imag=new ImageView(new Image(epa.getAvatar()));
			g.drawImage(imag.getImage(), epa.getPosX(), epa.getPosY(), 30, 30);
			pintarLineas(g);
		}
	}
	
	public void pintarLineas(GraphicsContext g) {
		ArrayList<Programador> rutas=Main.getEvento().programadores();
		for (int i = 0; i < rutas.size()-1; i++) {
			g.strokeLine(rutas.get(i).getPosX()+30,rutas.get(i).getPosY()+40, rutas.get(i+1).getPosX(), rutas.get(i+1).getPosY());
		}
	}
	
	
	
	
	
}
