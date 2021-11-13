package utcn.ti.proiect_licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utcn.ti.proiect_licenta.model.Incasare;

public interface IncasareRepository extends JpaRepository<Incasare,Integer> {
	Incasare findByNrDocument(String nrDocument);
}
