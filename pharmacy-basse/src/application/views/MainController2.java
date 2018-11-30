package application.views;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.User;
import mz.humansolutions.utils.FrameManager;

public class MainController2 implements Initializable {

	@FXML
	Button clienteBtn;

	@FXML
	Button userBtn;

	@FXML
	Button settingsButton;

	FrameManager frameManager=new FrameManager();
	DataManagerImp dataManager=new DataManagerImp();
	User user;
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		setContextMenu();

		// ...
	}

	public void setContextMenu() {
		user=dataManager.findCurrentUser();
		MenuItem addCliente = new MenuItem("Adicionar cliente");
		MenuItem viewClientes = new MenuItem("Visualizar todos clientes");
		final ContextMenu contextMenuCliente = new ContextMenu();
		contextMenuCliente.getItems().addAll(addCliente, viewClientes);
		addCliente.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Cut...");
			}
		});
		viewClientes.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Cut...");
			}
		});
		clienteBtn.setContextMenu(contextMenuCliente);

		MenuItem addUser = new MenuItem("Adicionar usuário");
		MenuItem viewUser = new MenuItem("Visualizar todos usuários");
		final ContextMenu contextMenuUser = new ContextMenu();
		contextMenuUser.getItems().addAll(addUser, viewUser);
		addUser.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Cut...");
			}
		});
		viewUser.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Cut...");
			}
		});
		userBtn.setContextMenu(contextMenuUser);

		MenuItem addPerfil = new MenuItem("Adicionar perfil");
		MenuItem viewPerfis = new MenuItem("Visualizar todos perfis");
		MenuItem addTransaccao = new MenuItem("Adicionar transação");
		MenuItem viewTransaccoes = new MenuItem("Visualizar todas transações");
		final ContextMenu contextMenuSettings = new ContextMenu();
		contextMenuSettings.getItems().addAll(addPerfil, viewPerfis, addTransaccao, viewTransaccoes);
		addPerfil.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Cut...");
			}
		});
		viewPerfis.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Cut...");
			}
		});
		addTransaccao.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Cut...");
			}
		});
		viewTransaccoes.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("Cut...");
			}
		});
		
		settingsButton.setContextMenu(contextMenuSettings);
	}
	
	public void visualizar() {
		frameManager.searchMedicamento();
	}

	
	public void entrada() {
		frameManager.addMovimento(user);
	}

}
