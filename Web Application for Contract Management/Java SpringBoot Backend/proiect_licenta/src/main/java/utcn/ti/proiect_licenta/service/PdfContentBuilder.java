package utcn.ti.proiect_licenta.service;


import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import utcn.ti.proiect_licenta.dto.ContractTertiDTO;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class PdfContentBuilder {

    private TemplateEngine templateEngine;

    @Autowired
    public PdfContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String buildRegisterMail(){
        Context context = new Context();

        return templateEngine.process("registerEmailBody", context);
    }

    public String buildContractTerti(ContractTertiDTO contractDto) {

        Context context = new Context();
        context.setVariable("data_contract", contractDto.getDataContract());
        context.setVariable("numar", contractDto.getNumarContract());

        return templateEngine.process("contract_terti", context);
    }

    public void generatePdfFromHtml(String html, String name) throws IOException  {
        HtmlConverter.convertToPdf(html, new FileOutputStream(name));
    }
}
