package utcn.ti.proiect_licenta.service.serviceInterface;

import utcn.ti.proiect_licenta.dto.CheltuialaDTO;
import utcn.ti.proiect_licenta.model.Cheltuiala;

import java.util.List;
import java.util.Optional;

public interface CheltuialaServiceInterface {
    List<Cheltuiala> findAll();

    Optional<Cheltuiala> findById(Integer id);

    Cheltuiala save(CheltuialaDTO cheltuiala);

    void delete(Cheltuiala cheltuiala);

    Cheltuiala update(Cheltuiala cheltuiala);
}
