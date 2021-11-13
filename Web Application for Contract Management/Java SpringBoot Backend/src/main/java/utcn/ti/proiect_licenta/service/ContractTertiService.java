package utcn.ti.proiect_licenta.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.ti.proiect_licenta.dto.ContractTertiDTO;
import utcn.ti.proiect_licenta.dto.ContractTertiLunarDTO;
import utcn.ti.proiect_licenta.dto.RaportBuget;
import utcn.ti.proiect_licenta.model.*;
import utcn.ti.proiect_licenta.repository.*;
import utcn.ti.proiect_licenta.service.serviceInterface.ContractTertiServiceInterface;

import java.sql.Date;
import java.util.*;

@Service
public class ContractTertiService implements ContractTertiServiceInterface {

    @Autowired
    private ContractTertiRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StatFunctiiRepository statFunctiiRepository;
    @Autowired
    private AngajatRepository angajatRepository;
    @Autowired
    BeneficiarService beneficiarService;
    @Autowired
    private  CursValutarService cursValutarService;
    @Autowired
    private PdfContentBuilder pdfContentBuilder;
    @Autowired
    private ActAditionalRepository actAditionalRepository;

    @Autowired
    private FileStorageService storageService;

    @Autowired
    private ContractTertiRepository contractTertiRepository;

    @Override
    public List<ContractTerti> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<ContractTerti> findById(Integer id) {
        return this.repository.findById(id);
    }

    @Override
    public ContractTerti save(ContractTerti stat) {
        return this.repository.save(stat);
    }

    @Override
    public void delete(ContractTerti stat){
        this.repository.delete(stat);
    }

    @Override
    public ContractTerti update(ContractTerti stat){
        return this.repository.save(stat);
    }

    public List<ContractTerti> findContracteByUser(Integer idUser) {
        //lista cu contracte corespunzatoare user-ului
        List<ContractTerti> contracteUser = new ArrayList();
        //angajat corespunzator user-ului
        Angajat angajat = userRepository.findById(idUser).get().getAngajat();
        //lista de functii corespunzatoare angajatului
        List<StatFunctii> listaFunctii = new ArrayList<>(angajat.getFunctii());
        for (StatFunctii functieAngajat:listaFunctii
             ) {
            //pentru fiecare functie a angajatului, luam contractul corespunzator acestuia si il adaugam la lista returnata
            contracteUser.add(functieAngajat.getContractTerti());
        }

        return contracteUser;
    }

    public void addDirectorToContract(int idContract, Integer idDirector) {

        ContractTerti contractTerti = findById(idContract).get();

        Angajat angajat = angajatRepository.findById(idDirector).get();
        StatFunctii functieDirector = new StatFunctii("director",contractTerti.getDataInceput(),contractTerti.getDataSfarsit(),false);
        functieDirector.setAngajat(angajat);
        functieDirector.setContractTerti(contractTerti);
        statFunctiiRepository.save(functieDirector);

        contractTerti.getStatFunctiiList().add(functieDirector);
        repository.save(contractTerti);

    }

    public void addContractTerti(ContractTertiDTO contractTertiDTO) {

        //adaugare contract
        ContractTerti contractTerti = new ContractTerti(contractTertiDTO.getNumarContract(), contractTertiDTO.getDataContract(), contractTertiDTO.getTip(),
                contractTertiDTO.getTitlu(), contractTertiDTO.getValTotala(), contractTertiDTO.getMoneda(), contractTertiDTO.getDataIncheiere(),
                contractTertiDTO.getNumarPagini(), contractTertiDTO.getNumarDeExemplare(), contractTertiDTO.getExemplareBeneficiar(), contractTertiDTO.getDataInceput(),
                contractTertiDTO.getDataSfarsit(), contractTertiDTO.getPartener(), contractTertiDTO.getValTVA());

        //cautare beneficiar specific contractului
        Beneficiar beneficiar = beneficiarService.findByCifCui(contractTertiDTO.getCifCuiBeneficiar());
        contractTerti.setBeneficiar(beneficiar);
        //initializam o lista goala de functii
        Set<StatFunctii> functiiList = new HashSet<>();
        contractTerti.setStatFunctiiList(functiiList);

        java.sql.Date dataCurenta = java.sql.Date.valueOf(new Date(System.currentTimeMillis()).toString());
        //procesam valoarea in lei a contractului
        Double valoareInLei = cursValutarService.procesareCursValutar(contractTertiDTO.getMoneda(),dataCurenta,contractTertiDTO.getValTotala());
        contractTerti.setValoareInLei(valoareInLei);

        ContractTerti contractTertiDB =repository.save(contractTerti);
        //adaugare director la contract
        addDirectorToContract(contractTertiDB.getIdContractTerti(),contractTertiDTO.getIdDirector());

        //salvarea Contract ca si PDF file
        saveContractAsPDFFile(contractTertiDB.getIdContractTerti(),contractTertiDTO.getIdDirector());
    }

    public void saveContractAsPDFFile(Integer idContract,Integer idDirector){
        ContractTerti contractTerti = repository.findById(idContract).get();
        Beneficiar beneficiar = contractTerti.getBeneficiar();
        String director = angajatRepository.findById(idDirector).get().getNume();
        String fileName = contractTerti.getTitluProiect();

        String htmlString = pdfContentBuilder.buildContractTerti(contractTerti,beneficiar,director);
        try {

            byte[] pdfAsBytes =  pdfContentBuilder.generatePdfFromHtml(htmlString, fileName);

            storageService.saveByte(pdfAsBytes,contractTerti.getTitluProiect(), contractTerti.getIdContractTerti());


        }catch (Exception e){
         e.printStackTrace();
        }
    }

    public List<String> findTitluProiectByAngajat(Integer idAngajat) {

        //lista cu contracte corespunzatoare angajat
        List<String> titluProiecte =new ArrayList<>();
        //angajat corespunzator idAngajat
        Angajat angajat = angajatRepository.findById(idAngajat).get();
        //lista de functii corespunzatoare angajatului
        List<StatFunctii> listaFunctii = new ArrayList<>(angajat.getFunctii());
        for (StatFunctii functieAngajat:listaFunctii
        ) {
            //pentru fiecare functie a angajatului, luam contractul corespunzator acesteia si il adaugam la lista returnata
            titluProiecte.add(functieAngajat.getContractTerti().getTitluProiect());
        }

        return titluProiecte;
    }

    public List<ContractTertiLunarDTO> getAllByMonth(Date data){
        //luam toate contractele care au data de sfarsit dupa data transmisa, adica sunt inca in derulare
        //folosim set pentru a nu avea duplicate cand adaugam si dupa acte aditionale
        Set<ContractTerti> contractTertiList = new HashSet<>(this.contractTertiRepository.findAllByDataSfarsitAfter(data));
        //luam actele aditionale care au data de sf dupa data transmisa
        //pentru ca am folosit Set la lista nu verificam daca sunt duplicate cu contracte
        List<ActAditional> actAditionalList = this.actAditionalRepository.findAllByDataSfarsitAfter(data);
        for (ActAditional actAditional: actAditionalList) {
            contractTertiList.add(actAditional.getContractTerti());
        }
        String numeDirector = "";
        List<ContractTertiLunarDTO> contractTertiLunarDTOList = new ArrayList<>();
        //parcurgem lista de contracte active
        for (ContractTerti contractTerti: contractTertiList) {
            List<StatFunctii> statFunctiiList = new ArrayList<>(contractTerti.getStatFunctiiList());
            //gasim prima functie de director(va fi unica) din lista de functii a fiecarui contract
            Optional<StatFunctii> directorStatFunctii =
                    statFunctiiList.stream().filter(obj -> obj.getFunctie().equals("director")).findFirst();
            //extragem numele directorului
            numeDirector = directorStatFunctii.get().getAngajat().getNume();
            //Construim un obiect cu datele partiale contractului din db
            ContractTertiLunarDTO contractTertiLunarDTO = new ContractTertiLunarDTO(contractTerti.getIdContractTerti(),contractTerti.getBeneficiar().getDenumire(),
                    numeDirector,contractTerti.getCoordonatorPartener(),contractTerti.getTitluProiect(),contractTerti.getMoneda(),contractTerti.getValoare(),
                    contractTerti.getTva(),contractTerti.getValoareInLei(),contractTerti.getTip(),contractTerti.getNumar(),contractTerti.getData(),contractTerti.getDataInceput(),
                    contractTerti.getDataSfarsit(),contractTerti.getNrPagini(),contractTerti.getNrExemplare(),contractTerti.getNrExemplareBeneficiar(),contractTerti.getRegie());
            contractTertiLunarDTO.setActAditionalList(contractTerti.getActAditionalLista());
            //il adaugam la lista
            contractTertiLunarDTOList.add(contractTertiLunarDTO);
        }
        return  contractTertiLunarDTOList;
    }

    public RaportBuget bugetContract(Integer idContract){
        //cautare contract
        ContractTerti contractTerti= this.repository.findById(idContract).get();
        String numeProiect = contractTerti.getTitluProiect();
        Double valoareContractInLei= contractTerti.getValoareInLei();
        Double valoareCheltuita = valoareCheltuita(new ArrayList<>(contractTerti.getCheltuialaLista()));
        Double valoareImprumutata= valoareImprumutata(new ArrayList<>(contractTerti.getCerereImprumutLista()));
        Double valoareIncasata = valoareIncasata(new ArrayList<>(contractTerti.getIncasareLista()));
        Double valoareDeIncasat = contractTerti.getValoareInLei() - valoareIncasata;
        Double profit = valoareContractInLei - (valoareCheltuita+valoareImprumutata);
        String moneda = contractTerti.getMoneda();
        Double valoareContract = contractTerti.getValoare();

        RaportBuget raportBuget= new RaportBuget(numeProiect,valoareContractInLei,valoareCheltuita,valoareImprumutata,valoareIncasata,valoareDeIncasat,profit,moneda,valoareContract);

        return raportBuget;
    }
    public Double valoareCheltuita(List<Cheltuiala> cheltuieli){
        Double valoareCheltuita = 0.0;
        if(!cheltuieli.isEmpty()){
            for (Cheltuiala cheltuiala: cheltuieli) {
                valoareCheltuita = cheltuiala.getValoareInLei() +valoareCheltuita;
            }
            return valoareCheltuita;
        }
        return valoareCheltuita;
    }

    public  Double valoareImprumutata(List<CerereImprumut> imprumuturi){
        Double valoareImprumutata= 0.0;

        if(!imprumuturi.isEmpty()){
            for(CerereImprumut cerereImprumut: imprumuturi){
                if(cerereImprumut.getAprobata()){
                    valoareImprumutata = cerereImprumut.getValoareaTotala()+ valoareImprumutata;
                }
            }
            return  valoareImprumutata;
        }
        return valoareImprumutata;
    }



    public Double valoareIncasata(List<Incasare> incasari){

        Double valoareIncasata = 0.0;
        if(!incasari.isEmpty()){
            for(Incasare incasare:incasari) {
                valoareIncasata = incasare.getValoareInLei() + valoareIncasata;
            }
            return valoareIncasata;
        }
        return valoareIncasata;
    }



}
