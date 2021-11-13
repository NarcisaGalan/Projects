package utcn.ti.proiect_licenta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import utcn.ti.proiect_licenta.model.Beneficiar;
import utcn.ti.proiect_licenta.model.ContractTerti;
import utcn.ti.proiect_licenta.model.FileDB;
import utcn.ti.proiect_licenta.repository.ContractTertiRepository;
import utcn.ti.proiect_licenta.repository.FileDBRepository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;
    @Autowired
    private PdfContentBuilder pdfContentBuilder;
    @Autowired
    private ContractTertiRepository contractTertiRepository;

    public FileDB saveByte(byte[] pdf,String name, Integer idContractTerti){
        FileDB fileDB = new FileDB(name,"application/pdf",pdf);
        ContractTerti contractTerti = contractTertiRepository.findById(idContractTerti).get();
        fileDB.setContractTerti(contractTerti);
        return  fileDBRepository.save(fileDB);
    }

    public FileDB store(MultipartFile file,Integer idContractTerti) throws IOException {
        ContractTerti contractTerti = contractTertiRepository.findById(idContractTerti).get();

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes());

        if(contractTerti.getFileDB()!=null){
            String idFile = contractTerti.getFileDB().getId();
            fileDB.setId(idFile);
        }
        //formam legatura contract - pdf
        fileDB.setContractTerti(contractTerti);

        return fileDBRepository.save(fileDB);
    }

    public FileDB getFile(Integer id) {
        ContractTerti contractTerti = contractTertiRepository.findById(id).get();
        return fileDBRepository.findFileDBByContractTerti(contractTerti);
    }

    public FileDB getFileByName(String fileName) {
        ContractTerti contractTerti = contractTertiRepository.findByTitluProiect(fileName);
        return fileDBRepository.findFileDBByContractTerti(contractTerti);
    }



}