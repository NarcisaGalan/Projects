package utcn.ti.proiect_licenta.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import utcn.ti.proiect_licenta.model.Adresa;
import utcn.ti.proiect_licenta.model.Beneficiar;
import utcn.ti.proiect_licenta.model.Universitate;

public interface AdresaRepository extends JpaRepository<Adresa,Integer> {

}
