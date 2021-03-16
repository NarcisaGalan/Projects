package businessLayer;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import models.Order;

public class FacturaBll {

	ProductBll productBll;
	OrderBll orderBll;

	public FacturaBll() {
		productBll = new ProductBll();
		orderBll = new OrderBll();
	}

	public void printFactura(int idOrder) {
		Order order = orderBll.findById(idOrder);
		productBll = new ProductBll();
		int price = productBll.getPrice(order.getProduct());
		Document document = new Document();

		try {
			PdfWriter.getInstance(document,
					new FileOutputStream("facturi/factura-" + order.getIdOrder() + "-" + order.getClient() + ".pdf"));
			document.open();
			document.add(new Paragraph("Factura comanda " + order.getIdOrder()));
			document.add(new Paragraph("Nume cumparator: " + order.getClient()));
			document.add(new Paragraph("Produs:" + order.getProduct()));
			document.add(new Paragraph("Cantitate: " + order.getQuantity()));
			document.add(new Paragraph("Pret total: " + order.getQuantity() * price));
			document.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
