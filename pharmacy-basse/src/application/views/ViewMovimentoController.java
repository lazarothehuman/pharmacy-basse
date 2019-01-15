package application.views;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Medicamento;
import mz.humansolutions.models.Movimento;
import mz.humansolutions.models.Profile;
import mz.humansolutions.models.User;
import mz.humansolutions.utils.AlertUtils;
import mz.humansolutions.utils.FrameManager;

public class ViewMovimentoController implements Initializable {

	// put timer to refresh
	
	@FXML
	AnchorPane ContentPane;

	@FXML
	Button pesquisar;

	@FXML
	Button actualizarStock;

	@FXML
	Button modificarUser;

	@FXML
	Button removerUser;

	@FXML
	TextField codigoTf = new TextField();

	@FXML
	TextField nomeTf = new TextField();
	
	@FXML
	TextField movimentoTf = new TextField();

	@FXML
	ComboBox<Profile> comboProfile;

	@FXML
	CheckBox active = new CheckBox();

	@FXML
	TableView<Movimento> tableMovimento;

	@FXML
	TableColumn<Movimento, String> nomeColumn;

	@FXML
	TableColumn<Movimento, Date> dataColumn;

	@FXML
	TableColumn<Movimento, Date> validadeColumn;


	@FXML
	TableColumn<Medicamento, Integer> quantidadeColumn;
	
	@FXML
	TableColumn<Movimento, Long> codigoColumn;
	
	@FXML
	TableColumn<Movimento, String> codigoMedicamentoColumn;

	@FXML
	TableColumn<Movimento, Integer> codFornecedorColumn;
	
	@FXML
	TableColumn<Movimento, String> registadorColumn;
	
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

	List<Medicamento> listMedicamentos = new ArrayList<Medicamento>();

	
	//PropertyValueFactory<Movimento, String> cod_medicamentoFactory=new PropertyValueFactory("medicamento");
	PropertyValueFactory<Movimento, String> nome_medicamentoFactory=new PropertyValueFactory("medicamento");
	PropertyValueFactory<Movimento, String> registadorFactory=new PropertyValueFactory("registador");
	User user;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Profile> profiles = dataManager.findProfiles(true);
		user = dataManager.findCurrentUser();
		if (user != null) {
			lblUser.setText(user.getName().toLowerCase());
			lblProfile.setText(user.getProfile().getProfilename());
		}
		codigoColumn.setCellValueFactory(new PropertyValueFactory<Movimento, Long>("id"));
		codFornecedorColumn.setCellValueFactory(new PropertyValueFactory<Movimento, Integer>("id_fornecedor"));
		codigoMedicamentoColumn.setCellValueFactory(new PropertyValueFactory<Movimento, String>("tipo"));
		dataColumn.setCellValueFactory(new PropertyValueFactory<Movimento, Date>("data"));
		validadeColumn.setCellValueFactory(new PropertyValueFactory<Movimento, Date>("data_validade"));
		quantidadeColumn.setCellValueFactory(new PropertyValueFactory<Medicamento, Integer>("quantidade"));
		
		//codigoMedicamentoColumn.setCellValueFactory(cod_medicamentoFactory);
		nomeColumn.setCellValueFactory(nome_medicamentoFactory);
		
		/*codigoMedicamentoColumn.setCellValueFactory(mov -> {
			Movimento movimentoAux=mov.getValue();
			Medicamento medicamento =movimentoAux.getMedicamento();
			
			return new SimpleStringProperty(medicamento.getId()+"");
		});*/
		
		nomeColumn.setCellValueFactory(mov -> {
			Movimento movimentoAux=mov.getValue();
			Medicamento medicamento =movimentoAux.getMedicamento();
			
			return new SimpleStringProperty(medicamento.getNome()+"");
		});
		
		registadorColumn.setCellValueFactory(mov -> {
			Movimento movimentoAux=mov.getValue();
			User usuario =movimentoAux.getRegistador();
			
			return new SimpleStringProperty(usuario.getName()+"");
		});
		
		pesquisar();

	}

	public void pesquisar() {
		
	}

	public void addMedicamento() {// fazer controle de permissoes
		User user = dataManager.findCurrentUser();
		if (user != null) {
			frameManager.addMedicamento(user);
			pesquisar();
		} else 
			AlertUtils.alertSemPrivelegio();
		
	}



	public void goHome() {
		Stage stage = (Stage) actualizarStock.getScene().getWindow();
		stage.close();
	}

	public void about() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Sobre esta janela");
		alert.setContentText("Esta janela tem objectivo de ajudar a visualizar todos "
				+ "os medicamentos gravados no sistema. Do lado direito da tela vai  encontrar um conjunto de filtros, "
				+ "preencha-os para uma busca mais refinada. Para sair desta tela, apenas prima OK ou "
				+ "no canto superior direito para tirar a janela. Nesta janela também é possível adicionar, modificar e remover um medicamento. "
				+ "Para voltar para janela principal, é necessário clicar no  HOME ou no x no canto superior a direita");
		alert.setHeaderText(null);
		alert.showAndWait();
	}

	public void enterKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER)
			pesquisar();
	}
	
	public void actualizarStock() {
		frameManager.updateStock(user);
	}

	
}
