package application.views;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.scene.input.MouseEvent;
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

public class ViewMedicamentosDoMovimentoController implements Initializable {

	// put timer to refresh
	
	@FXML
	AnchorPane ContentPane;

	@FXML
	Button pesquisar;

	@FXML
	Button adicionarMedicamento;

	@FXML
	Button modificarUser;

	@FXML
	Button removerUser;

	@FXML
	TextField codigoTf = new TextField();

	@FXML
	TextField nomeTf = new TextField();

	@FXML
	TextField paisTf = new TextField();



	@FXML
	CheckBox active = new CheckBox();

	@FXML
	TableView<Medicamento> tableMedicamento;

	@FXML
	TableColumn<Medicamento, String> nomeColumn;

	@FXML
	TableColumn<Medicamento, String> fabricanteColumn;

	@FXML
	TableColumn<Medicamento, String> origemColumn;

	@FXML
	TableColumn<Medicamento, Double> precoColumn;

	@FXML
	TableColumn<Medicamento, Integer> quantidadeColumn;

	@FXML
	TableColumn<Medicamento, Long> codigoColumn;

	@FXML
	Label lblTotal = new Label();
	
	@FXML
	Label lblTotal2 = new Label();
	
	

	DataManager dataManager = new DataManagerImp();

	FrameManager frameManager = new FrameManager();

	User user;

	List<Medicamento> medicamentos;
	Movimento movimento;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		user = dataManager.findCurrentUser();
		codigoColumn.setCellValueFactory(new PropertyValueFactory<Medicamento, Long>("id"));
		nomeColumn.setCellValueFactory(new PropertyValueFactory<Medicamento, String>("nome"));
		fabricanteColumn.setCellValueFactory(new PropertyValueFactory<Medicamento, String>("fabricante"));
		origemColumn.setCellValueFactory(new PropertyValueFactory<Medicamento, String>("paisOrigem"));
		precoColumn.setCellValueFactory(new PropertyValueFactory<Medicamento, Double>("precoUnitario"));
		quantidadeColumn.setCellValueFactory(new PropertyValueFactory<Medicamento, Integer>("quantidadeStock"));

	}

	public void pesquisar() {
		Long id = null;
		String nome = null;
		String paisOrigem = null;
		if (!codigoTf.getText().trim().isEmpty())
			id = Long.parseLong(codigoTf.getText());
		if (!nomeTf.getText().trim().isEmpty())
			nome = nomeTf.getText();
		if (!paisTf.getText().trim().isEmpty())
			paisOrigem = paisTf.getText();
		Boolean activee = Boolean.valueOf(!active.isSelected());
		medicamentos = movimento.getMedicamentos();
		if (medicamentos != null ) {
			tableMedicamento.setItems(FXCollections.observableArrayList(medicamentos));
			lblTotal.setText(medicamentos.size() + "");
			lblTotal2.setText(calculaTotal()+"");
		} else {
			AlertUtils.pesquisaVazia();
			tableMedicamento.setItems(null);
		}
	}

	public void addMedicamento() {// fazer controle de permissoes
		AnchorPane content = frameManager.addMedicamento(dataManager.findCurrentUser());
		setContent(content);
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


	private void refreshItems() {
		pesquisar();

	}

	public void goHome() {
		Stage stage = (Stage) adicionarMedicamento.getScene().getWindow();
		stage.close();
	}

	public void about() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Sobre esta janela");
		alert.setContentText("Esta janela tem objectivo de ajudar a visualizar todos "
				+ "os medicamentos gravados no sistema. Do lado direito da tela vai  encontrar um conjunto de filtros, "
				+ "preencha-os para uma busca mais refinada. Para sair desta tela, apenas prima OK ou "
				+ "no canto superior direito para tirar a janela. Nesta janela tamb�m � poss�vel adicionar, modificar e remover um medicamento. "
				+ "Para voltar para janela principal, � necess�rio clicar no  HOME ou no x no canto superior a direita");
		alert.setHeaderText(null);
		alert.showAndWait();
	}


	public void enterKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER)
			pesquisar();
	}
	
	public void setMovimento(Movimento movimento) {
		this.movimento=movimento;
		refreshItems();
	}
	
	public double calculaTotal() {
		double total=0;
		for(Medicamento med:medicamentos) {
			total+=med.getQuantidadeStock()*med.getPrecoUnitario();
		}
		return total;
	}

}
