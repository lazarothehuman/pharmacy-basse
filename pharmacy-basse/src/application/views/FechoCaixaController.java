package application.views;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import mz.humansolutions.system.SystemFunction;
import mz.humansolutions.system.managers.SystemManager;
import mz.humansolutions.system.managers.SystemManagerImp;

public class FechoCaixaController implements Initializable {

	SystemManager systemManager = new SystemManagerImp();
	
	@FXML
	AnchorPane ContentPane;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

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

	public void fechoCaixa() {
		SystemFunction fechoFunction = systemManager.getFunction("fecho");
		if (fechoFunction.getValue().equals("false")) {

		}

	}

}
