package application.views;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Profile;
import mz.humansolutions.models.User;
import mz.humansolutions.utils.FrameManager;

public class CaixaMainController implements Initializable {

	@FXML
	AnchorPane ContentPane;


	@FXML
	JFXButton registar;

	@FXML
	JFXButton visualizarMedicamento;


	AnchorPane content;

	FrameManager frameManager = new FrameManager();
	DataManagerImp dataManager = new DataManagerImp();
	User user;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// por content como venda
		user = dataManager.findCurrentUser();
		if (content != null) {
			setContent(content);
		}
	}


	public void viewMedicamento() {
		AnchorPane content = frameManager.searchMedicamento(user);
		setContent(content);
	}

	public void entrada() {
		AnchorPane content = frameManager.updateStock(user);
		setContent(content);
	}

	public void venda() {
		AnchorPane content = frameManager.venda(user);
		setContent(content);
	}


	public void addMedicamento() {
		AnchorPane content = frameManager.addMedicamento(user);
		setContent(content);
	}
	
	public void logout() {
		frameManager.login();
		Stage stage = (Stage) ContentPane.getScene().getWindow();
		stage.close();
	}

	public void setContent(AnchorPane content) {
		if (content != null) {
			ContentPane.setTopAnchor(content, 0.0);
			ContentPane.setLeftAnchor(content, 0.0);
			ContentPane.setBottomAnchor(content, 0.0);
			ContentPane.setRightAnchor(content, 0.0);
			ContentPane.getChildren().setAll(content);
		}
	}
	
	public void home() {
		AnchorPane content = frameManager.venda(user);
		setContent(content);
	}

	public void refreshContent() {
		if (content != null)
			setContent(content);

	}

	public void setContentFromOtherView(AnchorPane content) {
		if (content != null)
			this.content = content;

	}
}
