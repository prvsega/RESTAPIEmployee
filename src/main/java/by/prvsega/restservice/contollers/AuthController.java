package by.prvsega.restservice.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String loginPage() {
        return "/auth/login";
    }

    @GetMapping("/test")
    public String testPage() {
        return "/auth/test";
    }

    @GetMapping("/user")
    public String userPage() {
        return "/auth/role/user";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "/auth/role/admin";
    }

    @GetMapping("/manager")
    public String managerPage() {
        return "/auth/role/manager";
    }

    @GetMapping("/am")
    public String togetherPage() {
        return "/auth/role/am";
    }

}
