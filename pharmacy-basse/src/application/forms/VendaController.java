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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Medicamento;
import mz.humansolutions.models.MedicamentoParaVenda;
import mz.humansolutions.models.Movimento;
import mz.humansolutions.models.User;
import mz.humansolutions.utils.AlertUtils;
import mz.humansolutions.utils.FrameManager;

public class VendaController implements Initializable{

	
	@FXML
	TextField pesquisaTf;
	
	@FXML
	TextField medicamentoTf;
	
	@FXML
	TextField quantidadeTf;
	
	@FXML
	TextField precoTf;
	
	@FXML
	TextField subTf;
	
	@FXML
	TextField descontoTf;
	
	@FXML
	TextField totalTf;
	
	@FXML
	Button adicionarB;
	
	@FXML
	Button retirarB;
	
	@FXML
	Button cancelarB;
	
	@FXML
	Button submeterB;
	
	@FXML
	TableView<MedicamentoParaVenda> tabItems;

	@FXML
	TableColumn<MedicamentoParaVenda, String> nomeColumn;
	
	@FXML
	TableColumn<MedicamentoParaVenda, Double> precoColumn;
	
	@FXML
	TableColumn<MedicamentoParaVenda, Integer> quantidadeColumn; 
	
	@FXML
	TableColumn<MedicamentoParaVenda, Double> totalColumn;
	
	@FXML
	TableView<Medicamento> tabListItems;
	
	@FXML
	TableColumn<Medicamento, String> medicamentosColumn;
	
	@FXML
	Label lblUser = new Label();

	@FXML
	Label stockLb;
	
	@FXML
	Label lblProfile = new Label();
	
	DataManager dataManager = new DataManagerImp();

	FrameManager frameManager = new FrameManager();
	
	List<Medicamento> listMedicamentos;
	List<MedicamentoParaVenda> listItems = new ArrayList<MedicamentoParaVenda>();
	Medicamento selectedMedicamento;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		User user = dataManager.findCurrentUser();
		if (user != null) {
			lblUser.setText(user.getName().toLowerCase());
			lblProfile.setText(user.getProfile().getProfilename());
		}
		
		subTf.setText("0");
		descontoTf.setText("0");
		totalTf.setText("0");
		
		subTf.setEditable(false);
		totalTf.setEditable(false);
		medicamentoTf.setEditable(false);
		precoTf.setEditable(false);
		
		quantidadeColumn.setCellValueFactory(new PropertyValueFactory<MedicamentoParaVenda, Integer>("quantidade"));
		precoColumn.setCellValueFactory(new PropertyValueFactory<MedicamentoParaVenda, Double>("precoUnitario"));
		nomeColumn.setCellValueFactory(new PropertyValueFactory<MedicamentoParaVenda, String>("nome"));
		totalColumn.setCellValueFactory(new PropertyValueFactory<MedicamentoParaVenda, Double>("total"));
		
		medicamentosColumn.setCellValueFactory(new PropertyValueFactory<Medicamento, String>("nome"));
		refresh();
		
	}

	public void pesquisar() {
		String nome=null;
		if(!pesquisaTf.getText().trim().isEmpty())
			nome=pesquisaTf.getText().trim();
		if(listMedicamentos==null)
		listMedicamentos = dataManager.findMedicamento(null, null, true, nome, null, null, null,null);
		tabListItems.setItems(FXCollections.observableArrayList(listMedicamentos));
		
	}
	
	public void listToFields() {
		selectedMedicamento = tabListItems.getSelectionModel().getSelectedItem();
		retirarB.setDisable(true);
		adicionarB.setDisable(false);
		if(selectedMedicamento!=null) {
			medicamentoTf.setText(selectedMedicamento.getNome());
			precoTf.setText(selectedMedicamento.getPrecoUnitario()+"");
			stockLb.setText(selectedMedicamento.getQuantidadeStock()+"");
		}
	}
	
	public void selectedToFields() {
		MedicamentoParaVenda selectedItem=tabItems.getSelectionModel().getSelectedItem();
		adicionarB.setDisable(true);
		retirarB.setDisable(false);
		if(selectedItem!=null) {
			medicamentoTf.setText(selectedItem.getNome());
			precoTf.setText(selectedItem.getPrecoUnitario()+"");
			quantidadeTf.setText(selectedItem.getQuantidade()+"");
			stockLb.setText(selectedItem.getMedicamento().getQuantidadeStock()+"");
		}
	}
	
	public void submeter() {
		Medicamento aux;
		Movimento movimento;
		
		Instant instant = Instant.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()));
		Date dataNow = Date.from(instant);
		
		for(MedicamentoParaVenda med:listItems){
			
			movimento=new Movimento();
			movimento.setTipo("saida");
			movimento.setMedicamento(med.getMedicamento());
			movimento.setData(dataNow);
			movimento.setData_validade(dataNow);
			movimento.setId_fornecedor(1);
			movimento.setQuantidade(med.getQuantidade());
			movimento.setIdCliente(1);
			movimento.setRegistador(dataManager.findCurrentUser());
			
			dataManager.createMovimento(movimento);
			
			aux=med.getMedicamento();
			aux.setQuantidadeStock(aux.getQuantidadeStock()-med.getQuantidade());
			dataManager.updateMedicamento(aux);
		}
		
		AlertUtils.alertSucesso("Operação concluída com sucesso");
		refresh();
	}
	
	public void adicionar() {
		Alert alert;
		try {
			int qtd=Integer.parseInt(quantidadeTf.getText().trim());
			int stock=Integer.parseInt(stockLb.getText());
			
			if(qtd<=0 || qtd>stock) {
				alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("Por favor insira uma quantidade válida!");
				alert.setTitle("Quantidade inválida");
				alert.showAndWait();
				quantidadeTf.setStyle("-fx-border-color:#ff0000;");
				quantidadeTf.requestFocus();
			}
			else {
				
				MedicamentoParaVenda novo=new MedicamentoParaVenda();
				novo.setCodigo(selectedMedicamento.getId());
				novo.setNome(medicamentoTf.getText());
				novo.setPrecoUnitario(Double.parseDouble(precoTf.getText()));
				novo.setQuantidade(qtd);
				novo.setTotal(novo.getPrecoUnitario()*qtd);
				novo.setMedicamento(selectedMedicamento);
				listItems.add(novo);
				
				listMedicamentos.remove(selectedMedicamento);				
				refresh();
			}
		}catch(NumberFormatException e) {
			quantidadeTf.setText("1");
			alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Por favor insira uma quantidade válida!");
			alert.setTitle("Quantidade inválida");
			alert.showAndWait();
			quantidadeTf.setStyle("-fx-border-color:#ff0000;");
			quantidadeTf.requestFocus();
		}
	}
	
	private void refresh() {
		tabItems.setItems(FXCollections.observableArrayList(listItems));
		medicamentoTf.setText("");
		precoTf.setText("");
		quantidadeTf.setText("");
		stockLb.setText("0");
		pesquisar();
		valores();
	}

	public void retirarMedicamento() {
		int posicao=tabItems.getSelectionModel().getSelectedIndex();
		if(posicao>=0) {
			listMedicamentos.add(listItems.get(posicao).getMedicamento());
			listItems.remove(posicao);
			refresh();
		}
	}
	
	public void valores() {
		double subtotal=0,desconto=0;
		
		if(listItems.size()>0) {
			for(MedicamentoParaVenda med: listItems) {
				subtotal+=med.getTotal();
			}
			try {
				desconto=Double.parseDouble(descontoTf.getText());
			}catch(NumberFormatException e) {
				descontoTf.setText("0");
				valores();
			}
			subTf.setText(subtotal+"");
			totalTf.setText(subtotal-desconto+"");
		}
		else {
			subTf.setText("0");
			descontoTf.setText("0");
			totalTf.setText("0");
		}
	}
	
	public void enterKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER)
			pesquisar();
	}
	
	public void enterKeyPressedValores(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER)
			valores();
	}

}
