package application.views;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Profile;
import mz.humansolutions.models.User;
import mz.humansolutions.utils.FrameManager;

public class MainController implements Initializable {

	@FXML
	Button clienteBtn;
	
	@FXML
	Button fornecedorBtn;


	@FXML
	AnchorPane ContentPane;

	@FXML
	Button userBtn;

	@FXML
	Button settingsButton;

	@FXML
	JFXButton registar;

	@FXML
	JFXButton visualizarMedicamento;

	@FXML
	JFXButton visualizarMovimentos;

	@FXML
	VBox seccaoSistema;

	AnchorPane content;

	FrameManager frameManager = new FrameManager();
	DataManagerImp dataManager = new DataManagerImp();
	User user;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// por content como venda
		setContextMenu();
		if (content != null) {
			setContent(content);
		}
	}

	public void setContextMenu() {
		user = dataManager.findCurrentUser();
		visibilidadeMenus();//hum ???
		
		MenuItem addFornecedor = new MenuItem("Adicionar fornecedor");
		MenuItem viewFornecedores = new MenuItem("Visualizar fornecedores");
		final ContextMenu contextMenuFornecedor = new ContextMenu();
		contextMenuFornecedor.getItems().addAll(addFornecedor,viewFornecedores);
		addFornecedor.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				addFornecedor();
				
			}
		});
		viewFornecedores.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				viewFornecedores();
				
			}
		});
		fornecedorBtn.setContextMenu(contextMenuFornecedor);
		
		MenuItem addCliente = new MenuItem("Adicionar cliente");
		MenuItem viewClientes = new MenuItem("Visualizar todos clientes");
		final ContextMenu contextMenuCliente = new ContextMenu();
		contextMenuCliente.getItems().addAll(addCliente, viewClientes);
		addCliente.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				addCliente();
			}
		});
		viewClientes.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				viewCliente();
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
				adicionarUser();
			}
		});
		viewUser.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				viewUsers();
			}
		});
		userBtn.setContextMenu(contextMenuUser);

		MenuItem addPerfil = new MenuItem("Adicionar perfil");
		MenuItem viewPerfis = new MenuItem("Visualizar todos perfis");
		MenuItem addTransaccao = new MenuItem("Adicionar transa��o");
		MenuItem viewTransaccoes = new MenuItem("Visualizar todas transa��es");
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

	protected void adicionarUser() {
		AnchorPane content = frameManager.addUser(user);
		setContent(content);
		
	}

	protected void viewUsers() {
		AnchorPane content = frameManager.viewUsers(user);
		setContent(content);
		
	}

	protected void viewFornecedores() {
		AnchorPane content = frameManager.searchFornecedores(user);
		setContent(content);
		
	}

	public void viewMedicamento() {
		AnchorPane content = frameManager.searchMedicamento(user);
		setContent(content);
	}

	public void viewMovimento() {
		AnchorPane content = frameManager.searchMovimento(user);
		setContent(content);
	}

	public void entrada() {
		AnchorPane content = frameManager.updateStock(user);
		setContent(content);
	}

	public void venda() {
		AnchorPane content = frameManager.venda(user);
		setContent(content);
	}

	public void addCliente() {
		AnchorPane content = frameManager.addCliente(user);
		setContent(content);
	}

	public void viewCliente() {
		AnchorPane content = frameManager.searchCliente(user);
		setContent(content);
	}

	public void addMedicamento() {
		AnchorPane content = frameManager.addMedicamento(user);
		setContent(content);
	}
	
	public void addFornecedor() {
		AnchorPane content = frameManager.addFornecedor(user);
		setContent(content);
	}
	
	public void logout() {
		frameManager.login();
		Stage stage = (Stage) ContentPane.getScene().getWindow();
		stage.close();
	}

	public void setContent(AnchorPane content) {
		if (content != null) {
			ContentPane.setTopAnchor(content, 0.0);
			ContentPane.setLeftAnchor(content, 0.0);
			ContentPane.setBottomAnchor(content, 0.0);
			ContentPane.setRightAnchor(content, 0.0);
			ContentPane.getChildren().setAll(content);
		}
	}

	private void visibilidadeMenus() {
		Profile profile = user.getProfile();
		if (profile.getId() == 5) {
			// seccaoSistema.setDisable(true);
			registar.setDisable(true);
			settingsButton.setDisable(true);
			clienteBtn.setDisable(true);
			userBtn.setDisable(true);
			visualizarMedicamento.setDisable(true);
			visualizarMovimentos.setDisable(true);
		}

	}
	
	public void home() {
		AnchorPane content = frameManager.venda(user);
		setContent(content);
	}

	public void refreshContent() {
		if (content != null)
			setContent(content);

	}

	public void setContentFromOtherView(AnchorPane content) {
		if (content != null)
			this.content = content;

	}
}
