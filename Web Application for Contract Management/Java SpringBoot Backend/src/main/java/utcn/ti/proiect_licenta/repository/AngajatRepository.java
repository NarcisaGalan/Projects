package utcn.ti.proiect_licenta.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import utcn.ti.proiect_licenta.model.Angajat;
import utcn.ti.proiect_licenta.model.ContractTerti;
import utcn.ti.proiect_licenta.model.StatFunctii;
import utcn.ti.proiect_licenta.model.User;

public interface AngajatRepository extends JpaRepository<Angajat, Integer>{
    Angajat findByFunctii(StatFunctii statFunctii);
    Angajat findByNume(String nume);
    Angajat findByUser(User user);

}

