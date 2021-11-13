package utcn.ti.proiect_licenta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.ti.proiect_licenta.dto.CheltuialaDTO;
import utcn.ti.proiect_licenta.dto.CheltuialaTable;
import utcn.ti.proiect_licenta.model.Cheltuiala;
import utcn.ti.proiect_licenta.model.ContractTerti;
import utcn.ti.proiect_licenta.repository.CheltuialaRepository;
import utcn.ti.proiect_licenta.repository.ContractTertiRepository;
import utcn.ti.proiect_licenta.service.serviceInterface.CheltuialaServiceInterface;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CheltuialaService implements CheltuialaServiceInterface {

    @Autowired
    private CheltuialaRepository repository;
    @Autowired
    private ContractTertiRepository contractTertiRepository;
    @Autowired
    private CursValutarService cursValutarService;

    @Override
    public List<Cheltuiala> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Cheltuiala> findById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public Cheltuiala save(CheltuialaDTO cheltuialaDTO) {
        Cheltuiala cheltuiala = new Cheltuiala(cheltuialaDTO.getNumarDocument(), cheltuialaDTO.getDataCheltuiala(), cheltuialaDTO.getTipDocument(), cheltuialaDTO.getValoareCheltuiala(),
                cheltuialaDTO.getValoareTVA(), cheltuialaDTO.getMoneda(), cheltuialaDTO.getExplicatii(), cheltuialaDTO.getIncadrareCheltuiala());

        Date dataCurenta = Date.valueOf(new Date(System.currentTimeMillis()).toString());//converting current data to string and then to sql date
        Double valoareInLei = cursValutarService.procesareCursValutar(cheltuialaDTO.getMoneda(), dataCurenta, cheltuialaDTO.getValoareCheltuiala());
        cheltuiala.setValoareInLei(valoareInLei);
        ContractTerti contractTerti = contractTertiRepository.findByTitluProiect(cheltuialaDTO.getTitluProiect());
        cheltuiala.setContractTerti(contractTerti);

        return repository.save(cheltuiala);
    }

    @Override
    public void delete(Cheltuiala stat) {
        this.repository.delete(stat);
    }

    @Override
    public Cheltuiala update(Cheltuiala stat) {
        return this.repository.save(stat);
    }


    public List<CheltuialaTable> getCheltuieliTableByContractTerti(Integer idContractTerti) {
        Integer contractInvalid = 0;
        List<Cheltuiala> cheltuieli;
        //lista de cheltuieli returnata
        List<CheltuialaTable> tabel = new ArrayList();
        //daca a fost selectat un contract
        if (!idContractTerti.equals(contractInvalid)) {
            //find contract in db
            ContractTerti contractTerti = contractTertiRepository.findById(idContractTerti).get();
            //daca exista chletuieli inregistrate pentru acest contract
            if ((cheltuieli = repository.findAllByContractTerti(contractTerti)) != null) {
                //parcurgem cheltuielile contractului si cream o noua lista de tipul CheltuialaTable
                for (Cheltuiala cheltuiala : cheltuieli) {
                    CheltuialaTable cheltuialaTable = new CheltuialaTable(cheltuiala.getNrDocument(), cheltuiala.getDataDocument(), cheltuiala.getTipDocument(),
                            cheltuiala.getValoareCheltuiala(), cheltuiala.getValoareTva(), cheltuiala.getMoneda(), cheltuiala.getExplicatii(), cheltuiala.getIncadrareCheltuiala(), cheltuiala.getValoareInLei());
                    tabel.add(cheltuialaTable);
                }
                return tabel;
            }
        }

        Date data = new Date(0);
        CheltuialaTable cheltuialaTable = new CheltuialaTable("-", data, "-", 0.0, 0, "-",
                "-", "-", 0.0);
        tabel.add(cheltuialaTable);
        return tabel;
    }

    public List<Cheltuiala> getCheltuieliLunare(Date startDate, Date endDate, ContractTerti contractTerti){

        return this.repository.findAllByDataDocumentBetweenAndContractTerti(startDate,endDate,contractTerti);
    }




}
