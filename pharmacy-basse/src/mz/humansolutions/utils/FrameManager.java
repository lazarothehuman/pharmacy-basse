package mz.humansolutions.utils;

import application.forms.ModifyUserController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;

import mz.humansolutions.models.Profile;
import mz.humansolutions.models.Transaccao;
import mz.humansolutions.models.User;

public class FrameManager {

	DataManager dataManager = new DataManagerImp();

	public void mainController() {
		Stage primaryStage = new Stage();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/views/Main.fxml"));
			loader.load();
			Parent root = loader.getRoot();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Farmacia Baddam");
			primaryStage.getIcons().add(new Image("Farmacia baddam.png"));
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setOnCloseRequest(e -> {
				Platform.exit();
				System.exit(0);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void login() {
		Stage primaryStage = new Stage();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/login/Login.fxml"));
			loader.load();
			Parent root = loader.getRoot();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Farmacia Baddam");
			primaryStage.getIcons().add(new Image("Farmacia baddam.png"));
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setOnCloseRequest(e -> {
				Platform.exit();
				System.exit(0);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void searchCliente() {
		Stage primaryStage = new Stage();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/views/View-Client.fxml"));
			loader.load();
			Parent root = loader.getRoot();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Procurar ");
			primaryStage.getIcons().add(new Image("frelimo.jpg"));
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}





	public void addUser(User user) {
		if (user != null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(203l);
			if (transaction.getProfiles().contains(profile))
				load(transaction.getUrl());
			else
				AlertUtils.alertSemPrivelegio();
		}
	}



	private void load(String url) {
		Stage primaryStage = new Stage();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/forms/Add-Medicamento.fxml"));
			loader.load();
			Parent root = loader.getRoot();
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Farmacia Baddam");
			primaryStage.getIcons().add(new Image("Farmacia baddam.png"));
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void load(String url, Object object) {
		Stage primaryStage = new Stage();
		User user = null;
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(url));
			loader.load();
			Parent root = loader.getRoot();
			Scene scene = new Scene(root);

			if (object instanceof User) {
				user = (User) object;
				if (user != null) {
					ModifyUserController modifyUserController = loader.getController();
					modifyUserController.setUser(user);
				}
			}
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void modifyUser(User user, User selectedUser) {
		if (user != null && user != null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(206l);
			if (transaction.getProfiles().contains(profile))
				load(transaction.getUrl(), selectedUser);
			else
				AlertUtils.alertSemPrivelegio();
		}

	}








	public void viewUsers(User user) {
		if (user != null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(303l);
			if (transaction.getProfiles().contains(profile))
				load(transaction.getUrl());
			else
				AlertUtils.alertSemPrivelegio();
		}

	}





	public void addMedicamento(User user) {
		if (user != null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(202l);
			//if (transaction.getProfiles().contains(profile))
				load("");
			//else
				//AlertUtils.alertSemPrivelegio();
		}
	}
	
	public void addMovimento(User user) {
		if (user != null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(203l);
			if (transaction.getProfiles().contains(profile))
				load(transaction.getUrl());
			else
				AlertUtils.alertSemPrivelegio();
		}
	}
	

}
