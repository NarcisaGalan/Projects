package utcn.ti.proiect_licenta.service.serviceInterface;




import utcn.ti.proiect_licenta.model.Angajat;

import java.util.List;
import java.util.Optional;

public interface AngajatServiceInterface {
    List<Angajat> findAll();

    Optional<Angajat> findById(Integer id);

    Angajat save(Angajat angajat);

    void delete(Angajat angajat);

    Angajat update(Angajat angajat);
}
