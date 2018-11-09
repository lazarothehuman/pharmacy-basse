package application.views;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Profile;
import mz.humansolutions.models.User;
import mz.humansolutions.utils.AlertUtils;
import mz.humansolutions.utils.FrameManager;

public class ViewUserController implements Initializable {
	
	//put timer to refresh

	@FXML
	Button pesquisar;

	@FXML
	Button adicionarUser;

	@FXML
	Button modificarUser;

	@FXML
	Button removerUser;

	@FXML
	TextField usernameTf = new TextField();

	@FXML
	TextField nomeTf = new TextField();

	@FXML
	ComboBox<Profile> comboProfile;

	

	@FXML
	CheckBox active = new CheckBox();

	@FXML
	TableView<User> tableUser;

	@FXML
	TableColumn<User, String> nomeColumn;

	@FXML
	TableColumn<User, String> usernameColumn;

	@FXML
	TableColumn<User, String> profileColumn;

	@FXML
	TableColumn<User, String> distritoColumn;

	@FXML
	Label lblUser = new Label();

	@FXML
	Label lblProfile = new Label();

	@FXML
	Label lblTotal = new Label();

	DataManager dataManager = new DataManagerImp();

	FrameManager frameManager = new FrameManager();

	@FXML
	Hyperlink home;

	@FXML
	Hyperlink about;

	List<User> listUsers = new ArrayList<User>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Profile> profiles = dataManager.findProfiles(true);
		if (profiles != null)
			comboProfile.setItems(FXCollections.observableArrayList(profiles));
		User user = dataManager.findCurrentUser();
		if (user != null) {
			lblUser.setText(user.getName().toLowerCase());
			lblProfile.setText(user.getProfile().getProfilename());
		}
		nomeColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
		usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		profileColumn.setCellValueFactory(new PropertyValueFactory<User, String>("profile"));
		
	}

	public void pesquisar() {
		String username = usernameTf.getText();
		String nome = nomeTf.getText();
		Profile profile = comboProfile.getValue();
		Boolean activee = Boolean.valueOf(!active.isSelected());
		List<User> users = dataManager.findUsers(username, nome, profile, activee);
		if (users != null) {
			tableUser.setItems(FXCollections.observableArrayList(users));
			lblTotal.setText(users.size() + "");
		}
		else {
			AlertUtils.pesquisaVazia();
			tableUser.setItems(null);
		}
	}

	public void addUser() {// fazer controle de permissoes
		User user = dataManager.findCurrentUser();
		if (user != null) {
			frameManager.addUser(user);
			pesquisar();
		} else {
			AlertUtils.alertSemPrivelegio();
		}
	}

	public void modifyUser() {// fazer controle de permissoes
		User selectedUser = null;
		selectedUser = tableUser.getSelectionModel().getSelectedItem();
		User user = dataManager.findCurrentUser();
		if (user != null) {
			if (selectedUser != null) {
				frameManager.modifyUser(user, selectedUser);
				pesquisar();
			} else {
				AlertUtils.alertErroSelecionar("Para modificar um usuario é necessário selecionar um!");
			}
		} else {
			AlertUtils.alertSemPrivelegio();
		}
	}

	public void removerUser() throws UnsupportedEncodingException, GeneralSecurityException {
		User user = dataManager.findCurrentUser();
		if (user != null) {
			if (user.getProfile().getId() == 1l || user.getProfile().getId() == 2l) {
				User selectedUser = null;
				selectedUser = tableUser.getSelectionModel().getSelectedItem();
				if (selectedUser != null) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Confirmação de remoção");
					alert.setHeaderText(null);
					alert.setContentText("Tem certeza que deseja remover o usuario ?" + selectedUser.getName());

					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK) {
						selectedUser.setActive(false);
						dataManager.updateUser(selectedUser);
						listUsers.remove(selectedUser);
						refreshItems();
					}

				}
			} else {
				AlertUtils.alertSemPrivelegio();
			}

		}

	}

	private void refreshItems() {
		pesquisar();

	}

	public void goHome() {
		Stage stage = (Stage) adicionarUser.getScene().getWindow();
		stage.close();
	}

	public void about() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Sobre esta janela");
		alert.setContentText("Esta janela tem objectivo de ajudar a visualizar todos "
				+ "os usuários gravados no sistema. Do lado direito da tela vais encontrar um conjunto de filtros, "
				+ "preencha os para uma busca mais refinada. Para sair desta tela, apenas prima voltar ou "
				+ "no canto superior direito para tirar a janela. Nesta janela também é possível adicionar, modificar e remover um usuário. "
				+ "Para voltar para janela principal, é necessário clicar no  HOME ou no x no canto superior a direita");
		alert.setHeaderText(null);
		alert.showAndWait();
	}

	public void doubleClickOnUser(MouseEvent event) {
		if (event.getClickCount() == 2) {
			modifyUser();
		}
	}

	public void enterKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER)
			pesquisar();
	}

}
