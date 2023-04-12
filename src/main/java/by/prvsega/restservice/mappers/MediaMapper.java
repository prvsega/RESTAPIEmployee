package by.prvsega.restservice.mappers;

import by.prvsega.restservice.dto.EmployeeDTO;
import by.prvsega.restservice.dto.MediaDTO;
import by.prvsega.restservice.models.Employee;
import by.prvsega.restservice.models.Media;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MediaMapper {

        private final ModelMapper modelMapper;


        public MediaDTO converterToDTO(Media media) {
            return modelMapper.map(media, MediaDTO.class);
        }

        public Media converterToEmployee(MediaDTO mediaDTO) {
            return modelMapper.map(mediaDTO, Media.class);
        }
}
