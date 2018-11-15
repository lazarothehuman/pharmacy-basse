package application.views;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.managers.NotificationManager;
import mz.humansolutions.managers.NotificationManagerImp;
import mz.humansolutions.models.User;
import mz.humansolutions.utils.AlertUtils;
import mz.humansolutions.utils.FrameManager;

public class MainController implements Initializable {
	
	/*
	 * 
	 * version 1.3
	 */

	int DIA_ENVIO_MENSAGEM = 28;
	@FXML
	Label usernameLbl = new Label();

	@FXML
	Label userProfileLbl = new Label();

	@FXML
	Label districNameLbl = new Label();

	@FXML
	Label developerMessage = new Label();

	User user;

	DataManager dataManager = new DataManagerImp();

	FrameManager frameManager = new FrameManager();

	NotificationManager notifcationManager = new NotificationManagerImp();

	

	public void addUser() {
		frameManager.addUser(user);
	}



	public void viewUsers() {
		frameManager.viewUsers(user);
	}

	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		user = dataManager.findCurrentUser();
		System.out.println("User:" +user.getName());
		if (user != null) {
			usernameLbl.setText(user.getName());
			userProfileLbl.setText(user.getProfile().getProfilename());
		}
		developerMessage.setText("Unidade, Paz e Desenvolvimento" + 
				" FRELIMO a Força da Mudança");
	}
	
	
}
