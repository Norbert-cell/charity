package pl.coderslab.charity.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.*;
import pl.coderslab.charity.repository.TokenRepository;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;
import pl.coderslab.charity.validator.EditUserValidationGroup;
import pl.coderslab.charity.validator.RegistryUserValidationGroup;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;
    private final CategoryService categoryService;


    public AdminController(InstitutionService institutionService, DonationService donationService, UserService userService, PasswordEncoder passwordEncoder, TokenRepository tokenRepository, CategoryService categoryService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
        this.categoryService = categoryService;
    }


    @ModelAttribute("institutions")
    public List<Institution> institutionList(){
        return institutionService.findAll();
    }

    @ModelAttribute("adminName")
    public String adminName(Principal principal){
        return userService.getPrincipalName(principal.getName());
    }

    @GetMapping("/")
    public String get(Model model){
        model.addAttribute("sumUser", userService.findAllWhereRole(Role.ROLE_USER).size());
        model.addAttribute("sumAdmin", userService.findAllWhereRole(Role.ROLE_ADMIN).size());
        model.addAttribute("sumFoundation", institutionService.findAll().size());
        model.addAttribute("sumAllGifts", donationService.sumAllDonationWhereStatusIs(Status.DELIVERED));
    return "admin/index";
    }

    @GetMapping("/charity")
    public String charity(){
    return "admin/charityList";
    }

    @GetMapping("/charity/add")
    public String addInstitution(Model model){
        model.addAttribute("institution", new Institution());
    return "admin/addCharity";
    }

    @PostMapping("/charity/add")
    public String addInstitution(@ModelAttribute @Valid Institution institution, BindingResult result){
        if (result.hasErrors()){
            return "admin/addCharity";
        }
        institutionService.save(institution);
    return "redirect:/admin/charity";
    }


    @GetMapping("/charity/details/{institutionId}")
    public String detailsCharity(@PathVariable long institutionId, Model model){
        Optional<Institution> optionalInstitution = institutionService.findById(institutionId);
        if (optionalInstitution.isPresent()){
            model.addAttribute("donation",donationService.sumAllDonationForInputInstitution(institutionId));
            model.addAttribute("institution", optionalInstitution.get());
        }
    return "admin/charityDetails";
    }

    @GetMapping("/charity/edit/{institutionId}")
    public String editCharity(@PathVariable long institutionId, Model model){
        Optional<Institution> optionalInstitution = institutionService.findById(institutionId);
        optionalInstitution.ifPresent(institution -> model.addAttribute("institution", institution));
    return "admin/editCharity";
    }

    @PostMapping("/charity/edit")
    public String editCharity(@ModelAttribute @Valid Institution institution, BindingResult result){
        if (result.hasErrors()){
            return "admin/editCharity";
        }
        institutionService.save(institution);
        return "redirect:/admin/charity";
    }

    @GetMapping("/charity/remove/{institutionId}")
    public String removeCharity(@PathVariable long institutionId){
        institutionService.remove(institutionId);
        return "redirect:/admin/charity";
    }

    @GetMapping("/list/admin")
    public String adminList(Model model){
        model.addAttribute("users", userService.findAllWhereRole(Role.ROLE_ADMIN));
        return "admin/adminList";
    }

    @GetMapping("/list/user")
    public String userList(Model model){
        model.addAttribute("users", userService.findAllWhereRole(Role.ROLE_USER));
    return "admin/userList";
    }

    @GetMapping("/registry")
    public String registryAdmin(Model model){
        model.addAttribute("user", new User());
    return "admin/registryAdmin";
    }

    @PostMapping("/registry")
    public String registry(@ModelAttribute @Validated(RegistryUserValidationGroup.class) User user, BindingResult result,
                           HttpServletRequest request, Model model){
        String password2 = request.getParameter("password2");
        if (result.hasErrors() && !user.getPassword().equals(password2)){
            if (!user.getPassword().equals(password2)){
                model.addAttribute("invalidPassword", "Hasla sie nie zgadzaja!");
            }
            return "admin/registryAdmin";
        }
        user.setAccountNonLocked(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_ADMIN);
        user.setEnabled(true);
        userService.saveUser(user);
    return "redirect:/admin/";
    }

    @GetMapping("/details/user/{userId}")
    public String userDetails(@PathVariable long userId, Model model){
        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            model.addAttribute("user",user);
            return "admin/userDetails";
        }
    return "notFound";
    }

    @GetMapping("/details/admin/{userId}")
    public String adminDetails(@PathVariable long userId, Model model){
        Optional<User> optionalAdmin = userService.findById(userId);
        if (optionalAdmin.isPresent()) {
            User user = optionalAdmin.get();
            model.addAttribute("user",user );
        } else {
            return "errors/404";
        }
        return "admin/adminDetails";
    }

    @GetMapping("/edit/{userId}")
    public String editUser(@PathVariable long userId, Model model){
        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isPresent()) {
            model.addAttribute("user",optionalUser.get() );
        } else {
            return "errors/404";
        }
    return "admin/editUser";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute @Validated(EditUserValidationGroup.class) User user, BindingResult result){
        if (result.hasErrors()){
            return "admin/editUser";
        }
        userService.saveUser(user);
    return "redirect:/admin/";
    }

    @GetMapping("/remove/{userId}")
    public String removeUser(@PathVariable long userId, Principal principal, Model model){
        Optional<User> optionalUser = userService.findUserByEmail(principal.getName());
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if (user.getId() != userId) {
                Optional<User> userToRemove = userService.findById(userId);
                if (userToRemove.isPresent()) {
                    Optional<Token> byUser = tokenRepository.findByUser(userToRemove.get());
                    byUser.ifPresent(tokenRepository::delete);
                    userService.remove(userToRemove.get());
                    return "redirect:/admin/list/user/";
                }
            } else {
                model.addAttribute("error", "Przykro nam ale nie możesz siebie usunać!");
                return "errors/deleteUser";
            }
        }
    return "redirect:/admin/";
    }

    @GetMapping("/block/{userId}")
    public String blockUser(@PathVariable long userId){
        Optional<User> optionalUser = userService.findById(userId);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if (user.getRole().equals(Role.ROLE_USER)) {
                userService.blockUser(user.getId());
            } else {
                return "redirect:/admin/";
            }

        }
    return "redirect:/admin/details/user/"+userId;
    }

    @GetMapping("/send-charity/{institutionId}")
    public String sendCharity(@PathVariable long institutionId, Model model){
        Optional<Institution> optionalInstitution = institutionService.findById(institutionId);
        if (optionalInstitution.isPresent()) {
            Institution institution = optionalInstitution.get();

            int countGifts = 0;
            int countBags = 0;

            Map<Integer, Integer> giftsAndBags = donationService.sendAllPackageToInstitutionAndSetStatus(institution, Status.RECEIVED);

            for (Map.Entry<Integer, Integer> entry : giftsAndBags.entrySet()) {
               countGifts =  entry.getKey();
               countBags = entry.getValue();
            }

            model.addAttribute("institution", institution);
            model.addAttribute("countGifts", countGifts);
            model.addAttribute("countBags", countBags);
        } else {
            return "errors/404";
        }
    return "admin/succesSendGifts";
    }

    @GetMapping("/categories")
    public String listCategory(Model model){
        model.addAttribute("categories",categoryService.findAll());
    return "admin/categoryList";
    }

    @GetMapping("/category/add")
    public String addCategory(Model model){
        model.addAttribute("category", new Category());
    return "admin/addCategory";
    }

    @PostMapping("/category/add")
    public String saveCategory(@ModelAttribute @Valid Category category, BindingResult result){
        if (result.hasErrors()) {
            return "admin/addCategory";
        }
        category.setVisible(true);
        categoryService.save(category);
    return "redirect:/admin/categories";
    }

    @GetMapping("/category/remove/{categoryId}")
    public String removeCategory(@PathVariable long categoryId){
        Optional<Category> optionalCategory = categoryService.findById(categoryId);
        if (optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            category.setVisible(false);
            categoryService.update(category);
        }
    return "redirect:/admin/categories";
    }

    @GetMapping("/category/edit/{categoryId}")
    public String editCategory(@PathVariable long categoryId, Model model){
        Optional<Category> optionalCategory = categoryService.findById(categoryId);
        if (optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            model.addAttribute("category",category);
            return "admin/editCategory";
        }
    return "errors/404";
    }

    @PostMapping("/category/edit")
    public String editCategory(@ModelAttribute @Valid Category category, BindingResult result){
        if (result.hasErrors()) {
            return "admin/editCategory";
        }
        categoryService.save(category);
    return "redirect:/admin/categories";
    }
}
