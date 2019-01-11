package application.forms;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Cliente;
import mz.humansolutions.models.Fornecedor;
import mz.humansolutions.models.Medicamento;
import mz.humansolutions.models.Sexo;
import mz.humansolutions.utils.AlertUtils;
import mz.humansolutions.utils.Validations;

public class AddFornecedorController implements Initializable {

	@FXML
	private TextField nameTF = new TextField();

	@FXML
	private TextField emailTF = new TextField();

	@FXML
	private TextField phoneTF = new TextField();

	@FXML
	private Label nome_lbl = new Label();

	@FXML
	private TextField moradaTf = new TextField();

	DataManager dataManager = new DataManagerImp();

	public void add() {
		// needs to catch duplicates
		String nome = nameTF.getText();
		String email = emailTF.getText();
		String telefone = phoneTF.getText();
		String endereco = moradaTf.getText();
		boolean duplicate = false;
		Alert alert;

		Fornecedor fornecedor = new Fornecedor();
		if (nome == null || nome.isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Por favor insira o nome do fornecedor!");
			alert.setTitle("Nome inv�lido");
			alert.showAndWait();
			nameTF.setStyle("-fx-border-color:#ff0000;");
			
			
		} else {
			List<Fornecedor> fornecedores = dataManager.findFornecedor(null, nome, null, null, null, null);
			fornecedor.setNome(nome);
			if (fornecedores != null) {
				for (Fornecedor fornecedorAux : fornecedores) {
					if (fornecedorAux.getNome().equalsIgnoreCase(nome))
						duplicate = true;
				}
			}
		}
		if (endereco == null || endereco.isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Por favor insira o endereco do fornecedor!");
			alert.setTitle("Endereco inv�lido");
			alert.showAndWait();
			moradaTf.setStyle("-fx-border-color:#ff0000;");
		}
			
		else {
			fornecedor.setEndereco(endereco);
		}
		if (email == null || email.isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Por favor insira o endereco de e-mail do fornecedor!");
			alert.setTitle("Endereco de e-mail inv�lido");
			alert.showAndWait();
			emailTF.setStyle("-fx-border-color:#ff0000;");
		}
		else {
			boolean valid = Validations.isValidForEmailNotification(email);
			if (valid) {
				fornecedor.setEmail(email);
			}
		}
		if (telefone == null && telefone.isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Por favor insira o n�mero de telefone do fornecedor!");
			alert.setTitle("N�mero de telefone inv�lido");
			alert.showAndWait();
			phoneTF.setStyle("-fx-border-color:#ff0000;");
		}
		else {
			boolean valid = Validations.isValidForSMSNotification(telefone);
			if (valid) {
				fornecedor.setTelefone(telefone);
			} else {
				AlertUtils.displayWarning("N�mero de telefone inserido n�o � v�lido para notifica��o por mensagem");
			}
		}

		if (duplicate == false) {
			dataManager.createFornecedor(fornecedor);

			AlertUtils.alertSucesso("Cliente inserido com sucesso");

		} else {
			alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("O fornecedor ja existe!");
			alert.setTitle("Erro");
			alert.showAndWait();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Tooltip tool = new Tooltip("Campo n�o pode estar vazio");
		tool.setStyle("-fx-background-color: #FF0000;");
		nome_lbl.setTooltip(tool);

	}

}
