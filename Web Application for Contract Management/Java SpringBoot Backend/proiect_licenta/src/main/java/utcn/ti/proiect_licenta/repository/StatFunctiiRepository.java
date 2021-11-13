package utcn.ti.proiect_licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utcn.ti.proiect_licenta.model.Angajat;
import utcn.ti.proiect_licenta.model.StatFunctii;

public interface StatFunctiiRepository extends JpaRepository<StatFunctii,Integer> {
		StatFunctii findByAngajat(Angajat angajat);
}
