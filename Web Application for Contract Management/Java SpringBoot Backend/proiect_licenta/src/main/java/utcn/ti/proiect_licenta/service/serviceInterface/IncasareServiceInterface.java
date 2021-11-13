package utcn.ti.proiect_licenta.service.serviceInterface;


import utcn.ti.proiect_licenta.dto.IncasareDTO;
import utcn.ti.proiect_licenta.model.Incasare;

import java.util.List;
import java.util.Optional;

public interface IncasareServiceInterface {
    List<Incasare> findAll();

    Optional<Incasare> findById(Integer id);

    Incasare save(IncasareDTO incasareDTO);

    void delete(Incasare incasare);

    Incasare update(Incasare incasare);
}
