package utcn.ti.proiect_licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utcn.ti.proiect_licenta.model.Facultate;
import utcn.ti.proiect_licenta.model.Universitate;

import java.util.List;

public interface FacultateRepository  extends JpaRepository<Facultate,Integer> {
    List<Facultate> findAllByUniversitate(Universitate universitate);
}
