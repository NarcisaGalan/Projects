package utcn.ti.proiect_licenta.service;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.w3c.dom.*;
import utcn.ti.proiect_licenta.model.CursValutar;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {

    public List<CursValutar> parseXMLCursValutar(){
        List<CursValutar> cursValutarList = new ArrayList<>();

        try {
            //sursa xml
            URL u = new URL(
                    "https://www.bnr.ro/nbrfxrates.xml");
            HttpsURLConnection http = (HttpsURLConnection) u.openConnection();
            http.setSSLSocketFactory(createSSLContext().getSocketFactory());
            http.setAllowUserInteraction(true);
            http.setRequestMethod("GET");
            http.connect();


            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setNamespaceAware(false);
            dbFactory.setValidating(false);
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(http.getInputStream());
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("Rate");


            String dataCursValutarText = "0000-00-00";
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    if(temp == 0){
                        Element cubeElement =(Element) nNode.getParentNode();
                        System.out.println(cubeElement.getTagName());
                        System.out.println(cubeElement.getAttribute("date"));
                        dataCursValutarText = cubeElement.getAttribute("date");
                    }

                    String moneda = eElement.getAttribute("currency");
                    Double valoare =Double.parseDouble(eElement.getTextContent());
                    Date dataCursValutar=Date.valueOf(dataCursValutarText);

                    CursValutar cursValutar = new CursValutar(moneda,valoare,dataCursValutar);
                    cursValutarList.add(cursValutar);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cursValutarList;
    }

    private SSLContext createSSLContext() throws Exception {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        FileInputStream in = new FileInputStream("example.cer");
        KeyStore trustStore = KeyStore.getInstance("JKS");
        trustStore.load(null);
        try {
            X509Certificate cacert = (X509Certificate) cf.generateCertificate(in);
            trustStore.setCertificateEntry("ca", cacert);
        } finally {
            IOUtils.closeQuietly(in);
        }

        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(trustStore);

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, tmf.getTrustManagers(), new SecureRandom());
        return sslContext;
    }
}
