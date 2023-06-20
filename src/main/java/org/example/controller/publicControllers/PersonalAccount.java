package org.example.controller.publicControllers;

import lombok.extern.log4j.Log4j2;
import org.example.model.UserEntity;
import org.example.repository.UserRepository;
import org.example.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Log4j2
@RequestMapping("/personal_account")
@Controller
public class PersonalAccount {

    final
    UserRepository userRepository;

    @Autowired
    public PersonalAccount(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String showPersonalAccount(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("us", userRepository.findFirstByLog(userDetails.getUsername()).get());
        return "public/personalAccount";
    }

    @PostMapping
    public String updateUserPublic(@RequestParam("password") String password,
                                   @RequestParam("passwordRepeat") String passwordRepeat,
                                   @RequestParam("name") String name,
                                   @RequestParam("surname") String surname,
                                   @RequestParam("telephone") String telephone,
                                   @RequestParam("email") String email, Model model,
                                   @RequestParam("gender") Boolean gender,
                                   @RequestParam("address") String address,
                                   @RequestParam("city") String city,
                                   @RequestParam(name = "birthday",required = false) Optional<String> birthday,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            if (!passwordRepeat.equals(password)) {
                model.addAttribute("errorMessage", "Разные пароли");
                model.addAttribute("us", userRepository.findFirstByLog(userDetails.getUsername()).get());
                return "public/personalAccount";
            }
            UserEntity user = userRepository.findFirstByLog(userDetails.getUsername()).get();
            if (birthday.isPresent()&& !birthday.get().isEmpty()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date parsedDate = dateFormat.parse(birthday.get());
                    user.setBirthday(parsedDate);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
            user.setPassword(password);
            user.setCity(city);
            user.setName(name);
            user.setSurname(surname);
            user.setTelephone(telephone);
            user.setEMail(email);
            user.setGender(gender);
            user.setAddress(address);
            userRepository.save(user);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Произошла ошибка");
            log.error(e);
            return "public/personalAccount";
        }
        return "redirect:/";
    }
}
