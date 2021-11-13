package utcn.ti.proiect_licenta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcn.ti.proiect_licenta.dto.ContractBeneficiarExistentDTO;
import utcn.ti.proiect_licenta.dto.ContractTertiDTO;
import utcn.ti.proiect_licenta.dto.FillSelectUFDDTO;
import utcn.ti.proiect_licenta.model.*;
import utcn.ti.proiect_licenta.repository.*;

import java.util.*;

@Service
public class SchemeFormulareService {

	@Autowired
	UniversitateRepository universitateRepository;
	@Autowired
	FacultateRepository facultateRepository;
	@Autowired
	DepartamentRepository departamentRepository;
	@Autowired
	ContractTertiRepository contractTertiRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	BeneficiarService beneficiarService;
	@Autowired
	AngajatRepository angajatRepository;
	@Autowired
	BancaService bancaService;
	@Autowired
	AdresaService adresaService;
	@Autowired
	AngajatService angajatService;
	@Autowired
	ContractTertiService contractTertiService;


	public FillSelectUFDDTO fillInfo() {

		FillSelectUFDDTO cerereImprumutDTO = new FillSelectUFDDTO();
		cerereImprumutDTO.setUniversitateList(this.universitateRepository.findAll());
		cerereImprumutDTO.setFacultateList(this.facultateRepository.findAll());
		cerereImprumutDTO.setDepartamentList(this.departamentRepository.findAll());

		return cerereImprumutDTO;
	}


	public List<Facultate> fillFacultate(Universitate universitate) {
		return this.facultateRepository.findAllByUniversitate(universitate);
	}

	public List<Departament> fillDepartament(Facultate facultate) {
		return this.departamentRepository.findAllByFacultate(facultate);
	}

	public List<String> findTitluProiectByUser(Integer idAngajat) {

		List<ContractTerti> allContracts = this.contractTertiRepository.findAll();
        List<String> proiect = new ArrayList();
		Angajat userAngajat = new Angajat();
		userAngajat = angajatRepository.findById(idAngajat).get();

		System.out.println("director:  "+userAngajat.getNume());

		for (ContractTerti contract : allContracts) {
			Set<Angajat> angajati = new HashSet();
			angajati = contract.getListaAngajati();
			//daca contractul contine id-ul user-ului inseamna ca acesta este directr si se adauga la lista
			for (Angajat angajat : angajati) {
				System.out.println("Angajati din contract in for: "+ angajat );
				if (angajat.equals(userAngajat)) {
					proiect.add(contract.getTitluProiect());
				}
			}
		}
		return proiect;
	}

	public ContractTerti findByTitluProiect(String titluProiect) {
		return this.contractTertiRepository.findByTitluProiect(titluProiect);
	}

	public void addContractTerti(ContractTertiDTO contractTertiDTO) {
		//BANCA
		Banca bancaBeneficiar = new Banca(contractTertiDTO.getBanca(), contractTertiDTO.getCont());


		//ADRESA
		Adresa adresaBeneficiar = new Adresa(contractTertiDTO.getTara(), contractTertiDTO.getJudet(), contractTertiDTO.getLocalitate(),
				contractTertiDTO.getStrada(), contractTertiDTO.getNumarStrada());
		this.adresaService.save(adresaBeneficiar);


		/*Adaugare beneficiar in baza de date*/
		Beneficiar beneficiar = new Beneficiar(contractTertiDTO.getNumeBeneficiar(), contractTertiDTO.getCif(), contractTertiDTO.getNumarTelefon(),
				contractTertiDTO.getAdresaEmailBeneficiar(), contractTertiDTO.getReprezentant(), contractTertiDTO.getFunctieReprezentant());

		bancaBeneficiar.setBeneficiar(beneficiar);

		Set<Banca> banciBeneficiar = new HashSet();
		banciBeneficiar.add(bancaBeneficiar);
		beneficiar.setAdresa(adresaBeneficiar);
		beneficiar.setBanci(banciBeneficiar);

		this.beneficiarService.save(beneficiar);//adaugare in baza de date
		this.bancaService.save(bancaBeneficiar);


		//adaugare contract
		ContractTerti contractTerti = new ContractTerti(contractTertiDTO.getNumarContract(), contractTertiDTO.getDataContract(), contractTertiDTO.getTip(),
				contractTertiDTO.getTitlu(), contractTertiDTO.getValTotala(), contractTertiDTO.getMoneda(), contractTertiDTO.getDataIncheiere(),
				contractTertiDTO.getNumarPagini(), contractTertiDTO.getNumarDeExemplare(), contractTertiDTO.getExemplareBeneficiar(), contractTertiDTO.getDataInceput(),
				contractTertiDTO.getDataSfarsit(), contractTertiDTO.getPartener(), contractTertiDTO.getValTVA());


		contractTerti.setBeneficiar(beneficiar);


		contractTertiRepository.save(contractTerti);
		//adaugare angajat la contract
		contractTertiService.addAngajatToContract(contractTertiRepository.findByTitluProiect(contractTertiDTO.getTitlu()).getIdContractTerti(),angajatRepository.findByNume(contractTertiDTO.getDirector()).getIdAngajat());



	}
	public void addContractTertiBeneficiarExistent(ContractBeneficiarExistentDTO contractTertiDTO) {

		/*Gasire beneficiar din baza de date*/
		System.out.println("ID BENEFICIAR: "+ contractTertiDTO.getIdBeneficiar()+ "\n\n");
		Beneficiar beneficiar = beneficiarService.findById(contractTertiDTO.getIdBeneficiar()).get();


		//adaugare contract
		ContractTerti contractTerti = new ContractTerti(contractTertiDTO.getNumarContract(), contractTertiDTO.getDataContract(), contractTertiDTO.getTip(),
				contractTertiDTO.getTitlu(), contractTertiDTO.getValTotala(), contractTertiDTO.getMoneda(), contractTertiDTO.getDataIncheiere(),
				contractTertiDTO.getNumarPagini(), contractTertiDTO.getNumarDeExemplare(), contractTertiDTO.getExemplareBeneficiar(), contractTertiDTO.getDataInceput(),
				contractTertiDTO.getDataSfarsit(), contractTertiDTO.getPartener(), contractTertiDTO.getValTVA());


		contractTerti.setBeneficiar(beneficiar);


		contractTertiRepository.save(contractTerti);
		//adaugare angajat la contract
		contractTertiService.addAngajatToContract(contractTertiRepository.findByTitluProiect(contractTertiDTO.getTitlu()).getIdContractTerti(),angajatRepository.findByNume(contractTertiDTO.getDirector()).getIdAngajat());

	}
}
