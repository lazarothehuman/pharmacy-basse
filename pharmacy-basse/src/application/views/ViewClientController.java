package application.views;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Cliente;
import mz.humansolutions.models.Sexo;
import mz.humansolutions.models.User;
import mz.humansolutions.utils.AlertUtils;
import mz.humansolutions.utils.FrameManager;

public class ViewClientController implements Initializable {

	List<Cliente> clientes;

	@FXML
	Button pesquisar;
	
	@FXML
	AnchorPane ContentPane;

	@FXML
	Button adicionarCliente;

	@FXML
	Button modificarCliente;

	@FXML
	Button removerCliente;

	@FXML
	TextField telefoneTf = new TextField();

	@FXML
	TextField emailTf = new TextField();

	@FXML
	TextField nomeTf = new TextField();

	@FXML
	ComboBox<Sexo> sexoCombo;

	@FXML
	CheckBox active;

	@FXML
	DatePicker dataInicio;

	@FXML
	DatePicker dataFim;

	FrameManager frameManagers = new FrameManager();

	@FXML
	TableView<Cliente> tableCliente;

	@FXML
	TableColumn<Cliente, String> nomeColumn;

	@FXML
	TableColumn<Cliente, String> emailColumn;

	@FXML
	TableColumn<Cliente, String> telefoneColumn;

	@FXML
	TableColumn<Cliente, String> dataColumn;

	@FXML
	TableColumn<Cliente, String> sexoColumn;

	@FXML
	TableColumn<Cliente, String> naturalidadeColumn;

	@FXML
	TableColumn<Cliente, String> moradaColumn;

	@FXML
	Label lblUser = new Label();

	@FXML
	Label lblProfile = new Label();

	@FXML
	Label lblTotal = new Label();

	DataManager dataManager = new DataManagerImp();

	FrameManager frameManager = new FrameManager();

	User user = dataManager.findCurrentUser();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sexoCombo.setItems(FXCollections.observableArrayList(Sexo.values()));
		nomeColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("email"));
		telefoneColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefone"));
		dataColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("dataNascimento"));
		sexoColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("sexo"));
		naturalidadeColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("naturalidade"));
		moradaColumn.setCellValueFactory(new PropertyValueFactory<Cliente, String>("morada"));
	}

	public void pesquisar() {
		
		if (clientes != null) {
			tableCliente.setItems(FXCollections.observableArrayList(clientes));
		}
		lblTotal.setText(clientes.size() + "");
	}

	public void addCliente() {
		AnchorPane content = frameManager.addCliente(user);
		if (content != null)
			setContent(content);
	}

	private void setContent(AnchorPane content) {
		// TODO Auto-generated method stub
		
	}

	public void modificarCliente() {
		Cliente cliente = null;
		cliente = tableCliente.getSelectionModel().getSelectedItem();
		if (cliente != null && user != null)
			frameManager.modifyCliente(cliente, user);
		else
			AlertUtils.alertErroSelecionar("Para modificar um cliente é necessário selecionar um!");

	}

	public void removerCliente() {
		Cliente selectedCliente = null;
		selectedCliente = tableCliente.getSelectionModel().getSelectedItem();
		if (selectedCliente != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmação de remoção");
			alert.setHeaderText(null);
			alert.setContentText("Tem certeza que deseja remover o cliente?" + selectedCliente.getNome());
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				selectedCliente.setActive(false);
				dataManager.updateCliente(selectedCliente);
				refreshItems();
			}
		}
	}

	private void refreshItems() {
		// TODO Auto-generated method stub
	}

	public void goHome() {
		frameManager.venda(dataManager.findCurrentUser());
	}

	public void doubleClickOnClient(MouseEvent event) {
		if (event.getClickCount() == 2) {
			modificarCliente();
		}
	}

	public void enterKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER)
			pesquisar();
	}

}