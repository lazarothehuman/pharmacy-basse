package application.forms;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Cliente;
import mz.humansolutions.models.Fornecedor;
import mz.humansolutions.models.Medicamento;
import mz.humansolutions.models.MedicamentoParaVenda;
import mz.humansolutions.models.Movimento;
import mz.humansolutions.models.Tipo;
import mz.humansolutions.models.User;
import mz.humansolutions.utils.AlertUtils;
import mz.humansolutions.utils.FrameManager;

public class VendaController implements Initializable {

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
	
	@FXML
	private ComboBox<Cliente> comboClientes;
	
	Cliente cliente;

	DataManager dataManager = new DataManagerImp();

	FrameManager frameManager = new FrameManager();

	List<Cliente> clientes;
	
	List<Medicamento> listMedicamentos;
	List<MedicamentoParaVenda> listItems = new ArrayList<MedicamentoParaVenda>();
	Medicamento selectedMedicamento;
	User user;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		user = dataManager.findCurrentUser();

		if (user != null) {
			lblUser.setText(user.getName().toLowerCase());
			lblProfile.setText(user.getProfile().getProfilename());
		}
		
		clientes = dataManager.findClientes(null, null, null, null, null, null, null, true);
		if (clientes != null)
			comboClientes.setItems(FXCollections.observableArrayList(clientes));
		


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
		String nome = null;
		if (!pesquisaTf.getText().trim().isEmpty())
			nome = pesquisaTf.getText().trim();

		listMedicamentos = dataManager.findMedicamento(null, null, true, nome, null, null, null, null);

		if (listItems.size() != 0) {
			Medicamento medicamentoAux;
			for (int i = 0; i < listItems.size(); i++) {
				medicamentoAux = listItems.get(i).getMedicamento();
				listMedicamentos.remove(medicamentoAux);
			}
		}
		tabListItems.setItems(FXCollections.observableArrayList(listMedicamentos));

	}

	public void listToFields() {
		selectedMedicamento = tabListItems.getSelectionModel().getSelectedItem();
		retirarB.setDisable(true);
		adicionarB.setDisable(false);
		if (selectedMedicamento != null) {
			medicamentoTf.setText(selectedMedicamento.getNome());
			precoTf.setText(selectedMedicamento.getPrecoUnitario() + "");
			stockLb.setText(selectedMedicamento.getQuantidadeStock() + "");
		}
	}

	public void selectedToFields() {
		MedicamentoParaVenda selectedItem = tabItems.getSelectionModel().getSelectedItem();
		adicionarB.setDisable(true);
		retirarB.setDisable(false);
		if (selectedItem != null) {
			medicamentoTf.setText(selectedItem.getNome());
			precoTf.setText(selectedItem.getPrecoUnitario() + "");
			quantidadeTf.setText(selectedItem.getQuantidade() + "");
			stockLb.setText(selectedItem.getMedicamento().getQuantidadeStock() + "");
		}
	}

	public void submeter() {
		Medicamento medicamento;
		Movimento movimento;
		cliente=comboClientes.getValue();
		if(listItems.size()!=0 ) {
			if(cliente!=null) {
				movimento = new Movimento();
				movimento.setTipo(Tipo.SAIDA);
				movimento.setCliente(cliente);
				movimento.setDataRealizacao(Calendar.getInstance().getTime());
				movimento.setRegistador(dataManager.findCurrentUser());
				for (MedicamentoParaVenda med : listItems) {

					med.getMedicamento().removeFromStock(med.getQuantidade());
					movimento.addMedicamento(med.getMedicamento());

				}

		for (MedicamentoParaVenda med : listItems) {
			movimento = new Movimento();
			movimento.setTipo(Tipo.SAIDA);
			med.getMedicamento().removeFromStock(med.getQuantidade());
			movimento.addMedicamento(med.getMedicamento());
			movimento.setDataRealizacao(new Date());
			// movimento.setCliente(1); sair uma janela para adicionar cliente antes do for
			movimento.setRegistador(dataManager.findCurrentUser());
			medicamento = med.getMedicamento();
			if (med.getQuantidade() >= medicamento.getQuantidadeStock())
				AlertUtils.alertErroSelecionar("Quantidade desejada não disponível");
			else
				dataManager.createMovimento(movimento);
			
				AlertUtils.alertSucesso("Operação concluída com sucesso");
				refresh();
			}
			else {
				AlertUtils.alertErro("Por favor selecione um cliente ou registe um novo.", "Cliente não selecionado", comboClientes);
			}
		}
		else {
			AlertUtils.alertErro("Não possui nenhum medicamento na lista desta venda.", "Medicamentos não selecioados", null);
		}
	}

	public void adicionar() {
		try {
			int qtd = Integer.parseInt(quantidadeTf.getText().trim());
			int stock = Integer.parseInt(stockLb.getText());

			if (qtd <= 0 || qtd > stock)
				AlertUtils.alertErro("Por favor insira uma quantidade válida!", "Quantidade inválida", quantidadeTf);
			else {

				MedicamentoParaVenda novo = new MedicamentoParaVenda();
				novo.setCodigo(selectedMedicamento.getId());
				novo.setNome(medicamentoTf.getText());
				novo.setPrecoUnitario(Double.parseDouble(precoTf.getText()));
				novo.setQuantidade(qtd);
				novo.setTotal(novo.getPrecoUnitario() * qtd);
				novo.setMedicamento(selectedMedicamento);
				listItems.add(novo);
				listMedicamentos.remove(selectedMedicamento);
				refresh();
			}
		} catch (NumberFormatException e) {
			quantidadeTf.setText("1");
			AlertUtils.alertErro("Por favor insira uma quantidade válida!", "Quantidade inválida", quantidadeTf);
	
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
		int posicao = tabItems.getSelectionModel().getSelectedIndex();
		if (posicao >= 0) {
			listMedicamentos.add(listItems.get(posicao).getMedicamento());
			listItems.remove(posicao);
			refresh();
		}
	}

	public void valores() {
		double subtotal = 0, desconto = 0;

		if (listItems.size() > 0) {
			for (MedicamentoParaVenda med : listItems) {
				subtotal += med.getTotal();
			}
			try {
				desconto = Double.parseDouble(descontoTf.getText());
			} catch (NumberFormatException e) {
				descontoTf.setText("0");
				valores();
			}
			subTf.setText(subtotal + "");
			totalTf.setText(subtotal - desconto + "");
		} else {
			subTf.setText("0");
			descontoTf.setText("0");
			totalTf.setText("0");
		}
	}

	public void novoCliente() {
		String nome,telefone;
		TextInputDialog dialog;
		dialog = new TextInputDialog("");
		dialog.setTitle("Adicionar cliente");
		dialog.setHeaderText(null);
		dialog.setContentText("Por favor insira o nome do cliente:");
		Optional<String> result = dialog.showAndWait();
		
		if (result.isPresent()){
		    nome=result.get();
		    dialog = new TextInputDialog("");
		    dialog.setTitle("Adicionar cliente");
		    dialog.setHeaderText(null);
		    dialog.setContentText("Por favor insira o telefone do cliente:");
			result = dialog.showAndWait();
			if (result.isPresent()){
			    telefone=result.get();
			    Cliente novoCliente=new Cliente();
			    novoCliente.setNome(nome);
			    novoCliente.setTelefone(telefone);
			    novoCliente.setDataNascimento(Calendar.getInstance().getTime());
			    //novoCliente.setSexo(new Comb);
			    dataManager.createCliente(novoCliente);
			    cliente=dataManager.findClientes(null, novoCliente.getNome(), null, novoCliente.getTelefone(), null, null, null, null).get(0);
			    clientes.add(cliente);
			    comboClientes.setItems(FXCollections.observableArrayList(clientes));
			    comboClientes.getSelectionModel().select(cliente);
			}
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
