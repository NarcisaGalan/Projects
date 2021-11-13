package utcn.ti.proiect_licenta.service.serviceInterface;


import utcn.ti.proiect_licenta.model.StatFunctii;

import java.util.List;
import java.util.Optional;

public interface StatFunctiiServiceInterface {
    List<StatFunctii> findAll();

    Optional<StatFunctii> findById(Integer id);

    StatFunctii save(StatFunctii stat);

    void delete(StatFunctii stat);

    StatFunctii update(StatFunctii stat);
}
