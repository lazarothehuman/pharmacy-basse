package application.forms;

import java.net.URL;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Medicamento;
import mz.humansolutions.models.Movimento;
import mz.humansolutions.models.Tipo;
import mz.humansolutions.utils.AlertUtils;

public class AddMovimentoController implements Initializable {

	// representa uma adicao de stock nos medicamentos

	@FXML
	ComboBox<Medicamento> comboMedicamento;

	@FXML
	ComboBox<Tipo> comboTipo;

	@FXML
	TextField quantidadeTf;

	List<Medicamento> listMedicamentos = new ArrayList<Medicamento>();

	DataManagerImp dataManager = new DataManagerImp();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listMedicamentos = dataManager.findMedicamento(null, null, true, null, null, null, null, null);
		if (listMedicamentos != null)
			comboMedicamento.setItems(FXCollections.observableArrayList(listMedicamentos));
		comboTipo.setItems(FXCollections.observableArrayList(Tipo.values()));
	}

	public void submeter() {
		Medicamento selectedMedicamento = comboMedicamento.getSelectionModel().getSelectedItem();
		Tipo selectedTipo = comboTipo.getSelectionModel().getSelectedItem();
		int quantidade = 0;
		try {
			quantidade = Integer.parseInt(quantidadeTf.getText());
		} catch (NumberFormatException e) {
			quantidadeTf.setText("0");
			quantidade = 0;
		}

		if (selectedMedicamento == null)
			AlertUtils.alertErro("Por favor selecione um medicamento!", "Medicamento inválido", comboMedicamento);
		else if (selectedTipo == null)
			AlertUtils.alertErro("Por favor selecione um tipo!", "Tipo inválido", comboTipo);
		else if (quantidade == 0)
			AlertUtils.alertErro("Por favor insira uma quantidade válida!", "Quantidade inválida", quantidadeTf);
		else {
			if (selectedMedicamento != null && selectedTipo != null && quantidade > 0) {

				Movimento movimento = new Movimento();
				movimento.setTipo(selectedTipo);
				if(selectedTipo.equals(Tipo.ENTRADA))
					selectedMedicamento.addToStock(quantidade);
				else
					selectedMedicamento.removeFromStock(quantidade);
				movimento.addMedicamento(selectedMedicamento);
				movimento.setDataRealizacao(Calendar.getInstance().getTime());
				movimento.setRegistador(dataManager.findCurrentUser());
				selectedMedicamento.addMovimento(movimento);
				dataManager.createMovimento(movimento);
				AlertUtils.alertSucesso("Operação concluída com sucesso");

			} else
				AlertUtils.alertErroInsercaoDados();

		}
	}
}
