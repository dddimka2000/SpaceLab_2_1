package org.example.controller.adminControllers;

import lombok.extern.log4j.Log4j2;
import org.example.model.DistributionEntity;
import org.example.model.UserEntity;
import org.example.repository.UserRepository;
import org.example.service.DistributionService;
import org.example.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/admin/mailSender")
@Log4j2
public class MailSenderAdminController {


    @Value("${path.messages}")
    String path;

    private List<UserEntity> selectUsers = new ArrayList();

    final
    MailSenderService mailSender;
    final
    UserRepository userRepository;

    final
    DistributionService distributionService;


    @Autowired
    public MailSenderAdminController(MailSenderService mailSender, UserRepository userRepository, DistributionService distributionService) {
        this.mailSender = mailSender;
        this.userRepository = userRepository;
        this.distributionService = distributionService;
    }

    @GetMapping("/selectUsers")
    public String mailSenderShowUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "/admin/mailSender/selectUsers";
    }

    @PostMapping("/selectUsers")
    public String mailSenderSend(@RequestParam(name = "myCheckbox", required = false) List<Integer> check) {
        log.info(check);
        for (int i : check) {
            selectUsers.add(userRepository.findById(i).get());
        }
        log.info(selectUsers);
        return "redirect:/admin/mailSender";
    }

    @GetMapping
    public String mailSenderShow(Model model) {
        model.addAttribute("users", userRepository.findAll());
        List<DistributionEntity> distributionEntities = distributionService.findAll();
        Integer letters = distributionEntities.stream().mapToInt(DistributionEntity::getNum_times).sum();
        if (distributionEntities.size() > 5) {
            distributionEntities = distributionEntities.subList(distributionEntities.size() - 6, distributionEntities.size() - 1);
        }
        model.addAttribute("distributionEntities", distributionEntities);
        model.addAttribute("letters", letters);

        return "/admin/mailSender/mailSend";
    }

    Double percents = 0.d;

    @PostMapping("/send")
    public String mailSenderShowPost(@RequestParam("choiceUsers") Optional<Boolean> choiceUsers,
                                     @RequestParam(name = "htmlLetter", required = false) MultipartFile htmlLetter,
                                     @RequestParam(name = "checkbox_distributionEntities", required = false) List<Integer> checkId,
                                     @RequestParam(name = "deleteItem", required = false) Optional<Integer> integer) {
        if (integer.isPresent()) {
            distributionService.delete(distributionService.findById(integer.get()).get());
            return "redirect:/admin/mailSender";
        }
        List<UserEntity> users;
        if (choiceUsers.isPresent() && choiceUsers.get() == true) {
            users = userRepository.findAll();
        } else {
            users = selectUsers;
        }

        DistributionEntity distributionEntity;
        if (!htmlLetter.isEmpty()) {
            distributionEntity = new DistributionEntity();
            String uuidFile;
            String resultFilename;
            String mainImaginePath;
            String htmlContent;
            if (!htmlLetter.isEmpty()) {
                uuidFile = UUID.randomUUID().toString();
                resultFilename = uuidFile + "." + htmlLetter.getOriginalFilename();
                try {
                    htmlLetter.transferTo(new File(path + resultFilename));
                    htmlContent = new String(Files.readAllBytes(Paths.get(path + resultFilename)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                mainImaginePath = "/messages/" + resultFilename;
                distributionEntity.setPath(mainImaginePath);
                distributionEntity.setName(htmlLetter.getOriginalFilename());
                AtomicInteger count = new AtomicInteger(0);
                final String finalHtmlContent = htmlContent;
                Integer dif = (int) users.stream().filter(user -> user.getEMail() == null || user.getEMail() == ""
            || user.getEMail().isBlank()).count();

//                users.stream()
//                        .filter(user -> user.getEMail() != null && !user.getEMail().isEmpty())
//                        .forEach(user -> {
//                            mailSender.sendSimpleMail(user.getEMail(), htmlLetter.getName(), finalHtmlContent);
//                            count.incrementAndGet();
//                            synchronized (percentsLock) {
//                                session.setAttribute("percents", (((double) (count.get() / (users.size() - dif))) * 100));
//                                log.info((((double) (count.get() / (users.size() - dif))) * 100));
//                            }
//                        });
                for (UserEntity user : users) {
                    Optional<UserEntity> optional = Optional.of(user);
                    if (optional.isPresent() && optional.get().getEMail() != null && !optional.get().getEMail().isBlank()
                            && optional.get().getEMail() != "") {
                        mailSender.sendSimpleMail(user.getEMail(), htmlLetter.getName(), finalHtmlContent);
                        count.incrementAndGet();
                        log.info((users.size() - dif));
                        log.info(count.get());
                        percents = count.get() / (double) (users.size() - dif);
                        log.info(percents);
                    }

                }
                distributionEntity.setNum_times(count.get());
                distributionService.save(distributionEntity);
            }
        } else if (!checkId.isEmpty()) {
            String htmlContent;
            for (int num : checkId) {
                distributionEntity = distributionService.findById(num).get();
                try {
                    htmlContent = new String(Files.readAllBytes(Paths.get(path + distributionEntity.getPath().replace("/messages/", ""))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                AtomicInteger count = new AtomicInteger(distributionEntity.getNum_times());
                final String finalHtmlContent = htmlContent;
                Integer dif = (int) users.stream().filter(user -> user.getEMail() == null || user.getEMail().isBlank()
                        || user.getEMail() == "").count();
                AtomicInteger i = new AtomicInteger(0);
                for (UserEntity user : users) {
                    Optional<UserEntity> optional = Optional.of(user);
                    if (optional.isPresent() && optional.get().getEMail() != null && !optional.get().getEMail().isBlank() && optional.get().getEMail() != "") {
                        mailSender.sendSimpleMail(user.getEMail(), htmlLetter.getName(), finalHtmlContent);
                        count.incrementAndGet();
                        i.incrementAndGet();
                        percents = i.get() / (double) (users.size() - dif);
                        log.info(percents);
                    }

                }

                distributionEntity.setNum_times(count.get());
                distributionService.save(distributionEntity);
            }
        }


        return "redirect:/admin/mailSender";
    }

    @GetMapping("/getPercents")
    @ResponseBody
    public Integer getPercents() {
        Integer newInt = (int) (percents * 100);
        return newInt;
    }
}
