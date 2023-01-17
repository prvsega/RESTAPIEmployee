package by.prvsega.restservice.contollers;

import by.prvsega.restservice.dto.RoleNameDTO;
import by.prvsega.restservice.mappers.RoleMapper;
import by.prvsega.restservice.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;
    private final RoleMapper roleMapper;

    @Autowired
    public RoleController(RoleService roleService, RoleMapper roleMapper) {
        this.roleService = roleService;

        this.roleMapper = roleMapper;
    }


    @GetMapping
    public List<RoleNameDTO> findAll() {
        return roleService.getRolesAll().stream().map(roleMapper::converterToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RoleNameDTO findOne(@PathVariable("id") int id) {
        return roleMapper.converterToDTO(roleService.getRolesOne(id));
    }
}
