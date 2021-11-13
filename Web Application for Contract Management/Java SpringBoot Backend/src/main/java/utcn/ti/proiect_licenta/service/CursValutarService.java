package utcn.ti.proiect_licenta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.ti.proiect_licenta.model.CursValutar;
import utcn.ti.proiect_licenta.repository.CursValutarRepository;

import java.sql.Date;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class CursValutarService  {

    @Autowired
    private CursValutarRepository cursValutarRepository;

   public CursValutar findByDataAndMoneda(Date data, String moneda){
        return cursValutarRepository.findCursValutarByDataAndMoneda(data,moneda);
    }


    public Double schimbValutarInLei(Double valoareMoneda, Double suma){
        return  valoareMoneda * suma;
    }


    public Double procesareCursValutar(String moneda, Date dataCurenta, Double suma){

        if(moneda.equals("RON")){
            return suma;
        }

        //calculam ora curenta pentru zona specificata, deoarece BNR foloseste zona Europa/Bucharest in XML-ul de curs valutar
        LocalTime now = LocalTime.now( ZoneId.of( "Europe/Bucharest" ) );
        //luam 2 minute in plus pentru a fi siguri ca este curs pe site si nu citim in bucla
        LocalTime ora13 = LocalTime.parse( "13:02" );
        boolean dupaOra13 = now.isAfter( ora13 );

         Date dataCitire = dataCitireCursValutar(dataCurenta,dupaOra13);

        CursValutar cursValutarDB;

        if((cursValutarDB = cursValutarRepository.findCursValutarByDataAndMoneda(dataCitire,moneda)) != null){
            //daca avem curs valutar pentru data curenta sau data de ieri(daca este inainte de ora 13)
            System.out.println("CURS EXISTENT PENTRU DATA CERUTA");
            return schimbValutarInLei(cursValutarDB.getValoare(),suma);
        }else{
            //citim cursul valutar pus la dispozitie de BNR
            //si salvam in baza de date noul curs valutar
            System.out.println("APELEAZA XML ");
             XMLParser xmlParser = new XMLParser();
            List<CursValutar> cursValutarList ;
            cursValutarList =  xmlParser.parseXMLCursValutar();


            for (CursValutar cursValutarXML: cursValutarList) {
                System.out.println("Salvare curs valutar nou in baza de date" + cursValutarXML.getMoneda());
                cursValutarRepository.save(cursValutarXML);
            }

            //cautam in baza de date cu moneda cu data

            CursValutar cursValutarNou = cursValutarRepository.findCursValutarByDataAndMoneda(dataCitire,moneda);


            //transformam in lei
            return schimbValutarInLei(cursValutarNou.getValoare(),suma);
        }
    }

    public Date dataCitireCursValutar(Date dataCurenta,boolean dupaOra13){

        Date dataCitire;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dataCurenta);
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));

        //daca e luni inainte de ora 13
        if ((calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) && (!dupaOra13)){
            //se citeste cursul de vineri
            dataCitire=Date.valueOf(new Date(System.currentTimeMillis()-72*60*60*1000).toString());
            System.out.println("Este luni inainte de 13");
            return dataCitire;
        }

        //daca ora este inainte de ora 13, atunci BNR nu va avea curs pentru data curenta, va trebui sa folosim cursul cu data de ieri
        //sau daca este sambata
        if ((calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
                || (!dupaOra13)){
            //data de ieri
            dataCitire=Date.valueOf(new Date(System.currentTimeMillis()-24*60*60*1000).toString());
            System.out.println("Este ora inainte de 13 sau sambata");
            return dataCitire;
        }
        //daca este duminica se foloseste de vineri
        if ((calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)){
            //data de vineri
            dataCitire=Date.valueOf(new Date(System.currentTimeMillis()-48*60*60*1000).toString());

            System.out.println("Este duminica");
            return dataCitire;
        }

        return dataCurenta;

    }
}
