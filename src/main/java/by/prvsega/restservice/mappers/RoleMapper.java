package by.prvsega.restservice.mappers;

import by.prvsega.restservice.dto.RoleNameDTO;
import by.prvsega.restservice.models.Roles;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    private final ModelMapper modelMapper;
    @Autowired
    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public RoleNameDTO converterToDTO(Roles roles){
        return modelMapper.map(roles, RoleNameDTO.class);
    }

}
