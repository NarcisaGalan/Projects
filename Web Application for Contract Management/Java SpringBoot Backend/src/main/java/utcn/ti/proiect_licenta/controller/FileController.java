package utcn.ti.proiect_licenta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import utcn.ti.proiect_licenta.dto.ResponseFile;
import utcn.ti.proiect_licenta.model.FileDB;
import utcn.ti.proiect_licenta.service.FileStorageService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(value = "/fisiere")
public class FileController {

    @Autowired
    private FileStorageService storageService;

    @PostMapping("/incarcare/{idContractTerti}")
    public ResponseEntity<?> uploadFile(@PathVariable Integer idContractTerti,@RequestParam("file") MultipartFile file) {

        try {
            storageService.store(file,idContractTerti);

            return ResponseEntity.ok().build();
        } catch (Exception e) {

            return new ResponseEntity("INTERNAL_SERVER_ERROR" + "Contractul " + file.getOriginalFilename() + " nu a putut fi incarcat!", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @GetMapping("/{idContractTerti}")
    public ResponseEntity<byte[]> getFile(@PathVariable Integer idContractTerti) {
        try{
            FileDB fileDB = storageService.getFile(idContractTerti);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getNume() + "\"")
                    .body(fileDB.getData());
        }catch (Exception e ){
            e.printStackTrace();
            return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getByName/{fileName}")
    public ResponseEntity<byte[]> getFileByName(@PathVariable String fileName) {
        try{
            FileDB fileDB = storageService.getFileByName(fileName);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getNume() + "\"")
                    .body(fileDB.getData());
        }catch (Exception e){
            e.printStackTrace();
             return new ResponseEntity("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}