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
	@FXML private Button butDeletePet;
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
	
	public void initializeTypes() {
		chooseType.setItems(FXCollections.observableArrayList("Perro", "Gato"));
	}
	
	public void initializeGenders() {
		chooseGender.setItems(FXCollections.observableArrayList("Macho", "Hembra"));
	}
	
	public void buttonActions() {
		Alert popUp=new Alert(AlertType.INFORMATION);
		popUp.setTitle("No tan rapido velocista");
		popUp.setHeaderText(null);
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
				Main.getPetClub().findByName(txtNameOwner.getText()+" "+txtLastName.getText()).add(
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
				findOwner(1, popUp);
			} else if (result.get() == buttonTypeNombre) {
				findOwner(-1, popUp);
			}
		});
		butDeleteOwner.setOnAction(e->{
			deleteOwner(popUp);
		});
		butOwners.setOnAction(e->{
			listOwners(popUp);
		});
		butPets.setOnAction(e->{
			listPets(popUp);
		});
		butFindPet.setOnAction(e->{
			popUp.setContentText("Quiere buscar una mascota por su nombre o su fecha de nacimiento?");
			ButtonType buttonTypeDOB = new ButtonType("Fecha de nacimiento");
			ButtonType buttonTypeNombre = new ButtonType("Nombre");
			popUp.getButtonTypes().clear();
			popUp.getButtonTypes().addAll(buttonTypeDOB, buttonTypeNombre);
			Optional<ButtonType> result = popUp.showAndWait();
			if (result.get() == buttonTypeDOB){
				findPet(1, popUp);
			} else if (result.get() == buttonTypeNombre) {
				findPet(-1, popUp);
			}
		});
		butDeletePet.setOnAction(e->{
			deletePet();
		});
		butRePets.setOnAction(e->{
			listRepeatedPets(popUp);
		});
		
	}
	
	public void findOwner(int x, Alert popUp2) {
		String found="";
		TextInputDialog popUp=new TextInputDialog("");
		popUp.setTitle("No tan rapido velocista");
		popUp.setHeaderText(null);
		if(x>0) {
			popUp.setContentText("Ingrese la cedula del usuario que desea buscar");
			Optional<String> result = popUp.showAndWait();
			if(result.isPresent()) {
				try {
					found=Main.getPetClub().findById(Integer.parseInt(result.get())).toString();
					String[] founded=found.split(" ");
					txtNameOwner.setText(founded[0]);
					txtLastName.setText(founded[1]);
					txtId.setText(founded[2]);
					txtDOBOwner.setText(founded[3]);
				} catch (Exception e) {
					popUp2.setContentText(e.getMessage());
					popUp2.getButtonTypes().clear();
					popUp2.getButtonTypes().add(new ButtonType("Okay"));
					popUp2.showAndWait();
				}
			}
		}else {
			popUp.setContentText("Ingrese el nombre y el apellido del usuario que desea buscar");
			Optional<String> result = popUp.showAndWait();
			if(result.isPresent()) {
				try {
					found=Main.getPetClub().findByName(result.get()).toString();
					String[] founded=found.split(" ");
					txtNameOwner.setText(founded[0]);
					txtLastName.setText(founded[1]);
					txtId.setText(founded[2]);
					txtDOBOwner.setText(founded[3]);
				} catch (Exception e) {
					popUp2.setContentText(e.getMessage());
					popUp2.getButtonTypes().clear();
					popUp2.getButtonTypes().add(new ButtonType("Okay"));
					popUp2.showAndWait();
				}
			}
		}
	}
	
	public void deleteOwner(Alert popUp) {
		popUp.getButtonTypes().clear();
		popUp.getButtonTypes().add(new ButtonType("Okay"));
		TextInputDialog popUp2=new TextInputDialog("");
		popUp2.setTitle("No tan rapido velocista");
		popUp2.setContentText("Ingrese la cedula del usuario que desea buscar");
		popUp2.setHeaderText(null);
		Optional<String> result = popUp2.showAndWait();
		if(result.isPresent()) {
			try {
				popUp.setContentText("Se eliminó el usuario deseado");
				Main.getPetClub().delete(Integer.parseInt(result.get()));
			}catch(Exception e) {
				popUp.setContentText(e.getMessage());
			}finally {
				popUp.showAndWait();
			}
		}
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
		String[] pets=result.split("#");
		List<String> list=new ArrayList<String>();
		for(int i=0;i<pets.length;i++) {
			list.add(pets[i]);
		}
		ObservableList<String> petsList=FXCollections.observableList(list);
		listPets_Owners.setItems(petsList);
	}
	
	public void findPet(int x, Alert popUp2) {
		String found="";
		TextInputDialog popUp=new TextInputDialog("");
		popUp.setTitle("No tan rapido velocista");
		popUp.setHeaderText(null);
		if(x>0) {
			popUp.setContentText("Ingrese la fecha de nacimiento de la mascota que desea buscar");
			Optional<String> result = popUp.showAndWait();
			if(result.isPresent()) {
				try {
					found=Main.getPetClub().findById(Integer.parseInt(txtId.getText())).findByDOB(Main.getPetClub().findById(Integer.parseInt(txtId.getText())).getFirst(), result.get());
					String[] founded=found.split(" ");
					txtNamePet.setText(founded[0]);
					chooseGender.setValue(founded[1]);
					chooseType.setValue(founded[2]);
					txtDOBPet.setText(founded[3]);
				} catch (Exception e) {
					popUp2.setContentText(e.getMessage());
					popUp2.getButtonTypes().clear();
					popUp2.getButtonTypes().add(new ButtonType("Okay"));
					popUp2.showAndWait();
				}
			}
		}else {
			popUp.setContentText("Ingrese el nombre de la mascota que desea buscar");
			Optional<String> result = popUp.showAndWait();
			if(result.isPresent()) {
				try {
					found=Main.getPetClub().findById(Integer.parseInt(txtId.getText())).findByName(Main.getPetClub().findById(Integer.parseInt(txtId.getText())).getFirst(), result.get());
					String[] founded=found.split(" ");
					txtNamePet.setText(founded[0]);
					chooseType.setValue(founded[1]);
					chooseGender.setValue(founded[2]);
					txtDOBPet.setText(founded[3]);
				} catch (Exception e) {
					popUp2.setContentText(e.getMessage());
					popUp2.getButtonTypes().clear();
					popUp2.getButtonTypes().add(new ButtonType("Okay"));
					popUp2.showAndWait();
				}
			}
		}
	}
	
	public void deletePet() {
		try {
			Main.getPetClub().findById(Integer.parseInt(txtId.getText())).delete(txtNamePet.getText());
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			txtNamePet.setText(null);
			txtDOBPet.setText(null);
			chooseGender.getSelectionModel().clearSelection();
			chooseType.getSelectionModel().clearSelection();
		}
	}
	
	public void listRepeatedPets(Alert popUp) {
		String passed="";
		String result="";
		if(txtFilter.getText().equals("")) {
			passed=null;
		}else {
			passed=txtFilter.getText();
		}
		try {
			result=Main.getPetClub().listRepeatedPets(passed);
		} catch (EmptyList e) {
			popUp.setContentText(e.getMessage());
			popUp.getButtonTypes().clear();
			popUp.getButtonTypes().add(new ButtonType("Okay"));
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
