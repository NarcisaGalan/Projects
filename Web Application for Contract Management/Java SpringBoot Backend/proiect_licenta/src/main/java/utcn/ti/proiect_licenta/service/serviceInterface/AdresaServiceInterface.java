package utcn.ti.proiect_licenta.service.serviceInterface;

import utcn.ti.proiect_licenta.model.Adresa;

import java.util.List;
import java.util.Optional;

public interface AdresaServiceInterface {
    Adresa save(Adresa adresa);

    void delete(Adresa adresa);

    Optional<Adresa> findById(int id);

    List<Adresa> findAll();

}
