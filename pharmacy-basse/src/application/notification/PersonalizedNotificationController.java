package application.notification;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.managers.NotificationManager;
import mz.humansolutions.managers.NotificationManagerImp;
import mz.humansolutions.utils.AlertUtils;

public class PersonalizedNotificationController implements Initializable {

	@FXML
	private TextField content;

	

	DataManager dataManager = new DataManagerImp();
	NotificationManager notificationManager = new NotificationManagerImp();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

	}

	public void send() {
		String message = content.getText();
		if (message != null) {
			if (!message.isEmpty()) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmação de SMS");
				alert.setHeaderText(null);
				alert.setContentText("Tem certeza que deseja informar aos membros atraves de mensagens (SMS)  ?");
				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					//if (membros != null)
						//notificationManager.sendSmsNotificationPersonalized(membros, message);
				}
			} else
				AlertUtils.alertErroInsercaoDados();

		} else
			AlertUtils.alertErroInsercaoDados();

	}

	public void clear() {
		content.setText("");
	}

}
