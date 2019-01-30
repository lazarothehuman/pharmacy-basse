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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Fornecedor;
import mz.humansolutions.models.Medicamento;
import mz.humansolutions.utils.AlertUtils;

public class AddMedicamentoController implements Initializable {

	@FXML
	private TextField nomeTF = new TextField();

	@FXML
	private ComboBox<Fornecedor> comboFornecedor;

	@FXML
	private TextField precoTF = new TextField();

	@FXML
	private TextField paisTF = new TextField();

	@FXML
	private TextField codigoTf = new TextField();

	@FXML
	private TextField fabricanteTf = new TextField();

	@FXML
	private Button adicionar = new Button();
	
	@FXML
	private DatePicker prazo;

	DataManager dataManager = new DataManagerImp();

	public void add() {
		String selectedNome = nomeTF.getText();
		String stringPreco = precoTF.getText();
		double selectedPreco = 0;
		if (stringPreco != null)
			if (!stringPreco.isEmpty())
				selectedPreco = Double.parseDouble(stringPreco);

		String selectedPais = paisTF.getText();
		Fornecedor selectedFornecedor = comboFornecedor.getValue();
		String selectedFabricante = fabricanteTf.getText();
		boolean duplicate = false;
		String selectedCodigo = codigoTf.getText();
		LocalDate localDate = prazo.getValue();
		Date selectedPrazo = null;
		if (localDate != null) {
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			selectedPrazo = Date.from(instant);
		}

		if (selectedNome == null || selectedNome.isEmpty())
			AlertUtils.alertErro("Por favor insira um nome para o medicamento!", "Nome inválido", nomeTF);
		else if (selectedPreco <= 0)
			AlertUtils.alertErro("Por favor, insira um preço válido (>0)", "Preço inválido", precoTF);
		else {
			if (selectedCodigo == null || selectedCodigo.isEmpty())
				AlertUtils.alertErro("Peço que insira o código", "Insira o código", codigoTf);
			else {

				Medicamento medicamento = new Medicamento();
				medicamento.setNome(selectedNome);
				medicamento.setPrecoUnitario(selectedPreco);
				medicamento.setQuantidadeStock(0);
				medicamento.setPaisOrigem(selectedPais);
				medicamento.setFabricante(selectedFabricante);
				medicamento.setCodigo(selectedCodigo);
				medicamento.setPrazo(selectedPrazo);
				medicamento.setFornecedor(selectedFornecedor);
				selectedFornecedor.addMedicamento(medicamento);

				List<Medicamento> medicamentos = dataManager.findMedicamento(null, null, null, selectedNome, null, null,
						selectedCodigo, null);
				if (medicamentos != null) {
					for (Medicamento medicamentoAux : medicamentos) {
						if (medicamentoAux.getNome().equalsIgnoreCase(selectedNome)
								&& medicamentoAux.getFabricante().equalsIgnoreCase(selectedFabricante))
							duplicate = true;
					}
				}

				if (duplicate == false) {
					dataManager.createMedicamento(medicamento);
					AlertUtils.alertSucesso("Medicamento adicionado com sucesso");
				} else
					AlertUtils.alertErroSelecionar("O medicamento ja existe!");
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// comboFabricante.setItems(list);
		List<Fornecedor> forncedores = dataManager.findFornecedor(null, null, null, null, null, true);
		if (forncedores != null)
			comboFornecedor.setItems(FXCollections.observableArrayList(forncedores));
	}

}
