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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Medicamento;
import mz.humansolutions.models.Movimento;
import mz.humansolutions.utils.AlertUtils;

public class AddMovimentoController implements Initializable{

	@FXML
	private ComboBox<String> comboMedicamento;
	
	@FXML
	private ComboBox<String> comboFornecedor;
	
	@FXML
	private TextField quantidadeTf;
	
	@FXML
	private DatePicker dataValidade;
	
	
	ObservableList<String> obListFornecedores = FXCollections.observableArrayList("fornecedor 1", "fornecedor 2","fornecedor 3");
	ObservableList<String> obListMedicamentos=FXCollections.observableArrayList();
	
	List<Medicamento> listMedicamentos = new ArrayList<Medicamento>();

	DataManagerImp dataManager=new DataManagerImp();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listMedicamentos = dataManager.findMedicamento(null, null, true, null, null, null, null);
		System.out.println("SIze da lista="+listMedicamentos.size());
		if (listMedicamentos != null) {
			for (Medicamento medicamentoAux : listMedicamentos) {
				obListMedicamentos.add(medicamentoAux.getNome());
			}
			comboMedicamento.setItems(obListMedicamentos);
		}
		
		comboFornecedor.setItems(obListFornecedores);
	}
	
	public void submeter() {
		Alert alert;
		int medicamento = comboMedicamento.getSelectionModel().getSelectedIndex();
		int quantidade=0;
		try {
			quantidade = Integer.parseInt(quantidadeTf.getText().toString());
		}catch(NumberFormatException e) {
			quantidadeTf.setText("0");
			quantidade=0;
		}
		LocalDate localDate=dataValidade.getValue();
		
		Date dataNow=null,dataV=null;
		int fornecedor = comboFornecedor.getSelectionModel().getSelectedIndex();
		if(medicamento==-1) {
			alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Por favor selecione um medicamento!");
			alert.setTitle("Medicamento inválido");
			alert.showAndWait();
			comboMedicamento.setStyle("-fx-border-color:#ff0000;");
			comboMedicamento.requestFocus();
		}
		else if(fornecedor==-1) {
			alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Por favor selecione um fornecedor!");
			alert.setTitle("Fornecedor inválido");
			alert.showAndWait();
			comboFornecedor.setStyle("-fx-border-color:#ff0000;");
			comboFornecedor.requestFocus();
		}
		else if(quantidade==0) {
			alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Por favor insira uma quantidade válida!");
			alert.setTitle("Quantidade inválida");
			alert.showAndWait();
			quantidadeTf.setStyle("-fx-border-color:#ff0000;");
			quantidadeTf.requestFocus();
		}
		else if(localDate==null || localDate.equals(LocalDate.now())){
			alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Por favor insira uma data válida!");
			alert.setTitle("Data inválida");
			alert.showAndWait();
			dataValidade.setStyle("-fx-border-color:#ff0000;");
			dataValidade.requestFocus();
		}
		else {
			
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			dataV = Date.from(instant);
			
			instant = Instant.from(localDate.now().atStartOfDay(ZoneId.systemDefault()));
			dataNow = Date.from(instant);
			
			Movimento movimento=new Movimento();
			movimento.setTipo("entrada");
			movimento.setMedicamento(listMedicamentos.get(medicamento));
			movimento.setData(dataNow);
			movimento.setData_validade(dataV);
			movimento.setId_fornecedor(fornecedor);
			movimento.setQuantidade(quantidade);
			movimento.setIdCliente(1);
			movimento.setRegistador(dataManager.findCurrentUser());
			
			dataManager.addMovimento(movimento);
			
			Medicamento medicamentoAux=listMedicamentos.get(medicamento);
			medicamentoAux.setQuantidadeStock(medicamentoAux.getQuantidadeStock()+quantidade);
			
			dataManager.updateMedicamento(medicamentoAux);
			
			AlertUtils.alertSucesso("Operacao concluida com sucesso");
			Stage stage = (Stage) comboFornecedor.getScene().getWindow();
			stage.close();
			
		}
	}

}
