package application.forms;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Profile;
import mz.humansolutions.models.User;
import mz.humansolutions.utils.AlertUtils;
import mz.humansolutions.utils.Validations;

public class AddUserController implements Initializable {

	@FXML
	ComboBox<Profile> comboPerfil;



	@FXML
	TextField nomeTf = new TextField();

	@FXML
	TextField usernameTf = new TextField();
	
	@FXML
	TextField telefoneTf = new TextField();

	@FXML
	TextField passwordTf = new TextField();

	@FXML
	TextField repeatPasswordTf = new TextField();

	@FXML
	Button add;

	DataManager dataManager = new DataManagerImp();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comboPerfil.setItems(FXCollections.observableArrayList(dataManager.findProfiles(Boolean.TRUE)));
		
	}

	public void add() {
		boolean valid = false;
		String nome = nomeTf.getText();
		String username = usernameTf.getText();
		String password = passwordTf.getText();
		String repeat = repeatPasswordTf.getText();
		Profile profile = comboPerfil.getSelectionModel().getSelectedItem();
		boolean isUnique = true;
		String telefone = telefoneTf.getText();
		if (telefone != null && !telefone.isEmpty()) {
			valid = Validations.isValidForSMSNotification(telefone);
		}
		if (nome != null && !nome.isEmpty() && username != null && !username.isEmpty() && password != null
				&& !password.isEmpty() && repeat != null && !repeat.isEmpty() && profile != null  && valid) {
			username = username.trim();
			username = username.toLowerCase();
			User usuario = dataManager.findUser(username);
			if (usuario != null)
				isUnique = false;
			if (password.equals(repeat) && isUnique == true) {
				User user = new User();
				user.setActive(true);
				user.setName(nome);
				user.setProfile(profile);
				user.setUsername(username);
				user.setPassword(password);
				user.setTelefone(telefone);
				try {
					dataManager.createUser(user);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (GeneralSecurityException e) {
					e.printStackTrace();
				}
				AlertUtils.alertSucesso("Usuario adicionado com sucesso");
				Stage stage = (Stage) add.getScene().getWindow();
				stage.close();
			} else {
				// String message = "";
				if (!isUnique) {
					// message = "Já existe um usuário com esse username";
					usernameTf.setStyle("-fx-border-color:#ff0000;");
				} else
					// message = "As palavras passes inseridas são diferentes";
					AlertUtils.alertErroInsercaoDados();
			}

		} else {
			AlertUtils.alertErroInsercaoDados();
		}

	}

}
