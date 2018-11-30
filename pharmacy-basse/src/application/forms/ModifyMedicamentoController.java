package application.forms;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.GeneralSecurityException;
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
import mz.humansolutions.models.User;
import mz.humansolutions.utils.AlertUtils;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class ModifyMedicamentoController implements Initializable{
	
	@FXML
	private TextField nomeTF = new TextField();
	
	@FXML
	private ComboBox<String> comboFabricante;
	
	ObservableList<String> list =FXCollections.observableArrayList("fabricante 1","fabricante 2");
	
	@FXML
	private TextField precoTF = new TextField();
	
	@FXML
	private TextField paisTF = new TextField();
	@FXML
	private TextField quantidadeTF = new TextField();
	@FXML
	private Button adicionar = new Button();
	
	private Medicamento medicamento;
	
	DataManager dataManager = new DataManagerImp();

	
	public void setMedicamento(Medicamento medicamento) {
		if (medicamento != null) {
			this.medicamento = medicamento;
			setValues();
		}
	}
	
	public void setValues() {
		if (this.medicamento != null) {
			nomeTF.setText(medicamento.getNome());
			comboFabricante.setValue(medicamento.getFabricante());
			paisTF.setText(medicamento.getPaisOrigem());
			precoTF.setText(medicamento.getPrecoUnitario()+"");
			quantidadeTF.setText(medicamento.getQuantidadeStock()+"");
		}
	}
	
	public void modify() throws UnsupportedEncodingException, GeneralSecurityException {
		Alert alert;
		String nome = nomeTF.getText().toString();
		double preco = Double.parseDouble(precoTF.getText().toString());
		String pais = paisTF.getText().toString();
		String fabricante =comboFabricante.getSelectionModel().getSelectedItem();
		int quantidade=Integer.parseInt(quantidadeTF.getText().toString());
		boolean duplicate = false;
		
		if(nome==null || nome.isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Por favor insira um nome para o medicamento!");
			alert.setTitle("Nome inválido");
			alert.showAndWait();
			nomeTF.setStyle("-fx-border-color:#ff0000;");
		}
		else if(preco==0.0) {
			alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Por favor insira um preco valido!");
			alert.setTitle("Preco inválido");
			alert.showAndWait();
			precoTF.setStyle("-fx-border-color:#ff0000;");
		}
		else if(pais==null || pais.isEmpty()) {
			alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Por favor insira o pais de origem deste medicamento!");
			alert.setTitle("Pais inválido");
			alert.showAndWait();
			paisTF.setStyle("-fx-border-color:#ff0000;");
		}
		else {
			medicamento.setNome(nome);
			medicamento.setPrecoUnitario(preco);
			medicamento.setQuantidadeStock(0);
			medicamento.setPaisOrigem(pais);
			medicamento.setFabricante(fabricante);
			medicamento.setQuantidadeStock(quantidade);
			
			dataManager.updateMedicamento(this.medicamento);
			
			AlertUtils.alertSucesso("Medicamento modificado com sucesso");
			Stage stage = (Stage) nomeTF.getScene().getWindow();
			stage.close();
		}
	}
	
	public void cancelar() {
		Stage stage = (Stage) nomeTF.getScene().getWindow();
		stage.close();
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		comboFabricante.setItems(list);
	}

}
