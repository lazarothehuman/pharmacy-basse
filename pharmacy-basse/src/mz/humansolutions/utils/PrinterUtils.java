package mz.humansolutions.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Mes;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class PrinterUtils {

	/*public static void main(String[] args) throws JRException, FileNotFoundException {
		print();
	}

	public static void print() throws JRException, FileNotFoundException {
		DataManager dataManager = new DataManagerImp();
		List<Membro> membros = dataManager.findMembros(Boolean.TRUE);
		Collection<Map<String,Object>> reportObjects  = new ArrayList<Map<String, Object>>();
		if (membros != null && !membros.isEmpty()) {
			for (Membro membro : membros) {
				Map<String, Object> reportObject = new HashMap<String, Object>();
				reportObject.put("nome", membro.getNome());
				for (Pagamento pagamento : membro.getPagamentos()) {
					if(pagamento.getMes()==Mes.Janeiro)
						reportObject.put("janPayment", pagamento.getValor());
				}
				reportObjects.add(reportObject);
			}
		}
		@SuppressWarnings("rawtypes")
		List list = (List) reportObjects;
		Map<String, Object> parameters = new HashMap<>();
		@SuppressWarnings("unchecked")
		JRMapCollectionDataSource mapCollectionDataSource = new JRMapCollectionDataSource(
				list);
		JasperReport jasper = JasperCompileManager.compileReport("MapsPayments.jrxml");
		JasperPrint jasperPrint = JasperFillManager.fillReport(
				jasper, parameters,
				mapCollectionDataSource);
		 JRExporter exporter = new JRPdfExporter();
	        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream("teste.pdf"));
	        exporter.exportReport();
		
	}*/
}
