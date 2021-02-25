package pl.coderslab.charity.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.UserService;
import pl.coderslab.charity.validator.ChangePasswordValidationGroup;
import pl.coderslab.charity.validator.EditUserValidationGroup;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/app/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute("fullName")
    public String fullName(Principal principal){
        return userService.getPrincipalName(principal.getName());
    }

    @GetMapping("/details")
    public String userDetails(Model model, Principal principal){
        model.addAttribute("user", userService.findUserByEmail(principal.getName()).orElse(new User()));
    return "user/details";
    }

    @GetMapping("/edit")
    public String editUser(Principal principal, Model model){
        model.addAttribute("user", userService.findUserByEmail(principal.getName()).orElse(new User()));
    return "user/edit";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute @Validated(EditUserValidationGroup.class) User user, BindingResult result){
        if (result.hasErrors()){
            return "user/edit";
        }
        userService.saveUser(user);
    return "redirect:/app/user/details";
    }

    @GetMapping("/pass")
    public String changePassword(Model model, Principal principal) {
        model.addAttribute("user", userService.findUserByEmail(principal.getName()));
    return "user/changePass";
    }

    @PostMapping("/pass")
    public String changePassword(@ModelAttribute @Validated(ChangePasswordValidationGroup.class) User user,BindingResult result,
                                 HttpServletRequest request, Model model){
        if (result.hasErrors()){
            return "user/changePass";
        }
        String password2 = request.getParameter("password2");
        if (!user.getPassword().equals(password2)){
            model.addAttribute("errorMessage", "Hasła sie nie zgadzaja!");
            return "user/changePass";
        }
        userService.updatePassword(passwordEncoder.encode(user.getPassword()),user);
        return "user/succesChangePassword";
    }

}
