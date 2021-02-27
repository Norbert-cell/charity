package pl.coderslab.charity.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.entity.Token;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.TokenRepository;
import pl.coderslab.charity.service.MailService;
import pl.coderslab.charity.service.UserService;
import pl.coderslab.charity.validator.ChangePasswordValidationGroup;

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
        if (optionalUser.isEmpty()){
            model.addAttribute("errorMessage", "Nie znaleziono takiego meila w naszym serwisie!");
        } else {
            User user = optionalUser.get();

            Token token = new Token();
            token.setUser(user);
            token.setValue(UUID.randomUUID().toString());
            token.setExpiryDate(30);

            String scheme = request.getScheme();
            String serverName = request.getServerName();
            int serverPort = request.getServerPort();

            String url = "<a href=\"" + scheme+"://"+serverName+":" + serverPort + "/reset?value="+token.getValue() + "\">Kliknij</a></h3>";

            String subject = "Charity application. Reset hasła.";
            String text = "</h3>Link resetujacy haslo dla " + user.getUsername() + ". Kliknij w niego aby zresetowac haslo!";

            tokenRepository.save(token);

            try {
                mailService.sendMail(user.getUsername(),subject,text + url,true);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            model.addAttribute("successMessage", "Na meila " + user.getUsername() + " zostal wyslany link do zresetowania hasla" );
        }
        return "forgot-password/succesSendMail";
    }

    @GetMapping("/reset")
    public String resetPassword(@RequestParam("value") String value, Model model){
        Optional<Token> token = tokenRepository.findByValue(value);

        if (token.isEmpty()){
            model.addAttribute("errorToken", "Token nie istnieje");
            return "forgot-password/errorToken";
        }else {
            Token tkn = token.get();
            model.addAttribute("resetToken", tkn.getValue());
            model.addAttribute("user", tkn.getUser());
        }

        return "forgot-password/resetPassword";
    }

    @PostMapping("/reset")
    public String resetPass(@ModelAttribute @Validated(ChangePasswordValidationGroup.class) User user,
                            BindingResult result, HttpServletRequest request, Model model){
        String resetToken = request.getParameter("resetToken");
        if (result.hasErrors()){
            return "redirect:/reset?value="+resetToken;
        }

        String password2 = request.getParameter("password2");
        Optional<Token> optionalToken = tokenRepository.findByValue(resetToken);

        if (user.getPassword().equals(password2)){
            if (optionalToken.isPresent()){
                Token token = optionalToken.get();
                userService.updatePassword(passwordEncoder.encode(user.getPassword()), token.getUser().getId());
                tokenRepository.delete(token);
            }
        } else {
            model.addAttribute("notMatchPassword", "Hasla sie nie zgadzaja!");
            model.addAttribute("resetToken", resetToken);
            return "forgot-password/resetPassword";
        }
        return "redirect:/login";
    }
}
