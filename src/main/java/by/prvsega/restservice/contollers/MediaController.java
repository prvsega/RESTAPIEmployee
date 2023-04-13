package by.prvsega.restservice.contollers;
import by.prvsega.restservice.dto.MediaDTO;
import by.prvsega.restservice.services.EmployeeService;
import by.prvsega.restservice.services.MediaService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import static by.prvsega.restservice.config.ConfigSecurityContextHolder.getUserId;


@RestController
@RequestMapping("/media")
@AllArgsConstructor
public class MediaController {

    private final EmployeeService employeeService;
    private final MediaService mediaService;


    @PostMapping(value = "/upload-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@RequestParam("image")MultipartFile file){

        Integer UserId = getUserId();
        employeeService.saveImage(UserId, file);

        return ResponseEntity.ok("Size - " + file.getSize() + ", Name - " + file.getOriginalFilename());
    }

    @PostMapping("/upload-file")
    public ResponseEntity<MediaDTO> uploadFile(@RequestParam("file")MultipartFile file){

        Integer UserId = getUserId();
        MediaDTO mediaDTO = mediaService.save(file, UserId);

        return ResponseEntity.ok(mediaDTO);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<FileSystemResource> download(@PathVariable("id") int idFile){
        Integer userId = getUserId();

        File file = mediaService.checkFile(userId, idFile);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(file.length());
        headers.setContentDispositionFormData("attachment", file.getName());
        headers.setCacheControl("no-cache, no-store, must-revalidate");
        headers.setPragma("no-cache");

        return new ResponseEntity<>(new FileSystemResource(file), headers, HttpStatus.OK);

    }


}
