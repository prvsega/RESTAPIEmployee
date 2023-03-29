package by.prvsega.restservice.mappers;

import by.prvsega.restservice.dto.RoleDTO;
import by.prvsega.restservice.models.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.HashSet;
import java.util.Set;

@Component
public class RoleMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RoleDTO convertToDTO(Role role) {
        return modelMapper.map(role, RoleDTO.class);
    }

    public Role convertToRole(RoleDTO roleDTO) {
        return modelMapper.map(roleDTO, Role.class);
    }

    public Set<Role> converterSETDTOToRole(Set<RoleDTO> setDTO) {
        Set<Role> set = new HashSet<>();

        for (RoleDTO dto : setDTO) {
            Role role = convertToRole(dto);

            set.add(role);
        }

        return set;

    }

}

