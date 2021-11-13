package utcn.ti.proiect_licenta.service;

import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import utcn.ti.proiect_licenta.model.*;
import utcn.ti.proiect_licenta.repository.MembruRepository;
import utcn.ti.proiect_licenta.repository.UniversitateRepository;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PdfContentBuilder {

    private TemplateEngine templateEngine;
    @Autowired
    UniversitateRepository universitateRepository;
    @Autowired
    MembruRepository membruRepository;


    @Autowired
    public PdfContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String buildContractTerti(ContractTerti contractTerti, Beneficiar beneficiar,String director) {
        Universitate universitate = universitateRepository.findByCodUniversitate("UTCN");
        Adresa adresaUniversitate = universitate.getAdresa();
        Adresa adresaBeneficiar =beneficiar.getAdresa();
        List<Banca> banciBeneficiar = new ArrayList<>(beneficiar.getBanci());
        Banca bancaBeneficiar = banciBeneficiar.get(0);
        Integer numarExemplareUniv = contractTerti.getNrExemplare() - contractTerti.getNrExemplareBeneficiar();
        Membru directorDMCDI = membruRepository.findByFunctie("Director DMCDI");

        Context context = new Context();
        context.setVariable("directorProiect", director);
        context.setVariable("numarContract", contractTerti.getNumar());
        context.setVariable("dataContract", contractTerti.getData());
        context.setVariable("taraUniv", adresaUniversitate.getTara());
        context.setVariable("judetUniv", adresaUniversitate.getJudet());
        context.setVariable("localitateUniv", adresaUniversitate.getLocalitate());
        context.setVariable("stradaUniv", adresaUniversitate.getStrada());
        context.setVariable("numarStradaUniv", adresaUniversitate.getDetaliiAdresaNr());
        context.setVariable("codPostalUniv", universitate.getCodPostal());
        context.setVariable("telefonUniv", universitate.getTelefon());
        context.setVariable("faxUniv", universitate.getFax());
        context.setVariable("codFiscalUniv", universitate.getCodFiscal());
        context.setVariable("reprezentant1Univ", universitate.getReprezentant1());
        context.setVariable("reprezentant2Univ", universitate.getReprezentant2());
        context.setVariable("denumireBeneficiar", beneficiar.getDenumire());
        context.setVariable("taraBeneficiar", adresaBeneficiar.getTara());
        context.setVariable("judetBeneficiar", adresaBeneficiar.getJudet());
        context.setVariable("localitateBeneficiar", adresaBeneficiar.getLocalitate());
        context.setVariable("stradaBeneficiar", adresaBeneficiar.getStrada());
        context.setVariable("numarStradaBeneficiar", adresaBeneficiar.getDetaliiAdresaNr());
        context.setVariable("telefonBeneficiar", beneficiar.getTelefon());
        context.setVariable("cifCui", beneficiar.getCifCui());
        context.setVariable("contBeneficiar", bancaBeneficiar.getContBancar());
        context.setVariable("reprezentantBeneficiar", beneficiar.getReprezentant());
        context.setVariable("functieRepBeneficiar", beneficiar.getFunctieReprezentant());
        context.setVariable("titluProiect", contractTerti.getTitluProiect());
        context.setVariable("valoareTotala", contractTerti.getValoare());
        context.setVariable("tva", contractTerti.getTva());
        context.setVariable("moneda", contractTerti.getMoneda());
        context.setVariable("dataInceput", contractTerti.getDataInceput());
        context.setVariable("numarPagini", contractTerti.getNrPagini());
        context.setVariable("numarExemplare", contractTerti.getNrExemplare());
        context.setVariable("exemplareBeneficiar", contractTerti.getNrExemplareBeneficiar());
        context.setVariable("exemplareUniv", numarExemplareUniv);
        context.setVariable("directorDMCDI", directorDMCDI.getNume());

        return templateEngine.process("contract_terti", context);
    }

    public byte[] generatePdfFromHtml(String html, String name) throws IOException  {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        HtmlConverter.convertToPdf(html, buffer);
        byte[] pdfAsBytes = buffer.toByteArray();


        return pdfAsBytes;
    }


}
