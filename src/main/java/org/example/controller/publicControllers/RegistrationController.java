package org.example.controller.publicControllers;

import org.example.model.UserEntity;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    @GetMapping("/auth/registration")
    public String showRegistrationForm() {
        return "/auth/registration";
    }

    @PostMapping("/auth/registration")
    public String registration( @RequestParam("username") String username,
                                @RequestParam("password") String password,
                               @RequestParam("name") String name,
                               @RequestParam("surname") String surname,
                               @RequestParam("telephone") String telephone,
                               @RequestParam("email") String email, Model model) {
        try {
            UserEntity user = new UserEntity();
            user.setLog(username);
            user.setPassword(password);
            user.setName(name);
            user.setSurname(surname);
            user.setTelephone(telephone);
            user.setEMail(email);
            user.setGender(true);
            userRepository.save(user);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Произошла ошибка");
            return "/auth/registration";
        }

        return "redirect:/";
    }
}
