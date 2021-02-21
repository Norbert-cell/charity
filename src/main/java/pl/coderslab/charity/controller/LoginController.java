package pl.coderslab.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.Token;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.TokenRepository;
import pl.coderslab.charity.service.MailService;
import pl.coderslab.charity.service.UserService;
import pl.coderslab.charity.validator.RegistryUserValidationGroup;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

@Controller
public class LoginController {

    private final UserService userService;
    private PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final MailService mailService;

    @Autowired
    public LoginController(UserService userService, PasswordEncoder passwordEncoder, TokenRepository tokenRepository, MailService mailService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
        this.mailService = mailService;
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
    public String registry(@ModelAttribute @Validated(RegistryUserValidationGroup.class) User user, BindingResult result,
                           HttpServletRequest request, Model model){
        String password2 = request.getParameter("password2");
        if (result.hasErrors() && !user.getPassword().equals(password2)){
            if (!user.getPassword().equals(password2)){
                model.addAttribute("invalidPassword", "Hasla sie nie zgadzaja!");
            }
            return "authenticated/registry";
        }
        user.setRole(Role.ROLE_USER);
        user.setAccountNonLocked(true);
        String passw = passwordEncoder.encode(user.getPassword());
        user.setPassword(passw);
        userService.saveUser(user);
        sendToken(user);
        return "redirect:/login";
    }

    private void sendToken(User user){
        String value = UUID.randomUUID().toString();
        Token token = new Token();
        token.setValue(value);
        token.setUser(user);
        token.setExpiryDate(30);
        tokenRepository.save(token);

        String url = " http://localhost:8080/token?value="+value;
        String msg = "Link aktywacyjny do naszego portalu! Kliknij w niego ";

        try {
            mailService.sendMail(user.getUsername(),"Link aktywacyjny do portalu CharityDonation",msg + url,false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/token")
    public String checkToken(@RequestParam String value, Model model) {
        Optional<Token> optionalToken = tokenRepository.findByValue(value);
        if (optionalToken.isPresent()) {
            Token token = optionalToken.get();
            User user = token.getUser();
            user.setEnabled(true);
            userService.saveUser(user);
            tokenRepository.delete(token);
        } else {
            model.addAttribute("errorToken", "Token nie istnieje");
        }
        return "redirect:/login";
    }
}
