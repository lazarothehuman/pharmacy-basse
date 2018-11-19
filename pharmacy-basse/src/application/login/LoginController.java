package application.login;

import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.User;
import mz.humansolutions.utils.FrameManager;

public class LoginController implements Initializable {

	@FXML
	Button aceder;

	@FXML
	ImageView mainView;

	@FXML
	ImageView userView;

	@FXML
	private TextField username = new TextField();

	@FXML
	private PasswordField password = new PasswordField();

	@FXML
	private Hyperlink forgotPassword;

	private int count = 0;

	private int MAXATTEMPTS = 3;

	public FrameManager frameManagers = new FrameManager();

	private DataManager dataManager = new DataManagerImp();

	public void aceder() {
		String usern = username.getText().trim();
		String pass = password.getText().trim();
		Alert alert = new Alert(AlertType.ERROR);
		if (usern != null && !usern.isEmpty() && pass != null && !pass.isEmpty()) {
			User user = dataManager .findUser(usern.toLowerCase());
			if (user != null) {
				boolean loginOk = false;
				try {
					loginOk = dataManager.checkPassword(user.getPassword(), pass);
				} catch (GeneralSecurityException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (loginOk) {
					dataManager.setSelectedUser(user);
					frameManagers.mainController2();
					Stage stage = (Stage) aceder.getScene().getWindow();
					stage.close();
				}
				else {
					alert.setContentText("Password ou username errado!");
					alert.setTitle("Erro de autenticacao");
					alert.setHeaderText(null);
					alert.showAndWait();
					if (count < MAXATTEMPTS)
						count++;
					else {
						alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Atingiu limites de tentativas de acesso");
						alert.setHeaderText(null);
						alert.setContentText("Atingiu limite de tentativas de acesso que são 3.");
						alert.showAndWait();
						aceder.setDisable(true);

					}
				}
			} else {
				alert = new Alert(AlertType.ERROR);
				alert.setTitle("Usuário não encontrado");
				alert.setHeaderText(null);
				alert.setContentText("Usuário com nome : " + username.getText()
						+ " não existe. Tente remover os espaços ou colocar todos os caracteres em minúsculos");
				alert.showAndWait();
			}
		}

	}

	public void forgottenPassword() {

	}

	public void enterKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER)
			aceder();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
