package utcn.ti.proiect_licenta.service.serviceInterface;

import utcn.ti.proiect_licenta.model.CerereImprumut;

import java.util.Optional;

public interface CerereImprumutServiceInterface {
    CerereImprumut save(CerereImprumut cerereImprumut);

    void delete(CerereImprumut cerereImprumut);

    Optional<CerereImprumut> findById(int id);
}
