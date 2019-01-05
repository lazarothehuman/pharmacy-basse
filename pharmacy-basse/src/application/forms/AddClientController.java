package application.forms;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Cliente;
import mz.humansolutions.models.Sexo;
import mz.humansolutions.utils.AlertUtils;
import mz.humansolutions.utils.Validations;

public class AddClientController implements Initializable {

	@FXML
	private TextField nameTF = new TextField();

	@FXML
	private TextField emailTF = new TextField();

	@FXML
	private TextField phoneTF = new TextField();

	@FXML
	private DatePicker dateNasc = new DatePicker(LocalDate.now());

	@FXML
	private ComboBox<Sexo> comboSex;

	@FXML
	private Label nome_lbl = new Label();

	@FXML
	private Label sexo_lbl = new Label();

	@FXML
	private Label data_lbl = new Label();

	@FXML
	private TextField naturalidadeTf = new TextField();

	@FXML
	private TextField moradaTf = new TextField();

	DataManager dataManager = new DataManagerImp();

	public void add() {
		// needs to catch duplicates
		String nome = nameTF.getText();
		String email = emailTF.getText();
		String telefone = phoneTF.getText();
		String naturalidade = naturalidadeTf.getText();
		String morada = moradaTf.getText();
		Sexo sexo = comboSex.getSelectionModel().getSelectedItem();
		LocalDate localDate = dateNasc.getValue();
		Date birthDate = null;
		if (localDate != null) {
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			birthDate = Date.from(instant);
		}
		if (nome != null && birthDate != null && sexo != null) {
			Cliente cliente = new Cliente();
			cliente.setNome(nome);
			cliente.setDataNascimento(birthDate);
			cliente.setSexo(sexo);
			if (naturalidade != null)
				cliente.setNaturalidade(naturalidade);
			if (morada != null)
				cliente.setMorada(morada);
			if (email != null && !email.isEmpty()) {
				boolean valid = Validations.isValidForEmailNotification(email);
				if (valid) {
					cliente.setEmail(email);
				}
			}
			if (telefone != null && !telefone.isEmpty()) {
				boolean valid = Validations.isValidForSMSNotification(telefone);
				if (valid) {
					cliente.setTelefone(telefone);
				}else {
					AlertUtils.displayWarning("Número de telefone inserido não é válido para notificação por mensagem");
				}
			}

			dataManager.createCliente(cliente);
			AlertUtils.alertSucesso("Cliente inserido com sucesso");

		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comboSex.setItems(FXCollections.observableArrayList(Sexo.values()));
		Tooltip tool = new Tooltip("Campo não pode estar vazio");
		tool.setStyle("-fx-background-color: #FF0000;");
		nome_lbl.setTooltip(tool);
		sexo_lbl.setTooltip(tool);
		data_lbl.setTooltip(tool);

	}

}
