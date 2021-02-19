package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class LoginController {

    private final UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public LoginController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login(){
    return "authenticated/login";
    }

    @PostMapping("/login")
    public String successLogin(@RequestParam boolean error, Model model, Principal principal){
        if(error) {
            model.addAttribute("errorMessage", "Nie poprawny email lub haslo.");
            return "authenticated/login";
        }
        Role role = userService.checkAuthority(principal.getName());
        if (role.equals(Role.ROLE_ADMIN)){
            return "redirect:/admin/";
        }
        return "redirect:/app/";
    }

    @GetMapping("/registry")
    public String registry(Model model){
        model.addAttribute("user", new User());
    return "authenticated/registry";
    }

    @PostMapping("/registry")
    public String registry(@ModelAttribute @Valid User user, BindingResult result){
        if (result.hasErrors()){
            return "authenticated/registry";
        }
        user.setRole(Role.ROLE_USER);
        user.setAccountNonLocked(true);
        String passw = passwordEncoder.encode(user.getPassword());
        user.setPassword(passw);
        userService.saveUser(user);
    return "redirect:/login";
    }

}
