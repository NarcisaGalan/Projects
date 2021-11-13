package utcn.ti.proiect_licenta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.ti.proiect_licenta.dto.CerereImprumutDTO;
import utcn.ti.proiect_licenta.model.CerereImprumut;
import utcn.ti.proiect_licenta.model.ContractTerti;
import utcn.ti.proiect_licenta.repository.CerereImprumutRepository;
import utcn.ti.proiect_licenta.repository.ContractTertiRepository;


import java.util.List;
import java.util.Optional;

@Service
public class CerereImprumutService {
    @Autowired
    private CerereImprumutRepository cerereImprumutRepository;
    @Autowired
    private ContractTertiRepository contractTertiRepository;


    public CerereImprumut save(CerereImprumutDTO act) {

        CerereImprumut cerereImprumut = new CerereImprumut(act.getNumar(),act.getData(),act.getAnul(),act.getEtapa(),
                act.getValoareaTotala(),act.getSalarii(),act.getAchizitii(),act.getDeplasari(),act.getTaxe(),
                act.getDataReturnarii(),act.getAprobata());

            ContractTerti contractTerti = contractTertiRepository.findByTitluProiect(act.getTitluProiect());
            cerereImprumut.setContractTerti(contractTerti);


            return cerereImprumutRepository.save(cerereImprumut);

    }


    public void delete(CerereImprumut act){
        cerereImprumutRepository.delete(act);
    }


    public Optional<CerereImprumut> findById(int id){
        return cerereImprumutRepository.findById(id);
    }

    public List<CerereImprumut> findAll(){
        return cerereImprumutRepository.findAll();
    }

}
