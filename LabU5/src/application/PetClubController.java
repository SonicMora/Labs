package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import exceptions.EmptyList;
import exceptions.ExistentOwner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

public class PetClubController {

	@FXML private Button butAddOwner;
	@FXML private Button butDeleteOwner;
	@FXML private Button butFindOwner;
	@FXML private Button butModifyOwner;
	
	@FXML private Button butAddPet;
	@FXML private Button butDletePet;
	@FXML private Button butFindPet;
	@FXML private Button butModifyPet;
	
	@FXML private Button butOwners;
	@FXML private Button butPets;
	@FXML private Button butReOwners;
	@FXML private Button butRePets;
	
	@FXML private TextField txtId;
	@FXML private TextField txtNameOwner;
	@FXML private TextField txtLastName;
	@FXML private TextField txtDOBOwner;
	
	@FXML private TextField txtNamePet;
	@FXML private TextField txtDOBPet;
	
	@FXML private TextField txtFilter;
	
	@FXML private ChoiceBox<String> chooseGender;
	@FXML private ChoiceBox<String> chooseType;
	
	@FXML private ListView<String> listPets_Owners;
	
	public PetClubController() {
		
	}
	
	public void initialize() {
		initializeTypes();
		initializeGenders();
		buttonActions();
	}
	
	public void buttonActions() {
		Alert popUp=new Alert(AlertType.INFORMATION);
		popUp.setTitle("No tan rapido velocista");
		popUp.setHeaderText(null);
		butOwners.setOnAction(e->{
			listOwners(popUp);
		});
		butPets.setOnAction(e->{
			listPets(popUp);
		});
		butAddOwner.setOnAction(e->{
			try {
				Main.getPetClub().add(Integer.parseInt(txtId.getText()), txtNameOwner.getText(), txtLastName.getText(), txtDOBOwner.getText());
			} catch (ExistentOwner e1) {
				popUp.setContentText(e1.getMessage());
				popUp.getButtonTypes().clear();
				popUp.getButtonTypes().add(new ButtonType("Okay"));
				popUp.showAndWait();
			}
		});
		butAddPet.setOnAction(e->{
			try {
				Main.getPetClub().find(Main.getPetClub().getFirstOwners(), txtNameOwner.getText(), txtLastName.getText(), Integer.parseInt(txtId.getText())).add(
						txtNamePet.getText(), txtDOBPet.getText(), chooseGender.getSelectionModel().getSelectedItem(), chooseType.getSelectionModel().getSelectedItem());
			} catch (Exception e1) {
				popUp.setContentText(e1.getMessage());
				popUp.getButtonTypes().clear();
				popUp.getButtonTypes().add(new ButtonType("Okay"));
				popUp.showAndWait();
			}
		});
		butFindOwner.setOnAction(e->{
			popUp.setContentText("Quiere buscar un dueño por su nombre o su cédula");
			ButtonType buttonTypeCedula = new ButtonType("Cédula");
			ButtonType buttonTypeNombre = new ButtonType("Nombre");
			popUp.getButtonTypes().clear();
			popUp.getButtonTypes().addAll(buttonTypeCedula, buttonTypeNombre);
			Optional<ButtonType> result = popUp.showAndWait();
			if (result.get() == buttonTypeCedula){
				find(1);
			} else if (result.get() == buttonTypeNombre) {
				find(-1);
			}
		});
		butDeleteOwner.setOnAction(e->{
			deleteOwner(popUp);
		});
	}
	
	public void deleteOwner(Alert popUp) {
		TextInputDialog popUp2=new TextInputDialog("");
		popUp2.setTitle("No tan rapido velocista");
		popUp2.setContentText("Ingrese la cedula del usuario que desea buscar");
		popUp2.setHeaderText(null);
		Optional<String> result = popUp2.showAndWait();
		if(result.isPresent()) {
			try {
				popUp.setContentText("Se eliminó el usuario deseado");
				Main.getPetClub().delete(Integer.parseInt(result.get()));
				popUp.showAndWait();
			}catch(Exception e) {
				popUp.setContentText(e.getMessage());
				popUp.showAndWait();
			}
		}
	}
	
	public void find(int x) {
		String found="";
		TextInputDialog popUp=new TextInputDialog("");
		popUp.setTitle("No tan rapido velocista");
		popUp.setHeaderText(null);
		Optional<String> result = popUp.showAndWait();
		if(x>0) {
			popUp.setContentText("Ingrese la cedula del usuario que desea buscar");
			if(result.isPresent()) {
				try {
					found=Main.getPetClub().findById(Integer.parseInt(result.get()));
					String[] founded=found.split(" ");
					txtNameOwner.setText(founded[0]);
					txtLastName.setText(founded[1]);
					txtId.setText(founded[2]);
					txtDOBOwner.setText(founded[3]);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}else {
			popUp.setContentText("Ingrese el nombre y el apellido del usuario que desea buscar");
			if(result.isPresent()) {
				try {
					found=Main.getPetClub().findByName(result.get());
					String[] founded=found.split(" ");
					txtNameOwner.setText(founded[0]);
					txtLastName.setText(founded[1]);
					txtId.setText(founded[2]);
					txtDOBOwner.setText(founded[3]);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
	
	public void initializeTypes() {
		List<String> list=new ArrayList<String>();
		list.add("Perro");
		list.add("Gato");
		ObservableList<String> types=FXCollections.observableList(list);
		chooseType.setItems(types);
	}
	
	public void initializeGenders() {
		List<String> list=new ArrayList<String>();
		list.add("Macho");
		list.add("Hembra");
		ObservableList<String> genders=FXCollections.observableList(list);
		chooseGender.setItems(genders);
	}
	
	public void listOwners(Alert popUp) {
		String passed="";
		String result="";
		if(txtFilter.getText().equals("")) {
			passed=null;
		}else {
			passed=txtFilter.getText();
		}
		try {
			result=Main.getPetClub().owners(passed);
		} catch (EmptyList e) {
			popUp.setContentText(e.getMessage());
			popUp.showAndWait();
		}
		String[] owners=result.split("#");
		List<String> list=new ArrayList<String>();
		for(int i=0;i<owners.length;i++) {
			list.add(owners[i]);
		}
		ObservableList<String> ownersList=FXCollections.observableList(list);
		listPets_Owners.setItems(ownersList);
	}
	
	public void listPets(Alert popUp) {
		String passed="";
		String result="";
		if(txtFilter.getText().equals("")) {
			passed=null;
		}else {
			passed=txtFilter.getText();
		}
		try {
			result=Main.getPetClub().listPets(passed);
		} catch (EmptyList e) {
			popUp.setContentText(e.getMessage());
			popUp.showAndWait();
		}
		String[] owners=result.split("#");
		List<String> list=new ArrayList<String>();
		for(int i=0;i<owners.length;i++) {
			list.add(owners[i]);
		}
		ObservableList<String> ownersList=FXCollections.observableList(list);
		listPets_Owners.setItems(ownersList);
	}
	
}
