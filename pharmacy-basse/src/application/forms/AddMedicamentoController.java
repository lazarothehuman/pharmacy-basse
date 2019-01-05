package application.forms;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Medicamento;
import mz.humansolutions.utils.AlertUtils;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class AddMedicamentoController implements Initializable {

	@FXML
	private TextField nomeTF = new TextField();

	@FXML
	private ComboBox<String> comboFabricante;

	ObservableList<String> list = FXCollections.observableArrayList("fabricante 1", "fabricante 2");

	@FXML
	private TextField precoTF = new TextField();

	@FXML
	private TextField paisTF = new TextField();
	
	@FXML
	private TextField codigoTf = new TextField();

	@FXML
	private Button adicionar = new Button();

	DataManager dataManager = new DataManagerImp();

	public void add() {
		Alert alert;
		String nome = nomeTF.getText().toString();
		double preco = Double.parseDouble(precoTF.getText().toString());
		String pais = paisTF.getText().toString();
		String fabricante = comboFabricante.getSelectionModel().getSelectedItem();
		boolean duplicate = false;
		String codigo = codigoTf.getText();

		if (nome == null || nome.isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Por favor insira um nome para o medicamento!");
			alert.setTitle("Nome inválido");
			alert.showAndWait();
			nomeTF.setStyle("-fx-border-color:#ff0000;");
		} else if (preco == 0.0) {
			alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Por favor insira um preco valido!");
			alert.setTitle("Preco inválido");
			alert.showAndWait();
			precoTF.setStyle("-fx-border-color:#ff0000;");
		} else if (pais == null || pais.isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Por favor insira o pais de origem deste medicamento!");
			alert.setTitle("Pais inválido");
			alert.showAndWait();
			paisTF.setStyle("-fx-border-color:#ff0000;");
		} else {
			if (codigo == null || codigo.isEmpty()) {
				alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("Por favor insira o pais de origem deste medicamento!");
				alert.setTitle("Pais inválido");
				alert.showAndWait();
				codigoTf.setStyle("-fx-border-color:#ff0000;");
			} else {
				Medicamento medicamento = new Medicamento();
				medicamento.setNome(nome);
				medicamento.setPrecoUnitario(preco);
				medicamento.setQuantidadeStock(0);
				medicamento.setPaisOrigem(pais);
				medicamento.setFabricante(fabricante);
				medicamento.setCodigo(codigo);

				List<Medicamento> medicamentos = dataManager.findMedicamento(null, fabricante, null, nome, null, null,
						codigo,null);
				if (medicamentos != null) {
					for (Medicamento medicamentoAux : medicamentos) {
						if (medicamentoAux.getNome().equalsIgnoreCase(nome)
								&& medicamentoAux.getFabricante().equalsIgnoreCase(fabricante))
							duplicate = true;
					}
				}

				if (duplicate == false) {
					dataManager.createMedicamento(medicamento);

					AlertUtils.alertSucesso("Medicamento adicionado com sucesso");
					
				} else {
					alert = new Alert(AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setContentText("O medicamento ja existe!");
					alert.setTitle("Erro");
					alert.showAndWait();
				}
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		comboFabricante.setItems(list);
		precoTF.setText("0.0");
	}

}
