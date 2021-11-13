package utcn.ti.proiect_licenta.service.serviceInterface;




import utcn.ti.proiect_licenta.model.Beneficiar;

import java.util.Optional;

public interface BeneficiarServiceInterface {
    Beneficiar save(Beneficiar beneficiar);

    void delete(Beneficiar beneficiar);

    Optional<Beneficiar> findById(int id);
}
