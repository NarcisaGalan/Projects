package utcn.ti.proiect_licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utcn.ti.proiect_licenta.model.Membru;

public interface MembruRepository extends JpaRepository<Membru,Integer> {
    Membru findByFunctie(String functie);
}
