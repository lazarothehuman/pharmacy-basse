package application.views;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Movimento;
import mz.humansolutions.models.Tipo;
import mz.humansolutions.models.User;
import mz.humansolutions.utils.AlertUtils;
import mz.humansolutions.utils.FrameManager;

public class ViewMovimentoController implements Initializable {

	// put timer to refresh

	@FXML
	AnchorPane ContentPane;

	@FXML
	TextField idTf = new TextField();

	@FXML
	ComboBox<String> tipoCombo;

	@FXML
	CheckBox active = new CheckBox();

	@FXML
	DatePicker dataInicio;

	@FXML
	DatePicker dataFim;

	@FXML
	TableView<Movimento> tableMovimento;

	@FXML
	TableColumn<Movimento, Date> dataColumn;

	@FXML
	TableColumn<Movimento, String> medicamentoColumn;

	@FXML
	TableColumn<Movimento, String> tipoColumn;

	@FXML
	TableColumn<Movimento, String> registadorColumn;

	@FXML
	Label lblTotal = new Label();

	DataManager dataManager = new DataManagerImp();
	FrameManager frameManager = new FrameManager();

	List<Movimento> movimentosModelList = new ArrayList<Movimento>();

	PropertyValueFactory<Movimento, String> medicamentoProperty = new PropertyValueFactory<Movimento, String>(
			"medicamento");
	PropertyValueFactory<Movimento, String> registadorProperty = new PropertyValueFactory<Movimento, String>(
			"registador");

	User user;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		user = dataManager.findCurrentUser();

		ArrayList<String> tipoDummies = new ArrayList<>();
		for (Tipo tipo : Tipo.values())
			tipoDummies.add(tipo.name());
		tipoCombo.setItems(FXCollections.observableArrayList(tipoDummies));

		dataColumn.setCellValueFactory(new PropertyValueFactory<Movimento, Date>("dataRealizacao"));
		medicamentoColumn.setCellValueFactory(medicamentoProperty);
		tipoColumn.setCellValueFactory(new PropertyValueFactory<Movimento, String>("tipo"));
		registadorColumn.setCellValueFactory(registadorProperty);

		medicamentoColumn.setCellValueFactory(mov -> {
			Movimento movimentoAux = mov.getValue();
			return new SimpleStringProperty(movimentoAux.getMedicamentos().size() + "");
		});

		registadorColumn.setCellValueFactory(mov -> {
			Movimento movimentoAux = mov.getValue();
			User usuario = movimentoAux.getRegistador();

			return new SimpleStringProperty(usuario.getName() + "");
		});

	}

	public void pesquisar() {
		String id = idTf.getText();
		Long selectedId = null;
		if (id != null && !id.isEmpty())
			selectedId = Long.parseLong(id);
		String selectedType = tipoCombo.getValue();
		Tipo selectedTipo = null;
		LocalDate localDateFim = dataFim.getValue();
		LocalDate localDate = dataInicio.getValue();
		Date selectedStartDate = null;
		Date selectedEndDate = null;
		if (localDate != null) {
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			selectedStartDate = Date.from(instant);
		}
		if (localDateFim != null) {
			Instant instant = Instant.from(localDateFim.atStartOfDay(ZoneId.systemDefault()));
			selectedEndDate = Date.from(instant);
		}
		if (selectedType != null) {
			if (selectedType.charAt(0) == 'E')
				selectedTipo = Tipo.ENTRADA;
			else
				selectedTipo = Tipo.SAIDA;
		} else
			selectedType = null;

		movimentosModelList = dataManager.findMovimento(selectedId, selectedTipo, selectedStartDate, selectedEndDate,
				null, !active.isSelected());
		if (movimentosModelList != null)
			tableMovimento.setItems(FXCollections.observableArrayList(movimentosModelList));
	}

	public void addMedicamento() {// fazer controle de permissoes
		User user = dataManager.findCurrentUser();
		if (user != null) {
			frameManager.addMedicamento(user);
			pesquisar();
		} else
			AlertUtils.alertSemPrivelegio();

	}

	public void enterKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER)
			pesquisar();
	}

	public void actualizarStock() {
		frameManager.updateStock(user);
	}

}
