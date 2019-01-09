package mz.humansolutions.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertUtils {

	public static void alertSemPrivelegio() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Sem privelegio");
		alert.setContentText("O usuario nao tem acesso a esta funcao do sistema.");
		alert.setHeaderText(null);
		alert.showAndWait();
		
	}

	public static void alertErroInsercaoDados() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erro na inserção de dados");
		alert.setContentText("Alguma das informações importantes, não estão certas");
		alert.setHeaderText(null);
		alert.showAndWait();
		
	}

	public static void alertSucesso(String string) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Adição com sucesso");
		alert.setContentText("O "+string+" foi adicionado com sucesso");
		alert.setHeaderText(null);
		alert.showAndWait();
		
	}

	public static void alertErroSelecionar(String string) {
		// TODO Auto-generated method stub
		
	}

	public static void pesquisaVazia() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Pesquisa voltou sem nenhum resultado");
		alert.setContentText("Pesquisa voltou sem nenhum resultado");
		alert.setHeaderText(null);
		alert.showAndWait();
		
	}



	public static void displayWarning(String message) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Número incorrecto");
		alert.setContentText(message);
		alert.setHeaderText(null);
		alert.showAndWait();
		
	}
	
	public static void displayInavailabity() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Função temporiaramente indisponível");
		alert.setContentText("Função temporiaramente indisponível, mas em breve ficará disponível");
		alert.setHeaderText(null);
		alert.showAndWait();
		
	}
	
	

}
