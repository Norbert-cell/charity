package pl.coderslab.charity.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.entity.Token;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.TokenRepository;
import pl.coderslab.charity.service.MailService;
import pl.coderslab.charity.service.UserService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ForgotPasswordController {

    private final UserService userService;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    public ForgotPasswordController(UserService userService, TokenRepository tokenRepository, PasswordEncoder passwordEncoder, MailService mailService) {
        this.userService = userService;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }


    @GetMapping("/forgot")
    public String forgotPage(){
    return "forgot-password/forgotPassword";
    }

    @PostMapping("/forgot")
    public String getEmail(HttpServletRequest request, Model model){
        String email = request.getParameter("username");
        Optional<User> optionalUser= userService.findUserByEmail(email);
        if (!optionalUser.isPresent()){
            model.addAttribute("errorMessage", "Nie znaleziono takiego meila w naszym serwisie!");
        } else {
            User user = optionalUser.get();

            Token token = new Token();
            token.setUser(user);
            token.setValue(UUID.randomUUID().toString());
            token.setExpiryDate(30);

            String subject = "Charity application. Reset hasła.";
            String text = "Link resetujacy haslo dla " + user.getUsername() + ". Kliknij w niego aby zresetowac haslo! ";
            String url = " http://localhost:8080/reset?value="+token.getValue();

            tokenRepository.save(token);

            try {
                mailService.sendMail(user.getUsername(),subject,text + url,false);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            model.addAttribute("successMessage", "Na meila " + user.getUsername() + " zostal wyslany link do zresetowania hasla" );
        }
        return "forgot-password/forgotPassword";
    }

    @GetMapping("/reset")
    public String resetPassword(@RequestParam("value") String value, Model model){
        Optional<Token> token = tokenRepository.findByValue(value);

        if (token.isPresent()){
            model.addAttribute("resetToken", token.get().getValue());
        }else {
            model.addAttribute("errorToken", "Token nie istnieje");
        }

        return "forgot-password/resetPassword";
    }

    @PostMapping("/reset")
    public String resetPass(HttpServletRequest request, Model model){
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String resetToken = request.getParameter("token");
        if (password.equals(password2)){
            Optional<Token> optionalToken = tokenRepository.findByValue(resetToken);
            if (optionalToken.isPresent()){
                Token token = optionalToken.get();
                User user = token.getUser();
                user.setPassword(passwordEncoder.encode(password));
                userService.saveUser(user);
                tokenRepository.delete(token);
            }
        } else {
            model.addAttribute("notMatchPassword", "Hasla sie nie zgadzaja!");
            model.addAttribute("token", resetToken);
            return "forgot-password/resetPassword";
        }
        return "redirect:/login";
    }
}
