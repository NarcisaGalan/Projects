package utcn.ti.proiect_licenta.dto.mapper;


import org.mapstruct.Mapper;
import utcn.ti.proiect_licenta.dto.AngajatDTO;
import utcn.ti.proiect_licenta.model.Angajat;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AngajatMapper {
    Angajat angajatDTOToAngajat(AngajatDTO AngajatDTO);

    AngajatDTO angajatToAngajatDTO(Angajat angajat);

    List<AngajatDTO> angajatListToAngajatDtoList(List<Angajat> clients);

}
