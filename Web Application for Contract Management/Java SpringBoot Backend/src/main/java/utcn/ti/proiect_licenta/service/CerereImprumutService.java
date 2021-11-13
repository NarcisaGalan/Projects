package utcn.ti.proiect_licenta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.ti.proiect_licenta.dto.CerereImprumutDTO;
import utcn.ti.proiect_licenta.model.CerereImprumut;
import utcn.ti.proiect_licenta.model.ContractTerti;
import utcn.ti.proiect_licenta.repository.CerereImprumutRepository;
import utcn.ti.proiect_licenta.repository.ContractTertiRepository;


import java.sql.Date;
import java.util.ArrayList;
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
    public CerereImprumut update(CerereImprumut cerereImprumut){
        return this.cerereImprumutRepository.save(cerereImprumut);
    }

    public List<CerereImprumut> findAllAprobateByContract(Integer idContract){
        Integer contractInvalid = 0;
        List<CerereImprumut> cerereImprumutAprobataList = new ArrayList();
        if(!idContract.equals(contractInvalid)){
            ContractTerti contractTerti =contractTertiRepository.findById(idContract).get();


            if((cerereImprumutAprobataList=cerereImprumutRepository.findAllByContractTertiAndAprobata(contractTerti,true))!=null){
                return cerereImprumutAprobataList;
            }
        }

        Date data = new Date(0);
        CerereImprumut cerereImprumut=new CerereImprumut(0,data,0,"-",0.0,0.0,0.0,0.0,0.0,data,true);
        cerereImprumutAprobataList.add(cerereImprumut);
        return cerereImprumutAprobataList;
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
    public List<CerereImprumut> getImprumuturiLunare(Date startDate,Date endDate, ContractTerti contractTerti){
      return this.cerereImprumutRepository.findAllByDataBetweenAndContractTerti(startDate,endDate,contractTerti);
    }

}
