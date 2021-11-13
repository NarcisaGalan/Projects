package utcn.ti.proiect_licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utcn.ti.proiect_licenta.model.Beneficiar;
import utcn.ti.proiect_licenta.model.ContractTerti;

import java.util.List;

public interface BeneficiarRepository extends JpaRepository<Beneficiar,Integer> {
	Beneficiar findByDenumireAndCifCui(String denumire,String cifCui);
	List<Beneficiar> findByDenumireContaining(String denumire);
	Beneficiar findByCifCui(String cifCui);
	boolean existsBeneficiarByCifCui(String cifCui);
}
