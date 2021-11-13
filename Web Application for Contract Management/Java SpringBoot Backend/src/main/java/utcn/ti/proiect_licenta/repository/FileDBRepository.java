package utcn.ti.proiect_licenta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utcn.ti.proiect_licenta.model.ContractTerti;
import utcn.ti.proiect_licenta.model.FileDB;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

        FileDB findFileDBByContractTerti(ContractTerti contractTerti);

}
