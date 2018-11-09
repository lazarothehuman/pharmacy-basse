package mz.humansolutions.tests;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.managers.NotificationManager;
import mz.humansolutions.managers.NotificationManagerImp;

public class NotificationManagerImpTest {

	NotificationManager notificationManager = new NotificationManagerImp();

	DataManager dataManager = new DataManagerImp();

	@Test
	public void sendSms() throws IOException {
		notificationManager.sendSms("+258825004957",
				"Caro camarada Maria Luisa, lembramos-lhe do seu dever de pagamento de quotas referentes nomemes o envio do comprovativo à sede da cidade. Caso já tenha efectuado o pagamento e o envio do comprovativo, pode ignorar esta mensagem. DAF Cidade de Maputo");
	}

	@Test
	public void sendSmsSislog() throws IOException {
		notificationManager.sendSmsNotificationSislog("+258825004957",
				"lazaro camarada Maria Luisa, lembramos-lhe do seu dever de pagamento de quotas referentes nomemes o envio do comprovativo à sede da cidade. Caso já tenha efectuado o pagamento e o envio do comprovativo, pode ignorar esta mensagem. DAF Cidade de Maputo");
	}

}
