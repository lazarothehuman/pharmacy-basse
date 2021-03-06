package mz.humansolutions.utils;

import java.io.IOException;

import application.forms.ModifyMedicamentoController;
import application.forms.ModifyUserController;
import application.views.ViewMedicamentosDoMovimentoController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Cliente;
import mz.humansolutions.models.Medicamento;
import mz.humansolutions.models.Movimento;
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
			primaryStage.getIcons().add(new Image("pharmacy.png"));
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
	
	public void caixaMainController() {
		Stage primaryStage = new Stage();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/views/CaixaMain.fxml"));
			loader.load();
			Parent root = loader.getRoot();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Farmacia Baddam");
			primaryStage.getIcons().add(new Image("pharmacy.png"));
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
			primaryStage.getIcons().add(new Image("pharmacy.png"));
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

	public AnchorPane searchCliente(User user) {
		AnchorPane content = null;
		if (user != null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(306l);
			if (transaction.getProfiles().contains(profile))
				content = loadContent(transaction.getUrl());
			else
				AlertUtils.alertSemPrivelegio();
		}
		return content;
	}

	public AnchorPane searchMedicamento(User user) {
		AnchorPane content = null;
		if (user != null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(301l);
			if (transaction.getProfiles().contains(profile))
				content = loadContent(transaction.getUrl());
			else
				AlertUtils.alertSemPrivelegio();
		}
		return content;

	}

	public AnchorPane addUser(User user) {
		AnchorPane content = null;
		if (user != null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(203l);
			if (transaction.getProfiles().contains(profile))
				content = loadContent(transaction.getUrl());
			else
				AlertUtils.alertSemPrivelegio();
		}
		return content;
	}

	private AnchorPane loadContent(String url) {
		AnchorPane content = null;
		try {
			content = (AnchorPane) FXMLLoader.load(getClass().getResource(url));
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return content;

	}

	private AnchorPane loadContent(String url, Object modelo) {// para casos de modificar um certo dado
		AnchorPane content = null;
		try {
			content = (AnchorPane) FXMLLoader.load(getClass().getResource(url));
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return content;

	}

	private void load(String url, Object object) {
		Stage primaryStage = new Stage();
		User user = null;
		Medicamento medicamento = null;
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

			else if (object instanceof Medicamento) {
				medicamento = (Medicamento) object;
				if (medicamento != null) {
					ModifyMedicamentoController modifyMedicamentoController = loader.getController();
					modifyMedicamentoController.setMedicamento(medicamento);
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

	public AnchorPane viewUsers(User user) {
		AnchorPane content = null;
		if (user != null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(303l);
			if (transaction.getProfiles().contains(profile)) {
				content = loadContent(transaction.getUrl());
			} else
				AlertUtils.alertSemPrivelegio();
		}
		return content;
	}

	public AnchorPane addMedicamento(User user) {
		AnchorPane content = null;
		if (user != null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(202l);
			if (transaction.getProfiles().contains(profile)) {
				content = loadContent(transaction.getUrl());
			} else
				AlertUtils.alertSemPrivelegio();
		}
		return content;
	}

	public AnchorPane updateStock(User user) {
		AnchorPane content = null;
		if (user != null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(203l);
			if (transaction.getProfiles().contains(profile))
				content = loadContent(transaction.getUrl());
			else
				AlertUtils.alertSemPrivelegio();
		}
		return content;
	}

	public AnchorPane modifyMedicamento(User user, Medicamento selectedMedicamento) {
		AnchorPane content = null;
		if (user != null && selectedMedicamento != null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(204l);
			if (transaction.getProfiles().contains(profile))
				content = loadContent(transaction.getUrl(), selectedMedicamento);
			else
				AlertUtils.alertSemPrivelegio();
		}

		return content;

	}

	public AnchorPane venda(User user) {
		AnchorPane content = null;
		if (user != null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(205l);
			if (transaction.getProfiles().contains(profile))
				content = loadContent(transaction.getUrl());
			else
				AlertUtils.alertSemPrivelegio();
		}
		return content;
	}

	public AnchorPane addCliente(User user) {
		AnchorPane content = null;
		if (user != null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(206l);
			if (transaction.getProfiles().contains(profile))
				content = loadContent(transaction.getUrl());
			else
				AlertUtils.alertSemPrivelegio();
		}
		return content;

	}

	public void modifyCliente(Cliente cliente, User user) {
		// TODO Auto-generated method stub

	}

	public AnchorPane searchMovimento(User user) {
		AnchorPane content = null;
		if (user != null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(302l);
			if (transaction.getProfiles().contains(profile))
				content = loadContent(transaction.getUrl());
			else
				AlertUtils.alertSemPrivelegio();
		}
		return content;
	}

	public AnchorPane addFornecedor(User user) {
		AnchorPane content = null;
		if (user != null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(207l);
			if (transaction.getProfiles().contains(profile))
				content = loadContent(transaction.getUrl());
			else
				AlertUtils.alertSemPrivelegio();
		}
		return content;
	}
	
	public AnchorPane searchFornecedores(User user) {
		AnchorPane content = null;
		if (user != null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(307l);
			if (transaction.getProfiles().contains(profile))
				content = loadContent(transaction.getUrl());
			else
				AlertUtils.alertSemPrivelegio();
		}
		return content;
	}
	
	
	
	public AnchorPane goToMain() {
		return null;
	}
	
	public void viewMedicamentosDoMovimento(Movimento movimento) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/application/views/View-MedicamentosDoMovimento.fxml"));
		
			try {
				loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			ViewMedicamentosDoMovimentoController view=loader.getController();
			view.setMovimento(movimento);
			
			Parent p=loader.getRoot();
			Stage stage=new Stage();
			stage.setScene(new Scene(p));
			stage.showAndWait();
		
	}

}
