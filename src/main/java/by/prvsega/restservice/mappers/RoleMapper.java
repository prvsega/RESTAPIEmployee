package by.prvsega.restservice.mappers;

import by.prvsega.restservice.dto.EmployeeDTO;
import by.prvsega.restservice.dto.RoleDTO;
import by.prvsega.restservice.models.Employee;
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

    public Set<Role> convertSetToRole(Set<RoleDTO> setDTO){
        Set<Role> set = new HashSet<>();
        for (RoleDTO roleDTO: setDTO)
            set.add(convertToRole(roleDTO));

        return set;
    }

    public Set<RoleDTO> convertSetToDTO(Set<Role> setRole){
        Set<RoleDTO> setDTO = new HashSet<>();
        for (Role role: setRole)
            setDTO.add(convertToDTO(role));

        return setDTO;
    }


}

