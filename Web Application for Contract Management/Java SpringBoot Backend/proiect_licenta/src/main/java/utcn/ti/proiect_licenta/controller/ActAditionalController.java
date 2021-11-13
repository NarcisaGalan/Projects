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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

	@GetMapping("/getActAditional/{idActAditional}")
	public ResponseEntity<?> getActAditional(@PathVariable Integer idActAditional) {
		try {
			ActAditional actAditional = actAditionalService.findById(idActAditional);

			return ResponseEntity.ok().body(actAditional);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/add")
	private ResponseEntity<?> addActAditional(@RequestBody ActAditionalDTO actAditionalDTO) {
		try {
			ContractTerti contractTerti = contractTertiRepository.findByTitluProiect(actAditionalDTO.getTitluProiect());
			//System.out.println("nume contract:  "+contractTerti.getTitluProiect() +"\n data sfarsit" + contractTerti.getDataSfarsit());
			//lista cu actele aditionale ale unui contract
			List<ActAditional> actAditionalListByContractTerti = actAditionalService.findAllByContractTerti(contractTerti);
			//data de inceput a actului aditional trebuie sa fie egala cu data de sfarsit a contractului
			//sau egala cu data de sfarsit a ultimului act aditional intocmit pentru acelasi contract
			if (actAditionalListByContractTerti != null && !actAditionalListByContractTerti.isEmpty()) {
				if (contractTerti.getDataSfarsit().equals(actAditionalDTO.getDataInceput())
						|| actAditionalListByContractTerti.get(actAditionalListByContractTerti.size() - 1).getDataSfarsit().toString().equals(actAditionalDTO.getDataInceput().toString())) {
				    ActAditional actAditional = new ActAditional(actAditionalDTO.getNumar(), actAditionalDTO.getDataAct(), actAditionalDTO.getDataInceput(), actAditionalDTO.getDataSfarsit());
					actAditional.setContractTerti(contractTerti);
				    ActAditional actAditionalDB = this.actAditionalService.save(actAditional);
					actAditionalListByContractTerti.add(actAditionalDB);
					Set<ActAditional> setActeAditionale =  new HashSet<>(actAditionalListByContractTerti);
					contractTerti.setActAditionalLista(setActeAditionale);
					contractTertiService.save(contractTerti);

					return ResponseEntity.ok().build();
				} else
				    System.out.println(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data de inceput a Actului Aditionala trebuie sa fie acceasi cu data" +
                            " de sfarsit a ultimului Act Aditional"));
                  return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data de inceput a Actului Aditionala trebuie sa fie acceasi cu data" +
                            " de sfarsit a ultimului Act Aditional");
			}
			if (contractTerti.getDataSfarsit().toString().equals(actAditionalDTO.getDataInceput().toString() ) ) {
				ActAditional actAditional = new ActAditional(actAditionalDTO.getNumar(), actAditionalDTO.getDataAct(), actAditionalDTO.getDataInceput(), actAditionalDTO.getDataSfarsit());

				actAditional.setContractTerti(contractTerti);
                ActAditional actAditionalDB = this.actAditionalService.save(actAditional);
                actAditionalListByContractTerti.add(actAditionalDB);
                Set<ActAditional> setActeAditionale =  new HashSet<>(actAditionalListByContractTerti);
                contractTerti.setActAditionalLista(setActeAditionale);
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
