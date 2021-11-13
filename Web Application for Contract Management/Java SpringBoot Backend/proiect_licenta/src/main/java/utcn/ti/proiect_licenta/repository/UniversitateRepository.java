package utcn.ti.proiect_licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utcn.ti.proiect_licenta.model.Universitate;

public interface UniversitateRepository extends JpaRepository<Universitate,Integer> {
	Universitate findByCodUniversitate(String codUnicersitate);
}
