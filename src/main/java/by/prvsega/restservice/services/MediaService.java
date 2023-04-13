package by.prvsega.restservice.services;

import by.prvsega.restservice.dto.MediaDTO;
import by.prvsega.restservice.exceptions.FileIsEmptyException;
import by.prvsega.restservice.exceptions.MediaNotFoundException;
import by.prvsega.restservice.mappers.MediaMapper;
import by.prvsega.restservice.models.Media;
import by.prvsega.restservice.repositories.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.Objects.isNull;

@Service
public class MediaService {

    @Value("${upload.path}")
    private String uploadPath;
    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;
    private final EmployeeService employeeService;

    @Autowired
    public MediaService(MediaRepository mediaRepository, MediaMapper mediaMapper, EmployeeService employeeService) {
        this.mediaRepository = mediaRepository;
        this.mediaMapper = mediaMapper;
        this.employeeService = employeeService;
    }

    @Transactional
    public MediaDTO save(MultipartFile file, Integer userId) {
        String mediaName;

        if (isNull(file)) {
            throw new FileIsEmptyException();
        } else
            try {
                mediaName = "/" + downloadInDirectory(file, userId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        Media media = new Media();
        media.setPath(mediaName);
        mediaRepository.save(media);
        employeeService.saveMedia(media, userId);
        return mediaMapper.converterToDTO(media);
    }

    public String downloadInDirectory(MultipartFile file, int id) throws IOException {
        String userId = String.valueOf(id).concat("/");
        String fileName = file.getOriginalFilename();
        Path path = Paths.get(uploadPath + userId);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        try {
            Path localPathFile = Paths.get(path.toAbsolutePath() + "/" + fileName);
            Files.write(localPathFile, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileName;
    }

    public File checkFile(Integer userId, int idFile) {
        Media media = mediaRepository.getById(idFile);
        Path path = Paths.get(uploadPath.concat(String.valueOf(userId)).concat("/").concat(media.getPath()));
        if (!Files.exists(path)) {
            throw new MediaNotFoundException();
        }
        return new File(String.valueOf(path));
    }
}
