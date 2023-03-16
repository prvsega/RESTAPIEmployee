package by.prvsega.restservice.contollers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String loginPage (){

        return "/auth/login";
    }

    @GetMapping("/test")
    public String testPage(){

        return "/auth/test";
    }

    @GetMapping("/user")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String userPage(){

        return "/auth/role/user";
    }

    @GetMapping("/admin")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String adminPage(){

        return "/auth/role/admin";
    }


    @GetMapping("/manager")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public String managerPage(){

        return "/auth/role/manager";
    }
    @GetMapping("/am")
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')")
    public String togetherPage(){

        return "/auth/role/am";
    }

}
