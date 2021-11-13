package utcn.ti.proiect_licenta.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utcn.ti.proiect_licenta.dto.ActAditionalDTO;
import utcn.ti.proiect_licenta.model.ActAditional;
import utcn.ti.proiect_licenta.model.ContractTerti;
import utcn.ti.proiect_licenta.repository.ContractTertiRepository;
import utcn.ti.proiect_licenta.service.ActAditionalService;
import utcn.ti.proiect_licenta.service.ContractTertiService;

import java.sql.Date;
import java.util.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(value = "/act-aditional")
public class ActAditionalController {

	@Autowired
	private ActAditionalService actAditionalService;

	@Autowired
	private ContractTertiService contractTertiService;
	@Autowired
	private ContractTertiRepository contractTertiRepository;


	/* path param: idContractTerti
	 *  returns: Lista cu ActeAditionale in functie de ContractTerti  */
	@GetMapping("/getAllByContractTerti/{idContractTerti}")
	public ResponseEntity<?> getAllByContractTerti(@PathVariable Integer contractTertiId) {
		try {
			ContractTerti contractTerti = contractTertiService.findById(contractTertiId).get();
			List<ActAditional> contracteTerti = this.actAditionalService.findAllByContractTerti(contractTerti);

			return ResponseEntity.ok().body(contracteTerti);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getAllByDirector/{idDirector}")
	public ResponseEntity<?> getAllByDirector(@PathVariable Integer idDirector) {
		try {
			//contractele directorului
			List<ContractTerti> contractTertiList = this.contractTertiService.findContracteByUser(idDirector);
			//lista de acte aditionale care va fi retunata
			List<ActAditional> actAditionalList = new ArrayList<>();
			//parcurgere contracte pentru extragere acte aditionale
			for (ContractTerti contract: contractTertiList
				 ) {
				List<ActAditional> actAditionalContractList;
				//daca contractul are acte aditionale atunci se adauga toate la lista finala
				if(!(actAditionalContractList = new ArrayList<>(contract.getActAditionalLista())).isEmpty()){
					actAditionalList.addAll(actAditionalContractList);
				}
			}
			if(actAditionalList.isEmpty()){
				java.sql.Date data = new Date(0);
				ActAditional actAditional = new ActAditional(0,data,data,data);
				actAditionalList.add(actAditional);
			}
			return ResponseEntity.ok().body(actAditionalList);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getActAditional/{idActAditional}")
	public ResponseEntity<?> getActAditional(@PathVariable Integer idActAditional) {
		try {
			ActAditional actAditional = actAditionalService.findById(idActAditional);

			return ResponseEntity.ok().body(actAditional);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping(value = "/edit")
	public ResponseEntity<?> editActAditional(@RequestBody ActAditional actAditional){
		try {
			actAditionalService.update(actAditional);
			return ResponseEntity.ok().build();
		}catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = "/add")
	public ResponseEntity<?> addActAditional(@RequestBody ActAditionalDTO actAditionalDTO) {
		try {
			ContractTerti contractTerti = contractTertiRepository.findByTitluProiect(actAditionalDTO.getTitluProiect());
			//lista cu actele aditionale ale unui contract
			List<ActAditional> actAditionalListByContractTerti = actAditionalService.findAllByContractTerti(contractTerti);
			//data de inceput a actului aditional trebuie sa fie egala cu data de sfarsit a contractului
			//sau egala cu data de sfarsit a ultimului act aditional intocmit pentru acelasi contract

			//daca exista acte aditionale trebuie verificat cu data de sf a actului adițional
			if (actAditionalListByContractTerti != null && !actAditionalListByContractTerti.isEmpty()) {
				if (actAditionalListByContractTerti.get(actAditionalListByContractTerti.size() - 1).getDataSfarsit().toString().equals(actAditionalDTO.getDataInceput().toString())) {
				    ActAditional actAditional = new ActAditional(actAditionalDTO.getNumar(), actAditionalDTO.getDataAct(), actAditionalDTO.getDataInceput(), actAditionalDTO.getDataSfarsit());
					actAditional.setContractTerti(contractTerti);
				    ActAditional actAditionalDB = this.actAditionalService.save(actAditional);
				    //se adaugă in lista de acte aditionale ale contractului
					actAditionalListByContractTerti.add(actAditionalDB);
					Set<ActAditional> setActeAditionale =  new HashSet<>(actAditionalListByContractTerti);
					//se seteaza noua lista de acte aditionale ale contractului
					contractTerti.setActAditionalLista(setActeAditionale);
					//se salvează contractul cu noua lista de acte aditionale/ update
					contractTertiService.save(contractTerti);

					return ResponseEntity.ok().build();
				} else

                  return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data de inceput a Actului Aditionala trebuie sa fie acceasi cu data" +
                            " de sfarsit a ultimului Act Aditional");
			}
			//daca nu există acte aditionale se verifică cu data de sf a contractului
			if (contractTerti.getDataSfarsit().toString().equals(actAditionalDTO.getDataInceput().toString() ) ) {
				ActAditional actAditional = new ActAditional(actAditionalDTO.getNumar(), actAditionalDTO.getDataAct(), actAditionalDTO.getDataInceput(), actAditionalDTO.getDataSfarsit());
				//se face legatura cu contractul
				actAditional.setContractTerti(contractTerti);
				//se salveaza in db actul
                ActAditional actAditionalDB = this.actAditionalService.save(actAditional);
                actAditionalListByContractTerti.add(actAditionalDB);
                Set<ActAditional> setActeAditionale =  new HashSet<>(actAditionalListByContractTerti);
                //se seteaza noul Set de acte adiționale ale contractului
                contractTerti.setActAditionalLista(setActeAditionale);
                //se face update la contract
                contractTertiService.save(contractTerti);

				return ResponseEntity.ok().build();
			} else {
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data de inceput a Actului Aditionala trebuie sa fie acceasi cu data" +
                        " de sfarsit a contractului");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
