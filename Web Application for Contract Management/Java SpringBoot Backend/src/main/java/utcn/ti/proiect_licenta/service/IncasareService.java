package utcn.ti.proiect_licenta.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.ti.proiect_licenta.dto.IncasareDTO;
import utcn.ti.proiect_licenta.model.ContractTerti;
import utcn.ti.proiect_licenta.model.Incasare;
import utcn.ti.proiect_licenta.repository.ContractTertiRepository;
import utcn.ti.proiect_licenta.repository.IncasareRepository;
import utcn.ti.proiect_licenta.service.serviceInterface.IncasareServiceInterface;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class IncasareService implements IncasareServiceInterface {

    @Autowired
    private IncasareRepository repository;
    @Autowired
    private ContractTertiRepository contractTertiRepository;
    @Autowired
    private  CursValutarService cursValutarService;

    @Override
    public List<Incasare> findAll(){
        return this.repository.findAll();
    }

    public List<Incasare> findAllByContract(Integer idContract) {
        List<Incasare> incasareList=new ArrayList();
        Integer contractInvalid = 0;
        //daca contractul este valid, a fost selectat din interfata
        if(!idContract.equals(contractInvalid)){
            ContractTerti contractTerti=contractTertiRepository.findById(idContract).get();

            if((contractTerti.getIncasareLista()!=null)&&(!contractTerti.getIncasareLista().isEmpty()) ){
                Set<Incasare> incasari = contractTerti.getIncasareLista();
                return  new ArrayList<>(incasari);
            }
            return incasareList;
        }

        Date data = new Date(0);
        Incasare incasare = new Incasare("-",data,"-",0.0,0,"-",
                "-","-");
        incasareList.add(incasare);
        return incasareList;

    }

    @Override
    public Optional<Incasare> findById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public  Incasare save(IncasareDTO incasareDTO) {
        Incasare incasare = new Incasare(incasareDTO.getNrDocument(),incasareDTO.getDataDocument(),incasareDTO.getTipDocument(),
                incasareDTO.getValoareIncasata(),incasareDTO.getValoareTVA(),incasareDTO.getMoneda(),incasareDTO.getExplicatii(),
                incasareDTO.getIncadrareIncasare());

            Date dataCurenta = Date.valueOf(new Date(System.currentTimeMillis()).toString());//converting current data to string and then to sql date
            Double valoareInLei = cursValutarService.procesareCursValutar(incasareDTO.getMoneda(),dataCurenta,incasareDTO.getValoareIncasata());
            ContractTerti contractTerti = contractTertiRepository.findByTitluProiect(incasareDTO.getTitluProiect());
            incasare.setValoareInLei(valoareInLei);
            incasare.setContractTerti(contractTerti);


        return repository.save(incasare);
    }

    @Override
    public void delete(Incasare stat) {
        this.repository.delete(stat);
    }

    @Override
    public Incasare update(Incasare stat) {
        return this.repository.save(stat);
    }
   public List<Incasare> getIncasariLunare(Date startDate, Date endDate, ContractTerti contractTerti){
        return this.repository.findAllByDataDocumentBetweenAndContractTerti(startDate,endDate,contractTerti);
   }

}
