package org.example.controller.adminControllers;

import lombok.extern.log4j.Log4j2;
import org.example.model.UserEntity;
import org.example.repository.UserRepository;
import org.example.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Controller
@RequestMapping("/admin/users")
public class UsersAdminController {

    final
    UserDetailsServiceImpl userRepository;

    @Autowired
    public UsersAdminController(UserDetailsServiceImpl userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String showUsers(@RequestParam(defaultValue = "0") int page, Model model) {
        int pageSize = 1;
        Page<UserEntity> userPage = userRepository.findAllPage(page, pageSize);
        List<UserEntity> users = userPage.getContent();
        log.warn(userPage);
        log.warn(users);
        log.warn(userPage.getTotalPages());

        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());

        return "admin/users";
    }

    @PostMapping
    public String nextPageUsers(@RequestParam("page") int page) {
        return "redirect:/admin/users?page=" + page;
    }


    @GetMapping("/{idUser}")
    public String blockDetails(@PathVariable(value = "idUser") Integer id, Model model) {
        Optional<UserEntity> user = userRepository.findById(id);

        System.out.println(id);
        ArrayList<UserEntity> res = new ArrayList<>();
        user.ifPresent(res::add);
        model.addAttribute("user", res);
        return "admin/user-details";
    }

    @PostMapping("/{idUser}")
    public String updateUser(@PathVariable("idUser") Integer idUser,
                             @RequestParam("log") String login,
                             @RequestParam("password") String password,
                             @RequestParam("name") String name,
                             @RequestParam("surname") String surname,
                             @RequestParam("telephone") String telephone,
                             @RequestParam("email") String email, Model model) {
        try {
            UserEntity user = userRepository.findById(idUser).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + idUser));
            user.setLog(login);
            user.setPassword(password);
            user.setName(name);
            user.setSurname(surname);
            user.setTelephone(telephone);
            user.setEMail(email);
            userRepository.save(user);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Произошла ошибка");
            log.error(e);
            return "admin/user-details";
        }
        return "redirect:/admin/users";
    }

    @PostMapping("/{idUser}/delete")
    public String deleteUser(@PathVariable("idUser") Integer userId, Model model) {
        try {
            Optional<UserEntity> user = userRepository.findById(userId);
            userRepository.delete(user.get());
            model.addAttribute("successMessage", "Пользователь " + userId + " удален");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Произошла ошибка");
            log.error(e);
        }
        return "redirect:/admin/users";
    }


}
