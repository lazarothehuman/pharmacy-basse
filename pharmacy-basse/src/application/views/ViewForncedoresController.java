package application.views;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Fornecedor;
import mz.humansolutions.utils.AlertUtils;
import mz.humansolutions.utils.FrameManager;

public class ViewForncedoresController implements Initializable {

	@FXML
	AnchorPane ContentPane;

	@FXML
	TableView<Fornecedor> tableFornecedores;

	@FXML
	TextField emailTf = new TextField();

	@FXML
	TextField nomeTf = new TextField();

	@FXML
	TextField telefoneTf = new TextField();

	@FXML
	CheckBox active;

	@FXML
	TableColumn<Fornecedor, String> nomeColumn;

	@FXML
	TableColumn<Fornecedor, String> emailColumn;

	@FXML
	TableColumn<Fornecedor, String> telefoneColumn;

	@FXML
	TableColumn<Fornecedor, String> enderecoColumn;

	@FXML
	Label lblTotal = new Label();

	DataManager dataManager = new DataManagerImp();
	FrameManager frameManager = new FrameManager();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nomeColumn.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("nome"));
		emailColumn.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("email"));
		telefoneColumn.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("telefone"));
		enderecoColumn.setCellValueFactory(new PropertyValueFactory<Fornecedor, String>("endereco"));

	}

	public void pesquisar() {
		String selectedEmail = emailTf.getText();
		String selectedNome = nomeTf.getText();
		String selectedTelefone = telefoneTf.getText();
		List<Fornecedor> fornecedores = dataManager.findFornecedor(null, selectedNome, selectedTelefone, selectedEmail,
				null, !active.isSelected());
		if (fornecedores != null) {
			if (!fornecedores.isEmpty()) {
				tableFornecedores.setItems(FXCollections.observableArrayList(fornecedores));
				lblTotal.setText(fornecedores.size()+"");
			} else
				AlertUtils.pesquisaVazia();
		}
	}

	public void addFornecedor() {
		AnchorPane content = frameManager.addFornecedor(dataManager.findCurrentUser());
		setContent(content);

	}

	public void removeFornecedor() {

	}

	public void modifyFornecedor() {

	}

	@SuppressWarnings("static-access")
	public void setContent(AnchorPane content) {
		if (content != null) {
			ContentPane.setTopAnchor(content, 0.0);
			ContentPane.setLeftAnchor(content, 0.0);
			ContentPane.setBottomAnchor(content, 0.0);
			ContentPane.setRightAnchor(content, 0.0);
			ContentPane.getChildren().setAll(content);
		}

	}

}
