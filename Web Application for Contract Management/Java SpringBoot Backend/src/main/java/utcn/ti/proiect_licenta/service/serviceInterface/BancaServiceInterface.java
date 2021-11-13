package utcn.ti.proiect_licenta.service.serviceInterface;

import utcn.ti.proiect_licenta.model.Banca;

import java.util.List;
import java.util.Optional;

public interface BancaServiceInterface {
    Banca save(Banca banca);

    void delete(Banca banca);

    Optional<Banca> findById(int id);

    List<Banca> findAll();
}
