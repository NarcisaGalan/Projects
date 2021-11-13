package utcn.ti.proiect_licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utcn.ti.proiect_licenta.model.Departament;
import utcn.ti.proiect_licenta.model.Facultate;

import java.util.List;

public interface DepartamentRepository extends JpaRepository<Departament,Integer> {
	List<Departament> findAllByFacultate(Facultate facultate);
	Departament findByCodDepartament(String codDepartament);
}
