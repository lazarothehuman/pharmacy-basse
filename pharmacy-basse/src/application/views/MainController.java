package application.views;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.managers.NotificationManager;
import mz.humansolutions.managers.NotificationManagerImp;
import mz.humansolutions.models.User;
import mz.humansolutions.utils.FrameManager;

public class MainController implements Initializable {

	User user;
	
	DataManager dataManager = new DataManagerImp();

	FrameManager frameManager = new FrameManager();

	NotificationManager notifcationManager = new NotificationManagerImp();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		user = dataManager.findCurrentUser();
		//System.out.println("User:" +user.getName());
	}
	
	public void addMedicamento(){
		frameManager.addMedicamento(user);
	}
	
	public void addMovimento() {
		frameManager.updateStock(user);
	}

}
