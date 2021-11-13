package utcn.ti.proiect_licenta.service.serviceInterface;

import utcn.ti.proiect_licenta.model.Universitate;

import java.util.List;
import java.util.Optional;

public interface UniversitateServiceInterface {
    Universitate save(Universitate universitate);

    void delete(Universitate universitate);

    Optional<Universitate> findById(int id);

    List<Universitate> findAll();
}
