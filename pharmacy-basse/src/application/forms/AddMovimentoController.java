package application.forms;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Fornecedor;
import mz.humansolutions.models.Medicamento;
import mz.humansolutions.models.Movimento;
import mz.humansolutions.models.Tipo;
import mz.humansolutions.utils.AlertUtils;

public class AddMovimentoController implements Initializable {

	// representa uma adicao de stock nos medicamentos

	@FXML
	ComboBox<Medicamento> comboMedicamento;

	@FXML
	ComboBox<Fornecedor> comboFornecedor;

	@FXML
	TextField quantidadeTf;

	@FXML
	DatePicker dataValidade;

	List<Medicamento> listMedicamentos = new ArrayList<Medicamento>();

	DataManagerImp dataManager = new DataManagerImp();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listMedicamentos = dataManager.findMedicamento(null, null, true, null, null, null, null, null);
		if (listMedicamentos != null)
			comboMedicamento.setItems(FXCollections.observableArrayList(listMedicamentos));
		List<Fornecedor> fornecedores = dataManager.findFornecedor(null, null, null, null, null, true);
		if (fornecedores != null)
			comboFornecedor.setItems(FXCollections.observableArrayList(fornecedores));
	}

	public void submeter() {
		Alert alert;
		Medicamento selectedMedicamento = comboMedicamento.getSelectionModel().getSelectedItem();
		Fornecedor selectedFornecedor = comboFornecedor.getSelectionModel().getSelectedItem();
		int quantidade = 0;
		try {
			quantidade = Integer.parseInt(quantidadeTf.getText().toString());
		} catch (NumberFormatException e) {
			quantidadeTf.setText("0");
			quantidade = 0;
		}
		LocalDate localDate = dataValidade.getValue();

		if (selectedMedicamento == null) {
			AlertUtils.alertErro("Por favor selecione um medicamento!", "Medicamento inválido", comboMedicamento);
		} else if (selectedFornecedor == null) {
			AlertUtils.alertErro("Por favor selecione um fornecedor!", "Fornecedor inválido", comboFornecedor);
		} else if (quantidade == 0) {
			alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Por favor insira uma quantidade válida!");
			alert.setTitle("Quantidade inválida");
			alert.showAndWait();
			quantidadeTf.setStyle("-fx-border-color:#ff0000;");
			quantidadeTf.requestFocus();
		} else if (localDate == null || localDate.equals(LocalDate.now())) {
			alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Por favor insira uma data válida!");
			alert.setTitle("Data inválida");
			alert.showAndWait();
			dataValidade.setStyle("-fx-border-color:#ff0000;");
			dataValidade.requestFocus();
		} else {

			if (selectedMedicamento != null && selectedFornecedor != null && quantidade > 0) {
				Date dataNow = null;
				Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
				instant = Instant.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()));
				dataNow = Date.from(instant);

				Movimento movimento = new Movimento();
				movimento.setTipo(Tipo.ENTRADA);
				movimento.addMedicamento(selectedMedicamento);
				movimento.setDataRealizacao(dataNow);
				//movimento.setQuantidade(quantidade);
				movimento.setRegistador(dataManager.findCurrentUser());
				selectedMedicamento.addMovimento(movimento);
				selectedMedicamento.addToStock(quantidade);
				dataManager.createMovimento(movimento);
				AlertUtils.alertSucesso("Operação concluída com sucesso");
				/*
				 * Stage stage = (Stage) comboFornecedor.getScene().getWindow(); stage.close();
				 */
			}else {
				AlertUtils.alertErroInsercaoDados();
			}
		}
	}

}
