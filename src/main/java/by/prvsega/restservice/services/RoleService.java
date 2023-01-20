package by.prvsega.restservice.services;

import by.prvsega.restservice.models.Role;
import by.prvsega.restservice.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;


    }

    public Role getRolesOne(int id){
        return roleRepository.findById(id).orElse(null);
    }



}
